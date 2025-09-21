package com.student.score.dto.response;

import java.math.BigDecimal;
import lombok.Data;

/**
 * <p>
 *   GPA响应数据
 * </p>
 * @author seekpan
 */
@Data
public class GPAResponse {

    /**
     * 学期GPA
     */
    private BigDecimal gpa;

    /**
     * 修读课程数
     */
    private Integer courseCount;
}