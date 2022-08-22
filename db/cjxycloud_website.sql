/*
 Navicat Premium Data Transfer

 Source Server         : aliyun.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : cjxycloud_website

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 22/08/2022 22:57:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `classes` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级',
  `semester` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期',
  `weekly` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '周次',
  `institute` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院校',
  `specialty` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `course_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'json课程表',
  `status` int(0) NOT NULL DEFAULT 1 COMMENT '状态 1 正常 0 无效',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'B1902', '2021-2022-2', '1', '信息工程学院', NULL, '[[{\"courseName\":\"大数据处理与智能决策\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"李睿--\",\"weekly\":\"1-16(周)[01-02节]\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"云计算部署与实施\",\"courseRoom\":\"【南3】南机房3209\",\"teacherName\":\"杨兴贵--\",\"weekly\":\"1-16(周)[01-02节]\"},{\"courseName\":\"高级语言程序设计\",\"courseRoom\":\"【南3】南机房3408\",\"teacherName\":\"林世明高级工程师\",\"weekly\":\"1-16(周)[01-02节]\"},{\"courseName\":\"云应用系统开发\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"李睿--\",\"weekly\":\"1-16(周)[01-02节]\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"云计算部署与实施\",\"courseRoom\":\"【南3】南机房3209\",\"teacherName\":\"杨兴贵--\",\"weekly\":\"1-16(周)[03-04节]\"},{\"courseName\":\"求职能力提升与训练\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"黄海峰--\",\"weekly\":\"1,3,5,7,9,11,13,15(周)[03-04节]\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"云应用系统开发\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"李睿--\",\"weekly\":\"1-16(周)[05-06节]\"},{\"courseName\":\"大数据处理与智能决策\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"李睿--\",\"weekly\":\"1-16(周)[05-06节]\"},{\"courseName\":\"大数据处理与智能决策\",\"courseRoom\":\"【南3】南机房3203\",\"teacherName\":\"李睿--\",\"weekly\":\"1-16(单周)[05-06节]\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"网页与网站设计\",\"courseRoom\":\"【南3】南机房3408\",\"teacherName\":\"仇岗助教\",\"weekly\":\"1-16(周)[07-08节]\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"软件测试与维护\",\"courseRoom\":\"【南1】南机房1204\",\"teacherName\":\"牛芳--\",\"weekly\":\"1-16(周)[07-08节]\"}],[{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"},{\"courseName\":\"\",\"courseRoom\":\"\",\"teacherName\":\"\",\"weekly\":\"\"}]]', 1, '2022-08-22 22:55:20', '2022-08-22 22:55:20');

SET FOREIGN_KEY_CHECKS = 1;
