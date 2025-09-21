package com.student.score.repository;

import com.student.score.entity.StudentInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.student.score.entity.StudentInfoDO;

/**
 * <p>
 *   学生信息仓库接口
 * </p>
 * @author seekpan
 */
@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {

    /**
     * 根据姓名和出生日期查找学生记录是否存在
     *
     * @param studentName 学生姓名
     * @param birthDate 出生日期
     * @return Optional<StudentInfo>
     */
    @Query("SELECT s FROM StudentInfo s WHERE s.studentName = :studentName AND s.birthDate = :birthDate")
    Optional<StudentInfo> findByStudentNameAndBirthDate(@Param("studentName") String studentName, @Param("birthDate") LocalDate birthDate);

    /**
     * 分页查询符合条件的学生信息列表
     *
     * @param classId 班级ID（可选）
     * @param studentName 学生姓名（可选）
     * @return List<StudentInfo>
     */
    @Query(value = "SELECT s FROM StudentInfo s WHERE (:classId IS NULL OR s.classId = :classId)" + "AND (:studentName IS NULL OR s.studentName LIKE %:studentName%)")
    List<StudentInfo> findStudentsByClassIdOrStudentName(@Param("classId") Integer classId, @Param("studentName") String studentName);

    /**
     * 获取指定班级的所有学生信息
     *
     * @param classId 班级ID
     * @return 学生列表
     */
    @Query(value = "SELECT s FROM StudentInfo s WHERE s.classId = ?1")
    List<StudentInfo> findByClassId(Integer classId);
}
