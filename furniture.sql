/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : furniture

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 03/04/2024 22:10:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cartitem
-- ----------------------------
DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE `cartitem`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `total` decimal(10, 2) NULL DEFAULT NULL,
  `memberid` int(10) NOT NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cartitem
-- ----------------------------
INSERT INTO `cartitem` VALUES (20, 'ssm', 2, 221.00, 442.00, 1, 'assets/images/product-image/default.jpg');
INSERT INTO `cartitem` VALUES (2, '栽花银桶', 1, 30.00, 30.00, 1, 'assets/images/product-image/2.jpg');
INSERT INTO `cartitem` VALUES (21, 'pytahod', 1, 2.00, 2.00, 1, 'assets/images/product-image/default.jpg');
INSERT INTO `cartitem` VALUES (1, '舒适小椅', 2, 60.00, 120.00, 1, 'assets/images/product-image/1.jpg');
INSERT INTO `cartitem` VALUES (2, '栽花银桶', 1, 30.00, 30.00, 22, 'assets/images/product-image/default.jpg');
INSERT INTO `cartitem` VALUES (20, 'ssm', 1, 221.00, 221.00, 22, 'assets/images/product-image/default.jpg');
INSERT INTO `cartitem` VALUES (1, '舒适小椅', 1, 60.00, 60.00, 23, 'assets/images/product-image/default.jpg');
INSERT INTO `cartitem` VALUES (2, '栽花银桶', 1, 30.00, 30.00, 23, 'assets/images/product-image/default.jpg');

-- ----------------------------
-- Table structure for furn
-- ----------------------------
DROP TABLE IF EXISTS `furn`;
CREATE TABLE `furn`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `business` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `sales` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of furn
-- ----------------------------
INSERT INTO `furn` VALUES (1, '舒适小椅', '大象家居', 60.00, '23', 4, 'assets/images/product-image/1.jpg');
INSERT INTO `furn` VALUES (2, '栽花银桶', '大象家居', 30.00, '9', 4, 'assets/images/product-image/2.jpg');
INSERT INTO `furn` VALUES (20, 'ssm', 'springboot', 221.00, '36', 11, 'assets/images/product-image/cat.png');
INSERT INTO `furn` VALUES (21, '北欧夜灯', '海豹家居', 30.00, '12', 20, 'assets/images/product-image/12.jpg');
INSERT INTO `furn` VALUES (22, '1111they', '大象家居', 1999.00, '102', 78, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (23, '3124', '大象家居', 231.00, '23', 7, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (24, '32423a', '大象家居', 12312.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (25, '1244~~~', '大象家居', 1221.00, '111', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (26, '123', '大象家居', 314.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (27, '124a', '大象家居', 1231.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (28, '356', '大象家居', 434.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (29, '568a', '大象家居', 345.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (30, 'sds', '大象家居', 345.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (31, 'sdgwa', '大象家居', 43534.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (32, 'dfhe', '大象家居', 435.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (34, 'fjda', '大象家居', 363.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (35, 'fgera', '大象家居', 3436.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (36, 'dfgd', '大象家居', 3454.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (37, '24gw', '大象家居', 5673.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (39, 'asf', 'afsqw', 3.00, '5', 6, 'assets/images/product-image/2.jpg');
INSERT INTO `furn` VALUES (40, 'ADdf', 'asdg', 3.00, '5', 6, 'assets/images/product-image/2.jpg');
INSERT INTO `furn` VALUES (49, '居然沙发', '居然之家', 100.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (50, '海尔沙发', '大象家居', 1222.00, '100', 80, 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES (51, '金毛狗', '大象家居', 500.00, '20', 5, 'assets/images/product-image/dog.jpg');
INSERT INTO `furn` VALUES (52, '小德牧犬', '大象家居', 500.00, '100', 20, 'assets/images/product-image/dog2.png');
INSERT INTO `furn` VALUES (53, '哈士奇座椅', '大象家居', 320.00, '2', 50, 'assets/images/product-image/hashiqi.jpg');
INSERT INTO `furn` VALUES (54, '海报坐骑', '大象家居', 420.00, '7', 10, 'assets/images/product-image/5269_haibao.jpg');
INSERT INTO `furn` VALUES (55, '比亚迪', '大象家居', 79800.00, '100', 80, 'assets/images/product-image/207_BYD.jpg');
INSERT INTO `furn` VALUES (56, '东方小桌子', '大象家居', 120.00, '32', 22, 'assets/images/product-image/82846_8.jpg');
INSERT INTO `furn` VALUES (57, '台式小台灯', '大象家居', 220.00, '30', 12, 'assets/images/product-image/92454_13.jpg');
INSERT INTO `furn` VALUES (58, '温式大床', '大象家居', 320.00, '100', 55, 'assets/images/product-image/19796_5.jpg');
INSERT INTO `furn` VALUES (59, '蓝皮桌椅', '大象家居', 320.00, '22', 40, 'assets/images/product-image/14586_9.jpg');
INSERT INTO `furn` VALUES (60, '男人的衣柜', '大象家居', 550.00, '20', 24, 'assets/images/product-image/49056_10.jpg');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'milan1234', '6e48efd5ee769ef43674c95f0819b3a7', 'milan1234@qq.com');
INSERT INTO `member` VALUES (2, 'jack', '4ff9fc6e4e5d5f590c4f2134a8cc96d1', 'jack@qq.com');
INSERT INTO `member` VALUES (3, 'smith', 'a66e44736e753d4533746ced572ca821', 'smith@qq.com');
INSERT INTO `member` VALUES (9, 'milan123', '49e34051a5bb3df733080908649b9ad1', 'milan123@qq.com');
INSERT INTO `member` VALUES (11, 'pasture123', '4fbfa61ae13792a7fc28d05019143d92', 'pasture123@qq.com');
INSERT INTO `member` VALUES (12, 'mary123', 'f387c152606d845d3c4fcb4137b0c084', 'mary@qq.com');
INSERT INTO `member` VALUES (13, 'zhangsan', '01d7f40760960e7bd9443513f22ab9af', 'zhangsan@qq.com');
INSERT INTO `member` VALUES (19, 'asdiawu', 'e10adc3949ba59abbe56e057f20f883e', '1234435@qq.com');
INSERT INTO `member` VALUES (20, 'sqddaswdq', 'e10adc3949ba59abbe56e057f20f883e', 'sqddaswdq@qq.com');
INSERT INTO `member` VALUES (21, 'qdedaswfq', 'e10adc3949ba59abbe56e057f20f883e', 'pasture123@qq.com');
INSERT INTO `member` VALUES (22, 'javaweb', 'cfebd6c962e27d470c7c51b4bd05fb36', 'javaweb@qq.com');
INSERT INTO `member` VALUES (23, 'ajax123', '0cc52cb257762ebc0b893c9a8877f419', 'ajax123@qq.com');
INSERT INTO `member` VALUES (24, 'tom1234', 'b17f6bd009703d6f800760d3b9222e26', 'tom1234@qq.com');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dateTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('202404011221084423', '2024-04-92 12:21:08', 4311.00, '0', 12);
INSERT INTO `order` VALUES ('202404020907394492', '2024-04-93 09:07:39', 120.00, '0', 13);
INSERT INTO `order` VALUES ('202404020929499313', '2024-04-93 09:29:49', 251.00, '0', 23);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `total_price` decimal(10, 2) NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (1, '舒适小椅', 1, 60.00, 60.00, '202404011221084423');
INSERT INTO `orderitem` VALUES (2, '栽花银桶', 1, 30.00, 30.00, '202404011221084423');
INSERT INTO `orderitem` VALUES (20, 'ssm', 1, 221.00, 221.00, '202404011221084423');
INSERT INTO `orderitem` VALUES (21, 'pytahod', 1, 2.00, 2.00, '202404011221084423');
INSERT INTO `orderitem` VALUES (22, '1111they', 2, 1999.00, 3998.00, '202404011221084423');
INSERT INTO `orderitem` VALUES (1, '舒适小椅', 2, 60.00, 120.00, '202404020907394492');
INSERT INTO `orderitem` VALUES (20, 'ssm', 1, 221.00, 221.00, '202404020929499313');
INSERT INTO `orderitem` VALUES (2, '栽花银桶', 1, 30.00, 30.00, '202404020929499313');

SET FOREIGN_KEY_CHECKS = 1;
