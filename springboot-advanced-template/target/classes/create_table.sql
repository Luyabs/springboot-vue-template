/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : springboot_db

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/03/2023 00:12:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no description',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '计算机', '高级语言程序设计', 'SHU计算机系开山之作', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (2, '计算机', '面向对象程序设计 C++', 'OOP, C++', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (3, '计算机', '面向对象程序设计 Python', 'OOP, Python', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (4, '计算机', '面向对象程序设计 Java', 'OOP, Java, Kotlin, Groovy', '2023-03-08 12:48:02', '2023-03-08 21:51:47');
INSERT INTO `book` VALUES (5, '计算机', '数据结构 C++', 'DS, C++', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (6, '计算机', '数据结构 Python', 'DS, Python', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (7, '计算机', '计算机组成原理', '这本书没有简介', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (8, '计算机', '计算机网络', '这本书没有简介', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (9, '计算机', '操作系统', 'OS', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (10, '计算机', '软件工程', '这本书没有简介', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (11, '计算机', '数据库原理', '基于openGauss数据库', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (12, '计算机', '计算机体系结构', '', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (13, '数学', '高等数学', '', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (14, '数学', '线性代数', '', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (15, '数学', '概率论与数理统计', '', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (16, '物理', '普通物理学', '必修', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (17, '物理', '量子物理', '', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (18, '政治', '中国近现代史纲要', 'hi', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (29, '玄学', '闪现', 'magic!', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (53, '数学', '789', '4', '2023-03-08 12:48:02', '2023-03-08 12:48:02');
INSERT INTO `book` VALUES (84, '数学', '离散化学', 'test', '2023-03-08 12:49:25', '2023-03-08 12:49:25');
INSERT INTO `book` VALUES (85, '数学', '离散化学', 'test', '2023-03-08 12:49:28', '2023-03-08 12:49:28');
INSERT INTO `book` VALUES (86, '数学', '离散化学', 'test', '2023-03-08 12:49:29', '2023-03-08 12:49:29');
INSERT INTO `book` VALUES (87, '数学', '离散化学', 'test', '2023-03-08 12:49:29', '2023-03-08 12:49:29');
INSERT INTO `book` VALUES (88, '数学', '离散化学', 'test', '2023-03-08 12:49:29', '2023-03-08 12:49:29');
INSERT INTO `book` VALUES (89, '数学', '离散化学', 'test', '2023-03-08 12:49:30', '2023-03-08 12:49:30');
INSERT INTO `book` VALUES (90, '1', '2', '3', '2023-03-08 21:19:02', '2023-03-08 21:19:02');
INSERT INTO `book` VALUES (91, '1', '2', '3', '2023-03-08 21:19:05', '2023-03-08 21:19:05');
INSERT INTO `book` VALUES (92, '1', '2', '3', '2023-03-08 21:33:48', '2023-03-08 21:33:48');
INSERT INTO `book` VALUES (93, '3', '2', '1', '2023-03-08 21:55:55', '2023-03-08 21:55:55');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '丁真', '1375666', '1888-08-08');
INSERT INTO `user` VALUES (2, '珍珠', 'qwer', '2012-01-26');
INSERT INTO `user` VALUES (3, '锐克15', '2077', '2077-07-07');
INSERT INTO `user` VALUES (17, 'q', '1a2b3c', '2010-12-14');
INSERT INTO `user` VALUES (18, 'admin', '111111', '2023-03-03');

SET FOREIGN_KEY_CHECKS = 1;
