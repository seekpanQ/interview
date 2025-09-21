package com.student.score.controller;

import com.student.score.common.RestResult;
import com.student.score.dto.request.*;
import com.student.score.dto.request.ClassRankingRequest;
import com.student.score.dto.request.CourseDistributionRequest;
import com.student.score.dto.request.GpaStatisticsRequest;
import com.student.score.dto.request.SemesterAnalysisRequest;
import com.student.score.dto.request.TranscriptRequest;
import com.student.score.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *   报表生成与展示控制器
 * </p>
 * @author seekpan
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * 生成成绩单报表
     *
     * @param request 成绩单请求参数
     * @return 成绩单数据
     */
    @PostMapping("/generate/transcript")
    public RestResult<Object> generateTranscript(@RequestBody @Valid TranscriptRequest request) {
        Object data = reportService.generateTranscript(request.getStudentId(), request.getSemester());
        if (data == null) {
            return RestResult.error("无相关成绩记录");
        }
        return RestResult.success(data);
    }

    /**
     * 生成班级成绩排行报表
     *
     * @param request 班级排名请求参数
     * @return 排名列表
     */
    @PostMapping("/generate/class-ranking")
    public RestResult<Object> generateClassRanking(@RequestBody @Valid ClassRankingRequest request) {
        Object data = reportService.generateClassRanking(request.getClassId(), request.getSemester());
        if (data == null) {
            return RestResult.error("暂无成绩数据");
        }
        return RestResult.success(data);
    }

    /**
     * 生成课程成绩分布图
     *
     * @param request 课程分布请求参数
     * @return 分布图表数据
     */
    @PostMapping("/generate/course-distribution")
    public RestResult<Object> generateCourseDistribution(@RequestBody @Valid CourseDistributionRequest request) {
        Object data = reportService.generateCourseDistribution(request.getCourseId(), request.getSemester());
        if (data == null) {
            return RestResult.error("暂无成绩数据");
        }
        return RestResult.success(data);
    }

    /**
     * 生成GPA统计报表
     *
     * @param request GPA请求参数
     * @return GPA历史变化趋势数据
     */
    @PostMapping("/generate/gpa-statistics")
    public RestResult<Object> generateGpaStatistics(@RequestBody @Valid GpaStatisticsRequest request) {
        Object data = reportService.generateGpaStatistics(request.getStudentId(), request.getSemester());
        if (data == null) {
            return RestResult.error("暂无GPA统计数据");
        }
        return RestResult.success(data);
    }

    /**
     * 生成学期整体成绩分析报告
     *
     * @param request 学期分析请求参数
     * @return 整体学业表现摘要
     */
    @PostMapping("/generate/semester-analysis")
    public RestResult<Object> generateSemesterAnalysis(@RequestBody @Valid SemesterAnalysisRequest request) {
        Object data = reportService.generateSemesterAnalysis(request.getSemester());
        if (data == null) {
            return RestResult.error("暂无成绩数据");
        }
        return RestResult.success(data);
    }
}
