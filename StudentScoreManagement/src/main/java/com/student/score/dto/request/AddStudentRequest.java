package com.student.score.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Data;

/**
 * <p>
 *   添加学生请求参数DTO
 * </p>
 * @author seekpan
 */
@Data
public class AddStudentRequest {

    /**
     * 学生姓名 - 必填
     */
    @NotBlank(message = "学生姓名不能为空")
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