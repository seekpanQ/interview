package com.student.score.service;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddStudentRequest;
import com.student.score.dto.request.DeleteStudentRequest;
import com.student.score.dto.request.ModifyStudentRequest;
import com.student.score.dto.request.QueryStudentListRequest;
import com.student.score.dto.response.StudentDetailResponse;
import com.student.score.dto.response.StudentListResponse;
import java.util.List;

/**
 * <p>
 *   学生信息服务接口
 * </p>
 * @author seekpan
 */
public interface StudentService {

    /**
     * 新增学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    RestResult<?> addStudent(AddStudentRequest request);

    /**
     * 删除学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    RestResult<?> deleteStudent(DeleteStudentRequest request);

    /**
     * 修改学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    RestResult<?> modifyStudent(ModifyStudentRequest request);

    /**
     * 查询学生信息列表
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    RestResult<List<StudentListResponse>> queryStudentList(QueryStudentListRequest request);

    /**
     * 获取单个学生详情
     *
     * @param studentId 学生ID
     * @return RestResult
     */
    RestResult<StudentDetailResponse> getStudentDetail(Integer studentId);
}