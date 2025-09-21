CREATE TABLE student_info (
    student_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '学生唯一标识',
    student_name VARCHAR(100) NOT NULL COMMENT '学生姓名',
    gender ENUM('男', '女') COMMENT '性别',
    birth_date DATE COMMENT '出生日期',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '电子邮箱',
    class_id INT COMMENT '所属班级ID',
    create_by VARCHAR(50) COMMENT '创建人',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by VARCHAR(50) COMMENT '修改人',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT='学生信息表';