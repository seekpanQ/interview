package com.student.score.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 成绩单响应数据结构
 * </p>
 *
 * @author seekpan
 */
@Data
public class TranscriptResponse {
    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 所属班级名称
     */
    private String className;

    /**
     * 学期
     */
    private String semester;

    /**
     * 各门课程的成绩详情
     */
    private List<CourseScoreDetail> courseScores;

    /**
     * <p>
     * 单个课程成绩详情
     * </p>
     *
     * @author seekpan
     */
    @Data
    public static class CourseScoreDetail {
        /**
         * 课程名称
         */
        private String courseName;

        /**
         * 平时成绩
         */
        private BigDecimal usualScore;

        /**
         * 期末成绩
         */
        private BigDecimal finalScore;

        /**
         * 综合成绩
         */
        private BigDecimal totalScore;

        /**
         * 学分
         */
        private BigDecimal credits;
    }
}

