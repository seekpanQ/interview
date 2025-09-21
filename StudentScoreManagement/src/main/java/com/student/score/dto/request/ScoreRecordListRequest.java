package com.student.score.dto.request;

import lombok.*;

/**
 * <p>
 *   查询成绩记录列表请求对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordListRequest {

    private Integer studentId;
    private Integer courseId;
    private String semester;
}