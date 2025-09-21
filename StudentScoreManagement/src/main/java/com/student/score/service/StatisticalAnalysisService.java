package com.student.score.service;

import com.student.score.common.RestResult;
import com.student.score.dto.request.CalculateGPARequest;
import com.student.score.dto.request.ClassRankingRequest;
import com.student.score.dto.request.CourseRankingRequest;
import com.student.score.dto.request.GetGPAsByStudentRequest;
import com.student.score.dto.response.ClassRankingResponse;
import com.student.score.dto.response.CourseRankingResponse;
import com.student.score.dto.response.GPAResponse;
import com.student.score.dto.response.StudentGPAListResponse;

/**
 * <p>
 *   成绩统计分析服务接口
 * </p>
 * @author seekpan
 */
public interface StatisticalAnalysisService {

    /**
     * 按班级统计成绩排行
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    RestResult<ClassRankingResponse> getClassRanking(ClassRankingRequest request);

    /**
     * 按课程统计成绩排行
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    RestResult<CourseRankingResponse> getCourseRanking(CourseRankingRequest request);

    /**
     * 计算学生学期GPA
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    RestResult<GPAResponse> calculateStudentGPA(CalculateGPARequest request);

    /**
     * 获取指定学生多个学期GPA列表
     *
     * @param request 请求参数
     * @return 结果包装对象
     */
    RestResult<StudentGPAListResponse> getStudentGPAs(GetGPAsByStudentRequest request);
}