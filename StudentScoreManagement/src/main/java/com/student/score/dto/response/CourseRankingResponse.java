package com.student.score.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * <p>
 *   按课程统计成绩排行响应数据
 * </p>
 * @author seekpan
 */
@Data
public class CourseRankingResponse {

    /**
     * 排行榜数据列表
     */
    private List<StudentScoreRankItem> rankingList;

    /**
     * 学生分数排名项
     */
    @Data
    public static class StudentScoreRankItem {
        /**
         * 学生ID
         */
        private Integer studentId;

        /**
         * 学生姓名
         */
        private String studentName;

        /**
         * 成绩
         */
        private BigDecimal score;

        /**
         * 排名
         */
        private Integer rank;
    }
}