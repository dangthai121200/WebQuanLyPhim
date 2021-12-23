-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th12 23, 2021 lúc 12:06 AM
-- Phiên bản máy phục vụ: 5.7.31
-- Phiên bản PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `manage_movie`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gmail` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sdt` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`id_admin`, `fullName`, `username`, `password`, `birthday`, `gmail`, `sdt`) VALUES
(1, 'Đặng Hồng Bảo Thái', 'dangthai121200@gmail.com', 'Thai0913442325.', '2001-01-12', 'dangthai121212@gmail.com', 93560376),
(7, 'Đặng Hồng Bảo Thái', 'gaonet012@gmail.com', 'Thai0913442325.', NULL, NULL, NULL),
(8, 'Đặng Hồng Bảo Thái', 'abc@gmail.com', 'Thai0913442325.', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_category`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id_category`, `name`) VALUES
(4, 'cổ trang'),
(6, 'hài hước'),
(2, 'hành động'),
(3, 'hoạt hình'),
(7, 'phiêu lưu'),
(1, 'tình cảm'),
(5, 'TV SHOW');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category_moive`
--

DROP TABLE IF EXISTS `category_moive`;
CREATE TABLE IF NOT EXISTS `category_moive` (
  `id_categoryMoive` int(11) NOT NULL AUTO_INCREMENT,
  `id_category` int(11) DEFAULT NULL,
  `id_movie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_categoryMoive`),
  KEY `id_category` (`id_category`),
  KEY `id_movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `filmtype`
--

DROP TABLE IF EXISTS `filmtype`;
CREATE TABLE IF NOT EXISTS `filmtype` (
  `id_filmType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_filmType`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `filmtype`
--

INSERT INTO `filmtype` (`id_filmType`, `name`) VALUES
(1, 'Phim bộ'),
(3, 'Phim chiếu rạp'),
(4, 'Phim hoạt hình'),
(2, 'Phim lẻ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `id_movie` int(11) NOT NULL AUTO_INCREMENT,
  `nameMovie` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'đang cập nhật',
  `director` varchar(200) COLLATE utf8_unicode_ci DEFAULT 'đang cập nhật',
  `timeMovie` time DEFAULT '00:00:00',
  `statusMovie` varchar(15) COLLATE utf8_unicode_ci DEFAULT 'đang cập nhật',
  `urlimage` text COLLATE utf8_unicode_ci,
  `content` text COLLATE utf8_unicode_ci,
  `yearOfManufacture` int(11) DEFAULT NULL,
  `dateAdd` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_filmType` int(11) NOT NULL,
  PRIMARY KEY (`id_movie`),
  UNIQUE KEY `nameMovie` (`nameMovie`),
  KEY `id_filmType` (`id_filmType`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `movie`
--

INSERT INTO `movie` (`id_movie`, `nameMovie`, `country`, `director`, `timeMovie`, `statusMovie`, `urlimage`, `content`, `yearOfManufacture`, `dateAdd`, `id_filmType`) VALUES
(39, 'Asdasd', 'Asdasd', 'Asdasd', '22:03:47', 'Đang cập nhật', '/image/asdasd.jfif', 'asdasdad', 12312, '2021-12-22 20:52:48', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `movie_admin`
--

DROP TABLE IF EXISTS `movie_admin`;
CREATE TABLE IF NOT EXISTS `movie_admin` (
  `id_movieAdmin` int(11) NOT NULL AUTO_INCREMENT,
  `id_movie` int(11) DEFAULT NULL,
  `id_admin` int(11) DEFAULT NULL,
  `dateAction` date DEFAULT NULL,
  `title` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id_movieAdmin`),
  KEY `id_movie` (`id_movie`),
  KEY `id_admin` (`id_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `category_moive`
--
ALTER TABLE `category_moive`
  ADD CONSTRAINT `category_moive_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `category_moive_ibfk_2` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `movie_ibfk_1` FOREIGN KEY (`id_filmType`) REFERENCES `filmtype` (`id_filmType`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `movie_admin`
--
ALTER TABLE `movie_admin`
  ADD CONSTRAINT `movie_admin_ibfk_1` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `movie_admin_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
