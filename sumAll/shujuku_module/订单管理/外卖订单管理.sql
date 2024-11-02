-- ----------------------------
-- Table structure for table `oorder`
-- ----------------------------
DROP TABLE IF EXISTS `oorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oorder` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `shop_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
  `order_money` int NOT NULL COMMENT '订单金额',

  `order_way` int DEFAULT '1' COMMENT '0 线上订餐 1 线下订餐' ,
  `cons_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '顾客电话',
  `cons_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '顾客姓名',
  `cons_addre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '顾客地址',
  `checked` int DEFAULT '0' COMMENT  '0 已审核 1 未审核',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `idx_order_id` (`order_id`),
  KEY `idx_shop_name` (`shop_name`),
  KEY `idx_order_way` (`order_way`),
  KEY `idx_checked` (`checked`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单管理';

-- ----------------------------
-- Records of oorder
-- ----------------------------
INSERT INTO `oorder` VALUES (1, '大美鸡公煲', 12, 0, '13525188888', '老三', '15公寓', 0, '2022-12-16 14:35:17');
INSERT INTO `oorder` VALUES (2, '小美负一楼蛋炒饭', 10, 1, '13525188888', '吴方', '10公寓', 1, '2022-12-16 14:35:26');
INSERT INTO `oorder` VALUES (3, '至美猪脚饭', 20, 0, '13525188888', '吴方', '8公寓', 0, '2022-12-16 14:35:35');
