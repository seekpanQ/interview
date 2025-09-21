package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   按课程统计成绩排行请求参数
 * </p>
 * @author seekpan
 */
@Data
public class CourseRankingRequest {

    /**
     * 课程ID
     */
    @NotNull(message = "课程ID不能为空")
    private Integer courseId;

    /**
     * 学期（如：2023-秋）
     */
    @NotBlank(message = "学期不能为空")
    private String semester;
}