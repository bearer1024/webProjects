-- phpMyAdmin SQL Dump
-- version 3.4.8
-- http://www.phpmyadmin.net
--
-- 主机: 61.139.126.66
-- 生成日期: 2014 年 09 月 16 日 09:29
-- 服务器版本: 5.1.65
-- PHP 版本: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `weilifly`
--

-- --------------------------------------------------------

--
-- 表的结构 `salon`
--

CREATE TABLE IF NOT EXISTS `salon` (
  `salon_id` int(11) NOT NULL AUTO_INCREMENT,
  `picture` mediumblob NOT NULL,
  `picture_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salon_host` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `host_info` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salon_topic` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salon_jianjie` varchar(250) NOT NULL,
  `salon_time` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `salon_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salon_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`salon_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
