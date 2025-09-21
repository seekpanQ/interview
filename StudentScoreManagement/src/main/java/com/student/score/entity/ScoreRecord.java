package com.student.score.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *   成绩记录实体类
 * </p>
 * @author seekpan
 */
@NoArgsConstructor
@Entity
@Table(name = "score_record")
@AllArgsConstructor
@Data
public class ScoreRecord {

    /**
     * 成绩记录唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 平时成绩
     */
    private BigDecimal usualScore;

    /**
     * 期末成绩
     */
    private BigDecimal finalScore;

    /**
     * 综合成绩
     */
    private BigDecimal totalScore;

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
