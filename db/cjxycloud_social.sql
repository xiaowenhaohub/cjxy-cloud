/*
 Navicat Premium Data Transfer

 Source Server         : www.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : www.xiaowenhao.ren:3306
 Source Schema         : cjxycloud_social

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 08/03/2023 17:58:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_messages
-- ----------------------------
DROP TABLE IF EXISTS `chat_messages`;
CREATE TABLE `chat_messages` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `from_user_account` varchar(30) DEFAULT NULL COMMENT '发送用户学号',
  `to_user_account` bigint NOT NULL COMMENT '接收用户学号',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息正文',
  `state` tinyint DEFAULT '0' COMMENT '消息状态: 0 未读,1 已读',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='聊天记录表';

-- ----------------------------
-- Records of chat_messages
-- ----------------------------
BEGIN;
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (1, '1945829064', 1945829064, '1', 0, '2023-03-06 14:12:15', '2023-03-08 15:32:41');
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (2, '1945829064', 1945829065, '2', 0, '2023-03-06 15:50:18', '2023-03-08 15:32:44');
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (3, '1945829065', 1945829064, '3111', 0, '2023-03-06 15:58:04', '2023-03-08 15:32:47');
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (4, '1945829064', 1945829064, '4111', 0, '2023-03-06 16:00:37', '2023-03-08 15:32:48');
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (5, '1945829064', 1945829065, '5111', 0, '2023-03-06 16:03:38', '2023-03-08 15:32:51');
INSERT INTO `chat_messages` (`id`, `from_user_account`, `to_user_account`, `content`, `state`, `create_time`, `update_time`) VALUES (6, '1945829065', 1945829064, '1116', 0, '2023-03-06 16:12:46', '2023-03-08 15:32:54');
COMMIT;

-- ----------------------------
-- Table structure for friend_circle_comment
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_comment`;
CREATE TABLE `friend_circle_comment` (
  `comment_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `friend_circle_id` bigint unsigned DEFAULT NULL COMMENT '朋友圈id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论内容',
  `like_count` int unsigned DEFAULT '0' COMMENT '点赞数',
  `root_comment_id` bigint DEFAULT NULL COMMENT '顶级评论id',
  `to_comment_id` bigint DEFAULT NULL COMMENT '回复目标评论id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  KEY `friend_circle_id` (`friend_circle_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='朋友圈评论表';

-- ----------------------------
-- Records of friend_circle_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for friend_circle_like
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_like`;
CREATE TABLE `friend_circle_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `friend_circle_id` bigint NOT NULL COMMENT '被点赞的朋友圈id',
  `user_id` bigint NOT NULL COMMENT '点赞的用户id',
  `status` tinyint(1) DEFAULT '1' COMMENT '点赞状态，0取消，1点赞',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `friend_circle_id` (`friend_circle_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='朋友圈点赞表';

-- ----------------------------
-- Records of friend_circle_like
-- ----------------------------
BEGIN;
INSERT INTO `friend_circle_like` (`id`, `friend_circle_id`, `user_id`, `status`, `create_time`, `update_time`) VALUES (17, 2, 110608, 0, '2022-09-22 10:07:13', '2022-09-22 11:13:15');
INSERT INTO `friend_circle_like` (`id`, `friend_circle_id`, `user_id`, `status`, `create_time`, `update_time`) VALUES (18, 1, 110608, 1, '2022-09-22 10:34:43', '2022-09-22 11:13:14');
COMMIT;

-- ----------------------------
-- Table structure for friend_circle_message
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_message`;
CREATE TABLE `friend_circle_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `picture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT '' COMMENT '图片',
  `location` varbinary(100) DEFAULT '' COMMENT '位置',
  `liked_count` int DEFAULT '0' COMMENT '点赞数',
  `is_delete` tinyint(1) DEFAULT '1' COMMENT '1 正常， 0 删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='朋友圈表';

-- ----------------------------
-- Records of friend_circle_message
-- ----------------------------
BEGIN;
INSERT INTO `friend_circle_message` (`id`, `user_id`, `content`, `picture`, `location`, `liked_count`, `is_delete`, `create_time`, `update_time`) VALUES (3, 110608, 'xiaowenhao', 'xxxx', '', 0, 0, '2022-09-20 14:22:25', '2022-09-23 09:06:46');
INSERT INTO `friend_circle_message` (`id`, `user_id`, `content`, `picture`, `location`, `liked_count`, `is_delete`, `create_time`, `update_time`) VALUES (4, 110608, 'test', 'xxxx', '', 0, 1, '2022-09-21 17:39:52', '2022-09-21 17:39:52');
INSERT INTO `friend_circle_message` (`id`, `user_id`, `content`, `picture`, `location`, `liked_count`, `is_delete`, `create_time`, `update_time`) VALUES (5, 110608, 'xiaowenhao 9/23 09:30', 'http://localhost', '', 0, 1, '2022-09-23 09:30:37', '2022-09-23 09:30:37');
COMMIT;

-- ----------------------------
-- Table structure for user_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_relationship`;
CREATE TABLE `user_relationship` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_account` varchar(30) DEFAULT NULL COMMENT '用户id',
  `friend_account` bigint NOT NULL COMMENT '朋友id',
  `rel_state` tinyint DEFAULT '0' COMMENT '是否好友(1:是 0:不是)(双向好友)',
  `alias_user` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '好友备注(USER_ID对FRIEND_ID的备注)',
  `alias_friend` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '好友备注(FRIEND_ID对USER_ID的备注)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_account` (`user_account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='好友表';

-- ----------------------------
-- Records of user_relationship
-- ----------------------------
BEGIN;
INSERT INTO `user_relationship` (`id`, `user_account`, `friend_account`, `rel_state`, `alias_user`, `alias_friend`, `create_time`, `update_time`) VALUES (2, '1945829064', 1945829065, 1, '小句号', NULL, '2023-03-07 11:01:02', '2023-03-08 11:27:30');
INSERT INTO `user_relationship` (`id`, `user_account`, `friend_account`, `rel_state`, `alias_user`, `alias_friend`, `create_time`, `update_time`) VALUES (3, '1945829064', 1945829064, 1, '小问号', '小问号', '2023-03-07 11:02:34', '2023-03-07 11:13:32');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
