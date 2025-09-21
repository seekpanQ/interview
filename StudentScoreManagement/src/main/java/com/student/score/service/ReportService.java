package com.student.score.service;

/**
 * <p>
 *   报表生成与展示服务接口
 * </p>
 * @author seekpan
 */
public interface ReportService {

    /**
     * 生成成绩单报表
     *
     * @param studentId 学生ID
     * @param semester 学期
     * @return 成绩单数据
     */
    Object generateTranscript(Integer studentId, String semester);

    /**
     * 生成班级成绩排行报表
     *
     * @param classId 班级ID
     * @param semester 学期
     * @return 排名列表
     */
    Object generateClassRanking(Integer classId, String semester);

    /**
     * 生成课程成绩分布图
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 分布图表数据
     */
    Object generateCourseDistribution(Integer courseId, String semester);

    /**
     * 生成GPA统计报表
     *
     * @param studentId 学生ID
     * @param semester 学期（可选）
     * @return GPA历史变化趋势数据
     */
    Object generateGpaStatistics(Integer studentId, String semester);

    /**
     * 生成学期整体成绩分析报告
     *
     * @param semester 学期
     * @return 整体学业表现摘要
     */
    Object generateSemesterAnalysis(String semester);
}
