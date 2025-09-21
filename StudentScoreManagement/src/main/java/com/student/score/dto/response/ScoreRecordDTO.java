package com.student.score.dto.response;

import java.math.BigDecimal;
import lombok.*;

/**
 * <p>
 *   成绩记录数据传输对象
 * </p>
 * @author seekpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRecordDTO {

    private Integer recordId;
    private Integer studentId;
    private Integer courseId;
    private BigDecimal usualScore;
    private BigDecimal finalScore;
    private BigDecimal totalScore;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
}