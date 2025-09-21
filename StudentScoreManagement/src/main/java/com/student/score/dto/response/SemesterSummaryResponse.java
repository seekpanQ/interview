package com.student.score.dto.response;

import java.math.BigDecimal;
import lombok.Data;

/**
 * <p>
 *   学期整体成绩分析报告响应数据结构
 * </p>
 * @author seekpan
 */
@Data
public class SemesterSummaryResponse {

    /**
     * 学期
     */
    private String semester;

    /**
     * 平均成绩
     */
    private BigDecimal avgScore;

    /**
     * 最高分
     */
    private BigDecimal maxScore;

    /**
     * 最低分
     */
    private BigDecimal minScore;

    /**
     * 总人数
     */
    private Integer totalStudentCount;
}
