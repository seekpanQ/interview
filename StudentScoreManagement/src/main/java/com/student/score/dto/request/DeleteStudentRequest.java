package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   删除学生请求参数DTO
 * </p>
 * @author seekpan
 */
@Data
public class DeleteStudentRequest {

    /**
     * 学生唯一标识 - 必填
     */
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;
}