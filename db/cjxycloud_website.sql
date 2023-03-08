/*
 Navicat Premium Data Transfer

 Source Server         : www.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : www.xiaowenhao.ren:3306
 Source Schema         : cjxycloud_website

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 08/03/2023 17:58:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `classes` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级',
  `semester` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期',
  `weekly` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '周次',
  `institute` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院校',
  `specialty` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  `course_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'json课程表',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 无效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='课程表';

-- ----------------------------
-- Records of courses
-- ----------------------------
BEGIN;
INSERT INTO `courses` (`id`, `classes`, `semester`, `weekly`, `institute`, `specialty`, `course_info`, `status`, `create_time`, `update_time`) VALUES (8, 'B1902', '2021-2022-2', '1', '信息工程学院', NULL, '[[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}]]', 1, '2023-03-04 13:44:20', '2023-03-04 13:44:20');
INSERT INTO `courses` (`id`, `classes`, `semester`, `weekly`, `institute`, `specialty`, `course_info`, `status`, `create_time`, `update_time`) VALUES (9, 'B1902', '2021-2022-2', '1', '计算机工程系', NULL, '[[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}]]', 1, '2023-03-04 13:44:42', '2023-03-04 13:44:42');
COMMIT;

-- ----------------------------
-- Table structure for institute_info
-- ----------------------------
DROP TABLE IF EXISTS `institute_info`;
CREATE TABLE `institute_info` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `institute` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of institute_info
-- ----------------------------
BEGIN;
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('14', '教务处');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('30', '人文与传播学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('31', '国家通用语言教育学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('32', '外国语学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('33', '经济与管理学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('34', '数学与数据科学学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('35', '信息工程学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('36', '物理与材料科学学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('37', '化学与化工学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('38', '体育与健康学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('39', '音乐与舞蹈学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('40', '美术与设计学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('41', '马克思主义学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('42', '教育科学学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('44', '航空学院');
INSERT INTO `institute_info` (`id`, `institute`) VALUES ('FAE692683E5347608CDDFD71BDD35935', '能源与控制工程学院');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
