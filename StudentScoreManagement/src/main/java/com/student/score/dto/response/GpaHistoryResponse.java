package com.student.score.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * GPA历史变化趋势响应数据结构
 * </p>
 *
 * @author seekpan
 */
@Data
public class GpaHistoryResponse {

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 每个学期的GPA数据
     */
    private List<SemesterGpa> gpaList;

    /**
     * <p>
     * 每个学期的GPA信息
     * </p>
     *
     * @author seekpan
     */
    @Data
    public static class SemesterGpa {

        /**
         * 学期
         */
        private String semester;

        /**
         * 当前学期GPA
         */
        private BigDecimal gpa;
    }
}
