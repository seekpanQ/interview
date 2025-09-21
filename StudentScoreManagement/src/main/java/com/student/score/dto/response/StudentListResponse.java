package com.student.score.dto.response;

import java.time.LocalDate;
import lombok.Data;

/**
 * <p>
 *   学生列表响应DTO
 * </p>
 * @author seekpan
 */
@Data
public class StudentListResponse {

    /**
     * 学生唯一标识
     */
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