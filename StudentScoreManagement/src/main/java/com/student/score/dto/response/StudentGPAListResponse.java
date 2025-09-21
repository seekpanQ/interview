package com.student.score.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * <p>
 *   学生多学期GPA列表响应数据
 * </p>
 * @author seekpan
 */
@Data
public class StudentGPAListResponse {

    /**
     * GPA记录列表
     */
    private List<GPARecord> gpaRecords;

    /**
     * GPA记录项
     */
    @Data
    public static class GPARecord {
        /**
         * 学期
         */
        private String semester;

        /**
         * GPA值
         */
        private BigDecimal gpa;

        /**
         * 修读课程数量
         */
        private Integer courseCount;
    }
}