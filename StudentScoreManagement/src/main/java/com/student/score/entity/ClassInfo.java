package com.student.score.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   班级信息实体类
 * </p>
 * @author seekpan
 */
@NoArgsConstructor
@Entity
@Table(name = "class_info")
@AllArgsConstructor
@Data
public class ClassInfo {

    /**
     * 班级唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级年份
     */
    private Integer gradeYear;

    /**
     * 班主任姓名
     */
    private String teacherName;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
