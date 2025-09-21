package com.student.score.service.impl;

import com.student.score.dto.request.ScoreRecordListRequest;
import com.student.score.dto.request.ScoreRecordRequest;
import com.student.score.dto.request.ScoreRecordUpdateRequest;
import com.student.score.dto.response.ScoreRecordDTO;
import com.student.score.entity.CourseInfo;
import com.student.score.entity.CourseInfoDO;
import com.student.score.entity.ScoreRecordDO;
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

        ScoreRecordDO scoreRecordDO = new ScoreRecordDO();
        scoreRecordDO.setStudentId(request.getStudentId());
        scoreRecordDO.setCourseId(request.getCourseId());
        scoreRecordDO.setUsualScore(request.getUsualScore());
        scoreRecordDO.setFinalScore(request.getFinalScore());
        scoreRecordDO.setTotalScore(calculateTotalScore(request.getUsualScore(), request.getFinalScore(), courseInfo));
        scoreRecordDO.setCreateBy("admin");
        scoreRecordDO.setCreateTime(new Date());

        scoreRecordDO = scoreRecordRepository.save(scoreRecordDO);
        return convertToDTO(scoreRecordDO);
    }

    @Override
    @Transactional
    public ScoreRecordDTO updateScoreRecord(ScoreRecordUpdateRequest request) {
        ScoreRecordDO scoreRecordDO = scoreRecordRepository.findById(request.getRecordId()).orElse(null);

        if (scoreRecordDO == null) {
            throw new IllegalArgumentException("成绩记录不存在");
        }

        CourseInfo courseInfo = courseInfoRepository.findById(scoreRecordDO.getCourseId()).orElse(null);

        if (request.getUsualScore() != null) {
            scoreRecordDO.setUsualScore(request.getUsualScore());
        }

        if (request.getFinalScore() != null) {
            scoreRecordDO.setFinalScore(request.getFinalScore());
        }

        scoreRecordDO.setTotalScore(calculateTotalScore(scoreRecordDO.getUsualScore(), scoreRecordDO.getFinalScore(), courseInfo));
        scoreRecordDO.setUpdateBy("admin");
        scoreRecordDO.setUpdateTime(new Date());

        scoreRecordDO = scoreRecordRepository.save(scoreRecordDO);
        return convertToDTO(scoreRecordDO);
    }

    @Override
    @Transactional
    public void deleteScoreRecord(Integer recordId) {
        ScoreRecordDO scoreRecordDO = scoreRecordRepository.findById(recordId).orElse(null);

        if (scoreRecordDO == null) {
            throw new IllegalArgumentException("成绩记录不存在");
        }

        scoreRecordRepository.delete(scoreRecordDO);
    }

    @Override
    public List<ScoreRecordDTO> getScoreRecordList(ScoreRecordListRequest request) {
        List<ScoreRecordDO> scoreRecordList = scoreRecordRepository.findByConditions(request.getStudentId(), request.getCourseId());
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

    private ScoreRecordDTO convertToDTO(ScoreRecordDO scoreRecordDO) {
        ScoreRecordDTO scoreRecordDTO = new ScoreRecordDTO();
        scoreRecordDTO.setRecordId(scoreRecordDO.getRecordId());
        scoreRecordDTO.setStudentId(scoreRecordDO.getStudentId());
        scoreRecordDTO.setCourseId(scoreRecordDO.getCourseId());
        scoreRecordDTO.setUsualScore(scoreRecordDO.getUsualScore());
        scoreRecordDTO.setFinalScore(scoreRecordDO.getFinalScore());
        scoreRecordDTO.setTotalScore(scoreRecordDO.getTotalScore());
        scoreRecordDTO.setCreateBy(scoreRecordDO.getCreateBy());
        scoreRecordDTO.setCreateTime(scoreRecordDO.getCreateTime().toString());
        scoreRecordDTO.setUpdateBy(scoreRecordDO.getUpdateBy());
        scoreRecordDTO.setUpdateTime(scoreRecordDO.getUpdateTime().toString());
        return scoreRecordDTO;
    }
}