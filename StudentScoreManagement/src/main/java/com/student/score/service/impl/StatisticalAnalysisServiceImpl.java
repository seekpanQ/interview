package com.student.score.service.impl;

import com.student.score.common.RestResult;
import com.student.score.dto.request.CalculateGPARequest;
import com.student.score.dto.request.ClassRankingRequest;
import com.student.score.dto.request.CourseRankingRequest;
import com.student.score.dto.request.GetGPAsByStudentRequest;
import com.student.score.dto.response.ClassRankingResponse;
import com.student.score.dto.response.CourseRankingResponse;
import com.student.score.dto.response.GPAResponse;
import com.student.score.dto.response.StudentGPAListResponse;
import com.student.score.entity.GpaStatistics;
import com.student.score.entity.ScoreRecord;
import com.student.score.repository.GpaStatisticsRepository;
import com.student.score.repository.ScoreRecordRepository;
import com.student.score.service.StatisticalAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 成绩统计分析服务实现类
 * </p>
 *
 * @author seekpan
 */
@Service
@Slf4j
public class StatisticalAnalysisServiceImpl implements StatisticalAnalysisService {

    @Autowired
    private ScoreRecordRepository scoreRecordRepository;

    @Autowired
    private GpaStatisticsRepository gpaStatisticsRepository;

    @Override
    public RestResult<ClassRankingResponse> getClassRanking(ClassRankingRequest request) {
        // 查询该班所有学生的成绩记录
        List<ScoreRecord> records = scoreRecordRepository.findScoreRecordsByClassIdAndSemester(request.getClassId(), request.getSemester());

        if (records.isEmpty()) {
            return RestResult.error("000001", "暂无成绩数据");
        }

        // 将成绩按学生分组并计算总分
        Map<Integer, List<ScoreRecord>> groupedByStudent = records.stream()
                .collect(Collectors.groupingBy(ScoreRecord::getStudentId));

        // 构建排名数据
        List<ClassRankingResponse.StudentScoreRankItem> items = new ArrayList<>();
        for (Map.Entry<Integer, List<ScoreRecord>> entry : groupedByStudent.entrySet()) {
            Integer studentId = entry.getKey();
            List<ScoreRecord> studentRecords = entry.getValue();

            // 计算该学生总分
            BigDecimal totalScore = studentRecords.stream()
                    .map(ScoreRecord::getTotalScore)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            ClassRankingResponse.StudentScoreRankItem item = new ClassRankingResponse.StudentScoreRankItem();
            item.setStudentId(studentId);
            item.setTotalScore(totalScore);
            items.add(item);
        }

        // 按照总分降序排列
        items.sort((a, b) -> b.getTotalScore().compareTo(a.getTotalScore()));

        // 添加排名
        int rank = 1;
        for (int i = 0; i < items.size(); i++) {
            if (i > 0 && !items.get(i).getTotalScore().equals(items.get(i - 1).getTotalScore())) {
                rank = i + 1;
            }
            items.get(i).setRank(rank);
        }

        ClassRankingResponse response = new ClassRankingResponse();
        response.setRankingList(items);

        return RestResult.success(response);
    }

    @Override
    public RestResult<CourseRankingResponse> getCourseRanking(CourseRankingRequest request) {
        // 查询该课程所有学生的成绩记录
        List<ScoreRecord> records = scoreRecordRepository.findScoreRecordsByCourseIdAndSemester(request.getCourseId(), request.getSemester());

        if (records.isEmpty()) {
            return RestResult.error("000001", "暂无成绩数据");
        }

        // 构建排名数据
        List<CourseRankingResponse.StudentScoreRankItem> items = records.stream()
                .map(record -> {
                    CourseRankingResponse.StudentScoreRankItem item = new CourseRankingResponse.StudentScoreRankItem();
                    item.setStudentId(record.getStudentId());
                    item.setScore(record.getTotalScore());
                    return item;
                })
                .sorted((a, b) -> b.getScore().compareTo(a.getScore()))
                .collect(Collectors.toList());

        // 添加排名
        int rank = 1;
        for (int i = 0; i < items.size(); i++) {
            if (i > 0 && !items.get(i).getScore().equals(items.get(i - 1).getScore())) {
                rank = i + 1;
            }
            items.get(i).setRank(rank);
        }

        CourseRankingResponse response = new CourseRankingResponse();
        response.setRankingList(items);

        return RestResult.success(response);
    }

    @Override
    public RestResult<GPAResponse> calculateStudentGPA(CalculateGPARequest request) {
        try {
            // 获取该学生在该学期的所有成绩记录
            List<ScoreRecord> records = scoreRecordRepository.findScoreRecordsByStudentIdAndSemester(request.getStudentId(), request.getSemester());

            if (records.isEmpty()) {
                return RestResult.error("000001", "暂无成绩数据");
            }

            // 获取课程信息用于获取学分
            Set<Integer> courseIds = records.stream()
                    .map(ScoreRecord::getCourseId)
                    .collect(Collectors.toSet());

            // 这里假设有一个方法可以获取课程信息，实际开发中应注入CourseInfoRepository进行查询
            // 示例代码省略了此部分

            // 计算加权平均分
            BigDecimal weightedSum = BigDecimal.ZERO;
            BigDecimal totalCredits = BigDecimal.ZERO;

            for (ScoreRecord record : records) {
                // 假设每个record都有对应的courseId和totalScore，以及课程的credits字段
                // 在真实环境中需要通过courseId从CourseInfoDO中查出credits
                // 此处为了演示仅使用一个固定值代替
                BigDecimal credits = BigDecimal.valueOf(3); // 应改为根据courseId查询得到的实际学分
                BigDecimal score = record.getTotalScore();

                if (score != null && credits != null) {
                    weightedSum = weightedSum.add(score.multiply(credits));
                    totalCredits = totalCredits.add(credits);
                }
            }

            BigDecimal gpa;
            if (totalCredits.compareTo(BigDecimal.ZERO) == 0) {
                gpa = BigDecimal.ZERO;
            } else {
                gpa = weightedSum.divide(totalCredits, 2, RoundingMode.HALF_UP);
            }

            // 更新或保存GPA统计数据
            List<GpaStatistics> existingRecordOpt = gpaStatisticsRepository.findByStudentIdAndSemester(request.getStudentId(), request.getSemester());

            GpaStatistics gpaRecord;
            if (!existingRecordOpt.isEmpty()) {
                gpaRecord = existingRecordOpt.get(0);
                gpaRecord.setTotalGpa(gpa);
                gpaRecord.setCourseCount(records.size());
                gpaRecord.setUpdateBy("system");
                gpaRecord.setUpdateTime(LocalDateTime.now());
            } else {
                gpaRecord = new GpaStatistics();
                gpaRecord.setStudentId(request.getStudentId());
                gpaRecord.setSemester(request.getSemester());
                gpaRecord.setTotalGpa(gpa);
                gpaRecord.setCourseCount(records.size());
                gpaRecord.setCreateBy("system");
                gpaRecord.setCreateTime(LocalDateTime.now());
                gpaRecord.setUpdateBy("system");
                gpaRecord.setUpdateTime(LocalDateTime.now());
            }

            gpaStatisticsRepository.save(gpaRecord);

            GPAResponse response = new GPAResponse();
            response.setGpa(gpa);
            response.setCourseCount(records.size());

            return RestResult.success(response);
        } catch (Exception e) {
            log.error("计算学生GPA时发生错误: ", e);
            return RestResult.error("999999", "系统异常");
        }
    }

    @Override
    public RestResult<StudentGPAListResponse> getStudentGPAs(GetGPAsByStudentRequest request) {
        List<GpaStatistics> records = gpaStatisticsRepository.findByStudentIdOrderByCreateTimeDesc(request.getStudentId());

        StudentGPAListResponse response = new StudentGPAListResponse();
        List<StudentGPAListResponse.GPARecord> gpaRecords = records.stream()
                .map(record -> {
                    StudentGPAListResponse.GPARecord r = new StudentGPAListResponse.GPARecord();
                    r.setSemester(record.getSemester());
                    r.setGpa(record.getTotalGpa());
                    r.setCourseCount(record.getCourseCount());
                    return r;
                })
                .collect(Collectors.toList());

        response.setGpaRecords(gpaRecords);

        return RestResult.success(response);
    }
}