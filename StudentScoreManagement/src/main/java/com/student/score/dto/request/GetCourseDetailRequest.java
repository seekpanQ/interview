package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   查询课程详情请求对象
 * </p>
 * @author seekpan
 */
@Data
public class GetCourseDetailRequest {

    @NotNull(message = "课程ID不能为空")
    private Integer courseId;
}