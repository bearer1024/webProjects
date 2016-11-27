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
-- 表的结构 `succeed_case`
--

CREATE TABLE IF NOT EXISTS `succeed_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `gongsi_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `case_introduce` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `beizhu` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
