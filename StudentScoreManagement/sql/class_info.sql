
CREATE TABLE class_info (
    class_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '班级唯一标识',
    class_name VARCHAR(100) NOT NULL COMMENT '班级名称',
    grade_year INT COMMENT '年级年份',
    teacher_name VARCHAR(100) COMMENT '班主任姓名',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '修改人',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='班级信息表';