-- phpMyAdmin SQL Dump
-- version 3.5.8.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 05, 2013 at 05:53 PM
-- Server version: 5.5.32-0ubuntu0.13.04.1
-- PHP Version: 5.4.9-4ubuntu2.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `files`
--

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE IF NOT EXISTS `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `size` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `file`
--

INSERT INTO `file` (`id`, `name`, `size`, `type`, `content`, `created`, `user_id`) VALUES
(18, 'forms.php', '18796', 'application/octet-stream', '<html><head>\n<script language="javascript" type="text/javascript" src="datetimepicker.js">\n\n//Date T', '2013-07-24 07:27:42', '0'),
(19, 'forms.php', '18796', 'application/octet-stream', '<html><head>\n<script language="javascript" type="text/javascript" src="datetimepicker.js">\n\n//Date T', '2013-07-24 07:28:30', '0'),
(20, 'forms.php', '18796', 'application/octet-stream', '<html><head>\n<script language="javascript" type="text/javascript" src="datetimepicker.js">\n\n//Date T', '2013-07-24 07:29:05', '0'),
(21, 'forms.php', '18796', 'application/octet-stream', '<html><head>\n<script language="javascript" type="text/javascript" src="datetimepicker.js">\n\n//Date T', '2013-07-24 07:29:58', '0'),
(24, 'index.jpeg', '10134', 'image/jpeg', 'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	\r\r( \Z%"2"&)+.1. 383,7(-.,\n\n\n\r\Z', '2013-08-01 09:10:15', '0'),
(25, 'jquery.mobile-1.3.1.min.css', '94281', 'text/css', '/*! jQuery Mobile 1.3.1 | Git HEAD hash: 74b4bec <> 2013-04-08T19:41:28Z | (c) 2010, 2013 jQuery Fou', '2013-08-01 09:24:55', '0'),
(26, 'index.jpeg', '10134', 'image/jpeg', 'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	\r\r( \Z%"2"&)+.1. 383,7(-.,\n\n\n\r\Z', '2013-08-05 14:50:55', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(210) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`,`username`),
  UNIQUE KEY `id_3` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `username`, `password`, `status`) VALUES
(5, 'will', 'william', 'it has', 'worked', 0),
(8, 'francis', 'frank', 'admin', 'admin', 1),
(11, 'user', 'user', 'user', 'user', 0),
(16, 'erik', 'erik', 'erik', 'someren', 1),
(20, 'erik', 'erik', 'lee', 'lee', 1),
(21, 'erik', 'erik', 'des', 'lee', 0),
(22, 'lawrence', 'ndonga', 'lau', 'lau', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
