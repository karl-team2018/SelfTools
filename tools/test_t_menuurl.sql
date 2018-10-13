CREATE TABLE test.t_menuurl
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    href varchar(255) NOT NULL COMMENT '资源路径',
    noteid int(11) NOT NULL COMMENT '资源id',
    biz_block varchar(255) NOT NULL COMMENT '所属业务模块',
    parentnoteid int(10) unsigned zerofill NOT NULL COMMENT '父级id',
    title varchar(255) NOT NULL COMMENT '列表名称',
    icon varchar(255) NOT NULL COMMENT '列表图标',
    spread char(1) NOT NULL,
    target varchar(255) COMMENT '是否新开窗口',
    roles_id int(11)
);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (101, 'pages/contentmanage', 100100, 'contentManagement', 0, '代办事项', '&#xe634;', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (102, 'pages/techshare', 100101, 'contentManagement', 0, '技术分享', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (103, '', 100102, 'contentManagement', 0, '其他页面', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (104, 'pages/soaptest', 100201, 'contentManagement', 100102, 'soap测试', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (105, 'pages/soptest', 100202, 'contentManagement', 100102, 'sop测试', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (106, 'pages/attendance', 200100, 'projectManager', 0, '出勤', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (107, 'pages/assess', 200101, 'projectManager', 0, '考核', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (108, 'pages/projectmember', 200102, 'projectManager', 0, '成员信息', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (109, 'pages/applicantinfo', 300100, 'employeeManagement', 0, '求职者信息', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (110, 'pages/interviewerinfo', 300101, 'employeeManagement', 0, '面试官信息', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (111, 'pages/interviewcontent', 300102, 'employeeManagement', 0, '面试内容', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (112, 'pages/questionbank', 300103, 'employeeManagement', 0, '题库', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (113, 'pages/newtraining', 200103, 'projectManager', 0, '新人培训', 'icon-text', '0', '0', 102);
INSERT INTO test.t_menuurl (id, href, noteid, biz_block, parentnoteid, title, icon, spread, target, roles_id) VALUES (114, 'pages/deliverysystem', 200104, 'projectManager', 0, '交付体系文档', 'icon-text', '0', '_blank', 102);