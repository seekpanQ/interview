package com.student.score.dto.request;

import lombok.Data;

/**
 * <p>
 *   查询学生列表请求参数DTO
 * </p>
 * @author seekpan
 */
@Data
public class QueryStudentListRequest {

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 学生姓名
     */
    private String studentName;
}