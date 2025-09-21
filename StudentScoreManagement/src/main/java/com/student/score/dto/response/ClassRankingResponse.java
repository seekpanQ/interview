package com.student.score.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 按班级统计成绩排行响应数据
 * </p>
 *
 * @author seekpan
 */
@Data
public class ClassRankingResponse {

    /**
     * 前N名学生的成绩信息
     */
    private List<StudentRankInfo> topStudents;

    /**
     * 学期
     */
    private String semester;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 排行榜数据列表
     */
    private List<StudentScoreRankItem> rankingList;

    /**
     * 学生分数排名项
     */
    @Data
    public static class StudentRankInfo {

        /**
         * 学生ID
         */
        private Integer studentId;

        /**
         * 学生姓名
         */
        private String studentName;

        /**
         * 总分
         */
        private BigDecimal totalScore;

        /**
         * 排名
         */
        private Integer rank;

    }

    @Data
    public static class StudentScoreRankItem {
        private Integer studentId;
        private Integer rank;
        private BigDecimal totalScore;
    }
}
