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

 Date: 16/05/2018 11:06:00
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
-- Table structure for Area
-- ----------------------------
DROP TABLE IF EXISTS `Area`;
CREATE TABLE `Area`  (
  `area_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
