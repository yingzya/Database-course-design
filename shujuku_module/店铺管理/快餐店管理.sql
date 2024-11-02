CREATE TABLE `tb_shop` (
  `shop_id` bigint NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `shop_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '店铺名称',
  `shop_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '店铺地址',
  `shop_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺电话',
  `shop_status` int DEFAULT '1' COMMENT '1 营业中 0 打烊',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺管理';

INSERT INTO `tb_shop` (`shop_id`, `shop_name`, `shop_address`, `shop_phone`, `shop_status`) VALUES
(1, '鸡公煲店', '地址1', '12345678901', 1),
(2, '秀苑饭店', '地址2', '12345678902', 1),
(3, '壁虎苑餐厅', '地址3', '12345678903', 1),
(4, '猪脚饭店', '地址4', '12345678904', 1);
