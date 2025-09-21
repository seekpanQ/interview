package com.student.score.controller;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddCourseRequest;
import com.student.score.dto.request.DeleteCourseRequest;
import com.student.score.dto.request.GetCourseDetailRequest;
import com.student.score.dto.request.UpdateCourseRequest;
import com.student.score.dto.response.CourseInfoResponse;
import com.student.score.service.CourseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程信息 Controller 类
 * </p>
 *
 * @author seekpan
 */
@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseInfoController {

    @Autowired
    private CourseInfoService courseInfoService;

    @PostMapping("/add")
    public RestResult<CourseInfoResponse> addCourse(@Validated @RequestBody AddCourseRequest request) {
        log.info("新增课程请求: {}", request);
        return courseInfoService.addCourse(request);
    }

    @PostMapping("/delete")
    public RestResult<Void> deleteCourse(@Validated @RequestBody DeleteCourseRequest request) {
        log.info("删除课程请求: {}", request);
        return courseInfoService.deleteCourse(request);
    }

    @PostMapping("/update")
    public RestResult<CourseInfoResponse> updateCourse(@Validated @RequestBody UpdateCourseRequest request) {
        log.info("修改课程请求: {}", request);
        return courseInfoService.updateCourse(request);
    }

    @GetMapping("/detail")
    public RestResult<CourseInfoResponse> getCourseDetail(@Validated GetCourseDetailRequest request) {
        log.info("查询课程详情请求: {}", request);
        return courseInfoService.getCourseDetail(request);
    }

    @GetMapping("/list")
    public RestResult<List<CourseInfoResponse>> getAllCourses() {
        log.info("查询所有课程列表请求");
        return courseInfoService.getAllCourses();
    }
}