package com.student.score.repository;

import com.student.score.entity.GpaStatistics;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   GPA统计仓库接口
 * </p>
 * @author seekpan
 */
@Repository
public interface GpaStatisticsRepository extends JpaRepository<GpaStatistics, Integer> {

    /**
     * 根据学生ID和学期查找GPA记录
     *
     * @param studentId 学生ID
     * @param semester 学期
     * @return GPA记录列表
     */
    @Query("SELECT gs FROM GpaStatistics gs WHERE gs.studentId = ?1 AND (?2 IS NULL OR gs.semester = ?2)")
    List<GpaStatistics> findByStudentIdAndSemester(Integer studentId, String semester);

    List<GpaStatistics> findByStudentIdOrderByCreateTimeDesc(Integer studentId);
}
