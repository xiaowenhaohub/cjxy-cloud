/*
 Navicat Premium Data Transfer

 Source Server         : www.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : www.xiaowenhao.ren:3306
 Source Schema         : cjxycloud_auth

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 08/03/2023 17:57:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_account
-- ----------------------------
DROP TABLE IF EXISTS `auth_account`;
CREATE TABLE `auth_account` (
  `uid` bigint unsigned NOT NULL COMMENT '全平台用户唯一id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `create_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建ip',
  `status` tinyint NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `user_id` bigint NOT NULL COMMENT '用户id',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE,
  KEY `idx_account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='统一账户信息';

-- ----------------------------
-- Records of auth_account
-- ----------------------------
BEGIN;
INSERT INTO `auth_account` (`uid`, `create_time`, `update_time`, `account`, `password`, `create_ip`, `status`, `user_id`) VALUES (112303, '2023-03-04 20:49:56', '2023-03-04 20:49:56', '1945829064', '$2a$10$DPX6X9Cd5UtQsr61Qyn4FuaumldN8UnDjSc/xiJ8rdvkx59weLTWW', '127.0.0.1', 1, 110705);
INSERT INTO `auth_account` (`uid`, `create_time`, `update_time`, `account`, `password`, `create_ip`, `status`, `user_id`) VALUES (112304, '2023-03-07 10:55:39', '2023-03-07 10:55:39', '1945829065', '$2a$10$DPX6X9Cd5UtQsr61Qyn4FuaumldN8UnDjSc/xiJ8rdvkx59weLTWW', '127.0.0.1', 1, 110706);
COMMIT;

-- ----------------------------
-- Table structure for sys_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role`;
CREATE TABLE `sys_auth_role` (
  `uid` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_id`,`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_auth_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112110, 1);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112017, 2);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112110, 2);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112206, 2);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112300, 2);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112303, 2);
INSERT INTO `sys_auth_role` (`uid`, `role_id`) VALUES (112304, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '全平台用户唯一id',
  `status` tinyint NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `menu_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `perms` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (1, 1, '用户列表', 'system:user:list', '2022-08-21 02:44:45', '2022-08-21 02:44:45');
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (2, 1, '修改用户', 'system:user:edit', '2022-08-21 02:45:39', '2022-08-21 02:45:39');
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (3, 1, '获取课表', 'system:course:query', '2022-08-21 03:05:04', '2022-08-21 03:05:04');
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (4, 1, '好友管理', 'common:social:relationship', '2023-03-07 09:59:40', '2023-03-07 10:01:29');
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (5, 1, '朋友圈', 'common:social:friendCircle', '2023-03-07 10:01:04', '2023-03-07 10:01:04');
INSERT INTO `sys_menu` (`menu_id`, `status`, `menu_name`, `perms`, `create_time`, `update_time`) VALUES (6, 1, '用户信息', 'common:user:query', '2023-03-07 20:20:39', '2023-03-07 20:20:39');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '全平台用户唯一id',
  `status` tinyint NOT NULL COMMENT '状态 1:启用 0:禁用 -1:删除',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `status`, `role_name`, `role_key`, `create_time`, `update_time`) VALUES (1, 1, '超级管理员', 'admin', '2022-08-21 02:43:26', '2022-08-21 03:03:48');
INSERT INTO `sys_role` (`role_id`, `status`, `role_name`, `role_key`, `create_time`, `update_time`) VALUES (2, 1, '普通用户', 'common', '2022-08-21 03:03:36', '2022-08-21 03:04:02');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 2);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 3);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 4);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 5);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
