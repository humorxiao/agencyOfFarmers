/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-12 22:35:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `Admin_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Admin_Password` varchar(32) NOT NULL,
  `Admin_Name` varchar(10) NOT NULL,
  `Admin_Cell` varchar(11) NOT NULL COMMENT '必须为11位',
  `Admin_email` varchar(30) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Admin_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='管理员账户';

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('100', '25d55ad283aa400af464c76d713c07ad', '林天真', '15918746467', '15918746467@139.com', null);
INSERT INTO `admin_info` VALUES ('101', '25d55ad283aa400af464c76d713c07ad', 'default', '1', '1', '');
INSERT INTO `admin_info` VALUES ('102', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for cart_info
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info` (
  `Cart_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Goods_List` varchar(5000) DEFAULT NULL COMMENT '记录商品的主键号，用特殊符号#分隔',
  `Goods_Num` varchar(5000) DEFAULT NULL COMMENT '记录和商品列表对应的商品的数量，用特殊符号分隔',
  `Cart_Reserve_1` char(10) DEFAULT NULL,
  `Cart_Reserve_2` char(10) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Cart_PK`),
  CONSTRAINT `FK_User_Cart2` FOREIGN KEY (`Cart_PK`) REFERENCES `user_info` (`User_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8 COMMENT='用户的唯一购物车，主键与用户相同，唯一绑定。';

-- ----------------------------
-- Records of cart_info
-- ----------------------------
INSERT INTO `cart_info` VALUES ('100000', '', '', null, null, null);
INSERT INTO `cart_info` VALUES ('100003', '', '', null, null, null);

-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `delivery_address`;
CREATE TABLE `delivery_address` (
  `Deliv_PK` int(11) NOT NULL,
  `Deliv_Cell` varchar(11) NOT NULL COMMENT '必须为11位',
  `Deliv_Name` varchar(10) NOT NULL,
  `Deliv_Address` varchar(200) NOT NULL,
  `Deliv_Zipcode` varchar(6) NOT NULL COMMENT '必须为6位',
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Deliv_PK`),
  CONSTRAINT `FK_User_Delivery_Address2` FOREIGN KEY (`Deliv_PK`) REFERENCES `user_info` (`User_PK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的唯一记录的收货地址，主键与用户相同，唯一绑定。';

-- ----------------------------
-- Records of delivery_address
-- ----------------------------
INSERT INTO `delivery_address` VALUES ('100000', '', '', '', '', null);
INSERT INTO `delivery_address` VALUES ('100003', '13421166393', '林先生', '广东省揭阳市某某区某某街道', '522000', null);

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `Goods_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Goods_Name` varchar(100) DEFAULT NULL,
  `Goods_Type` int(11) NOT NULL COMMENT '商品类别，1为水果，2为加工品，3为粮蔬，4为水产，5为禽畜。若有补充累加1。',
  `Prod_Season` int(11) NOT NULL,
  `Prod_Blossom` varchar(50) NOT NULL,
  `Prod_Fruit` varchar(50) NOT NULL,
  `Prod_Mature` varchar(50) NOT NULL,
  `Prod_Expiration` varchar(50) NOT NULL,
  `Goods_Mark` varchar(1) DEFAULT NULL,
  `Goods_Show` varchar(1) DEFAULT NULL,
  `Goods_Picture` varchar(200) DEFAULT NULL,
  `Goods_Reserve_1` varchar(5) DEFAULT NULL,
  `Goods_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Goods_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100019 DEFAULT CHARSET=utf8 COMMENT='商品的相关信息';

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('100000', '鹰嘴桃', '1', '1', '3月份', '4-6月份', '7月份', '5-7天', '0', '2', 'yingzuitao.jpg', '1', null, null);
INSERT INTO `goods_info` VALUES ('100002', '板栗', '3', '1', '3月份', '8-9月份', '10月份', '一年', '0', '2', 'banli.jpg', '2', null, null);
INSERT INTO `goods_info` VALUES ('100003', '蜂蜜', '2', '2', '无', '无', '无', '1年', '0', '2', 'fengmi.jpg', '6', null, null);
INSERT INTO `goods_info` VALUES ('100004', '三华李', '1', '1', '2-3月份', '4-5月份', '6月份', '10天左右', '0', '2', 'sanhuali.jpg', '8', null, null);
INSERT INTO `goods_info` VALUES ('100005', '水鱼', '4', '-1', '无', '无', '不定期', '鲜活类', '0', '2', 'shuiyu.jpg', '9', null, null);
INSERT INTO `goods_info` VALUES ('100006', '早李', '1', '1', '2-3月份', '3-4月份', '4-5月份', '10天左右', '0', '1', 'zaoli.jpg', '18', null, null);
INSERT INTO `goods_info` VALUES ('100007', '茶枝柑', '1', '1', '5，6月份', '7，8月份', '10月份', '7天左右', '0', '1', 'chazhigan.jpg', '19', null, null);
INSERT INTO `goods_info` VALUES ('100008', '贡柑', '1', '1', '5，6月份', '7，8月份', '10月份', '7天左右', '0', '1', 'gonggan.jpg', '20', null, null);
INSERT INTO `goods_info` VALUES ('100009', '生猪', '5', '-1', '无', '无', '无', '鲜活类', '0', '1', 'zhu.jpg', '13', null, null);
INSERT INTO `goods_info` VALUES ('100010', '砂糖桔', '1', '1', '5，6月份', '7，8月份', '10月份', '7天左右', '0', '1', 'shatangji.jpg', '12', null, null);
INSERT INTO `goods_info` VALUES ('100011', '番薯', '3', '2', '无', '无', '6/10月份', '两个月左右', '0', '1', 'fanshu.jpg', '14', null, null);
INSERT INTO `goods_info` VALUES ('100012', '水稻', '3', '2', '无', '无', '7/12月份', '一年或以上', '0', '0', 'shuidao.jpg', '15', null, null);
INSERT INTO `goods_info` VALUES ('100013', '玉米', '3', '4', '无', '无', '每年每季', '5天左右', '0', '0', 'yumi.jpg', '11', null, null);
INSERT INTO `goods_info` VALUES ('100014', '百香果', '1', '2', '4，7月份', '6，8，10月份', '9，11，1月份', '10天左右', '0', '0', 'baixiangguo.jpg', '16', null, null);
INSERT INTO `goods_info` VALUES ('100015', '薯干', '2', '4', '无', '无', '每年每季', '5天左右', '0', '0', 'shugan.jpg', '21', null, null);
INSERT INTO `goods_info` VALUES ('100016', '生羊', '5', '-1', '无', '无', '无', '鲜活类', '0', '0', 'yangrou.jpg', '23', null, null);
INSERT INTO `goods_info` VALUES ('100017', '山地鸡', '5', '4', '无', '无', '无', '无', '0', '0', 'shandiji.jpg', '4', null, null);
INSERT INTO `goods_info` VALUES ('100018', '火蒜', '3', '4', '无', '无', '每年每季', '5天左右', '0', '0', 'huosuan.jpg', '22', null, null);

-- ----------------------------
-- Table structure for goods_log
-- ----------------------------
DROP TABLE IF EXISTS `goods_log`;
CREATE TABLE `goods_log` (
  `GL_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Goods_PK` int(11) NOT NULL,
  `Goods_In` int(11) DEFAULT NULL,
  `Goods_Out` int(11) DEFAULT NULL,
  `Goods_PriceChange` float DEFAULT NULL COMMENT '保留2位小数',
  `GL_Time` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `GL_Reserve_1` varchar(5) DEFAULT NULL,
  `GL_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`GL_PK`),
  KEY `FK_Goods_NumLog` (`Goods_PK`),
  CONSTRAINT `FK_Goods_NumLog` FOREIGN KEY (`Goods_PK`) REFERENCES `goods_info` (`Goods_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COMMENT='商品的进货，出售，存储量的记录。供管理员统计，记录。';

-- ----------------------------
-- Records of goods_log
-- ----------------------------
INSERT INTO `goods_log` VALUES ('100000', '100000', '100', '0', '0', '2017-03-29 16:03:54', null, null, null);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `Order_PK` int(11) NOT NULL AUTO_INCREMENT,
  `User_PK` int(11) NOT NULL,
  `Order_ID` varchar(18) DEFAULT NULL,
  `Goods_List` varchar(5000) DEFAULT NULL,
  `Goods_Num` varchar(5000) DEFAULT NULL,
  `Goods_Prices` varchar(5000) DEFAULT NULL,
  `Order_Time` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `Order_IsPay` tinyint(1) NOT NULL,
  `Order_PayTime` datetime DEFAULT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `Order_PayPrice` float NOT NULL COMMENT '保留2位小数',
  `Order_State` int(11) DEFAULT NULL COMMENT '1，未完成。2，取消。3，用户已支付，快递正在路上。4，用户已签收。5，用户已确认收货，订单完成。\r\n            （待定）',
  `Order_TrackNum` varchar(50) DEFAULT NULL,
  `Order_Aftersale` int(11) DEFAULT NULL COMMENT '商品若出现问题，提供售后服务的状态。由对应数字表示状态。\r\n            如1为无售后记录。2为正在退货。3为退货成功，已退款，售后完成。\r\n            （待定）',
  `Order_Reserve_1` varchar(1000) DEFAULT NULL,
  `Order_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Order_PK`),
  UNIQUE KEY `AK_Key_2` (`Order_ID`),
  KEY `FK_User_Order` (`User_PK`),
  CONSTRAINT `FK_User_Order` FOREIGN KEY (`User_PK`) REFERENCES `user_info` (`User_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100038 DEFAULT CHARSET=utf8 COMMENT='订单日志。供用户查询，管理员查询及管理，统计等。';

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('100000', '100003', '201703302003100003', '100000#', '2#', '12#', '2017-03-30 20:03:46', '1', '2017-03-30 20:03:46', '24', '5', '11111111111111', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', '\0', null);
INSERT INTO `order_info` VALUES ('100001', '100003', '201704010904100003', '100000#', '4#', '8#', '2017-04-01 09:04:23', '0', null, '32', '2', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', '\0', null);
INSERT INTO `order_info` VALUES ('100002', '100003', '201704011004100003', '100000#', '4#', '8#', '2017-04-01 10:04:41', '0', null, '32', '2', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', '\0', null);
INSERT INTO `order_info` VALUES ('100003', '100003', '201705240305100003', '100006#100001#', '2#1#', '2#500#', '2017-05-24 03:05:37', '0', null, '504', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100005', '100003', '201705240305100004', '100000#', '1#', '5#', '2017-05-24 03:05:41', '0', null, '5', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100008', '100003', '201705240305100005', '100000#', '1#', '8#', '2017-05-24 03:05:46', '0', null, '8', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100012', '100003', '201705240305100006', '100000#', '1#', '8#', '2017-05-24 03:05:52', '0', null, '8', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100013', '100003', '201705240905100003', '100010#', '1#', '5#', '2017-05-24 09:05:29', '0', null, '5', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100014', '100003', '201705241705100003', '100011#', '2#', '10#', '2017-05-24 17:05:31', '0', null, '20', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100015', '100003', '201705241805100003', '100001#', '1#', '500#', '2017-05-24 18:05:51', '0', null, '500', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100017', '100003', '201705241805100004', '100005#', '1#', '500#', '2017-05-24 18:05:09', '0', null, '500', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100020', '100003', '201705241805100005', '100005#', '1#', '500#', '2017-05-24 18:05:32', '0', null, '500', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100024', '100003', '201705241805100006', '100005#', '1#', '500#', '2017-05-24 18:05:13', '0', null, '500', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100029', '100003', '201705241805100007', '100005#', '1#', '500#', '2017-05-24 18:05:02', '0', null, '500', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100035', '100003', '201705241805100008', '100010#100004#', '1#2#', '5#2.5#', '2017-05-24 18:05:27', '0', null, '10', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100036', '100003', '201705251505100003', '100009#100006#', '2#2#', '2000#2#', '2017-05-25 15:05:17', '0', null, '4004', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);
INSERT INTO `order_info` VALUES ('100037', '100003', '201706020906100003', '100006#', '1#', '2#', '2017-06-02 09:06:01', '0', null, '2', '1', '', '0', '13421166393;林先生;广东省揭阳市某某区某某街道;522000;', null, null);

-- ----------------------------
-- Table structure for sign_in_log
-- ----------------------------
DROP TABLE IF EXISTS `sign_in_log`;
CREATE TABLE `sign_in_log` (
  `SignIn_PK` int(11) NOT NULL AUTO_INCREMENT,
  `SignIn_IsAdmin` tinyint(1) NOT NULL,
  `User_Pk` int(11) DEFAULT NULL,
  `Admin_Pk` int(11) DEFAULT NULL,
  `SignIn_Time` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `SignIn_Reserve_1` varchar(5) DEFAULT NULL,
  `SignIn_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SignIn_PK`),
  KEY `FK_User_login` (`User_Pk`),
  KEY `FK_admin_login` (`Admin_Pk`),
  CONSTRAINT `FK_User_login` FOREIGN KEY (`User_Pk`) REFERENCES `user_info` (`User_PK`),
  CONSTRAINT `FK_admin_login` FOREIGN KEY (`Admin_Pk`) REFERENCES `admin_info` (`Admin_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100024 DEFAULT CHARSET=utf8 COMMENT='系统的用户登录日志。';

-- ----------------------------
-- Records of sign_in_log
-- ----------------------------
INSERT INTO `sign_in_log` VALUES ('100001', '1', '100003', '0', '2017-03-30 14:54:39', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100002', '1', '100000', '0', '2017-03-30 16:06:11', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100003', '1', '100003', '0', '2017-03-30 19:36:43', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100004', '1', '100003', '0', '2017-03-31 00:24:16', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100005', '1', '100003', '0', '2017-03-31 09:59:03', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100006', '1', '100003', '0', '2017-03-31 15:44:44', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100007', '1', '100003', '0', '2017-04-01 09:07:48', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100008', '1', '100003', '0', '2017-04-01 10:24:06', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100009', '1', '100003', '0', '2017-05-22 23:13:16', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100010', '1', '100003', '0', '2017-05-23 10:18:31', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100011', '1', '100003', '0', '2017-05-23 13:09:08', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100012', '1', '100003', '0', '2017-05-24 03:02:02', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100013', '1', '100003', '0', '2017-05-24 09:46:56', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100014', '1', '100003', '0', '2017-05-24 10:51:19', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100015', '1', '100003', '0', '2017-05-24 17:35:11', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100016', '1', '100003', '0', '2017-05-24 17:47:39', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100017', '1', '100003', '0', '2017-05-24 17:50:17', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100018', '1', '100003', '0', '2017-05-24 18:02:13', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100019', '1', '100003', '0', '2017-05-24 20:18:02', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100020', '1', '100003', '0', '2017-05-25 00:47:59', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100021', '1', '100003', '0', '2017-05-25 15:50:13', null, null, null);
INSERT INTO `sign_in_log` VALUES ('100022', '0', '100003', '0', '2017-06-02 09:13:47', null, null, null);

-- ----------------------------
-- Table structure for union_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `union_goods_info`;
CREATE TABLE `union_goods_info` (
  `Union_Goods_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Union_PK` int(11) NOT NULL,
  `Goods_PK` int(11) NOT NULL,
  `Union_Name` varchar(200) NOT NULL,
  `Goods_Name` varchar(100) DEFAULT NULL,
  `Goods_Price` int(11) NOT NULL,
  `Union_Master` varchar(10) NOT NULL,
  `Union_Cell` varchar(11) NOT NULL COMMENT '必须为11位',
  `Goods_Type` int(11) NOT NULL COMMENT '商品类别，1为水果，2为加工品，3为粮蔬，4为水产，5为禽畜。若有补充累加1。',
  `Goods_Num` int(11) NOT NULL,
  `Reserved_1` varchar(5) DEFAULT NULL,
  `Reserved_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Union_Goods_PK`),
  KEY `FK_Goods_Union_Relation_1` (`Goods_PK`),
  KEY `FK_Goods_Union_Relation_2` (`Union_PK`),
  CONSTRAINT `FK_Goods_Union_Relation_1` FOREIGN KEY (`Goods_PK`) REFERENCES `goods_info` (`Goods_PK`),
  CONSTRAINT `FK_Goods_Union_Relation_2` FOREIGN KEY (`Union_PK`) REFERENCES `union_info` (`Union_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100034 DEFAULT CHARSET=utf8 COMMENT='记录每家合作社所生产的商品信息';

-- ----------------------------
-- Records of union_goods_info
-- ----------------------------
INSERT INTO `union_goods_info` VALUES ('100000', '100000', '100000', '连平县丰隆种植专业合作社', '鹰嘴桃', '8', '谢衍生', '13553227856', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100001', '100000', '100001', '连平县丰隆种植专业合作社', '茶叶', '500', '谢衍生', '13553227856', '2', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100002', '100000', '100002', '连平县丰隆种植专业合作社', '板栗', '18', '谢衍生', '13553227856', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100003', '100001', '100000', '连平县连兴种植专业合作社', '鹰嘴桃', '8', '谢海根', '13435513908', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100004', '100002', '100000', '连平县金生种养专业合作社', '鹰嘴桃', '8', '谢金坚', '13750237498', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100005', '100002', '100001', '连平县金生种养专业合作社', '茶叶', '500', '谢金坚', '13750237498', '2', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100006', '100002', '100002', '连平县金生种养专业合作社', '板栗', '18', '谢金坚', '13750237498', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100007', '100002', '100003', '连平县金生种养专业合作社', '蜂蜜', '100', '谢金坚', '13750237498', '2', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100008', '100002', '100004', '连平县金生种养专业合作社', '三华李', '3', '谢金坚', '13750237498', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100009', '100002', '100017', '连平县金生种养专业合作社', '山地鸡', '20', '谢金坚', '13750237498', '5', '200', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100010', '100003', '100004', '连平县连宏达种养专业合作社', '三华李', '3', '周志平', '13650668213', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100011', '100003', '100005', '连平县连宏达种养专业合作社', '水鱼', '500', '周志平', '13650668213', '4', '26', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100012', '100003', '100006', '连平县连宏达种养专业合作社', '早李', '2', '周志平', '13650668213', '1', '995', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100013', '100004', '100005', '连平县连生态种养专业合作社', '水鱼', '500', '周文达', '13435514020', '4', '26', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100014', '100004', '100008', '连平县连生态种养专业合作社', '贡柑', '7', '周文达', '13435514020', '1', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100015', '100004', '100010', '连平县连生态种养专业合作社', '砂糖桔', '5', '周文达', '13435514020', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100016', '100005', '100001', '连平县炜森种养专业合作社', '茶叶', '500', '谢房栋', '13553287443', '2', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100017', '100005', '100017', '连平县炜森种养专业合作社', '山地鸡', '20', '谢房栋', '13553287443', '5', '200', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100018', '100005', '100018', '连平县炜森种养专业合作社', '火蒜', '20', '谢房栋', '13553287443', '3', '500', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100019', '100006', '100012', '连平县陂头塔下种植专业合作社', '水稻', '2', '廖彩坤', '13690926063', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100020', '100006', '100013', '连平县陂头塔下种植专业合作社', '玉米', '5', '廖彩坤', '13690926063', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100021', '100006', '100014', '连平县陂头塔下种植专业合作社', '百香果', '10', '廖彩坤', '13690926063', '1', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100022', '100007', '100011', '连平县陂头跃丰种养专业合作社', '番薯', '10', '曾超和', '13435328821', '3', '994', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100023', '100007', '100012', '连平县陂头跃丰种养专业合作社', '水稻', '2', '曾超和', '13435328821', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100024', '100007', '100014', '连平县陂头跃丰种养专业合作社', '百香果', '10', '曾超和', '13435328821', '1', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100025', '100008', '100009', '连平县隆街镇龙埔竹鼠养殖专业合作社', '生猪', '2000', '李国雄', '13829325433', '5', '98', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100026', '100008', '100016', '连平县隆街镇龙埔竹鼠养殖专业合作社', '生羊', '100', '李国雄', '13829325433', '5', '200', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100027', '100008', '100017', '连平县隆街镇龙埔竹鼠养殖专业合作社', '山地鸡', '20', '李国雄', '13829325433', '5', '200', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100028', '100009', '100002', '连平县众联兴种植专业合作社', '板栗', '18', '邱志增', '13827804970', '3', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100029', '100009', '100003', '连平县众联兴种植专业合作社', '蜂蜜', '100', '邱志增', '13827804970', '2', '1000', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100030', '100009', '100004', '连平县众联兴种植专业合作社', '三华李', '3', '邱志增', '13827804970', '1', '998', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100031', '100010', '100011', '连平县绿强种养专业合作社', '番薯', '10', '谢冠强', '13509271567', '3', '994', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100032', '100010', '100015', '连平县绿强种养专业合作社', '薯干', '20', '谢冠强', '13509271567', '2', '500', null, null, null);
INSERT INTO `union_goods_info` VALUES ('100033', '100010', '100017', '连平县绿强种养专业合作社', '山地鸡', '20', '谢冠强', '13509271567', '5', '200', null, null, null);

-- ----------------------------
-- Table structure for union_info
-- ----------------------------
DROP TABLE IF EXISTS `union_info`;
CREATE TABLE `union_info` (
  `Union_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Union_Name` varchar(200) NOT NULL,
  `Union_Master` varchar(10) NOT NULL,
  `Union_License` varchar(30) NOT NULL,
  `Union_Address` varchar(200) NOT NULL,
  `Union_Establish` date NOT NULL COMMENT '格式为YYYY-MM-DD',
  `Union_Asset` int(11) NOT NULL,
  `Union_Tele` varchar(20) NOT NULL,
  `Union_Cell` varchar(11) NOT NULL COMMENT '必须为11位',
  `Union_Email` varchar(30) NOT NULL,
  `Union_Mark` varchar(1) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Union_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100040 DEFAULT CHARSET=utf8 COMMENT='记录合作社的相关信息';

-- ----------------------------
-- Records of union_info
-- ----------------------------
INSERT INTO `union_info` VALUES ('100000', '连平县丰隆种植专业合作社', '谢衍生', '441623NA000448X', '连平县陂头镇官岭村水背小姐1号', '2014-09-05', '1000000', '13553227856', '13553227856', '2401108883@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100001', '连平县连兴种植专业合作社', '谢海根', '93441623MA4UHY0B7D', '连平县上坪镇旗石村委太阳升42号', '2015-10-15', '1000000', '13435513908', '13435513908', '1817100012@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100002', '连平县金生种养专业合作社', '谢金坚', '93441623594067754W', '连平县溪山镇谢屋禾坑山', '2012-04-23', '500000', '13750237498', '13750237498', '无', '0', null);
INSERT INTO `union_info` VALUES ('100003', '连平县连宏达种养专业合作社', '周志平', '93441623MA4UHR2T87', '连平县内莞镇九连桃坪村河唇屋', '2015-09-30', '1000000', '13650668213', '13650668213', '无', '0', null);
INSERT INTO `union_info` VALUES ('100004', '连平县连生态种养专业合作社', '周文达', '93441623MA4UHT230X', '连平县内莞镇桃坪村河唇屋', '2015-09-30', '1000000', '13435514020', '13435514020', '703291134@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100005', '连平县炜森种养专业合作社', '谢房栋', '93441623MA4UJ1MY6B', '连平县上坪镇古坑村良田3号', '2015-10-14', '1000000', '13553287443', '13553287443', '无', '0', null);
INSERT INTO `union_info` VALUES ('100006', '连平县陂头塔下种植专业合作社', '廖彩坤', '93441623MA4UHYN460', '连平县陂头镇腊溪村委会塔下', '2015-10-14', '500000', '13690926063', '13690926063', '无', '0', null);
INSERT INTO `union_info` VALUES ('100007', '连平县陂头跃丰种养专业合作社', '曾超和', '93441623MA4UJ0XN30', '连平县陂头镇官岭村委会上屋', '2015-10-15', '500000', '13435328821', '13435328821', '无', '0', null);
INSERT INTO `union_info` VALUES ('100008', '连平县隆街镇龙埔竹鼠养殖专业合作社', '李国雄', '93441623MA4UURGY9B', '连平县隆街镇龙浦村埔前屋', '2016-09-20', '1500000', '13829325433', '13829325433', '无', '0', null);
INSERT INTO `union_info` VALUES ('100009', '连平县众联兴种植专业合作社', '邱志增', '93441623MA4UHQ2X83', '连平县上坪镇新陂村元背11号', '2015-09-25', '1000000', '13827804970', '13827804970', '1742433248@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100010', '连平县绿强种养专业合作社', '谢冠强', '93441623MA4UQFM71P', '连平县陂头镇新东街一巷7号', '2016-06-08', '500000', '0762-4772567', '13509271567', '无', '0', null);
INSERT INTO `union_info` VALUES ('100011', '连平县丰盛种植专业合作社', '谢碧华', '441623NA000322X', '连平县陂头镇官岭管理区黄梅村围背生产队', '2013-08-07', '500000', '13827855268', '13827855268', '无', '0', null);
INSERT INTO `union_info` VALUES ('100012', '连平县嘉嘉种养专业合作社', '谢明杯', '93441623MA4UHU2J41', '连平县上坪镇中村村水尾屋15号', '2015-09-30', '1200000', '15907627378', '15907627378', '无', '0', null);
INSERT INTO `union_info` VALUES ('100013', '连平县内莞镇七星墩种植专业合作社', '余妙俊', '441623NA000356X', '连平县内莞镇蓝州村七星墩', '2013-12-10', '1000000', '13570143182', '13570143182', '308839011@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100014', '连平县水口种养专业合作社', '何新学', '441623NA000517X', '连平县油溪镇九潭水口屋', '2015-05-08', '600000', '13536773468', '13536773468', '无', '0', null);
INSERT INTO `union_info` VALUES ('100015', '连平县陂头镇正方粮油专业合作社', '谢小军', '441623NA000255X', '连平县陂头镇连光村社头围15号', '2012-12-04', '800000', '13435349346', '13435349346', '497223903@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100016', '连平县彩安堂种养专业合作社', '廖彩安', '93441623MA4UHWHA1U', '连平县陂头镇三水村岩仔4号', '2015-10-09', '1000000', '15216927655', '15216927655', '无', '0', null);
INSERT INTO `union_info` VALUES ('100017', '连平县辉远种植专业合作社', '韦远新', '93441623MA4UJ81034', '连平县高莞镇二联村川龙屋66屋', '2015-10-23', '1000000', '13502322277', '13502322277', 'lpcool@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100018', '连平县上坪镇腾飞沙糖桔种植专业合作社', '谢房兴', '93441623315146856K', '连平县上坪镇布联村坑口屋25号', '2014-09-22', '-1', '13690956137', '13690956137', 'xiehuidang@126.com', '0', null);
INSERT INTO `union_info` VALUES ('100019', '连平县家兴种植专业合作社', '刘娘生', '93441623623MA4UJA1H8K', '连平县高莞镇高村村光头层1号', '2015-10-23', '1000000', '18707621205', '18707621205', '无', '0', null);
INSERT INTO `union_info` VALUES ('100020', '连平县森木花木苗圃场', '陈镜辉', '91441623073526329C', '连平县内莞中村', '2013-07-19', '-1', '13553226527', '13553226527', '无', '0', null);
INSERT INTO `union_info` VALUES ('100021', '连平县邱美灵种养专业合作社', '邱美灵', '93441623MA4UHQX130', '连平县元善镇警雄村委会内莞径6号', '2015-09-30', '1000000', '13119270383', '13119270383', '无', '0', null);
INSERT INTO `union_info` VALUES ('100022', '连平县旭旺种养专业合作社', '谢仁旭', '93441623MA4UJ0XH4X', '连平县上坪镇旗石村太阳升28号', '2015-10-15', '1000000', '13750208189', '13750208189', '105962447@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100023', '连平县水文种养专业合作社', '谢文水', '93441623MA4UHRK64N', '连平县上坪镇中村村委会水尾屋12号', '2015-10-09', '1000000', '13690905526', '13690905526', '无', '0', null);
INSERT INTO `union_info` VALUES ('100024', '连平县上坪惠联种植专业合作社', '谢文礼', '93441623MA4UJ1L95W', '连平县上坪镇布联村下新屋', '2015-10-14', '800000', '15019385285', '15019385285', '812725563@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100025', '连平县骏发种养专业合作社', '谢佛房', '93441623MA4UJ1390W', '连平县上坪镇新镇村塘头4号', '2015-10-16', '1000000', '15876232523', '15876232523', '无', '0', null);
INSERT INTO `union_info` VALUES ('100026', '连平县展望种植专业合作社', '谢振望', '93441623MA4UHYLE5U', '连平县上坪镇新镇村满池堂18号', '2015-10-14', '1000000', '13435513218', '13435513218', '1186358340@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100027', '连平县美晋园种养专业合作社', '谢晋营', '93441623MA4UHP4757', '连平县上坪镇旗石村委会19号', '2015-09-25', '800000', '13690970647', '13690970647', '652443409@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100028', '连平县峰金种植专业合作社', '谢丽青', '93441623MA4UHU2F1L', '连平县上坪镇旗石村石子角屋6号', '2015-09-30', '1000000', '13650660890', '13650660890', '无', '0', null);
INSERT INTO `union_info` VALUES ('100029', '连平县文钦种植专业合作社', '谢文钦', '93441623MA4UJ13747', '连平县上坪镇新镇村大古陂屋塘头3号', '2015-10-16', '1000000', '13553225861', '13553225861', '无', '0', null);
INSERT INTO `union_info` VALUES ('100030', '连平县国太种养专业合作社', '谢国太', '93441623MA4UHM530T', '连平县上坪镇古坑村105国道边', '2015-09-25', '1200000', '15986037676', '15986037676', '无', '0', null);
INSERT INTO `union_info` VALUES ('100031', '连平县太阳升种植专业合作社', '谢景增', '93441623068523131A', '连平县上坪镇旗石村太阳升28号', '2013-05-07', '3000000', '13690977586', '13690977586', '无', '0', null);
INSERT INTO `union_info` VALUES ('100032', '连平县正源果蔬种植专业合作社', '叶秋媛', '93441623MA4UHMF0XA', '连平县陂头连光村社头围14号', '2015-09-28', '1000000', '15819258161', '15819258161', '1053663096@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100033', '连平县福旺种养专业合作社', '谢幸福', '93441623MA4UHYXY5F', '连平县上坪镇古坑村105国道边', '2015-10-15', '1000000', '13827845478', '13827845478', '无', '0', null);
INSERT INTO `union_info` VALUES ('100034', '连平县胜辉种养专业合作社', '谢文胜', '93441623MA4UJ0MEXT', '连平县上平镇古坑村古坑围5号', '2015-10-14', '1200000', '13725655839', '13725655839', '无', '0', null);
INSERT INTO `union_info` VALUES ('100035', '连平县信利源种植专业合作社', '余碧然', '93441623MA4UJ0XEX8', '连平县内莞镇蓝州余屋七星墩', '2015-10-15', '1200000', '13825397368', '13825397368', '3069178105@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100036', '连平县文朗种植专业合作社', '谢文朗', '93441623MA4UJ1Y049', '连平县上坪镇下楼村水楼屋16号', '2015-10-15', '1000000', '13553227950', '13553227950', '无', '0', null);
INSERT INTO `union_info` VALUES ('100037', '连平县碣石下种植专业合作社', '谢房浅', '441623NA000410X', '连平县上坪镇古坑村上新7号', '2014-06-06', '1000000', '13553227958', '13553227958', '无', '0', null);
INSERT INTO `union_info` VALUES ('100038', '连平县绿佳种植专业合作社', '丘春伟', '441623NA000515X', '连平县上坪镇下洞村委会下余坑4号', '2015-05-14', '1000000', '18316920629', '18316920629', '33129681747@qq.com', '0', null);
INSERT INTO `union_info` VALUES ('100039', '连平县新龙绿野康种养专业合作社', '余房淦', '93441623MA4UJ4209F', '连平县元善镇新龙村东门路东23号', '2015-10-21', '1000000', '13380941939', '13380941939', '13376788278@189.cn', '0', null);

-- ----------------------------
-- Table structure for union_news
-- ----------------------------
DROP TABLE IF EXISTS `union_news`;
CREATE TABLE `union_news` (
  `News_PK` int(11) NOT NULL AUTO_INCREMENT,
  `News_Title` varchar(200) DEFAULT NULL,
  `News_Text` text,
  `News_Time` datetime DEFAULT NULL,
  `News_Mark` int(11) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`News_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of union_news
-- ----------------------------
INSERT INTO `union_news` VALUES ('1', '第一条公告', '<p>\r\n	logo是\r\n</p>\r\n<p>\r\n	<img src=\"/agencyOfFarmers/attached/image/20170627/20170627182148_321.png\" alt=\"\" />\r\n</p>\r\n<p>\r\n	经营商品有\r\n</p>\r\n<p>\r\n	<img src=\"/agencyOfFarmers/attached/image/20170627/20170627182300_941.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182300_770.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182300_180.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182300_760.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_892.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_108.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_211.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_399.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_80.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_71.jpg\" alt=\"\" /><img src=\"/agencyOfFarmers/attached/image/20170627/20170627182301_360.jpg\" alt=\"\" />\r\n</p>', '2017-06-27 18:06:18', '1', null);
INSERT INTO `union_news` VALUES ('2', '第二条公告', '<h2>\r\n	第二条公告\r\n</h2>\r\n<p>\r\n	纯文字好吗？好吧！必须得好。<span style=\"font-family:KaiTi_GB2312;\"><span style=\"font-family:KaiTi_GB2312;\">我</span><span style=\"background-color:#E53333;font-family:KaiTi_GB2312;\">没</span></span><span style=\"font-family:KaiTi_GB2312;\"><span style=\"background-color:#E53333;font-family:KaiTi_GB2312;\">图</span><span style=\"font-size:10px;background-color:#E53333;font-family:KaiTi_GB2312;\"></span><span style=\"background-color:#E53333;font-family:KaiTi_GB2312;\">了</span></span><span style=\"background-color:#E53333;font-family:KaiTi_GB2312;\">。</span><span style=\"background-color:#E56600;\"></span>\r\n</p>', '2017-06-27 18:06:24', '1', null);

-- ----------------------------
-- Table structure for union_staff
-- ----------------------------
DROP TABLE IF EXISTS `union_staff`;
CREATE TABLE `union_staff` (
  `Staff_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Union_PK` int(11) DEFAULT NULL,
  `Staff_Name` varchar(10) NOT NULL,
  `Staff_Sex` int(11) NOT NULL COMMENT '1为男，2为女，3为保密',
  `Staff_Birthday` date NOT NULL COMMENT '格式为YYYY-MM-DD',
  `Staff_Address` varchar(200) NOT NULL,
  `Staff_Phone` varchar(11) NOT NULL COMMENT '必须为11位',
  `Staff_ID` varchar(18) NOT NULL COMMENT '必须为18位',
  `Staff_Email` varchar(30) NOT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Staff_PK`),
  KEY `FK_Union_Staff_Relationship` (`Union_PK`),
  CONSTRAINT `FK_Union_Staff_Relationship` FOREIGN KEY (`Union_PK`) REFERENCES `union_info` (`Union_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100040 DEFAULT CHARSET=utf8 COMMENT='记录各合作社员工个人信息';

-- ----------------------------
-- Records of union_staff
-- ----------------------------
INSERT INTO `union_staff` VALUES ('100000', '100000', '谢衍生', '1', '1965-07-01', '连平县陂头镇官岭村水背小姐1号', '13553227856', '441623196507012017', '2401108883@qq.com', null);
INSERT INTO `union_staff` VALUES ('100001', '100001', '谢海根', '1', '1981-11-18', '连平县上坪镇旗石村委太阳升42号', '13435513908', '441623198111180654', '1817100012@qq.com', null);
INSERT INTO `union_staff` VALUES ('100002', '100002', '谢金坚', '1', '1967-08-18', '广东省连平县溪山镇百高村委会田心屋1号', '13750237498', '44252419670818241X', '无', null);
INSERT INTO `union_staff` VALUES ('100003', '100003', '周志平', '1', '1972-11-15', '连平县内莞镇九连桃坪村河唇屋', '13650668213', '441623197211154918', '无', null);
INSERT INTO `union_staff` VALUES ('100004', '100004', '周文达', '1', '1980-04-21', '连平县内莞镇桃坪村河唇屋', '13435514020', '441623198004214919', '703291134@qq.com', null);
INSERT INTO `union_staff` VALUES ('100005', '100005', '谢房栋', '1', '1979-03-26', '连平县上坪镇古坑村良田3号', '13553287443', '441623197903260631', '无', null);
INSERT INTO `union_staff` VALUES ('100006', '100006', '廖彩坤', '1', '1942-07-15', '连平县陂头镇腊溪村委会塔下', '13690926063', '442524194207152010', '无', null);
INSERT INTO `union_staff` VALUES ('100007', '100007', '曾超和', '1', '1959-02-24', '连平县陂头镇', '13435328821', '442524195902242012', '无', null);
INSERT INTO `union_staff` VALUES ('100008', '100008', '李国雄', '1', '1970-01-06', '连平县隆街镇龙浦村埔前屋', '13829325433', '442524197001062718', '无', null);
INSERT INTO `union_staff` VALUES ('100009', '100009', '邱志增', '1', '1975-05-07', '连平县上坪镇新陂村元背11号', '13827804970', '441623197505070613', '1742433248@qq.com', null);
INSERT INTO `union_staff` VALUES ('100010', '100010', '谢冠强', '1', '1966-09-14', '广东省连平县陂头镇街镇居委会老街', '13509271567', '442524196609142017', '无', null);
INSERT INTO `union_staff` VALUES ('100011', '100011', '谢碧华', '1', '1979-07-24', '连平县陂头镇官岭管理区黄梅村围背生产队', '13827855268', '441623197907242019', '无', null);
INSERT INTO `union_staff` VALUES ('100012', '100012', '谢明杯', '1', '1980-09-18', '连平县上坪镇中村村水尾屋15号', '15907627378', '441623198009180631', '无', null);
INSERT INTO `union_staff` VALUES ('100013', '100013', '余妙俊', '1', '1980-07-18', '连平县内莞镇蓝州村七星墩', '13570143182', '441623198007181016', '308839011@qq.com', null);
INSERT INTO `union_staff` VALUES ('100014', '100014', '何新学', '1', '1968-08-19', '连平县油溪镇九潭水口屋', '13536773468', '442524196808193773', '无', null);
INSERT INTO `union_staff` VALUES ('100015', '100015', '谢小军', '1', '1985-05-17', '连平县陂头镇连光村社头围15号', '13435349346', '441623198505172016', '497223903@qq.com', null);
INSERT INTO `union_staff` VALUES ('100016', '100016', '廖彩安', '1', '1959-01-06', '连平县陂头镇三水村岩仔4号', '15216927655', '441623195901062018', '无', null);
INSERT INTO `union_staff` VALUES ('100017', '100017', '韦远新', '1', '1976-07-18', '连平县高莞镇二联村川龙屋66屋', '13502322277', '441623197607184611', 'lpcool@qq.com', null);
INSERT INTO `union_staff` VALUES ('100018', '100018', '谢房兴', '1', '2014-09-22', '连平县上坪镇布联村坑口屋25号', '13690956137', '441623196505100611', 'xiehuidang@126.com', null);
INSERT INTO `union_staff` VALUES ('100019', '100019', '刘娘生', '1', '1962-03-07', '连平县高莞镇高村村光头层1号', '18707621205', '441623196203074614', '无', null);
INSERT INTO `union_staff` VALUES ('100020', '100020', '陈镜辉', '1', '1953-11-02', '连平县内莞中村', '13553226527', '442524195311020012', '无', null);
INSERT INTO `union_staff` VALUES ('100021', '100021', '邱美灵', '1', '1966-11-10', '连平县元善镇警雄村委会内莞径6号', '13119270383', '442524196611101310', '无', null);
INSERT INTO `union_staff` VALUES ('100022', '100022', '谢仁旭', '1', '1986-11-07', '连平县上坪镇旗石村太阳升28号', '13750208189', '441623198611070611', '105962447@qq.com', null);
INSERT INTO `union_staff` VALUES ('100023', '100023', '谢文水', '1', '1977-01-01', '连平县上坪镇', '13690905526', '441623197701010031', '无', null);
INSERT INTO `union_staff` VALUES ('100024', '100024', '谢文礼', '1', '1979-09-04', '连平县上坪镇布联村下新屋', '15019385285', '441623197909040613', '812725563@qq.com', null);
INSERT INTO `union_staff` VALUES ('100025', '100025', '谢佛房', '1', '1969-10-08', '连平县上坪镇新镇村塘头4号', '15876232523', '442524196910080652', '无', null);
INSERT INTO `union_staff` VALUES ('100026', '100026', '谢振望', '1', '1981-07-01', '连平县上坪镇新镇村满池堂18号', '13435513218', '44162319810701061X', '1186358340@qq.com', null);
INSERT INTO `union_staff` VALUES ('100027', '100027', '谢晋营', '1', '1978-11-30', '连平县上坪镇旗石村委会19号', '13690970647', '441623197811300659', '652443409@qq.com', null);
INSERT INTO `union_staff` VALUES ('100028', '100028', '谢丽青', '2', '1970-05-19', '连平县上坪镇旗石村石子角屋6号', '13650660890', '441623197005190643', '无', null);
INSERT INTO `union_staff` VALUES ('100029', '100029', '谢文钦', '1', '1964-09-29', '连平县上坪镇新镇村大古陂屋塘头3号', '13553225861', '441623196409290654', '无', null);
INSERT INTO `union_staff` VALUES ('100030', '100030', '谢国太', '1', '1970-02-04', '连平县上坪镇古坑村105国道边', '15986037676', '441623197002040615', '无', null);
INSERT INTO `union_staff` VALUES ('100031', '100031', '谢景增', '1', '1988-02-02', '连平县上坪镇旗石村太阳升28号', '13690977586', '441623198802020022', '无', null);
INSERT INTO `union_staff` VALUES ('100032', '100032', '叶秋媛', '2', '1985-06-08', '连平县陂头镇连光村社头围14号', '15819258161', '452122198506080964', '1053663096@qq.com', null);
INSERT INTO `union_staff` VALUES ('100033', '100033', '谢幸福', '2', '1958-08-09', '广东省连平县上坪镇古坑村委会大屋19号', '13827845478', '442524195808090622', '无', null);
INSERT INTO `union_staff` VALUES ('100034', '100034', '谢文胜', '1', '1988-03-03', '', '13725655839', '441623198803030033', '无', null);
INSERT INTO `union_staff` VALUES ('100035', '100035', '余碧然', '1', '1975-10-02', '连平县内莞镇蓝州村余屋七星墩', '13825397368', '441623197510021015', '3069178105@qq.com', null);
INSERT INTO `union_staff` VALUES ('100036', '100036', '谢文朗', '1', '1988-04-04', '连平县上坪镇下楼村水楼屋16号', '13553227950', '441623198804040014', '无', null);
INSERT INTO `union_staff` VALUES ('100037', '100037', '谢房浅', '1', '1966-06-06', '连平县上坪镇古坑村上新7号', '13553227958', '442524196606060055', '无', null);
INSERT INTO `union_staff` VALUES ('100038', '100038', '丘春伟', '1', '1969-04-02', '连平县上坪镇下洞村委会下余坑4号', '18316920629', '442524196904020610', '3312981747@qq.com', null);
INSERT INTO `union_staff` VALUES ('100039', '100039', '余房淦', '1', '1979-10-10', '连平县元善镇新龙村东门路东23号', '13380941939', '441623197910101313', '13376788278@189.cn', null);

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection` (
  `Collect_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Union_Goods_PK` int(11) NOT NULL,
  `User_PK` int(11) NOT NULL,
  `Goods_PK` int(11) NOT NULL,
  `Collect_Time` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `Collect_Reserve_1` varchar(5) DEFAULT NULL,
  `Collect_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Collect_PK`),
  KEY `FK_Collection_Goods` (`Goods_PK`),
  KEY `FK_User_Collection` (`User_PK`),
  CONSTRAINT `FK_Collection_Goods` FOREIGN KEY (`Goods_PK`) REFERENCES `goods_info` (`Goods_PK`),
  CONSTRAINT `FK_User_Collection` FOREIGN KEY (`User_PK`) REFERENCES `user_info` (`User_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8 COMMENT='用户对商品的收藏';

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES ('100000', '100000', '100003', '100000', '2017-03-30 14:03:31', null, null, null);
INSERT INTO `user_collection` VALUES ('100001', '100000', '100003', '100000', '2017-03-31 00:03:15', null, null, null);
INSERT INTO `user_collection` VALUES ('100002', '100012', '100003', '100006', '2017-05-22 08:05:57', null, null, null);
INSERT INTO `user_collection` VALUES ('100003', '100014', '100003', '100008', '2017-05-25 16:05:44', null, null, null);

-- ----------------------------
-- Table structure for user_comments
-- ----------------------------
DROP TABLE IF EXISTS `user_comments`;
CREATE TABLE `user_comments` (
  `Comm_PK` int(11) NOT NULL AUTO_INCREMENT,
  `Union_Goods_PK` int(11) NOT NULL,
  `User_PK` int(11) NOT NULL,
  `Goods_PK` int(11) NOT NULL,
  `Comm_Rank` int(11) NOT NULL COMMENT '1到5',
  `Comm_Text` text NOT NULL,
  `Comm_Time` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `Comm_Reserve_1` varchar(5) DEFAULT NULL,
  `Comm_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Comm_PK`),
  KEY `FK_Goods_Comment` (`Goods_PK`),
  KEY `FK_User_Comment` (`User_PK`),
  CONSTRAINT `FK_Goods_Comment` FOREIGN KEY (`Goods_PK`) REFERENCES `goods_info` (`Goods_PK`),
  CONSTRAINT `FK_User_Comment` FOREIGN KEY (`User_PK`) REFERENCES `user_info` (`User_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8 COMMENT='用户对某一商品的评价。';

-- ----------------------------
-- Records of user_comments
-- ----------------------------
INSERT INTO `user_comments` VALUES ('100002', '100000', '100003', '100000', '4', '123', '2017-05-25 01:05:35', null, null, null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `User_PK` int(11) NOT NULL AUTO_INCREMENT,
  `User_Password` varchar(32) NOT NULL COMMENT '密码应不低于8位，不高于16位。',
  `User_Name` varchar(20) DEFAULT NULL,
  `User_Cell` varchar(11) DEFAULT NULL COMMENT '必须为11位',
  `User_Email` varchar(30) DEFAULT NULL,
  `User_Sex` int(11) NOT NULL COMMENT '1为男，2为女，3为保密',
  `User_RegTime` datetime NOT NULL COMMENT '格式为YYYY-MM-DD HH:MM:SS',
  `User_Realname` varchar(10) DEFAULT NULL,
  `User_ID` varchar(18) DEFAULT NULL COMMENT '必须为18位',
  `User_Mark` varchar(1) DEFAULT NULL,
  `User_Reserve_1` varchar(5) DEFAULT NULL,
  `User_Reserve_2` varchar(5) DEFAULT NULL,
  `Remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`User_PK`),
  UNIQUE KEY `UNQ_User_user_name` (`User_Name`),
  UNIQUE KEY `UNQ_User_user_cell` (`User_Cell`),
  UNIQUE KEY `UNQ_User_user_email` (`User_Email`),
  UNIQUE KEY `UNQ_User_user_ID` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8 COMMENT='注册用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('100000', '25d55ad283aa400af464c76d713c07ad', '林莹莹', '18814167467', '1624471560@qq.com', '2', '2017-03-28 15:03:15', '林莹莹', '445202199412018022', '1', null, null, null);
INSERT INTO `user_info` VALUES ('100003', '25d55ad283aa400af464c76d713c07ad', '林天真', '13421166393', '123@qq.com', '2', '2017-03-30 14:03:03', '林天真', '445202199412019022', '0', null, null, null);
INSERT INTO `user_info` VALUES ('100004', '25d55ad283aa400af464c76d713c07ad', 'default', '1', '1', '2', '2017-03-28 15:03:15', 'default', '1', '0', '', '', '');
INSERT INTO `user_info` VALUES ('100005', '0', '0', '0', '0', '0', '2017-05-17 22:41:23', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Procedure structure for Procedure_1
-- ----------------------------
DROP PROCEDURE IF EXISTS `Procedure_1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedure_1`()
update Order_Info set Order_State=5 where TO_DAYS(now())-TO_DAYS(Order_PayTime)>=15 AND Order_IsPay=true AND Order_State<>2
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Procedure_2
-- ----------------------------
DROP PROCEDURE IF EXISTS `Procedure_2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedure_2`()
update Order_Info set Order_State=2 where TO_DAYS(now())-TO_DAYS(Order_Time)>=1 AND Order_State=1
;;
DELIMITER ;

-- ----------------------------
-- Event structure for Procedure_1
-- ----------------------------
DROP EVENT IF EXISTS `Procedure_1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `Procedure_1` ON SCHEDULE EVERY 60 SECOND STARTS '2017-05-22 22:11:44' ON COMPLETION PRESERVE ENABLE DO CALL Procedure_1
;;
DELIMITER ;

-- ----------------------------
-- Event structure for Procedure_2
-- ----------------------------
DROP EVENT IF EXISTS `Procedure_2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `Procedure_2` ON SCHEDULE EVERY 60 SECOND STARTS '2017-05-22 22:12:23' ON COMPLETION PRESERVE ENABLE DO CALL Procedure_2
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
