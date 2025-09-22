package com.student.score.service.impl;

import com.student.score.dto.request.ScoreRecordListRequest;
import com.student.score.dto.request.ScoreRecordRequest;
import com.student.score.dto.request.ScoreRecordUpdateRequest;
import com.student.score.dto.response.ScoreRecordDTO;
import com.student.score.entity.CourseInfo;
import com.student.score.entity.ScoreRecord;
import com.student.score.entity.StudentInfo;
import com.student.score.repository.CourseInfoRepository;
import com.student.score.repository.ScoreRecordRepository;
import com.student.score.repository.StudentInfoRepository;
import com.student.score.service.ScoreRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 成绩记录服务接口实现
 * </p>
 *
 * @author seekpan
 */
@Service
@Slf4j
public class ScoreRecordServiceImpl implements ScoreRecordService {

    @Autowired
    private ScoreRecordRepository scoreRecordRepository;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Autowired
    private CourseInfoRepository courseInfoRepository;

    @Override
    @Transactional
    public ScoreRecordDTO addScoreRecord(ScoreRecordRequest request) {
        StudentInfo studentInfo = studentInfoRepository.findById(request.getStudentId()).orElse(null);
        CourseInfo courseInfo = courseInfoRepository.findById(request.getCourseId()).orElse(null);

        if (studentInfo == null || courseInfo == null) {
            throw new IllegalArgumentException("学生或课程不存在");
        }

        ScoreRecord scoreRecord = new ScoreRecord();
        scoreRecord.setStudentId(request.getStudentId());
        scoreRecord.setCourseId(request.getCourseId());
        scoreRecord.setUsualScore(request.getUsualScore());
        scoreRecord.setFinalScore(request.getFinalScore());
        scoreRecord.setTotalScore(calculateTotalScore(request.getUsualScore(), request.getFinalScore(), courseInfo));
        scoreRecord.setCreateBy("admin");
        scoreRecord.setCreateTime(LocalDateTime.now());

        scoreRecord = scoreRecordRepository.save(scoreRecord);
        return convertToDTO(scoreRecord);
    }

    @Override
    @Transactional
    public ScoreRecordDTO updateScoreRecord(ScoreRecordUpdateRequest request) {
        ScoreRecord scoreRecord = scoreRecordRepository.findById(request.getRecordId()).orElse(null);

        if (scoreRecord == null) {
            throw new IllegalArgumentException("成绩记录不存在");
        }

        CourseInfo courseInfo = courseInfoRepository.findById(scoreRecord.getCourseId()).orElse(null);

        if (request.getUsualScore() != null) {
            scoreRecord.setUsualScore(request.getUsualScore());
        }

        if (request.getFinalScore() != null) {
            scoreRecord.setFinalScore(request.getFinalScore());
        }

        scoreRecord.setTotalScore(calculateTotalScore(scoreRecord.getUsualScore(), scoreRecord.getFinalScore(), courseInfo));
        scoreRecord.setUpdateBy("admin");
        scoreRecord.setUpdateTime(LocalDateTime.now());

        scoreRecord = scoreRecordRepository.save(scoreRecord);
        return convertToDTO(scoreRecord);
    }

    @Override
    @Transactional
    public void deleteScoreRecord(Integer recordId) {
        ScoreRecord scoreRecord = scoreRecordRepository.findById(recordId).orElse(null);

        if (scoreRecord == null) {
            throw new IllegalArgumentException("成绩记录不存在");
        }

        scoreRecordRepository.delete(scoreRecord);
    }

    @Override
    public List<ScoreRecordDTO> getScoreRecordList(ScoreRecordListRequest request) {
        List<ScoreRecord> scoreRecordList = scoreRecordRepository.findByConditions(request.getStudentId(), request.getCourseId());
        return scoreRecordList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> batchImportScoreRecords(List<ScoreRecordRequest> scoreList) {
        List<String> validationFailures = new ArrayList<>();

        for (ScoreRecordRequest request : scoreList) {
            try {
                addScoreRecord(request);
            } catch (IllegalArgumentException e) {
                validationFailures.add(String.format("学生ID: %d, 课程ID: %d, 错误信息: %s", request.getStudentId(), request.getCourseId(), e.getMessage()));
            }
        }

        return validationFailures;
    }

    private BigDecimal calculateTotalScore(BigDecimal usualScore, BigDecimal finalScore, CourseInfo courseInfo) {
        BigDecimal totalScore = BigDecimal.ZERO;
        if (usualScore != null) {
            totalScore = totalScore.add(usualScore.multiply(courseInfo.getUsualScoreRatio()));
        }
        if (finalScore != null) {
            totalScore = totalScore.add(finalScore.multiply(courseInfo.getFinalScoreRatio()));
        }
        return totalScore;
    }

    private ScoreRecordDTO convertToDTO(ScoreRecord scoreRecord) {
        ScoreRecordDTO scoreRecordDTO = new ScoreRecordDTO();
        scoreRecordDTO.setRecordId(scoreRecord.getRecordId());
        scoreRecordDTO.setStudentId(scoreRecord.getStudentId());
        scoreRecordDTO.setCourseId(scoreRecord.getCourseId());
        scoreRecordDTO.setUsualScore(scoreRecord.getUsualScore());
        scoreRecordDTO.setFinalScore(scoreRecord.getFinalScore());
        scoreRecordDTO.setTotalScore(scoreRecord.getTotalScore());
        scoreRecordDTO.setCreateBy(scoreRecord.getCreateBy());
        scoreRecordDTO.setCreateTime(scoreRecord.getCreateTime().toString());
        scoreRecordDTO.setUpdateBy(scoreRecord.getUpdateBy());
        scoreRecordDTO.setUpdateTime(scoreRecord.getUpdateTime().toString());
        return scoreRecordDTO;
    }
}