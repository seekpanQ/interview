package com.student.score.repository;

import com.student.score.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *   班级信息仓库接口
 * </p>
 * @author seekpan
 */
@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Integer> {
}
