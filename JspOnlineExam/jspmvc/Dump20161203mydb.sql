-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

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
-- Table structure for table `OPTIONS`
--

DROP TABLE IF EXISTS `OPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oNO` char(1) COLLATE utf8_bin NOT NULL,
  `oName` varchar(100) COLLATE utf8_bin NOT NULL,
  `qId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `qId` (`qId`),
  CONSTRAINT `OPTIONS_ibfk_1` FOREIGN KEY (`qId`) REFERENCES `QUESTIONS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS`
--

LOCK TABLES `OPTIONS` WRITE;
/*!40000 ALTER TABLE `OPTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `OPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUESTIONS`
--

DROP TABLE IF EXISTS `QUESTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUESTIONS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qName` varchar(100) COLLATE utf8_bin NOT NULL,
  `qAnswer` char(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUESTIONS`
--

LOCK TABLES `QUESTIONS` WRITE;
/*!40000 ALTER TABLE `QUESTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUESTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEST_PAPER`
--

DROP TABLE IF EXISTS `TEST_PAPER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TEST_PAPER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tName` varchar(100) COLLATE utf8_bin NOT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEST_PAPER`
--

LOCK TABLES `TEST_PAPER` WRITE;
/*!40000 ALTER TABLE `TEST_PAPER` DISABLE KEYS */;
/*!40000 ALTER TABLE `TEST_PAPER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEST_QUESTIONS`
--

DROP TABLE IF EXISTS `TEST_QUESTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TEST_QUESTIONS` (
  `tId` int(11) DEFAULT NULL,
  `qId` int(11) DEFAULT NULL,
  KEY `qId` (`qId`),
  KEY `tId` (`tId`),
  CONSTRAINT `TEST_QUESTIONS_ibfk_1` FOREIGN KEY (`qId`) REFERENCES `QUESTIONS` (`id`),
  CONSTRAINT `TEST_QUESTIONS_ibfk_2` FOREIGN KEY (`tId`) REFERENCES `TEST_PAPER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEST_QUESTIONS`
--

LOCK TABLES `TEST_QUESTIONS` WRITE;
/*!40000 ALTER TABLE `TEST_QUESTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TEST_QUESTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEST_RESULT`
--

DROP TABLE IF EXISTS `TEST_RESULT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TEST_RESULT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `mark` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `tId` (`tId`),
  CONSTRAINT `TEST_RESULT_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USER_INFO` (`id`),
  CONSTRAINT `TEST_RESULT_ibfk_2` FOREIGN KEY (`tId`) REFERENCES `TEST_PAPER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEST_RESULT`
--

LOCK TABLES `TEST_RESULT` WRITE;
/*!40000 ALTER TABLE `TEST_RESULT` DISABLE KEYS */;
/*!40000 ALTER TABLE `TEST_RESULT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_INFO`
--

DROP TABLE IF EXISTS `USER_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_INFO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) COLLATE utf8_bin NOT NULL,
  `pwd` varchar(16) COLLATE utf8_bin NOT NULL,
  `name` varchar(16) COLLATE utf8_bin NOT NULL,
  `sex` char(2) COLLATE utf8_bin DEFAULT NULL,
  `telephone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `remark` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_INFO`
--

LOCK TABLES `USER_INFO` WRITE;
/*!40000 ALTER TABLE `USER_INFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_INFO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-03 12:25:21
