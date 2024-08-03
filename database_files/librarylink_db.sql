-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 06, 2024 at 03:44 PM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarylinkdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `attends`
--

CREATE TABLE `attends` (
  `Card_Number_ID` varchar(10) NOT NULL,
  `Class_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `audio_book`
--

CREATE TABLE `audio_book` (
  `Language` varchar(25) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `Genre` varchar(25) NOT NULL,
  `Material_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `ISBN` int(11) NOT NULL,
  `Difficulty_Level` int(11) NOT NULL,
  `Genre` varchar(25) NOT NULL,
  `Material_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `Branch_ID` varchar(10) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Opening_Time` int(11) NOT NULL,
  `Town_City` varchar(25) NOT NULL,
  `County` varchar(25) NOT NULL,
  `Street_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cd_dvd`
--

CREATE TABLE `cd_dvd` (
  `Category` varchar(25) NOT NULL,
  `Disk_Quantity` int(11) NOT NULL,
  `Material_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `Class_Size` int(11) NOT NULL,
  `Teacher_Name` varchar(50) NOT NULL,
  `Class_Name` varchar(50) NOT NULL,
  `Age_Category` int(11) NOT NULL,
  `Class_ID` varchar(10) NOT NULL,
  `Time_Slot` int(11) NOT NULL,
  `Branch_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `Status` int(11) NOT NULL,
  `Time_Slot` int(11) NOT NULL,
  `Equipment_ID` varchar(10) NOT NULL,
  `Branch_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `Borrow_ID` varchar(10) NOT NULL,
  `Return_Date` date NOT NULL,
  `Renewal_Number` int(11) NOT NULL,
  `Material_ID` varchar(10) NOT NULL,
  `Card_Number_ID` varchar(10) NOT NULL,
  `Borrow_Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `Age_Category` int(11) NOT NULL,
  `Material_Type` varchar(25) NOT NULL,
  `Material_ID` varchar(10) NOT NULL,
  `Branch_ID` varchar(10) NOT NULL,
  `Status` int(11) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Author_Producer` varchar(50) NOT NULL,
  `Shelf_Location` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `Last_Name` varchar(25) NOT NULL,
  `Street_Name` varchar(50) NOT NULL,
  `Town_City` varchar(25) NOT NULL,
  `County` varchar(25) NOT NULL,
  `DOB` date NOT NULL,
  `Material_Quantity` int(11) NOT NULL,
  `First_Name` varchar(25) NOT NULL,
  `Card_Number_ID` varchar(10) NOT NULL,
  `Branch_ID` varchar(10) NOT NULL,
  `Equipment_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `newspaper`
--

CREATE TABLE `newspaper` (
  `Date` date NOT NULL,
  `Category` varchar(25) NOT NULL,
  `Resource_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reference`
--

CREATE TABLE `reference` (
  `ISBN` int(11) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Resource_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `resource`
--

CREATE TABLE `resource` (
  `Resource_Type` varchar(25) NOT NULL,
  `Resource_ID` varchar(10) NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Age_Category` int(11) NOT NULL,
  `Shelf_Location` varchar(25) NOT NULL,
  `Branch_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `uses`
--

CREATE TABLE `uses` (
  `Card_Number_ID` varchar(10) NOT NULL,
  `Resource_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attends`
--
ALTER TABLE `attends`
  ADD PRIMARY KEY (`Card_Number_ID`,`Class_ID`),
  ADD KEY `Class_ID` (`Class_ID`);

--
-- Indexes for table `audio_book`
--
ALTER TABLE `audio_book`
  ADD PRIMARY KEY (`Material_ID`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`Material_ID`);

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`Branch_ID`);

--
-- Indexes for table `cd_dvd`
--
ALTER TABLE `cd_dvd`
  ADD PRIMARY KEY (`Material_ID`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`Class_ID`),
  ADD KEY `Branch_ID` (`Branch_ID`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`Equipment_ID`),
  ADD KEY `Branch_ID` (`Branch_ID`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`Borrow_ID`),
  ADD UNIQUE KEY `Card_Number_ID` (`Card_Number_ID`,`Material_ID`),
  ADD KEY `Material_ID` (`Material_ID`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`Material_ID`),
  ADD KEY `Branch_ID` (`Branch_ID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`Card_Number_ID`),
  ADD KEY `Branch_ID` (`Branch_ID`),
  ADD KEY `Equipment_ID` (`Equipment_ID`);

--
-- Indexes for table `newspaper`
--
ALTER TABLE `newspaper`
  ADD PRIMARY KEY (`Resource_ID`);

--
-- Indexes for table `reference`
--
ALTER TABLE `reference`
  ADD PRIMARY KEY (`Resource_ID`);

--
-- Indexes for table `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`Resource_ID`),
  ADD KEY `Branch_ID` (`Branch_ID`);

--
-- Indexes for table `uses`
--
ALTER TABLE `uses`
  ADD PRIMARY KEY (`Resource_ID`,`Card_Number_ID`),
  ADD KEY `Card_Number_ID` (`Card_Number_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attends`
--
ALTER TABLE `attends`
  ADD CONSTRAINT `attends_ibfk_1` FOREIGN KEY (`Card_Number_ID`) REFERENCES `member` (`Card_Number_ID`),
  ADD CONSTRAINT `attends_ibfk_2` FOREIGN KEY (`Class_ID`) REFERENCES `class` (`Class_ID`);

--
-- Constraints for table `audio_book`
--
ALTER TABLE `audio_book`
  ADD CONSTRAINT `audio_book_ibfk_1` FOREIGN KEY (`Material_ID`) REFERENCES `material` (`Material_ID`);

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Material_ID`) REFERENCES `material` (`Material_ID`);

--
-- Constraints for table `cd_dvd`
--
ALTER TABLE `cd_dvd`
  ADD CONSTRAINT `cd_dvd_ibfk_1` FOREIGN KEY (`Material_ID`) REFERENCES `material` (`Material_ID`);

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`Branch_ID`);

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`Branch_ID`);

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`Card_Number_ID`) REFERENCES `member` (`Card_Number_ID`),
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`Material_ID`) REFERENCES `material` (`Material_ID`);

--
-- Constraints for table `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `material_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`Branch_ID`);

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `member_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`Branch_ID`),
  ADD CONSTRAINT `member_ibfk_2` FOREIGN KEY (`Equipment_ID`) REFERENCES `equipment` (`Equipment_ID`);

--
-- Constraints for table `newspaper`
--
ALTER TABLE `newspaper`
  ADD CONSTRAINT `newspaper_ibfk_1` FOREIGN KEY (`Resource_ID`) REFERENCES `resource` (`Resource_ID`);

--
-- Constraints for table `reference`
--
ALTER TABLE `reference`
  ADD CONSTRAINT `reference_ibfk_1` FOREIGN KEY (`Resource_ID`) REFERENCES `resource` (`Resource_ID`);

--
-- Constraints for table `resource`
--
ALTER TABLE `resource`
  ADD CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`Branch_ID`) REFERENCES `branch` (`Branch_ID`);

--
-- Constraints for table `uses`
--
ALTER TABLE `uses`
  ADD CONSTRAINT `uses_ibfk_1` FOREIGN KEY (`Resource_ID`) REFERENCES `resource` (`Resource_ID`),
  ADD CONSTRAINT `uses_ibfk_2` FOREIGN KEY (`Card_Number_ID`) REFERENCES `member` (`Card_Number_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;