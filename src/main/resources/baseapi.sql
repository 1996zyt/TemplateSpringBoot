SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_name` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名称',
  `password_md5` varchar(32) NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
  `is_locked` tinyint NOT NULL DEFAULT 0 COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

LOCK TABLES `user` WRITE;

INSERT INTO `user` (`user_id`, `user_name`, `password_md5`, `is_deleted`, `is_locked`, `create_time`)
VALUES
	(1,'用户1','e10adc3949ba59abbe56e057f20f883e',0,0,'2021-01-01 08:00:00'),
	(2,'用户2','e10adc3949ba59abbe56e057f20f883e',0,0,'2021-01-01 08:00:00');

UNLOCK TABLES;






DROP TABLE IF EXISTS `user_token`;

CREATE TABLE `user_token` (
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `token` varchar(32) NOT NULL COMMENT 'token值(32位字符串)',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'token过期时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






DROP TABLE IF EXISTS `template_table`;

CREATE TABLE `template_table` (
  `primary_key` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',

  `tinyint_column` tinyint NOT NULL DEFAULT 0 COMMENT '整数 1byte',
  `smallint_column` smallint NOT NULL DEFAULT 0 COMMENT '整数 2bytes',
  `mediumint_column` mediumint NOT NULL DEFAULT 0 COMMENT '整数 3bytes',
  `int_column` int NOT NULL DEFAULT 0 COMMENT '整数 4bytes',
  `bigint_column` bigint NOT NULL DEFAULT 0 COMMENT '整数 8bytes',

  `float_column` float NOT NULL DEFAULT 0 COMMENT '单精度 4bytes',
  `double_column` double NOT NULL DEFAULT 0 COMMENT '双精度 8bytes',
  `decimal_column` decimal NOT NULL DEFAULT 0 COMMENT 'M>D?M+2:D+2',

  `date_column` date NOT NULL DEFAULT '2021-01-01' COMMENT '日期值 3bytes',
  `time_column` time NOT NULL DEFAULT '01:01:01' COMMENT '时间值 3bytes',
  `year_column` year NOT NULL DEFAULT '2021' COMMENT '年份值 1bytes',
  `datetime_column` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '混合日期和时间值 8bytes',
  `timestamp_column` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳 4bytes',

  `char_column` char(10) NOT NULL DEFAULT '' COMMENT '定长字符串，不够会补空格 0-255 bytes',
  `varchar_column` varchar(10) NOT NULL DEFAULT '' COMMENT '变长字符串，不够不补空格 0-65535 bytes',

  `template_text` varchar(100) NOT NULL DEFAULT '' COMMENT '模板文本',
  `template_img_url` varchar(100) NOT NULL DEFAULT '' COMMENT '模板url',

  PRIMARY KEY (`primary_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

LOCK TABLES `template_table` WRITE;

INSERT INTO `template_table` (`template_text`,`template_img_url`)
VALUES
	('测试文本1','https://www.baidu.com/img/flexible/logo/pc/result.png'),
	('测试文本2','https://www.baidu.com/img/flexible/logo/pc/result.png');

UNLOCK TABLES;
