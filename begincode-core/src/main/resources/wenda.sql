-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.50 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 begincode 的数据库结构
DROP DATABASE IF EXISTS `begincode`;
CREATE DATABASE IF NOT EXISTS `begincode` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `begincode`;


-- 导出  表 begincode.answer 结构
DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识无意义',
  `content` longtext COMMENT '回答内容',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '回复人名称',
  `agree_count` int(11) NOT NULL DEFAULT '0' COMMENT '赞同人数',
  `opposition_count` int(11) NOT NULL DEFAULT '0' COMMENT '反对人数',
  `adopt` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否采纳（0未采纳，1采纳）',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0未删除，1删除）',
  `feedback` tinyint(4) NOT NULL DEFAULT '0' COMMENT '反馈（0正常，1违规）',
  `problem_id` int(11) NOT NULL DEFAULT '0' COMMENT '问题标识',
  `begincode_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户标识',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答';

-- 正在导出表  begincode.answer 的数据：~0 rows (大约)
DELETE FROM `answer`;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;


-- 导出  表 begincode.ans_agree 结构
DROP TABLE IF EXISTS `ans_agree`;
CREATE TABLE IF NOT EXISTS `ans_agree` (
  `ans_agree_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '无意义标识',
  `agree` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0默认，1赞同，2反对',
  `answer_id` int(11) NOT NULL DEFAULT '0' COMMENT '回复标识',
  `begincode_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '人员标识',
  PRIMARY KEY (`ans_agree_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  begincode.ans_agree 的数据：~0 rows (大约)
DELETE FROM `ans_agree`;
/*!40000 ALTER TABLE `ans_agree` DISABLE KEYS */;
/*!40000 ALTER TABLE `ans_agree` ENABLE KEYS */;


-- 导出  表 begincode.begincode_user 结构
DROP TABLE IF EXISTS `begincode_user`;
CREATE TABLE IF NOT EXISTS `begincode_user` (
  `begincode_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `cdate` datetime NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `tel_phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `receive_email` char(1) DEFAULT NULL,
  `pic` varchar(200) DEFAULT NULL,
  `check_flag` char(1) DEFAULT NULL,
  `gag` char(1) NOT NULL,
  `course` varchar(5) DEFAULT NULL,
  `open_id` varchar(32) DEFAULT NULL,
  `access_token` varchar(32) DEFAULT NULL,
  `user_source_id` int(11) DEFAULT NULL,
  `delete_flag` char(1) DEFAULT NULL,
  `invite_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`begincode_user_id`),
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  begincode.begincode_user 的数据：~5 rows (大约)
DELETE FROM `begincode_user`;
/*!40000 ALTER TABLE `begincode_user` DISABLE KEYS */;
INSERT INTO `begincode_user` (`begincode_user_id`, `login_name`, `pwd`, `nickname`, `cdate`, `sex`, `tel_phone`, `email`, `receive_email`, `pic`, `check_flag`, `gag`, `course`, `open_id`, `access_token`, `user_source_id`, `delete_flag`, `invite_code`) VALUES
	(1, 'yang', 'dsdf', '杨晓阳', '2015-07-11 17:27:19', '1', '12', 'yangshuangjun@126.com', '1', 'dd', '1', '1', '12', '', '111', NULL, NULL, NULL);
/*!40000 ALTER TABLE `begincode_user` ENABLE KEYS */;


-- 导出  表 begincode.label 结构
DROP TABLE IF EXISTS `label`;
CREATE TABLE IF NOT EXISTS `label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签标识',
  `label_name` varchar(50) NOT NULL DEFAULT '' COMMENT '标签名',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '标签级别',
  `parent_id` tinyint(4) NOT NULL DEFAULT '0' COMMENT '父标签标识',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识（0未删除，1已删除）',
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  begincode.label 的数据：~0 rows (大约)
DELETE FROM `label`;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
/*!40000 ALTER TABLE `label` ENABLE KEYS */;


-- 导出  表 begincode.message 结构
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `answer_id` int(11) NOT NULL DEFAULT '0' COMMENT '回答标识',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '问题标识',
  `begincode_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '人员标识',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识0默认，1删除',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提醒';

-- 正在导出表  begincode.message 的数据：~0 rows (大约)
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- 导出  表 begincode.problem 结构
DROP TABLE IF EXISTS `problem`;
CREATE TABLE IF NOT EXISTS `problem` (
  `problem_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext NOT NULL COMMENT '内容',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '提问人姓名',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
  `vote_count` int(11) NOT NULL DEFAULT '0' COMMENT '投票支持人数',
  `collect_count` int(11) NOT NULL DEFAULT '0' COMMENT '收藏人数',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览人数',
  `solve` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否解决（0未解决，1解决）',
  `delete_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识（0未删除，1已删除）',
  `feedback` tinyint(4) NOT NULL DEFAULT '0' COMMENT '反馈（0正常，1违规）',
  `begincode_user_id` int(11) NOT NULL COMMENT '提问人标识',
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

-- 正在导出表  begincode.problem 的数据：~0 rows (大约)
DELETE FROM `problem`;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;


-- 导出  表 begincode.problem_label 结构
DROP TABLE IF EXISTS `problem_label`;
CREATE TABLE IF NOT EXISTS `problem_label` (
  `pro_label_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `problem_id` int(11) NOT NULL DEFAULT '0' COMMENT '问题标识',
  `label_id` int(11) NOT NULL DEFAULT '0' COMMENT '标签标识',
  PRIMARY KEY (`pro_label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题标签';

-- 正在导出表  begincode.problem_label 的数据：~0 rows (大约)
DELETE FROM `problem_label`;
/*!40000 ALTER TABLE `problem_label` DISABLE KEYS */;
/*!40000 ALTER TABLE `problem_label` ENABLE KEYS */;


-- 导出  表 begincode.pro_attention 结构
DROP TABLE IF EXISTS `pro_attention`;
CREATE TABLE IF NOT EXISTS `pro_attention` (
  `pro_attention_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `vote` tinyint(4) NOT NULL DEFAULT '0' COMMENT '投票（0未投，1投票）',
  `collect` tinyint(4) NOT NULL DEFAULT '0' COMMENT '收藏（0为收藏，1收藏）',
  `begincode_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `problem_id` int(11) NOT NULL DEFAULT '0' COMMENT '问题标识',
  PRIMARY KEY (`pro_attention_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注';

-- 正在导出表  begincode.pro_attention 的数据：~0 rows (大约)
DELETE FROM `pro_attention`;
/*!40000 ALTER TABLE `pro_attention` DISABLE KEYS */;
/*!40000 ALTER TABLE `pro_attention` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
