-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2018 at 05:22 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arcade`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `uid` int(11) NOT NULL,
  `image_path` varchar(100) NOT NULL,
  `image_name` varchar(100) NOT NULL,
  `details` varchar(50) NOT NULL,
  `usepoint` int(10) NOT NULL,
  `link` varchar(50) NOT NULL,
  `package` varchar(50) NOT NULL,
  `adname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`uid`, `image_path`, `image_name`, `details`, `usepoint`, `link`, `package`, `adname`) VALUES
(2, '/upload/birddsafasf.jpg', 'birddsafasf', 'use 10 point', 20, 'https://www.google.com', '', 'admin'),
(3, '/upload/flappylo.png', 'bird', 'use 10 point', 10, 'https://www.google.com', 'com.Company.arcade1', 'admin'),
(7, '/upload/.jpg', '', '', 0, '', '', '0'),
(8, '/upload/.jpg', '', '', 0, '', '', '2'),
(9, '/upload/.jpg', '', '', 0, '', '', 'null'),
(10, '/upload/.jpg', '', '', 0, '', '', 'admin'),
(11, '/upload/.jpg', '', '', 0, '', '', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `point`
--

CREATE TABLE `point` (
  `pid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `details` varchar(50) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `point`
--

INSERT INTO `point` (`pid`, `id`, `details`, `date`) VALUES
(22, 1, '+20', '2018-10-24'),
(23, 1, '+20', '2018-10-24'),
(24, 1, '-60', '2018-10-24'),
(25, 1, '-60', '2018-10-24'),
(26, 1, '-60', '2018-10-24'),
(27, 1, '-60', '2018-10-24'),
(28, 1, '-20', '2018-10-24'),
(29, 1, '-1', '2018-10-24'),
(30, 1, '-2', '2018-10-24'),
(31, 1, '+20', '2018-10-24'),
(32, 1, '+20', '2018-10-24'),
(33, 1, '+20', '2018-10-24'),
(34, 1, '+20', '2018-10-24'),
(35, 1, '-10', '2018-10-24'),
(36, 1, '+20', '2018-10-24'),
(37, 1, '+20', '2018-10-24'),
(38, 1, '-2', '2018-10-25'),
(39, 1, '+20', '2018-10-25'),
(40, 1, '-50', '2018-10-27'),
(41, 1, '-20', '2018-10-28'),
(42, 1, '-20', '2018-11-15'),
(43, 1, '-50', '2018-11-16'),
(44, 1, '+50', '2018-11-16'),
(46, 1, 'Playgame -20', '2018-11-19'),
(47, 1, 'Playgame -20', '2018-11-19'),
(48, 1, 'Playgame -20', '2018-11-19'),
(49, 1, 'Playgame -20', '2018-11-19'),
(50, 1, 'Playgame +9', '2018-11-19'),
(51, 1, 'Playgame -10', '2018-11-19'),
(52, 1, 'Playgame +6', '2018-11-19'),
(53, 1, 'Playgame -10', '2018-11-19'),
(54, 1, 'Playgame -10', '2018-11-19'),
(55, 1, 'Playgame +4', '2018-11-19'),
(56, 1, 'Playgame -10', '2018-11-19'),
(57, 1, 'Playgame +2', '2018-11-19'),
(58, 1, 'Playgame -10', '2018-11-19'),
(59, 1, 'Playgame +-9', '2018-11-19'),
(60, 1, 'Playgame -10', '2018-11-19'),
(61, 1, 'Playgame +4', '2018-11-19'),
(62, 1, 'Playgame -10', '2018-11-21'),
(63, 1, 'Playgame +1', '2018-11-21'),
(64, 1, '-10', '2018-11-21'),
(65, 1, '-2', '2018-11-21'),
(66, 1, '-20', '2018-11-21'),
(67, 1, 'Playgame -10', '2018-11-26'),
(68, 1, 'Playgame +16', '2018-11-26'),
(69, 1, 'Playgame -10', '2018-11-26'),
(70, 1, 'Playgame +4', '2018-11-26'),
(71, 1, 'Playgame -10', '2018-11-26'),
(72, 1, 'Playgame +8', '2018-11-26'),
(73, 1, 'Playgame -10', '2018-11-26'),
(74, 1, 'Playgame +8', '2018-11-26'),
(75, 1, '-5', '2018-11-27');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `point` int(10) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `point`, `status`) VALUES
(1, 'ginae', '0458718237', 'colaza_@windowslive.com', 41, ''),
(2, 'admin', 'admin', 'admin@admin.com', 0, 'admin'),
(7, 'ginae1', '0458718237', 'colaza1_@windowslive.com', 0, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `point`
--
ALTER TABLE `point`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `point`
--
ALTER TABLE `point`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
