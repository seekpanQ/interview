package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   获取指定学生多个学期GPA列表请求参数
 * </p>
 * @author seekpan
 */
@Data
public class GetGPAsByStudentRequest {

    /**
     * 学生ID
     */
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;
}