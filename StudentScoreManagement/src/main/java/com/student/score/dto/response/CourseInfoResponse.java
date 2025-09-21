package com.student.score.dto.response;

import lombok.Data;

/**
 * <p>
 *   课程信息响应对象
 * </p>
 * @author seekpan
 */
@Data
public class CourseInfoResponse {

    private Integer courseId;
    private String courseName;
    private Double credits;
    private Double usualScoreRatio;
    private Double finalScoreRatio;
}