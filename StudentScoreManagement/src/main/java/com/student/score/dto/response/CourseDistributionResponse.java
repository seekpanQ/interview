package com.student.score.dto.response;

import java.util.Map;
import lombok.Data;

/**
 * <p>
 *   课程成绩分布响应数据结构
 * </p>
 * @author seekpan
 */
@Data
public class CourseDistributionResponse {

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 学期
     */
    private String semester;

    /**
     * 成绩段人数分布情况
     * key: 分数区间字符串 如"90~100", value: 对应人数
     */
    private Map<String, Long> scoreDistribution;
}
