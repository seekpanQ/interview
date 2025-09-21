package com.student.score.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

/**
 * <p>
 *   学生信息实体类
 * </p>
 * @author seekpan
 */
@Entity
@Table(name = "student_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}