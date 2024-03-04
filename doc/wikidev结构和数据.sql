/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : wikidev

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 04/03/2024 21:58:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sort` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent`(`parent` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 504 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (100, 0, '前端开发', 100);
INSERT INTO `category` VALUES (101, 100, 'Vue', 101);
INSERT INTO `category` VALUES (102, 100, 'HTML && CSS', 102);
INSERT INTO `category` VALUES (200, 0, 'Java', 200);
INSERT INTO `category` VALUES (201, 200, '基础应用', 201);
INSERT INTO `category` VALUES (202, 200, '框架应用', 202);
INSERT INTO `category` VALUES (300, 0, 'Python', 300);
INSERT INTO `category` VALUES (301, 300, '基础应用', 301);
INSERT INTO `category` VALUES (302, 300, '进阶方向应用', 302);
INSERT INTO `category` VALUES (400, 0, '数据库', 400);
INSERT INTO `category` VALUES (401, 400, 'MySQL', 401);
INSERT INTO `category` VALUES (500, 0, '其他', 500);
INSERT INTO `category` VALUES (501, 500, '服务器', 501);
INSERT INTO `category` VALUES (502, 500, '开发工具', 502);
INSERT INTO `category` VALUES (503, 500, '热门服务端语言', 503);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, '这是Vue基础的内容');
INSERT INTO `content` VALUES (2, '这是Vue组件的内容');
INSERT INTO `content` VALUES (3, '这是HTML基础的内容');
INSERT INTO `content` VALUES (4, '这是CSS基础的内容');
INSERT INTO `content` VALUES (5, '这是Java基础的内容');
INSERT INTO `content` VALUES (6, '这是Python基础的内容');
INSERT INTO `content` VALUES (7, '这是MySQL基础的内容');
INSERT INTO `content` VALUES (8, '这是服务器基础的内容');

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ebook_id` bigint NULL DEFAULT NULL,
  `parent` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sort` int NULL DEFAULT NULL,
  `view_count` int NULL DEFAULT NULL,
  `vote_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ebook_id`(`ebook_id` ASC) USING BTREE,
  INDEX `idx_parent`(`parent` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doc
-- ----------------------------
INSERT INTO `doc` VALUES (1, 1, 0, 'Vue基础', 1, 105, 19);
INSERT INTO `doc` VALUES (2, 1, 1, 'Vue组件', 2, 203, 20);
INSERT INTO `doc` VALUES (3, 2, 0, 'HTML基础', 1, 300, 30);
INSERT INTO `doc` VALUES (4, 2, 3, 'CSS基础', 2, 400, 40);
INSERT INTO `doc` VALUES (5, 3, 0, 'Java基础', 1, 500, 50);
INSERT INTO `doc` VALUES (6, 4, 0, 'Python基础', 1, 600, 60);
INSERT INTO `doc` VALUES (7, 5, 0, 'MySQL基础', 1, 700, 70);
INSERT INTO `doc` VALUES (8, 6, 0, '服务器基础', 1, 800, 80);

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category1_id` bigint NULL DEFAULT NULL,
  `category2_id` bigint NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `doc_count` int NULL DEFAULT NULL,
  `view_count` int NULL DEFAULT NULL,
  `vote_count` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category1_id`(`category1_id` ASC) USING BTREE,
  INDEX `idx_category2_id`(`category2_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES (1, 'Vue入门指南', 100, 101, '这是一本关于Vue的入门指南', 'cover1.jpg', 2, 308, 39);
INSERT INTO `ebook` VALUES (2, 'HTML && CSS基础教程', 100, 102, '这是一本关于HTML和CSS的基础教程', 'cover2.jpg', 2, 700, 70);
INSERT INTO `ebook` VALUES (3, 'Java基础应用', 200, 201, '这是一本关于Java基础应用的书', 'cover3.jpg', 1, 500, 50);
INSERT INTO `ebook` VALUES (4, 'Python基础应用', 300, 301, '这是一本关于Python基础应用的书', 'cover4.jpg', 1, 600, 60);
INSERT INTO `ebook` VALUES (5, 'MySQL入门指南', 400, 401, '这是一本关于MySQL的入门指南', 'cover5.jpg', 1, 700, 70);
INSERT INTO `ebook` VALUES (6, '服务器基础知识', 500, 501, '这是一本关于服务器基础知识的书', 'cover6.jpg', 1, 800, 80);
INSERT INTO `ebook` VALUES (7, 'Vue入门指南', 100, 101, '这是一本关于Vue的入门指南', 'cover1.jpg', 10, 1000, 100);
INSERT INTO `ebook` VALUES (8, 'HTML && CSS基础教程', 100, 102, '这是一本关于HTML和CSS的基础教程', 'cover2.jpg', 20, 2000, 200);
INSERT INTO `ebook` VALUES (9, 'Java基础应用', 200, 201, '这是一本关于Java基础应用的书', 'cover3.jpg', 30, 3000, 300);
INSERT INTO `ebook` VALUES (14, 'Python基础应用', 300, 301, '这是一本关于Python基础应用的书', 'cover4.jpg', 40, 4000, 400);
INSERT INTO `ebook` VALUES (15, 'MySQL入门指南', 400, 401, '这是一本关于MySQL的入门指南', 'cover5.jpg', 50, 5000, 500);
INSERT INTO `ebook` VALUES (16, '服务器基础知识', 500, 501, '这是一本关于服务器基础知识的书', 'cover6.jpg', 60, 6000, 600);

-- ----------------------------
-- Table structure for ebook_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `ebook_snapshot`;
CREATE TABLE `ebook_snapshot`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ebook_id` bigint NOT NULL,
  `date` datetime NOT NULL,
  `view_count` int NULL DEFAULT NULL,
  `vote_count` int NULL DEFAULT NULL,
  `view_increase` int NULL DEFAULT NULL,
  `vote_increase` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ebook_id`(`ebook_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook_snapshot
-- ----------------------------
INSERT INTO `ebook_snapshot` VALUES (1, 1, '2024-03-04 00:00:00', 308, 39, 308, 39);
INSERT INTO `ebook_snapshot` VALUES (2, 2, '2024-03-04 00:00:00', 700, 70, 700, 70);
INSERT INTO `ebook_snapshot` VALUES (3, 3, '2024-03-04 00:00:00', 500, 50, 500, 50);
INSERT INTO `ebook_snapshot` VALUES (4, 4, '2024-03-04 00:00:00', 600, 60, 600, 60);
INSERT INTO `ebook_snapshot` VALUES (5, 5, '2024-03-04 00:00:00', 700, 70, 700, 70);
INSERT INTO `ebook_snapshot` VALUES (6, 6, '2024-03-04 00:00:00', 800, 80, 800, 80);
INSERT INTO `ebook_snapshot` VALUES (8, 7, '2024-03-04 00:00:00', 1000, 100, 1000, 100);
INSERT INTO `ebook_snapshot` VALUES (9, 8, '2024-03-04 00:00:00', 2000, 200, 2000, 200);
INSERT INTO `ebook_snapshot` VALUES (10, 9, '2024-03-04 00:00:00', 3000, 300, 3000, 300);
INSERT INTO `ebook_snapshot` VALUES (11, 14, '2024-03-04 00:00:00', 4000, 400, 4000, 400);
INSERT INTO `ebook_snapshot` VALUES (12, 15, '2024-03-04 00:00:00', 5000, 500, 5000, 500);
INSERT INTO `ebook_snapshot` VALUES (13, 16, '2024-03-04 00:00:00', 6000, 600, 6000, 600);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xftxyz', '通晓宇宙', '773270df1066191b7f061f9e633fb95a');

SET FOREIGN_KEY_CHECKS = 1;
