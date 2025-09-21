package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>
 *   学期分析请求参数
 * </p>
 * @author seekpan
 */
@Data
public class SemesterAnalysisRequest {

    /**
     * 学期（如：2023-秋）
     */
    @NotBlank(message = "学期不能为空")
    private String semester;
}
