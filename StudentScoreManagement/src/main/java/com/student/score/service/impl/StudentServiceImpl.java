package com.student.score.service.impl;

import com.student.score.common.RestResult;
import com.student.score.dto.request.AddStudentRequest;
import com.student.score.dto.request.DeleteStudentRequest;
import com.student.score.dto.request.ModifyStudentRequest;
import com.student.score.dto.request.QueryStudentListRequest;
import com.student.score.dto.response.StudentDetailResponse;
import com.student.score.dto.response.StudentListResponse;
import com.student.score.entity.StudentInfo;
import com.student.score.repository.StudentInfoRepository;
import com.student.score.service.StudentService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *   学生服务实现类
 * </p>
 * @author seekpan
 */
@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    public RestResult<?> addStudent(AddStudentRequest request) {
        log.info("新增学生信息请求: {}", request);

        if (request.getStudentName() == null || request.getStudentName().trim().isEmpty()) {
            return RestResult.error("000001", "学生姓名不能为空");
        }

        // 检查是否已存在相同姓名与出生日期的学生
        Optional<StudentInfo> existingStudentOpt = studentInfoRepository.findByStudentNameAndBirthDate(
                request.getStudentName(), request.getBirthDate());
        if (existingStudentOpt.isPresent()) {
            return RestResult.error("000001", "学生信息已存在");
        }

        try {
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(request, studentInfo);
            studentInfo.setCreateTime(LocalDateTime.now());
            studentInfo.setUpdateTime(LocalDateTime.now());

            studentInfoRepository.save(studentInfo);
            return RestResult.success(null);
        } catch (Exception e) {
            log.error("保存学生信息异常", e);
            return RestResult.error("000002", "系统错误");
        }
    }

    @Override
    public RestResult<?> deleteStudent(DeleteStudentRequest request) {
        log.info("删除学生信息请求: {}", request);

        Optional<StudentInfo> optionalStudent = studentInfoRepository.findById(request.getStudentId());
        if (!optionalStudent.isPresent()) {
            return RestResult.error("000001", "学生信息不存在");
        }

        try {
            studentInfoRepository.deleteById(request.getStudentId());
            return RestResult.success(null);
        } catch (Exception e) {
            log.error("删除学生信息异常", e);
            return RestResult.error("000002", "系统错误");
        }
    }

    @Override
    public RestResult<?> modifyStudent(ModifyStudentRequest request) {
        log.info("修改学生信息请求: {}", request);

        Optional<StudentInfo> optionalStudent = studentInfoRepository.findById(request.getStudentId());
        if (!optionalStudent.isPresent()) {
            return RestResult.error("000001", "无效的学生ID");
        }

        try {
            StudentInfo studentInfo = optionalStudent.get();
            BeanUtils.copyProperties(request, studentInfo);
            studentInfo.setUpdateTime(LocalDateTime.now());

            studentInfoRepository.save(studentInfo);
            return RestResult.success(null);
        } catch (Exception e) {
            log.error("更新学生信息异常", e);
            return RestResult.error("000002", "系统错误");
        }
    }

    @Override
    public RestResult<List<StudentListResponse>> queryStudentList(QueryStudentListRequest request) {
        log.info("查询学生列表请求: {}", request);

        try {
            Pageable pageable = Pageable.ofSize(10); // 可替换为动态分页参数
            List<StudentInfo> students = studentInfoRepository.findStudentsByClassIdOrStudentName(
                    request.getClassId(), request.getStudentName());

            List<StudentListResponse> responseList = new ArrayList<>();
            for (StudentInfo info : students) {
                StudentListResponse resp = new StudentListResponse();
                BeanUtils.copyProperties(info, resp);
                responseList.add(resp);
            }

            Page<StudentListResponse> pageResult = new PageImpl<>(responseList, pageable, responseList.size());
            return RestResult.success(pageResult.getContent());
        } catch (Exception e) {
            log.error("查询学生列表异常", e);
            return RestResult.error("000002", "系统错误");
        }
    }

    @Override
    public RestResult<StudentDetailResponse> getStudentDetail(Integer studentId) {
        log.info("获取学生详情请求: id={}", studentId);

        Optional<StudentInfo> optionalStudent = studentInfoRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            return RestResult.error("000001", "未找到对应学生信息");
        }

        try {
            StudentInfo studentInfo = optionalStudent.get();
            StudentDetailResponse detailResp = new StudentDetailResponse();
            BeanUtils.copyProperties(studentInfo, detailResp);
            return RestResult.success(detailResp);
        } catch (Exception e) {
            log.error("获取学生详情异常", e);
            return RestResult.error("000002", "系统错误");
        }
    }
}