package com.student.score.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   新增课程请求对象
 * </p>
 * @author seekpan
 */
@Data
public class AddCourseRequest {

    @NotBlank(message = "课程名称不能为空")
    private String courseName;

    @NotNull(message = "学分不能为空")
    @DecimalMin(value = "0.0", message = "学分不能小于0.0")
    private Double credits;

    @DecimalMin(value = "0.0", message = "平时成绩占比不能小于0.0")
    @DecimalMax(value = "1.0", message = "平时成绩占比不能大于1.0")
    private Double usualScoreRatio = 0.00;

    @DecimalMin(value = "0.0", message = "期末成绩占比不能小于0.0")
    @DecimalMax(value = "1.0", message = "期末成绩占比不能大于1.0")
    private Double finalScoreRatio = 0.00;
}