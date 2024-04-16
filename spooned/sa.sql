CREATE OR REPLACE PROCEDURE first_procedure (p_myname IN VARCHAR2)
IS
BEGIN
dbms_output.put_line ('Hi! my name is' ||p_myname);
END;

CREATE OR REPLACE PROCEDURE GENERATE_NEW_TABLE
     (TEMP_PRODS varchar2, COLUMNS_DATATYPES varchar2)
     is
     q_str varchar2(200);
     var_1 varchar2(200);
     var_2 varchar2(200);
     var_3 varchar2(200);
     Begin
    var_1 :=  'CREATE TABLE';
    var_2 := '(';
    var_3 := ')';
    var_final_str := var_1 || TEMP_PRODS || var_2 || COLUMNS_DATATYPES || var _3;
    EXECUTE IMMEDIATE var_final_str;
     end;
--
-- Database: `samplevideo_db`
--

-- --------------------------------------------------------

SELECT INSTR('Melbourne, Australia', 'a', -1) into sal1 FROM DUAL;

--
-- Table structure for table `user_details`
--

CREATE TABLE IF NOT EXISTS `user_details` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` tinyint(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

SELECT DBMS_RANDOM.VALUE FROM DUAL;

--
-- Dumping data for table `user_details`
--

CREATE OR REPLACE PROCEDURE first_procedure (p_myname IN VARCHAR2)
IS
BEGIN
dbms_output.put_line ('Hi! my name is' ||p_myname);
END;

INSERT INTO `user_details` (`user_id`, `username`, `first_name`, `last_name`, `gender`, `password`, `status`) VALUES
(1, 'rogers63', 'david', 'john', 'Female', 'e6a33eee180b07e563d74fee8c2c66b8', 1),
(2, 'mike28', 'rogers', 'paul', 'Male', '2e7dc6b8a1598f4f75c3eaa47958ee2f', 1),
(3, 'rivera92', 'david', 'john', 'Male', '1c3a8e03f448d211904161a6f5849b68', 1),
(4, 'ross95', 'maria', 'sanders', 'Male', '62f0a68a4179c5cdd997189760cbcf18', 1),
(5, 'paul85', 'morris', 'miller', 'Female', '61bd060b07bddfecccea56a82b850ecf', 1),
(6, 'smith34', 'daniel', 'michael', 'Female', '7055b3d9f5cb2829c26cd7e0e601cde5', 1),
(7, 'james84', 'sanders', 'paul', 'Female', 'b7f72d6eb92b45458020748c8d1a3573', 1),
(8, 'daniel53', 'mark', 'mike', 'Male', '299cbf7171ad1b2967408ed200b4e26c', 1),
(9, 'brooks80', 'morgan', 'maria', 'Female', 'aa736a35dc15934d67c0a999dccff8f6', 1),
(10, 'morgan65', 'paul', 'miller', 'Female', 'a28dca31f5aa5792e1cefd1dfd098569', 1);

-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2020 at 12:18 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE bill (
  `bill_id` int(4) NOT NULL,
  `date` date DEFAULT NULL,
  `customer_name` char(50) DEFAULT NULL,
  `room_no` int(11) NOT NULL,
  `amount` int(6) DEFAULT NULL,
  `payment` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO bill (`bill_id`, `date`, `customer_name`, `room_no`, `amount`, `payment`) VALUES
(1, '2020-08-10', 'Bruce Wayne', 403, 28000, 'card'),
(2, '2020-08-11', 'Diana', 103, 8000, 'card'),
(3, '2020-08-11', 'Kent Clark', 102, 4500, 'cash'),
(4, '2020-08-15', 'Louise Lane', 102, 4500, 'cash'),
(5, '2020-08-17', 'Pepper Pots', 402, 8500, 'card'),
(6, '2020-08-17', 'Steven Strange', 404, 9500, 'card'),
(7, '2020-08-20', 'Marques Brownlee', 101, 2500, 'cash'),
(8, '2020-10-15', 'John', 202, 3500, 'card'),
(9, '2020-10-15', 'Bruce Wayne', 203, 4500, 'card'),
(10, '2020-10-15', 'Mega Mind', 404, 5500, 'card'),
(11, '2020-10-20', 'Mega Mind', 204, 2500, 'card'),
(12, '2020-10-31', 'Wasim Saifee', 302, 3200, 'cash'),
(13, '2020-10-15', 'Martha Wayne', 404, 5500, 'card'),
(14, '2020-10-16', 'Talha Fakih', 401, 3200, 'cash'),
(15, '2020-10-16', 'Talha Fakih', 301, 2200, 'cash'),
(16, '2020-10-31', 'Shroud', 201, 3300, 'card'),
(17, '2020-10-20', 'Wasim Saifee', 103, 5500, 'card'),
(18, '2020-10-16', 'Aaris Kazi', 101, 3200, 'card');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(4) NOT NULL,
  `cus_name` varchar(50) DEFAULT NULL,
  `reservation_date` date DEFAULT NULL,
  `room_no` int(3) NOT NULL,
  `no_days` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `cus_name`, `reservation_date`, `room_no`, `no_days`) VALUES
(1, 'Shahrukh Khan', '2020-08-01', 202, 4),
(2, 'Timothee Chalamet', '2020-08-17', 0, 2),
(3, 'Saif Hmadare', '2020-08-20', 0, 10),
(4, 'Christiano Ronaldo', '2020-08-16', 0, 5),
(5, 'Pooja Chauhan', '2020-08-26', 0, 10),
(6, 'Muskan Kazi', '2020-08-25', 0, 5),
(7, 'Zendaya', '2020-08-25', 0, 5),
(8, 'Talha Fakih', '2020-08-25', 0, 5),
(9, 'Bhagyashree Chaudhary', '2020-08-30', 0, 3),
(10, 'Kylie Jenner', '2020-10-20', 104, 4),
(11, 'Zendaya', '2020-10-16', 201, 7),
(12, 'Cleo', '2020-10-12', 104, 1992),
(13, 'Jack Frost', '2020-10-16', 204, 4),
(14, 'Akon', '2020-10-20', 304, 4);

-- --------------------------------------------------------

--
-- Table structure for table `cus_room`
--

CREATE TABLE `cus_room` (
  `room_no` int(3) DEFAULT NULL,
  `name` char(50) DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `amount` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cus_room`
--

INSERT INTO `cus_room` (`room_no`, `name`, `check_in`, `check_out`, `amount`) VALUES
(102, 'Aaris Kazi', '2020-10-16', '2020-10-20', 3500),
(204, 'Jack Frost', '2020-10-16', '2020-10-23', 2500),
(304, 'Akon', '2020-10-20', '2020-10-24', 2700);

-- --------------------------------------------------------

--
-- Table structure for table `hotel_system`
--

CREATE TABLE `hotel_system` (
  `customer_id` int(4) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `room_no` int(4) NOT NULL,
  `price` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel_system`
--

INSERT INTO `hotel_system` (`customer_id`, `name`, `check_in`, `check_out`, `room_no`, `price`) VALUES
(2, 'Wasim Saifee', '2020-07-05', '2020-07-10', 103, 5500),
(3, 'Zendaya', '2020-07-11', '2020-07-15', 202, 3500),
(4, 'Aaris Kazi', '2020-07-08', '2020-07-11', 204, 2500),
(5, 'Talha Fakih', '2020-07-08', '2020-07-13', 301, 2200),
(6, 'Altamash Khan', '2020-07-09', '2020-07-13', 302, 3200),
(7, 'Hugh Jackman', '2020-07-12', '2020-07-15', 404, 5500),
(8, 'john', '2020-10-10', '2020-10-10', 202, 3500),
(9, 'Rocky Dsouza', '2020-10-15', '2020-10-16', 203, 4500),
(10, 'Pooja Chauhan', '2020-10-10', '2020-10-15', 202, 3500),
(11, 'John Doe', '2020-10-16', '2020-10-20', 104, 2500),
(12, 'Bruce Willis', '2020-10-15', '2020-10-20', 103, 5500),
(13, 'Shroud', '2020-10-15', '2020-10-20', 201, 3300),
(14, 'Martha Wayne', '2020-10-16', '2020-10-20', 404, 5500),
(15, 'Martha Wayne', '2020-10-16', '2020-10-20', 404, 5500),
(16, 'Jenny Dsouza', '2020-10-16', '2020-10-20', 404, 5500),
(17, 'Talha Fakih', '2020-10-01', '2020-10-03', 401, 3200),
(18, 'Aaris Kazi', '2020-10-16', '2020-10-20', 102, 3500);

-- --------------------------------------------------------

--
-- Table structure for table `room_status`
--

CREATE TABLE `room_status` (
  `room_no` int(3) NOT NULL,
  `price` int(6) NOT NULL,
  `status` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room_status`
--

INSERT INTO `room_status` (`room_no`, `price`, `status`) VALUES
(101, 3200, 'available'),
(102, 3500, 'not available'),
(103, 5500, 'available'),
(104, 2500, 'reserved'),
(201, 3300, 'available'),
(202, 3500, 'available'),
(203, 4500, 'available'),
(204, 2500, 'reserved'),
(301, 2200, 'available'),
(302, 3200, 'available'),
(303, 4200, 'available'),
(304, 2700, 'reserved'),
(401, 3200, 'available'),
(402, 3000, 'available'),
(403, 3500, 'available'),
(404, 5500, 'available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `room_no` (`room_no`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `hotel_system`
--
ALTER TABLE `hotel_system`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `room_no` (`room_no`);

--
-- Indexes for table `room_status`
--
ALTER TABLE `room_status`
  ADD PRIMARY KEY (`room_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `hotel_system`
--
ALTER TABLE `hotel_system`
  MODIFY `customer_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`room_no`) REFERENCES `room_status` (`room_no`) ON UPDATE CASCADE;

--
-- Constraints for table `hotel_system`
--
ALTER TABLE `hotel_system`
  ADD CONSTRAINT `hotel_system_ibfk_1` FOREIGN KEY (`room_no`) REFERENCES `room_status` (`room_no`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;