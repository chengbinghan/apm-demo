/*
Navicat MySQL Data Transfer

Source Server         : pinpoint
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-14 16:17:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for my_table
-- ----------------------------
DROP TABLE IF EXISTS `my_table`;
CREATE TABLE `my_table` (
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of my_table
-- ----------------------------
INSERT INTO `my_table` VALUES ('1', 'zhangsan', '1112', '214', '111@qq.com');
INSERT INTO `my_table` VALUES ('98', 'lisi', 'uytrewq', '214', 'JHGFDSA98765432');
