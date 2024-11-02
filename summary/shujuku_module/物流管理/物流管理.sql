-- ----------------------------
-- Table structure for table `wuliu`
-- ----------------------------
DROP TABLE IF EXISTS `wuliu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wuliu` (
  `wuliu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物流编号',
  `shop_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
  `order_id` int NOT NULL COMMENT '订单编号',
  `dispatcher_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调度员编号',
  PRIMARY KEY (`wuliu_id`),
  UNIQUE KEY `idx_wuliu_id` (`wuliu_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_dispatcher_id` (`dispatcher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='物流信息表';

-- ----------------------------
-- Records of wuliu
-- ----------------------------
LOCK TABLES `wuliu` WRITE;
INSERT INTO `wuliu` VALUES ('W001', '大美鸡公煲', 1, '010100');
INSERT INTO `wuliu` VALUES ('W002', '小美负一楼蛋炒饭', 2, '1000011');
UNLOCK TABLES;

DROP TABLE IF EXISTS `dispatcher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispatcher` (
  `dispatcher_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调度员编号',
  `dispatcher_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调度员姓名',
  `dispatcher_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '调度员电话',
  PRIMARY KEY (`dispatcher_id`),
  UNIQUE KEY `idx_dispatcher_id` (`dispatcher_id`),
  KEY `idx_dispatcher_name` (`dispatcher_name`),
  KEY `idx_dispatcher_phone` (`dispatcher_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='调度员信息表';

-- ----------------------------
-- Records of dispatcher
-- ----------------------------
LOCK TABLES `dispatcher` WRITE;
INSERT INTO `dispatcher` VALUES ('010100', '摇摆羊', '13365789765');
INSERT INTO `dispatcher` VALUES ('010101', '小亮', '15878977898');
INSERT INTO `dispatcher` VALUES ('1000011', '老八', '13526777887');
INSERT INTO `dispatcher` VALUES ('10111', '赵三金', '15965578765');
UNLOCK TABLES;