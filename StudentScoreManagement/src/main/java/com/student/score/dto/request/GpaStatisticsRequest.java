package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   GPA统计请求参数
 * </p>
 * @author seekpan
 */
@Data
public class GpaStatisticsRequest {

    /**
     * 学生ID
     */
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;

    /**
     * 学期（可选，若为空则查询所有学期）
     */
    private String semester;
}
