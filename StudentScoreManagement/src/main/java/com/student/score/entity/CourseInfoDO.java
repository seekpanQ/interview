package com.student.score.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 * <p>
 *   课程信息实体类
 * </p>
 * @author seekpan
 */
@Entity
@Table(name = "course_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "credits", nullable = false)
    private BigDecimal credits;

    @Column(name = "usual_score_ratio")
    private BigDecimal usualScoreRatio;

    @Column(name = "final_score_ratio")
    private BigDecimal finalScoreRatio;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}