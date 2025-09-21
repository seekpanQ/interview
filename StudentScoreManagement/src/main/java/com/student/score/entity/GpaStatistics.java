package com.student.score.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   GPA统计实体类
 * </p>
 * @author seekpan
 */
@NoArgsConstructor
@Entity
@Table(name = "gpa_statistics")
@AllArgsConstructor
@Data
public class GpaStatistics {

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
