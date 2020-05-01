-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: adal
-- ------------------------------------------------------
-- Server version	5.7.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adal_permission`
--

DROP TABLE IF EXISTS `adal_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adal_permission` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '权限名',
  `url` varchar(1000) DEFAULT NULL COMMENT '类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址',
  `type` int(2) NOT NULL COMMENT '权限类型，页面-1，按钮-2',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限表达式',
  `method` varchar(50) DEFAULT NULL COMMENT '后端接口访问方式',
  `sort` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(64) NOT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adal_permission`
--

LOCK TABLES `adal_permission` WRITE;
/*!40000 ALTER TABLE `adal_permission` DISABLE KEYS */;
INSERT INTO `adal_permission` VALUES (1072806379288399872,'测试页面','/test',1,'page:test',NULL,1,0),(1072806379313565696,'测试页面-查询','/**/test',2,'btn:test:query','GET',1,1072806379288399872),(1072806379330342912,'测试页面-添加','/**/test',2,'btn:test:insert','POST',2,1072806379288399872),(1072806379342925824,'监控在线用户页面','/monitor',1,'page:monitor:online',NULL,2,0),(1072806379363897344,'在线用户页面-查询','/**/api/monitor/online/user',2,'btn:monitor:online:query','GET',1,1072806379342925824),(1072806379384868864,'在线用户页面-踢出','/**/api/monitor/online/user/kickout',2,'btn:monitor:online:kickout','DELETE',2,1072806379342925824);
/*!40000 ALTER TABLE `adal_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adal_role`
--

DROP TABLE IF EXISTS `adal_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adal_role` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adal_role`
--

LOCK TABLES `adal_role` WRITE;
/*!40000 ALTER TABLE `adal_role` DISABLE KEYS */;
INSERT INTO `adal_role` VALUES (1072806379208708096,'管理员','超级管理员',1544611947239,1544611947239),(1072806379238068224,'普通用户','普通用户',1544611947246,1544611947246);
/*!40000 ALTER TABLE `adal_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adal_role_permission`
--

DROP TABLE IF EXISTS `adal_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adal_role_permission` (
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  `permission_id` bigint(64) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adal_role_permission`
--

LOCK TABLES `adal_role_permission` WRITE;
/*!40000 ALTER TABLE `adal_role_permission` DISABLE KEYS */;
INSERT INTO `adal_role_permission` VALUES (1072806379208708096,1072806379288399872),(1072806379208708096,1072806379313565696),(1072806379208708096,1072806379330342912),(1072806379208708096,1072806379342925824),(1072806379208708096,1072806379363897344),(1072806379208708096,1072806379384868864),(1072806379238068224,1072806379288399872),(1072806379238068224,1072806379313565696);
/*!40000 ALTER TABLE `adal_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adal_user`
--

DROP TABLE IF EXISTS `adal_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adal_user` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` bigint(13) DEFAULT NULL COMMENT '生日',
  `sex` int(2) DEFAULT NULL COMMENT '性别，男-1，女-2',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '状态，启用-1，禁用-0',
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adal_user`
--

LOCK TABLES `adal_user` WRITE;
/*!40000 ALTER TABLE `adal_user` DISABLE KEYS */;
INSERT INTO `adal_user` VALUES (1072806377661009920,'admin','$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq','管理员','17300000000','admin@xkcoding.com',785433600000,1,1,1544611947032,1544611947032),(1072806378780889088,'user','$2a$10$OUDl4thpcHfs7WZ1kMUOb.ZO5eD4QANW5E.cexBLiKDIzDNt87QbO','普通用户','17300001111','user@xkcoding.com',785433600000,1,1,1544611947234,1544611947234);
/*!40000 ALTER TABLE `adal_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adal_user_role`
--

DROP TABLE IF EXISTS `adal_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adal_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT '用户主键',
  `role_id` bigint(64) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adal_user_role`
--

LOCK TABLES `adal_user_role` WRITE;
/*!40000 ALTER TABLE `adal_user_role` DISABLE KEYS */;
INSERT INTO `adal_user_role` VALUES (1072806377661009920,1072806379208708096),(1072806378780889088,1072806379238068224);
/*!40000 ALTER TABLE `adal_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-01 21:47:49