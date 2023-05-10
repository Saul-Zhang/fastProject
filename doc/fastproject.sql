-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fastproject
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `def_department`
--

DROP TABLE IF EXISTS `def_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_department` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` int DEFAULT NULL COMMENT '状态',
  `order_num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_department`
--

LOCK TABLES `def_department` WRITE;
/*!40000 ALTER TABLE `def_department` DISABLE KEYS */;
INSERT INTO `def_department` VALUES ('1','0','v2','v2','13012345678','v2@qq.com',1,1),('2','1','技术部门','x某某','13012345678','v2@qq.com',1,2),('3','1','人事部门','a某某','13012345678','v2@qq.com',1,3),('4','2','开发一小组','b某某','13012345678','v2@qq.com',1,4),('5','3','销售部门','d某某','13012345678','v2@qq.com',1,5),('6','5','销售一组','e某某','13012345678','v2@qq.com',1,6);
/*!40000 ALTER TABLE `def_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_dict_data`
--

DROP TABLE IF EXISTS `def_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_dict_data` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典键值',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_dict_data`
--

LOCK TABLES `def_dict_data` WRITE;
/*!40000 ALTER TABLE `def_dict_data` DISABLE KEYS */;
INSERT INTO `def_dict_data` VALUES ('1',0,'男','1','gender','','','N','0','',NULL,'',NULL,''),('2',0,'女','0','gender','','','N','0','',NULL,'',NULL,''),('331043380933038080',1,'一般','1','sys_notice_type','','info','Y','0','admin','2019-09-09 22:15:03','admin','2019-09-09 22:15:43',''),('331043525137403904',2,'重要','2','sys_notice_type','','important','N','0','admin','2019-09-09 22:15:37','admin','2020-12-27 13:57:22',''),('340080322395901952',1,'开启','0','sys_province_state','','info','Y','0','admin','2019-10-04 20:44:37','admin','2019-10-04 20:46:41',''),('340080779201744896',2,'关闭','-1','sys_province_state','','important','N','0','admin','2019-10-04 20:46:26','admin','2020-12-27 13:57:46',''),('373494384659927040',0,'GET请求','1','sys_inter_url_type','','primary','Y','0','admin','2020-01-05 01:40:11','admin','2020-12-27 14:27:28',''),('373494483465146368',1,'POST请求','2','sys_inter_url_type','','info','N','0','admin','2020-01-05 01:40:34','admin','2020-12-27 14:26:59',''),('506431838588375040',0,'DELETE请求','3','sys_inter_url_type','','default','N','0','admin','2021-01-05 13:46:10','admin','2021-01-05 13:48:43',''),('506432620712824832',0,'PUT请求','4','sys_inter_url_type','','default','N','0','admin','2021-01-05 13:49:16','admin','2021-01-05 13:49:20',''),('563746747239763968',0,'微信','1','payment_type','','default','Y','0','admin','2021-06-12 17:35:09','admin','2021-06-12 17:37:14',''),('563746783184949248',0,'支付宝','2','payment_type','','default','Y','0','admin','2021-06-12 17:35:17','admin','2021-06-12 17:37:18',''),('563746818496794624',0,'水滴筹','3','payment_type','','default','Y','0','admin','2021-06-12 17:35:26','admin','2021-06-12 17:37:21',''),('563747125104611328',0,'火锅底料','1','gift_type','','default','Y','0','admin','2021-06-12 17:36:39','admin','2021-06-12 17:36:50',''),('563747405598691328',0,'冒菜底料','2','gift_type','','default','Y','0','admin','2021-06-12 17:37:46','','2021-06-12 17:37:46',''),('563747459235450880',0,'重庆小面佐料','3','gift_type','','default','Y','0','admin','2021-06-12 17:37:58','','2021-06-12 17:37:58',''),('563747480336994304',0,'其他','4','gift_type','','default','Y','0','admin','2021-06-12 17:38:03','','2021-06-12 17:38:03',''),('571366029360500736',0,'是','1','yes_or_no','','default','Y','0','admin','2021-07-03 18:11:27','','2021-07-03 18:11:27',''),('571366105029939200',0,'否','-1','yes_or_no','','info','Y','0','admin','2021-07-03 18:11:45','','2021-07-03 18:11:45','');
/*!40000 ALTER TABLE `def_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_dict_type`
--

DROP TABLE IF EXISTS `def_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_dict_type` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典名称',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dict_type` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_dict_type`
--

LOCK TABLES `def_dict_type` WRITE;
/*!40000 ALTER TABLE `def_dict_type` DISABLE KEYS */;
INSERT INTO `def_dict_type` VALUES ('1','性别','gender','1','',NULL,'',NULL,''),('340079827459641344','省份状态','sys_province_state','1','admin','2019-10-04 20:42:39','','2019-10-04 20:42:39','省份状态'),('373493952487231488','拦截器类型','sys_inter_url_type','1','admin','2020-01-05 01:38:28','admin','2020-03-29 23:23:43','拦截器类型'),('563746635880992768','捐款类型','payment_type','1','admin','2021-06-12 17:34:42','','2021-06-12 17:34:42',''),('563747016396640256','礼物类型','gift_type','1','admin','2021-06-12 17:36:13','','2021-06-12 17:36:13',''),('571365854613213184','是与否','yes_or_no','1','admin','2021-07-03 18:10:45','','2021-07-03 18:10:45','用于select'),('6','通知类型','sys_notice_type','1','admin','2018-03-16 11:33:00','admin','2020-12-27 14:26:42','通知类型列表');
/*!40000 ALTER TABLE `def_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_notice`
--

DROP TABLE IF EXISTS `def_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_notice` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `type` int DEFAULT NULL COMMENT '类型',
  `create_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '发信时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_notice`
--

LOCK TABLES `def_notice` WRITE;
/*!40000 ALTER TABLE `def_notice` DISABLE KEYS */;
INSERT INTO `def_notice` VALUES ('330381411007729664','测试公告','<p>啊啊啊<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>',1,'1','admin','2019-09-08 02:24:37'),('330381806358630400','鲜花视频','<p>哈哈哈哈<img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>',2,'1','admin','2019-09-08 02:26:11'),('373478036928073728','顶顶顶顶顶顶顶顶顶','<p>顶顶顶顶顶顶顶顶顶顶<img src=\"http://img.baidu.com/hi/jx2/j_0014.gif\"/></p>',1,'1','admin','2020-01-05 00:35:13');
/*!40000 ALTER TABLE `def_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_permission`
--

DROP TABLE IF EXISTS `def_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_permission` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权链接',
  `is_blank` int DEFAULT '0' COMMENT '是否跳转 0 不跳转 1跳转',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父节点id',
  `code` varchar(191) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `type` int DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `status` int DEFAULT NULL COMMENT '是否可见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_permission`
--

LOCK TABLES `def_permission` WRITE;
/*!40000 ALTER TABLE `def_permission` DISABLE KEYS */;
INSERT INTO `def_permission` VALUES (4,'用户管理','用户展示','/UserController/view',0,'411522822607867904','system:user:view',1,'icon icon-user',1,1),(5,'用户集合','用户集合','/UserController/list',0,'4','system:user:list',2,'',NULL,1),(6,'用户添加','用户添加','/UserController/add',0,'4','system:user:add',2,'entypo-plus-squared',NULL,1),(7,'用户删除','用户删除','/UserController/remove',0,'4','system:user:remove',2,'entypo-trash',NULL,1),(8,'用户修改','用户修改','/UserController/edit',0,'4','system:user:edit',2,'fa fa-wrench',NULL,1),(9,'角色管理','角色展示','/RoleController/view',0,'411522822607867904','system:role:view',1,'fa fa-group',2,1),(10,'角色集合','角色集合','/RoleController/list',0,'9','system:role:list',2,'',NULL,1),(11,'角色添加','角色添加','/RoleController/add',0,'9','system:role:add',2,'entypo-plus-squared',NULL,1),(12,'角色删除','角色删除','/RoleController/remove',0,'9','system:role:remove',2,'entypo-trash',NULL,1),(13,'角色修改','角色修改','/RoleController/edit',0,'9','system:role:edit',2,'fa fa-wrench',NULL,1),(14,'权限展示','权限展示','/PermissionController/view',0,'411522822607867904','system:permission:view',1,'fa fa-key',3,1),(15,'权限集合','权限集合','/PermissionController/list',0,'14','system:permission:list',2,'',NULL,1),(16,'权限添加','权限添加','/permissionController/add',0,'14','system:permission:add',2,'entypo-plus-squared',NULL,1),(17,'权限删除','权限删除','/PermissionController/remove',0,'14','system:permission:remove',2,'entypo-trash',NULL,1),(18,'权限修改','权限修改','/PermissionController/edit',0,'14','system:permission:edit',2,'fa fa-wrench',NULL,1),(19,'文件管理','文件管理','/FileController/view',0,'592059865673760768','system:file:view',1,'fa fa-file-image-o',4,1),(20,'文件添加','文件添加','/FileController/add',0,'19','system:file:add',2,'entypo-plus-squared',NULL,1),(21,'文件删除','文件删除','/FileController/remove',0,'19','system:file:remove',2,'entypo-trash',NULL,1),(22,'文件修改','文件修改','/FileController/edit',0,'19','system:file:edit',2,'fa fa-wrench',NULL,1),(23,'文件集合','文件集合','/FileController/list',0,'19','system:file:list',2,'',NULL,1),(330365026642825216,'公告管理','公告展示','/SysNoticeController/view',0,'592059865673760768','gen:sysNotice:view',1,'fa fa-telegram',10,1),(332157860920299520,'定时任务','定时任务调度表展示','/SysQuartzJobController/view',0,'592059865673760768','gen:sysQuartzJob:view',1,'fa fa-hourglass-1',13,1),(332857281479839744,'定时任务日志','定时任务日志','/SysQuartzJobLogController/view',0,'592059865673760768','gen:sysQuartzJobLog:view',1,'fa fa-database',14,1),(410791701859405824,'岗位管理','岗位展示','/PositionController/view',0,'411522822607867904','system:position:view',1,'fa fa-vcard',17,1),(410989805699207168,'部门管理','部门展示','/DepartmentController/view',0,'411522822607867904','system:department:view',1,'fa fa-odnoklassniki',18,1),(411522822607867904,'用户管理',NULL,'',0,'0','',0,'layui-icon layui-icon-user',3,1),(486690002869157888,'用户密码修改','用户密码修改','/UserController/editPwd',0,'4','system:user:editPwd',2,'entypo-tools',3,1),(496126970468237312,'日志展示','日志管理','/LogController/view',0,'592059865673760768','system:log:view',1,'fa fa-info',9,1),(496127240363311104,'日志删除','日志删除','/LogController/remove',0,'496126970468237312','system:log:remove',2,'entypo-trash',NULL,1),(496127794879660032,'日志集合','日志集合','/LogController/list',0,'496126970468237312','system:log:list',2,NULL,NULL,1),(592059865673760768,'系统管理',NULL,'',0,'0','',0,'layui-icon layui-icon-home',1,1),(610635485890478080,'代码生成',NULL,'',0,'0','',0,'layui-icon layui-icon layui-icon layui-icon-praise',2,1),(610635950447394816,'全局配置','','/autoCodeController/global',0,'610635485890478080','system:autocode:global',1,'fa fa-university',NULL,1),(618918631769636864,'字典管理','字典类型表展示','/DictTypeController/view',0,'592059865673760768','system:dictType:view',1,'fa fa-puzzle-piece',11,1),(619836559427895296,'字典数据视图','字典数据视图','/DictDataController/view',0,'618918631769636864','system:dictData:view',2,NULL,NULL,1),(815087359539417088,'www',NULL,'',0,'0',NULL,0,'layui-icon ',1,NULL),(815091441268297728,'ww',NULL,'',0,'592059865673760768','',0,'layui-icon layui-icon-rate',1,1),(3303650266428252171,'公告修改','公告修改','/SysNoticeController/edit',0,'330365026642825216','gen:sysNotice:edit',2,'fa fa-wrench',NULL,1),(3303650266428252182,'公告修改','公告修改','/SysNoticeController/edit',0,'330365026642825216','gen:sysNotice:edit',2,'fa fa-wrench',NULL,1),(3303650266428252193,'公告修改','公告修改','/SysNoticeController/edit',0,'330365026642825216','gen:sysNotice:edit',2,'fa fa-wrench',NULL,1),(3303650266428252204,'公告修改','公告修改','/SysNoticeController/edit',0,'330365026642825216','gen:sysNotice:edit',2,'fa fa-wrench',NULL,1),(3321578609202995211,'定时任务调度表修改','定时任务调度表修改','/SysQuartzJobController/edit',0,'332157860920299520','gen:sysQuartzJob:edit',2,'fa fa-wrench',NULL,1),(3321578609202995222,'定时任务调度表修改','定时任务调度表修改','/SysQuartzJobController/edit',0,'332157860920299520','gen:sysQuartzJob:edit',2,'fa fa-wrench',NULL,1),(3321578609202995233,'定时任务调度表修改','定时任务调度表修改','/SysQuartzJobController/edit',0,'332157860920299520','gen:sysQuartzJob:edit',2,'fa fa-wrench',NULL,1),(3321578609202995244,'定时任务调度表修改','定时任务调度表修改','/SysQuartzJobController/edit',0,'332157860920299520','gen:sysQuartzJob:edit',2,'fa fa-wrench',NULL,1),(3328572814798397451,'定时任务调度日志表删除','定时任务调度日志表删除','/SysQuartzJobLogController/remove',0,'332857281479839744','gen:sysQuartzJobLog:remove',2,'entypo-trash',NULL,1),(3328572814798397473,'定时任务调度日志表删除','定时任务调度日志表删除','/SysQuartzJobLogController/remove',0,'332857281479839744','gen:sysQuartzJobLog:remove',2,'entypo-trash',NULL,1),(4107917018594058251,'岗位集合','岗位集合','/PositionController/list',0,'410791701859405824','system:position:list',2,'layui-icon fa fa-wrench',NULL,1),(4107917018594058262,'岗位表添加','岗位添加','/PositionController/add',0,'410791701859405824','system:position:add',2,'fa fa-wrench',NULL,1),(4107917018594058273,'岗位表删除','岗位删除','/PositionController/remove',0,'410791701859405824','system:position:remove',2,'fa fa-wrench',NULL,1),(4107917018594058284,'岗位表修改','岗位编辑','/PositionController/edit',0,'410791701859405824','system:position:edit',2,'fa fa-wrench',NULL,1),(4109898056992071691,'部门集合','部门集合','/DepartmentController/list',0,'410989805699207168','system:department:list',2,'fa fa-wrench',NULL,1),(4109898056992071702,'部门添加','部门添加','/DepartmentController/add',0,'410989805699207168','system:department:add',2,'fa fa-wrench',NULL,1),(4109898056992071713,'部门删除','部门删除','/DepartmentController/remove',0,'410989805699207168','system:department:remove',2,'fa fa-wrench',NULL,1),(4109898056992071724,'部门修改','部门修改','/DepartmentController/edit',0,'410989805699207168','system:department:edit',2,'fa fa-wrench',NULL,1),(6189186317738311681,'字典类型表集合','字典类型表集合','/DictTypeController/list',0,'618918631769636864','system:dictType:list',2,NULL,NULL,1),(6189186317948026882,'字典类型表删除','字典类型表删除','/DictTypeController/remove',0,'618918631769636864','system:dictType:remove',2,NULL,NULL,1),(6189186317948026883,'字典类型表删除','字典类型表删除','/DictTypeController/remove',0,'618918631769636864','system:dictType:remove',2,NULL,NULL,1),(6189186317989969924,'字典类型表修改','字典类型表修改','/DictTypeController/edit',0,'618918631769636864','system:dictType:edit',2,NULL,NULL,1),(6192095214866268161,'字典数据表添加','字典数据表添加','/DictDataController/add',0,'618918631769636864','system:dictData:add',2,NULL,NULL,1),(6192095214866268162,'字典数据表添加','字典数据表添加','/DictDataController/add',0,'618918631769636864','system:dictData:add',2,NULL,NULL,1),(6192095215075983363,'字典数据表修改','字典数据表修改','/DictDataController/edit',0,'618918631769636864','system:dictData:edit',2,NULL,NULL,1),(6192095215075983364,'字典数据表修改','字典数据表修改','/DictDataController/edit',0,'618918631769636864','system:dictData:edit',2,NULL,NULL,1);
/*!40000 ALTER TABLE `def_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_position`
--

DROP TABLE IF EXISTS `def_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_position` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位名称',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `status` int DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_position`
--

LOCK TABLES `def_position` WRITE;
/*!40000 ALTER TABLE `def_position` DISABLE KEYS */;
INSERT INTO `def_position` VALUES ('48','总经理',1,1),('52','技术经理',2,1);
/*!40000 ALTER TABLE `def_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_role`
--

DROP TABLE IF EXISTS `def_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_role` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_role`
--

LOCK TABLES `def_role` WRITE;
/*!40000 ALTER TABLE `def_role` DISABLE KEYS */;
INSERT INTO `def_role` VALUES ('24','用户','1',NULL),('4','能修改用户密码角色','1',NULL),('60','管理员','1','管理员角色');
/*!40000 ALTER TABLE `def_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `def_user`
--

DROP TABLE IF EXISTS `def_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `def_user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
  `real_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `dept_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '部门id',
  `pos_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位id',
  `employee_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `def_user`
--

LOCK TABLES `def_user` WRITE;
/*!40000 ALTER TABLE `def_user` DISABLE KEYS */;
INSERT INTO `def_user` VALUES ('1','admin','21232f297a57a5a743894a0e4a801fc3','管理员','2','48','t53','1','15824534567','1','123@qq.com'),('2','fuce','21232f297a57a5a743894a0e4a801fc3','付册','2','52','125','1','15245653454','0','222@qq.com'),('809728789289504768','123','202cb962ac59075b964b07152d234b70','123','1','48','','0','','0','');
/*!40000 ALTER TABLE `def_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_notice_user`
--

DROP TABLE IF EXISTS `rel_notice_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rel_notice_user` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `notice_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公告id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `state` int DEFAULT NULL COMMENT '0未阅读 1 阅读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='公告_用户外键';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_notice_user`
--

LOCK TABLES `rel_notice_user` WRITE;
/*!40000 ALTER TABLE `rel_notice_user` DISABLE KEYS */;
INSERT INTO `rel_notice_user` VALUES ('330381411037089792','330381411007729664','1',1),('330381411045478400','330381411007729664','488294747442511872',0),('330381806375407616','330381806358630400','1',1),('330381806379601920','330381806358630400','488294747442511872',0),('330622143622680576','330622143597514752','1',1),('330622143626874880','330622143597514752','488294747442511872',0),('354984345649418240','354984345632641024','1',1),('373478037158760448','373478036928073728','1',1),('373478037162954752','373478036928073728','368026921239449600',0),('373478037171343360','373478036928073728','368026937181999104',0),('373478037175537664','373478036928073728','368027013392502784',0),('373478037183926272','373478036928073728','368027030899527680',0),('373478037192314880','373478036928073728','368027048402358272',0),('373478037204897792','373478036928073728','368027066563694592',0),('373478037213286400','373478036928073728','368027087866564608',0),('373478037217480704','373478036928073728','368027104895438848',0),('373478037225869312','373478036928073728','368027130728157184',0),('373478037230063616','373478036928073728','368027151624179712',0),('373478037238452224','373478036928073728','368382463233363968',0),('502750147499921408','502750147395063808','1',0),('502750147508310016','502750147395063808','433236479427350528',0),('502758207983325184','502758207907827712','1',0),('502758207991713792','502758207907827712','433236479427350528',0),('502820822130495488','502820822042415104','1',0),('502820822138884096','502820822042415104','433236479427350528',0);
/*!40000 ALTER TABLE `rel_notice_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_permission_role`
--

DROP TABLE IF EXISTS `rel_permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rel_permission_role` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色权限中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_permission_role`
--

LOCK TABLES `rel_permission_role` WRITE;
/*!40000 ALTER TABLE `rel_permission_role` DISABLE KEYS */;
INSERT INTO `rel_permission_role` VALUES ('1654510175567634433','24','340068151804956700'),('1654510176024813569','24','3400681518049566700'),('1654510176289054721','24','3400681518049566700'),('1654510176742039553','24','3400681518049566700'),('1654510177195024385','24','3400681518049566700'),('1654510177593483266','24','340088022018166800'),('1654510178059051009','24','3400880220181668000'),('1654510179325730817','24','3400880220181668000'),('1654510180047151105','24','3400880220181668000'),('1654510181498380289','24','3400880220181668000'),('1654510184396644354','24','340096183135506400'),('1654510184853823490','24','3400961831355064300'),('1654510185118064641','24','3400961831355064300'),('1654510185579438081','24','3400961831355064300'),('1654510185973702657','24','3400961831355064300'),('1654510186430881793','24','340127412270534660'),('1654510186825146370','24','3401274122705346600'),('1654510187282325505','24','3401274122705346600'),('1654510187680784386','24','3401274122705346600'),('1654510188146352129','24','3401274122705346600'),('1654510188536422402','24','340301160042860540'),('1656210828173078530','60','411522822607867904'),('1656210828173078531','60','4'),('1656210828240187394','60','5'),('1656210828303101954','60','6'),('1656210828303101955','60','7'),('1656210828366016514','60','8'),('1656210828433125378','60','486690002869157888'),('1656210828433125379','60','9'),('1656210828496039938','60','10'),('1656210828563148802','60','11'),('1656210828563148803','60','12'),('1656210828626063361','60','13'),('1656210828693172225','60','14'),('1656210828693172226','60','15'),('1656210828756086785','60','16'),('1656210828823195649','60','17'),('1656210828823195650','60','18'),('1656210828886110210','60','410791701859405824'),('1656210828949024769','60','4107917018594058251'),('1656210828949024770','60','4107917018594058262'),('1656210829016133633','60','4107917018594058273'),('1656210829079048193','60','4107917018594058284'),('1656210829141962754','60','410989805699207168'),('1656210829141962755','60','4109898056992071691'),('1656210829209071617','60','4109898056992071702'),('1656210829271986177','60','4109898056992071713'),('1656210829271986178','60','4109898056992071724'),('1656210829339095042','60','592059865673760768'),('1656210829339095043','60','19'),('1656210829402009602','60','20'),('1656210829464924162','60','21'),('1656210829464924163','60','22'),('1656210829527838722','60','23'),('1656210829527838723','60','330365026642825216'),('1656210829594947586','60','3303650266428252171'),('1656210829657862145','60','3303650266428252182'),('1656210829657862146','60','3303650266428252193'),('1656210829724971009','60','3303650266428252204'),('1656210829724971010','60','332157860920299520'),('1656210829787885569','60','3321578609202995211'),('1656210829787885570','60','3321578609202995222'),('1656210829850800129','60','3321578609202995233'),('1656210829913714690','60','3321578609202995244'),('1656210829913714691','60','332857281479839744'),('1656210829976629250','60','3328572814798397451'),('1656210830043738113','60','3328572814798397473'),('1656210830106652673','60','496126970468237312'),('1656210830106652674','60','496127240363311104'),('1656210830169567233','60','496127794879660032'),('1656210830169567234','60','618918631769636864'),('1656210830232481793','60','619836559427895296'),('1656210830299590657','60','6189186317738311681'),('1656210830299590658','60','6189186317948026882'),('1656210830362505218','60','6189186317948026883'),('1656210830362505219','60','6189186317989969924'),('1656210830425419777','60','6192095214866268161'),('1656210830425419778','60','6192095214866268162'),('1656210830492528642','60','6192095215075983363'),('1656210830492528643','60','6192095215075983364'),('1656210830555443201','60','815091441268297728'),('1656210830618357761','60','610635485890478080'),('1656210830618357762','60','610635950447394816');
/*!40000 ALTER TABLE `rel_permission_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_role_user`
--

DROP TABLE IF EXISTS `rel_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rel_role_user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户角色中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_role_user`
--

LOCK TABLES `rel_role_user` WRITE;
/*!40000 ALTER TABLE `rel_role_user` DISABLE KEYS */;
INSERT INTO `rel_role_user` VALUES ('809617611225698304','2','4'),('809628583499796480','1','24'),('809628583583682560','1','4'),('809628583608848384','1','60');
/*!40000 ALTER TABLE `rel_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_department`
--

DROP TABLE IF EXISTS `t_sys_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_department` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` int DEFAULT NULL COMMENT '状态',
  `order_num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_department`
--

LOCK TABLES `t_sys_department` WRITE;
/*!40000 ALTER TABLE `t_sys_department` DISABLE KEYS */;
INSERT INTO `t_sys_department` VALUES (1,'0','v2','v2','13012345678','v2@qq.com',1,1),(2,'1','技术部门','x某某','13012345678','v2@qq.com',1,2),(3,'1','人事部门','a某某','13012345678','v2@qq.com',1,3),(4,'2','开发一小组','b某某','13012345678','v2@qq.com',1,4),(5,'3','销售部门','d某某','13012345678','v2@qq.com',1,5),(6,'5','销售一组','e某某','13012345678','v2@qq.com',1,6);
/*!40000 ALTER TABLE `t_sys_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_inter_url`
--

DROP TABLE IF EXISTS `t_sys_inter_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_inter_url` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `inter_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '拦截名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '拦截url',
  `type` int DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='拦截url表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_inter_url`
--

LOCK TABLES `t_sys_inter_url` WRITE;
/*!40000 ALTER TABLE `t_sys_inter_url` DISABLE KEYS */;
INSERT INTO `t_sys_inter_url` VALUES ('411495038321823744','字典表新增','/DictDataController/add',2),('506433268967673856','字典表修改','/DictDataController/edit',2),('506434978159136768','字典表删除','/DictDataController/remove',3),('506435565655298048','字典表状态修改','/DictDataController/updateDefault',4),('506435921147727872','字典表状态修改2','/DictDataController/updateEnable',4),('506436031403397120','字典表类型新增','/DictTypeController/add',2),('506436148680331264','字典表类型修改','/DictTypeController/edit',2),('506436165776314368','字典表类型删除','/DictTypeController/remove',3),('506436180578013184','字典表类型状态修改','/DictTypeController/updateEnable',4),('506436662134444032','邮件新增','/EmailController/add',2),('506436757722632192','邮件删除','/EmailController/remove',3),('506437010966319104','日志删除','/LogController/remove',3),('506437420099702784','oss新增','/oss/bucket/',2),('506437439112482816','oss删除','/oss/bucket/',3),('506437964436475904','权限新增','/PermissionController/add',2),('506438040823140352','权限保存','/PermissionController/edit',2),('506438121399914496','权限删除','/PermissionController/remove',3),('506438208599494656','权限授权','/PermissionController/saveRolePower',4),('506438306276446208','权限状态修改','/PermissionController/updateVisible',4),('506438447226032128','定时器新增','/SysQuartzJobController/add',2),('506438589874311168',' 任务调度状态修改','/SysQuartzJobController/changeStatus',4),('506438725388079104','定时器保存','/SysQuartzJobController/edit',2),('506438870959788032','定时器修改','/SysQuartzJobController/remove',3),('506439003516571648','定时任务日志删除','/SysQuartzJobLogController/remove',3),('506439171481669632','角色新增','/RoleController/add',2),('506439186778296320','角色修改','/RoleController/edit',4),('506439297122045952','角色删除','/RoleController/remove',3),('506439669773373440','地区新增','/SysAreaController/add',2),('506439687859212288','地区修改','/SysAreaController/edit',2),('506439835490324480','地区删除','/SysAreaController/remove',3),('506440103976112128','City新增','/SysCityController/add',2),('506440145147400192','City修改',' /SysCityController/edit',2),('506440217188765696','City删除','/SysCityController/remove',3),('506440386873528320','部门新增','/SysDepartmentController/add',2),('506440448223612928','部门修改','/SysDepartmentController/edit',4),('506440515110178816','部门删除','/SysDepartmentController/remove',3),('506440574635741184','部门状态','/SysDepartmentController/updateVisible',4),('506440668508459008','拦截器url新增','/SysInterUrlController/add',2),('506440708056551424','拦截器url修改','/SysInterUrlController/edit',2),('506440802856210432','拦截器url删除','/SysInterUrlController/remove',3),('506441001783660544','公告新增','/SysNoticeController/add',2),('506441051263864832','公告修改','/SysNoticeController/edit',2),('506441105743679488','公告删除','/SysNoticeController/remove',3),('506441242591236096','职位新增','/SysPositionController/add',2),('506441287038275584','职位修改','/SysPositionController/edit',2),('506441350200299520','职位删除','/SysPositionController/remove',3),('506441420677189632','职位状态修改','/SysPositionController/updateVisible',4),('506441780003213312','省份新增','/SysProvinceController/add',2),('506441807383629824','省份修改','/SysProvinceController/edit',2),('506441871850082304','省份删除','/SysProvinceController/remove',3),('506441980012793856','街道新增','/SysStreetController/add',2),('506442015706320896','街道修改','/SysStreetController/edit',2),('506442092445306880','街道删除','/SysStreetController/remove',3),('506442186552905728','用户新增','/UserController/add',2),('506442212696002560','用户修改','/UserController/edit',2),('506442271252680704','用户修改密码','/UserController/editPwd',2),('506442344443285504','用户删除','/UserController/remove',3),('506444610625736704','拦截器url复制','/SysInterUrlController/copy/',3);
/*!40000 ALTER TABLE `t_sys_inter_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_oper_log`
--

DROP TABLE IF EXISTS `t_sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_oper_log` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '方法',
  `oper_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'url',
  `oper_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  `error_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `oper_time` date DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='日志记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_oper_log`
--

LOCK TABLES `t_sys_oper_log` WRITE;
/*!40000 ALTER TABLE `t_sys_oper_log` DISABLE KEYS */;
INSERT INTO `t_sys_oper_log` VALUES ('354984005894017024','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin22\"],\"password\":[\"admin22\"],\"nickname\":[\"222\"],\"roles\":[\"488243256161730560\"]}',NULL,'2019-11-14'),('354988722611163136','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin222\"],\"password\":[\"admin22\"],\"nickname\":[\"22222\"],\"roles\":[\"488243256161730560\"]}',NULL,'2019-11-15'),('354989789822455808','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin33\"],\"password\":[\"admin33\"],\"nickname\":[\"333\"],\"roles\":[\"488305788310257664\"]}',NULL,'2019-11-15'),('368026921411416064','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin1\"],\"password\":[\"admin1\"],\"nickname\":[\"\"]}',NULL,'2019-12-20'),('368026937215553536','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin2\"],\"password\":[\"admin2\"],\"nickname\":[\"\"]}',NULL,'2019-12-20'),('368026972204437504','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"33333\"],\"password\":[\"3333333\"],\"nickname\":[\"333\"]}',NULL,'2019-12-20'),('368027013421862912','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"44\"],\"password\":[\"444444\"],\"nickname\":[\"444\"]}',NULL,'2019-12-20'),('368027030928887808','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"5555555\"],\"password\":[\"555555555555\"],\"nickname\":[\"5555555555555\"]}',NULL,'2019-12-20'),('368027048427524096','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"66\"],\"password\":[\"666666666\"],\"nickname\":[\"6666666666\"]}',NULL,'2019-12-20'),('368027066593054720','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"2222\"],\"password\":[\"222222222\"],\"nickname\":[\"2222222222222222\"]}',NULL,'2019-12-20'),('368027087887536128','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"4444\"],\"password\":[\"44444444444444\"],\"nickname\":[\"44444444444\"]}',NULL,'2019-12-20'),('368027104924798976','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"5555\"],\"password\":[\"55555555555555\"],\"nickname\":[\"555555555555\"]}',NULL,'2019-12-20'),('368027130757517312','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"666\"],\"password\":[\"66666666666\"],\"nickname\":[\"666666666\"]}',NULL,'2019-12-20'),('368027151649345536','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"33333333333333\"],\"password\":[\"333333333333\"],\"nickname\":[\"33333333333333\"]}',NULL,'2019-12-20'),('368382463388553216','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"adminpppoooo\"],\"password\":[\"adminppp\"],\"nickname\":[\"pppppppppp\"]}',NULL,'2019-12-21'),('433236479515430912','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"fuce\"],\"password\":[\"111111\"],\"nickname\":[\"fuce\"]}',NULL,'2020-06-17'),('495560243967823872','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"手动阀手动阀\"],\"password\":[\"123456\"],\"nickname\":[\"手动阀手动阀\"],\"depId\":[\"3\"],\"positionId\":[\"411477874382606336\"]}',NULL,'2020-12-06'),('495570972590608384','用户新增','com.fc.test.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"何平\"],\"password\":[\"111111\"],\"nickname\":[\"手动阀手动阀\"],\"depId\":[\"4\"],\"posId\":[\"410792443127140352\"]}',NULL,'2020-12-06'),('501769007083425792','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501772647076597760','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501775645991374848','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501776479886118912','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501779375067369472','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501779625379237888','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501780125961031680','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{}','','2020-12-24'),('501782630312841216','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"eeee\"],\"password\":[\"eeeeee\"],\"nickname\":[\"eeeeeeee\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"488243256161730560,488289006124007424\"]}',NULL,'2020-12-24'),('501783503843758080','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"55555\"],\"password\":[\"5555\"],\"nickname\":[\"555555\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"488243256161730560,488289006124007424\"]}',NULL,'2020-12-24'),('501783738078859264','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"3\"],\"password\":[\"33333333\"],\"nickname\":[\"3333333333333333333\"],\"depId\":[\"3\"],\"selectParent_select_input\":[\"人事部门\"],\"posId\":[\"410792443127140352\"],\"roleIds\":[\"488289006124007424\"]}',NULL,'2020-12-24'),('501786177666420736','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"eeee\"],\"password\":[\"eee\"],\"nickname\":[\"eeeee\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792478929719296\"],\"roleIds\":[\"488243256161730560,488289006124007424,488305788310257664\"]}',NULL,'2020-12-24'),('501786241449201664','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"r\"],\"password\":[\"rrrrrrrrrrr\"],\"nickname\":[\"rrrrrrrrr\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"488243256161730560,488289006124007424,488305788310257664\"]}',NULL,'2020-12-24'),('501786725912285184','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"rrrrr\"],\"password\":[\"rrrrrrrrrrrrrr\"],\"nickname\":[\"rrrrrrrrrrrrrr\"],\"depId\":[\"3\"],\"selectParent_select_input\":[\"人事部门\"],\"posId\":[\"410792478929719296\"],\"roleIds\":[\"488243256161730560,488289006124007424\"]}',NULL,'2020-12-24'),('501787814850072576','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"rrrrrwwww\"],\"password\":[\"rrrrrrrrrrrrr\"],\"nickname\":[\"rrrrrrrrrrrrrrrrrr\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"\"]}',NULL,'2020-12-24'),('501787928188555264','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"yyyy\"],\"password\":[\"yyyyyyyyyyyyy\"],\"nickname\":[\"yyyyyyyyyyyyyy\"],\"depId\":[\"5\"],\"selectParent_select_input\":[\"销售部门\"],\"posId\":[\"410792478929719296\"],\"roleIds\":[\"488243256161730560,488289006124007424,488305788310257664\"]}',NULL,'2020-12-24'),('501796773694672896','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"tttt\"],\"password\":[\"ttt\"],\"nickname\":[\"tttttt\"],\"depId\":[\"4\"],\"selectParent_select_input\":[\"开发一小组\"],\"posId\":[\"410792443127140352\"],\"roleIds\":[\"488243256161730560,488305788310257664\"]}',NULL,'2020-12-24'),('501985140440961024','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin\"],\"password\":[\"admin\"],\"nickname\":[\"11111111111\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"488289006124007424,488305788310257664\"]}',NULL,'2020-12-24'),('501986656363089920','用户新增','com.fc.v2.controller.admin.UserController.add()','admin','/UserController/add','{\"username\":[\"admin\"],\"password\":[\"admin\"],\"nickname\":[\"admin\"],\"depId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"410792368778907648\"],\"roleIds\":[\"488289006124007424,488305788310257664\"]}',NULL,'2020-12-24'),('809628850983145472','用户新增','com.fastproject.controller.admin.UserController.add()','admin','/UserController/add','{\"realName\":[\"11\"],\"username\":[\"11\"],\"password\":[\"\"],\"employeeId\":[\"\"],\"phone\":[\"11111111111\"],\"email\":[\"11111@qq.11\"],\"gender\":[\"0\"],\"deptId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"48\"],\"roleIds\":[\"\"]}','nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'nickname\' in \'class com.fastproject.model.auto.User\'','2023-04-22'),('809628874118926336','用户新增','com.fastproject.controller.admin.UserController.add()','admin','/UserController/add','{\"realName\":[\"11\"],\"username\":[\"11\"],\"password\":[\"\"],\"employeeId\":[\"\"],\"phone\":[\"11111111111\"],\"email\":[\"11111@qq.11\"],\"gender\":[\"0\"],\"deptId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"48\"],\"roleIds\":[\"\"]}','nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'nickname\' in \'class com.fastproject.model.auto.User\'','2023-04-22'),('809629485040275456','用户新增','com.fastproject.controller.admin.UserController.add()','admin','/UserController/add','{\"realName\":[\"11\"],\"username\":[\"11\"],\"password\":[\"\"],\"employeeId\":[\"\"],\"phone\":[\"11111111111\"],\"email\":[\"11111@qq.11\"],\"gender\":[\"0\"],\"deptId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"48\"],\"roleIds\":[\"4,60\"]}','nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'nickname\' in \'class com.fastproject.model.auto.User\'','2023-04-22'),('809716950983905280','用户新增','com.fastproject.controller.admin.UserController.add()','admin','/UserController/add','{\"realName\":[\"姓名\"],\"username\":[\"登录名称\"],\"password\":[\"123456\"],\"employeeId\":[\"工号\"],\"phone\":[\"电话\"],\"email\":[\"邮箱\"],\"gender\":[\"1\"],\"deptId\":[\"5\"],\"selectParent_select_input\":[\"销售部门\"],\"posId\":[\"52\"],\"roleIds\":[\"24\"]}','nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'nickname\' in \'class com.fastproject.model.auto.User\'','2023-04-22'),('809728789989953536','用户新增','com.fastproject.controller.admin.UserController.add()','admin','/UserController/add','{\"realName\":[\"123\"],\"username\":[\"123\"],\"password\":[\"\"],\"employeeId\":[\"\"],\"phone\":[\"\"],\"email\":[\"\"],\"gender\":[\"0\"],\"deptId\":[\"1\"],\"selectParent_select_input\":[\"v2\"],\"posId\":[\"48\"],\"roleIds\":[\"\"]}',NULL,'2023-04-22');
/*!40000 ALTER TABLE `t_sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_quartz_job`
--

DROP TABLE IF EXISTS `t_sys_quartz_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_quartz_job` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志id',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cron执行表达式',
  `misfire_policy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cron计划策略',
  `concurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否并发执行（0允许 1禁止）',
  `status` int DEFAULT NULL COMMENT '任务状态（0正常 1暂停）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_quartz_job`
--

LOCK TABLES `t_sys_quartz_job` WRITE;
/*!40000 ALTER TABLE `t_sys_quartz_job` DISABLE KEYS */;
INSERT INTO `t_sys_quartz_job` VALUES ('332182389491109888','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','*/5 * * * * ?','2','0',1);
/*!40000 ALTER TABLE `t_sys_quartz_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_quartz_job_log`
--

DROP TABLE IF EXISTS `t_sys_quartz_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_sys_quartz_job_log` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用目标字符串',
  `job_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志信息',
  `status` int DEFAULT NULL COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异常信息',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_quartz_job_log`
--

LOCK TABLES `t_sys_quartz_job_log` WRITE;
/*!40000 ALTER TABLE `t_sys_quartz_job_log` DISABLE KEYS */;
INSERT INTO `t_sys_quartz_job_log` VALUES ('333610566486724608','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2019-09-17 00:16:07','2019-09-17 00:16:07'),('333610572270669824','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2019-09-17 00:16:09','2019-09-17 00:16:09'),('354984595927732224','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',0,NULL,'2019-11-14 23:48:53','2019-11-14 23:48:53'),('354990312722141184','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2019-11-15 00:11:36','2019-11-15 00:11:36'),('354996339316232192','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',0,NULL,'2019-11-15 00:35:33','2019-11-15 00:35:33'),('415421274211356672','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：5毫秒',0,NULL,'2020-04-29 18:22:40','2020-04-29 18:22:40'),('415421274303631360','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:22:40','2020-04-29 18:22:40'),('415421274324602880','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:22:40','2020-04-29 18:22:40'),('415421274366545920','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:22:40','2020-04-29 18:22:40'),('415421315554611200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:22:50','2020-04-29 18:22:50'),('415421357501845504','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:23:00','2020-04-29 18:23:00'),('415421399453274112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:23:10','2020-04-29 18:23:10'),('415421441375342592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:23:20','2020-04-29 18:23:20'),('415421483351937024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-04-29 18:23:30','2020-04-29 18:23:30'),('415421525257228288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:23:40','2020-04-29 18:23:40'),('415421567233822720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:23:50','2020-04-29 18:23:50'),('415421609130725376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:24:00','2020-04-29 18:24:00'),('415421648662040576','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:24:09','2020-04-29 18:24:09'),('415421651073765376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:24:10','2020-04-29 18:24:10'),('415421693041971200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:24:20','2020-04-29 18:24:20'),('415421734959845376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:24:30','2020-04-29 18:24:30'),('415421776974188544','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:24:40','2020-04-29 18:24:40'),('415421818862702592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:24:50','2020-04-29 18:24:50'),('415421860805742592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:25:00','2020-04-29 18:25:00'),('415421902736199680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:25:10','2020-04-29 18:25:10'),('415423552540512256','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：4毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423552636981248','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423552670535680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423552687312896','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423552716673024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423552741838848','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:43','2020-04-29 18:31:43'),('415423559536611328','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:45','2020-04-29 18:31:45'),('415423580482965504','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:31:50','2020-04-29 18:31:50'),('415423601454485504','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:31:55','2020-04-29 18:31:55'),('415423622405033984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:32:00','2020-04-29 18:32:00'),('415423643372359680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:32:05','2020-04-29 18:32:05'),('415423664343879680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:10','2020-04-29 18:32:10'),('415423685311205376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:15','2020-04-29 18:32:15'),('415423706328862720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:20','2020-04-29 18:32:20'),('415423727325548544','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:25','2020-04-29 18:32:25'),('415423748250931200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:30','2020-04-29 18:32:30'),('415423769188896768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:35','2020-04-29 18:32:35'),('415423790227525632','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:40','2020-04-29 18:32:40'),('415423811190657024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:32:45','2020-04-29 18:32:45'),('415423832178954240','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:32:50','2020-04-29 18:32:50'),('415423853100142592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:32:55','2020-04-29 18:32:55'),('415423874054885376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:00','2020-04-29 18:33:00'),('415423895026405376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:05','2020-04-29 18:33:05'),('415423916002119680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:10','2020-04-29 18:33:10'),('415423937015582720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:15','2020-04-29 18:33:15'),('415423957970325504','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:20','2020-04-29 18:33:20'),('415423978925068288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:25','2020-04-29 18:33:25'),('415423999942725632','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:30','2020-04-29 18:33:30'),('415424020889079808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:35','2020-04-29 18:33:35'),('415424041894154240','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:40','2020-04-29 18:33:40'),('415424062844702720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:33:45','2020-04-29 18:33:45'),('415424083803639808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:50','2020-04-29 18:33:50'),('415424104754188288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:33:55','2020-04-29 18:33:55'),('415424125746679808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:34:00','2020-04-29 18:34:00'),('415424146726588416','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:05','2020-04-29 18:34:05'),('415424167727468544','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:10','2020-04-29 18:34:10'),('415424188678017024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:15','2020-04-29 18:34:15'),('415424209636954112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:20','2020-04-29 18:34:20'),('415424230595891200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:34:25','2020-04-29 18:34:25'),('415424251567411200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:30','2020-04-29 18:34:30'),('415424272559902720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:35','2020-04-29 18:34:35'),('415424293518839808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:40','2020-04-29 18:34:40'),('415424314507137024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:34:45','2020-04-29 18:34:45'),('415424335436713984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:34:50','2020-04-29 18:34:50'),('415424356404039680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:34:55','2020-04-29 18:34:55'),('415424377396531200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:35:00','2020-04-29 18:35:00'),('415424398414188544','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:05','2020-04-29 18:35:05'),('415424419326988288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:35:10','2020-04-29 18:35:10'),('415424440332062720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:35:15','2020-04-29 18:35:15'),('415424461282611200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:20','2020-04-29 18:35:20'),('415424482254131200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:25','2020-04-29 18:35:25'),('415424503200485376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:30','2020-04-29 18:35:30'),('415424524163616768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:35','2020-04-29 18:35:35'),('415424545135136768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:40','2020-04-29 18:35:40'),('415424566148599808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:35:45','2020-04-29 18:35:45'),('415424587103342592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:35:50','2020-04-29 18:35:50'),('415424608150360064','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:35:55','2020-04-29 18:35:55'),('415424629029605376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:36:00','2020-04-29 18:36:00'),('415424650072428544','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:05','2020-04-29 18:36:05'),('415424671035559936','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:10','2020-04-29 18:36:10'),('415424691981914112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:15','2020-04-29 18:36:15'),('415424712961822720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:20','2020-04-29 18:36:20'),('415424733899788288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:25','2020-04-29 18:36:25'),('415424754862919680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:30','2020-04-29 18:36:30'),('415424775888965632','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:36:35','2020-04-29 18:36:35'),('415424796797571072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:36:40','2020-04-29 18:36:40'),('415424817836199936','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:36:45','2020-04-29 18:36:45'),('415424838769971200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:36:50','2020-04-29 18:36:50'),('415424859741491200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:36:55','2020-04-29 18:36:55'),('415424880696233984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:37:00','2020-04-29 18:37:00'),('415424901705502720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:05','2020-04-29 18:37:05'),('415424922681217024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:10','2020-04-29 18:37:10'),('415424943690485760','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:15','2020-04-29 18:37:15'),('415424964632645632','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:20','2020-04-29 18:37:20'),('415424985562222592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:37:25','2020-04-29 18:37:25'),('415425006516965376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:30','2020-04-29 18:37:30'),('415425027484291072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:37:35','2020-04-29 18:37:35'),('415425048476782592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-04-29 18:37:40','2020-04-29 18:37:40'),('415425069427331072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:37:45','2020-04-29 18:37:45'),('415425090436599808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:37:50','2020-04-29 18:37:50'),('415425111412314112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:37:55','2020-04-29 18:37:55'),('415425132354473984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:38:00','2020-04-29 18:38:00'),('415425153334382592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:38:05','2020-04-29 18:38:05'),('415425174335262720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:10','2020-04-29 18:38:10'),('415425195290005504','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:38:15','2020-04-29 18:38:15'),('415425216248942592','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:20','2020-04-29 18:38:20'),('415425237224656896','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-04-29 18:38:25','2020-04-29 18:38:25'),('415425258200371200','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:30','2020-04-29 18:38:30'),('415425279192862720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:35','2020-04-29 18:38:35'),('415425300126633984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:38:40','2020-04-29 18:38:40'),('415425321089765376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:45','2020-04-29 18:38:45'),('415425342082256896','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:38:50','2020-04-29 18:38:50'),('415425363041193984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:38:55','2020-04-29 18:38:55'),('415425384000131072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:00','2020-04-29 18:39:00'),('415425404967456768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:05','2020-04-29 18:39:05'),('415425425997697024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:39:10','2020-04-29 18:39:10'),('415425446956634112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:15','2020-04-29 18:39:15'),('415425467902988288','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:20','2020-04-29 18:39:20'),('415425488866119680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:25','2020-04-29 18:39:25'),('415425509825056768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:39:30','2020-04-29 18:39:30'),('415425530800771072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:39:35','2020-04-29 18:39:35'),('415425551768096768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:40','2020-04-29 18:39:40'),('415425572743811072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:39:45','2020-04-29 18:39:45'),('415425593706942464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:50','2020-04-29 18:39:50'),('415425614678462464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:39:55','2020-04-29 18:39:55'),('415425635654176768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:00','2020-04-29 18:40:00'),('415425656629891072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:05','2020-04-29 18:40:05'),('415425677597216768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:10','2020-04-29 18:40:10'),('415425698568736768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:15','2020-04-29 18:40:15'),('415425719540256768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:20','2020-04-29 18:40:20'),('415425740515971072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:25','2020-04-29 18:40:25'),('415425761487491072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:30','2020-04-29 18:40:30'),('415425782454816768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:35','2020-04-29 18:40:35'),('415425803430531072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:40','2020-04-29 18:40:40'),('415425824397856768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:45','2020-04-29 18:40:45'),('415425845373571072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:40:50','2020-04-29 18:40:50'),('415425866340896768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:40:55','2020-04-29 18:40:55'),('415425887312416768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:00','2020-04-29 18:41:00'),('415425908283936768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:05','2020-04-29 18:41:05'),('415425929255456768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:41:10','2020-04-29 18:41:10'),('415425950226976768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:15','2020-04-29 18:41:15'),('415425971198496768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:20','2020-04-29 18:41:20'),('415425992165822464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:25','2020-04-29 18:41:25'),('415426013141536768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:30','2020-04-29 18:41:30'),('415426034117251072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:41:35','2020-04-29 18:41:35'),('415426055088771072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:40','2020-04-29 18:41:40'),('415426076056096768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:41:45','2020-04-29 18:41:45'),('415426117479043072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：4毫秒',0,NULL,'2020-04-29 18:41:51','2020-04-29 18:41:51'),('415426138983239680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:42:00','2020-04-29 18:42:00'),('415426180909502464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:42:10','2020-04-29 18:42:10'),('415426222856736768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:42:20','2020-04-29 18:42:20'),('415426264799776768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:42:30','2020-04-29 18:42:30'),('415426306742816768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:42:40','2020-04-29 18:42:40'),('415426348685856768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:42:50','2020-04-29 18:42:50'),('415426390633091072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:43:00','2020-04-29 18:43:00'),('415426432580325376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:43:10','2020-04-29 18:43:10'),('415426453539262464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:43:15','2020-04-29 18:43:15'),('415426474519171072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-04-29 18:43:20','2020-04-29 18:43:20'),('415426495486496768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:43:25','2020-04-29 18:43:25'),('415426516462211072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:43:30','2020-04-29 18:43:30'),('415426537437925376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:43:35','2020-04-29 18:43:35'),('415426558417833984','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:43:40','2020-04-29 18:43:40'),('415426579372576768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:43:45','2020-04-29 18:43:45'),('415426600344096768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:43:50','2020-04-29 18:43:50'),('415426789146497024','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',0,NULL,'2020-04-29 18:44:35','2020-04-29 18:44:35'),('415426810071879680','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:44:40','2020-04-29 18:44:40'),('415426831039205376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:44:45','2020-04-29 18:44:45'),('415426851998142464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:44:50','2020-04-29 18:44:50'),('415426872982245376','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:44:55','2020-04-29 18:44:55'),('415426894041845760','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:45:00','2020-04-29 18:45:00'),('415426914921091072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:45:05','2020-04-29 18:45:05'),('415426935888416768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:45:10','2020-04-29 18:45:10'),('415426956864131072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:45:15','2020-04-29 18:45:15'),('415426977835651072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-04-29 18:45:20','2020-04-29 18:45:20'),('415427019774496768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:45:30','2020-04-29 18:45:30'),('415427061713342464','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:45:40','2020-04-29 18:45:40'),('415427103660576768','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：0毫秒',0,NULL,'2020-04-29 18:45:50','2020-04-29 18:45:50'),('503199187412848640','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：45毫秒',0,NULL,'2020-12-27 15:40:45','2020-12-27 15:40:45'),('503200830422388736','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：37毫秒',0,NULL,'2020-12-27 15:47:17','2020-12-27 15:47:17'),('503205708326637568','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：148毫秒',0,NULL,'2020-12-27 16:06:40','2020-12-27 16:06:40'),('503205749506314240','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：10毫秒',0,NULL,'2020-12-27 16:06:50','2020-12-27 16:06:50'),('503205791440965632','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：5毫秒',0,NULL,'2020-12-27 16:07:00','2020-12-27 16:07:00'),('503205917249114112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：7毫秒',0,NULL,'2020-12-27 16:07:30','2020-12-27 16:07:30'),('503205959217319936','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：8毫秒',0,NULL,'2020-12-27 16:07:40','2020-12-27 16:07:40'),('503218856400130048','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：39毫秒',0,NULL,'2020-12-27 16:58:55','2020-12-27 16:58:55'),('503222358929182720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-12-27 17:12:50','2020-12-27 17:12:50'),('503222379850371072','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：1毫秒',0,NULL,'2020-12-27 17:12:55','2020-12-27 17:12:55'),('503222442806874112','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',0,NULL,'2020-12-27 17:13:10','2020-12-27 17:13:10'),('503222463786782720','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：2毫秒',0,NULL,'2020-12-27 17:13:15','2020-12-27 17:13:15'),('503222842696011776','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：5毫秒',0,NULL,'2020-12-27 17:14:45','2020-12-27 17:14:45'),('503222862266634240','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：4毫秒',0,NULL,'2020-12-27 17:14:50','2020-12-27 17:14:50'),('503222883204599808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',0,NULL,'2020-12-27 17:14:55','2020-12-27 17:14:55'),('503222904176119808','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：3毫秒',1,'ewrwerwer','2020-12-27 17:15:00','2020-12-30 17:15:00'),('503236547471085568','v2Task2','SYSTEM','v2Task.runTask2(1,2l,\'asa\',true,2D)','v2Task2 总共耗时：37毫秒',0,NULL,'2020-12-27 18:09:13','2020-12-27 18:09:13');
/*!40000 ALTER TABLE `t_sys_quartz_job_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 18:48:15
