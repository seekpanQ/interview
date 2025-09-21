package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

/**
 * <p>
 *   修改成绩记录请求对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordUpdateRequest {

    @NotNull(message = "成绩记录ID不能为空")
    private Integer recordId;

    private BigDecimal usualScore;
    private BigDecimal finalScore;
}