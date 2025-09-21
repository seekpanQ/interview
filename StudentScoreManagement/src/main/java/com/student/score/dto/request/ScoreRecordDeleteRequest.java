package com.student.score.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * <p>
 *   删除成绩记录请求对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordDeleteRequest {

    @NotNull(message = "成绩记录ID不能为空")
    private Integer recordId;
}