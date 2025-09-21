package com.student.score.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 *   GPA统计表实体类
 * </p>
 * @author seekpan
 */
@Entity
@Table(name = "gpa_statistics")
@Data
public class GPAStatisticsDO {

    /**
     * 统计记录唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 学期（如：2023-秋）
     */
    private String semester;

    /**
     * 该学期GPA
     */
    private BigDecimal totalGpa;

    /**
     * 修读课程数
     */
    private Integer courseCount;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}