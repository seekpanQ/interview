package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *   删除课程请求对象
 * </p>
 * @author seekpan
 */
@Data
public class DeleteCourseRequest {

    @NotNull(message = "课程ID不能为空")
    private Integer courseId;
}