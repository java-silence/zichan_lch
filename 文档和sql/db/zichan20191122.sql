/*
 Navicat Premium Data Transfer

 Source Server         : 47.92.68.245
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 47.92.68.245:3306
 Source Schema         : zichan

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/11/2019 15:25:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cg_dingdan
-- ----------------------------
DROP TABLE IF EXISTS `cg_dingdan`;
CREATE TABLE `cg_dingdan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ccode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划编码',
  `ddate` datetime(0) NULL DEFAULT NULL COMMENT '需求日期',
  `busstype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `csource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据来源',
  `bussid` int(11) NULL DEFAULT NULL COMMENT '对应业务单号',
  `jglx` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格类型',
  `fkfs` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `whid` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `userid` int(11) NULL DEFAULT NULL COMMENT '业务员',
  `ksid` int(11) NULL DEFAULT NULL COMMENT '供应商',
  `inum` decimal(11, 2) NULL DEFAULT NULL COMMENT '数量',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `tax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `itotal` decimal(11, 2) NULL DEFAULT NULL COMMENT '价税合计',
  `yfdj` decimal(11, 2) NULL DEFAULT NULL COMMENT '运费单价',
  `yfje` decimal(11, 2) NULL DEFAULT NULL COMMENT '运费金额',
  `yffkid` int(11) NULL DEFAULT NULL COMMENT '运费付款单号',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `dhsl` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '到货数量',
  `dhje` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '到货金额',
  `rksl` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '入库数量',
  `ykfp` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '已开发票',
  `yfkje` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '已付金额',
  `clbm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `telephone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cg_dingdans
-- ----------------------------
DROP TABLE IF EXISTS `cg_dingdans`;
CREATE TABLE `cg_dingdans`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '主表ID',
  `invid` int(11) NULL DEFAULT NULL COMMENT '存货',
  `cpgg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品规格',
  `danwei` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `inum` decimal(11, 2) NULL DEFAULT NULL COMMENT '数量',
  `iprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `itax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `itotal` decimal(11, 2) NULL DEFAULT NULL COMMENT '价税合计',
  `cbatch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批号',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `dhsl` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '到货数量',
  `rksl` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '入库数量',
  `yfdj` decimal(11, 2) NULL DEFAULT NULL COMMENT '运费单价',
  `yfje` decimal(11, 2) NULL DEFAULT NULL COMMENT '运费金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 351 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购订单表子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_news
-- ----------------------------
DROP TABLE IF EXISTS `cms_news`;
CREATE TABLE `cms_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionId` int(11) NOT NULL,
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `brief` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容摘要',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgtype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片类型',
  `status` tinyint(1) NULL DEFAULT 1,
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `hits` int(11) NULL DEFAULT 0 COMMENT '点击量',
  `eventdate` datetime(0) NULL DEFAULT NULL COMMENT '事件日期',
  `eventtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `eventlocation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 192 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cw_fukuanshenqing
-- ----------------------------
DROP TABLE IF EXISTS `cw_fukuanshenqing`;
CREATE TABLE `cw_fukuanshenqing`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ccode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据编码',
  `ddate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `busstype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `csource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据来源',
  `bussid` int(11) NULL DEFAULT NULL COMMENT '对应业务单号',
  `fkfs` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `fkxm` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款项目',
  `fkje` decimal(11, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `ksid` int(11) NULL DEFAULT NULL COMMENT '供应商',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件md5',
  `filename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `contentType` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '物理路径',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int(1) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `bizid` int(11) NULL DEFAULT NULL COMMENT '业务ID',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流程ID',
  `flowname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述说明',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow
-- ----------------------------
INSERT INTO `flow` VALUES (1, '设备调拨流程', '设备调拨流程', 1, '2018-05-05 21:36:09', 1, '2018-05-05 21:36:09', '1', '0', NULL, '1', '1', '1', '1');
INSERT INTO `flow` VALUES (2, '设备处置流程', '设备处置流程', 1, '2018-05-05 21:37:49', 1, '2018-05-05 21:37:49', '1', '0', NULL, '1', '1', '1', '1');
INSERT INTO `flow` VALUES (3, '设备报废流程', '设备报废流程', 1, '2018-03-06 22:42:55', NULL, '2018-03-06 22:42:55', '1', '0', '', NULL, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (4, '设备维修流程', '设备维修流程', 1, '2018-03-06 22:42:55', NULL, '2018-03-06 22:42:55', NULL, '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (5, '设备采购流程', '设备采购流程', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (6, '设备入库流程', '设备入库流程', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (7, '设备安装流程', '设备安装流程', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (8, '设备更换流程', '设备更换流程', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for flow_doc
-- ----------------------------
DROP TABLE IF EXISTS `flow_doc`;
CREATE TABLE `flow_doc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `brief` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容摘要',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgtype` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片类型',
  `ddate1` datetime(0) NULL DEFAULT NULL COMMENT '日期1',
  `ddate2` datetime(0) NULL DEFAULT NULL COMMENT '日期2',
  `status` tinyint(1) NULL DEFAULT 1,
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门编码',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `doctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档类型',
  `c01` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `c04` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用4',
  `c05` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用5',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow_todo
-- ----------------------------
DROP TABLE IF EXISTS `flow_todo`;
CREATE TABLE `flow_todo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审批人',
  `sendby` int(11) NULL DEFAULT NULL COMMENT '发送人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '审批时间',
  `biaoti` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `neirong` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `bizid` int(11) NULL DEFAULT NULL COMMENT '业务ID',
  `biztable` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务表名',
  `bizcreateby` int(11) NULL DEFAULT NULL COMMENT '业务单据创建人',
  `bizdeptid` int(11) NULL DEFAULT NULL COMMENT '业务单据部门',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态:待审批/同意/拒绝',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务处理URL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 919 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow_todo
-- ----------------------------
INSERT INTO `flow_todo` VALUES (802, 3, 59, '2019-08-06 03:47:11', '2019-08-06 03:47:55', '【管材主任】申请油封等', '已采购', NULL, 89, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (803, 59, 3, '2019-08-06 03:47:55', '2019-08-06 03:47:55', '【管材主任】申请油封等', NULL, NULL, 89, 'cg_jihua', 59, 4, '0', NULL, NULL, NULL, NULL, 4, 3, 'caigou/bjsqsh3.html');
INSERT INTO `flow_todo` VALUES (809, 3, 59, '2019-08-06 10:32:48', '2019-08-06 10:36:55', '【管材主任】申请三角带等', '', NULL, 95, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (810, 3, 59, '2019-08-06 10:32:53', '2019-08-06 10:36:13', '【管材主任】申请三角带等', '通过', NULL, 96, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (812, 59, 3, '2019-08-06 10:36:13', '2019-08-06 10:36:47', '【管材主任】申请三角带等', '', NULL, 96, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 3, 'caigou/bjsqsh3.html');
INSERT INTO `flow_todo` VALUES (814, 3, 59, '2019-08-06 10:39:10', '2019-08-06 10:39:41', '【管材主任】申请螺丝等', '', NULL, 97, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (815, 59, 3, '2019-08-06 10:39:41', '2019-08-06 10:40:12', '【管材主任】申请螺丝等', '', NULL, 97, 'cg_jihua', 59, 4, '1', NULL, NULL, NULL, NULL, 4, 3, 'caigou/bjsqsh3.html');
INSERT INTO `flow_todo` VALUES (816, 3, 71, '2019-08-06 11:13:33', '2019-08-06 14:45:12', '【李军建】null维修单审核', '', NULL, 305, 'zx_repair', 71, 5, '1', NULL, NULL, NULL, NULL, 1, 2, 'repair/auditzhan.html');
INSERT INTO `flow_todo` VALUES (817, 3, 72, '2019-08-06 11:37:41', '2019-08-06 11:41:54', '【李春芳】申请插座等', '', NULL, 98, 'cg_jihua', 72, 5, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (818, 72, 3, '2019-08-06 11:41:54', '2019-08-06 11:49:58', '【李春芳】申请插座等', '', NULL, 98, 'cg_jihua', 72, 5, '1', NULL, NULL, NULL, NULL, 4, 3, 'caigou/bjsqsh3.html');
INSERT INTO `flow_todo` VALUES (819, 3, 3, '2019-08-06 14:45:12', '2019-08-06 14:45:12', '【李军建】null维修单审核', NULL, NULL, 305, 'zx_repair', 71, 5, '0', NULL, NULL, NULL, NULL, 1, 3, 'repair/auditZcBf.html');
INSERT INTO `flow_todo` VALUES (820, 3, 1, '2019-08-07 14:40:06', '2019-08-07 14:40:06', '【管理员】1申请脏料等', NULL, NULL, 99, 'cg_jihua', 1, 1, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (831, NULL, 3, '2019-08-14 16:33:40', '2019-08-14 16:33:40', '【朱晨贵】申请11等', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (832, NULL, 3, '2019-08-14 16:34:24', '2019-08-14 16:34:24', '【朱晨贵】申请11等', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (833, 7, 3, '2019-08-14 16:53:57', '2019-08-14 16:53:57', '【朱晨贵】申请11等[￥2000.00]', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (834, 7, 3, '2019-08-14 16:59:23', '2019-08-14 16:59:23', '【朱晨贵】申请11等[￥2000.00]', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (835, 7, 3, '2019-08-14 16:59:45', '2019-08-14 16:59:45', '【朱晨贵】申请11等[￥2000.00]', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (836, 7, 3, '2019-08-14 17:13:42', '2019-08-14 17:13:42', '【朱晨贵】申请11等[￥2000.00]', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (847, 7, 3, '2019-08-15 13:50:01', '2019-08-15 13:50:01', '【朱晨贵】申请11等[￥2000.00]', NULL, NULL, 101, 'cg_jihua', 3, 3, '0', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (848, 71, 3, '2019-08-17 12:47:11', '2019-08-17 12:48:28', '【朱晨贵】申请其他备件等[￥0.00]', '', NULL, 102, 'cg_jihua', 3, 5, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (849, 3, 71, '2019-08-17 12:50:28', '2019-08-17 12:50:28', '【李军建】null维修单审核', NULL, NULL, 306, 'zx_repair', 71, 5, '0', NULL, NULL, NULL, NULL, 1, 2, 'repair/auditzhan.html');
INSERT INTO `flow_todo` VALUES (850, 71, 3, '2019-08-17 12:51:06', '2019-08-17 12:54:50', '【朱晨贵】申请其他备件等[￥1294.00]', '', NULL, 102, 'cg_jihua', 3, 5, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (851, 71, 3, '2019-08-17 12:52:07', '2019-08-17 12:54:04', '【朱晨贵】申请其他备件等[￥1294.00]', '', NULL, 102, 'cg_jihua', 3, 5, '1', NULL, NULL, NULL, NULL, 4, 2, 'caigou/bjsqsh.html');
INSERT INTO `flow_todo` VALUES (852, 7, 120, '2019-08-25 08:38:35', '2019-08-25 08:38:35', '【李文建-订单任务】', NULL, NULL, 277, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (853, 7, 120, '2019-09-02 16:40:48', '2019-09-02 16:40:48', '【李文建-订单任务】', NULL, NULL, 316, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (854, 7, 120, '2019-09-02 16:41:39', '2019-09-02 16:41:39', '【李文建-订单任务】', NULL, NULL, 302, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (855, 7, 120, '2019-09-02 16:42:06', '2019-09-02 16:42:06', '【李文建-订单任务】王 五 寺', NULL, NULL, 296, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (856, 7, 120, '2019-09-03 09:58:16', '2019-09-03 09:58:16', '【李文建-订单任务】', NULL, NULL, 320, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (857, 7, 120, '2019-09-04 14:31:14', '2019-09-04 14:31:14', '【李文建-订单任务】', NULL, NULL, 331, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (858, 7, 120, '2019-09-06 09:48:48', '2019-09-06 09:48:48', '【李文建-订单任务】', NULL, NULL, 335, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (859, 7, 120, '2019-09-07 10:09:37', '2019-09-07 10:09:37', '【李文建-订单任务】', NULL, NULL, 346, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (860, 7, 120, '2019-09-07 10:30:44', '2019-09-07 10:30:44', '【李文建-订单任务】', NULL, NULL, 347, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (861, 7, 120, '2019-09-07 10:39:12', '2019-09-07 10:39:12', '【李文建-订单任务】', NULL, NULL, 348, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (862, 7, 120, '2019-09-07 10:41:48', '2019-09-07 10:41:48', '【李文建-订单任务】', NULL, NULL, 349, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (863, 7, 120, '2019-09-10 09:51:05', '2019-09-10 09:51:05', '【李文建-订单任务】铺车皮黑膜何总', NULL, NULL, 358, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (864, 7, 120, '2019-09-10 11:12:18', '2019-09-10 11:12:18', '【李文建-订单任务】蒲城刘清理', NULL, NULL, 363, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (865, 7, 120, '2019-09-10 11:12:20', '2019-09-10 11:12:20', '【李文建-订单任务】蒲城刘清理', NULL, NULL, 359, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (866, 7, 120, '2019-09-10 11:12:22', '2019-09-10 11:12:22', '【李文建-订单任务】蒲城刘清理', NULL, NULL, 360, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (867, 7, 120, '2019-09-10 11:12:24', '2019-09-10 11:12:24', '【李文建-订单任务】蒲城刘清理', NULL, NULL, 361, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (868, 7, 120, '2019-09-10 11:12:26', '2019-09-10 11:12:26', '【李文建-订单任务】蒲城刘清理', NULL, NULL, 362, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (869, 7, 120, '2019-09-12 10:53:03', '2019-09-12 10:53:03', '【李文建-订单任务】西安马雄伟', NULL, NULL, 367, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (870, 7, 7, '2019-09-12 17:11:15', '2019-09-12 17:11:15', '【王永波-订单任务】西安马雄伟', NULL, NULL, 367, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (871, 7, 7, '2019-09-12 17:11:32', '2019-09-12 17:11:32', '【王永波-订单任务】蒲城刘清理', NULL, NULL, 362, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (872, 7, 7, '2019-09-12 17:12:23', '2019-09-12 17:12:23', '【王永波-订单任务】蒲城刘清理', NULL, NULL, 362, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (873, 7, 7, '2019-09-12 17:12:31', '2019-09-12 17:12:31', '【王永波-订单任务】蒲城刘清理', NULL, NULL, 361, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (874, 7, 7, '2019-09-12 17:12:38', '2019-09-12 17:12:38', '【王永波-订单任务】蒲城刘清理', NULL, NULL, 362, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (875, 7, 120, '2019-09-15 11:49:50', '2019-09-15 11:49:50', '【李文建-订单任务】汉中邓强', NULL, NULL, 378, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (876, 7, 120, '2019-09-15 15:14:27', '2019-09-15 15:14:27', '【李文建-订单任务】西安周至县赵千凯', NULL, NULL, 379, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (877, 7, 120, '2019-09-16 09:49:57', '2019-09-16 09:49:57', '【李文建-订单任务】海泉五交化', NULL, NULL, 384, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (878, 7, 120, '2019-09-16 10:08:38', '2019-09-16 10:08:38', '【李文建-订单任务】风陵渡四娃', NULL, NULL, 385, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (879, 7, 120, '2019-09-16 15:19:59', '2019-09-16 15:19:59', '【李文建-订单任务】灵石肖林鹏', NULL, NULL, 390, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (880, 7, 120, '2019-09-16 15:32:58', '2019-09-16 15:32:58', '【李文建-订单任务】泾阳魏红', NULL, NULL, 391, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (881, 7, 120, '2019-09-16 15:37:45', '2019-09-16 15:37:45', '【李文建-订单任务】泾阳魏红', NULL, NULL, 392, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (882, 7, 120, '2019-09-16 15:49:17', '2019-09-16 15:49:17', '【李文建-订单任务】泾阳魏红', NULL, NULL, 393, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (883, 2, 104, '2019-09-22 09:54:12', '2019-09-22 10:42:50', '【左会锋】申请茂金属1520等[￥0.00]', '', NULL, 103, 'cg_jihua', 104, 5, '1', NULL, NULL, NULL, NULL, 2, 2, 'caigou/ylsqsh.html');
INSERT INTO `flow_todo` VALUES (884, 7, 120, '2019-09-23 14:53:13', '2019-09-23 14:53:13', '【李文建-订单任务】太原郭学梅', NULL, NULL, 422, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (885, 7, 120, '2019-09-23 14:55:39', '2019-09-23 14:55:39', '【李文建-订单任务】海泉五交化', NULL, NULL, 423, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (886, 7, 120, '2019-09-24 14:59:28', '2019-09-24 14:59:28', '【李文建-订单任务】户县李芳芳', NULL, NULL, 426, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (887, 7, 120, '2019-09-24 15:54:23', '2019-09-24 15:54:23', '【李文建-订单任务】小解', NULL, NULL, 428, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (888, 71, 104, '2019-09-26 17:02:58', '2019-09-26 17:02:58', '【左会锋-订单任务】呼和浩特市东程科技包装制品', NULL, NULL, 403, 'xs_dingdan', 104, 5, '0', NULL, NULL, NULL, NULL, 12, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (889, 71, 104, '2019-09-27 17:35:04', '2019-09-27 17:35:04', '【左会锋-订单任务】平遥县时代印业有限公司', NULL, NULL, 438, 'xs_dingdan', 104, 5, '0', NULL, NULL, NULL, NULL, 12, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (890, 71, 104, '2019-09-27 17:35:23', '2019-09-27 17:35:23', '【左会锋-订单任务】宝鸡佳美特印务有限公司', NULL, NULL, 424, 'xs_dingdan', 104, 5, '0', NULL, NULL, NULL, NULL, 12, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (891, 71, 71, '2019-09-28 09:15:16', '2019-09-28 09:15:16', '【李军建-订单任务】平遥县时代印业有限公司', NULL, NULL, 439, 'xs_dingdan', 71, 5, '0', NULL, NULL, NULL, NULL, 12, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (892, 71, 104, '2019-09-28 12:21:50', '2019-09-28 12:21:50', '【左会锋-订单任务】平遥县时代印业有限公司', NULL, NULL, 439, 'xs_dingdan', 71, 5, '0', NULL, NULL, NULL, NULL, 12, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (893, 7, 120, '2019-09-28 13:38:32', '2019-09-28 13:38:32', '【李文建-订单任务】蒲城薛焕斌', NULL, NULL, 440, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (894, 7, 120, '2019-10-02 09:39:48', '2019-10-02 09:39:48', '【李文建-订单任务】海泉五交化', NULL, NULL, 450, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (895, 7, 120, '2019-10-02 09:39:57', '2019-10-02 09:39:57', '【李文建-订单任务】海泉五交化', NULL, NULL, 450, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (896, 7, 120, '2019-10-02 14:31:37', '2019-10-02 14:31:37', '【李文建-订单任务】海泉五交化', NULL, NULL, 450, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (897, 7, 120, '2019-10-04 10:19:58', '2019-10-04 10:19:58', '【李文建-订单任务】户县李芳芳', NULL, NULL, 457, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (898, 7, 120, '2019-10-08 14:18:18', '2019-10-08 14:18:18', '【李文建-订单任务】甘肃平凉车站李朝阳', NULL, NULL, 473, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (899, 7, 120, '2019-10-10 13:42:09', '2019-10-10 13:42:09', '【李文建-订单任务】户县李芳芳', NULL, NULL, 486, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (900, 7, 120, '2019-10-15 15:52:32', '2019-10-15 15:52:32', '【李文建-订单任务】陕西汉中毛伟娟', NULL, NULL, 508, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (901, 7, 120, '2019-10-30 13:04:48', '2019-10-30 13:04:48', '【李文建-订单任务】大荔张卫国', NULL, NULL, 587, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (902, 7, 120, '2019-10-30 13:08:33', '2019-10-30 13:08:33', '【李文建-订单任务】大荔张卫国', NULL, NULL, 588, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (903, 7, 7, '2019-10-30 13:11:37', '2019-10-30 13:11:37', '【王永波-订单任务】大荔张卫国', NULL, NULL, 588, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (904, 7, 7, '2019-10-30 13:11:43', '2019-10-30 13:11:43', '【王永波-订单任务】大荔张卫国', NULL, NULL, 587, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (905, 98, 2, '2019-10-30 13:15:39', '2019-10-30 13:53:23', '【史金贵】付高靠社付物料款137400', '已付款', NULL, 20, 'cw_fukuanshenqing', 2, 1, '1', NULL, NULL, NULL, NULL, 14, 2, 'caigou/fukuansh.html');
INSERT INTO `flow_todo` VALUES (906, 7, 120, '2019-10-30 13:17:12', '2019-10-30 13:17:12', '【李文建-订单任务】大荔张卫国', NULL, NULL, 588, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (907, 5, 2, '2019-10-31 12:12:50', '2019-10-31 12:12:50', '【史金贵】付北京四联付物料款146000', NULL, NULL, 21, 'cw_fukuanshenqing', 2, 1, '0', NULL, NULL, NULL, NULL, 13, 2, 'caigou/fukuansh.html');
INSERT INTO `flow_todo` VALUES (908, 5, 2, '2019-10-31 13:02:27', '2019-10-31 13:02:27', '【史金贵】付李小梅付物料款400', NULL, NULL, 22, 'cw_fukuanshenqing', 2, 1, '0', NULL, NULL, NULL, NULL, 13, 2, 'caigou/fukuansh.html');
INSERT INTO `flow_todo` VALUES (909, 5, 1, '2019-10-31 20:50:32', '2019-10-31 20:50:32', '【管理员】付上海延海石油化工有限公司11111111', NULL, NULL, 23, 'cw_fukuanshenqing', 1, 1, '0', NULL, NULL, NULL, NULL, 13, 2, 'caigou/fukuansh.html');
INSERT INTO `flow_todo` VALUES (910, 7, 120, '2019-11-01 13:19:08', '2019-11-01 13:19:08', '【李文建-订单任务】西安马雄伟', NULL, NULL, 592, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (912, 5, 2, '2019-11-14 11:51:47', '2019-11-14 11:51:47', '【史金贵】付中油华北分公司付物料款220500', NULL, NULL, 25, 'cw_fukuanshenqing', 2, 1, '0', NULL, NULL, NULL, NULL, 13, 2, 'caigou/fukuansh.html');
INSERT INTO `flow_todo` VALUES (913, 7, 120, '2019-11-17 15:36:53', '2019-11-17 15:36:53', '【李文建-订单任务】阿克苏李治疆', NULL, NULL, 676, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (914, 7, 120, '2019-11-19 10:42:32', '2019-11-19 10:42:32', '【李文建-订单任务】西安马雄伟', NULL, NULL, 683, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (915, 7, 120, '2019-11-19 10:47:34', '2019-11-19 10:47:34', '【李文建-订单任务】阿克苏李治疆', NULL, NULL, 676, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (916, 7, 120, '2019-11-19 10:47:52', '2019-11-19 10:47:52', '【李文建-订单任务】阿瓦提宏宇农资', NULL, NULL, 675, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (917, 7, 120, '2019-11-19 10:49:47', '2019-11-19 10:49:47', '【李文建-订单任务】西安马雄伟', NULL, NULL, 684, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');
INSERT INTO `flow_todo` VALUES (918, 7, 120, '2019-11-21 10:23:44', '2019-11-21 10:23:44', '【李文建-订单任务】翼城要地膜', NULL, NULL, 703, 'xs_dingdan', 120, 3, '0', NULL, NULL, NULL, NULL, 11, 2, 'xsdingdan/auditxsdingdan.html');

-- ----------------------------
-- Table structure for flowmember
-- ----------------------------
DROP TABLE IF EXISTS `flowmember`;
CREATE TABLE `flowmember`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程ID',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '步骤ID',
  `memid` int(11) NULL DEFAULT NULL COMMENT '成员ID',
  `memtype` int(11) NULL DEFAULT NULL COMMENT '成员类型 1:用户,2:岗位,3:部门,4:上级岗位,5:本人,6上一流程指定,7业务所在部门岗位,8同上流程',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述说明',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flowmember
-- ----------------------------
INSERT INTO `flowmember` VALUES (18, 1, 2, 3, 1, '维修主管', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (51, 1, 3, NULL, 6, '下步站长指派', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (61, 1, 4, NULL, 8, '下步所指派维修人', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (66, 1, 5, 3, 1, '下步本站站长确认完工', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (80, 3, 4, NULL, 5, '付款申请人', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (82, 2, 2, 2, 1, '采购主管审批', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (87, 5, 2, 2, 1, '采购主管审批', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (88, 6, 2, 8, 7, '站长', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (89, 7, 2, 8, 7, '站长', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (90, 8, 2, 8, 7, '站长', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (91, 9, 2, 8, 7, '站长', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (92, 10, 2, 8, 7, '站长', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (93, 11, 2, 7, 1, '地膜车间主任接收', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (95, 12, 2, 71, 1, '包装膜车间主任接收', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (105, 3, 2, 5, 1, '财务主管', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (106, 3, 3, 6, 1, '会计', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (107, 4, 2, NULL, 6, '采购主管指定', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (108, 13, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (109, 13, 2, 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (110, 14, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowmember` VALUES (111, 14, 2, 98, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for flowstep
-- ----------------------------
DROP TABLE IF EXISTS `flowstep`;
CREATE TABLE `flowstep`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程ID',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '步骤ID',
  `stepname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '步骤名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述说明',
  `tofinish` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到结束节点',
  `flowrule` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程规则',
  `flowact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程动作',
  `passnum` int(11) NULL DEFAULT NULL COMMENT '通过数(0:只要一人通过即可 1-99:需通过的人数,99-全部通过)',
  `localalert` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地报警',
  `basehour` int(11) NULL DEFAULT NULL COMMENT '基本时长',
  `cyctimes` int(11) NULL DEFAULT NULL COMMENT '循环次数',
  `period` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '周期',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flowstep
-- ----------------------------
INSERT INTO `flowstep` VALUES (1, 1, 1, '设备调拨申请', '各使用单位', '0', NULL, 'repair/auditZcBf.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (2, 1, 2, '设备调拨审批', '各管理部门', '0', '', 'repair/auditzhan.html', 99, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (3, 1, 3, '设备接收确认', '接收单位', '0', NULL, 'repair/auditZcBf.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (4, 1, 4, '财务审核', '财务审核', '1', NULL, 'repair/auditZcBf.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (12, 2, 1, '原料采购申请', '车间主任等', '0', NULL, 'caigou/ylsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (13, 2, 2, '采购主管审批', '采购主管审批', '1', NULL, 'caigou/ylsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (17, 3, 1, '付款申请', '采购主管', '0', '', 'fukuan/auditfukuanshenqing.html', NULL, '', NULL, NULL, '', NULL, '2018-12-21 22:40:44', NULL, '2018-12-21 22:40:51', '', '', '', '', '', '', '');
INSERT INTO `flowstep` VALUES (18, 3, 2, '财务主管审批', '财务主管审批', '0', '', 'fukuan/auditfukuanshenqing.html', NULL, '', NULL, NULL, '', NULL, '2018-12-21 22:40:48', NULL, '2018-12-21 22:40:51', '', '', '', '', '', '', '');
INSERT INTO `flowstep` VALUES (19, 3, 3, '会计付款', '会计付款', '0', '', 'fukuan/auditfukuanshenqing.html', NULL, '', NULL, NULL, '', NULL, '2018-12-21 22:40:51', NULL, '2018-12-21 22:40:51', '', '', '', '', '', '', '');
INSERT INTO `flowstep` VALUES (20, 3, 4, '付款确认', '付款确认', '1', '', 'fukuan/auditfukuanshenqing.html', NULL, '', NULL, NULL, '', NULL, '2018-12-21 22:40:54', NULL, '2018-12-21 22:40:51', '', '', '', '', '', '', '');
INSERT INTO `flowstep` VALUES (25, 4, 1, '采购主管备件申请', '采购主管', '0', NULL, 'caigou/bjsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (26, 4, 2, '各单位审批', '采购主管指定', '1', NULL, 'caigou/bjsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (28, 5, 1, '辅料采购申请', '车间主任', '0', NULL, 'caigou/ylsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (29, 5, 2, '辅料采购申请审核', '辅料采购主管', '1', NULL, 'caigou/ylsqsh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (30, 6, 1, '备件入库申请', '各站录入备件入库单', '0', NULL, 'stockin/auditsockin.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (31, 6, 2, '备件入库审核', '站长审核后入库', '1', NULL, 'stockin/auditsockin.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (33, 7, 1, '设备入库申请', '各站巡检员', '0', NULL, 'eqp/auditruku.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (34, 7, 2, '设备入库审核', '站长审核', '1', NULL, 'eqp/auditruku.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (35, 8, 1, '设备安装申请', '各站巡检员', '0', NULL, 'anzhuang/auditanzhuang.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (36, 8, 2, '设备安装审核', '站长审核', '1', NULL, 'anzhuang/auditanzhuang.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (37, 9, 1, '设备更换申请', '各站巡检员', '0', NULL, 'change/auditchange.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (38, 9, 2, '设备更换审核', '站长审核', '1', NULL, 'change/auditchange.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (39, 10, 1, '设备报废申请', '各站巡检员', '0', NULL, 'baofei/auditbaofei.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (40, 10, 2, '设备报废审核', '站长审核', '1', NULL, 'baofei/auditbaofei.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (41, 11, 1, '销售订单任务发送', '地膜主管', '0', NULL, 'xsdingdan/auditxsdingdan.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (42, 11, 2, '销售订单任务接收', '地膜主任', '1', NULL, 'xsdingdan/auditxsdingdan.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (43, 12, 1, '销售订单任务发送', '包装膜主管', '0', NULL, 'xsdingdan/auditxsdingdan.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (44, 12, 2, '销售订单任务接收', '包装膜主任', '1', NULL, 'xsdingdan/auditxsdingdan.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (45, 13, 1, '发起对公付款通知', '史总', '0', NULL, 'caigou/fukuansh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (46, 13, 2, '对公付款', '财务', '1', NULL, 'caigou/fukuansh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (48, 14, 1, '发起其他付款通知', '史总', '0', NULL, 'caigou/fukuansh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `flowstep` VALUES (49, 14, 2, '其他付款', '办公室', '1', NULL, 'caigou/fukuansh.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for jiekuan
-- ----------------------------
DROP TABLE IF EXISTS `jiekuan`;
CREATE TABLE `jiekuan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `jkje` decimal(11, 2) NULL DEFAULT NULL COMMENT '借款金额',
  `jksy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事由',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiekuan
-- ----------------------------
INSERT INTO `jiekuan` VALUES (1, 11, 111.00, '111', NULL, '111', NULL, 1, '2019-11-22 14:30:42', NULL, '2019-11-22 14:30:42', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for kc_stockout
-- ----------------------------
DROP TABLE IF EXISTS `kc_stockout`;
CREATE TABLE `kc_stockout`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ccode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `ddate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `busstype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `csource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据来源',
  `bussid` int(11) NULL DEFAULT NULL COMMENT '对应业务单号',
  `whid` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `whuserid` int(11) NULL DEFAULT NULL COMMENT '库管',
  `whid2` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `deptid2` int(11) NULL DEFAULT NULL COMMENT '部门',
  `whuserid2` int(11) NULL DEFAULT NULL COMMENT '库管',
  `userid` int(11) NULL DEFAULT NULL COMMENT '职员',
  `userid2` int(11) NULL DEFAULT NULL COMMENT '职员',
  `cusid` int(11) NULL DEFAULT NULL COMMENT '客户',
  `venid` int(11) NULL DEFAULT NULL COMMENT '供应商',
  `orderid` int(11) NULL DEFAULT NULL COMMENT '订单',
  `invoice` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3，已用标注司机APP操作状态（已退货，已收款）',
  `invid` int(11) NULL DEFAULT NULL COMMENT '存货',
  `inum` decimal(11, 3) NULL DEFAULT NULL COMMENT '数量',
  `jian` int(11) NULL DEFAULT NULL COMMENT '件',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `zhuangcheid` int(11) NULL DEFAULT NULL COMMENT '装车id',
  `xsddtype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售订单类型',
  `tax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `thje` decimal(11, 2) NULL DEFAULT NULL COMMENT '退货金额',
  `itotal` decimal(11, 2) NULL DEFAULT NULL COMMENT '价税合计',
  `fkfs` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方式',
  `clbm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `yunfei` decimal(11, 2) NULL DEFAULT NULL COMMENT '运费',
  `yfdj` decimal(11, 2) NULL DEFAULT NULL COMMENT '其他',
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '订金',
  `ssje` decimal(11, 2) NULL DEFAULT NULL COMMENT '实收金额',
  `n01` decimal(11, 2) NULL DEFAULT NULL,
  `n02` decimal(11, 2) NULL DEFAULT NULL,
  `n03` decimal(11, 2) NULL DEFAULT NULL,
  `lat` decimal(10, 6) NULL DEFAULT NULL COMMENT '纬度',
  `lng` decimal(10, 6) NULL DEFAULT NULL COMMENT '经度',
  `sksj` datetime(0) NULL DEFAULT NULL COMMENT '收款时间',
  `printstatus` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印状态',
  `xianjin` decimal(11, 2) NULL DEFAULT NULL COMMENT '现金',
  `weixin` decimal(11, 2) NULL DEFAULT NULL COMMENT '微信',
  `zhifubao` decimal(11, 2) NULL DEFAULT NULL COMMENT '支付宝',
  `shouzhang` decimal(11, 2) NULL DEFAULT NULL COMMENT '收账',
  `qiankuan` decimal(11, 2) NULL DEFAULT NULL COMMENT '欠款',
  `shaofu` decimal(11, 2) NULL DEFAULT NULL COMMENT '少付',
  `duoxiao` decimal(11, 2) NULL DEFAULT NULL COMMENT '多销金额',
  `kouyufu` decimal(11, 2) NULL DEFAULT NULL COMMENT '扣预付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kc_stockouts
-- ----------------------------
DROP TABLE IF EXISTS `kc_stockouts`;
CREATE TABLE `kc_stockouts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父节点ID',
  `ksid` int(11) NULL DEFAULT NULL COMMENT '客商',
  `invid` int(11) NULL DEFAULT NULL COMMENT '存货',
  `cpgg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品规格',
  `ilen` decimal(11, 2) NULL DEFAULT NULL COMMENT '长度',
  `inum` decimal(11, 3) NULL DEFAULT NULL COMMENT '数量',
  `iprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '折扣',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `itax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `cbatch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批号',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `costprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '成本价',
  `costmoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '成本金额',
  `profit` decimal(11, 2) NULL DEFAULT NULL COMMENT '利润',
  `danwei` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `jian` decimal(11, 2) NULL DEFAULT NULL COMMENT '件',
  `jianzhong` decimal(11, 3) NULL DEFAULT NULL COMMENT '单件重量',
  `zhuangcheid` int(11) NULL DEFAULT NULL COMMENT '装车id',
  `mxwhid` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `perlen` decimal(11, 2) NULL DEFAULT NULL COMMENT '单件长度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `CRON_EXPRESSION` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `ENTRY_ID` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `JOB_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `LOCK_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('adminQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('adminQuartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('adminQuartzScheduler', 'iZ8vb4f4u7ud9pbw99lidvZ1574122065354', 1574347458193, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) NOT NULL DEFAULT 1 COMMENT '成功失败',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `createTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `createTime`(`createTime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logs
-- ----------------------------
INSERT INTO `sys_logs` VALUES (1, 1, '保存角色', 1, NULL, '2019-11-22 07:09:13');
INSERT INTO `sys_logs` VALUES (2, 1, '退出', 1, NULL, '2019-11-22 07:09:50');
INSERT INTO `sys_logs` VALUES (3, 1, '登陆', 1, NULL, '2019-11-22 07:09:52');
INSERT INTO `sys_logs` VALUES (4, 1, '保存角色', 1, NULL, '2019-11-22 07:12:50');
INSERT INTO `sys_logs` VALUES (5, 1, '退出', 1, NULL, '2019-11-22 10:58:50');
INSERT INTO `sys_logs` VALUES (6, 1, '登陆', 1, NULL, '2019-11-22 10:58:53');
INSERT INTO `sys_logs` VALUES (7, 1, '退出', 1, NULL, '2019-11-22 11:58:41');
INSERT INTO `sys_logs` VALUES (8, 1, '登陆', 1, NULL, '2019-11-22 11:59:00');
INSERT INTO `sys_logs` VALUES (9, 1, '登陆', 1, NULL, '2019-11-22 13:45:52');
INSERT INTO `sys_logs` VALUES (10, 1, '生成代码', 1, NULL, '2019-11-22 14:26:15');
INSERT INTO `sys_logs` VALUES (11, 1, '保存菜单', 1, NULL, '2019-11-22 14:30:09');
INSERT INTO `sys_logs` VALUES (12, 1, '保存角色', 1, NULL, '2019-11-22 14:30:26');

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `ccode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `val1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `val2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值2',
  `val3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值3',
  `isort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES (1, 0, '01', '系统参数', '', '', '', NULL, 1, '2018-06-15 03:47:53', 1, '2018-06-15 17:19:44', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (2, 0, '02', '基础参数', '', '', '', NULL, 1, '2018-06-15 03:49:35', NULL, '2018-06-15 03:49:35', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (3, 0, '03', '业务参数', '', '', '', NULL, 1, '2018-06-15 03:50:30', NULL, '2018-06-15 03:50:30', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (4, 0, '04', '其他参数', '', '', '', NULL, 1, '2018-06-15 03:50:40', NULL, '2018-06-15 03:50:40', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (5, 3, '0301', '地膜车间工资标准', '190', '190', '', NULL, 1, '2018-06-15 17:59:36', 9, '2019-11-15 08:40:57', NULL, NULL, NULL, NULL, '值1-一车间标准工资，值2-二车间标准工资', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (6, 3, '0302', '地膜验收时自动出库', '1', '0', '', NULL, 1, '2018-06-15 17:59:58', 1, '2019-02-05 09:50:14', NULL, NULL, NULL, NULL, '值1-原料自动出库(1是0否) 值2-辅料自动出库(1是0否)', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (7, 3, '0303', '', '', '', '', NULL, 1, '2018-06-15 18:02:00', 1, '2018-06-16 18:10:08', NULL, NULL, NULL, NULL, ' ', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (8, 3, '0304', '', '', '', '', NULL, 1, '2018-06-15 18:03:20', NULL, '2018-06-15 18:03:20', NULL, NULL, NULL, NULL, ' ', '', NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (9, 3, '0305', ' ', ' ', ' ', ' ', NULL, 1, '2018-06-24 21:22:34', NULL, '2018-06-24 21:22:37', NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (10, 3, '0306', ' ', ' ', ' ', ' ', NULL, 1, '2018-06-26 17:42:23', NULL, '2018-06-26 17:42:21', NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (11, 3, '0307', ' ', ' ', ' ', ' ', NULL, 1, '2018-06-26 19:07:15', NULL, '2018-06-26 19:07:18', NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (12, 1, '0101', ' APP版本信息', '1.6.8', '20191031司机退货多销及财务付款审核APP端更新', '/jgsl.apk', NULL, NULL, '2018-06-29 12:30:54', 2, '2019-10-31 07:10:47', NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (13, 3, '0308', ' ', '', '', '', NULL, 1, '2018-06-30 10:20:57', 1, '2019-10-11 10:20:45', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (14, 1, '0102', ' ', ' ', ' ', ' ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (15, 1, '0103', ' ', ' ', ' ', ' ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (16, 1, '0104', ' ', ' ', ' ', ' ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);
INSERT INTO `sys_params` VALUES (17, 1, '0105', ' ', ' ', ' ', ' ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `css` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cssapp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'APP样式',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hrefapp` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `typeapp` tinyint(4) NULL DEFAULT NULL COMMENT '所在终端类型 0-全部 1-WEB 2-APP',
  `permission` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `memo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 599 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '协同', 'layui-icon layui-icon-home', '', '', '', 1, 0, '', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-11-22 06:31:52');
INSERT INTO `sys_permission` VALUES (2, 58, '人员档案', 'fa-user', 'male.png', 'pages/user/userList.html', NULL, 1, 0, '', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-16 10:47:12');
INSERT INTO `sys_permission` VALUES (3, 2, '查询', '', 'todo.png', '', NULL, 2, 0, 'sys:user:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (4, 2, '新增', '', 'todo.png', '', NULL, 2, 0, 'sys:user:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, 7, '修改密码', 'fa-pencil-square-o', 'todo.png', 'pages/user/changePassword.html', NULL, 1, 1, 'sys:user:password', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-28 15:24:29');
INSERT INTO `sys_permission` VALUES (7, 0, '系统设置', 'layui-icon layui-icon-set-sm', 'todo.png', '', NULL, 1, 0, '', 200, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-30 11:02:48');
INSERT INTO `sys_permission` VALUES (8, 7, '菜单', 'fa-cog', 'todo.png', 'pages/menu/menuList.html', NULL, 1, 1, '', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, 8, '查询', '', 'todo.png', '', NULL, 2, 0, 'sys:menu:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, 8, '新增', '', 'todo.png', '', NULL, 2, 0, 'sys:menu:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, 8, '删除', '', 'todo.png', '', NULL, 2, 0, 'sys:menu:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, 7, '角色', 'fa-user-secret', 'todo.png', 'pages/role/roleList.html', NULL, 1, 1, '', 7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (13, 12, '查询', '', 'todo.png', '', NULL, 2, 0, 'sys:role:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, 12, '新增', '', 'todo.png', '', NULL, 2, 0, 'sys:role:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (15, 12, '删除', '', 'todo.png', '', NULL, 2, 0, 'sys:role:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (16, 7, '文件管理', 'fa-folder-open', 'todo.png', 'pages/file/fileList.html', NULL, 1, 1, '', 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (17, 16, '查询', '', 'todo.png', '', NULL, 2, 0, 'sys:file:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (18, 16, '删除', '', 'todo.png', '', NULL, 2, 0, 'sys:file:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (19, 7, '数据源监控', 'fa-eye', 'todo.png', 'druid/index.html', NULL, 1, 1, '', 9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (20, 7, '接口swagger', 'fa-file-pdf-o', 'todo.png', 'swagger-ui.html', NULL, 1, 1, '', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (21, 7, '代码生成', 'fa-wrench', 'todo.png', 'pages/generate/edit.html', NULL, 1, 1, 'generate:edit', 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (22, 7, '公告管理', 'fa-book', 'todo.png', 'pages/notice/noticeList.html', NULL, 1, 1, '', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (23, 22, '查询', '', 'todo.png', '', NULL, 2, 0, 'notice:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (24, 22, '添加', '', 'todo.png', '', NULL, 2, 0, 'notice:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (25, 22, '删除', '', 'todo.png', '', NULL, 2, 0, 'notice:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (26, 7, '日志查询', 'fa-reorder', 'todo.png', 'pages/log/logList.html', NULL, 1, 1, 'sys:log:query', 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (27, 7, '邮件管理', 'fa-envelope', 'todo.png', 'pages/mail/mailList.html', NULL, 1, 1, '', 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, 27, '发送邮件', '', 'todo.png', '', NULL, 2, 0, 'mail:send', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (29, 27, '查询', '', 'todo.png', '', NULL, 2, 0, 'mail:all:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (30, 7, '定时任务管理', 'fa-tasks', 'todo.png', 'pages/job/jobList.html', NULL, 1, 1, '', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (31, 30, '查询', '', 'todo.png', '', NULL, 2, 0, 'job:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (32, 30, '新增', '', 'todo.png', '', NULL, 2, 0, 'job:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (33, 30, '删除', '', 'todo.png', '', NULL, 2, 0, 'job:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (34, 7, 'excel导出', 'fa-arrow-circle-down', 'todo.png', 'pages/excel/sql.html', NULL, 1, 1, '', 16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, 34, '导出', '', 'todo.png', '', NULL, 2, 0, 'excel:down', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 34, '页面显示数据', '', 'todo.png', '', NULL, 2, 0, 'excel:show:datas', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (37, 7, '字典管理', 'fa-reddit', 'todo.png', 'pages/dict/dictList.html', NULL, 1, 1, '', 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (38, 37, '查询', '', 'todo.png', '', NULL, 2, 0, 'dict:query', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (39, 37, '新增', '', 'todo.png', '', NULL, 2, 0, 'dict:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (40, 37, '删除', '', 'todo.png', '', NULL, 2, 0, 'dict:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (41, 1, '待办事宜', 'fa-bookmark-o', 'todo.png', 'pages/todo/todoList.html', NULL, 1, 1, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-10 10:01:54');
INSERT INTO `sys_permission` VALUES (42, 1, '跟踪事宜', 'fa-bookmark', 'todo.png', 'pages/todo/trackList.html', NULL, 1, 0, '', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (43, 1, '通知公告', 'fa-bookmark-o', 'notice.png', 'pages/notice/noticePubList.html', '', 1, 0, '', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 15:41:09');
INSERT INTO `sys_permission` VALUES (44, 1, '我的消息', 'fa-comment-o', 'chatting.png', 'pages/pushmsg/pushmsgList.html', NULL, 1, 0, '', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-03 17:25:00');
INSERT INTO `sys_permission` VALUES (47, 0, '统计分析', 'layui-icon layui-icon-chart', 'todo.png', '', '', 1, 1, '', 80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-15 14:17:56');
INSERT INTO `sys_permission` VALUES (58, 0, '基础档案', 'layui-icon layui-icon-read', NULL, '', NULL, 1, 0, '', 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-14 13:35:30');
INSERT INTO `sys_permission` VALUES (59, 58, '组织架构', 'fa-sitemap', '', 'pages/dept/depttreeList.html', '', 1, 1, '', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-19 20:40:55');
INSERT INTO `sys_permission` VALUES (61, 58, '岗位管理', 'fa-user', 'todo.png', 'pages/position/positionList.html', 'position/addPosition.html', 2, 0, '', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 15:03:45');
INSERT INTO `sys_permission` VALUES (62, 58, '流程管理', 'fa-qrcode', 'todo.png', 'pages/flow/flowList.html', NULL, 1, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (71, 0, '中文栏目', 'fa-home', 'todo.png', '', NULL, 1, 0, '', 600, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (76, 71, '学院概况', '', 'todo.png', '', NULL, 1, 0, '', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (77, 76, '学院简介', '', 'todo.png', '/pages/cms/showdoc.html?pid=76&mid=77', NULL, 1, 0, '', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (96, 71, '新闻中心', 'fa-puzzle-piece', 'todo.png', '', NULL, 1, 0, '', 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (99, 96, '通知公告', '', 'todo.png', '/pages/cms/listdoc.html?pid=96&mid=99', NULL, 1, 0, '', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (140, 7, '中文信息发布', '', 'todo.png', 'pages/menu/menuchsList.html?pid=71', NULL, 1, 1, '', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-07-11 08:29:42');
INSERT INTO `sys_permission` VALUES (141, 7, '英文信息发布', '', 'todo.png', 'pages/menu/menuchsList.html?pid=72', '', 2, 1, '', 20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-29 15:04:19');
INSERT INTO `sys_permission` VALUES (148, 0, '设备管理', 'layui-icon layui-icon-util', NULL, '', NULL, 1, 0, '', 20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-20 09:27:02');
INSERT INTO `sys_permission` VALUES (151, 59, '查询', '', 'todo.png', '', NULL, 2, 0, 'sys:dept:query', 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (152, 59, '新增', '', 'todo.png', '', NULL, 2, 0, 'sys:dept:add', 20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (153, 59, '删除', '', 'todo.png', '', NULL, 2, 0, 'sys:dept:del', 30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (196, 71, '后台内容', '', 'todo.png', '', NULL, 2, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (199, 148, '维修单', '', 'todo.png', 'pages/repair/repairList.html', 'repair/addRepair.html', 1, 0, '', 50, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-19 15:51:57');
INSERT INTO `sys_permission` VALUES (200, 199, '查询本部', '', 'todo.png', '', '', 2, 0, 'sys:repair:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 07:21:56');
INSERT INTO `sys_permission` VALUES (201, 199, '新增', '', 'todo.png', '', NULL, 2, 0, 'sys:inspect:add', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (202, 199, '删除', '', 'todo.png', '', NULL, 2, 0, 'sys:inspect:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (203, 199, '查询所有用户', '', 'todo.png', '', '', 2, 0, 'sys:repair:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 07:22:17');
INSERT INTO `sys_permission` VALUES (204, 2, '查询本部', '', 'todo.png', '', '', 2, 0, 'sys:user:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2018-05-29 17:19:18', NULL, '2019-06-19 20:20:46');
INSERT INTO `sys_permission` VALUES (239, 58, '客户分类', '', 'todo.png', 'pages/customerclass/customerclassList.html', 'customerclass/addTCustomerclass.html', 1, 1, '', 20, NULL, NULL, NULL, NULL, NULL, NULL, '2018-07-27 09:28:30', NULL, '2019-07-19 16:02:08');
INSERT INTO `sys_permission` VALUES (245, 58, '客户档案', '', 'contact.png', 'pages/customer/customerList.html', '', 1, 0, '', 21, NULL, NULL, NULL, NULL, NULL, NULL, '2018-09-24 00:36:49', NULL, '2019-07-19 16:02:21');
INSERT INTO `sys_permission` VALUES (246, 233, '查询本站', '', 'todo.png', '', NULL, 2, 0, 'sys:repair:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2018-11-25 14:31:55', NULL, '2018-12-24 19:50:35');
INSERT INTO `sys_permission` VALUES (250, 47, '设备完好率', '', 'todo.png', 'pages/tongji/shebeiWhl.html', NULL, 1, 1, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-19 21:30:07', NULL, '2019-01-30 22:09:43');
INSERT INTO `sys_permission` VALUES (251, 47, '包装车间生产统计', '', 'todo.png', 'pages/plant/plantList.html', '', 1, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-19 21:31:13', NULL, '2019-08-06 19:21:59');
INSERT INTO `sys_permission` VALUES (252, 58, '系统参数', '', 'todo.png', 'pages/params/paramtreeList.html', NULL, 1, 0, '', 91, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-19 22:37:25', NULL, '2018-12-19 22:37:25');
INSERT INTO `sys_permission` VALUES (308, 2, '查询全部', '', NULL, '', NULL, 2, 0, 'sys:user:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-01-28 15:31:35', NULL, '2019-01-28 15:31:35');
INSERT INTO `sys_permission` VALUES (309, 0, '采购管理', 'layui-icon layui-icon-cart', 'todo.png', '', '', 1, 0, '', 3, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-16 11:13:25', NULL, '2019-08-02 10:55:37');
INSERT INTO `sys_permission` VALUES (312, 309, '原料订单', '', '', 'pages/caigou/ylddlist.html?ctype=1', 'caigou/ylddadd.html?ctype=1', 1, 0, '', 11, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-22 09:27:42', NULL, '2019-07-19 15:42:40');
INSERT INTO `sys_permission` VALUES (328, 58, '供应商分类', '', 'todo.png', 'pages/vendor/vendorclassList.html', '', 1, 1, '', 22, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-29 10:58:23', NULL, '2019-07-19 16:02:36');
INSERT INTO `sys_permission` VALUES (329, 58, '供应商档案', '', 'todo.png', 'pages/vendor/vendorList.html', '', 1, 0, '', 23, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-29 10:58:56', NULL, '2019-07-19 16:37:53');
INSERT INTO `sys_permission` VALUES (330, 309, '付款单', '', 'todo.png', 'pages/fukuan/fukuanshenqingList.html', '', 1, 0, '', 40, NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-09 00:48:30', NULL, '2019-10-30 12:49:16');
INSERT INTO `sys_permission` VALUES (336, 58, '机组档案', '', 'archive.png', 'pages/scjizu/scJizuList.html', '', 1, 1, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-17 11:55:37', NULL, '2019-10-15 13:39:00');
INSERT INTO `sys_permission` VALUES (346, 58, '料仓管理', '', 'mic.svg', 'pages/liaocang/scLiaocangList.html', '', 1, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-28 13:22:01', NULL, '2019-07-19 16:04:27');
INSERT INTO `sys_permission` VALUES (354, 312, '查询本人', '', '', '', '', 2, 0, 'sys:yldd:queryown', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-10 10:19:07', NULL, '2019-06-10 10:19:07');
INSERT INTO `sys_permission` VALUES (355, 312, '查询本部', '', '', '', '', 2, 0, 'sys:yldd:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-10 10:19:51', NULL, '2019-06-10 10:19:51');
INSERT INTO `sys_permission` VALUES (356, 312, '查询全部', '', '', '', '', 2, 0, 'sys:yldd:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-10 10:20:34', NULL, '2019-06-10 10:20:34');
INSERT INTO `sys_permission` VALUES (369, 148, '设备类别', '', '', 'pages/sblb/sblbList.html', 'sblb/addSblb.html', 1, 0, '', 10, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 06:13:41', NULL, '2019-06-11 06:13:41');
INSERT INTO `sys_permission` VALUES (370, 148, '设备档案', '', '', 'pages/eqp/equipmentList.html', 'eqp/addEquipment.html', 1, 0, '', 20, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 06:14:43', NULL, '2019-06-11 07:23:34');
INSERT INTO `sys_permission` VALUES (371, 370, '查询本部', '', '', '', '', 2, 0, 'sys:eqp:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 07:23:01', NULL, '2019-06-11 07:23:01');
INSERT INTO `sys_permission` VALUES (372, 370, '查询全部', '', '', '', '', 2, 0, 'sys:eqp:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-11 07:23:24', NULL, '2019-06-11 07:23:24');
INSERT INTO `sys_permission` VALUES (375, 225, '查询本人', '', '', '', '', 2, 0, 'sys:stockin:queryown', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-15 13:15:11', NULL, '2019-06-15 13:15:11');
INSERT INTO `sys_permission` VALUES (377, 224, '查询本人', '', '', '', '', 2, 0, 'sys:stocktran:queryown', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-15 13:22:26', NULL, '2019-06-15 13:22:26');
INSERT INTO `sys_permission` VALUES (382, 447, '出库单', '', '', 'pages/stockout/smlist.html?ctype=sm', 'stockout/addStockout.html', 1, 0, '', 62, NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-18 15:53:14', NULL, '2019-07-17 21:50:23');
INSERT INTO `sys_permission` VALUES (425, 148, '设备借用单', '', '', 'pages/borrow/zxBorrowList.html', '', 1, 0, '', 40, NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-09 12:50:33', NULL, '2019-07-09 12:50:33');
INSERT INTO `sys_permission` VALUES (522, 478, '客户分类', '', '', 'pages/customerclass/customerclassList.html', '', 1, 1, '', 21, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-04 03:59:29', NULL, '2019-10-04 03:59:29');
INSERT INTO `sys_permission` VALUES (523, 478, '供应商分类', '', '', 'pages/vendor/vendorclassList.html', '', 1, 1, '', 30, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-04 04:00:28', NULL, '2019-10-04 04:00:28');
INSERT INTO `sys_permission` VALUES (524, 478, '存货分类', '', '', 'pages/chfl/chflList.html', '', 1, 1, '', 40, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-04 04:03:58', NULL, '2019-10-28 11:00:06');
INSERT INTO `sys_permission` VALUES (525, 522, '查询本部', '', '', '', '', 2, 0, 'sys:customerClass:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-04 04:09:02', NULL, '2019-10-04 04:09:02');
INSERT INTO `sys_permission` VALUES (526, 522, '查询全部', '', '', '', '', 2, 0, 'sys:customerClass:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-04 04:09:34', NULL, '2019-10-04 04:09:34');
INSERT INTO `sys_permission` VALUES (534, 480, '查询本部', '', '', '', '', 2, 0, 'sys:customer:querydept', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-07 22:26:07', NULL, '2019-10-07 22:26:07');
INSERT INTO `sys_permission` VALUES (535, 480, '查询全部', '', '', '', '', 2, 0, 'sys:customer:queryall', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-07 22:26:33', NULL, '2019-10-07 22:26:33');
INSERT INTO `sys_permission` VALUES (536, 480, '编辑', '', '', '', '', 2, 0, 'sys:customer:edit', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-07 22:27:18', NULL, '2019-10-07 22:27:18');
INSERT INTO `sys_permission` VALUES (537, 480, '删除', '', '', '', '', 2, 0, 'sys:customer:del', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-07 22:27:41', NULL, '2019-10-07 22:27:41');
INSERT INTO `sys_permission` VALUES (538, 480, '开票', '', '', '', '', 2, 0, 'sys:customer:kaipiao', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-10-07 22:28:20', NULL, '2019-10-07 22:28:20');
INSERT INTO `sys_permission` VALUES (597, 309, '出库单', '', '', 'pages/stockout/smlist.html?ctype=sm', '', 1, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-11-22 06:35:26', NULL, '2019-11-22 06:35:26');
INSERT INTO `sys_permission` VALUES (598, 309, '借款', '', '', 'pages/jiekuan/jiekuanList.html', '', 1, 0, '', 100, NULL, NULL, NULL, NULL, NULL, NULL, '2019-11-22 14:30:09', NULL, '2019-11-22 14:30:09');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ADMIN', '管理员', '2017-05-01 13:25:39', '2019-11-22 14:30:25');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 29);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 33);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 37);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 41);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (1, 44);
INSERT INTO `sys_role_permission` VALUES (1, 47);
INSERT INTO `sys_role_permission` VALUES (1, 58);
INSERT INTO `sys_role_permission` VALUES (1, 59);
INSERT INTO `sys_role_permission` VALUES (1, 61);
INSERT INTO `sys_role_permission` VALUES (1, 140);
INSERT INTO `sys_role_permission` VALUES (1, 141);
INSERT INTO `sys_role_permission` VALUES (1, 148);
INSERT INTO `sys_role_permission` VALUES (1, 151);
INSERT INTO `sys_role_permission` VALUES (1, 152);
INSERT INTO `sys_role_permission` VALUES (1, 153);
INSERT INTO `sys_role_permission` VALUES (1, 199);
INSERT INTO `sys_role_permission` VALUES (1, 200);
INSERT INTO `sys_role_permission` VALUES (1, 201);
INSERT INTO `sys_role_permission` VALUES (1, 202);
INSERT INTO `sys_role_permission` VALUES (1, 239);
INSERT INTO `sys_role_permission` VALUES (1, 250);
INSERT INTO `sys_role_permission` VALUES (1, 251);
INSERT INTO `sys_role_permission` VALUES (1, 308);
INSERT INTO `sys_role_permission` VALUES (1, 309);
INSERT INTO `sys_role_permission` VALUES (1, 312);
INSERT INTO `sys_role_permission` VALUES (1, 354);
INSERT INTO `sys_role_permission` VALUES (1, 369);
INSERT INTO `sys_role_permission` VALUES (1, 370);
INSERT INTO `sys_role_permission` VALUES (1, 371);
INSERT INTO `sys_role_permission` VALUES (1, 425);
INSERT INTO `sys_role_permission` VALUES (1, 598);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `headImgUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telephone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门编码',
  `clientid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'APP设备ID',
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信公众号ID',
  `positionid` int(11) NULL DEFAULT NULL COMMENT '岗位',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `rzsj` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `zytc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业特长',
  `yuexin` decimal(11, 2) NULL DEFAULT NULL COMMENT '月薪',
  `jjlxr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '紧急联系人',
  `jjlxrdh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '紧急联系人电话',
  `zjzp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照片',
  `jlfj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简历附件',
  `zzzt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '在职状态',
  `lzsj` datetime(0) NULL DEFAULT NULL COMMENT '离职时间',
  `lzyy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '离职原因',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 216 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$nbnxJj8d7N4WQhREvvQOtup2oNiLHZf9q1ey65TuaBARqlni4umBG', '管理员', '/2019/05/30/7f1fba08344acca1aa3873e0cc1fb78f.jpg', '', '', '', '1998-06-30', 0, 1, '2017-04-10 15:21:38', '2019-05-18 11:50:00', 1, 'cffe72cb791aefa463f88cb2aa7051e9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_billcode
-- ----------------------------
DROP TABLE IF EXISTS `t_billcode`;
CREATE TABLE `t_billcode`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tablename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `colname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `txthead` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本',
  `datefmt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期格式',
  `subhead` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本',
  `codelen` int(11) NULL DEFAULT NULL COMMENT '流水长度',
  `codeval` int(11) NULL DEFAULT NULL COMMENT '当前值',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `deptcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `deptname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级部门ID',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `leader` int(11) NULL DEFAULT NULL COMMENT '负责人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '01', '运城市农商行', 0, '', '', NULL, '2019-06-10 09:15:32', '2019-06-10 09:15:32', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (2, '02', '盐湖区农商行', 0, '', '', NULL, '2019-03-20 15:41:12', '2019-03-20 15:41:12', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (3, '03', '永济市农商行', 0, '', '', NULL, '2019-03-20 15:41:32', '2019-03-20 15:41:32', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (4, '04', '河津市农商行', 0, '', '', NULL, '2019-03-20 15:41:45', '2019-03-20 15:41:45', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (5, '05', '临猗县农商行', 0, '', '', NULL, '2019-07-06 11:36:40', '2019-07-06 11:36:40', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (6, '06', '万荣县农商行', 0, '', '', NULL, '2019-03-20 15:42:17', '2019-03-20 15:42:17', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (7, '07', '稷山县农商行', 0, '', '', NULL, '2019-03-20 15:42:29', '2019-03-20 15:42:29', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (8, '08', '新绛县农商行', 0, '', '', NULL, '2019-03-20 15:42:37', '2019-03-20 15:42:37', NULL, NULL, '', NULL, NULL, NULL, '');
INSERT INTO `t_dept` VALUES (9, '09', '闻喜县农商行', 0, '', '', NULL, '2019-07-24 10:09:10', '2019-07-24 10:09:10', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (10, '10', '绛县农商行', 0, '', '', NULL, '2019-07-24 10:09:42', '2019-07-24 10:09:42', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (11, '11', '垣曲县农商行', 0, '', '', NULL, '2019-09-23 14:52:05', '2019-09-23 14:52:05', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (12, '12', '夏县农商行', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (13, '13', '平陆县农商行', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (14, '14', '芮城县农商行', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (16, '0201', '北相镇农商行', 2, '', '', NULL, '2019-11-22 06:51:58', '2019-11-22 06:51:58', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (17, '0202', '解州农商行', 2, '', '', NULL, '2019-11-22 06:52:44', '2019-11-22 06:52:44', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES (18, '020101', '会计科', 16, '', '', NULL, '2019-11-22 11:00:09', '2019-11-22 11:00:09', NULL, NULL, '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `k` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `val` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  `dwmark` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标识单位是不是标准件,0 不是 1 是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`, `k`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 'sex', '0', '女', '2017-11-17 09:58:24', '2017-11-18 14:21:05', NULL);
INSERT INTO `t_dict` VALUES (2, 'sex', '1', '男', '2017-11-17 10:03:46', '2017-11-17 10:03:46', NULL);
INSERT INTO `t_dict` VALUES (3, 'userStatus', '0', '无效', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (4, 'userStatus', '1', '正常', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (5, 'userStatus', '2', '锁定', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (6, 'noticeStatus', '0', '草稿', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (7, 'noticeStatus', '1', '发布', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (8, 'isRead', '0', '未读', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (9, 'isRead', '1', '已读', '2017-11-17 16:26:06', '2017-11-17 16:26:09', NULL);
INSERT INTO `t_dict` VALUES (14, 'rukutype', '1', '采购入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (15, 'rukutype', '2', '调拨入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (16, 'rukutype', '3', '退料入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (17, 'rukutype', '4', '备件入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (18, 'rukutype', '5', '返修件入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (19, 'rukutype', '6', '其他入库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (20, 'chukutype', '1', '销售出库', '2018-08-13 15:58:13', '2018-08-13 15:58:13', NULL);
INSERT INTO `t_dict` VALUES (26, 'chukutype', '2', '调拨出库', '2018-08-13 16:02:10', '2018-08-13 16:02:10', NULL);
INSERT INTO `t_dict` VALUES (27, 'chukutype', '3', '领料出库', '2018-08-13 16:02:10', '2018-08-13 16:02:10', NULL);
INSERT INTO `t_dict` VALUES (28, 'chukutype', '4', '其他出库', '2018-08-13 16:02:10', '2018-08-13 16:02:10', NULL);
INSERT INTO `t_dict` VALUES (48, 'cgjhtype', '1', '普通采购', '2019-01-30 22:33:11', '2019-01-30 22:33:13', NULL);
INSERT INTO `t_dict` VALUES (49, 'cgjhtype', '2', '紧急采购', '2019-01-30 22:33:11', '2019-01-30 22:33:13', NULL);
INSERT INTO `t_dict` VALUES (50, 'cgjhtype', '3', '其他采购', '2019-01-30 22:33:11', '2019-01-30 22:33:13', NULL);
INSERT INTO `t_dict` VALUES (51, 'cgddtype', '1', '普通订单', '2019-01-30 22:33:11', '2019-01-30 22:33:13', NULL);
INSERT INTO `t_dict` VALUES (52, 'cgddtype', '2', '紧急订单', '2019-03-29 11:16:05', '2019-03-29 11:16:06', NULL);
INSERT INTO `t_dict` VALUES (53, 'cgddtype', '3', '其他订单', '2019-03-29 11:16:16', '2019-03-29 11:16:18', NULL);
INSERT INTO `t_dict` VALUES (54, 'cgdhtype', '1', '订单到货', '2019-03-29 11:16:28', '2019-03-29 11:16:29', NULL);
INSERT INTO `t_dict` VALUES (55, 'cgdhtype', '2', '其他到货', '2019-04-27 13:40:37', '2019-04-27 13:40:37', NULL);
INSERT INTO `t_dict` VALUES (56, 'fktype', '1', '现金', '2019-04-27 13:46:07', '2019-07-10 22:32:03', NULL);
INSERT INTO `t_dict` VALUES (57, 'fktype', '2', '转账', '2019-05-04 16:00:47', '2019-07-10 22:32:28', NULL);
INSERT INTO `t_dict` VALUES (58, 'xsddtype', '1', '普通订单', '2019-01-30 22:33:11', '2019-01-30 22:33:13', NULL);
INSERT INTO `t_dict` VALUES (59, 'xsddtype', '2', '紧急订单', '2019-03-29 11:16:05', '2019-03-29 11:16:06', NULL);
INSERT INTO `t_dict` VALUES (60, 'xsddtype', '3', '其他订单', '2019-03-29 11:16:16', '2019-03-29 11:16:18', NULL);
INSERT INTO `t_dict` VALUES (61, 'sktype', '1', '普通收款', '2019-05-04 16:05:11', '2019-05-04 16:05:12', NULL);
INSERT INTO `t_dict` VALUES (62, 'sktype', '2', '其他收款', '2019-05-04 16:05:26', '2019-05-04 16:05:28', NULL);
INSERT INTO `t_dict` VALUES (63, 'whtype', '1', '原料库', '2019-05-18 00:31:47', '2019-05-18 00:31:47', NULL);
INSERT INTO `t_dict` VALUES (64, 'whtype', '2', '产品库', '2019-05-18 00:32:01', '2019-05-18 00:32:01', NULL);
INSERT INTO `t_dict` VALUES (65, 'whtype', '3', '半成品库', '2019-05-18 00:32:14', '2019-05-18 00:32:14', NULL);
INSERT INTO `t_dict` VALUES (66, 'whtype', '4', '废品库', '2019-05-18 00:32:25', '2019-05-18 00:32:25', NULL);
INSERT INTO `t_dict` VALUES (67, 'whtype', '5', '备品库', '2019-05-18 00:32:38', '2019-05-18 00:32:38', NULL);
INSERT INTO `t_dict` VALUES (68, 'whtype', '6', '备件库', '2019-05-18 00:32:48', '2019-05-18 00:32:48', NULL);
INSERT INTO `t_dict` VALUES (69, 'whtype', '7', '其他', '2019-05-18 00:32:56', '2019-05-18 00:32:56', NULL);
INSERT INTO `t_dict` VALUES (70, 'stockintype', '1', '采购入库', '2019-06-16 15:22:37', '2019-06-16 15:22:37', NULL);
INSERT INTO `t_dict` VALUES (71, 'stockintype', '2', '生产入库', '2019-06-16 15:22:49', '2019-06-16 15:22:49', NULL);
INSERT INTO `t_dict` VALUES (72, 'stockintype', '3', '退货入库', '2019-06-16 15:23:13', '2019-06-16 15:23:13', NULL);
INSERT INTO `t_dict` VALUES (73, 'stockintype', '4', '其他入库', '2019-06-16 15:23:54', '2019-06-16 15:23:54', NULL);
INSERT INTO `t_dict` VALUES (74, 'stockouttype', '1', '销售出库', '2019-06-16 15:24:42', '2019-06-16 15:24:42', NULL);
INSERT INTO `t_dict` VALUES (75, 'stockouttype', '2', '领料出库', '2019-06-16 15:25:06', '2019-06-16 15:25:06', NULL);
INSERT INTO `t_dict` VALUES (76, 'stockouttype', '3', '其他出库', '2019-06-16 15:25:24', '2019-06-16 15:25:24', NULL);
INSERT INTO `t_dict` VALUES (77, 'fhfs', '1', '无', '2019-06-30 15:35:26', '2019-07-14 19:42:47', NULL);
INSERT INTO `t_dict` VALUES (78, 'fhfs', '2', '无溶剂', '2019-06-30 15:35:41', '2019-07-14 19:42:37', NULL);
INSERT INTO `t_dict` VALUES (79, 'custype', '1', '普通客户', '2019-07-08 19:06:24', '2019-07-08 19:06:24', NULL);
INSERT INTO `t_dict` VALUES (80, 'custype', '2', '批发客户', '2019-07-08 19:06:33', '2019-07-08 19:06:33', NULL);
INSERT INTO `t_dict` VALUES (81, 'custype', '3', '配货客户', '2019-07-08 19:07:00', '2019-07-08 19:07:00', NULL);
INSERT INTO `t_dict` VALUES (82, 'custype', '4', '零售客户', '2019-07-08 19:07:24', '2019-07-08 19:07:24', NULL);
INSERT INTO `t_dict` VALUES (83, 'danwei', 'kg', 'kg', '2019-07-09 01:27:39', '2019-07-09 01:27:39', NULL);
INSERT INTO `t_dict` VALUES (84, 'danwei', '件', '件', '2019-07-09 01:27:58', '2019-07-09 01:27:58', '1');
INSERT INTO `t_dict` VALUES (85, 'danwei', '卷', '卷', '2019-07-09 01:28:15', '2019-07-09 01:28:15', NULL);
INSERT INTO `t_dict` VALUES (86, 'danwei', '条', '条', '2019-07-09 01:29:16', '2019-07-09 01:29:16', NULL);
INSERT INTO `t_dict` VALUES (87, 'danwei', '个', '个', '2019-07-09 01:29:37', '2019-07-09 01:29:37', '1');
INSERT INTO `t_dict` VALUES (88, 'danwei', '套', '套', '2019-07-10 19:51:13', '2019-07-10 19:51:13', NULL);
INSERT INTO `t_dict` VALUES (89, 'fktype', '3', '支付宝', '2019-07-10 22:32:43', '2019-07-10 22:32:43', NULL);
INSERT INTO `t_dict` VALUES (90, 'fktype', '4', '微信', '2019-07-10 22:32:56', '2019-07-10 22:32:56', NULL);
INSERT INTO `t_dict` VALUES (91, 'guanxin', '1', '普通', '2019-07-11 14:03:44', '2019-07-11 14:03:44', NULL);
INSERT INTO `t_dict` VALUES (92, 'guanxin', '2', '加粗', '2019-07-11 14:04:15', '2019-07-11 14:04:15', NULL);
INSERT INTO `t_dict` VALUES (93, 'guanxin', '3', '塑料', '2019-07-11 14:05:29', '2019-07-11 14:05:29', NULL);
INSERT INTO `t_dict` VALUES (94, 'fhfs', '3', '干式复合', '2019-07-14 19:42:24', '2019-07-14 19:42:24', NULL);
INSERT INTO `t_dict` VALUES (95, 'kctype', '1', '库存', '2019-07-20 10:18:48', '2019-07-20 10:18:57', NULL);
INSERT INTO `t_dict` VALUES (96, 'kctype', '2', '出库明细', '2019-07-20 10:18:49', '2019-07-20 10:18:58', NULL);
INSERT INTO `t_dict` VALUES (97, 'kctype', '3', '出库汇总', '2019-07-20 10:18:52', '2019-07-20 10:18:59', NULL);
INSERT INTO `t_dict` VALUES (98, 'kctype', '4', '入库明细', '2019-07-20 10:18:53', '2019-07-20 10:19:01', NULL);
INSERT INTO `t_dict` VALUES (99, 'kctype', '5', '入库汇总', '2019-07-20 10:18:54', '2019-07-20 10:19:02', NULL);
INSERT INTO `t_dict` VALUES (100, 'danwei', '片', '片', '2019-07-23 15:28:12', '2019-07-23 15:28:12', NULL);
INSERT INTO `t_dict` VALUES (101, 'danwei', '米', '米', '2019-07-23 15:29:01', '2019-07-23 15:29:01', '2');
INSERT INTO `t_dict` VALUES (102, 'stockouttype', '4', '自动出库', '2019-07-24 23:05:03', '2019-07-24 23:05:03', NULL);
INSERT INTO `t_dict` VALUES (103, 'smxsddtype', '1', '普通订单', '2019-08-01 09:44:38', '2019-08-01 09:44:40', NULL);
INSERT INTO `t_dict` VALUES (104, 'smxsddtype', '2', '配送订单', '2019-08-01 09:45:51', '2019-08-01 09:45:53', NULL);
INSERT INTO `t_dict` VALUES (105, 'smxsddtype', '3', '批发订单', '2019-08-01 09:46:11', '2019-08-01 09:46:14', NULL);
INSERT INTO `t_dict` VALUES (106, 'smstockintype', '1', '采购入库', '2019-08-08 03:36:36', '2019-08-08 03:36:38', NULL);
INSERT INTO `t_dict` VALUES (107, 'smstockintype', '3', '退货入库', '2019-08-08 03:36:53', '2019-08-08 03:36:54', NULL);
INSERT INTO `t_dict` VALUES (108, 'fktype', '5', '欠款', '2019-08-13 09:42:32', '2019-08-13 09:42:34', NULL);
INSERT INTO `t_dict` VALUES (109, 'danwei', '平方', '平方', '2019-08-19 12:31:21', '2019-08-19 12:31:21', NULL);
INSERT INTO `t_dict` VALUES (110, 'stockintype', '5', '盘点报溢入库', '2019-08-26 22:16:03', '2019-08-26 22:16:03', NULL);
INSERT INTO `t_dict` VALUES (111, 'stockouttype', '5', '盘点报损出库', '2019-08-26 22:16:36', '2019-08-26 22:16:36', NULL);
INSERT INTO `t_dict` VALUES (112, 'guanxin', '4', '塑料加粗', '2019-09-03 21:01:58', '2019-09-03 21:01:58', NULL);
INSERT INTO `t_dict` VALUES (113, 'stockintype', '6', '加工入库', '2019-09-06 16:07:19', '2019-09-06 16:07:19', NULL);
INSERT INTO `t_dict` VALUES (114, 'stockouttype', '6', '委外出库', '2019-09-06 16:08:22', '2019-09-06 16:08:22', NULL);
INSERT INTO `t_dict` VALUES (115, 'fktype', '6', '承兑', '2019-09-06 16:23:32', '2019-09-06 16:23:32', NULL);
INSERT INTO `t_dict` VALUES (116, 'guanxin', '5', '普通单刀', '2019-09-16 20:50:17', '2019-09-16 20:50:17', NULL);
INSERT INTO `t_dict` VALUES (117, 'guanxin', '6', '塑料单刀', '2019-09-16 20:50:36', '2019-09-16 20:50:36', NULL);
INSERT INTO `t_dict` VALUES (118, 'kctype', '6', '库存汇总', '2019-07-20 10:18:54', '2019-07-20 10:19:02', NULL);
INSERT INTO `t_dict` VALUES (119, 'kctype', '7', '库存品名汇总', '2019-10-03 06:40:51', '2019-10-03 06:40:51', NULL);

-- ----------------------------
-- Table structure for t_inv
-- ----------------------------
DROP TABLE IF EXISTS `t_inv`;
CREATE TABLE `t_inv`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `xtid` int(11) NULL DEFAULT NULL COMMENT '系统分类',
  `lbid` int(11) NULL DEFAULT NULL COMMENT '设备类别',
  `invcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存货代码',
  `invname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存货名称',
  `invabbname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存货简称',
  `invstd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `invcid` int(11) NULL DEFAULT NULL COMMENT '存货分类编码',
  `positionid` int(11) NULL DEFAULT NULL COMMENT '货位',
  `iweight` decimal(11, 2) NULL DEFAULT NULL COMMENT '单位重量',
  `ivolume` decimal(11, 2) NULL DEFAULT NULL COMMENT '单位体积',
  `iprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '零售价',
  `viprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '批发价',
  `icost` decimal(11, 2) NULL DEFAULT NULL COMMENT '配送价',
  `safenum` decimal(11, 2) NULL DEFAULT NULL COMMENT '安全库存',
  `topnum` decimal(11, 2) NULL DEFAULT NULL COMMENT '最高库存',
  `lownum` decimal(11, 2) NULL DEFAULT NULL COMMENT '最低库存',
  `unit1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主单位',
  `unit2` int(11) NULL DEFAULT NULL COMMENT '辅单位',
  `pic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品照片',
  `barcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级节点',
  `bomid` int(11) NULL DEFAULT NULL COMMENT 'BOM清单',
  `tdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '故障描述',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `ppath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类位置',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `printgg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印时规格显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_invclass
-- ----------------------------
DROP TABLE IF EXISTS `t_invclass`;
CREATE TABLE `t_invclass`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `ccode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `barcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `tdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '故障描述',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_invclass
-- ----------------------------
INSERT INTO `t_invclass` VALUES (1, 0, '01', '原材料', '', '', 1, '2018-08-09 07:44:51', 1, '2019-05-31 14:22:23', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cron` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `springBeanName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'springBean名',
  `methodName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法名',
  `isSysJob` tinyint(1) NOT NULL COMMENT '是否是系统job',
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `jobName`(`jobName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正文',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_mail_to
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_to`;
CREATE TABLE `t_mail_to`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mailId` int(11) NOT NULL,
  `toUser` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1成功，0失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (2, '9月26日更新日志', '<p>1.&nbsp;工资添加导出按钮，管工资列宽调整</p><p>2.&nbsp;添加库存明细查询，在库存管理中点【库存明细账】，可查询某个库，某个产品出入库变化及期初期末余额</p><p>3.&nbsp;包装膜双电脑同步卷号</p><p>4.&nbsp;APP装车添加已出库选项</p>', 1, '2019-09-27 07:53:58', '2019-09-27 07:53:58');
INSERT INTO `t_notice` VALUES (3, '2019-10-05系统更新日志', '<p>1.&nbsp;地膜、管、包装月工资打印</p><p>2.&nbsp;商品管理添加及编辑使用弹框方式</p><p>3. 调整【预装车单】页面，添加司机、线路等查询条件</p><p>4. 【商贸基础档案】下添加商品分类、客户分类、供应商分类菜单</p><p>5. 【管库装车APP】盘管扫码、黑管拆分及配货装车更新</p><p>6.&nbsp;各车间【申请派车】功能完善，各车间提交申请，自动带出客户、重量等，商贸审批派车申请，并统一进行装车管理（统一装车功能暂未完成）</p><p>7. 【零售自提】现金收款，在订单审核时，将现金实收金额写入备用金普通收入，经手人记为审核人，弃审时记一个支出；</p><p>8. 【商贸销售订单】已打印单据禁止删除、禁止编辑、禁止弃审（暂未开启该限制）</p><p><br></p>', 1, '2019-10-04 08:19:28', '2019-10-05 07:53:14');
INSERT INTO `t_notice` VALUES (4, '2019-10-06系统更新日志', '<p>1.&nbsp;管车间添加四类造类配方，验收时自动下各废品库，并入地膜或PE100原料库</p><p>2.&nbsp;包装车间添加客户销售月对账单，可导出对账EXCEL</p><p>3.&nbsp;商贸订单审核后可以修改，避免了为多装货物弃审订单，造成装车状态异常</p><p>&nbsp; &nbsp; &nbsp;商贸订单在装车状态下多装物料时，保存车号，修正目前追加的物料没有车号问题</p><p>4.&nbsp;管、地膜商贸出库打印出库单功能，打印明细已完成，按线路汇总晚上更新（这几天数据汇总后行数还是很多，只少了一两行）</p>', 1, '2019-10-06 08:12:43', '2019-10-06 08:12:43');
INSERT INTO `t_notice` VALUES (5, '紧急通知', '<p>所有部门，全部整理卫生</p>  \n					', 1, '2019-10-29 10:03:12', '2019-10-29 10:03:34');

-- ----------------------------
-- Table structure for t_notice_read
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_read`;
CREATE TABLE `t_notice_read`  (
  `noticeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  PRIMARY KEY (`noticeId`, `userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_position
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `positioncode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位编码',
  `positionname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位说明',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门编码',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级岗位ID',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO `t_position` VALUES (5, '001', '董事长', '', 1, NULL, '2019-03-24 18:14:52', '2019-03-24 18:14:52', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (6, '002', '生产副总', '', 1, NULL, '2019-03-24 18:15:03', '2019-03-24 18:15:03', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (7, '003', '销售副总', '', 1, NULL, '2019-03-24 18:15:14', '2019-03-24 18:15:14', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (8, '004', '车间主任', '', NULL, NULL, '2019-03-24 18:17:54', '2019-03-24 18:17:54', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (9, '005', '业务员', '', NULL, NULL, '2019-03-24 18:18:06', '2019-03-24 18:18:06', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (10, '006', '库管', '', NULL, NULL, '2019-03-24 18:19:55', '2019-03-24 18:19:55', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (11, '007', '车队队长', '', NULL, NULL, '2019-03-24 18:20:08', '2019-03-24 18:20:08', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (12, '008', '司机', '', NULL, NULL, '2019-03-24 18:20:19', '2019-03-24 18:20:19', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (13, '011', '操作工1级', '', NULL, NULL, '2019-05-18 12:03:11', '2019-05-18 12:03:11', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (14, '012', '操作工2级', '', NULL, NULL, '2019-05-18 12:03:27', '2019-05-18 12:03:27', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (15, '013', '操作工3级', '', NULL, NULL, '2019-05-18 12:03:40', '2019-05-18 12:03:40', NULL, '', NULL, NULL, NULL);
INSERT INTO `t_position` VALUES (16, '014', '操作工4级', '', NULL, NULL, '2019-05-18 12:03:59', '2019-05-18 12:03:59', NULL, '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_pushmsg
-- ----------------------------
DROP TABLE IF EXISTS `t_pushmsg`;
CREATE TABLE `t_pushmsg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `userid` int(11) NULL DEFAULT NULL COMMENT '接收人',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务处理URL',
  `bizid` int(11) NULL DEFAULT NULL COMMENT '业务ID',
  `todoid` int(11) NULL DEFAULT NULL COMMENT '审批ID',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '手机推送消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `val` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'LoginUser的json串',
  `expireTime` datetime(0) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_vendor
-- ----------------------------
DROP TABLE IF EXISTS `t_vendor`;
CREATE TABLE `t_vendor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ccode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `abbname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `cid` int(11) NULL DEFAULT NULL COMMENT '分类',
  `venaddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `venpostcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `venregcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册码',
  `venbank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行',
  `venaccount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `devdate` datetime(0) NULL DEFAULT NULL COMMENT '开发日期',
  `venperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `venphone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `venfax` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `venemail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `legalperson` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `pic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片',
  `barcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `tdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `ppath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类位置',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_vendor
-- ----------------------------
INSERT INTO `t_vendor` VALUES (4, '22020101', '德鑫7', '', 3, '', '1', '2019-05-31 14:05:00', '1', '2019-05-31 14:05:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'cj', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_vendorclass
-- ----------------------------
DROP TABLE IF EXISTS `t_vendorclass`;
CREATE TABLE `t_vendorclass`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `ccode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类代码',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `barcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `tdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '往来单位分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_vendorclass
-- ----------------------------
INSERT INTO `t_vendorclass` VALUES (1, 0, '03', '原料供应商', '', '', 1, '2019-03-21 14:34:46', 1, '2019-05-17 23:12:10', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL);
INSERT INTO `t_vendorclass` VALUES (5, 0, '01', '商贸供应商', NULL, NULL, 98, '2019-07-07 14:47:47', NULL, '2019-07-07 14:47:47', NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, 6);

-- ----------------------------
-- Table structure for zc_baofei
-- ----------------------------
DROP TABLE IF EXISTS `zc_baofei`;
CREATE TABLE `zc_baofei`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ccode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `ddate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `busstype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `csource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据来源',
  `bussid` int(11) NULL DEFAULT NULL COMMENT '对应业务单号',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门',
  `bfyuanyin` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报废原因',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zc_baofeis
-- ----------------------------
DROP TABLE IF EXISTS `zc_baofeis`;
CREATE TABLE `zc_baofeis`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) NOT NULL COMMENT '主表ID',
  `eqpid` int(11) NULL DEFAULT NULL COMMENT '设备ID',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '使用部门id',
  `newdeptid` int(11) NULL DEFAULT NULL COMMENT '新使用部门ID',
  `glbmid` int(11) NULL DEFAULT NULL COMMENT '管理部门ID',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zx_borrow
-- ----------------------------
DROP TABLE IF EXISTS `zx_borrow`;
CREATE TABLE `zx_borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptid` int(11) NULL DEFAULT NULL COMMENT '单位ID',
  `whid` int(11) NULL DEFAULT NULL COMMENT '入库',
  `bizdate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `eqpid` int(11) NULL DEFAULT NULL COMMENT '设备ID',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借用说明',
  `quantity` decimal(8, 2) NULL DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(8, 2) NULL DEFAULT NULL COMMENT '单价',
  `money` decimal(8, 2) NULL DEFAULT NULL COMMENT '金额',
  `expectreturn` datetime(0) NULL DEFAULT NULL COMMENT '预计归还时间',
  `returndate` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `returndesc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归还说明',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `whid2` int(11) NULL DEFAULT NULL COMMENT '借出仓库',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备借用单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_borrow
-- ----------------------------
INSERT INTO `zx_borrow` VALUES (16, 6, NULL, '2019-08-30 00:00:00', 54, '稷山秦赋工地使用', NULL, '胡大民', NULL, NULL, '2019-09-03 00:00:00', NULL, '', '1', '', NULL, NULL, 98, '2019-08-30 12:39:20', 98, '2019-09-18 07:43:40', 98, '2019-09-18 14:39:36', '13834101346', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_borrow` VALUES (18, 6, NULL, '2019-09-18 00:00:00', 49, '韩阳镇长旺村', NULL, '裴增录', NULL, 0.00, NULL, NULL, NULL, '1', '', NULL, NULL, 98, '2019-09-18 14:50:03', NULL, '2019-09-18 14:50:03', 98, '2019-09-18 14:50:03', '152155522555', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for zx_borrows
-- ----------------------------
DROP TABLE IF EXISTS `zx_borrows`;
CREATE TABLE `zx_borrows`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父节点ID',
  `invid` int(11) NULL DEFAULT NULL COMMENT '存货',
  `inum` decimal(11, 2) NULL DEFAULT NULL COMMENT '数量',
  `iprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '折扣',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `itax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `cbatch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批号',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `eqpid` int(11) NULL DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_borrows
-- ----------------------------
INSERT INTO `zx_borrows` VALUES (2, 10, 531, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '', NULL, '硬盘', '希捷4T', '', NULL);
INSERT INTO `zx_borrows` VALUES (3, 11, 554, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '', NULL, '球机', 'AB', '', NULL);
INSERT INTO `zx_borrows` VALUES (4, 12, 599, 4.00, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '', NULL, '车检器', 'MTC车检器+底座', '', NULL);
INSERT INTO `zx_borrows` VALUES (5, 13, 566, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, '', NULL, 'LED补光灯', '', '', NULL);

-- ----------------------------
-- Table structure for zx_equipment
-- ----------------------------
DROP TABLE IF EXISTS `zx_equipment`;
CREATE TABLE `zx_equipment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `xtid` int(11) NULL DEFAULT NULL COMMENT '系统分类',
  `lbid` int(11) NULL DEFAULT NULL COMMENT '设备类别',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '所属站',
  `xjqyid` int(11) NULL DEFAULT NULL COMMENT '巡检区域',
  `whqyid` int(11) NULL DEFAULT NULL COMMENT '维护区域',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级节点',
  `isort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `serialno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `etype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `factoryid` int(11) NULL DEFAULT NULL COMMENT '厂家id',
  `buildtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `techphone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术支持电话',
  `techname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术支持名称',
  `qualityperiod` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `maintainperiod` int(11) NULL DEFAULT NULL,
  `maintainbiao1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `maintainbiao2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `maintainbiao3` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `useperiod` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `equipmentcontent` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `equipmentstatus` int(11) NULL DEFAULT NULL,
  `adminid` int(11) NULL DEFAULT NULL,
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `isxun` int(11) NULL DEFAULT NULL,
  `iswei` int(11) NULL DEFAULT NULL,
  `tdesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '故障描述',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `whid` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  `pinpai` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `anzhuang` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安装单位',
  `zhuangfx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桩号方向',
  `zhuangfw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桩号方位',
  `zhuangwz` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桩号位置',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_equipment
-- ----------------------------
INSERT INTO `zx_equipment` VALUES (3, NULL, NULL, 4, 2, NULL, '直管机1号机', NULL, NULL, '', '', NULL, '', '', '', '', NULL, '', '', '', '', '', NULL, NULL, '2019-11-22 14:48:33', NULL, NULL, '', 3, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', '20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (4, NULL, NULL, 5, 2, NULL, '直管机2号机', NULL, NULL, '', '', NULL, '', '', '', '', NULL, '', '', '', '', '', NULL, NULL, '2019-11-22 14:48:50', NULL, NULL, '', 3, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (26, NULL, NULL, 4, 3, NULL, '吹塑机3号SJ90', NULL, NULL, '', '', NULL, '', '', '', '', NULL, '', '', '', '', '', NULL, NULL, '2019-11-22 14:49:18', NULL, NULL, '', 3, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (29, NULL, NULL, 3, NULL, NULL, '吹塑机6号SJ75', NULL, NULL, '', '', NULL, '', '', '', '', NULL, '', '', '', '', '', NULL, NULL, '2019-06-11 10:46:00', NULL, NULL, '', 3, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', NULL, '2019-06-11 10:50:00', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (48, NULL, NULL, 6, NULL, NULL, '热熔机', NULL, NULL, '1', '63-200四环手推热熔机', NULL, '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2019-09-04 15:29:51', NULL, NULL, '', 98, '2019-07-09 08:53:39', 98, '2019-09-04 15:29:52', NULL, NULL, '正常', NULL, '', NULL, NULL, NULL, NULL, NULL, 1000.00, '郑州金桥', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (49, NULL, NULL, 6, NULL, NULL, '热熔机', NULL, NULL, '2', '63-160手摇四环', NULL, '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2019-07-09 12:32:48', NULL, NULL, '', 98, '2019-07-09 08:54:58', NULL, '2019-07-09 08:54:58', NULL, NULL, '借用', NULL, '', NULL, NULL, NULL, NULL, NULL, 2000.00, '德瑞宝', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_equipment` VALUES (52, NULL, NULL, 6, NULL, NULL, '热熔机', NULL, NULL, '5', '63-160手摇四环', NULL, '', '', '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '2019-07-09 12:32:49', NULL, NULL, '', 98, '2019-07-09 08:57:29', NULL, '2019-07-09 08:57:29', NULL, NULL, '正常', NULL, '', NULL, NULL, NULL, NULL, NULL, 800.00, '浙江辉达', '', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for zx_repair
-- ----------------------------
DROP TABLE IF EXISTS `zx_repair`;
CREATE TABLE `zx_repair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xtid` int(11) NULL DEFAULT NULL COMMENT '系统分类',
  `lbid` int(11) NULL DEFAULT NULL COMMENT '设备类别',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '所属站',
  `xjqyid` int(11) NULL DEFAULT NULL COMMENT '巡检区域',
  `whqyid` int(11) NULL DEFAULT NULL COMMENT '维护区域',
  `bizdate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `eqpid` int(11) NULL DEFAULT NULL COMMENT '设备ID',
  `eqpcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编码',
  `eqpname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '损坏情况',
  `descpic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '损坏情况照片',
  `repair` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修复情况',
  `repairman` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修人',
  `repaircost` decimal(10, 2) NULL DEFAULT NULL COMMENT '维修费',
  `material` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用耗材及数量',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  `inspid` int(11) NULL DEFAULT NULL COMMENT '巡查ID',
  `flowid` int(11) NULL DEFAULT NULL COMMENT '流程',
  `stepid` int(11) NULL DEFAULT NULL COMMENT '节点',
  `gzid` int(11) NULL DEFAULT NULL COMMENT '故障ID',
  `gzmc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '故障名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 307 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_repair
-- ----------------------------
INSERT INTO `zx_repair` VALUES (156, 1, NULL, 32, 394, NULL, '2019-01-08 00:00:00', 1178, NULL, NULL, '经排查，因抓拍摄像机继电器故障，导致前线圈无车', '0', NULL, NULL, NULL, '1', '1', NULL, '', NULL, '1', 38, '2019-02-02 15:50:17', NULL, '2019-02-02 15:50:17', 38, '2019-02-02 15:50:17', NULL, NULL, NULL, NULL, 1, 2, 0, '前线圈不显示车辆');
INSERT INTO `zx_repair` VALUES (157, 1, NULL, 32, 402, NULL, '2019-01-22 00:00:00', 978, NULL, NULL, '经排查，票据打印机故障', '0', NULL, NULL, NULL, '1', '1', NULL, '', NULL, '1', 38, '2019-02-02 15:51:47', NULL, '2019-02-02 15:51:47', 38, '2019-02-02 15:51:47', NULL, NULL, NULL, NULL, 1, 2, 0, '票据打印机');
INSERT INTO `zx_repair` VALUES (158, 1, NULL, 33, 524, NULL, '2019-02-02 00:00:00', 9791, NULL, NULL, '岗亭门拉手损坏', '2', NULL, NULL, NULL, '1', '1', NULL, '', NULL, '1', 40, '2019-02-02 16:05:13', NULL, '2019-02-02 16:05:13', 40, '2019-02-02 16:05:13', NULL, NULL, NULL, NULL, 1, 5, NULL, '');
INSERT INTO `zx_repair` VALUES (306, NULL, NULL, 5, NULL, NULL, '2019-08-17 00:00:00', NULL, NULL, NULL, '地漏多会修', '1', NULL, NULL, NULL, '1', '1', NULL, '', NULL, '1', 71, '2019-08-17 12:50:28', NULL, '2019-08-17 12:50:28', 71, '2019-08-17 12:50:28', NULL, NULL, NULL, NULL, 1, 2, NULL, '');

-- ----------------------------
-- Table structure for zx_repairs
-- ----------------------------
DROP TABLE IF EXISTS `zx_repairs`;
CREATE TABLE `zx_repairs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父节点ID',
  `whid` int(11) NULL DEFAULT NULL COMMENT '仓库',
  `invcid` int(11) NULL DEFAULT NULL COMMENT '备件分类',
  `invid` int(11) NULL DEFAULT NULL COMMENT '备品备件',
  `inum` decimal(11, 2) NULL DEFAULT NULL COMMENT '数量',
  `iprice` decimal(11, 2) NULL DEFAULT NULL COMMENT '单价',
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '折扣',
  `taxrate` decimal(11, 3) NULL DEFAULT NULL COMMENT '税率',
  `itax` decimal(11, 2) NULL DEFAULT NULL COMMENT '税额',
  `imoney` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `cbatch` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批号',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for zx_sblb
-- ----------------------------
DROP TABLE IF EXISTS `zx_sblb`;
CREATE TABLE `zx_sblb`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `xtid` int(11) NULL DEFAULT NULL COMMENT '系统分类id',
  `ccode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类别编码',
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类别',
  `isort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ctype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_sblb
-- ----------------------------
INSERT INTO `zx_sblb` VALUES (1, NULL, 1, 'A001', '生产机组', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (2, NULL, 1, 'A002', '生产设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (3, NULL, 1, 'A003', '办公设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (4, NULL, 1, 'A004', '车辆', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (8, NULL, 3, 'C001', '监控设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (18, NULL, 2, 'B004', '网络设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (19, NULL, 2, 'B005', '附属设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (20, NULL, 4, 'D001', '发电设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (29, NULL, 5, 'E001', '照明设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (30, NULL, 5, 'E002', '消防设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (31, NULL, 5, 'E003', '安全设备', 3, 1, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, '2018-12-22 09:24:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `zx_sblb` VALUES (77, NULL, NULL, 'X01', '其他', 1, 3, '2019-06-11 10:39:25', NULL, '2019-06-11 10:39:25', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for zx_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `zx_warehouse`;
CREATE TABLE `zx_warehouse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `whcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `whname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `whaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `whphone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仓库电话',
  `whperson` int(11) NULL DEFAULT NULL COMMENT '仓库负责人',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '所属部门',
  `createby` int(11) NULL DEFAULT NULL COMMENT '制单人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '制单时间',
  `updateby` int(11) NULL DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `auditby` int(11) NULL DEFAULT NULL COMMENT '审核人',
  `auditTime` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `del` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作废标识',
  `memo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `biztype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `c01` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用1',
  `c02` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用2',
  `c03` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备用3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zx_warehouse
-- ----------------------------
INSERT INTO `zx_warehouse` VALUES (1, '10', '原料总库', NULL, NULL, 1, 2, 1, '2019-06-16 21:36:49', NULL, '2019-06-16 21:36:52', NULL, NULL, NULL, NULL, NULL, '原料库', NULL, NULL, NULL);
INSERT INTO `zx_warehouse` VALUES (10, '41', '商贸库', '', '', NULL, 6, 1, '2019-05-18 01:09:10', 1, '2019-05-18 01:09:38', NULL, NULL, NULL, NULL, '', '产品库', NULL, NULL, NULL);
INSERT INTO `zx_warehouse` VALUES (11, '51', '备件库', '', '', NULL, 2, 1, '2019-05-18 01:09:58', NULL, '2019-05-18 01:09:58', NULL, NULL, NULL, NULL, '', '备件库', NULL, NULL, NULL);

-- ----------------------------
-- View structure for v_flow_doc
-- ----------------------------
DROP VIEW IF EXISTS `v_flow_doc`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_flow_doc` AS select `t`.`id` AS `id`,`t`.`title` AS `title`,`t`.`author` AS `author`,`t`.`brief` AS `brief`,`t`.`content` AS `content`,`t`.`img` AS `img`,`t`.`imgtype` AS `imgtype`,`t`.`ddate1` AS `ddate1`,`t`.`ddate2` AS `ddate2`,`t`.`status` AS `status`,`t`.`deptid` AS `deptid`,`t`.`createby` AS `createby`,`t`.`createTime` AS `createTime`,`t`.`updateby` AS `updateby`,`t`.`updateTime` AS `updateTime`,`t`.`auditby` AS `auditby`,`t`.`auditTime` AS `auditTime`,`t`.`memo` AS `memo`,`t`.`biztype` AS `biztype`,`t`.`doctype` AS `doctype`,`t`.`c01` AS `c01`,`t`.`c02` AS `c02`,`t`.`c03` AS `c03`,`t`.`c04` AS `c04`,`t`.`c05` AS `c05`,`t`.`flowid` AS `flowid`,`t`.`stepid` AS `stepid`,`t_dept`.`deptname` AS `deptname`,`t_creator`.`nickname` AS `creator`,`flowstep`.`stepname` AS `stepname`,if((`t`.`status` = 1),'已审','待审') AS `statusname` from (((`flow_doc` `t` left join `sys_user` `t_creator` on((`t`.`createby` = `t_creator`.`id`))) left join `t_dept` on((`t`.`deptid` = `t_dept`.`id`))) left join `flowstep` on(((`t`.`flowid` = `flowstep`.`flowid`) and (`t`.`stepid` = `flowstep`.`stepid`))));

-- ----------------------------
-- View structure for v_flow_todo
-- ----------------------------
DROP VIEW IF EXISTS `v_flow_todo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_flow_todo` AS select `t`.`id` AS `id`,`t`.`auditby` AS `auditby`,`t`.`sendby` AS `sendby`,`t`.`createTime` AS `createTime`,`t`.`updateTime` AS `updateTime`,`t`.`biaoti` AS `biaoti`,`t`.`neirong` AS `neirong`,`t`.`biztype` AS `biztype`,`t`.`bizid` AS `bizid`,`t`.`biztable` AS `biztable`,`t`.`status` AS `status`,`t`.`memo` AS `memo`,`t`.`c01` AS `c01`,`t`.`c02` AS `c02`,`t`.`c03` AS `c03`,`t`.`flowid` AS `flowid`,`t`.`stepid` AS `stepid`,`t`.`url` AS `url`,`tsend`.`nickname` AS `sendname`,`taudit`.`nickname` AS `auditname`,`t_dept`.`id` AS `senddeptid`,`t_dept`.`deptname` AS `senddeptname`,`flowstep`.`tofinish` AS `tofinish` from ((((`flow_todo` `t` left join `sys_user` `tsend` on((`t`.`sendby` = `tsend`.`id`))) left join `sys_user` `taudit` on((`t`.`auditby` = `taudit`.`id`))) left join `t_dept` on((`tsend`.`deptid` = `t_dept`.`id`))) left join `flowstep` on(((`t`.`flowid` = `flowstep`.`flowid`) and (`t`.`stepid` = `flowstep`.`stepid`))));

-- ----------------------------
-- View structure for v_kc_currstock
-- ----------------------------
DROP VIEW IF EXISTS `v_kc_currstock`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_kc_currstock` AS select `kc_currstock`.`id` AS `id`,`kc_currstock`.`whid` AS `whid`,`kc_currstock`.`deptid` AS `deptid`,`kc_currstock`.`posid` AS `posid`,`kc_currstock`.`invid` AS `invid`,`kc_currstock`.`cpgg` AS `cpgg`,`kc_currstock`.`ddate` AS `ddate`,`kc_currstock`.`busstype` AS `busstype`,`kc_currstock`.`csource` AS `csource`,`kc_currstock`.`bussid` AS `bussid`,`kc_currstock`.`jian` AS `jian`,`kc_currstock`.`jianzhong` AS `jianzhong`,`kc_currstock`.`inum` AS `inum`,`kc_currstock`.`iprice` AS `iprice`,`kc_currstock`.`imoney` AS `imoney`,`kc_currstock`.`createby` AS `createby`,`kc_currstock`.`createTime` AS `createTime`,`kc_currstock`.`updateby` AS `updateby`,`kc_currstock`.`updateTime` AS `updateTime`,`kc_currstock`.`auditby` AS `auditby`,`kc_currstock`.`auditTime` AS `auditTime`,`kc_currstock`.`status` AS `status`,`kc_currstock`.`del` AS `del`,`kc_currstock`.`memo` AS `memo`,`t_inv`.`ctype` AS `ctype`,`kc_currstock`.`c01` AS `c01`,`kc_currstock`.`c02` AS `c02`,`kc_currstock`.`c03` AS `c03`,`zx_warehouse`.`whname` AS `whname`,`zx_warehouse`.`biztype` AS `whtype`,`t_dept`.`deptname` AS `deptname`,`t_inv`.`invname` AS `invname`,`t_inv`.`invabbname` AS `invabbname`,`t_inv`.`invstd` AS `invstd`,`t_inv`.`invcid` AS `invcid`,`t_dict`.`val` AS `statusname`,`kc_currstock`.`invcode` AS `invcode`,`t_inv`.`unit1` AS `unit1`,`kc_currstock`.`ksid` AS `ksid`,`t_customer`.`cname` AS `ksname`,`kc_currstock`.`danwei` AS `danwei`,`kc_currstock`.`ilen` AS `ilen`,`kc_currstock`.`perlen` AS `perlen`,`t_customer`.`cname` AS `cusname` from (((((`kc_currstock` left join `zx_warehouse` on((`kc_currstock`.`whid` = `zx_warehouse`.`id`))) left join `t_dept` on((`kc_currstock`.`deptid` = `t_dept`.`id`))) left join `t_inv` on((`kc_currstock`.`invid` = `t_inv`.`id`))) left join `t_dict` on(((convert(`kc_currstock`.`status` using utf8mb4) = `t_dict`.`k`) and (`t_dict`.`type` = 'bjstatus')))) left join `t_customer` on((`t_customer`.`id` = `kc_currstock`.`ksid`)));

-- ----------------------------
-- View structure for v_kc_log
-- ----------------------------
DROP VIEW IF EXISTS `v_kc_log`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_kc_log` AS select `t`.`id` AS `id`,`t`.`whid` AS `whid`,`t`.`deptid` AS `deptid`,`t`.`ksid` AS `ksid`,`t`.`invid` AS `invid`,`t`.`cpgg` AS `cpgg`,`t`.`ddate` AS `ddate`,`t`.`busstype` AS `busstype`,`t`.`csource` AS `csource`,`t`.`bussid` AS `bussid`,`t`.`danwei` AS `danwei`,`t`.`jianzhong` AS `jianzhong`,`t`.`perlen` AS `perlen`,`t`.`jian0` AS `jian0`,`t`.`inum0` AS `inum0`,`t`.`ilen0` AS `ilen0`,`t`.`iprice0` AS `iprice0`,`t`.`imoney0` AS `imoney0`,`t`.`jian1` AS `jian1`,`t`.`inum1` AS `inum1`,`t`.`ilen1` AS `ilen1`,`t`.`iprice1` AS `iprice1`,`t`.`imoney1` AS `imoney1`,`t`.`jian2` AS `jian2`,`t`.`inum2` AS `inum2`,`t`.`ilen2` AS `ilen2`,`t`.`iprice2` AS `iprice2`,`t`.`imoney2` AS `imoney2`,`t`.`jian4` AS `jian4`,`t`.`inum4` AS `inum4`,`t`.`ilen4` AS `ilen4`,`t`.`iprice4` AS `iprice4`,`t`.`imoney4` AS `imoney4`,`t`.`status` AS `status`,`t`.`del` AS `del`,`t`.`memo` AS `memo`,`t`.`ctype` AS `ctype`,`t`.`createby` AS `createby`,`sys_user`.`nickname` AS `creator`,`t`.`createTime` AS `createTime`,`zx_warehouse`.`whname` AS `whname`,`zx_warehouse`.`biztype` AS `whtype`,`t_dept`.`deptname` AS `deptname`,`t_inv`.`invname` AS `invname`,`t_inv`.`invabbname` AS `invabbname`,`t_inv`.`invstd` AS `invstd`,`t_inv`.`invcid` AS `invcid`,`t_inv`.`unit1` AS `unit1`,`t_customer`.`cname` AS `ksname`,`t_customer`.`cname` AS `cusname` from (((((`kc_log` `t` left join `zx_warehouse` on((`t`.`whid` = `zx_warehouse`.`id`))) left join `t_dept` on((`t`.`deptid` = `t_dept`.`id`))) left join `t_inv` on((`t`.`invid` = `t_inv`.`id`))) left join `sys_user` on((`t`.`createby` = `sys_user`.`id`))) left join `t_customer` on((`t_customer`.`id` = `t`.`ksid`)));

-- ----------------------------
-- View structure for v_sys_user
-- ----------------------------
DROP VIEW IF EXISTS `v_sys_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_sys_user` AS select `sys_user`.`id` AS `id`,`sys_user`.`username` AS `username`,`sys_user`.`password` AS `password`,`sys_user`.`nickname` AS `nickname`,`sys_user`.`headImgUrl` AS `headImgUrl`,`sys_user`.`phone` AS `phone`,`sys_user`.`telephone` AS `telephone`,`sys_user`.`email` AS `email`,`sys_user`.`birthday` AS `birthday`,`sys_user`.`sex` AS `sex`,`sys_user`.`status` AS `status`,`sys_user`.`createTime` AS `createTime`,`sys_user`.`updateTime` AS `updateTime`,`sys_user`.`deptid` AS `deptid`,`sys_user`.`clientid` AS `clientid`,`sys_user`.`openid` AS `openid`,`sys_user`.`positionid` AS `positionid`,`sys_user`.`createby` AS `createby`,`sys_user`.`updateby` AS `updateby`,`sys_user`.`auditby` AS `auditby`,`sys_user`.`auditTime` AS `auditTime`,`sys_user`.`del` AS `del`,`sys_user`.`memo` AS `memo`,`sys_user`.`ctype` AS `ctype`,`sys_user`.`c01` AS `c01`,`sys_user`.`c02` AS `c02`,`sys_user`.`c03` AS `c03`,`sys_user`.`yuexin` AS `yuexin`,`sys_user`.`rzsj` AS `rzsj`,`sys_user`.`zytc` AS `zytc`,`d`.`deptname` AS `deptname` from (`sys_user` left join `t_dept` `d` on((`d`.`id` = `sys_user`.`deptid`)));

-- ----------------------------
-- View structure for v_zx_borrow
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_borrow`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_borrow` AS select `t`.`id` AS `id`,`t`.`deptid` AS `deptid`,`t`.`whid` AS `whid`,`zx_warehouse`.`whname` AS `whname`,`t`.`bizdate` AS `bizdate`,`t`.`eqpid` AS `eqpid`,`zx_equipment`.`cname` AS `eqpname`,`zx_equipment`.`serialno` AS `serialno`,`t`.`description` AS `description`,`t`.`quantity` AS `quantity`,`t`.`unit` AS `unit`,`t`.`price` AS `price`,`t`.`money` AS `money`,`t`.`expectreturn` AS `expectreturn`,`t`.`returndate` AS `returndate`,`t`.`returndesc` AS `returndesc`,`t`.`status` AS `status`,`t`.`memo` AS `memo`,`t`.`del` AS `del`,`t`.`biztype` AS `biztype`,`t`.`createby` AS `createby`,`t`.`createTime` AS `createTime`,`t`.`updateby` AS `updateby`,`t`.`updateTime` AS `updateTime`,`t`.`auditby` AS `auditby`,`t`.`auditTime` AS `auditTime`,`t`.`c01` AS `c01`,`t`.`c02` AS `c02`,`t`.`c03` AS `c03`,`t_dept`.`deptname` AS `deptname`,`t_creator`.`nickname` AS `creator`,`t_auditor`.`nickname` AS `auditor`,`flowstep`.`stepname` AS `stepname`,if((`t`.`status` = 1),'已审','待审') AS `statusname`,`t`.`flowid` AS `flowid`,`t`.`stepid` AS `stepid`,`t`.`whid2` AS `whid2` from ((((((`zx_borrow` `t` left join `sys_user` `t_creator` on((`t`.`createby` = `t_creator`.`id`))) left join `sys_user` `t_auditor` on((`t`.`auditby` = `t_auditor`.`id`))) left join `t_dept` on((`t`.`deptid` = `t_dept`.`id`))) left join `zx_warehouse` on((`t`.`whid` = `zx_warehouse`.`id`))) left join `zx_equipment` on((`t`.`eqpid` = `zx_equipment`.`id`))) left join `flowstep` on(((`t`.`flowid` = `flowstep`.`flowid`) and (`t`.`stepid` = `flowstep`.`stepid`))));

-- ----------------------------
-- View structure for v_zx_borrows
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_borrows`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_borrows` AS select `zx_borrows`.`id` AS `id`,`zx_borrows`.`pid` AS `pid`,`zx_borrows`.`invid` AS `invid`,`zx_borrows`.`inum` AS `inum`,`zx_borrows`.`iprice` AS `iprice`,`zx_borrows`.`discount` AS `discount`,`zx_borrows`.`taxrate` AS `taxrate`,`zx_borrows`.`itax` AS `itax`,`zx_borrows`.`imoney` AS `imoney`,`zx_borrows`.`cbatch` AS `cbatch`,`zx_borrows`.`status` AS `status`,`zx_borrows`.`del` AS `del`,`zx_borrows`.`memo` AS `memo`,`zx_borrows`.`ctype` AS `ctype`,`zx_borrows`.`c01` AS `c01`,`zx_borrows`.`c02` AS `c02`,`zx_borrows`.`c03` AS `c03`,`t_inv`.`invname` AS `invname`,`t_inv`.`invstd` AS `invstd`,`t_inv`.`unit1` AS `unit1`,`e`.`cname` AS `cname`,`e`.`etype` AS `etype`,`zx_borrows`.`eqpid` AS `eqpid` from ((`zx_borrows` left join `t_inv` on((`zx_borrows`.`invid` = `t_inv`.`id`))) left join `zx_equipment` `e` on((`e`.`id` = `zx_borrows`.`eqpid`)));

-- ----------------------------
-- View structure for v_zx_equipment
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_equipment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_equipment` AS select `zx_equipment`.`id` AS `id`,`zx_equipment`.`xtid` AS `xtid`,`zx_equipment`.`lbid` AS `lbid`,`zx_equipment`.`deptid` AS `deptid`,`zx_equipment`.`xjqyid` AS `xjqyid`,`zx_equipment`.`whqyid` AS `whqyid`,`zx_equipment`.`cname` AS `cname`,`zx_equipment`.`pid` AS `pid`,`zx_equipment`.`isort` AS `isort`,`zx_equipment`.`serialno` AS `serialno`,`zx_equipment`.`etype` AS `etype`,`zx_equipment`.`factoryid` AS `factoryid`,`zx_equipment`.`buildtime` AS `buildtime`,`zx_equipment`.`techphone` AS `techphone`,`zx_equipment`.`techname` AS `techname`,`zx_equipment`.`qualityperiod` AS `qualityperiod`,`zx_equipment`.`maintainperiod` AS `maintainperiod`,`zx_equipment`.`maintainbiao1` AS `maintainbiao1`,`zx_equipment`.`maintainbiao2` AS `maintainbiao2`,`zx_equipment`.`maintainbiao3` AS `maintainbiao3`,`zx_equipment`.`useperiod` AS `useperiod`,`zx_equipment`.`equipmentcontent` AS `equipmentcontent`,`zx_equipment`.`equipmentstatus` AS `equipmentstatus`,`zx_equipment`.`adminid` AS `adminid`,`zx_equipment`.`addtime` AS `addtime`,`zx_equipment`.`isxun` AS `isxun`,`zx_equipment`.`iswei` AS `iswei`,`zx_equipment`.`tdesc` AS `tdesc`,`zx_equipment`.`createby` AS `createby`,`zx_equipment`.`createTime` AS `createTime`,`zx_equipment`.`updateby` AS `updateby`,`zx_equipment`.`updateTime` AS `updateTime`,`zx_equipment`.`auditby` AS `auditby`,`zx_equipment`.`auditTime` AS `auditTime`,`zx_equipment`.`status` AS `status`,`t_dict`.`val` AS `statusname`,`zx_equipment`.`del` AS `del`,`zx_equipment`.`memo` AS `memo`,`zx_equipment`.`ctype` AS `ctype`,`zx_equipment`.`c01` AS `c01`,`zx_equipment`.`c02` AS `c02`,`zx_equipment`.`c03` AS `c03`,`zx_equipment`.`pinpai` AS `pinpai`,`zx_equipment`.`anzhuang` AS `anzhuang`,`zx_equipment`.`zhuangfx` AS `zhuangfx`,`zx_equipment`.`zhuangfw` AS `zhuangfw`,`zx_equipment`.`zhuangwz` AS `zhuangwz`,`zx_equipment`.`whid` AS `whid`,`zx_equipment`.`price` AS `price`,`t_dept`.`deptname` AS `deptname`,`zx_sblb`.`cname` AS `sblb`,`sys_user`.`nickname` AS `creater` from ((((`zx_equipment` left join `t_dept` on((`zx_equipment`.`deptid` = `t_dept`.`id`))) left join `zx_sblb` on((`zx_equipment`.`lbid` = `zx_sblb`.`id`))) left join `sys_user` on((`zx_equipment`.`createby` = `sys_user`.`id`))) left join `t_dict` on(((convert(`zx_equipment`.`status` using utf8mb4) = `t_dict`.`k`) and (`t_dict`.`type` = 'status'))));

-- ----------------------------
-- View structure for v_zx_repair
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_repair`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_repair` AS select `zx_repair`.`id` AS `id`,`zx_repair`.`xtid` AS `xtid`,`zx_repair`.`lbid` AS `lbid`,`zx_repair`.`deptid` AS `deptid`,`zx_repair`.`xjqyid` AS `xjqyid`,`zx_repair`.`whqyid` AS `whqyid`,`zx_repair`.`bizdate` AS `bizdate`,`zx_repair`.`eqpid` AS `eqpid`,`zx_repair`.`eqpcode` AS `eqpcode`,`zx_repair`.`eqpname` AS `eqpname`,`zx_repair`.`description` AS `description`,`zx_repair`.`descpic` AS `descpic`,`zx_repair`.`repair` AS `repair`,`zx_repair`.`repairman` AS `repairman`,`zx_repair`.`repaircost` AS `repaircost`,`zx_repair`.`material` AS `material`,`zx_repair`.`status` AS `status`,`zx_repair`.`userid` AS `userid`,`zx_repair`.`memo` AS `memo`,`zx_repair`.`del` AS `del`,`zx_repair`.`biztype` AS `biztype`,`zx_repair`.`createby` AS `createby`,`sys_user`.`nickname` AS `createname`,`zx_repair`.`createTime` AS `createTime`,`zx_repair`.`updateby` AS `updateby`,`zx_repair`.`updateTime` AS `updateTime`,`zx_repair`.`auditby` AS `auditby`,`zx_repair`.`auditTime` AS `auditTime`,`zx_repair`.`c01` AS `c01`,`zx_repair`.`c02` AS `c02`,`zx_repair`.`c03` AS `c03`,`zx_repair`.`inspid` AS `inspid`,`zx_repair`.`flowid` AS `flowid`,`zx_repair`.`stepid` AS `stepid`,`zx_repair`.`gzid` AS `gzid`,`zx_repair`.`gzmc` AS `gzmc`,`zx_equipment`.`cname` AS `cname`,`t_dept`.`deptname` AS `deptname`,`t_dict`.`val` AS `biztype1`,`flowstep`.`stepname` AS `stepname`,`flowstep`.`flowact` AS `flowact` from (((((`zx_repair` left join `zx_equipment` on((`zx_repair`.`eqpid` = `zx_equipment`.`id`))) left join `t_dept` on((`zx_repair`.`deptid` = `t_dept`.`id`))) left join `sys_user` on((`zx_repair`.`createby` = `sys_user`.`id`))) left join `t_dict` on(((convert(`zx_repair`.`biztype` using utf8mb4) = `t_dict`.`k`) and (`t_dict`.`type` = 'biztype1')))) left join `flowstep` on(((`zx_repair`.`flowid` = `flowstep`.`flowid`) and (`zx_repair`.`stepid` = `flowstep`.`stepid`))));

-- ----------------------------
-- View structure for v_zx_repairs
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_repairs`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_repairs` AS select `zx_repairs`.`id` AS `id`,`zx_repairs`.`pid` AS `pid`,`zx_repairs`.`whid` AS `whid`,`zx_repairs`.`invcid` AS `invcid`,`zx_repairs`.`invid` AS `invid`,`zx_repairs`.`inum` AS `inum`,`zx_repairs`.`iprice` AS `iprice`,`zx_repairs`.`discount` AS `discount`,`zx_repairs`.`taxrate` AS `taxrate`,`zx_repairs`.`itax` AS `itax`,`zx_repairs`.`imoney` AS `imoney`,`zx_repairs`.`cbatch` AS `cbatch`,`zx_repairs`.`status` AS `status`,`zx_repairs`.`del` AS `del`,`zx_repairs`.`memo` AS `memo`,`zx_repairs`.`ctype` AS `ctype`,`zx_repairs`.`c01` AS `c01`,`zx_repairs`.`c02` AS `c02`,`zx_repairs`.`c03` AS `c03`,`t_inv`.`invname` AS `invname` from (`zx_repairs` left join `t_inv` on((`zx_repairs`.`invid` = `t_inv`.`id`)));

-- ----------------------------
-- View structure for v_zx_repair_todo
-- ----------------------------
DROP VIEW IF EXISTS `v_zx_repair_todo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_zx_repair_todo` AS select `v_zx_repair`.`id` AS `id`,`v_zx_repair`.`xtid` AS `xtid`,`v_zx_repair`.`lbid` AS `lbid`,`v_zx_repair`.`deptid` AS `deptid`,`v_zx_repair`.`xjqyid` AS `xjqyid`,`v_zx_repair`.`whqyid` AS `whqyid`,`v_zx_repair`.`bizdate` AS `bizdate`,`v_zx_repair`.`eqpid` AS `eqpid`,`v_zx_repair`.`eqpcode` AS `eqpcode`,`v_zx_repair`.`eqpname` AS `eqpname`,`v_zx_repair`.`description` AS `description`,`v_zx_repair`.`descpic` AS `descpic`,`v_zx_repair`.`repair` AS `repair`,`v_zx_repair`.`repairman` AS `repairman`,`v_zx_repair`.`repaircost` AS `repaircost`,`v_zx_repair`.`material` AS `material`,`v_zx_repair`.`status` AS `status`,`v_zx_repair`.`userid` AS `userid`,`v_zx_repair`.`memo` AS `memo`,`v_zx_repair`.`del` AS `del`,`v_zx_repair`.`biztype` AS `biztype`,`v_zx_repair`.`createby` AS `createby`,`v_zx_repair`.`createname` AS `createname`,`v_zx_repair`.`createTime` AS `createTime`,`v_zx_repair`.`updateby` AS `updateby`,`v_zx_repair`.`updateTime` AS `updateTime`,`v_zx_repair`.`auditby` AS `auditby`,`v_zx_repair`.`auditTime` AS `auditTime`,`v_zx_repair`.`c01` AS `c01`,`v_zx_repair`.`c02` AS `c02`,`v_zx_repair`.`c03` AS `c03`,`v_zx_repair`.`inspid` AS `inspid`,`v_zx_repair`.`flowid` AS `flowid`,`v_zx_repair`.`stepid` AS `stepid`,`v_zx_repair`.`gzid` AS `gzid`,`v_zx_repair`.`gzmc` AS `gzmc`,`v_zx_repair`.`cname` AS `cname`,`v_zx_repair`.`deptname` AS `deptname`,`v_zx_repair`.`biztype1` AS `biztype1`,`v_zx_repair`.`stepname` AS `stepname`,`flow_todo`.`id` AS `todoid`,`flow_todo`.`auditby` AS `todoauditby`,`flow_todo`.`sendby` AS `todosendby`,`flow_todo`.`status` AS `todostatus`,`flow_todo`.`biaoti` AS `biaoti`,`v_zx_repair`.`flowact` AS `flowact` from (`flow_todo` left join `v_zx_repair` on((`flow_todo`.`bizid` = `v_zx_repair`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
