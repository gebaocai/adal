/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.200
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.0.200:3306
 Source Schema         : adal

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 05/02/2022 18:54:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for casbin_rule
-- ----------------------------
DROP TABLE IF EXISTS `casbin_rule`;
CREATE TABLE `casbin_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ptype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `v0` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `v1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `v2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `v3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `v4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `v5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of casbin_rule
-- ----------------------------
INSERT INTO `casbin_rule` VALUES (26, 'p', 'bob', 'data2', 'write', NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (27, 'p', 'data2_admin', 'data2', 'read', NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (28, 'p', 'data2_admin', 'data2', 'write', NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (29, 'p', 'alice', 'data1', 'read', NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (30, 'g', 'alice', 'data2_admin', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (31, 'g', '1072806378780889088', '1072806379238068224', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (32, 'g', '1072806378780889088', '1460231340111826944', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (33, 'g', '1072806378780889088', '1460231318645379072', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (36, 'g', '1072806378780889088', 'depart:1427796982902165504', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (37, 'g', '1072806378780889088', 'depart:1468925598234710016', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (38, 'g', '1072806378780889088', 'depart:1468924150767816704', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (39, 'g', '1072806378780889088', 'depart:1468493118348333056', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (40, 'g', '1072806378780889088', 'role:1072806379238068224', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (41, 'g', '1072806378780889088', 'role:1460231318645379072', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (42, 'g', '1072806378780889088', 'role:1460231340111826944', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (76, 'p', 'role:1072806379208708096', '1465918103992012800', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (77, 'g', '1072806377661009920', 'depart:1427797901068865536', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (78, 'g', '1072806377661009920', 'role:1072806379238068224', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (79, 'g', '1072806377661009920', 'role:1072806379208708096', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (80, 'p', 'role:1072806379208708096', '1473581962080948224', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (81, 'p', 'role:1072806379208708096', '1473582179501084672', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (82, 'p', 'role:1072806379208708096', '1473582427938099200', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (83, 'p', 'role:1072806379208708096', '1484422820422356992', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (84, 'p', 'role:1072806379208708096', '1484422944443731968', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (85, 'g', '1427569011109007360', 'depart:1428320641978863616', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (86, 'g', '1427569011109007360', 'depart:1427797816570417152', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (87, 'g', '1427569011109007360', 'role:1072806379208708096', NULL, NULL, NULL, NULL);
INSERT INTO `casbin_rule` VALUES (88, 'g', '1072806378780889088', 'role:1072806379208708096', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for market_goods
-- ----------------------------
DROP TABLE IF EXISTS `market_goods`;
CREATE TABLE `market_goods`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `num` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of market_goods
-- ----------------------------
INSERT INTO `market_goods` VALUES (1298572725421477888, 'zxxx', '123456', 1, 0);
INSERT INTO `market_goods` VALUES (1298572748561453056, 'zxxx', '123456', 1, 0);
INSERT INTO `market_goods` VALUES (1427596607913857024, 'zxxx', '123456', 1, 0);

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `sort_no` double(8, 2) NULL DEFAULT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2连接)',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES (1488871396011544576, '用户管理', 1.00, '1489459363365130240', 0, NULL, '用户管理', '2022-02-02 21:46:17.359000', '2022-02-05 16:07:48.600000');
INSERT INTO `sys_api` VALUES (1489434036324667392, '角色管理', 2.00, '1489459363365130240', 0, NULL, '角色管理', '2022-02-04 11:02:01.268000', '2022-02-05 16:07:59.378000');
INSERT INTO `sys_api` VALUES (1489434086878613504, '部门管理', 3.00, '1489459363365130240', 0, NULL, '部门管理', '2022-02-04 11:02:13.317000', '2022-02-05 16:08:06.845000');
INSERT INTO `sys_api` VALUES (1489434154218164224, '菜单管理', 4.00, '1489459363365130240', 0, NULL, '菜单管理', '2022-02-04 11:02:29.371000', '2022-02-05 16:08:12.617000');
INSERT INTO `sys_api` VALUES (1489434186409447424, '接口管理', 5.00, '1489459363365130240', 0, NULL, '接口管理', '2022-02-04 11:02:37.046000', '2022-02-05 16:08:18.329000');
INSERT INTO `sys_api` VALUES (1489436645588602880, '创建用户', 1.00, '1488871396011544576', 2, '/api/sys/user/save', '创建用户', '2022-02-04 11:12:23.361000', '2022-02-04 11:12:23.361000');
INSERT INTO `sys_api` VALUES (1489436797967667200, '编辑用户', 2.00, '1488871396011544576', 2, '/api/sys/user/edit', '编辑用户', '2022-02-04 11:12:59.691000', '2022-02-04 11:12:59.691000');
INSERT INTO `sys_api` VALUES (1489437014003683328, '用户列表', 3.00, '1488871396011544576', 2, '/api/sys/user/list', '用户列表', '2022-02-04 11:13:51.198000', '2022-02-04 11:13:51.198000');
INSERT INTO `sys_api` VALUES (1489443280474214400, '修改密码', 4.00, '1488871396011544576', 2, '/api/sys/user/changePassword', '修改密码', '2022-02-04 11:38:45.241000', '2022-02-04 11:38:45.241000');
INSERT INTO `sys_api` VALUES (1489446852993617920, '角色列表', 1.00, '1489434036324667392', 2, '/api/sys/role/list', '角色列表', '2022-02-04 11:52:56.995000', '2022-02-04 11:52:56.995000');
INSERT INTO `sys_api` VALUES (1489446944530108416, '创建角色', 2.00, '1489434036324667392', 2, '/api/sys/role/save', '创建角色', '2022-02-04 11:53:18.820000', '2022-02-04 11:53:18.820000');
INSERT INTO `sys_api` VALUES (1489447021000658944, '编辑角色', 3.00, '1489434036324667392', 2, '/api/sys/role/edit', '编辑角色', '2022-02-04 11:53:37.052000', '2022-02-04 11:53:37.052000');
INSERT INTO `sys_api` VALUES (1489447323800047616, '角色关联用户', 4.00, '1489434036324667392', 2, '/api/sys/user/listByRoleId', '拥有某角色的用户', '2022-02-04 11:54:49.245000', '2022-02-04 11:54:49.245000');
INSERT INTO `sys_api` VALUES (1489447535515930624, '角色关联权限', 5.00, '1489434036324667392', 2, '/api/sys/rolePermission/list', '查看某角色拥有的权限', '2022-02-04 11:55:39.722000', '2022-02-04 11:55:39.722000');
INSERT INTO `sys_api` VALUES (1489447701182550016, '权限列表', 6.00, '1489459363365130240', 2, '/api/sys/permission/queryTreeList', '查看权限树状列表', '2022-02-04 11:56:19.220000', '2022-02-05 16:07:28.863000');
INSERT INTO `sys_api` VALUES (1489448342458077184, '部门列表', 1.00, '1489434086878613504', 2, '/api/sys/sysDepart/treeList', '部门列表', '2022-02-04 11:58:52.112000', '2022-02-04 11:58:52.113000');
INSERT INTO `sys_api` VALUES (1489448450197164032, '部门详情', 2.00, '1489434086878613504', 2, '/api/sys/sysDepart/info', '部门详情', '2022-02-04 11:59:17.799000', '2022-02-04 11:59:17.799000');
INSERT INTO `sys_api` VALUES (1489459363365130240, '系统管理', 1.00, NULL, 0, NULL, '系统管理', '2022-02-04 12:42:39.700000', '2022-02-04 12:42:39.700000');
INSERT INTO `sys_api` VALUES (1489875138055901184, '编辑部门', 3.00, '1489434086878613504', 2, '/api/sys/sysDepart/edit', '编辑部门', '2022-02-05 16:14:48.112000', '2022-02-05 16:14:48.112000');
INSERT INTO `sys_api` VALUES (1489875211909206016, '新增部门', 4.00, '1489434086878613504', 2, '/api/sys/sysDepart/add', '新增部门', '2022-02-05 16:15:05.719000', '2022-02-05 16:15:05.719000');
INSERT INTO `sys_api` VALUES (1489875368109281280, '部门权限修改', 5.00, '1489434086878613504', 2, '/api/sys/departPermission/batchSave', '部门权限修改', '2022-02-05 16:15:42.961000', '2022-02-05 16:15:42.961000');
INSERT INTO `sys_api` VALUES (1489875460795011072, '部门权限列表', 6.00, '1489434086878613504', 2, '/api/sys/departPermission/list', '部门权限列表', '2022-02-05 16:16:05.059000', '2022-02-05 16:16:05.059000');
INSERT INTO `sys_api` VALUES (1489875608430317568, '新增菜单', 1.00, '1489434154218164224', 2, '/api/sys/permission/add', '新增菜单', '2022-02-05 16:16:40.258000', '2022-02-05 16:16:40.258000');
INSERT INTO `sys_api` VALUES (1489875680647843840, '编辑菜单', 2.00, '1489434154218164224', 2, '/api/sys/permission/edit', '编辑菜单', '2022-02-05 16:16:57.476000', '2022-02-05 16:16:57.476000');
INSERT INTO `sys_api` VALUES (1489875765360201728, '删除菜单', 3.00, '1489434154218164224', 2, '/api/sys/permission/delete', '删除菜单', '2022-02-05 16:17:17.673000', '2022-02-05 16:17:17.673000');
INSERT INTO `sys_api` VALUES (1489875991106031616, '获取菜单接口列表', 4.00, '1489434154218164224', 2, '/api/sys/permissionApi/list', '获取菜单所使用的接口', '2022-02-05 16:18:11.495000', '2022-02-05 16:18:11.495000');
INSERT INTO `sys_api` VALUES (1489876297797734400, '修改菜单接口', 5.00, '1489434154218164224', 2, '/api/sys/permissionApi/batchSave', '修改菜单使用的接口', '2022-02-05 16:19:24.617000', '2022-02-05 16:19:24.617000');
INSERT INTO `sys_api` VALUES (1489876705861570560, '增加接口', 1.00, '1489434186409447424', 2, '/api/sys/api/save', '增加接口', '2022-02-05 16:21:01.906000', '2022-02-05 16:21:01.906000');
INSERT INTO `sys_api` VALUES (1489876813558714368, '编辑接口', 2.00, '1489434186409447424', 2, '/api/sys/api/edi', '编辑接口', '2022-02-05 16:21:27.583000', '2022-02-05 16:21:27.583000');
INSERT INTO `sys_api` VALUES (1489876911915143168, '删除接口', 3.00, '1489434186409447424', 2, '/api/sys/api/delete', '删除接口', '2022-02-05 16:21:51.033000', '2022-02-05 16:21:51.033000');
INSERT INTO `sys_api` VALUES (1489877044207685632, '接口数状列表', 4.00, '1489434186409447424', 2, '/api/sys/api/queryTreeList', '接口数状列表', '2022-02-05 16:22:22.574000', '2022-02-05 16:22:22.574000');
INSERT INTO `sys_api` VALUES (1489890822261444608, '首页', 2.00, NULL, 0, NULL, '首页', '2022-02-05 17:17:07.518000', '2022-02-05 17:17:07.518000');
INSERT INTO `sys_api` VALUES (1489890932387090432, '获取用户菜单', 1.00, '1489890822261444608', 2, '/api/sys/permission/getUserPermission', '获取用户菜单', '2022-02-05 17:17:33.774000', '2022-02-05 17:17:33.774000');

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart`  (
  `id` bigint(64) NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父机构ID',
  `depart_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构/部门名称',
  `depart_name_en` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `depart_name_abbr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩写',
  `depart_order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `org_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '机构类别 1公司，2组织机构，2岗位',
  `org_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型 1一级部门 2子部门',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编码',
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `fax` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态（1启用，0不启用）',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态（0，正常，1已删除）',
  `qywx_identifier` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接企业微信的ID',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_depart_org_code`(`org_code`) USING BTREE,
  INDEX `index_depart_parent_id`(`parent_id`) USING BTREE,
  INDEX `index_depart_depart_order`(`depart_order`) USING BTREE,
  INDEX `index_depart_org_code`(`org_code`) USING BTREE,
  INDEX `idx_sd_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sd_depart_order`(`depart_order`) USING BTREE,
  INDEX `idx_sd_org_code`(`org_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO `sys_depart` VALUES (1427582230926069760, 'c6d7cb4deeac411cb3384b1b31278596', '公司0', NULL, NULL, 0, NULL, '1', '2', 'A01A06', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:02', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582232196943872, 'c6d7cb4deeac411cb3384b1b31278596', '公司1', NULL, NULL, 0, NULL, '1', '2', 'A01A07', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:02', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582233316823040, 'c6d7cb4deeac411cb3384b1b31278596', '公司2', NULL, NULL, 0, NULL, '1', '2', 'A01A08', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:02', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582234394759168, 'c6d7cb4deeac411cb3384b1b31278596', '公司3', NULL, NULL, 0, NULL, '1', '2', 'A01A09', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582234847744000, 'c6d7cb4deeac411cb3384b1b31278596', '公司4', NULL, NULL, 0, NULL, '1', '2', 'A01A10', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582235460112384, 'c6d7cb4deeac411cb3384b1b31278596', '公司5', NULL, NULL, 0, NULL, '1', '2', 'A01A11', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582235934068736, 'c6d7cb4deeac411cb3384b1b31278596', '公司6', NULL, NULL, 0, NULL, '1', '2', 'A01A12', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582236680654848, 'c6d7cb4deeac411cb3384b1b31278596', '公司7', NULL, NULL, 0, NULL, '1', '2', 'A01A13', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582237083308032, 'c6d7cb4deeac411cb3384b1b31278596', '公司8', NULL, NULL, 0, NULL, '1', '2', 'A01A14', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427582237502738432, 'c6d7cb4deeac411cb3384b1b31278596', '公司9', NULL, NULL, 0, NULL, '1', '2', 'A01A15', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1234', '2021-08-17 18:45:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427796982902165504, '', 'zxxx', NULL, NULL, 0, NULL, '1', '1', 'A03', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-18 08:58:23', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427796996772728832, '', 'zxxx', NULL, NULL, 0, NULL, '1', '1', 'A04', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-18 08:58:26', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427797816570417152, '', 'zxxx', NULL, NULL, 0, NULL, '1', '1', 'A05', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-18 09:01:41', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427797901068865536, '', 'zxxx122', NULL, NULL, 0, NULL, '1', '1', 'A06', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-18 09:02:01', '1072806377661009920', '2021-12-08 16:06:03');
INSERT INTO `sys_depart` VALUES (1427797907993661440, '', 'zxxx', NULL, NULL, 0, NULL, '1', '1', 'A07', '17090073253', NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-18 09:02:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1427797912087302144, '', 'zxx111x', 'zxx111x', NULL, 2, NULL, '1', '1', 'A08', '17090073253', '17090073253', '17090073253', '111', NULL, 0, NULL, '1072806377661009920', '2021-08-18 09:02:04', '1072806377661009920', '2021-09-07 16:23:34');
INSERT INTO `sys_depart` VALUES (1428320641978863616, '', 'zxxx133', NULL, NULL, 0, NULL, '1', '1', 'A09', '17090073253', '17090073253', '17090073253', NULL, NULL, 0, NULL, '1072806377661009920', '2021-08-19 19:39:13', '1072806377661009920', '2021-09-25 17:40:57');
INSERT INTO `sys_depart` VALUES (1468492771965931520, '', '111', NULL, NULL, 0, NULL, '1', '1', 'A10', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-08 16:08:55', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468492779712811008, '', '111', NULL, NULL, 0, NULL, '1', '1', 'A11', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-08 16:08:57', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468493118348333056, '', '爱接力', NULL, NULL, 0, NULL, '1', '1', 'A12', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-08 16:10:17', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468923409370058752, '', '研发', NULL, NULL, 0, NULL, '1', '1', 'A13', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-09 20:40:07', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468923464353189888, '', '研发1', NULL, NULL, 0, NULL, '1', '1', 'A14', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-09 20:40:20', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468924150767816704, '1468493118348333056', 'aaa', NULL, NULL, 0, NULL, '1', '2', 'A12A01', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-09 20:43:03', NULL, NULL);
INSERT INTO `sys_depart` VALUES (1468925598234710016, '1468924150767816704', '11', NULL, NULL, 0, NULL, '1', '3', 'A12A01A01', NULL, NULL, NULL, NULL, NULL, 0, NULL, '1072806377661009920', '2021-12-09 20:48:49', NULL, NULL);

-- ----------------------------
-- Table structure for sys_depart_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart_permission`;
CREATE TABLE `sys_depart_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `depart_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据规则id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_depart_permission
-- ----------------------------
INSERT INTO `sys_depart_permission` VALUES ('1428164274018193408', '6d35e179cd814e3299bd588ea7daed3f', 'abd50036ff42f0a9943ace7eb63c63b6', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274026582016', '6d35e179cd814e3299bd588ea7daed3f', 'baf16b7174bd821b6bab23fa9abb200d', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274030776320', '6d35e179cd814e3299bd588ea7daed3f', 'e4335a37647952a691fd609ee6f92168', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274034970624', '6d35e179cd814e3299bd588ea7daed3f', '1939e035e803a99ceecb6f5563570fb2', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274039164928', '6d35e179cd814e3299bd588ea7daed3f', '09e7db25b4555332333f83afdadc7897', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274043359232', '6d35e179cd814e3299bd588ea7daed3f', 'cab5a60d58871cff3d5858e4d2580a26', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274047553536', '6d35e179cd814e3299bd588ea7daed3f', '2ea33825c6b365607b9ff04f3362bdda', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274051747840', '6d35e179cd814e3299bd588ea7daed3f', 'bae8992dc6f39b1736d65ef251fa68c2', NULL);
INSERT INTO `sys_depart_permission` VALUES ('1428164274055942144', '6d35e179cd814e3299bd588ea7daed3f', '1215097609505517569', NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `sort_no` double(8, 2) NULL DEFAULT NULL COMMENT '菜单排序',
  `always_show` tinyint(1) NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden` int(2) NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(1) NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int(3) NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
  `internal_or_external` tinyint(1) NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_prem_pid`(`parent_id`) USING BTREE,
  INDEX `index_prem_is_route`(`is_route`) USING BTREE,
  INDEX `index_prem_is_leaf`(`is_leaf`) USING BTREE,
  INDEX `index_prem_sort_no`(`sort_no`) USING BTREE,
  INDEX `index_prem_del_flag`(`del_flag`) USING BTREE,
  INDEX `index_menu_type`(`menu_type`) USING BTREE,
  INDEX `index_menu_hidden`(`hidden`) USING BTREE,
  INDEX `index_menu_status`(`status`) USING BTREE,
  INDEX `idx_sp_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sp_is_route`(`is_route`) USING BTREE,
  INDEX `idx_sp_is_leaf`(`is_leaf`) USING BTREE,
  INDEX `idx_sp_sort_no`(`sort_no`) USING BTREE,
  INDEX `idx_sp_del_flag`(`del_flag`) USING BTREE,
  INDEX `idx_sp_menu_type`(`menu_type`) USING BTREE,
  INDEX `idx_sp_hidden`(`hidden`) USING BTREE,
  INDEX `idx_sp_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1166535831146504193, '2a470fc0c3954d9dbb61de6d80846549', '对象存储', '/oss/file', 'modules/oss/OSSFileList', NULL, NULL, 1, NULL, '1', 1.00, 0, '', 1, 1, 0, 0, NULL, 'admin', '2019-08-28 02:19:50', 'admin', '2019-08-28 02:20:57', 0, 0, '1', NULL);
INSERT INTO `sys_permission` VALUES (1170592628746878978, 'd7d6e2e4e2934f2c9385a623fd98c6f3', '菜单管理2', '/isystem/newPermissionList', 'system/NewPermissionList', NULL, NULL, 1, NULL, '1', 100.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-09-08 15:00:05', 'admin', '2019-12-25 09:58:18', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1174506953255182338, 'd7d6e2e4e2934f2c9385a623fd98c6f3', '职务管理', '/isystem/position', 'system/SysPositionList', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-09-19 10:14:13', 'admin', '2019-09-19 10:15:22', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1174590283938041857, 'd7d6e2e4e2934f2c9385a623fd98c6f3', '通讯录', '/isystem/addressList', 'system/AddressList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-09-19 15:45:21', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1192318987661234177, 'e41b69c57a941a3bbcce45032fe57605', '系统编码规则', '/isystem/fillRule', 'system/SysFillRuleList', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-11-07 13:52:53', 'admin', '2020-07-10 16:55:03', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1205098241075453953, '1205097455226462210', '生产销售监控', '{{ window._CONFIG[\'domianURL\'] }}/test/bigScreen/templat/index1', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-12-12 20:13:05', 'admin', '2019-12-12 20:15:27', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES (1205306106780364802, '1205097455226462210', '智慧物流监控', '{{ window._CONFIG[\'domianURL\'] }}/test/bigScreen/templat/index2', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-12-13 09:59:04', 'admin', '2019-12-25 09:28:03', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1209731624921534465, 'e41b69c57a941a3bbcce45032fe57605', '多数据源管理', '/isystem/dataSource', 'system/SysDataSourceList', NULL, NULL, 1, NULL, '1', 6.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-12-25 15:04:30', 'admin', '2020-02-23 22:43:37', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1224641973866467330, 'e41b69c57a941a3bbcce45032fe57605', '系统校验规则', '/isystem/checkRule', 'system/SysCheckRuleList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2019-11-07 13:52:53', 'admin', '2020-07-10 16:55:12', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1229674163694841857, 'e41b69c57a941a3bbcce45032fe57605', 'AUTO在线表单ERP', '/online/cgformErpList/:code', 'modules/online/cgform/auto/erp/OnlCgformErpList', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 1, NULL, 'admin', '2020-02-18 15:49:00', 'admin', '2020-02-18 15:52:25', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1232123780958064642, 'f0675b52d89100ee88472b6800754a08', 'Online报表示例', '/online/cgreport/6c7f59741c814347905a938f06ee003c', 'modules/online/cgreport/auto/OnlCgreportAutoList', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 0, 1, 0, 0, NULL, 'admin', '2020-02-25 10:02:56', 'admin', '2020-05-02 15:37:30', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1235823781053313025, 'e41b69c57a941a3bbcce45032fe57605', 'AUTO在线内嵌子表', '/online/cgformInnerTableList/:code', 'modules/online/cgform/auto/innerTable/OnlCgformInnerTableList', NULL, NULL, 1, NULL, '1', 999.00, 0, NULL, 1, 1, 0, 1, NULL, 'admin', '2020-03-06 15:05:24', 'admin', '2020-03-06 15:07:42', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260922988733255681, '2a470fc0c3954d9dbb61de6d80846549', 'online订单管理', '/online/cgformInnerTableList/56efb74326e74064b60933f6f8af30ea', '111111', NULL, NULL, 1, NULL, '1', 11.00, 0, NULL, 0, 1, 0, 0, NULL, 'admin', '2020-05-14 21:20:42', 'admin', '2020-09-09 15:31:48', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260923256208216065, '2a470fc0c3954d9dbb61de6d80846549', 'online用户报表', '/online/cgreport/1260179852088135681', '333333', NULL, NULL, 1, NULL, '1', 11.00, 0, NULL, 0, 1, 0, 0, NULL, 'admin', '2020-05-14 21:21:46', 'admin', '2020-09-09 15:31:54', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260928341675982849, '3f915b2769fc80648e92d04e84ca059d', '添加按钮', NULL, NULL, NULL, NULL, 2, 'user:add', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-05-14 21:41:58', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260929666434318338, '3f915b2769fc80648e92d04e84ca059d', '用户编辑', NULL, NULL, NULL, NULL, 2, 'user:edit', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-05-14 21:47:14', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260931366557696001, '3f915b2769fc80648e92d04e84ca059d', '表单性别可见', '', NULL, NULL, NULL, 2, 'user:sex', '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-05-14 21:53:59', 'admin', '2020-05-14 21:57:00', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1260933542969458689, '3f915b2769fc80648e92d04e84ca059d', '禁用生日字段', NULL, NULL, NULL, NULL, 2, 'user:form:birthday', '2', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-05-14 22:02:38', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1265162119913824258, '08e6b9dc3c04489c8e1ff2ce6f105aa4', '路由网关', '/isystem/gatewayroute', 'system/SysGatewayRouteList', NULL, NULL, 1, NULL, '1', 0.00, 0, NULL, 1, 1, 0, 0, NULL, NULL, '2020-05-26 14:05:30', 'admin', '2020-09-09 14:47:52', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1280350452934307841, 'd7d6e2e4e2934f2c9385a623fd98c6f3', '租户管理', '/isys/tenant', 'system/TenantList', NULL, NULL, 1, NULL, '1', 10.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-07 11:58:30', 'admin', '2020-07-10 15:46:35', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1280464606292099074, '2a470fc0c3954d9dbb61de6d80846549', '图片裁剪', '/jeecg/ImagCropper', 'jeecg/ImagCropper', NULL, NULL, 1, NULL, '1', 9.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-07 19:32:06', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287715272999944193, '2a470fc0c3954d9dbb61de6d80846549', 'JVXETable示例', '/jeecg/j-vxe-table-demo', 'layouts/RouteView', NULL, NULL, 1, NULL, '1', 0.10, 0, '', 1, 0, 0, 0, NULL, 'admin', '2020-07-27 19:43:40', 'admin', '2020-09-09 14:52:06', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287715783966834689, '1287715272999944193', '普通示例', '/jeecg/j-vxe-table-demo/normal', 'jeecg/JVXETableDemo', NULL, NULL, 1, NULL, '1', 1.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-27 19:45:42', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287716451494510593, '1287715272999944193', '布局模板', '/jeecg/j-vxe-table-demo/layout', 'jeecg/JVxeDemo/layout-demo/Index', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-27 19:48:21', NULL, NULL, 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287718919049691137, '1287715272999944193', '即时保存', '/jeecg/j-vxe-table-demo/jsbc', 'jeecg/JVxeDemo/demo/JSBCDemo', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-27 19:57:36', 'admin', '2020-07-27 20:03:37', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287718938179911682, '1287715272999944193', '弹出子表', '/jeecg/j-vxe-table-demo/tczb', 'jeecg/JVxeDemo/demo/PopupSubTable', NULL, NULL, 1, NULL, '1', 4.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-27 19:57:41', 'admin', '2020-07-27 20:03:47', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1287718956957810689, '1287715272999944193', '无痕刷新', '/jeecg/j-vxe-table-demo/whsx', 'jeecg/JVxeDemo/demo/SocketReload', NULL, NULL, 1, NULL, '1', 5.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-07-27 19:57:44', 'admin', '2020-07-27 20:03:57', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1304032910990495745, 'e41b69c57a941a3bbcce45032fe57605', 'AUTO在线表单TAB', '/online/cgformTabList/:code', 'modules/online/cgform/auto/tab/OnlCgformTabList', NULL, NULL, 1, NULL, '1', 8.00, 0, NULL, 1, 1, 0, 1, NULL, 'admin', '2020-09-10 20:24:08', 'admin', '2020-09-10 20:36:37', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1335960713267093506, '1205097455226462210', '积木报表设计', '{{ window._CONFIG[\'domianURL\'] }}/jmreport/list?token=${token}', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 0.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2020-12-07 22:53:50', 'admin', '2020-12-08 09:28:06', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES (1352200630711652354, 'f0675b52d89100ee88472b6800754a08', 'Redis监控', '{{ window._CONFIG[\'domianURL\'] }}/jmreport/view/1352160857479581696?token=${token}', 'layouts/IframePageView', NULL, NULL, 1, NULL, '1', 5.00, 0, '', 1, 1, 0, 0, NULL, 'admin', '2021-01-21 18:25:28', 'admin', '2021-04-07 14:00:57', 0, 0, '1', 1);
INSERT INTO `sys_permission` VALUES (1365187528377102337, '2a470fc0c3954d9dbb61de6d80846549', '一对多JVxeTable', '/jeecg/JeecgOrderMainListForJVxeTable', 'jeecg/JeecgOrderMainListForJVxeTable', NULL, NULL, 1, NULL, '1', 2.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2021-02-26 14:30:45', 'admin', '2021-02-26 14:32:05', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1387612436586065922, '2a470fc0c3954d9dbb61de6d80846549', '第三方APP消息测试', '/jeecg/ThirdAppMessageTest', 'jeecg/ThirdAppMessageTest', NULL, NULL, 1, NULL, '1', 3.00, 0, NULL, 1, 1, 0, 0, NULL, 'admin', '2021-04-29 11:39:20', 'admin', '2021-04-29 11:39:27', 0, 0, '1', 0);
INSERT INTO `sys_permission` VALUES (1453700142363447296, '1453698703599734784', '新消息', '/aa/bb', NULL, '/a', NULL, 1, NULL, '0', NULL, 0, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1465918103992012800, '', '首页', '/index', NULL, '/', NULL, 0, NULL, '0', 1.00, 0, 'Home', 0, 0, 0, 0, NULL, NULL, NULL, '1072806377661009920', NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1465918509816090624, '1465918280685457408', '电子产品', '/goods/electronic', NULL, 'goods', NULL, 1, NULL, '0', NULL, 0, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1473581962080948224, '', '系统管理', '/system', NULL, '/system', NULL, 0, NULL, '0', 2.00, 0, 'setting', 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1473582179501084672, '1473581962080948224', '用户管理', '/system/user', NULL, '/', NULL, 1, NULL, '0', 2.10, 0, 'setting', 0, 0, 0, 0, NULL, NULL, NULL, '1427569011109007360', NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1473582427938099200, '1473581962080948224', '角色管理', '/system/role', NULL, './system/role', NULL, 1, NULL, '0', 2.20, 0, 'smile', 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1484422820422356992, '1473581962080948224', '部门管理', '/system/depart', NULL, '/', NULL, 1, NULL, '0', 2.30, 0, 'smile', 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1484422944443731968, '1473581962080948224', '菜单管理', '/system/permission', NULL, '/', NULL, 1, NULL, '0', 2.40, 0, 'smile', 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, 0);
INSERT INTO `sys_permission` VALUES (1484526307277672448, '1473581962080948224', '接口管理', '/system/interface', NULL, '/', NULL, 1, NULL, '0', 2.50, 0, 'smile', 0, 0, 0, 0, NULL, NULL, NULL, '1072806377661009920', NULL, 0, 0, NULL, 0);

-- ----------------------------
-- Table structure for sys_permission_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_api`;
CREATE TABLE `sys_permission_api`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(64) NOT NULL COMMENT '用户主键',
  `api_id` bigint(64) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468126825224998935 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_api
-- ----------------------------
INSERT INTO `sys_permission_api` VALUES (1468126825224998927, 1473582179501084672, 1488871396011544576);
INSERT INTO `sys_permission_api` VALUES (1468126825224998928, 1473582179501084672, 1489436645588602880);
INSERT INTO `sys_permission_api` VALUES (1468126825224998929, 1473582179501084672, 1489436797967667200);
INSERT INTO `sys_permission_api` VALUES (1468126825224998930, 1473582179501084672, 1489437014003683328);
INSERT INTO `sys_permission_api` VALUES (1468126825224998931, 1473582179501084672, 1489443280474214400);
INSERT INTO `sys_permission_api` VALUES (1468126825224998932, 1473582179501084672, 1489447701182550016);
INSERT INTO `sys_permission_api` VALUES (1468126825224998933, 1465918103992012800, 1489890932387090432);
INSERT INTO `sys_permission_api` VALUES (1468126825224998934, 1465918103992012800, 1489890822261444608);

-- ----------------------------
-- Table structure for sys_permission_bk
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_bk`;
CREATE TABLE `sys_permission_bk`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
  `type` int(2) NOT NULL COMMENT '权限类型，页面-1，按钮-2',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表达式',
  `method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后端接口访问方式',
  `sort` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(64) NOT NULL COMMENT '父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_bk
-- ----------------------------
INSERT INTO `sys_permission_bk` VALUES (1072806379288399872, '测试页面', '/test', 1, 'page:test', NULL, 1, 0);
INSERT INTO `sys_permission_bk` VALUES (1072806379313565696, '测试页面-查询', '/**/test', 2, 'btn:test:query', 'GET', 1, 1072806379288399872);
INSERT INTO `sys_permission_bk` VALUES (1072806379330342912, '测试页面-添加', '/**/test', 2, 'btn:test:insert', 'POST', 2, 1072806379288399872);
INSERT INTO `sys_permission_bk` VALUES (1072806379342925824, '监控在线用户页面', '/monitor', 1, 'page:monitor:online', NULL, 2, 0);
INSERT INTO `sys_permission_bk` VALUES (1072806379363897344, '在线用户页面-查询', '/**/api/monitor/online/user', 2, 'btn:monitor:online:query', 'GET', 1, 1072806379342925824);
INSERT INTO `sys_permission_bk` VALUES (1072806379384868864, '在线用户页面-踢出', '/**/api/monitor/online/user/kickout', 2, 'btn:monitor:online:kickout', 'DELETE', 2, 1072806379342925824);
INSERT INTO `sys_permission_bk` VALUES (1072806379384868865, '部门', '/sys/sysDepart/**', 2, 'btn:depart:add', 'POST', 2, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1072806379208708096, '管理员', '超级管理员', '2021-08-17 17:52:29.670000', '2021-08-17 17:52:29.670000');
INSERT INTO `sys_role` VALUES (1072806379238068224, '普通用户', '普通用户', '2021-08-17 17:52:29.670000', '2021-08-17 17:52:29.670000');
INSERT INTO `sys_role` VALUES (1460231318645379072, 'asdf', NULL, '2021-11-15 21:00:50.813000', '2021-11-15 21:00:50.814000');
INSERT INTO `sys_role` VALUES (1460231340111826944, 'sadfsaf', NULL, '2021-11-15 21:00:55.927000', '2021-11-15 21:00:55.927000');
INSERT INTO `sys_role` VALUES (1460231416523657216, 'asdfdsa', NULL, '2021-11-15 21:01:14.145000', '2021-11-15 21:01:14.145000');
INSERT INTO `sys_role` VALUES (1460231435389636608, 'fsadfsa', NULL, '2021-11-15 21:01:18.642000', '2021-11-15 21:01:18.642000');
INSERT INTO `sys_role` VALUES (1460231459360083968, 'asdfaf', NULL, '2021-11-15 21:01:24.358000', '2021-11-15 21:01:24.358000');
INSERT INTO `sys_role` VALUES (1460231497884766208, '1', NULL, '2021-11-15 21:01:33.543000', '2021-11-15 21:01:33.543000');
INSERT INTO `sys_role` VALUES (1460231514607456256, '11', NULL, '2021-11-15 21:01:37.530000', '2021-11-15 21:01:37.530000');
INSERT INTO `sys_role` VALUES (1460231566344196096, 'bbbbb', NULL, '2021-11-15 21:01:49.865000', '2021-11-15 21:01:49.866000');
INSERT INTO `sys_role` VALUES (1460231655166971904, 'asdfsaf', NULL, '2021-11-15 21:02:11.041000', '2021-11-15 21:02:11.041000');
INSERT INTO `sys_role` VALUES (1460231671025635328, 'asdfasf', NULL, '2021-11-15 21:02:14.823000', '2021-11-15 21:02:14.823000');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  `permission_id` bigint(64) NOT NULL COMMENT '权限主键',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data_rule_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate_date` datetime NULL DEFAULT NULL,
  `operate_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1460231318645379072, 1205098241075453957, '1465535184173666304', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231416523657216, 1205098241175453954, '1465605442221772800', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231416523657216, 1205098241175453954, '1465605442272104448', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231340111826944, 1205098241175453954, '1465605501659254784', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231340111826944, 1232123780958064642, '1465605501667643393', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231340111826944, 1352200630711652354, '1465605501671837696', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1072806379238068224, 1205098241075453958, '1465605527403892736', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231566344196096, 1232123780958064642, '1465605566696132608', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231566344196096, 1352200630711652354, '1465605566700326912', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231655166971904, 1205098241175453954, '1465658659697725440', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1460231340111826944, 1205098241075453958, '1465866387380637696', NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (1072806379208708096, 1205098241075453957, '1465876236709531648', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `gender` int(2) NULL DEFAULT NULL COMMENT '性别，男-1，女-2',
  `depart_ids` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '负责部门',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态，启用-1，禁用-0',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0正常1删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1072806377661009920, 'admin', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', '17300000000', 'admin@xkcoding.com', '2021-08-16', 1, '1427797901068865536', 1, '2021-08-17 17:52:29.670000', '2022-01-18 22:02:40.357000', 0);
INSERT INTO `sys_user` VALUES (1072806378780889088, 'user1111', '$2a$10$usGEHhsQ6vhw.ME9e3XwmeT7P.Vcbl7yGadv0Z.J/dpehRfs27j0q', '普通用户', '17300001111', 'user@xkcoding.com', '2021-08-22', 1, '1427796982902165504,1468925598234710016,1468924150767816704,1468493118348333056', 1, '2021-08-17 17:52:29.670000', '2022-02-05 17:04:31.384000', 0);
INSERT INTO `sys_user` VALUES (1427569011109007360, 'baocai', '$2a$10$lW9pOtX1w7JyHI0Vr9Efiee8uuT0uI5rv6tFtXg6MNCqAsscUK7DK', '包材', NULL, NULL, '2021-12-28', NULL, '1428320641978863616,1427797816570417152', 1, '2021-08-17 17:52:29.670000', '2022-01-21 15:10:53.303000', 0);
INSERT INTO `sys_user` VALUES (1428883368665288704, 'gebaocai', '$2a$10$ur/YUiLVYSUqpO2Djoc.J.MmTK0X48OUmqWO1yYfLoPSTcl6M7REm', NULL, NULL, NULL, NULL, NULL, NULL, 1, '2021-08-21 08:55:17.120000', '2021-08-21 08:55:17.121000', 0);
INSERT INTO `sys_user` VALUES (1428884666437472256, 'gebaocai2', '$2a$10$xT8GcIVmHruGVypA/1kj5eMk9ZNhc.V0Ybdgmxqw6FogNhY0xmJHS', NULL, NULL, NULL, NULL, NULL, NULL, 1, '2021-08-21 09:00:26.532000', '2021-08-21 09:00:26.534000', 0);
INSERT INTO `sys_user` VALUES (1428886418767351808, 'gebaocai3', '$2a$10$VtAVcyYVZO3wTyQOH8bHUuFtknVGbQeezDxELTL9Jkjp1u6D5tcMy', NULL, NULL, NULL, '2021-08-21', NULL, NULL, 1, '2021-08-21 09:07:24.321000', '2021-08-21 09:07:24.323000', 0);
INSERT INTO `sys_user` VALUES (1462672212619300864, 'admin11', '$2a$10$n4MH.0rZCgxOdykicY4uzu2SDu7tfa3GLSbdTNEAeffekm9Ay2W5K', '111', NULL, NULL, '2021-11-25', NULL, '1427797907993661440,1427797901068865536,1427796982902165504,1428320641978863616,1427797816570417152,1427797912087302144', 1, '2021-11-22 14:40:05.288000', '2021-11-25 15:24:01.914000', 0);
INSERT INTO `sys_user` VALUES (1468126825002700800, 'aaaaaa', '$2a$10$FKcfdYqn9VPohcjHObRMn.sU8orLJJN2IIr9UqlGv6czKJOraX2jy', 'asdfsdafdsaf', NULL, NULL, '2021-12-06', NULL, '1427797901068865536,1427796982902165504,1428320641978863616', 1, '2021-12-07 15:54:46.222000', '2021-12-07 19:48:35.699000', 0);

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_depart`;
CREATE TABLE `sys_user_depart`  (
  `user_id` bigint(64) NOT NULL COMMENT '用户主键',
  `depart_id` bigint(64) NOT NULL COMMENT '角色主键',
  `id` bigint(64) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1427797907993661440, 1463770450512449536);
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1427797901068865536, 1463770450525032448);
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1427796982902165504, 1463770450529226752);
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1428320641978863616, 1463770450533421056);
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1427797816570417152, 1463770450537615360);
INSERT INTO `sys_user_depart` VALUES (1462672212619300864, 1427797912087302144, 1463770450546003968);
INSERT INTO `sys_user_depart` VALUES (1468126825002700800, 1427797901068865536, 1468185669007446016);
INSERT INTO `sys_user_depart` VALUES (1468126825002700800, 1427796982902165504, 1468185669015834624);
INSERT INTO `sys_user_depart` VALUES (1468126825002700800, 1428320641978863616, 1468185669020028928);
INSERT INTO `sys_user_depart` VALUES (1427569011109007360, 1428320641978863616, 1469650050643791872);
INSERT INTO `sys_user_depart` VALUES (1427569011109007360, 1427797816570417152, 1469650050664763392);
INSERT INTO `sys_user_depart` VALUES (1072806378780889088, 1427797901068865536, 1482669379748499456);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(64) NOT NULL COMMENT '用户主键',
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1468126825224998913 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1072806377661009920, 1072806379208708096, 1);
INSERT INTO `sys_user_role` VALUES (1428883368665288704, 1, 1428883368979861504);
INSERT INTO `sys_user_role` VALUES (1428883368665288704, 2, 1428883368988250112);
INSERT INTO `sys_user_role` VALUES (1428883368665288704, 3, 1428883368992444416);
INSERT INTO `sys_user_role` VALUES (1428884666437472256, 1, 1428884666689130496);
INSERT INTO `sys_user_role` VALUES (1428884666437472256, 2, 1428884666697519104);
INSERT INTO `sys_user_role` VALUES (1428884666437472256, 3, 1428884666701713408);
INSERT INTO `sys_user_role` VALUES (1428886418767351808, 1, 1428886419044175872);
INSERT INTO `sys_user_role` VALUES (1428886418767351808, 2, 1428886419056758784);
INSERT INTO `sys_user_role` VALUES (1428886418767351808, 3, 1428886419060953088);
INSERT INTO `sys_user_role` VALUES (1427569011109007360, 1072806379238068224, 1463349507726118912);
INSERT INTO `sys_user_role` VALUES (1427569011109007360, 1460231318645379072, 1463349664454676480);
INSERT INTO `sys_user_role` VALUES (1427569011109007360, 1460231416523657216, 1463349664463065088);
INSERT INTO `sys_user_role` VALUES (1462672212619300864, 1460231318645379072, 1463769288405028864);
INSERT INTO `sys_user_role` VALUES (1462672212619300864, 1460231340111826944, 1463770450600529920);
INSERT INTO `sys_user_role` VALUES (1072806378780889088, 1072806379238068224, 1465119080439746560);
INSERT INTO `sys_user_role` VALUES (1072806378780889088, 1460231340111826944, 1465119080443940864);
INSERT INTO `sys_user_role` VALUES (1072806378780889088, 1460231318645379072, 1465119080448135168);
INSERT INTO `sys_user_role` VALUES (1072806378780889088, 1072806379208708096, 1465119080448135169);
INSERT INTO `sys_user_role` VALUES (1468126825002700800, 1072806379238068224, 1468126825224998912);

SET FOREIGN_KEY_CHECKS = 1;
