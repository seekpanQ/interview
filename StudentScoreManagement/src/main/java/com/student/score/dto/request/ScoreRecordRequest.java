package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

/**
 * <p>
 *   新增成绩记录请求对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordRequest {

    @NotNull(message = "学生ID不能为空")
    private Integer studentId;

    @NotNull(message = "课程ID不能为空")
    private Integer courseId;

    private BigDecimal usualScore;
    private BigDecimal finalScore;
}