-- ----------------------------
-- Table structure for table `dispatcher`
-- ----------------------------
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
INSERT INTO `dispatcher` VALUES ('010100', '摇摆羊', '13365789765');
INSERT INTO `dispatcher` VALUES ('010101', '小亮', '15878977898');
INSERT INTO `dispatcher` VALUES ('1000011', '老八', '13526777887');
INSERT INTO `dispatcher` VALUES ('10111', '赵三金', '15965578765');
