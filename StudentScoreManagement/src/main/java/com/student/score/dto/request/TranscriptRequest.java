package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   成绩单请求参数
 * </p>
 * @author seekpan
 */
@Data
public class TranscriptRequest {

    /**
     * 学生ID
     */
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;

    /**
     * 学期（如：2023-秋）
     */
    @NotBlank(message = "学期不能为空")
    private String semester;
}
