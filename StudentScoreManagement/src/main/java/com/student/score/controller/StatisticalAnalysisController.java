package com.student.score.controller;

import com.student.score.common.RestResult;
import com.student.score.dto.request.*;
import com.student.score.dto.request.CalculateGPARequest;
import com.student.score.dto.request.ClassRankingRequest;
import com.student.score.dto.request.CourseRankingRequest;
import com.student.score.dto.request.GetGPAsByStudentRequest;
import com.student.score.dto.response.*;
import com.student.score.dto.response.ClassRankingResponse;
import com.student.score.dto.response.CourseRankingResponse;
import com.student.score.dto.response.GPAResponse;
import com.student.score.dto.response.StudentGPAListResponse;
import com.student.score.service.StatisticalAnalysisService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   成绩统计分析控制器
 * </p>
 * @author seekpan
 */
@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticalAnalysisController {

    @Autowired
    private StatisticalAnalysisService statisticalAnalysisService;

    /**
     * 按班级统计成绩排行
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    @PostMapping("/class-ranking")
    public RestResult<ClassRankingResponse> getClassRanking(@Valid @RequestBody ClassRankingRequest request) {
        log.info("按班级统计成绩排行请求: {}", request);
        return statisticalAnalysisService.getClassRanking(request);
    }

    /**
     * 按课程统计成绩排行
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    @PostMapping("/course-ranking")
    public RestResult<CourseRankingResponse> getCourseRanking(@Valid @RequestBody CourseRankingRequest request) {
        log.info("按课程统计成绩排行请求: {}", request);
        return statisticalAnalysisService.getCourseRanking(request);
    }

    /**
     * 计算学生学期GPA
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    @PostMapping("/calculate-gpa")
    public RestResult<GPAResponse> calculateStudentGPA(@Valid @RequestBody CalculateGPARequest request) {
        log.info("计算学生学期GPA请求: {}", request);
        return statisticalAnalysisService.calculateStudentGPA(request);
    }

    /**
     * 获取指定学生多个学期GPA列表
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    @GetMapping("/student-gpas")
    public RestResult<StudentGPAListResponse> getStudentGPAs(@Valid GetGPAsByStudentRequest request) {
        log.info("获取指定学生多个学期GPA列表请求: {}", request);
        return statisticalAnalysisService.getStudentGPAs(request);
    }
}