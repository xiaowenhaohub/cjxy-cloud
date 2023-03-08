/*
 Navicat Premium Data Transfer

 Source Server         : www.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : www.xiaowenhao.ren:3306
 Source Schema         : cjxycloud_user

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 08/03/2023 17:58:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL COMMENT 'ID',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `institute` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '院校',
  `specialty` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  `classes` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '班级',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `picture` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像图片路径',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 无效',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `uk_account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`user_id`, `account`, `institute`, `specialty`, `classes`, `real_name`, `nick_name`, `picture`, `create_time`, `update_time`, `status`) VALUES (110705, '1945829064', '信息工程学院', '计算机科学与技术（ICT）', 'B1902', '姜刚刚', 'xiaowenhao', 'http://43.132.148.227/api/mi/file/view/1608278675034345472', '2023-03-04 20:49:56', '2023-03-08 11:03:40', 1);
INSERT INTO `users` (`user_id`, `account`, `institute`, `specialty`, `classes`, `real_name`, `nick_name`, `picture`, `create_time`, `update_time`, `status`) VALUES (110706, '1945829065', '110706', '110706', 'B1902', '卢云鹏', '理解归理解', 'http://43.132.148.227/api/mi/file/view/1633308986612781056', '2023-03-07 10:56:36', '2023-03-08 11:52:21', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
