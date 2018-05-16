/*
 Navicat Premium Data Transfer

 Source Server         : CouldServer
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 120.24.186.116:3306
 Source Schema         : Trade

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 16/05/2018 11:06:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Account
-- ----------------------------
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`  (
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pass_word` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Account
-- ----------------------------
INSERT INTO `Account` VALUES ('A00001', '280001');
INSERT INTO `Account` VALUES ('A00002', '280002');
INSERT INTO `Account` VALUES ('A00003', '280003');

-- ----------------------------
-- Table structure for Agent
-- ----------------------------
DROP TABLE IF EXISTS `Agent`;
CREATE TABLE `Agent`  (
  `agent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Message` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`agent_id`) USING BTREE,
  INDEX `area_id`(`area_id`) USING BTREE,
  CONSTRAINT `Agent_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `Area` (`area_id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Agent
-- ----------------------------
INSERT INTO `Agent` VALUES ('A00001', '王健林', '13905228191', '中国青岛庐山路 67号 九九别墅小区 03号楼 10单元 08室', 'AA0001', '要先定一个能达到的小目标，比如我先挣它一个亿。');
INSERT INTO `Agent` VALUES ('A00002', '马云', '18888888888', '中国青岛解放路 88号 10号名邸小区 02号楼 10单元 04室', 'AA0002', '我有生以来最大的错误就是创建阿里巴巴。');
INSERT INTO `Agent` VALUES ('A00003', '马化腾', '15152048479', '中国青岛长江路 82号 远景小区 07号楼 10单元 01室', 'AA0003', '坚持每天发现、修正一两个小问题，不到一年就能把产品打磨出来了。');
INSERT INTO `Agent` VALUES ('A00004', '比尔·盖茨', '15895250746', '中国青岛大学路 09号 10号名邸小区 05号楼 10单元 12室', 'AA0004', '');
INSERT INTO `Agent` VALUES ('A00005', '宗庆后', '15152045658', '中国青岛红旗路 66号 名仁家园小区 08号楼 10单元 10室', 'AA0005', '');
INSERT INTO `Agent` VALUES ('A00006', '沃伦·巴菲特', '15152045794', '中国青岛檀山路 21号 青云大夏小区 01号楼 10单元 04室', 'AA0006', '');
INSERT INTO `Agent` VALUES ('A00007', '丁磊', '13905228441', '中国青岛庐山路 94号 西湖花园小区 09号楼 10单元 07室', 'AA0007', '');
INSERT INTO `Agent` VALUES ('A00008', '李彦宏', '15895255127', '中国青岛大学路 65号 阳光100小区 07号楼 10单元 15室', 'AA0008', '');
INSERT INTO `Agent` VALUES ('A00009', '马克·扎克伯格', '15895253575', '中国青岛大学路 08号 第七街区小区 08号楼 10单元 02室', 'AA009', '');
INSERT INTO `Agent` VALUES ('A00010', '谢尔盖·布林', '13905224664', '中国青岛檀山路 72号 国际交易广场小区 04号楼 10单元 12室', 'AA010', '');
INSERT INTO `Agent` VALUES ('A00011', '拉里·佩奇', '15152043283', '中国青岛庐山路 51号 名仁家园小区 08号楼 10单元 05室', 'AA0001', '');
INSERT INTO `Agent` VALUES ('A00012', '史蒂夫·鲍尔默', '15895253842', '中国青岛庐山路 66号 西湖花园小区 03号楼 10单元 19室', 'AA0002', '');
INSERT INTO `Agent` VALUES ('A00013', '王卫', '13905225215', '中国青岛北京路 65号 青云大夏小区 05号楼 10单元 10室', 'AA0003', '');
INSERT INTO `Agent` VALUES ('A00014', '雷军', '13905227143', '中国青岛庐山路 25号 国际交易广场小区 07号楼 10单元 12室', 'AA0004', 'Are You OK ?');
INSERT INTO `Agent` VALUES ('A00015', '菲尔·奈特', '15895251241', '中国青岛长江路 32号 西湖花园小区 10号楼 10单元 16室', 'AA0005', '');
INSERT INTO `Agent` VALUES ('A00016', '唐纳德·特朗普', '13323333333', '美国华盛顿特区宾夕法尼亚大街1600号(白宫)', 'AA009', 'I am rich');

-- ----------------------------
-- Table structure for Area
-- ----------------------------
DROP TABLE IF EXISTS `Area`;
CREATE TABLE `Area`  (
  `area_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Area
-- ----------------------------
INSERT INTO `Area` VALUES ('AA0001', '青岛');
INSERT INTO `Area` VALUES ('AA0002', '大连');
INSERT INTO `Area` VALUES ('AA0003', '哈尔滨');
INSERT INTO `Area` VALUES ('AA0004', '济南');
INSERT INTO `Area` VALUES ('AA0005', '沈阳');
INSERT INTO `Area` VALUES ('AA0006', '北京');
INSERT INTO `Area` VALUES ('AA0007', '天津');
INSERT INTO `Area` VALUES ('AA0008', '上海');
INSERT INTO `Area` VALUES ('AA009', '西安');
INSERT INTO `Area` VALUES ('AA010', '杭州');

-- ----------------------------
-- Table structure for EXwarehouse
-- ----------------------------
DROP TABLE IF EXISTS `EXwarehouse`;
CREATE TABLE `EXwarehouse`  (
  `indent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `warehouse_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `EX_amount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`indent_id`, `goods_id`, `warehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of EXwarehouse
-- ----------------------------
INSERT INTO `EXwarehouse` VALUES ('I201706085367', 'G00033', 'W0002', '8');
INSERT INTO `EXwarehouse` VALUES ('I201706085367', 'G00039', 'W0010', '55');
INSERT INTO `EXwarehouse` VALUES ('I201706147780', 'G00035', 'W0007', '27');
INSERT INTO `EXwarehouse` VALUES ('I201706147780', 'G00042', 'W0001', '17');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00001', 'W0001', '30');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00001', 'W0005', '70');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00002', 'W0001', '45');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00002', 'W0003', '55');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00003', 'W0001', '45');
INSERT INTO `EXwarehouse` VALUES ('I201707072457', 'G00003', 'W0004', '55');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00001', 'W0001', '25');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00001', 'W0002', '25');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00001', 'W0005', '50');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00002', 'W0001', '70');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00002', 'W0003', '30');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00003', 'W0001', '70');
INSERT INTO `EXwarehouse` VALUES ('I201707080234', 'G00003', 'W0004', '30');

-- ----------------------------
-- Table structure for Employe
-- ----------------------------
DROP TABLE IF EXISTS `Employe`;
CREATE TABLE `Employe`  (
  `employe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employe_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brith` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employe_id`) USING BTREE,
  INDEX `group_id`(`group_id`) USING BTREE,
  CONSTRAINT `Employe_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `ProduceGroup` (`group_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Employe
-- ----------------------------
INSERT INTO `Employe` VALUES ('E0001', '张全蛋', '2012', 'GP001');

-- ----------------------------
-- Table structure for Goods
-- ----------------------------
DROP TABLE IF EXISTS `Goods`;
CREATE TABLE `Goods`  (
  `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reserve_date` smallint(6) NOT NULL,
  `pack` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `demand_amount` int(11) NULL DEFAULT 0,
  `total_amount` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Goods
-- ----------------------------
INSERT INTO `Goods` VALUES ('G00001', '可口可乐', '碳酸饮料', '430ml*12', 12, '瓶装', 130, 1239);
INSERT INTO `Goods` VALUES ('G00002', '可口可乐 零度', '碳酸饮料', '500ml*12', 9, '袋装', 51, 928);
INSERT INTO `Goods` VALUES ('G00003', '可口可乐 香草味', '碳酸饮料', '560ml*12', 11, '听装', 150, 434);
INSERT INTO `Goods` VALUES ('G00004', '可口可乐 樱桃味', '碳酸饮料', '1000ml*12', 12, '盒装', 138, 120);
INSERT INTO `Goods` VALUES ('G00005', '可口可乐', '碳酸饮料', '1500ml*12', 12, '瓶装', 90, 82);
INSERT INTO `Goods` VALUES ('G00006', '可口可乐 零度', '碳酸饮料', '330ml*24', 6, '袋装', 112, 1311);
INSERT INTO `Goods` VALUES ('G00007', '可口可乐 香草味', '碳酸饮料', '500ml*24', 9, '听装', 165, 133);
INSERT INTO `Goods` VALUES ('G00008', '可口可乐 樱桃味', '碳酸饮料', '560ml*24', 11, '盒装', 37, 51);
INSERT INTO `Goods` VALUES ('G00009', '健怡可口可乐', '碳酸饮料', '1000ml*24', 12, '瓶装', 169, 455);
INSERT INTO `Goods` VALUES ('G00010', '健怡可口可乐 柠檬', '碳酸饮料', '1000ml*24', 18, '袋装', 1000, 780);
INSERT INTO `Goods` VALUES ('G00011', '健怡可口可乐', '碳酸饮料', '330ml*12', 6, '听装', 1011, 338);
INSERT INTO `Goods` VALUES ('G00012', '健怡可口可乐 柠檬', '碳酸饮料', '500ml*12', 9, '盒装', 1083, 592);
INSERT INTO `Goods` VALUES ('G00013', '雪碧', '碳酸饮料', '560ml*12', 11, '瓶装', 1160, 473);
INSERT INTO `Goods` VALUES ('G00014', '雪碧 柠檬', '碳酸饮料', '1000ml*12', 12, '袋装', 1024, 74);
INSERT INTO `Goods` VALUES ('G00015', '雪碧 冰薄荷', '碳酸饮料', '1500ml*12', 18, '听装', 229, 232);
INSERT INTO `Goods` VALUES ('G00016', '雪碧 冰绿茶', '碳酸饮料', '330ml*24', 6, '盒装', 200, 960);
INSERT INTO `Goods` VALUES ('G00017', '醒目', '碳酸饮料', '500ml*24', 9, '瓶装', 0, 727);
INSERT INTO `Goods` VALUES ('G00018', '醒目 西瓜', '碳酸饮料', '560ml*24', 11, '袋装', 76, 0);
INSERT INTO `Goods` VALUES ('G00019', '醒目 苹果', '碳酸饮料', '1000ml*24', 12, '听装', 0, 1056);
INSERT INTO `Goods` VALUES ('G00020', '醒目 葡萄', '碳酸饮料', '1000ml*24', 18, '盒装', 67, 1171);
INSERT INTO `Goods` VALUES ('G00021', '醒目 西柚', '碳酸饮料', '330ml*12', 6, '瓶装', 0, 770);
INSERT INTO `Goods` VALUES ('G00022', '醒目 水蜜桃', '碳酸饮料', '500ml*12', 9, '袋装', 0, 1159);
INSERT INTO `Goods` VALUES ('G00023', '醒目 奇异果', '碳酸饮料', '560ml*12', 11, '听装', 0, 168);
INSERT INTO `Goods` VALUES ('G00024', '醒目 椰子', '碳酸饮料', '1000ml*12', 12, '盒装', 95, 25);
INSERT INTO `Goods` VALUES ('G00025', '醒目 冰激凌', '碳酸饮料', '1500ml*12', 18, '瓶装', 10, 0);
INSERT INTO `Goods` VALUES ('G00026', '醒目 红茶', '碳酸饮料', '330ml*24', 6, '袋装', 39, 6);
INSERT INTO `Goods` VALUES ('G00027', '醒目 盐汽水', '碳酸饮料', '500ml*24', 9, '听装', 68, 0);
INSERT INTO `Goods` VALUES ('G00028', '醒目 菠萝', '碳酸饮料', '560ml*24', 11, '盒装', 66, 842);
INSERT INTO `Goods` VALUES ('G00029', '芬达', '碳酸饮料', '1000ml*24', 12, '瓶装', 0, 222);
INSERT INTO `Goods` VALUES ('G00030', '芬达 橙汁', '碳酸饮料', '1000ml*24', 18, '袋装', 47, 727);
INSERT INTO `Goods` VALUES ('G00031', '芬达 苹果', '碳酸饮料', '330ml*12', 6, '听装', 0, 0);
INSERT INTO `Goods` VALUES ('G00032', '芬达 葡萄', '碳酸饮料', '500ml*12', 9, '盒装', 153, 909);
INSERT INTO `Goods` VALUES ('G00033', '芬达 青柠', '碳酸饮料', '560ml*12', 11, '瓶装', 148, 7);
INSERT INTO `Goods` VALUES ('G00034', '果粒橙', '果汁', '1000ml*12', 12, '袋装', 31, 6);
INSERT INTO `Goods` VALUES ('G00035', '美丽果', '果汁', '1500ml*12', 18, '听装', 27, 583);
INSERT INTO `Goods` VALUES ('G00036', '清凉橙', '果汁', '330ml*24', 6, '盒装', 0, 558);
INSERT INTO `Goods` VALUES ('G00037', '酷儿', '果汁', '500ml*24', 9, '瓶装', 6, 625);
INSERT INTO `Goods` VALUES ('G00038', '原叶茶饮', '茶', '560ml*24', 11, '袋装', 20, 432);
INSERT INTO `Goods` VALUES ('G00039', '原叶茶饮 翠缕绿茶', '茶', '1000ml*24', 12, '听装', 46, 341);
INSERT INTO `Goods` VALUES ('G00040', '原叶茶饮 滇红红茶', '茶', '1000ml*24', 18, '盒装', 27, 632);
INSERT INTO `Goods` VALUES ('G00041', '水森活', '饮用纯净水', '330ml*12', 6, '瓶装', 102, 753);
INSERT INTO `Goods` VALUES ('G00042', '天与地', '饮用纯净水', '500ml*12', 9, '袋装', 86, 1029);
INSERT INTO `Goods` VALUES ('G00043', '冰露', '饮用纯净水', '560ml*12', 11, '听装', 0, 106);
INSERT INTO `Goods` VALUES ('G00044', '酷乐仕', '维他命饮料', '1000ml*12', 12, '盒装', 0, 129);
INSERT INTO `Goods` VALUES ('G00045', '果粒奶优', '乳饮料', '1500ml*12', 18, '瓶装', 129, 889);
INSERT INTO `Goods` VALUES ('G00046', '果粒奶优 清新草莓', '乳饮料', '330ml*24', 6, '袋装', 0, 352);
INSERT INTO `Goods` VALUES ('G00047', '果粒奶优 水润蜜桃', '乳饮料', '500ml*24', 9, '听装', 89, 262);
INSERT INTO `Goods` VALUES ('G00048', '果粒奶优 清香菠萝', '乳饮料', '560ml*24', 11, '盒装', 92, 2039);
INSERT INTO `Goods` VALUES ('G00049', '果粒奶优 香浓芒果', '乳饮料', '1000ml*24', 12, '瓶装', 47, 12);

-- ----------------------------
-- Table structure for Indent
-- ----------------------------
DROP TABLE IF EXISTS `Indent`;
CREATE TABLE `Indent`  (
  `indent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payState` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `indent_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`indent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Indent
-- ----------------------------
INSERT INTO `Indent` VALUES ('I201606173050', 'A00003', '已接单', '未支付', '2016-06-17');
INSERT INTO `Indent` VALUES ('I201606203699', 'A00002', '已接单', '已支付', '2016-06-20');
INSERT INTO `Indent` VALUES ('I201606281007', 'A00003', '已出库', '未支付', '2016-06-28');
INSERT INTO `Indent` VALUES ('I201606306729', 'A00002', '未接单', '未支付', '2016-06-30');
INSERT INTO `Indent` VALUES ('I201607160755', 'A00012', '未接单', '未支付', '2016-07-16');
INSERT INTO `Indent` VALUES ('I201607197742', 'A00008', '未接单', '未支付', '2016-07-19');
INSERT INTO `Indent` VALUES ('I201607200502', 'A00014', '已接单', '未支付', '2016-07-20');
INSERT INTO `Indent` VALUES ('I201607221871', 'A00008', '已接单', '已支付', '2016-07-22');
INSERT INTO `Indent` VALUES ('I201607254447', 'A00002', '未接单', '未支付', '2016-07-25');
INSERT INTO `Indent` VALUES ('I201607281307', 'A00013', '未接单', '未支付', '2016-07-28');
INSERT INTO `Indent` VALUES ('I201608048483', 'A00003', '未接单', '未支付', '2016-08-04');
INSERT INTO `Indent` VALUES ('I201608075153', 'A00002', '已接单', '已支付', '2016-08-07');
INSERT INTO `Indent` VALUES ('I201608214649', 'A00002', '已接单', '已支付', '2016-08-21');
INSERT INTO `Indent` VALUES ('I201608228368', 'A00003', '未接单', '未支付', '2016-08-22');
INSERT INTO `Indent` VALUES ('I201608232128', 'A00015', '未接单', '未支付', '2016-08-23');
INSERT INTO `Indent` VALUES ('I201608278181', 'A00014', '已出库', '已支付', '2016-08-27');
INSERT INTO `Indent` VALUES ('I201609054450', 'A00002', '已出库', '已支付', '2016-09-05');
INSERT INTO `Indent` VALUES ('I201609070286', 'A00001', '已接单', '已支付', '2016-09-07');
INSERT INTO `Indent` VALUES ('I201609072268', 'A00008', '未接单', '未支付', '2016-09-07');
INSERT INTO `Indent` VALUES ('I201609127150', 'A00001', '已出库', '未支付', '2016-09-12');
INSERT INTO `Indent` VALUES ('I201609163837', 'A00012', '已接单', '已支付', '2016-09-16');
INSERT INTO `Indent` VALUES ('I201609189634', 'A00001', '已接单', '未支付', '2016-09-18');
INSERT INTO `Indent` VALUES ('I201609246757', 'A00001', '未接单', '未支付', '2016-09-24');
INSERT INTO `Indent` VALUES ('I201609294235', 'A00005', '已出库', '未支付', '2016-09-29');
INSERT INTO `Indent` VALUES ('I201609306100', 'A00002', '已出库', '未支付', '2016-09-30');
INSERT INTO `Indent` VALUES ('I201610013898', 'A00003', '未接单', '未支付', '2016-10-01');
INSERT INTO `Indent` VALUES ('I201610051795', 'A00001', '已出库', '已支付', '2016-10-05');
INSERT INTO `Indent` VALUES ('I201610057064', 'A00008', '已接单', '未支付', '2016-10-05');
INSERT INTO `Indent` VALUES ('I201610074550', 'A00011', '已出库', '已支付', '2016-10-07');
INSERT INTO `Indent` VALUES ('I201610079142', 'A00002', '未接单', '未支付', '2016-10-07');
INSERT INTO `Indent` VALUES ('I201610082584', 'A00012', '已出库', '已支付', '2016-10-08');
INSERT INTO `Indent` VALUES ('I201610143455', 'A00002', '已接单', '未支付', '2016-10-14');
INSERT INTO `Indent` VALUES ('I201610187787', 'A00001', '已出库', '已支付', '2016-10-18');
INSERT INTO `Indent` VALUES ('I201610192106', 'A00002', '已出库', '已支付', '2016-10-19');
INSERT INTO `Indent` VALUES ('I201610261892', 'A00001', '已接单', '未支付', '2016-10-26');
INSERT INTO `Indent` VALUES ('I201610283777', 'A00001', '未接单', '未支付', '2016-10-28');
INSERT INTO `Indent` VALUES ('I201611054511', 'A00009', '未接单', '未支付', '2016-11-05');
INSERT INTO `Indent` VALUES ('I201611104067', 'A00003', '已接单', '已支付', '2016-11-10');
INSERT INTO `Indent` VALUES ('I201611123503', 'A00002', '已接单', '未支付', '2016-11-12');
INSERT INTO `Indent` VALUES ('I201611147125', 'A00002', '未接单', '未支付', '2016-11-14');
INSERT INTO `Indent` VALUES ('I201611155869', 'A00013', '已接单', '未支付', '2016-11-15');
INSERT INTO `Indent` VALUES ('I201611173571', 'A00003', '已接单', '未支付', '2016-11-17');
INSERT INTO `Indent` VALUES ('I201611215987', 'A00003', '已出库', '未支付', '2016-11-21');
INSERT INTO `Indent` VALUES ('I201611230974', 'A00001', '已出库', '已支付', '2016-11-23');
INSERT INTO `Indent` VALUES ('I201611240588', 'A00002', '已出库', '未支付', '2016-11-24');
INSERT INTO `Indent` VALUES ('I201612045592', 'A00002', '未接单', '未支付', '2016-12-04');
INSERT INTO `Indent` VALUES ('I201612079603', 'A00011', '未接单', '未支付', '2016-12-07');
INSERT INTO `Indent` VALUES ('I201612089931', 'A00001', '已接单', '已支付', '2016-12-08');
INSERT INTO `Indent` VALUES ('I201612139324', 'A00003', '已出库', '已支付', '2016-12-13');
INSERT INTO `Indent` VALUES ('I201612193305', 'A00003', '已接单', '已支付', '2016-12-19');
INSERT INTO `Indent` VALUES ('I201612217775', 'A00011', '已接单', '已支付', '2016-12-21');
INSERT INTO `Indent` VALUES ('I201612227771', 'A00009', '未接单', '未支付', '2016-12-22');
INSERT INTO `Indent` VALUES ('I201701089888', 'A00003', '已接单', '未支付', '2017-01-08');
INSERT INTO `Indent` VALUES ('I201701097254', 'A00006', '已出库', '未支付', '2017-01-09');
INSERT INTO `Indent` VALUES ('I201701138000', 'A00001', '已出库', '未支付', '2017-01-13');
INSERT INTO `Indent` VALUES ('I201701195678', 'A00001', '已出库', '未支付', '2017-01-19');
INSERT INTO `Indent` VALUES ('I201701233596', 'A00002', '已出库', '未支付', '2017-01-23');
INSERT INTO `Indent` VALUES ('I201701293263', 'A00014', '未接单', '未支付', '2017-01-29');
INSERT INTO `Indent` VALUES ('I201702045093', 'A00001', '已接单', '未支付', '2017-02-04');
INSERT INTO `Indent` VALUES ('I201702045732', 'A00015', '已出库', '未支付', '2017-02-04');
INSERT INTO `Indent` VALUES ('I201702050630', 'A00015', '已出库', '已支付', '2017-02-05');
INSERT INTO `Indent` VALUES ('I201702053933', 'A00009', '已出库', '未支付', '2017-02-05');
INSERT INTO `Indent` VALUES ('I201702101743', 'A00003', '已接单', '已支付', '2017-02-10');
INSERT INTO `Indent` VALUES ('I201702102495', 'A00003', '已出库', '已支付', '2017-02-10');
INSERT INTO `Indent` VALUES ('I201702116346', 'A00002', '已接单', '已支付', '2017-02-11');
INSERT INTO `Indent` VALUES ('I201702147009', 'A00001', '已出库', '已支付', '2017-02-14');
INSERT INTO `Indent` VALUES ('I201702220701', 'A00003', '未接单', '未支付', '2017-02-22');
INSERT INTO `Indent` VALUES ('I201702285636', 'A00005', '未接单', '未支付', '2017-02-28');
INSERT INTO `Indent` VALUES ('I201703088192', 'A00003', '未接单', '未支付', '2017-03-08');
INSERT INTO `Indent` VALUES ('I201703094598', 'A00001', '未接单', '未支付', '2017-03-09');
INSERT INTO `Indent` VALUES ('I201703124628', 'A00002', '未接单', '未支付', '2017-03-12');
INSERT INTO `Indent` VALUES ('I201703144544', 'A00002', '已出库', '已支付', '2017-03-14');
INSERT INTO `Indent` VALUES ('I201703148867', 'A00001', '已接单', '已支付', '2017-03-14');
INSERT INTO `Indent` VALUES ('I201703254011', 'A00001', '未接单', '未支付', '2017-03-25');
INSERT INTO `Indent` VALUES ('I201703299242', 'A00015', '已接单', '未支付', '2017-03-29');
INSERT INTO `Indent` VALUES ('I201703306306', 'A00002', '已出库', '已支付', '2017-03-30');
INSERT INTO `Indent` VALUES ('I201704011013', 'A00007', '已出库', '已支付', '2017-04-01');
INSERT INTO `Indent` VALUES ('I201704029997', 'A00001', '已接单', '已支付', '2017-04-02');
INSERT INTO `Indent` VALUES ('I201704100499', 'A00003', '未接单', '未支付', '2017-04-10');
INSERT INTO `Indent` VALUES ('I201704110847', 'A00001', '已接单', '未支付', '2017-04-11');
INSERT INTO `Indent` VALUES ('I201704140807', 'A00009', '未接单', '未支付', '2017-04-14');
INSERT INTO `Indent` VALUES ('I201704155231', 'A00009', '已出库', '已支付', '2017-04-15');
INSERT INTO `Indent` VALUES ('I201704158887', 'A00012', '已接单', '已支付', '2017-04-15');
INSERT INTO `Indent` VALUES ('I201704217349', 'A00002', '未接单', '未支付', '2017-04-21');
INSERT INTO `Indent` VALUES ('I201704244614', 'A00003', '已出库', '已支付', '2017-04-24');
INSERT INTO `Indent` VALUES ('I201705059989', 'A00009', '已出库', '未支付', '2017-05-05');
INSERT INTO `Indent` VALUES ('I201705065764', 'A00005', '未接单', '未支付', '2017-05-06');
INSERT INTO `Indent` VALUES ('I201705102099', 'A00008', '未接单', '未支付', '2017-05-10');
INSERT INTO `Indent` VALUES ('I201705149432', 'A00009', '未接单', '未支付', '2017-05-14');
INSERT INTO `Indent` VALUES ('I201705159510', 'A00001', '已出库', '未支付', '2017-05-15');
INSERT INTO `Indent` VALUES ('I201705212643', 'A00004', '未接单', '未支付', '2017-05-21');
INSERT INTO `Indent` VALUES ('I201705277312', 'A00003', '未接单', '未支付', '2017-05-27');
INSERT INTO `Indent` VALUES ('I201705307336', 'A00001', '已接单', '已支付', '2017-05-30');
INSERT INTO `Indent` VALUES ('I201705313944', 'A00002', '未接单', '未支付', '2017-05-31');
INSERT INTO `Indent` VALUES ('I201705319007', 'A00012', '已出库', '已支付', '2017-05-31');
INSERT INTO `Indent` VALUES ('I201706055285', 'A00015', '已接单', '未支付', '2017-06-05');
INSERT INTO `Indent` VALUES ('I201706085367', 'A00001', '已出库', '未支付', '2017-06-08');
INSERT INTO `Indent` VALUES ('I201706127304', 'A00011', '已出库', '已支付', '2017-06-12');
INSERT INTO `Indent` VALUES ('I201706147780', 'A00015', '已出库', '未支付', '2017-06-14');
INSERT INTO `Indent` VALUES ('I201707015434', 'A00001', '未接单', '未支付', '2017-07-01');
INSERT INTO `Indent` VALUES ('I201707062440', 'A00002', '未接单', '未支付', '2017-07-06');
INSERT INTO `Indent` VALUES ('I201707072457', 'A00002', '已出库', '未支付', '2017-07-07');
INSERT INTO `Indent` VALUES ('I201707080234', 'A00002', '已出库', '未支付', '2017-07-08');
INSERT INTO `Indent` VALUES ('I201707100721', 'A00002', '已接单', '未支付', '2017-07-10');
INSERT INTO `Indent` VALUES ('I201707100810', 'A00001', '已接单', '未支付', '2017-07-10');
INSERT INTO `Indent` VALUES ('I201707104953', 'A00001', '未接单', '未支付', '2017-07-10');
INSERT INTO `Indent` VALUES ('I201804241513', 'A00002', '已接单', '未支付', '2018-04-24');

-- ----------------------------
-- Table structure for Inventory
-- ----------------------------
DROP TABLE IF EXISTS `Inventory`;
CREATE TABLE `Inventory`  (
  `warehouse_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `inventory_amount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`warehouse_id`, `goods_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  CONSTRAINT `Inventory_ibfk_1` FOREIGN KEY (`warehouse_id`) REFERENCES `Warehouse` (`warehouse_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `Inventory_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `Goods` (`goods_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Inventory
-- ----------------------------
INSERT INTO `Inventory` VALUES ('W0001', 'G00001', '183');
INSERT INTO `Inventory` VALUES ('W0001', 'G00002', '442');
INSERT INTO `Inventory` VALUES ('W0001', 'G00004', '20');
INSERT INTO `Inventory` VALUES ('W0001', 'G00006', '110');
INSERT INTO `Inventory` VALUES ('W0001', 'G00009', '330');
INSERT INTO `Inventory` VALUES ('W0001', 'G00011', '18');
INSERT INTO `Inventory` VALUES ('W0001', 'G00019', '497');
INSERT INTO `Inventory` VALUES ('W0001', 'G00022', '264');
INSERT INTO `Inventory` VALUES ('W0001', 'G00035', '169');
INSERT INTO `Inventory` VALUES ('W0001', 'G00036', '213');
INSERT INTO `Inventory` VALUES ('W0001', 'G00040', '22');
INSERT INTO `Inventory` VALUES ('W0001', 'G00042', '52');
INSERT INTO `Inventory` VALUES ('W0001', 'G00046', '210');
INSERT INTO `Inventory` VALUES ('W0002', 'G00001', '0');
INSERT INTO `Inventory` VALUES ('W0002', 'G00006', '288');
INSERT INTO `Inventory` VALUES ('W0002', 'G00012', '252');
INSERT INTO `Inventory` VALUES ('W0002', 'G00014', '74');
INSERT INTO `Inventory` VALUES ('W0002', 'G00015', '91');
INSERT INTO `Inventory` VALUES ('W0002', 'G00017', '340');
INSERT INTO `Inventory` VALUES ('W0002', 'G00029', '177');
INSERT INTO `Inventory` VALUES ('W0002', 'G00033', '7');
INSERT INTO `Inventory` VALUES ('W0002', 'G00034', '6');
INSERT INTO `Inventory` VALUES ('W0002', 'G00041', '124');
INSERT INTO `Inventory` VALUES ('W0002', 'G00045', '483');
INSERT INTO `Inventory` VALUES ('W0002', 'G00048', '212');
INSERT INTO `Inventory` VALUES ('W0003', 'G00002', '211');
INSERT INTO `Inventory` VALUES ('W0003', 'G00003', '273');
INSERT INTO `Inventory` VALUES ('W0003', 'G00006', '443');
INSERT INTO `Inventory` VALUES ('W0003', 'G00011', '320');
INSERT INTO `Inventory` VALUES ('W0003', 'G00019', '179');
INSERT INTO `Inventory` VALUES ('W0003', 'G00028', '488');
INSERT INTO `Inventory` VALUES ('W0003', 'G00036', '22');
INSERT INTO `Inventory` VALUES ('W0003', 'G00043', '106');
INSERT INTO `Inventory` VALUES ('W0003', 'G00045', '154');
INSERT INTO `Inventory` VALUES ('W0003', 'G00046', '76');
INSERT INTO `Inventory` VALUES ('W0004', 'G00001', '490');
INSERT INTO `Inventory` VALUES ('W0004', 'G00003', '134');
INSERT INTO `Inventory` VALUES ('W0004', 'G00005', '82');
INSERT INTO `Inventory` VALUES ('W0004', 'G00010', '480');
INSERT INTO `Inventory` VALUES ('W0004', 'G00016', '497');
INSERT INTO `Inventory` VALUES ('W0004', 'G00028', '32');
INSERT INTO `Inventory` VALUES ('W0004', 'G00040', '298');
INSERT INTO `Inventory` VALUES ('W0004', 'G00045', '252');
INSERT INTO `Inventory` VALUES ('W0004', 'G00047', '262');
INSERT INTO `Inventory` VALUES ('W0004', 'G00048', '292');
INSERT INTO `Inventory` VALUES ('W0005', 'G00001', '92');
INSERT INTO `Inventory` VALUES ('W0005', 'G00002', '275');
INSERT INTO `Inventory` VALUES ('W0005', 'G00004', '100');
INSERT INTO `Inventory` VALUES ('W0005', 'G00006', '42');
INSERT INTO `Inventory` VALUES ('W0005', 'G00010', '300');
INSERT INTO `Inventory` VALUES ('W0005', 'G00019', '47');
INSERT INTO `Inventory` VALUES ('W0005', 'G00024', '23');
INSERT INTO `Inventory` VALUES ('W0005', 'G00030', '76');
INSERT INTO `Inventory` VALUES ('W0005', 'G00032', '73');
INSERT INTO `Inventory` VALUES ('W0005', 'G00037', '484');
INSERT INTO `Inventory` VALUES ('W0005', 'G00042', '326');
INSERT INTO `Inventory` VALUES ('W0005', 'G00049', '12');
INSERT INTO `Inventory` VALUES ('W0006', 'G00013', '388');
INSERT INTO `Inventory` VALUES ('W0006', 'G00015', '141');
INSERT INTO `Inventory` VALUES ('W0006', 'G00020', '455');
INSERT INTO `Inventory` VALUES ('W0006', 'G00021', '316');
INSERT INTO `Inventory` VALUES ('W0006', 'G00022', '377');
INSERT INTO `Inventory` VALUES ('W0006', 'G00026', '6');
INSERT INTO `Inventory` VALUES ('W0006', 'G00029', '45');
INSERT INTO `Inventory` VALUES ('W0006', 'G00030', '480');
INSERT INTO `Inventory` VALUES ('W0006', 'G00032', '499');
INSERT INTO `Inventory` VALUES ('W0006', 'G00048', '474');
INSERT INTO `Inventory` VALUES ('W0007', 'G00001', '474');
INSERT INTO `Inventory` VALUES ('W0007', 'G00003', '27');
INSERT INTO `Inventory` VALUES ('W0007', 'G00007', '133');
INSERT INTO `Inventory` VALUES ('W0007', 'G00020', '219');
INSERT INTO `Inventory` VALUES ('W0007', 'G00021', '444');
INSERT INTO `Inventory` VALUES ('W0007', 'G00030', '171');
INSERT INTO `Inventory` VALUES ('W0007', 'G00032', '337');
INSERT INTO `Inventory` VALUES ('W0007', 'G00035', '360');
INSERT INTO `Inventory` VALUES ('W0007', 'G00041', '295');
INSERT INTO `Inventory` VALUES ('W0007', 'G00046', '66');
INSERT INTO `Inventory` VALUES ('W0007', 'G00048', '181');
INSERT INTO `Inventory` VALUES ('W0008', 'G00006', '428');
INSERT INTO `Inventory` VALUES ('W0008', 'G00013', '85');
INSERT INTO `Inventory` VALUES ('W0008', 'G00016', '164');
INSERT INTO `Inventory` VALUES ('W0008', 'G00017', '387');
INSERT INTO `Inventory` VALUES ('W0008', 'G00022', '25');
INSERT INTO `Inventory` VALUES ('W0008', 'G00023', '61');
INSERT INTO `Inventory` VALUES ('W0008', 'G00038', '432');
INSERT INTO `Inventory` VALUES ('W0008', 'G00042', '344');
INSERT INTO `Inventory` VALUES ('W0008', 'G00048', '458');
INSERT INTO `Inventory` VALUES ('W0009', 'G00008', '51');
INSERT INTO `Inventory` VALUES ('W0009', 'G00016', '279');
INSERT INTO `Inventory` VALUES ('W0009', 'G00019', '333');
INSERT INTO `Inventory` VALUES ('W0009', 'G00021', '10');
INSERT INTO `Inventory` VALUES ('W0009', 'G00022', '493');
INSERT INTO `Inventory` VALUES ('W0009', 'G00023', '107');
INSERT INTO `Inventory` VALUES ('W0009', 'G00036', '323');
INSERT INTO `Inventory` VALUES ('W0009', 'G00037', '141');
INSERT INTO `Inventory` VALUES ('W0009', 'G00040', '312');
INSERT INTO `Inventory` VALUES ('W0009', 'G00041', '334');
INSERT INTO `Inventory` VALUES ('W0009', 'G00042', '273');
INSERT INTO `Inventory` VALUES ('W0009', 'G00048', '191');
INSERT INTO `Inventory` VALUES ('W0010', 'G00009', '125');
INSERT INTO `Inventory` VALUES ('W0010', 'G00012', '340');
INSERT INTO `Inventory` VALUES ('W0010', 'G00016', '20');
INSERT INTO `Inventory` VALUES ('W0010', 'G00020', '497');
INSERT INTO `Inventory` VALUES ('W0010', 'G00024', '2');
INSERT INTO `Inventory` VALUES ('W0010', 'G00028', '322');
INSERT INTO `Inventory` VALUES ('W0010', 'G00039', '341');
INSERT INTO `Inventory` VALUES ('W0010', 'G00044', '129');
INSERT INTO `Inventory` VALUES ('W0010', 'G00048', '231');

-- ----------------------------
-- Table structure for Produce
-- ----------------------------
DROP TABLE IF EXISTS `Produce`;
CREATE TABLE `Produce`  (
  `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `warehouse_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `produce_amount` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Produce
-- ----------------------------
INSERT INTO `Produce` VALUES ('G00004', 'W0005', 'GP002', 100);
INSERT INTO `Produce` VALUES ('G00004', 'W0005', 'GP002', 100);
INSERT INTO `Produce` VALUES ('G00004', 'W0001', 'GP001', 20);
INSERT INTO `Produce` VALUES ('G00010', 'W0005', 'GP001', 300);

-- ----------------------------
-- Table structure for ProduceGroup
-- ----------------------------
DROP TABLE IF EXISTS `ProduceGroup`;
CREATE TABLE `ProduceGroup`  (
  `group_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ProduceGroup
-- ----------------------------
INSERT INTO `ProduceGroup` VALUES ('GP001', '八一锄头队', 'E0001');
INSERT INTO `ProduceGroup` VALUES ('GP002', '五四红旗队', 'E0001');
INSERT INTO `ProduceGroup` VALUES ('GP003', '九九镰刀队', 'E0001');

-- ----------------------------
-- Table structure for Sell
-- ----------------------------
DROP TABLE IF EXISTS `Sell`;
CREATE TABLE `Sell`  (
  `indent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `goods_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`indent_id`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Sell
-- ----------------------------
INSERT INTO `Sell` VALUES ('I201606173050', 'G00007', '43.07', '40');
INSERT INTO `Sell` VALUES ('I201606173050', 'G00009', '10.81', '90');
INSERT INTO `Sell` VALUES ('I201606203699', 'G00002', '234.67', '39');
INSERT INTO `Sell` VALUES ('I201606203699', 'G00048', '34.68', '92');
INSERT INTO `Sell` VALUES ('I201606281007', 'G00020', '45.45', '94');
INSERT INTO `Sell` VALUES ('I201606281007', 'G00030', '41.52', '51');
INSERT INTO `Sell` VALUES ('I201606306729', 'G00018', NULL, '87');
INSERT INTO `Sell` VALUES ('I201606306729', 'G00022', NULL, '37');
INSERT INTO `Sell` VALUES ('I201607160755', 'G00019', NULL, '98');
INSERT INTO `Sell` VALUES ('I201607160755', 'G00026', NULL, '22');
INSERT INTO `Sell` VALUES ('I201607197742', 'G00001', NULL, '28');
INSERT INTO `Sell` VALUES ('I201607197742', 'G00002', NULL, '91');
INSERT INTO `Sell` VALUES ('I201607200502', 'G00006', '83.39', '41');
INSERT INTO `Sell` VALUES ('I201607200502', 'G00018', '53.43', '76');
INSERT INTO `Sell` VALUES ('I201607221871', 'G00011', '17.51', '11');
INSERT INTO `Sell` VALUES ('I201607221871', 'G00028', '46.08', '58');
INSERT INTO `Sell` VALUES ('I201607254447', 'G00036', NULL, '32');
INSERT INTO `Sell` VALUES ('I201607254447', 'G00043', NULL, '55');
INSERT INTO `Sell` VALUES ('I201607281307', 'G00010', NULL, '27');
INSERT INTO `Sell` VALUES ('I201607281307', 'G00035', NULL, '31');
INSERT INTO `Sell` VALUES ('I201608048483', 'G00031', NULL, '89');
INSERT INTO `Sell` VALUES ('I201608048483', 'G00048', NULL, '79');
INSERT INTO `Sell` VALUES ('I201608075153', 'G00006', '30.16', '14');
INSERT INTO `Sell` VALUES ('I201608075153', 'G00040', '270.15', '27');
INSERT INTO `Sell` VALUES ('I201608214649', 'G00034', '364.32', '21');
INSERT INTO `Sell` VALUES ('I201608214649', 'G00042', '219.33', '69');
INSERT INTO `Sell` VALUES ('I201608228368', 'G00015', NULL, '58');
INSERT INTO `Sell` VALUES ('I201608228368', 'G00047', NULL, '1');
INSERT INTO `Sell` VALUES ('I201608232128', 'G00005', NULL, '82');
INSERT INTO `Sell` VALUES ('I201608232128', 'G00040', NULL, '35');
INSERT INTO `Sell` VALUES ('I201608278181', 'G00009', '12.75', '45');
INSERT INTO `Sell` VALUES ('I201608278181', 'G00049', '28.36', '42');
INSERT INTO `Sell` VALUES ('I201609054450', 'G00002', '63.30', '28');
INSERT INTO `Sell` VALUES ('I201609054450', 'G00021', '97.35', '10');
INSERT INTO `Sell` VALUES ('I201609070286', 'G00006', '9.43', '35');
INSERT INTO `Sell` VALUES ('I201609070286', 'G00030', '283.88', '47');
INSERT INTO `Sell` VALUES ('I201609072268', 'G00019', NULL, '84');
INSERT INTO `Sell` VALUES ('I201609072268', 'G00025', NULL, '54');
INSERT INTO `Sell` VALUES ('I201609127150', 'G00014', '32.24', '82');
INSERT INTO `Sell` VALUES ('I201609127150', 'G00041', '62.51', '41');
INSERT INTO `Sell` VALUES ('I201609163837', 'G00032', '83.56', '67');
INSERT INTO `Sell` VALUES ('I201609163837', 'G00033', '169.94', '42');
INSERT INTO `Sell` VALUES ('I201609189634', 'G00004', '72.24', '64');
INSERT INTO `Sell` VALUES ('I201609189634', 'G00015', '61.30', '86');
INSERT INTO `Sell` VALUES ('I201609246757', 'G00011', NULL, '35');
INSERT INTO `Sell` VALUES ('I201609246757', 'G00047', NULL, '43');
INSERT INTO `Sell` VALUES ('I201609294235', 'G00032', '25.27', '47');
INSERT INTO `Sell` VALUES ('I201609294235', 'G00037', '20.63', '89');
INSERT INTO `Sell` VALUES ('I201609306100', 'G00003', '375.28', '59');
INSERT INTO `Sell` VALUES ('I201609306100', 'G00035', '45.92', '28');
INSERT INTO `Sell` VALUES ('I201610013898', 'G00004', NULL, '30');
INSERT INTO `Sell` VALUES ('I201610013898', 'G00038', NULL, '82');
INSERT INTO `Sell` VALUES ('I201610051795', 'G00030', '133.09', '78');
INSERT INTO `Sell` VALUES ('I201610051795', 'G00034', '84.51', '98');
INSERT INTO `Sell` VALUES ('I201610057064', 'G00016', '219.86', '32');
INSERT INTO `Sell` VALUES ('I201610057064', 'G00027', '86.68', '55');
INSERT INTO `Sell` VALUES ('I201610074550', 'G00017', '387.05', '30');
INSERT INTO `Sell` VALUES ('I201610074550', 'G00025', '304.41', '24');
INSERT INTO `Sell` VALUES ('I201610079142', 'G00028', NULL, '25');
INSERT INTO `Sell` VALUES ('I201610079142', 'G00032', NULL, '16');
INSERT INTO `Sell` VALUES ('I201610082584', 'G00005', '31.41', '36');
INSERT INTO `Sell` VALUES ('I201610082584', 'G00044', '162.45', '10');
INSERT INTO `Sell` VALUES ('I201610143455', 'G00020', '323.53', '67');
INSERT INTO `Sell` VALUES ('I201610143455', 'G00032', '279.40', '86');
INSERT INTO `Sell` VALUES ('I201610187787', 'G00003', '298.92', '8');
INSERT INTO `Sell` VALUES ('I201610187787', 'G00012', '197.97', '84');
INSERT INTO `Sell` VALUES ('I201610187787', 'G00026', '42.29', '19');
INSERT INTO `Sell` VALUES ('I201610192106', 'G00008', '389.28', '77');
INSERT INTO `Sell` VALUES ('I201610192106', 'G00048', '2.01', '81');
INSERT INTO `Sell` VALUES ('I201610261892', 'G00009', '111.40', '79');
INSERT INTO `Sell` VALUES ('I201610261892', 'G00013', '94.89', '42');
INSERT INTO `Sell` VALUES ('I201610283777', 'G00020', NULL, '30');
INSERT INTO `Sell` VALUES ('I201610283777', 'G00024', NULL, '1');
INSERT INTO `Sell` VALUES ('I201611054511', 'G00008', NULL, '72');
INSERT INTO `Sell` VALUES ('I201611054511', 'G00036', NULL, '18');
INSERT INTO `Sell` VALUES ('I201611104067', 'G00004', '62.06', '42');
INSERT INTO `Sell` VALUES ('I201611104067', 'G00015', '6.88', '80');
INSERT INTO `Sell` VALUES ('I201611123503', 'G00003', '86.47', '5');
INSERT INTO `Sell` VALUES ('I201611123503', 'G00015', '181.00', '63');
INSERT INTO `Sell` VALUES ('I201611147125', 'G00003', NULL, '55');
INSERT INTO `Sell` VALUES ('I201611147125', 'G00029', NULL, '45');
INSERT INTO `Sell` VALUES ('I201611155869', 'G00007', '52.07', '61');
INSERT INTO `Sell` VALUES ('I201611155869', 'G00047', '34.95', '89');
INSERT INTO `Sell` VALUES ('I201611173571', 'G00014', '274.23', '24');
INSERT INTO `Sell` VALUES ('I201611173571', 'G00045', '84.23', '66');
INSERT INTO `Sell` VALUES ('I201611215987', 'G00003', '88.06', '84');
INSERT INTO `Sell` VALUES ('I201611215987', 'G00009', '19.28', '85');
INSERT INTO `Sell` VALUES ('I201611230974', 'G00013', '10.94', '2');
INSERT INTO `Sell` VALUES ('I201611230974', 'G00043', '35.42', '100');
INSERT INTO `Sell` VALUES ('I201611240588', 'G00017', '275.33', '47');
INSERT INTO `Sell` VALUES ('I201611240588', 'G00031', '78.12', '41');
INSERT INTO `Sell` VALUES ('I201612045592', 'G00004', NULL, '60');
INSERT INTO `Sell` VALUES ('I201612045592', 'G00029', NULL, '11');
INSERT INTO `Sell` VALUES ('I201612079603', 'G00006', NULL, '94');
INSERT INTO `Sell` VALUES ('I201612079603', 'G00028', NULL, '4');
INSERT INTO `Sell` VALUES ('I201612089931', 'G00007', '96.52', '59');
INSERT INTO `Sell` VALUES ('I201612089931', 'G00016', '67.15', '62');
INSERT INTO `Sell` VALUES ('I201612139324', 'G00031', '458.95', '12');
INSERT INTO `Sell` VALUES ('I201612139324', 'G00037', '364.11', '28');
INSERT INTO `Sell` VALUES ('I201612193305', 'G00008', '34.76', '37');
INSERT INTO `Sell` VALUES ('I201612193305', 'G00033', '341.11', '77');
INSERT INTO `Sell` VALUES ('I201612217775', 'G00003', '86.26', '64');
INSERT INTO `Sell` VALUES ('I201612217775', 'G00016', '172.74', '16');
INSERT INTO `Sell` VALUES ('I201612227771', 'G00021', NULL, '63');
INSERT INTO `Sell` VALUES ('I201612227771', 'G00046', NULL, '61');
INSERT INTO `Sell` VALUES ('I201701089888', 'G00028', '88.61', '8');
INSERT INTO `Sell` VALUES ('I201701089888', 'G00045', '2.42', '63');
INSERT INTO `Sell` VALUES ('I201701097254', 'G00022', '150.90', '78');
INSERT INTO `Sell` VALUES ('I201701097254', 'G00039', '130.41', '68');
INSERT INTO `Sell` VALUES ('I201701138000', 'G00012', '12.20', '94');
INSERT INTO `Sell` VALUES ('I201701138000', 'G00023', '323.02', '27');
INSERT INTO `Sell` VALUES ('I201701195678', 'G00001', '114.06', '93');
INSERT INTO `Sell` VALUES ('I201701195678', 'G00038', '258.00', '86');
INSERT INTO `Sell` VALUES ('I201701233596', 'G00030', '145.89', '27');
INSERT INTO `Sell` VALUES ('I201701233596', 'G00044', '288.86', '97');
INSERT INTO `Sell` VALUES ('I201701293263', 'G00019', NULL, '86');
INSERT INTO `Sell` VALUES ('I201701293263', 'G00031', NULL, '37');
INSERT INTO `Sell` VALUES ('I201702045093', 'G00006', '253.79', '22');
INSERT INTO `Sell` VALUES ('I201702045093', 'G00039', '83.56', '46');
INSERT INTO `Sell` VALUES ('I201702045732', 'G00009', '175.49', '36');
INSERT INTO `Sell` VALUES ('I201702045732', 'G00011', '187.55', '54');
INSERT INTO `Sell` VALUES ('I201702050630', 'G00003', '60.31', '10');
INSERT INTO `Sell` VALUES ('I201702050630', 'G00042', '101.37', '34');
INSERT INTO `Sell` VALUES ('I201702053933', 'G00002', '175.03', '92');
INSERT INTO `Sell` VALUES ('I201702053933', 'G00012', '68.82', '73');
INSERT INTO `Sell` VALUES ('I201702101743', 'G00024', '263.21', '95');
INSERT INTO `Sell` VALUES ('I201702101743', 'G00026', '141.94', '39');
INSERT INTO `Sell` VALUES ('I201702102495', 'G00013', '316.34', '85');
INSERT INTO `Sell` VALUES ('I201702102495', 'G00030', '52.27', '61');
INSERT INTO `Sell` VALUES ('I201702116346', 'G00027', '315.79', '13');
INSERT INTO `Sell` VALUES ('I201702116346', 'G00041', '48.53', '39');
INSERT INTO `Sell` VALUES ('I201702147009', 'G00010', '32.13', '95');
INSERT INTO `Sell` VALUES ('I201702147009', 'G00033', '95.95', '37');
INSERT INTO `Sell` VALUES ('I201702220701', 'G00021', NULL, '17');
INSERT INTO `Sell` VALUES ('I201702220701', 'G00033', NULL, '23');
INSERT INTO `Sell` VALUES ('I201702285636', 'G00027', NULL, '79');
INSERT INTO `Sell` VALUES ('I201702285636', 'G00033', NULL, '60');
INSERT INTO `Sell` VALUES ('I201703088192', 'G00001', NULL, '22');
INSERT INTO `Sell` VALUES ('I201703088192', 'G00018', NULL, '65');
INSERT INTO `Sell` VALUES ('I201703094598', 'G00022', NULL, '65');
INSERT INTO `Sell` VALUES ('I201703094598', 'G00038', NULL, '98');
INSERT INTO `Sell` VALUES ('I201703124628', 'G00001', NULL, '19');
INSERT INTO `Sell` VALUES ('I201703124628', 'G00032', NULL, '70');
INSERT INTO `Sell` VALUES ('I201703144544', 'G00006', '443.04', '51');
INSERT INTO `Sell` VALUES ('I201703144544', 'G00011', '131.30', '78');
INSERT INTO `Sell` VALUES ('I201703148867', 'G00003', '161.36', '67');
INSERT INTO `Sell` VALUES ('I201703148867', 'G00025', '154.76', '10');
INSERT INTO `Sell` VALUES ('I201703254011', 'G00008', NULL, '47');
INSERT INTO `Sell` VALUES ('I201703254011', 'G00014', NULL, '64');
INSERT INTO `Sell` VALUES ('I201703299242', 'G00001', '46.68', '100');
INSERT INTO `Sell` VALUES ('I201703299242', 'G00049', '174.08', '47');
INSERT INTO `Sell` VALUES ('I201703306306', 'G00039', '263.56', '97');
INSERT INTO `Sell` VALUES ('I201704011013', 'G00010', '74.69', '67');
INSERT INTO `Sell` VALUES ('I201704011013', 'G00023', '102.58', '38');
INSERT INTO `Sell` VALUES ('I201704029997', 'G00005', '258.66', '75');
INSERT INTO `Sell` VALUES ('I201704029997', 'G00013', '139.37', '90');
INSERT INTO `Sell` VALUES ('I201704100499', 'G00006', NULL, '42');
INSERT INTO `Sell` VALUES ('I201704100499', 'G00007', NULL, '50');
INSERT INTO `Sell` VALUES ('I201704110847', 'G00012', '265.52', '83');
INSERT INTO `Sell` VALUES ('I201704110847', 'G00034', '32.29', '7');
INSERT INTO `Sell` VALUES ('I201704140807', 'G00015', NULL, '89');
INSERT INTO `Sell` VALUES ('I201704140807', 'G00044', NULL, '89');
INSERT INTO `Sell` VALUES ('I201704155231', 'G00015', '80.24', '79');
INSERT INTO `Sell` VALUES ('I201704155231', 'G00020', '237.55', '79');
INSERT INTO `Sell` VALUES ('I201704158887', 'G00004', '35.14', '32');
INSERT INTO `Sell` VALUES ('I201704158887', 'G00041', '99.54', '63');
INSERT INTO `Sell` VALUES ('I201704217349', 'G00033', NULL, '89');
INSERT INTO `Sell` VALUES ('I201704217349', 'G00036', NULL, '22');
INSERT INTO `Sell` VALUES ('I201704244614', 'G00030', '151.28', '96');
INSERT INTO `Sell` VALUES ('I201704244614', 'G00045', '34.55', '61');
INSERT INTO `Sell` VALUES ('I201705059989', 'G00033', '95.47', '64');
INSERT INTO `Sell` VALUES ('I201705059989', 'G00047', '161.70', '73');
INSERT INTO `Sell` VALUES ('I201705065764', 'G00003', NULL, '95');
INSERT INTO `Sell` VALUES ('I201705102099', 'G00005', NULL, '5');
INSERT INTO `Sell` VALUES ('I201705102099', 'G00027', NULL, '83');
INSERT INTO `Sell` VALUES ('I201705149432', 'G00004', NULL, '86');
INSERT INTO `Sell` VALUES ('I201705149432', 'G00011', NULL, '39');
INSERT INTO `Sell` VALUES ('I201705159510', 'G00041', '152.80', '48');
INSERT INTO `Sell` VALUES ('I201705159510', 'G00043', '47.26', '29');
INSERT INTO `Sell` VALUES ('I201705212643', 'G00019', NULL, '82');
INSERT INTO `Sell` VALUES ('I201705212643', 'G00044', NULL, '14');
INSERT INTO `Sell` VALUES ('I201705277312', 'G00014', NULL, '7');
INSERT INTO `Sell` VALUES ('I201705277312', 'G00046', NULL, '79');
INSERT INTO `Sell` VALUES ('I201705307336', 'G00016', '53.03', '90');
INSERT INTO `Sell` VALUES ('I201705307336', 'G00033', '45.19', '29');
INSERT INTO `Sell` VALUES ('I201705313944', 'G00006', NULL, '29');
INSERT INTO `Sell` VALUES ('I201705313944', 'G00043', NULL, '72');
INSERT INTO `Sell` VALUES ('I201705319007', 'G00016', '229.61', '15');
INSERT INTO `Sell` VALUES ('I201705319007', 'G00021', '269.60', '94');
INSERT INTO `Sell` VALUES ('I201706055285', 'G00013', '30', '28');
INSERT INTO `Sell` VALUES ('I201706055285', 'G00038', '35', '20');
INSERT INTO `Sell` VALUES ('I201706085367', 'G00033', '61.56', '8');
INSERT INTO `Sell` VALUES ('I201706085367', 'G00039', '41.36', '55');
INSERT INTO `Sell` VALUES ('I201706127304', 'G00010', '249.36', '57');
INSERT INTO `Sell` VALUES ('I201706127304', 'G00027', '129.98', '49');
INSERT INTO `Sell` VALUES ('I201706147780', 'G00035', '271.85', '27');
INSERT INTO `Sell` VALUES ('I201706147780', 'G00042', '344.93', '17');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00002', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00003', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00004', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00005', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00006', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00007', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00008', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00009', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00010', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707015434', 'G00011', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707062440', 'G00034', NULL, '10');
INSERT INTO `Sell` VALUES ('I201707062440', 'G00035', NULL, '10');
INSERT INTO `Sell` VALUES ('I201707062440', 'G00036', NULL, '10');
INSERT INTO `Sell` VALUES ('I201707062440', 'G00037', NULL, '10');
INSERT INTO `Sell` VALUES ('I201707072457', 'G00001', '40', '100');
INSERT INTO `Sell` VALUES ('I201707072457', 'G00002', '40', '100');
INSERT INTO `Sell` VALUES ('I201707072457', 'G00003', '40', '100');
INSERT INTO `Sell` VALUES ('I201707080234', 'G00001', '45', '100');
INSERT INTO `Sell` VALUES ('I201707080234', 'G00002', '50', '100');
INSERT INTO `Sell` VALUES ('I201707080234', 'G00003', '55', '100');
INSERT INTO `Sell` VALUES ('I201707100721', 'G00010', '50', '1000');
INSERT INTO `Sell` VALUES ('I201707100721', 'G00011', '20', '1000');
INSERT INTO `Sell` VALUES ('I201707100721', 'G00012', '50', '1000');
INSERT INTO `Sell` VALUES ('I201707100721', 'G00013', '20', '1000');
INSERT INTO `Sell` VALUES ('I201707100721', 'G00014', '50', '1000');
INSERT INTO `Sell` VALUES ('I201707100810', 'G00001', '50', '25');
INSERT INTO `Sell` VALUES ('I201707100810', 'G00003', '123', '15');
INSERT INTO `Sell` VALUES ('I201707100810', 'G00005', '123', '15');
INSERT INTO `Sell` VALUES ('I201707104953', 'G00004', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707104953', 'G00037', NULL, '12');
INSERT INTO `Sell` VALUES ('I201707104953', 'G00038', NULL, '50');
INSERT INTO `Sell` VALUES ('I201707104953', 'G00040', NULL, '15');
INSERT INTO `Sell` VALUES ('I201707104953', 'G00046', NULL, '12');
INSERT INTO `Sell` VALUES ('I201804241513', 'G00002', '3', '12');
INSERT INTO `Sell` VALUES ('I201804241513', 'G00007', '3', '5');
INSERT INTO `Sell` VALUES ('I201804241513', 'G00034', '3.5', '3');
INSERT INTO `Sell` VALUES ('I201804241513', 'G00037', '2.5', '6');

-- ----------------------------
-- Table structure for Warehouse
-- ----------------------------
DROP TABLE IF EXISTS `Warehouse`;
CREATE TABLE `Warehouse`  (
  `warehouse_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `warehouse_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Warehouse
-- ----------------------------
INSERT INTO `Warehouse` VALUES ('W0001', '市南区仓库', '青岛市宁夏路286号');
INSERT INTO `Warehouse` VALUES ('W0002', '市北区仓库', '青岛市延吉路80号');
INSERT INTO `Warehouse` VALUES ('W0003', '黄岛区仓库', '青岛市长江中路369号');
INSERT INTO `Warehouse` VALUES ('W0004', '崂山区仓库', '青岛市长江中路369号');
INSERT INTO `Warehouse` VALUES ('W0005', '李沧区仓库', '青岛市黑龙江中路615号');
INSERT INTO `Warehouse` VALUES ('W0006', '城阳区仓库', '青岛市正阳中路201号');
INSERT INTO `Warehouse` VALUES ('W0007', '胶州市仓库', '胶州市正阳中路201号');
INSERT INTO `Warehouse` VALUES ('W0008', '即墨市仓库', '即墨市振华街140号');
INSERT INTO `Warehouse` VALUES ('W0009', '平度市仓库', '平度市长江路1号');
INSERT INTO `Warehouse` VALUES ('W0010', '莱西市仓库', '莱西市长江路1号');

-- ----------------------------
-- Procedure structure for Order_indent
-- ----------------------------
DROP PROCEDURE IF EXISTS `Order_indent`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Order_indent`(indent_id_set varchar(50))
begin
	declare indent_id_get varchar(50);      /*获得表中订单号*/
    declare amount_need int;
    declare goods_id_need varchar(50);
    declare goods_id_get varchar(50);
    
    declare no_more_record bool;
	declare cursor_search cursor for select goods_id,amount from Sell where Sell.indent_id = indent_id_set;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more_record = FALSE;
    
    /*判断该订单是否存在*/
    select indent_id into indent_id_get from Indent where Indent.indent_id = indent_id_set;   /*查找订单表中该订单的id*/   
    if indent_id_get is null then/*订单不存在*/
		rollback;
	end if;
	
	/*开始循环该订单下的销售记录,以增加商品信息里面的需求量*/
    open cursor_search;
    repeat
		fetch cursor_search into goods_id_need,amount_need;
		
        /*判断是否存在对应的商品信息*/
        select goods_id into goods_id_get from Goods where Goods.goods_id = goods_id_need;
        if goods_id_get is null then/*不存在该商品*/
			rollback;
        end if;
        
        /*增加需求量*/
        update Goods set demand_amount = demand_amount + amount_need where Goods.goods_id = goods_id_need;
    until not no_more_record end repeat;/*判断结束*/
    
    /*改变订单状态*/
    update Indent set state = '已接单' where Indent.indent_id = indent_id_set;
	commit;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for Produce_goods
-- ----------------------------
DROP PROCEDURE IF EXISTS `Produce_goods`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `Produce_goods`(goods_id_set varchar(50),warehouse_id_set varchar(50),group_id_set varchar(50),produce_amount_set int)
begin
	declare goods_id_get varchar(50);      /*获得表中商品编号*/
    declare warehouse_id_get varchar(50);  /*获得表中仓库号*/
    declare amount int;					   /*获得库存表中库存量*/
    
    select goods_id into goods_id_get from Goods where goods_id = goods_id_set;   /*查找商品表中该商品的id*/                   
    select warehouse_id into warehouse_id_get from Warehouse where warehouse_id = warehouse_id_set;/*查找仓库表中该库存的id*/
    
    if goods_id_get is null then/*商品不存在*/
		rollback;
	end if;
    
    if warehouse_id_get is null then/*仓库不存在*/
		rollback;
	end if;
    
    insert into Produce values(goods_id_set,warehouse_id_set,group_id_set,produce_amount_set);/*添加这次生产记录*/
    
    select inventory_amount into amount from Inventory  /*查找库存表中是否已存在该商品编号和仓库号所对应的库存信息*/
    where  Inventory.goods_id =  goods_id_set and  Inventory.warehouse_id = warehouse_id_set;
    
    if amount is null then/*库存信息中无对应的记录*/
		insert into Inventory values(warehouse_id_set,goods_id_set,0);/*增加一条对应的库存数量为0的记录*/
	end if;
    
    update Inventory set inventory_amount = inventory_amount + produce_amount_set /*增加对应库存记录的数量,增量为此次生产的数量*/
    where Inventory.goods_id = goods_id_set and Inventory.warehouse_id = warehouse_id_set;

	update Goods set total_amount = total_amount + produce_amount_set /*增加对应商品表的总库存数量,增量为此次生产的数量*/
    where Goods.goods_id = goods_id_set;
    
	commit;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
