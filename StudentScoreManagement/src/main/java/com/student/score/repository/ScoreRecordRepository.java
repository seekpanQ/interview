package com.student.score.repository;

import com.student.score.entity.ScoreRecordDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.student.score.entity.StudentInfo;
import com.student.score.entity.ScoreRecord;

/**
 * <p>
 *   成绩记录仓库接口
 * </p>
 * @author seekpan
 */
@Repository
public interface ScoreRecordRepository extends JpaRepository<ScoreRecordDO, Integer> {

    @Query("SELECT s FROM ScoreRecordDO s WHERE (:studentId IS NULL OR s.studentId = :studentId) AND (:courseId IS NULL OR s.courseId = :courseId)")
    List<ScoreRecordDO> findByConditions(Integer studentId, Integer courseId);

    /**
     * 根据班级ID和学期查询成绩记录
     *
     * @param classId 班级ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecordDO sr JOIN StudentInfoDO si ON sr.studentId = si.studentId WHERE si.classId = :classId AND sr.createTime LIKE CONCAT(:semester, '%')")
    List<ScoreRecordDO> findScoreRecordsByClassIdAndSemester(@Param("classId") Integer classId, @Param("semester") String semester);

    /**
     * 根据课程ID和学期查询成绩记录
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecordDO sr WHERE sr.courseId = :courseId AND sr.createTime LIKE CONCAT(:semester, '%')")
    List<ScoreRecordDO> findScoreRecordsByCourseIdAndSemester(@Param("courseId") Integer courseId, @Param("semester") String semester);

    /**
     * 根据学生ID和学期查询成绩记录
     *
     * @param studentId 学生ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecordDO sr WHERE sr.studentId = :studentId AND sr.createTime LIKE CONCAT(:semester, '%')")
    List<ScoreRecordDO> findScoreRecordsByStudentIdAndSemester(@Param("studentId") Integer studentId, @Param("semester") String semester);

    /**
     * 根据学生ID和学期查找成绩记录
     *
     * @param studentId 学生ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecord sr WHERE sr.studentId = ?1 AND sr.createTime LIKE CONCAT(?2, '%')")
    List<ScoreRecord> findByStudentIdAndSemester(Integer studentId, String semester);

    /**
     * 根据班级ID和学期获取该班所有学生成绩
     *
     * @param classId 班级ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecord sr JOIN StudentInfo s ON sr.studentId = s.studentId WHERE s.classId = ?1 AND sr.createTime LIKE CONCAT(?2, '%')")
    List<ScoreRecord> findRecordsOfClassAndSemester(Integer classId, String semester);

    /**
     * 根据课程ID和学期获取该课程的所有学生成绩
     *
     * @param courseId 课程ID
     * @param semester 学期
     * @return 成绩记录列表
     */
    @Query("SELECT sr FROM ScoreRecord sr WHERE sr.courseId = ?1 AND sr.createTime LIKE CONCAT(?2, '%')")
    List<ScoreRecord> findByCourseIdAndSemester(Integer courseId, String semester);
}
