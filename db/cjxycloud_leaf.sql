/*
 Navicat Premium Data Transfer

 Source Server         : www.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : www.xiaowenhao.ren:3306
 Source Schema         : cjxycloud_leaf

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 08/03/2023 17:58:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leaf_alloc
-- ----------------------------
DROP TABLE IF EXISTS `leaf_alloc`;
CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '区分业务',
  `max_id` bigint unsigned NOT NULL DEFAULT '1' COMMENT '该biz_tag目前所被分配的ID号段的最大值',
  `step` int NOT NULL COMMENT '每次分配的号段长度',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `random_step` int unsigned NOT NULL DEFAULT '1' COMMENT '每次getid时随机增加的长度，这样就不会有连续的id了',
  PRIMARY KEY (`biz_tag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of leaf_alloc
-- ----------------------------
BEGIN;
INSERT INTO `leaf_alloc` (`biz_tag`, `max_id`, `step`, `update_time`, `description`, `random_step`) VALUES ('cjxycloud-auth', 112400, 100, '2023-03-04 20:45:11', '数据库中auth_account这张表的uid', 10);
INSERT INTO `leaf_alloc` (`biz_tag`, `max_id`, `step`, `update_time`, `description`, `random_step`) VALUES ('cjxycloud-user', 110800, 100, '2023-03-04 20:45:11', '数据库中user这张表的user_id', 10);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
