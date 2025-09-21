
CREATE TABLE score_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '成绩记录唯一标识',
    student_id INT NOT NULL COMMENT '学生ID',
    course_id INT NOT NULL COMMENT '课程ID',
    usual_score DECIMAL(5,1) COMMENT '平时成绩',
    final_score DECIMAL(5,1) COMMENT '期末成绩',
    total_score DECIMAL(5,1) COMMENT '综合成绩',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '修改人',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='成绩记录表';