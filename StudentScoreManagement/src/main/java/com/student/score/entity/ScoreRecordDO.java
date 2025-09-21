package com.student.score.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 * <p>
 *   成绩记录实体类
 * </p>
 * @author seekpan
 */
@Entity
@Table(name = "score_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "usual_score")
    private BigDecimal usualScore;

    @Column(name = "final_score")
    private BigDecimal finalScore;

    @Column(name = "total_score")
    private BigDecimal totalScore;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}