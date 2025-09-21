package com.student.score.service.impl;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddCourseRequest;
import com.student.score.dto.request.DeleteCourseRequest;
import com.student.score.dto.request.GetCourseDetailRequest;
import com.student.score.dto.request.UpdateCourseRequest;
import com.student.score.dto.response.CourseInfoResponse;
import com.student.score.entity.CourseInfo;
import com.student.score.repository.CourseInfoRepository;
import com.student.score.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程信息 Service 实现类
 * </p>
 *
 * @author seekpan
 */
@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    private CourseInfoRepository courseInfoRepository;

    @Override
    @Transactional
    public RestResult<CourseInfoResponse> addCourse(AddCourseRequest request) {
        if (courseInfoRepository.findByCourseName(request.getCourseName()) != null) {
            return RestResult.failure("000001", "课程已存在");
        }

        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseName(request.getCourseName());
        courseInfo.setCredits(BigDecimal.valueOf(request.getCredits()));
        courseInfo.setUsualScoreRatio(BigDecimal.valueOf(request.getUsualScoreRatio()));
        courseInfo.setFinalScoreRatio(BigDecimal.valueOf(request.getFinalScoreRatio()));
        courseInfo.setCreateBy("admin"); // 假设创建人默认为admin

        CourseInfo savedCourseInfo = courseInfoRepository.save(courseInfo);
        return RestResult.success(convertToResponse(savedCourseInfo));
    }

    @Override
    @Transactional
    public RestResult<Void> deleteCourse(DeleteCourseRequest request) {
        Optional<CourseInfo> courseInfoOptional = courseInfoRepository.findById(request.getCourseId());
        if (!courseInfoOptional.isPresent()) {
            return RestResult.failure("000001", "课程不存在");
        }

        courseInfoRepository.deleteById(request.getCourseId());
        return RestResult.success(null);
    }

    @Override
    @Transactional
    public RestResult<CourseInfoResponse> updateCourse(UpdateCourseRequest request) {
        Optional<CourseInfo> courseInfoOptional = courseInfoRepository.findById(request.getCourseId());
        if (!courseInfoOptional.isPresent()) {
            return RestResult.failure("000001", "课程不存在");
        }

        CourseInfo courseInfo = courseInfoOptional.get();
        courseInfo.setCourseName(request.getCourseName());
        courseInfo.setCredits(BigDecimal.valueOf(request.getCredits()));
        courseInfo.setUsualScoreRatio(BigDecimal.valueOf(request.getUsualScoreRatio()));
        courseInfo.setFinalScoreRatio(BigDecimal.valueOf(request.getFinalScoreRatio()));
        courseInfo.setUpdateBy("admin"); // 假设修改人默认为admin

        CourseInfo updatedCourseInfo = courseInfoRepository.save(courseInfo);
        return RestResult.success(convertToResponse(updatedCourseInfo));
    }

    @Override
    public RestResult<CourseInfoResponse> getCourseDetail(GetCourseDetailRequest request) {
        Optional<CourseInfo> courseInfoOptional = courseInfoRepository.findById(request.getCourseId());
        if (!courseInfoOptional.isPresent()) {
            return RestResult.failure("000001", "课程不存在");
        }

        CourseInfo courseInfo = courseInfoOptional.get();
        return RestResult.success(convertToResponse(courseInfo));
    }

    @Override
    public RestResult<List<CourseInfoResponse>> getAllCourses() {
        List<CourseInfo> courseInfoList = courseInfoRepository.findAll();
        List<CourseInfoResponse> responseList = courseInfoList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return RestResult.success(responseList);
    }

    private CourseInfoResponse convertToResponse(CourseInfo courseInfo) {
        CourseInfoResponse response = new CourseInfoResponse();
        response.setCourseId(courseInfo.getCourseId());
        response.setCourseName(courseInfo.getCourseName());
        response.setCredits(courseInfo.getCredits().doubleValue());
        response.setUsualScoreRatio(courseInfo.getUsualScoreRatio().doubleValue());
        response.setFinalScoreRatio(courseInfo.getFinalScoreRatio().doubleValue());
        return response;
    }
}