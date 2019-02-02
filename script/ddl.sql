-- 部门表

drop table IF EXISTS cm_department;
create table cm_department(
  id BIGINT COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) COMMENT '部门名称',
  parent_id varchar(64) COMMENT '密码',
  status tinyint COMMENT '状态，0停用，1启用，2删除',
  creator_id BIGINT COMMENT '创建人',
  create_time DATETIME COMMENT '创建时间',
  modifier_id BIGINT COMMENT '修改人',
  modify_time DATETIME COMMENT '修改时间'
)comment '部门表';


-- 员工表
drop table IF EXISTS cm_employee;
create table cm_employee(
  id BIGINT COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
  username varchar(50) COMMENT '用户名',
  password varchar(64) COMMENT '密码',
  depart_id BIGINT COMMENT '部门ID',
  name varchar(50) COMMENT '姓名',
  phone varchar(50) COMMENT '电话',
  status tinyint COMMENT '状态，0停用，1启用，2删除',
  creator_id BIGINT COMMENT '创建人',
  create_time DATETIME COMMENT '创建时间',
  modifier_id BIGINT COMMENT '修改人',
  modify_time DATETIME COMMENT '修改时间',
  last_login_time DATETIME COMMENT '最后登录时间'
)comment '员工表';
create INDEX idx_username on cm_employee(username);

-- 角色
drop table IF EXISTS cm_role;
create table cm_role(
  id BIGINT COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) COMMENT '角色名称',
  status tinyint COMMENT '状态，0停用，1启用，2删除',
  creator_id BIGINT COMMENT '创建人',
  create_time DATETIME COMMENT '创建时间',
  modifier_id BIGINT COMMENT '修改人',
  modify_time DATETIME COMMENT '修改时间'
)comment '角色表';


--
-- 权限
drop table IF EXISTS cm_permission;
create table cm_permission(
  id BIGINT COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) COMMENT '权限名称',
  authorities varchar(500) COMMENT '权限',
  status tinyint COMMENT '状态，0停用，1启用',
  creator_id BIGINT COMMENT '创建人',
  create_time DATETIME COMMENT '创建时间',
  modifier_id BIGINT COMMENT '修改人',
  modify_time DATETIME COMMENT '修改时间'
)comment '权限表';


--
drop table IF EXISTS cm_role_permission;
create table cm_role_permission(
  role_id BIGINT,
  permission_id BIGINT
)comment '角色权限表';

--
drop table IF EXISTS cm_employee_role;
create table cm_employee_role(
  employee_id BIGINT,
  role_id BIGINT
)comment '用户角色表';
