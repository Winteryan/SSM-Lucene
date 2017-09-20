/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lucenetest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-20 16:15:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('12', '颜飞龙', '我是一个烽火云创的员工');
INSERT INTO `person` VALUES ('13', '梅博', '18岁的天空');
INSERT INTO `person` VALUES ('14', 'asd', 'asd');
INSERT INTO `person` VALUES ('15', '梅博', '20');
INSERT INTO `person` VALUES ('16', '颜飞龙', '中国');
INSERT INTO `person` VALUES ('17', '颜飞龙', '烽火云创');
INSERT INTO `person` VALUES ('18', '梅博', '你好');
INSERT INTO `person` VALUES ('20', '梅博', '烽火国际');
