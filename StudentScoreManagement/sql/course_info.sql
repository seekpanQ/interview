
CREATE TABLE course_info (
    course_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '课程唯一标识',
    course_name VARCHAR(100) NOT NULL COMMENT '课程名称',
    credits DECIMAL(3,1) NOT NULL COMMENT '学分',
    usual_score_ratio DECIMAL(5,2) DEFAULT 0.00 COMMENT '平时成绩占总成绩百分比',
    final_score_ratio DECIMAL(5,2) DEFAULT 0.00 COMMENT '期末成绩占总成绩百分比',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '修改人',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='课程信息表';