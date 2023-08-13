/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : fastproject

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 09/08/2023 22:05:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cfg_env
-- ----------------------------
DROP TABLE IF EXISTS `cfg_env`;
CREATE TABLE `cfg_env`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cfg_env
-- ----------------------------
INSERT INTO `cfg_env` VALUES (1, '是否开启多级审批', 'MULTI_LEVEL_AUDIT_ENABLE', '0', '设为1时开启多级审批，为0时关闭', NULL, '2023-07-16 00:10:19', NULL, 1);

-- ----------------------------
-- Table structure for def_audit
-- ----------------------------
DROP TABLE IF EXISTS `def_audit`;
CREATE TABLE `def_audit`  (
  `id` bigint(0) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批类型',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批状态',
  `entity_id` bigint(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '审批内容',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for def_customer
-- ----------------------------
DROP TABLE IF EXISTS `def_customer`;
CREATE TABLE `def_customer`  (
  `id` bigint(0) NOT NULL,
  `customer_id` bigint(0) NOT NULL,
  `field_id` bigint(0) NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_customer
-- ----------------------------
INSERT INTO `def_customer` VALUES (1688562494822154241, 848528251625279489, 1, '上海仪能');
INSERT INTO `def_customer` VALUES (1688562494901846017, 848528251625279489, 1672263140513890306, '浙江南泵流体机械有限公司');
INSERT INTO `def_customer` VALUES (1688562494956371970, 848528251625279489, 1672265251121217537, 'B类');
INSERT INTO `def_customer` VALUES (1688562494956371971, 848528251625279489, 1672265317521244162, '配套生产厂家');
INSERT INTO `def_customer` VALUES (1688562495027675138, 848528251625279489, 1672265362920390658, '2');
INSERT INTO `def_customer` VALUES (1688562495098978306, 848528251625279489, 1672265408768327681, '818461880107536384');
INSERT INTO `def_customer` VALUES (1688562495157698561, 848528251625279489, 1672265641535422465, '销售A组');
INSERT INTO `def_customer` VALUES (1688562495157698562, 848528251625279489, 1672265697797816322, '风机');
INSERT INTO `def_customer` VALUES (1688562495283527682, 848528251625279489, 1672265747873611778, '李总');
INSERT INTO `def_customer` VALUES (1688562495283527683, 848528251625279489, 1672265788076015617, '021-57735749');
INSERT INTO `def_customer` VALUES (1688562495346442241, 848528251625279489, 1672265838390886402, '长期协议规定的订单');
INSERT INTO `def_customer` VALUES (1688562495346442242, 848528251625279489, 1672265909857632258, '湖州生产基地免费送货');
INSERT INTO `def_customer` VALUES (1688562495413551106, 848528251625279489, 1672265958779994113, '是');
INSERT INTO `def_customer` VALUES (1688562495413551107, 848528251625279489, 1672555577528221697, '当月20号前');
INSERT INTO `def_customer` VALUES (1688562495476465666, 848528251625279489, 1672555875755819009, '备注要求填写客户订单号');
INSERT INTO `def_customer` VALUES (1688562495476465667, 848528251625279489, 1672559602348777473, '0.41');
INSERT INTO `def_customer` VALUES (1688562495539380225, 848528251625279489, 1675456723376939009, '0.41');
INSERT INTO `def_customer` VALUES (1688562495610683394, 848528251625279489, 1685686372972564481, '次月结清');
INSERT INTO `def_customer` VALUES (1688562495610683395, 848528251625279489, 1685686485488963585, '全合格承兑');
INSERT INTO `def_customer` VALUES (1688562495665209345, 848528251625279489, 1685686528375721986, '0.86');
INSERT INTO `def_customer` VALUES (1688562495740706818, 848528251625279489, 1685686562739654657, '0.86');
INSERT INTO `def_customer` VALUES (1688562495740706819, 848528251625279489, 1685686600668745729, '次月结清');
INSERT INTO `def_customer` VALUES (1688562495795232769, 848528251625279489, 1685686640640462850, '全合格承兑');
INSERT INTO `def_customer` VALUES (1688562495866535937, 848528251625279489, 1685686695338381313, '0');

-- ----------------------------
-- Table structure for def_department
-- ----------------------------
DROP TABLE IF EXISTS `def_department`;
CREATE TABLE `def_department`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_department
-- ----------------------------
INSERT INTO `def_department` VALUES ('1', '0', '上海仪能', '', '13012345678', 'v2@qq.com', 1, 1);
INSERT INTO `def_department` VALUES ('2', '1', '技术部门', 'x某某', '13012345678', 'v2@qq.com', 1, 2);
INSERT INTO `def_department` VALUES ('3', '1', '人事部门', 'a某某', '13012345678', 'v2@qq.com', 1, 3);
INSERT INTO `def_department` VALUES ('4', '2', '开发一小组', 'b某某', '13012345678', 'v2@qq.com', 1, 4);
INSERT INTO `def_department` VALUES ('5', '3', '销售部门', 'd某某', '13012345678', 'v2@qq.com', 1, 5);
INSERT INTO `def_department` VALUES ('6', '5', '销售一组', 'e某某', '13012345678', 'v2@qq.com', 1, 6);

-- ----------------------------
-- Table structure for def_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `def_dict_data`;
CREATE TABLE `def_dict_data`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '字典排序',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `is_user` tinyint(0) NULL DEFAULT NULL,
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表格回显样式',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_dict_data
-- ----------------------------
INSERT INTO `def_dict_data` VALUES ('1', 0, '男', '1', 'gender', 0, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('1688971059622629378', 0, '管理员', 't53', 'user', 1, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('1688971059685543938', 0, '陈志龙', '125', 'user', 1, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('1688971059685543939', 0, '陈小利', '23', 'user', 1, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('1688971059685543940', 0, '销售部总经理', '13456', 'user', 1, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('2', 0, '女', '0', 'gender', 0, '', '', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('563746747239763968', 0, '微信', '1', 'payment_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:35:09', 'admin', '2021-06-12 17:37:14', '');
INSERT INTO `def_dict_data` VALUES ('563746783184949248', 0, '支付宝', '2', 'payment_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:35:17', 'admin', '2021-06-12 17:37:18', '');
INSERT INTO `def_dict_data` VALUES ('563746818496794624', 0, '水滴筹', '3', 'payment_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:35:26', 'admin', '2021-06-12 17:37:21', '');
INSERT INTO `def_dict_data` VALUES ('563747125104611328', 0, '火锅底料', '1', 'gift_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:36:39', 'admin', '2021-06-12 17:36:50', '');
INSERT INTO `def_dict_data` VALUES ('563747405598691328', 0, '冒菜底料', '2', 'gift_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:37:46', '', '2021-06-12 17:37:46', '');
INSERT INTO `def_dict_data` VALUES ('563747459235450880', 0, '重庆小面佐料', '3', 'gift_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:37:58', '', '2021-06-12 17:37:58', '');
INSERT INTO `def_dict_data` VALUES ('563747480336994304', 0, '其他', '4', 'gift_type', 0, '', 'default', '1', 'admin', '2021-06-12 17:38:03', '', '2021-06-12 17:38:03', '');
INSERT INTO `def_dict_data` VALUES ('571366029360500736', 0, '是', 'true', 'true_or_false', 0, '', 'default', '1', 'admin', '2021-07-03 18:11:27', '', '2021-07-03 18:11:27', '');
INSERT INTO `def_dict_data` VALUES ('571366105029939200', 0, '否', 'false', 'true_or_false', 0, '', 'info', '1', 'admin', '2021-07-03 18:11:45', '', '2021-07-03 18:11:45', '');
INSERT INTO `def_dict_data` VALUES ('832243915166978048', 0, 'A类', 'A类', 'customer_category', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('832243981541838848', 0, 'B类', 'B类', 'customer_category', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('832244036281700352', 0, 'C类', 'C类', 'customer_category', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('834056824620716032', 0, '销售商务', '48', 'position', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('834056891381452800', 0, '销售员', '52', 'position', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('834056968737001472', 0, '销售部经理', '834055282635182080', 'position', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835368820335054848', 0, '添加客户', 'ADD_CUSTOMER', 'audit_type', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835369266952933376', 0, '修改客户信息', 'UPDATE_CUSTOMER', 'audit_type', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835369319293652992', 0, '删除客户', 'DELETE_CUSTOMER', 'audit_type', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835369863416516608', 0, '等待审批', 'WAITING', 'audit_status', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835370099262230528', 0, '通过', 'APPROVED', 'audit_status', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835370139431079936', 0, '拒绝', 'REJECTION', 'audit_status', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('835435973339910144', 0, 'D类', 'D', 'customer_category', 0, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('838393306353373184', 0, '陈志龙', '125', '23', 1, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('838393437769306112', 0, 'admin', 't53', '23', 1, '', 'default', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_data` VALUES ('848911095379922944', 0, '等待二级审批', 'WAITING_SECOND', 'audit_status', 0, '', '', '0', '', NULL, '', NULL, '');

-- ----------------------------
-- Table structure for def_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `def_dict_type`;
CREATE TABLE `def_dict_type`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `hidden` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否隐藏（0正常 1隐藏）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_dict_type
-- ----------------------------
INSERT INTO `def_dict_type` VALUES ('1', '性别', 'gender', '1', '', NULL, '', NULL, '勿修改');
INSERT INTO `def_dict_type` VALUES ('830760469865304064', '是否', 'true_or_false', '1', '', NULL, '', NULL, '勿修改');
INSERT INTO `def_dict_type` VALUES ('832243600803893248', '客户定档', 'customer_category', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_type` VALUES ('834053719778791424', '所有用户', 'user', '1', '', NULL, '', NULL, '勿修改，系统中所有用户');
INSERT INTO `def_dict_type` VALUES ('834056532583911424', '岗位', 'position', '1', '', NULL, '', NULL, '勿修改，系统中所有的岗位');
INSERT INTO `def_dict_type` VALUES ('835368675186970624', '审批类型', 'audit_type', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_type` VALUES ('835369578090598400', '审批状态', 'audit_status', '1', '', NULL, '', NULL, '');
INSERT INTO `def_dict_type` VALUES ('838384615302500352', '陈小利的上级销售经理', '23', '1', '', NULL, '', NULL, '字典编码是销售商务的工号');
INSERT INTO `def_dict_type` VALUES ('838394244040364032', '所属销售', 'sales_manager', '1', '', NULL, '', NULL, '勿修改字典编码');

-- ----------------------------
-- Table structure for def_notice
-- ----------------------------
DROP TABLE IF EXISTS `def_notice`;
CREATE TABLE `def_notice`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型',
  `create_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发信时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_notice
-- ----------------------------
INSERT INTO `def_notice` VALUES ('330381411007729664', '测试公告', '<p>啊啊啊<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>', 1, '1', 'admin', '2019-09-08 02:24:37');
INSERT INTO `def_notice` VALUES ('330381806358630400', '鲜花视频', '<p>哈哈哈哈<img src=\"http://img.baidu.com/hi/jx2/j_0024.gif\"/></p>', 2, '1', 'admin', '2019-09-08 02:26:11');
INSERT INTO `def_notice` VALUES ('373478036928073728', '顶顶顶顶顶顶顶顶顶', '<p>顶顶顶顶顶顶顶顶顶顶<img src=\"http://img.baidu.com/hi/jx2/j_0014.gif\"/></p>', 1, '1', 'admin', '2020-01-05 00:35:13');

-- ----------------------------
-- Table structure for def_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `def_operation_log`;
CREATE TABLE `def_operation_log`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数',
  `error_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_operation_log
-- ----------------------------
INSERT INTO `def_operation_log` VALUES ('1687869179093901313', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', NULL, NULL, '2023-08-06 00:52:26', 112);
INSERT INTO `def_operation_log` VALUES ('1687869581008842753', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T00:53:59.6231597\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265641535422465\",\"fieldKey\":null,\"fieldName\":\"所属销售组\",\"type\":\"INPUT\",\"required\":false,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 00:54:02', 2244);
INSERT INTO `def_operation_log` VALUES ('1688111208428273665', '导入客户', 'com.fastproject.controller.admin.CustomerController.upload()', '1', '/CustomerController/upload', '', NULL, '2023-08-06 16:54:10', 2244);
INSERT INTO `def_operation_log` VALUES ('1688111400632254466', '导入客户', 'com.fastproject.controller.admin.CustomerController.upload()', '1', '/CustomerController/upload', '', NULL, '2023-08-06 16:54:56', 23397);
INSERT INTO `def_operation_log` VALUES ('1688118808549109761', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:24:22.2102336\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265317521244162\",\"fieldKey\":null,\"fieldName\":\"客户类型\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:24:22', 138);
INSERT INTO `def_operation_log` VALUES ('1688118874806530049', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:24:38.0695831\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265641535422465\",\"fieldKey\":null,\"fieldName\":\"所属销售组\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:24:38', 82);
INSERT INTO `def_operation_log` VALUES ('1688118907903782914', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:24:45.9517573\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265788076015617\",\"fieldKey\":null,\"fieldName\":\"采购联系人电话\",\"type\":\"INPUT\",\"required\":false,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:24:46', 89);
INSERT INTO `def_operation_log` VALUES ('1688118940497719297', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:24:53.7379697\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265788076015617\",\"fieldKey\":null,\"fieldName\":\"采购联系人电话\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:24:54', 81);
INSERT INTO `def_operation_log` VALUES ('1688118965621600258', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:24:59.717601\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265838390886402\",\"fieldKey\":null,\"fieldName\":\"合同签订方式\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:25:00', 82);
INSERT INTO `def_operation_log` VALUES ('1688119015919693825', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:25:11.725515\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672555875755819009\",\"fieldKey\":null,\"fieldName\":\"开票特殊要求\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:25:12', 75);
INSERT INTO `def_operation_log` VALUES ('1688119055161602049', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:25:21.0556894\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672265909857632258\",\"fieldKey\":null,\"fieldName\":\"物流方案\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:25:21', 88);
INSERT INTO `def_operation_log` VALUES ('1688119084207157250', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:25:27.9861645\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672555577528221697\",\"fieldKey\":null,\"fieldName\":\"开票时间要求\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:25:28', 80);
INSERT INTO `def_operation_log` VALUES ('1688119111595962369', '编辑模板属性', 'com.fastproject.controller.admin.TemplateController.editSave()', '1', '/TemplateController/edit', '{\"createAt\":null,\"updateAt\":\"2023-08-06T17:25:34.52687\",\"createBy\":null,\"updateBy\":\"1\",\"id\":\"1672559602348777473\",\"fieldKey\":null,\"fieldName\":\"西门子电机定价（表价折扣率）\",\"type\":\"INPUT\",\"required\":true,\"dictTypeCode\":\"\"}', NULL, '2023-08-06 17:25:35', 85);
INSERT INTO `def_operation_log` VALUES ('1688119193393278977', '添加模板属性', 'com.fastproject.controller.admin.TemplateController.add()', '1', '/TemplateController/add', '{\"createAt\":\"2023-08-06T17:25:54.037202\",\"updateAt\":\"2023-08-06T17:25:54.037202\",\"createBy\":\"1\",\"updateBy\":\"1\",\"id\":\"1688119193066123266\",\"fieldKey\":null,\"fieldName\":\"11\",\"type\":\"SELECT\",\"required\":true,\"dictTypeCode\":\"gender\"}', NULL, '2023-08-06 17:25:54', 86);
INSERT INTO `def_operation_log` VALUES ('1688119265325592577', '删除模板属性', 'com.fastproject.controller.admin.TemplateController.remove()', '1', '/TemplateController/remove', '{\"ids\":[\"1688119193066123266\"]}', NULL, '2023-08-06 17:26:11', 109);
INSERT INTO `def_operation_log` VALUES ('1688120508307582977', '导入客户', 'com.fastproject.controller.admin.CustomerController.upload()', '1', '/CustomerController/upload', '', NULL, '2023-08-06 17:31:08', 33669);
INSERT INTO `def_operation_log` VALUES ('1688120691653193730', '导入客户', 'com.fastproject.controller.admin.CustomerController.upload()', '1', '/CustomerController/upload', '', NULL, '2023-08-06 17:31:51', 734);
INSERT INTO `def_operation_log` VALUES ('1688549346455076865', '导入客户', 'com.fastproject.controller.admin.CustomerController.upload()', '1', '/CustomerController/upload', '', NULL, '2023-08-07 21:55:11', 1844);
INSERT INTO `def_operation_log` VALUES ('1688562496835420162', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '828242314345451520', '/AuditController/approve', '{\"auditId\":[\"848528251625279488\"]}', NULL, '2023-08-07 22:47:26', 549);
INSERT INTO `def_operation_log` VALUES ('1688562572215451649', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '828242314345451520', '/AuditController/approve', '{\"auditId\":[\"848528252241842176\"]}', NULL, '2023-08-07 22:47:44', 346);
INSERT INTO `def_operation_log` VALUES ('1688930482461929474', '新增用户', 'com.fastproject.controller.admin.UserController.add()', '1', '/UserController/add', '{\"realName\":[\"测试用户\"],\"username\":[\"tt\"],\"password\":[\"tt\"],\"employeeId\":[\"tt\"],\"phone\":[\"\"],\"email\":[\"\"],\"gender\":[\"1\"],\"deptId\":[\"\"],\"selectParent_select_input\":[\"\"],\"posId\":[\"\"],\"roleIds\":[\"4\"]}', NULL, '2023-08-08 23:09:40', 333);
INSERT INTO `def_operation_log` VALUES ('1688930548413165569', '新增用户', 'com.fastproject.controller.admin.UserController.add()', '1', '/UserController/add', '{\"realName\":[\"test2\"],\"username\":[\"test2\"],\"password\":[\"test2\"],\"employeeId\":[\"test2\"],\"phone\":[\"\"],\"email\":[\"\"],\"gender\":[\"0\"],\"deptId\":[\"\"],\"selectParent_select_input\":[\"\"],\"posId\":[\"\"],\"roleIds\":[\"\"]}', NULL, '2023-08-08 23:09:56', 181);
INSERT INTO `def_operation_log` VALUES ('1688931028023439361', '新增客户', 'com.fastproject.controller.admin.CustomerController.add()', '1', '/CustomerController/add', '{\"1\":\"合作抬头\",\"1672263140513890306\":\"客户名称\",\"1672265251121217537\":\"D\",\"1672265317521244162\":\"客户类型\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"848909387706798080\",\"1672265641535422465\":\"所属销售组\",\"1672265697797816322\":\"客户所在行业\",\"1672265747873611778\":\"采购联系人姓名\",\"1672265788076015617\":\"采购联系人电话\",\"1672265838390886402\":\"合同签订方式\",\"1672265909857632258\":\"物流方案\",\"1672265958779994113\":\"出库单是否带价格\",\"1672555577528221697\":\"开票时间要求\",\"1672555875755819009\":\"开票特殊要求\",\"1672559602348777473\":\"西门子电机定价（表价折扣率\",\"1675456723376939009\":\"需评审西门子电机定价（表价折扣\",\"1685686372972564481\":\"西门子电机付款时\",\"1685686485488963585\":\"西门子电机付款\",\"1685686528375721986\":\"得电机定价（表价\",\"1685686562739654657\":\"需评审贝得电机定价（表价\",\"1685686600668745729\":\"需评审贝得电机定价（表价\",\"1685686640640462850\":\"得电机付款形式\",\"1685686695338381313\":\"固定垫资，需垫\",\"description\":\"glu\"}', NULL, '2023-08-08 23:11:51', 334);
INSERT INTO `def_operation_log` VALUES ('1688931265710452737', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848909933079564288\"]}', NULL, '2023-08-08 23:12:47', 2275);
INSERT INTO `def_operation_log` VALUES ('1688941509169655810', '审批拒绝', 'com.fastproject.controller.admin.AuditController.reject()', '1', '/AuditController/reject', '{\"auditId\":[\"848909933079564288\"]}', NULL, '2023-08-08 23:53:29', 2345);
INSERT INTO `def_operation_log` VALUES ('1688941621023354882', '审批拒绝', 'com.fastproject.controller.admin.AuditController.reject()', '1', '/AuditController/reject', '{\"auditId\":[\"848909933079564288\"]}', NULL, '2023-08-08 23:53:56', 12);
INSERT INTO `def_operation_log` VALUES ('1688942205587697666', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼1\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"818461880107536384\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528252241842177\"', NULL, '2023-08-08 23:56:16', 203);
INSERT INTO `def_operation_log` VALUES ('1688942379106054146', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848921111180742656\"]}', NULL, '2023-08-08 23:56:57', 327);
INSERT INTO `def_operation_log` VALUES ('1688952288111534082', '审批拒绝', 'com.fastproject.controller.admin.AuditController.reject()', '828242314345451520', '/AuditController/reject', '{\"auditId\":[\"848921111180742656\"]}', NULL, '2023-08-09 00:36:19', 2322);
INSERT INTO `def_operation_log` VALUES ('1688963571934388226', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '818461880107536384', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"2\",\"1672265408768327681\":\"2\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528252241842177\"', NULL, '2023-08-09 01:21:10', 353);
INSERT INTO `def_operation_log` VALUES ('1688963721650069505', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '2', '/AuditController/approve', '{\"auditId\":[\"848942476902404096\"]}', NULL, '2023-08-09 01:21:45', 320);
INSERT INTO `def_operation_log` VALUES ('1688963848246747137', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '828242314345451520', '/AuditController/approve', '{\"auditId\":[\"848942476902404096\"]}', NULL, '2023-08-09 01:22:16', 188);
INSERT INTO `def_operation_log` VALUES ('1688965685020479489', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"1\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528252241842177\"', NULL, '2023-08-09 01:29:33', 290);
INSERT INTO `def_operation_log` VALUES ('1688965842386571265', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '828242314345451520', '/AuditController/approve', '{\"auditId\":[\"848944590223446016\"]}', NULL, '2023-08-09 01:30:11', 216);
INSERT INTO `def_operation_log` VALUES ('1688970441617100801', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"1\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"1\"} \"848528252241842177\"', NULL, '2023-08-09 01:48:27', 197);
INSERT INTO `def_operation_log` VALUES ('1688970676909166593', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '828242314345451520', '/AuditController/approve', '{\"auditId\":[\"848949347205910528\"]}', NULL, '2023-08-09 01:49:24', 261);
INSERT INTO `def_operation_log` VALUES ('1688971167357521921', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"1\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528252241842177\"', NULL, '2023-08-09 01:51:21', 139);
INSERT INTO `def_operation_log` VALUES ('1688971169588891650', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"1\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528252241842177\"', NULL, '2023-08-09 01:51:21', 251);
INSERT INTO `def_operation_log` VALUES ('1688971359431479298', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848950074967986176\"]}', NULL, '2023-08-09 01:52:06', 19560);
INSERT INTO `def_operation_log` VALUES ('1688972949739589634', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848950073185406976\"]}', NULL, '2023-08-09 01:58:25', 364462);
INSERT INTO `def_operation_log` VALUES ('1688973228677603329', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528252241842177', '{\"1\":\"浙江易铼\",\"1672263140513890306\":\"浙江南方泵业有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"1\",\"1672265408768327681\":\"818461880107536384\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735750\",\"1672265838390886402\":\"已备案甲方提供标准合同\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"fff\"} \"848528252241842177\"', NULL, '2023-08-09 01:59:32', 2309);
INSERT INTO `def_operation_log` VALUES ('1688973367953661954', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848952125391245312\"]}', NULL, '2023-08-09 02:00:05', 18019);
INSERT INTO `def_operation_log` VALUES ('1688973782153764865', '删除客户', 'com.fastproject.controller.admin.CustomerController.remove()', '1', '/CustomerController/remove', '{\"ids\":[\"848528252241842177\"],\"description\":[\"ff\"]}', NULL, '2023-08-09 02:01:44', 9423);
INSERT INTO `def_operation_log` VALUES ('1688973865490391042', '审批通过', 'com.fastproject.controller.admin.AuditController.approve()', '1', '/AuditController/approve', '{\"auditId\":[\"848952677999185920\"]}', NULL, '2023-08-09 02:02:04', 290);
INSERT INTO `def_operation_log` VALUES ('1688973954703237122', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528251625279489', '{\"1\":\"上海仪能 \",\"1672263140513890306\":\"浙江南泵流体机械有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"2\",\"1672265408768327681\":\"818461880107536384\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735749\",\"1672265838390886402\":\"长期协议规定的订单\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"qqqq\"} \"848528251625279489\"', NULL, '2023-08-09 02:02:25', 117);
INSERT INTO `def_operation_log` VALUES ('1688974047242166274', '审批拒绝', 'com.fastproject.controller.admin.AuditController.reject()', '1', '/AuditController/reject', '{\"auditId\":[\"848952860610793472\"]}', NULL, '2023-08-09 02:02:47', 175);
INSERT INTO `def_operation_log` VALUES ('1688974216704630785', '编辑客户', 'com.fastproject.controller.admin.CustomerController.editSave()', '1', '/CustomerController/edit/848528251625279489', '{\"1\":\"上海仪能1\",\"1672263140513890306\":\"浙江南泵流体机械有限公司\",\"1672265251121217537\":\"B类\",\"1672265317521244162\":\"配套生产厂家\",\"1672265362920390658\":\"2\",\"1672265408768327681\":\"818461880107536384\",\"1672265641535422465\":\"销售A组\",\"1672265697797816322\":\"风机\",\"1672265747873611778\":\"李总\",\"1672265788076015617\":\"021-57735749\",\"1672265838390886402\":\"长期协议规定的订单\",\"1672265909857632258\":\"湖州生产基地免费送货\",\"1672265958779994113\":\"是\",\"1672555577528221697\":\"当月20号前\",\"1672555875755819009\":\"备注要求填写客户订单号\",\"1672559602348777473\":\"0.41\",\"1675456723376939009\":\"0.41\",\"1685686372972564481\":\"次月结清\",\"1685686485488963585\":\"全合格承兑\",\"1685686528375721986\":\"0.86\",\"1685686562739654657\":\"0.86\",\"1685686600668745729\":\"次月结清\",\"1685686640640462850\":\"全合格承兑\",\"1685686695338381313\":\"0\",\"description\":\"11\"} \"848528251625279489\"', NULL, '2023-08-09 02:03:28', 187);
INSERT INTO `def_operation_log` VALUES ('1688974258958049281', '审批拒绝', 'com.fastproject.controller.admin.AuditController.reject()', '1', '/AuditController/reject', '{\"auditId\":[\"848953122326974464\"]}', NULL, '2023-08-09 02:03:38', 155);

-- ----------------------------
-- Table structure for def_permission
-- ----------------------------
DROP TABLE IF EXISTS `def_permission`;
CREATE TABLE `def_permission`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权链接',
  `is_blank` int(0) NULL DEFAULT 0 COMMENT '是否跳转 0 不跳转 1跳转',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `code` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` int(0) NULL DEFAULT NULL COMMENT '是否可见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_permission
-- ----------------------------
INSERT INTO `def_permission` VALUES (4, '用户管理', '用户展示', '/UserController/view', 0, '411522822607867904', 'system:user:view', 1, 'icon icon-user', 1, 1);
INSERT INTO `def_permission` VALUES (5, '用户集合', '用户集合', '/UserController/list', 0, '4', 'system:user:list', 2, '', NULL, 1);
INSERT INTO `def_permission` VALUES (6, '用户添加', '用户添加', '/UserController/add', 0, '4', 'system:user:add', 2, 'entypo-plus-squared', NULL, 1);
INSERT INTO `def_permission` VALUES (7, '用户删除', '用户删除', '/UserController/remove', 0, '4', 'system:user:remove', 2, 'entypo-trash', NULL, 1);
INSERT INTO `def_permission` VALUES (8, '用户修改', '用户修改', '/UserController/edit', 0, '4', 'system:user:edit', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (9, '角色管理', '角色展示', '/RoleController/view', 0, '411522822607867904', 'system:role:view', 1, 'fa fa-group', 2, 1);
INSERT INTO `def_permission` VALUES (10, '角色集合', '角色集合', '/RoleController/list', 0, '9', 'system:role:list', 2, '', NULL, 1);
INSERT INTO `def_permission` VALUES (11, '角色添加', '角色添加', '/RoleController/add', 0, '9', 'system:role:add', 2, 'entypo-plus-squared', NULL, 1);
INSERT INTO `def_permission` VALUES (12, '角色删除', '角色删除', '/RoleController/remove', 0, '9', 'system:role:remove', 2, 'entypo-trash', NULL, 1);
INSERT INTO `def_permission` VALUES (13, '角色修改', '角色修改', '/RoleController/edit', 0, '9', 'system:role:edit', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (14, '权限展示', '权限展示', '/PermissionController/view', 0, '411522822607867904', 'system:permission:view', 1, 'fa fa-key', 3, 1);
INSERT INTO `def_permission` VALUES (15, '权限集合', '权限集合', '/PermissionController/list', 0, '14', 'system:permission:list', 2, '', NULL, 1);
INSERT INTO `def_permission` VALUES (16, '权限添加', '权限添加', '/permissionController/add', 0, '14', 'system:permission:add', 2, 'entypo-plus-squared', NULL, 1);
INSERT INTO `def_permission` VALUES (17, '权限删除', '权限删除', '/PermissionController/remove', 0, '14', 'system:permission:remove', 2, 'entypo-trash', NULL, 1);
INSERT INTO `def_permission` VALUES (18, '权限修改', '权限修改', '/PermissionController/edit', 0, '14', 'system:permission:edit', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (410791701859405824, '岗位管理', '岗位展示', '/PositionController/view', 0, '411522822607867904', 'system:position:view', 1, 'fa fa-vcard', 17, 1);
INSERT INTO `def_permission` VALUES (410989805699207168, '部门管理', '部门展示', '/DepartmentController/view', 0, '411522822607867904', 'system:department:view', 1, 'fa fa-odnoklassniki', 18, 1);
INSERT INTO `def_permission` VALUES (411522822607867904, '用户管理', NULL, '', 0, '0', '', 0, 'layui-icon layui-icon-user', 3, 1);
INSERT INTO `def_permission` VALUES (486690002869157888, '用户密码修改', '用户密码修改', '/UserController/editPwd', 0, '4', 'system:user:editPwd', 2, 'entypo-tools', 3, 1);
INSERT INTO `def_permission` VALUES (496126970468237312, '日志展示', '日志管理', '/LogController/view', 0, '592059865673760768', 'system:log:view', 1, 'fa fa-info', 9, 1);
INSERT INTO `def_permission` VALUES (496127240363311104, '日志删除', '日志删除', '/LogController/remove', 0, '496126970468237312', 'system:log:remove', 2, 'entypo-trash', NULL, 1);
INSERT INTO `def_permission` VALUES (496127794879660032, '日志集合', '日志集合', '/LogController/list', 0, '496126970468237312', 'system:log:list', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (592059865673760768, '系统管理', NULL, '', 0, '0', '', 0, 'layui-icon layui-icon layui-icon layui-icon-home', 1, 1);
INSERT INTO `def_permission` VALUES (618918631769636864, '字典管理', '字典类型表展示', '/DictTypeController/view', 0, '592059865673760768', 'system:dictType:view', 1, 'fa fa-puzzle-piece', 11, 1);
INSERT INTO `def_permission` VALUES (619836559427895296, '字典数据视图', '字典数据视图', '/DictDataController/view', 0, '618918631769636864', 'system:dictData:view', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (830399865111580672, '客户信息模板', NULL, '/TemplateController/view', 0, '592059865673760768', 'system:template:view', 1, 'layui-icon layui-icon layui-icon layui-icon layui-icon-layouts', NULL, 1);
INSERT INTO `def_permission` VALUES (830435502187483136, '模板视图', NULL, '/TemplateController/view', 0, '830399865111580672', 'system:template:list', 2, 'layui-icon layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (830435926206451712, '模板集合', NULL, '/TemplateController/list', 0, '830399865111580672', '', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (830773701376086016, '模板编辑', NULL, '/TemplateController/edit', 0, '830399865111580672', 'system:template:edit', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (830773987662499840, '模板删除', NULL, '/TemplateController/remove', 0, '830399865111580672', 'system:template:remove', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (830773987662499841, '模板添加', NULL, '/TemplateController/add', 0, '830399865111580672', 'system:template:add', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (831828755528945664, '客户管理', NULL, '', 0, '0', '', 0, 'layui-icon layui-icon-face-surprised', 1, 1);
INSERT INTO `def_permission` VALUES (831828755528945666, '客户列表', NULL, '/CustomerController/list', 0, '831828755528945667', 'system:customer:list', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (831828755528945667, '客户信息', NULL, '/CustomerController/view', 0, '831828755528945664', 'system:customer:view', 1, 'layui-icon ', 1, 1);
INSERT INTO `def_permission` VALUES (831828755528945668, '客户新增', NULL, '/CustomerController/add', 0, '831828755528945667', 'system:customer:add', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (831828755528945669, '客户编辑', NULL, '/CustomerController/edit', 0, '831828755528945667', 'system:customer:edit', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (831828755528945670, '客户删除', NULL, '/CustomerController/remove', 0, '831828755528945667', 'system:customer:remove', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (832575092578979840, '审批管理', NULL, '', 0, '0', '', 0, 'layui-icon layui-icon-form', NULL, 1);
INSERT INTO `def_permission` VALUES (832576319278682113, '我审批的', NULL, '/AuditController/view', 0, '832575092578979840', 'system:audit:view', 1, 'layui-icon layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (832576319278682114, '我发起的', NULL, '/AuditController/apply-view', 0, '832575092578979840', 'system:apply:view', 1, 'layui-icon layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (840229919358324736, '系统设置', NULL, '/EnvConfigController/view', 0, '592059865673760768', 'system:env:view', 1, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (844924903781175296, '批量导入客户信息', NULL, '', 0, '831828755528945667', 'system:customer:upload', 2, 'layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (848543977035665408, '批量导出', NULL, '', 0, '831828755528945667', 'system:customer:export', 2, 'layui-icon layui-icon ', NULL, 1);
INSERT INTO `def_permission` VALUES (4107917018594058251, '岗位集合', '岗位集合', '/PositionController/list', 0, '410791701859405824', 'system:position:list', 2, 'layui-icon fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4107917018594058262, '岗位表添加', '岗位添加', '/PositionController/add', 0, '410791701859405824', 'system:position:add', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4107917018594058273, '岗位表删除', '岗位删除', '/PositionController/remove', 0, '410791701859405824', 'system:position:remove', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4107917018594058284, '岗位表修改', '岗位编辑', '/PositionController/edit', 0, '410791701859405824', 'system:position:edit', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4109898056992071691, '部门集合', '部门集合', '/DepartmentController/list', 0, '410989805699207168', 'system:department:list', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4109898056992071702, '部门添加', '部门添加', '/DepartmentController/add', 0, '410989805699207168', 'system:department:add', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4109898056992071713, '部门删除', '部门删除', '/DepartmentController/remove', 0, '410989805699207168', 'system:department:remove', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (4109898056992071724, '部门修改', '部门修改', '/DepartmentController/edit', 0, '410989805699207168', 'system:department:edit', 2, 'fa fa-wrench', NULL, 1);
INSERT INTO `def_permission` VALUES (6189186317738311681, '字典类型表集合', '字典类型表集合', '/DictTypeController/list', 0, '618918631769636864', 'system:dictType:list', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6189186317948026882, '字典类型表添加', '字典类型表添加', '/DictTypeController/add', 0, '618918631769636864', 'system:dictType:add', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6189186317948026883, '字典类型表删除', '字典类型表删除', '/DictTypeController/remove', 0, '618918631769636864', 'system:dictType:remove', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6189186317989969924, '字典类型表修改', '字典类型表修改', '/DictTypeController/edit', 0, '618918631769636864', 'system:dictType:edit', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6192095214866268161, '字典数据表添加', '字典数据表添加', '/DictDataController/add', 0, '618918631769636864', 'system:dictData:add', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6192095214866268162, '字典数据表添加', '字典数据表集合', '/DictDataController/list', 0, '618918631769636864', 'system:dictData:list', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6192095215075983363, '字典数据表修改', '字典数据表修改', '/DictDataController/edit', 0, '618918631769636864', 'system:dictData:edit', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (6192095215075983364, '字典数据表修改', '字典数据表删除', '/DictDataController/remove', 0, '618918631769636864', 'system:dictData:remove', 2, NULL, NULL, 1);
INSERT INTO `def_permission` VALUES (8402299193583247367, '修改配置', NULL, '/EnvConfigController/edit', 0, '840229919358324736', 'system:env:edit', 1, 'layui-icon ', NULL, 1);

-- ----------------------------
-- Table structure for def_position
-- ----------------------------
DROP TABLE IF EXISTS `def_position`;
CREATE TABLE `def_position`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_position
-- ----------------------------
INSERT INTO `def_position` VALUES ('48', '销售商务', 7, 1);
INSERT INTO `def_position` VALUES ('52', '销售经理', 2, 1);
INSERT INTO `def_position` VALUES ('834055282635182080', '销售部经理', 1, 1);
INSERT INTO `def_position` VALUES ('838384939610279936', '销售部总经理', 1, 1);

-- ----------------------------
-- Table structure for def_role
-- ----------------------------
DROP TABLE IF EXISTS `def_role`;
CREATE TABLE `def_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_role
-- ----------------------------
INSERT INTO `def_role` VALUES ('4', '销售商务', NULL, '1', '可以发起审批');
INSERT INTO `def_role` VALUES ('60', '管理员', 'ADMIN', '1', '拥有所有权限');
INSERT INTO `def_role` VALUES ('838382256191049728', '销售经理', NULL, '1', '可以审批');
INSERT INTO `def_role` VALUES ('848540272202747904', '总经理', NULL, '1', '');

-- ----------------------------
-- Table structure for def_template
-- ----------------------------
DROP TABLE IF EXISTS `def_template`;
CREATE TABLE `def_template`  (
  `id` bigint(0) NOT NULL,
  `field_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `required` tinyint(1) NULL DEFAULT NULL,
  `dict_type_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `read_only` tinyint(1) NULL DEFAULT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_template
-- ----------------------------
INSERT INTO `def_template` VALUES (1, '合作抬头', '合作抬头', 'INPUT', 1, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `def_template` VALUES (1672263140513890306, NULL, '客户名称', 'INPUT', 1, '', 1, NULL, NULL, NULL, NULL);
INSERT INTO `def_template` VALUES (1672265251121217537, NULL, '客户定档', 'SELECT', 1, 'customer_category', 1, NULL, NULL, NULL, NULL);
INSERT INTO `def_template` VALUES (1672265317521244162, NULL, '客户类型', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:24:22', NULL, 1);
INSERT INTO `def_template` VALUES (1672265362920390658, NULL, '所属销售', 'SELECT', 1, 'sales_manager', 1, NULL, '2023-07-16 01:32:23', NULL, 1);
INSERT INTO `def_template` VALUES (1672265408768327681, NULL, '配合商务', 'SELECT', 1, 'user', 1, NULL, '2023-08-06 00:37:33', NULL, 1);
INSERT INTO `def_template` VALUES (1672265641535422465, NULL, '所属销售组', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:24:38', NULL, 1);
INSERT INTO `def_template` VALUES (1672265697797816322, NULL, '客户所在行业', 'INPUT', 1, '', 1, NULL, '2023-08-06 00:37:54', NULL, 1);
INSERT INTO `def_template` VALUES (1672265747873611778, NULL, '采购联系人姓名', 'INPUT', 1, '', 1, NULL, '2023-08-06 00:52:26', NULL, 1);
INSERT INTO `def_template` VALUES (1672265788076015617, NULL, '采购联系人电话', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:24:54', NULL, 1);
INSERT INTO `def_template` VALUES (1672265838390886402, NULL, '合同签订方式', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:25:00', NULL, 1);
INSERT INTO `def_template` VALUES (1672265909857632258, NULL, '物流方案', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:25:21', NULL, 1);
INSERT INTO `def_template` VALUES (1672265958779994113, NULL, '出库单是否带价格', 'INPUT', 1, '', 1, NULL, '2023-08-06 00:26:24', NULL, 1);
INSERT INTO `def_template` VALUES (1672555577528221697, NULL, '开票时间要求', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:25:28', NULL, 1);
INSERT INTO `def_template` VALUES (1672555875755819009, NULL, '开票特殊要求', 'INPUT', 1, '', 1, NULL, '2023-08-06 17:25:12', NULL, 1);
INSERT INTO `def_template` VALUES (1672559602348777473, NULL, '西门子电机定价（表价折扣率）', 'INPUT', 1, '', 1, '2023-06-24 18:57:39', '2023-08-06 17:25:35', NULL, 1);
INSERT INTO `def_template` VALUES (1675456723376939009, NULL, '需评审西门子电机定价（表价折扣率）', 'INPUT', 1, '', 1, '2023-07-02 18:49:46', '2023-07-02 18:49:46', 1, 1);
INSERT INTO `def_template` VALUES (1685686372972564481, NULL, '西门子电机付款时效', 'INPUT', 1, '', NULL, '2023-07-31 00:18:45', '2023-07-31 00:18:45', 1, 1);
INSERT INTO `def_template` VALUES (1685686485488963585, NULL, '西门子电机付款形式', 'INPUT', 1, '', NULL, '2023-07-31 00:19:11', '2023-07-31 00:19:11', 1, 1);
INSERT INTO `def_template` VALUES (1685686528375721986, NULL, '贝得电机定价（表价折扣率）', 'INPUT', 1, '', NULL, '2023-07-31 00:19:22', '2023-07-31 00:19:22', 1, 1);
INSERT INTO `def_template` VALUES (1685686562739654657, NULL, '需评审贝得电机定价（表价折扣率）', 'INPUT', 1, '', NULL, '2023-07-31 00:19:30', '2023-07-31 00:19:30', 1, 1);
INSERT INTO `def_template` VALUES (1685686600668745729, NULL, '贝得电机付款时效', 'INPUT', 1, '', NULL, '2023-07-31 00:19:39', '2023-07-31 00:19:39', 1, 1);
INSERT INTO `def_template` VALUES (1685686640640462850, NULL, '贝得电机付款形式', 'INPUT', 1, '', NULL, '2023-07-31 00:19:48', '2023-07-31 00:19:48', 1, 1);
INSERT INTO `def_template` VALUES (1685686695338381313, NULL, '如有固定垫资，需垫资含税金额：元', 'INPUT', 1, '', NULL, '2023-07-31 00:20:01', '2023-08-06 00:10:36', 1, 1);

-- ----------------------------
-- Table structure for def_user
-- ----------------------------
DROP TABLE IF EXISTS `def_user`;
CREATE TABLE `def_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `username` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `real_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `dept_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门id',
  `pos_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '岗位id',
  `employee_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_user
-- ----------------------------
INSERT INTO `def_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '2', '838384939610279936', 't53', '1', '15824534567', '1', '123@qq.com');
INSERT INTO `def_user` VALUES ('2', 'chenzhilong', 'bc1f5754ebc6814191bf55b0bc8f74dd', '陈志龙', '2', '52', '125', '1', '15245653454', '1', '222@qq.com');
INSERT INTO `def_user` VALUES ('818461880107536384', 'chenxiaoli', 'a2cfd1ab478daa979942b27af08abcc8', '陈小利', '', '48', '23', '1', '12343212344', '0', '1358996567@ww.cc');
INSERT INTO `def_user` VALUES ('828242314345451520', 'test', '098f6bcd4621d373cade4e832627b4f6', '销售部总经理', '', '834055282635182080', '13456', '1', '12345678777', '1', '');

-- ----------------------------
-- Table structure for rel_audit_user
-- ----------------------------
DROP TABLE IF EXISTS `rel_audit_user`;
CREATE TABLE `rel_audit_user`  (
  `id` bigint(0) NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `audit_id` bigint(0) NULL DEFAULT NULL,
  `audit_by` bigint(0) NULL DEFAULT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `update_at` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rel_department_user
-- ----------------------------
DROP TABLE IF EXISTS `rel_department_user`;
CREATE TABLE `rel_department_user`  (
  `id` bigint(0) NOT NULL,
  `department_id` bigint(0) NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_department_user
-- ----------------------------
INSERT INTO `rel_department_user` VALUES (1678405136583303169, 6, 818461880107536384);

-- ----------------------------
-- Table structure for rel_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `rel_notice_user`;
CREATE TABLE `rel_notice_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `notice_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `state` int(0) NULL DEFAULT NULL COMMENT '0未阅读 1 阅读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告_用户外键' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_notice_user
-- ----------------------------
INSERT INTO `rel_notice_user` VALUES ('330381411037089792', '330381411007729664', '1', 1);
INSERT INTO `rel_notice_user` VALUES ('330381411045478400', '330381411007729664', '488294747442511872', 0);
INSERT INTO `rel_notice_user` VALUES ('330381806375407616', '330381806358630400', '1', 1);
INSERT INTO `rel_notice_user` VALUES ('330381806379601920', '330381806358630400', '488294747442511872', 0);
INSERT INTO `rel_notice_user` VALUES ('330622143622680576', '330622143597514752', '1', 1);
INSERT INTO `rel_notice_user` VALUES ('330622143626874880', '330622143597514752', '488294747442511872', 0);
INSERT INTO `rel_notice_user` VALUES ('354984345649418240', '354984345632641024', '1', 1);
INSERT INTO `rel_notice_user` VALUES ('373478037158760448', '373478036928073728', '1', 1);
INSERT INTO `rel_notice_user` VALUES ('373478037162954752', '373478036928073728', '368026921239449600', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037171343360', '373478036928073728', '368026937181999104', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037175537664', '373478036928073728', '368027013392502784', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037183926272', '373478036928073728', '368027030899527680', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037192314880', '373478036928073728', '368027048402358272', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037204897792', '373478036928073728', '368027066563694592', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037213286400', '373478036928073728', '368027087866564608', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037217480704', '373478036928073728', '368027104895438848', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037225869312', '373478036928073728', '368027130728157184', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037230063616', '373478036928073728', '368027151624179712', 0);
INSERT INTO `rel_notice_user` VALUES ('373478037238452224', '373478036928073728', '368382463233363968', 0);
INSERT INTO `rel_notice_user` VALUES ('502750147499921408', '502750147395063808', '1', 0);
INSERT INTO `rel_notice_user` VALUES ('502750147508310016', '502750147395063808', '433236479427350528', 0);
INSERT INTO `rel_notice_user` VALUES ('502758207983325184', '502758207907827712', '1', 0);
INSERT INTO `rel_notice_user` VALUES ('502758207991713792', '502758207907827712', '433236479427350528', 0);
INSERT INTO `rel_notice_user` VALUES ('502820822130495488', '502820822042415104', '1', 0);
INSERT INTO `rel_notice_user` VALUES ('502820822138884096', '502820822042415104', '433236479427350528', 0);

-- ----------------------------
-- Table structure for rel_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `rel_permission_role`;
CREATE TABLE `rel_permission_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_permission_role
-- ----------------------------
INSERT INTO `rel_permission_role` VALUES ('1668270900064464897', '24', '411522822607867904');
INSERT INTO `rel_permission_role` VALUES ('1668270900265791489', '24', '4');
INSERT INTO `rel_permission_role` VALUES ('1668270900525838338', '24', '5');
INSERT INTO `rel_permission_role` VALUES ('1668270900722970625', '24', '6');
INSERT INTO `rel_permission_role` VALUES ('1668270901113040898', '24', '7');
INSERT INTO `rel_permission_role` VALUES ('1668270901377282050', '24', '8');
INSERT INTO `rel_permission_role` VALUES ('1668270901570220033', '24', '486690002869157888');
INSERT INTO `rel_permission_role` VALUES ('1668270901960290305', '24', '9');
INSERT INTO `rel_permission_role` VALUES ('1668270902228725762', '24', '10');
INSERT INTO `rel_permission_role` VALUES ('1668270902824316929', '24', '11');
INSERT INTO `rel_permission_role` VALUES ('1668270903289884673', '24', '12');
INSERT INTO `rel_permission_role` VALUES ('1668270904074219521', '24', '13');
INSERT INTO `rel_permission_role` VALUES ('1668270904665616385', '24', '14');
INSERT INTO `rel_permission_role` VALUES ('1668270905122795522', '24', '15');
INSERT INTO `rel_permission_role` VALUES ('1668270905642889218', '24', '16');
INSERT INTO `rel_permission_role` VALUES ('1668270905965850626', '24', '17');
INSERT INTO `rel_permission_role` VALUES ('1668270906167177217', '24', '18');
INSERT INTO `rel_permission_role` VALUES ('1668270906423029761', '24', '410791701859405824');
INSERT INTO `rel_permission_role` VALUES ('1668270906553053186', '24', '4107917018594058251');
INSERT INTO `rel_permission_role` VALUES ('1668270906741796865', '24', '4107917018594058262');
INSERT INTO `rel_permission_role` VALUES ('1668270906938929153', '24', '4107917018594058273');
INSERT INTO `rel_permission_role` VALUES ('1668270907203170305', '24', '4107917018594058284');
INSERT INTO `rel_permission_role` VALUES ('1668270907467411457', '24', '410989805699207168');
INSERT INTO `rel_permission_role` VALUES ('1668270907727458306', '24', '4109898056992071691');
INSERT INTO `rel_permission_role` VALUES ('1668270907987505154', '24', '4109898056992071702');
INSERT INTO `rel_permission_role` VALUES ('1668270908251746305', '24', '4109898056992071713');
INSERT INTO `rel_permission_role` VALUES ('1668270908511793154', '24', '4109898056992071724');
INSERT INTO `rel_permission_role` VALUES ('1678408984530067458', '4', '831828755528945664');
INSERT INTO `rel_permission_role` VALUES ('1678408984723005441', '4', '831828755528945667');
INSERT INTO `rel_permission_role` VALUES ('1678408985247293441', '4', '831828755528945665');
INSERT INTO `rel_permission_role` VALUES ('1678408985712861186', '4', '831828755528945666');
INSERT INTO `rel_permission_role` VALUES ('1678408986035822593', '4', '831828755528945668');
INSERT INTO `rel_permission_role` VALUES ('1678408986295869442', '4', '831828755528945669');
INSERT INTO `rel_permission_role` VALUES ('1678408986555916290', '4', '831828755528945670');
INSERT INTO `rel_permission_role` VALUES ('1678408986883072001', '4', '832575092578979840');
INSERT INTO `rel_permission_role` VALUES ('1678408987138924545', '4', '832576319278682114');
INSERT INTO `rel_permission_role` VALUES ('1678409150679031810', '838382256191049728', '831828755528945664');
INSERT INTO `rel_permission_role` VALUES ('1678409151001993218', '838382256191049728', '831828755528945667');
INSERT INTO `rel_permission_role` VALUES ('1678409151266234370', '838382256191049728', '831828755528945665');
INSERT INTO `rel_permission_role` VALUES ('1678409151660498945', '838382256191049728', '831828755528945666');
INSERT INTO `rel_permission_role` VALUES ('1678409152184786946', '838382256191049728', '832575092578979840');
INSERT INTO `rel_permission_role` VALUES ('1678409152507748354', '838382256191049728', '832576319278682113');
INSERT INTO `rel_permission_role` VALUES ('1678409152906207233', '838382256191049728', '832577421923454976');
INSERT INTO `rel_permission_role` VALUES ('1678409153749262338', '838382256191049728', '832577421923454977');
INSERT INTO `rel_permission_role` VALUES ('1688561575774703617', '848540272202747904', '831828755528945664');
INSERT INTO `rel_permission_role` VALUES ('1688561575997001729', '848540272202747904', '831828755528945667');
INSERT INTO `rel_permission_role` VALUES ('1688561576336740353', '848540272202747904', '831828755528945666');
INSERT INTO `rel_permission_role` VALUES ('1688561576722616321', '848540272202747904', '832575092578979840');
INSERT INTO `rel_permission_role` VALUES ('1688561577112686594', '848540272202747904', '832576319278682113');
INSERT INTO `rel_permission_role` VALUES ('1688566130637864961', '60', '411522822607867904');
INSERT INTO `rel_permission_role` VALUES ('1688566131485114369', '60', '4');
INSERT INTO `rel_permission_role` VALUES ('1688566132131037186', '60', '5');
INSERT INTO `rel_permission_role` VALUES ('1688566133104115713', '60', '6');
INSERT INTO `rel_permission_role` VALUES ('1688566133565489154', '60', '7');
INSERT INTO `rel_permission_role` VALUES ('1688566133825536002', '60', '8');
INSERT INTO `rel_permission_role` VALUES ('1688566134085582850', '60', '486690002869157888');
INSERT INTO `rel_permission_role` VALUES ('1688566134345629698', '60', '9');
INSERT INTO `rel_permission_role` VALUES ('1688566134735699970', '60', '10');
INSERT INTO `rel_permission_role` VALUES ('1688566135129964545', '60', '11');
INSERT INTO `rel_permission_role` VALUES ('1688566135587143682', '60', '12');
INSERT INTO `rel_permission_role` VALUES ('1688566135960436738', '60', '13');
INSERT INTO `rel_permission_role` VALUES ('1688566136283398146', '60', '14');
INSERT INTO `rel_permission_role` VALUES ('1688566136606359554', '60', '15');
INSERT INTO `rel_permission_role` VALUES ('1688566136870600705', '60', '16');
INSERT INTO `rel_permission_role` VALUES ('1688566137130647553', '60', '17');
INSERT INTO `rel_permission_role` VALUES ('1688566137457803265', '60', '18');
INSERT INTO `rel_permission_role` VALUES ('1688566137722044418', '60', '410791701859405824');
INSERT INTO `rel_permission_role` VALUES ('1688566138045005826', '60', '4107917018594058251');
INSERT INTO `rel_permission_role` VALUES ('1688566138497990657', '60', '4107917018594058262');
INSERT INTO `rel_permission_role` VALUES ('1688566138896449538', '60', '4107917018594058273');
INSERT INTO `rel_permission_role` VALUES ('1688566139420737538', '60', '4107917018594058284');
INSERT INTO `rel_permission_role` VALUES ('1688566139810807810', '60', '410989805699207168');
INSERT INTO `rel_permission_role` VALUES ('1688566140003745794', '60', '4109898056992071691');
INSERT INTO `rel_permission_role` VALUES ('1688566140335095810', '60', '4109898056992071702');
INSERT INTO `rel_permission_role` VALUES ('1688566140653862913', '60', '4109898056992071713');
INSERT INTO `rel_permission_role` VALUES ('1688566140976824321', '60', '4109898056992071724');
INSERT INTO `rel_permission_role` VALUES ('1688566141496918017', '60', '592059865673760768');
INSERT INTO `rel_permission_role` VALUES ('1688566141886988289', '60', '19');
INSERT INTO `rel_permission_role` VALUES ('1688566142209949698', '60', '20');
INSERT INTO `rel_permission_role` VALUES ('1688566142537105410', '60', '21');
INSERT INTO `rel_permission_role` VALUES ('1688566142855872513', '60', '22');
INSERT INTO `rel_permission_role` VALUES ('1688566143115919361', '60', '23');
INSERT INTO `rel_permission_role` VALUES ('1688566143505989634', '60', '330365026642825216');
INSERT INTO `rel_permission_role` VALUES ('1688566143979945986', '60', '3303650266428252171');
INSERT INTO `rel_permission_role` VALUES ('1688566144416153601', '60', '3303650266428252182');
INSERT INTO `rel_permission_role` VALUES ('1688566144672006145', '60', '3303650266428252193');
INSERT INTO `rel_permission_role` VALUES ('1688566144999161857', '60', '3303650266428252204');
INSERT INTO `rel_permission_role` VALUES ('1688566145259208706', '60', '332157860920299520');
INSERT INTO `rel_permission_role` VALUES ('1688566145657667585', '60', '3321578609202995211');
INSERT INTO `rel_permission_role` VALUES ('1688566146114846721', '60', '3321578609202995222');
INSERT INTO `rel_permission_role` VALUES ('1688566146639134722', '60', '3321578609202995233');
INSERT INTO `rel_permission_role` VALUES ('1688566146899181569', '60', '3321578609202995244');
INSERT INTO `rel_permission_role` VALUES ('1688566147297640449', '60', '332857281479839744');
INSERT INTO `rel_permission_role` VALUES ('1688566147553492993', '60', '3328572814798397451');
INSERT INTO `rel_permission_role` VALUES ('1688566147884843010', '60', '3328572814798397473');
INSERT INTO `rel_permission_role` VALUES ('1688566148207804417', '60', '496126970468237312');
INSERT INTO `rel_permission_role` VALUES ('1688566148534960129', '60', '496127240363311104');
INSERT INTO `rel_permission_role` VALUES ('1688566149055053826', '60', '496127794879660032');
INSERT INTO `rel_permission_role` VALUES ('1688566149445124097', '60', '618918631769636864');
INSERT INTO `rel_permission_role` VALUES ('1688566149843582977', '60', '619836559427895296');
INSERT INTO `rel_permission_role` VALUES ('1688566150300762113', '60', '6189186317738311681');
INSERT INTO `rel_permission_role` VALUES ('1688566150695026689', '60', '6189186317948026882');
INSERT INTO `rel_permission_role` VALUES ('1688566151085096961', '60', '6189186317948026883');
INSERT INTO `rel_permission_role` VALUES ('1688566151282229249', '60', '6189186317989969924');
INSERT INTO `rel_permission_role` VALUES ('1688566151936540673', '60', '6192095214866268161');
INSERT INTO `rel_permission_role` VALUES ('1688566152456634370', '60', '6192095214866268162');
INSERT INTO `rel_permission_role` VALUES ('1688566152850898945', '60', '6192095215075983363');
INSERT INTO `rel_permission_role` VALUES ('1688566153173860353', '60', '6192095215075983364');
INSERT INTO `rel_permission_role` VALUES ('1688566153501016065', '60', '830399865111580672');
INSERT INTO `rel_permission_role` VALUES ('1688566153895280641', '60', '830435502187483136');
INSERT INTO `rel_permission_role` VALUES ('1688566154214047745', '60', '830435926206451712');
INSERT INTO `rel_permission_role` VALUES ('1688566154474094594', '60', '830773701376086016');
INSERT INTO `rel_permission_role` VALUES ('1688566154872553474', '60', '830773987662499840');
INSERT INTO `rel_permission_role` VALUES ('1688566155380064258', '60', '830773987662499841');
INSERT INTO `rel_permission_role` VALUES ('1688566155711414274', '60', '834750985724366848');
INSERT INTO `rel_permission_role` VALUES ('1688566156567052289', '60', '840229919358324736');
INSERT INTO `rel_permission_role` VALUES ('1688566157028425730', '60', '8402299193583247367');
INSERT INTO `rel_permission_role` VALUES ('1688566157477216258', '60', '831828755528945664');
INSERT INTO `rel_permission_role` VALUES ('1688566157808566273', '60', '831828755528945667');
INSERT INTO `rel_permission_role` VALUES ('1688566158265745409', '60', '831828755528945666');
INSERT INTO `rel_permission_role` VALUES ('1688566158722924545', '60', '831828755528945668');
INSERT INTO `rel_permission_role` VALUES ('1688566158915862529', '60', '831828755528945669');
INSERT INTO `rel_permission_role` VALUES ('1688566159377235969', '60', '831828755528945670');
INSERT INTO `rel_permission_role` VALUES ('1688566159708585986', '60', '844924903781175296');
INSERT INTO `rel_permission_role` VALUES ('1688566159905718274', '60', '848543977035665408');
INSERT INTO `rel_permission_role` VALUES ('1688566160228679682', '60', '832575092578979840');
INSERT INTO `rel_permission_role` VALUES ('1688566160551641090', '60', '832576319278682113');
INSERT INTO `rel_permission_role` VALUES ('1688566161273061378', '60', '832576319278682114');

-- ----------------------------
-- Table structure for rel_role_user
-- ----------------------------
DROP TABLE IF EXISTS `rel_role_user`;
CREATE TABLE `rel_role_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rel_role_user
-- ----------------------------
INSERT INTO `rel_role_user` VALUES ('838384042675146752', '818461880107536384', '4');
INSERT INTO `rel_role_user` VALUES ('848939825997418496', '2', '838382256191049728');
INSERT INTO `rel_role_user` VALUES ('848949933645107200', '828242314345451520', '848540272202747904');
INSERT INTO `rel_role_user` VALUES ('848949965857361920', '1', '60');

SET FOREIGN_KEY_CHECKS = 1;
