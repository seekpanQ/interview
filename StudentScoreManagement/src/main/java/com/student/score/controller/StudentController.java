package com.student.score.controller;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddStudentRequest;
import com.student.score.dto.request.DeleteStudentRequest;
import com.student.score.dto.request.ModifyStudentRequest;
import com.student.score.dto.request.QueryStudentListRequest;
import com.student.score.dto.response.StudentDetailResponse;
import com.student.score.dto.response.StudentListResponse;
import com.student.score.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   学生信息控制器
 * </p>
 * @author seekpan
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 新增学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    @PostMapping("/add")
    public RestResult<?> addStudent(@Valid @RequestBody AddStudentRequest request) {
        return studentService.addStudent(request);
    }

    /**
     * 删除学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    @PostMapping("/delete")
    public RestResult<?> deleteStudent(@Valid @RequestBody DeleteStudentRequest request) {
        return studentService.deleteStudent(request);
    }

    /**
     * 修改学生信息
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    @PostMapping("/modify")
    public RestResult<?> modifyStudent(@Valid @RequestBody ModifyStudentRequest request) {
        return studentService.modifyStudent(request);
    }

    /**
     * 查询学生信息列表
     *
     * @param request 请求参数对象
     * @return RestResult
     */
    @GetMapping("/list")
    public RestResult<List<StudentListResponse>> queryStudentList(QueryStudentListRequest request) {
        return studentService.queryStudentList(request);
    }

    /**
     * 查询单个学生详情
     *
     * @param studentId 学生ID
     * @return RestResult
     */
    @GetMapping("/{id}")
    public RestResult<StudentDetailResponse> getStudentDetail(@PathVariable("id") Integer studentId) {
        return studentService.getStudentDetail(studentId);
    }
}