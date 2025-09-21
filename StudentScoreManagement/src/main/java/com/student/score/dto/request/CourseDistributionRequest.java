package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   课程成绩分布请求参数
 * </p>
 * @author seekpan
 */
@Data
public class CourseDistributionRequest {

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
