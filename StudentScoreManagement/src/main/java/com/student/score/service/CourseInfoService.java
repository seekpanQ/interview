package com.student.score.service;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddCourseRequest;
import com.student.score.dto.request.DeleteCourseRequest;
import com.student.score.dto.request.GetCourseDetailRequest;
import com.student.score.dto.request.UpdateCourseRequest;
import com.student.score.dto.response.CourseInfoResponse;

import java.util.List;

/**
 * <p>
 *   课程信息 Service 接口
 * </p>
 * @author seekpan
 */
public interface CourseInfoService {

    RestResult<CourseInfoResponse> addCourse(AddCourseRequest request);

    RestResult<Void> deleteCourse(DeleteCourseRequest request);

    RestResult<CourseInfoResponse> updateCourse(UpdateCourseRequest request);

    RestResult<CourseInfoResponse> getCourseDetail(GetCourseDetailRequest request);

    RestResult<List<CourseInfoResponse>> getAllCourses();
}