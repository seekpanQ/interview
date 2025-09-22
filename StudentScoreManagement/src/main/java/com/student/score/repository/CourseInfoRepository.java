package com.student.score.repository;

import com.student.score.entity.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   课程信息 Repository 接口
 * </p>
 * @author seekpan
 */
@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Integer> {

    @Query("SELECT ci FROM CourseInfo ci WHERE ci.courseName = :courseName")
    CourseInfo findByCourseName(String courseName);
}
