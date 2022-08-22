/*
 Navicat Premium Data Transfer

 Source Server         : aliyun.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : cjxycloud_auth

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 21/08/2022 03:46:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_account
-- ----------------------------
DROP TABLE IF EXISTS `auth_account`;
CREATE TABLE `auth_account`  (
  `uid` bigint(0) UNSIGNED NOT NULL COMMENT '全平台用户唯一id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `create_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建ip',
  `status` tinyint(0) NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `uk_user_id`(`user_id`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '统一账户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_account
-- ----------------------------
INSERT INTO `auth_account` VALUES (112017, '2022-08-21 03:25:40', '2022-08-21 03:25:40', '1945829064', '$2a$10$8g2D6mOljzpRIeKzTk4Wl.24OAl9akgnEtYBNYvsVzGecl/z44ZLG', '127.0.0.1', 1, 110409);

-- ----------------------------
-- Table structure for sys_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role`;
CREATE TABLE `sys_auth_role`  (
  `uid` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_id`, `uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auth_role
-- ----------------------------
INSERT INTO `sys_auth_role` VALUES (112014, 1);
INSERT INTO `sys_auth_role` VALUES (112017, 2);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '全平台用户唯一id',
  `status` tinyint(0) NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `menu_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `perms` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 1, '用户列表', 'system:user:list', '2022-08-21 02:44:45', '2022-08-21 02:44:45');
INSERT INTO `sys_menu` VALUES (2, 1, '修改用户', 'system:user:edit', '2022-08-21 02:45:39', '2022-08-21 02:45:39');
INSERT INTO `sys_menu` VALUES (3, 1, '获取课表', 'system:course:query', '2022-08-21 03:05:04', '2022-08-21 03:05:04');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '全平台用户唯一id',
  `status` tinyint(0) NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, '超级管理员', 'admin', '2022-08-21 02:43:26', '2022-08-21 03:03:48');
INSERT INTO `sys_role` VALUES (2, 1, '普通用户', 'common', '2022-08-21 03:03:36', '2022-08-21 03:04:02');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);

SET FOREIGN_KEY_CHECKS = 1;
