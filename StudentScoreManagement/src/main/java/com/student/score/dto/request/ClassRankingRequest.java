package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   按班级统计成绩排行请求参数
 * </p>
 * @author seekpan
 */
@Data
public class ClassRankingRequest {

    /**
     * 班级ID
     */
    @NotNull(message = "班级ID不能为空")
    private Integer classId;

    /**
     * 学期（如：2023-秋）
     */
    @NotBlank(message = "学期不能为空")
    private String semester;
}
