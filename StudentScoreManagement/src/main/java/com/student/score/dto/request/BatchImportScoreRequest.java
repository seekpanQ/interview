package com.student.score.dto.request;

import com.student.score.dto.request.ScoreRecordRequest;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.*;

/**
 * <p>
 *   批量导入成绩数据请求对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchImportScoreRequest {

    @NotEmpty(message = "成绩数据列表不能为空")
    private List<ScoreRecordRequest> scoreList;
}