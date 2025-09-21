package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

/**
 * <p>
 *   修改学生请求参数DTO
 * </p>
 * @author seekpan
 */
@Data
public class ModifyStudentRequest {

    /**
     * 学生唯一标识 - 必填
     */
    @NotNull(message = "学生ID不能为空")
    private Integer studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 所属班级ID
     */
    private Integer classId;
}