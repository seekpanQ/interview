
CREATE TABLE gpa_statistics (
    stat_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '统计记录唯一标识',
    student_id INT NOT NULL COMMENT '学生ID',
    semester VARCHAR(20) NOT NULL COMMENT '学期（如：2023-秋）',
    total_gpa DECIMAL(3,2) COMMENT '该学期GPA',
    course_count INT COMMENT '修读课程数',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '修改人',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='GPA统计表';