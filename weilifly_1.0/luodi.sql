-- phpMyAdmin SQL Dump
-- version 3.4.8
-- http://www.phpmyadmin.net
--
-- 主机: 61.139.126.66
-- 生成日期: 2014 年 09 月 16 日 09:30
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
-- 表的结构 `luodi`
--

CREATE TABLE IF NOT EXISTS `luodi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `luodi_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `luodi_introduce` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `beizhu` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
