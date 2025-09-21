package com.student.score.service.impl;

import com.student.score.dto.response.*;
import com.student.score.entity.*;
import com.student.score.repository.*;
import com.student.score.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 报表服务实现类
 * </p>
 *
 * @author seekpan
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ScoreRecordRepository scoreRecordRepository;

    private final StudentInfoRepository studentInfoRepository;

    private final ClassInfoRepository classInfoRepository;

    private final CourseInfoRepository courseInfoRepository;

    private final GpaStatisticsRepository gpaStatisticsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object generateTranscript(Integer studentId, String semester) {
        // 查询该学生在指定学期的成绩记录
        List<ScoreRecord> records = scoreRecordRepository.findByStudentIdAndSemester(studentId, semester);
        if (records.isEmpty()) {
            // 表示没有找到对应记录
            return null;
        }
        // 获取学生基本信息
        Optional<StudentInfo> studentOpt = studentInfoRepository.findById(studentId);
        if (!studentOpt.isPresent()) {
            return null;
        }
        StudentInfo student = studentOpt.get();
        // 获取班级信息
        Optional<ClassInfo> classOpt = classInfoRepository.findById(student.getClassId());
        String className = "";
        if (classOpt.isPresent()) {
            className = classOpt.get().getClassName();
        }
        TranscriptResponse response = new TranscriptResponse();
        response.setStudentName(student.getStudentName());
        response.setClassName(className);
        response.setSemester(semester);
        List<TranscriptResponse.CourseScoreDetail> details = records.stream().map(record -> {
            // 获取课程信息
            Optional<CourseInfo> courseOpt = courseInfoRepository.findById(record.getCourseId());
            CourseInfo course = courseOpt.orElse(new CourseInfo());
            TranscriptResponse.CourseScoreDetail detail = new TranscriptResponse.CourseScoreDetail();
            detail.setCourseName(course.getCourseName());
            detail.setUsualScore(record.getUsualScore());
            detail.setFinalScore(record.getFinalScore());
            detail.setTotalScore(record.getTotalScore());
            detail.setCredits(course.getCredits());
            return detail;
        }).collect(Collectors.toList());
        response.setCourseScores(details);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object generateClassRanking(Integer classId, String semester) {
        // 查询该班级在指定学期的所有成绩记录
        List<ScoreRecord> records = scoreRecordRepository.findRecordsOfClassAndSemester(classId, semester);
        if (records.isEmpty()) {
            // 表示没有找到对应记录
            return null;
        }
        // 获取班级信息
        Optional<ClassInfo> classOpt = classInfoRepository.findById(classId);
        if (!classOpt.isPresent()) {
            return null;
        }
        // 计算每个学生的综合成绩总和及数量
        Map<Integer, List<ScoreRecord>> groupedByStudent = records.stream().collect(Collectors.groupingBy(ScoreRecord::getStudentId));
        List<Map.Entry<Integer, List<ScoreRecord>>> sortedEntries = groupedByStudent.entrySet().stream().sorted((a, b) -> {
            BigDecimal sumA = a.getValue().stream().map(ScoreRecord::getTotalScore).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal sumB = b.getValue().stream().map(ScoreRecord::getTotalScore).reduce(BigDecimal.ZERO, BigDecimal::add);
            // 降序排列
            return sumB.compareTo(sumA);
        }).limit(// 取前10名
                10).collect(Collectors.toList());
        // 构建返回数据
        ClassRankingResponse rankingResponse = new ClassRankingResponse();
        rankingResponse.setClassName(classOpt.get().getClassName());
        rankingResponse.setSemester(semester);
        List<ClassRankingResponse.StudentRankInfo> studentRankings = new ArrayList<>();
        int rank = 1;
        for (Map.Entry<Integer, List<ScoreRecord>> entry : sortedEntries) {
            Integer studentId = entry.getKey();
            List<ScoreRecord> studentRecords = entry.getValue();
            // 计算平均成绩
            BigDecimal avgScore = studentRecords.stream().map(ScoreRecord::getTotalScore).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(studentRecords.size()), 2, RoundingMode.HALF_UP);
            // 获取学生姓名
            Optional<StudentInfo> studentOpt = studentInfoRepository.findById(studentId);
            String studentName = "";
            if (studentOpt.isPresent()) {
                studentName = studentOpt.get().getStudentName();
            }
            ClassRankingResponse.StudentRankInfo info = new ClassRankingResponse.StudentRankInfo();
            info.setStudentName(studentName);
            info.setTotalScore(avgScore);
            info.setRank(rank++);
            studentRankings.add(info);
        }
        rankingResponse.setTopStudents(studentRankings);
        return rankingResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object generateCourseDistribution(Integer courseId, String semester) {
        // 查询该课程在指定学期的所有成绩记录
        List<ScoreRecord> records = scoreRecordRepository.findByCourseIdAndSemester(courseId, semester);
        if (records.isEmpty()) {
            // 表示没有找到对应记录
            return null;
        }
        // 获取课程信息
        Optional<CourseInfo> courseOpt = courseInfoRepository.findById(courseId);
        if (!courseOpt.isPresent()) {
            return null;
        }
        // 将成绩划分到不同分数段并统计人数
        Map<String, Long> distribution = new LinkedHashMap<>();
        distribution.put("90~100", 0L);
        distribution.put("80~89", 0L);
        distribution.put("70~79", 0L);
        distribution.put("60~69", 0L);
        distribution.put("below_60", 0L);
        for (ScoreRecord record : records) {
            BigDecimal score = record.getTotalScore();
            if (score.compareTo(BigDecimal.valueOf(90)) >= 0 && score.compareTo(BigDecimal.valueOf(100)) <= 0) {
                distribution.put("90~100", distribution.get("90~100") + 1);
            } else if (score.compareTo(BigDecimal.valueOf(80)) >= 0 && score.compareTo(BigDecimal.valueOf(89)) <= 0) {
                distribution.put("80~89", distribution.get("80~89") + 1);
            } else if (score.compareTo(BigDecimal.valueOf(70)) >= 0 && score.compareTo(BigDecimal.valueOf(79)) <= 0) {
                distribution.put("70~79", distribution.get("70~79") + 1);
            } else if (score.compareTo(BigDecimal.valueOf(60)) >= 0 && score.compareTo(BigDecimal.valueOf(69)) <= 0) {
                distribution.put("60~69", distribution.get("60~69") + 1);
            } else {
                distribution.put("below_60", distribution.get("below_60") + 1);
            }
        }
        CourseDistributionResponse response = new CourseDistributionResponse();
        response.setCourseName(courseOpt.get().getCourseName());
        response.setSemester(semester);
        response.setScoreDistribution(distribution);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object generateGpaStatistics(Integer studentId, String semester) {
        // 查询该学生在指定学期或所有学期的GPA记录
        List<GpaStatistics> stats = gpaStatisticsRepository.findByStudentIdAndSemester(studentId, semester);
        if (stats.isEmpty()) {
            // 表示没有找到对应记录
            return null;
        }
        // 获取学生姓名
        Optional<StudentInfo> studentOpt = studentInfoRepository.findById(studentId);
        if (!studentOpt.isPresent()) {
            return null;
        }
        // 构建返回数据
        GpaHistoryResponse historyResponse = new GpaHistoryResponse();
        historyResponse.setStudentName(studentOpt.get().getStudentName());
        List<GpaHistoryResponse.SemesterGpa> gpas = stats.stream().map(stat -> {
            GpaHistoryResponse.SemesterGpa item = new GpaHistoryResponse.SemesterGpa();
            item.setSemester(stat.getSemester());
            item.setGpa(stat.getTotalGpa());
            return item;
        }).collect(Collectors.toList());
        historyResponse.setGpaList(gpas);
        return historyResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object generateSemesterAnalysis(String semester) {
        // 查询该学期的所有成绩记录
        List<ScoreRecordDO> allRecords = scoreRecordRepository.findAll().stream().filter(r -> r.getCreateTime().toString().startsWith(semester)).collect(Collectors.toList());
        if (allRecords.isEmpty()) {
            // 表示没有找到对应记录
            return null;
        }
        // 计算各项指标
        BigDecimal totalSum = allRecords.stream().map(ScoreRecordDO::getTotalScore).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgScore = totalSum.divide(BigDecimal.valueOf(allRecords.size()), 2, RoundingMode.HALF_UP);
        BigDecimal maxScore = allRecords.stream().map(ScoreRecordDO::getTotalScore).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal minScore = allRecords.stream().map(ScoreRecordDO::getTotalScore).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        SemesterSummaryResponse summary = new SemesterSummaryResponse();
        summary.setSemester(semester);
        summary.setAvgScore(avgScore);
        summary.setMaxScore(maxScore);
        summary.setMinScore(minScore);
        summary.setTotalStudentCount(allRecords.size());
        return summary;
    }
}
