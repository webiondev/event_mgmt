-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 06, 2016 at 06:38 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eventmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `id` int(4) NOT NULL,
  `description` varchar(50) NOT NULL,
  `venue` varchar(20) NOT NULL,
  `date_` date NOT NULL,
  `time_` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`id`, `description`, `venue`, `date_`, `time_`) VALUES
(3030, 'chill out session', 'galeria', '2016-05-29', '10:00:00'),
(9010, 'relaxation for everyone', 'swimming pool', '2015-04-28', '11:00:00'),
(9090, 'one two pass', 'indoor ', '2015-05-23', '10:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `group_`
--

CREATE TABLE `group_` (
  `name` varchar(30) NOT NULL,
  `total_members` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_`
--

INSERT INTO `group_` (`name`, `total_members`) VALUES
('chill out', 1),
('coaches', 2),
('counter attack', 2),
('Fitness Training', 1),
('football trainers', 1),
('full attack', 1),
('swippers', 1);

-- --------------------------------------------------------

--
-- Table structure for table `invitations`
--

CREATE TABLE `invitations` (
  `eveid` int(4) NOT NULL,
  `memid` varchar(8) NOT NULL,
  `invited` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invitations`
--

INSERT INTO `invitations` (`eveid`, `memid`, `invited`) VALUES
(3030, 'M3000', 'invited'),
(9010, 'M3000', 'invited'),
(9090, 'M3000', 'invited'),
(3030, 'M2000', 'invited'),
(9010, 'M2000', 'invited'),
(9090, 'M2000', 'invited'),
(3030, 'M1005', 'invited'),
(9010, 'M1005', 'invited'),
(9090, 'M1005', 'invited');

-- --------------------------------------------------------

--
-- Table structure for table `mbr_in_event`
--

CREATE TABLE `mbr_in_event` (
  `memid` varchar(8) NOT NULL,
  `eventid` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mbr_in_event`
--

INSERT INTO `mbr_in_event` (`memid`, `eventid`) VALUES

('M3000', 3030),
('M3000', 9010),
('M3000', 9090),
('M2000', 3030),
('M2000', 9010),
('M2000', 9090),
('M1005', 3030),
('M1005', 9010),
('M1005', 9090);

-- --------------------------------------------------------

--
-- Table structure for table `mbr_in_grp`
--

CREATE TABLE `mbr_in_grp` (
  `memid` varchar(8) NOT NULL,
  `grpname` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mbr_in_grp`
--

INSERT INTO `mbr_in_grp` (`memid`, `grpname`) VALUES
('M2000', 'football trainers'),
('M2000', 'counter attack'),
('M3000', 'coaches'),
('M3000', 'counter attack'),
('M3000', 'full attack'),
('M3000', 'swippers'),
('M2000', 'Fitness Training'),
('M3000', 'chill out'),
('M1005', 'coaches');

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE `meeting` (
  `eventid` int(4) NOT NULL,
  `status_` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `training`
--

CREATE TABLE `training` (
  `eventid` int(4) NOT NULL,
  `title` varchar(30) NOT NULL,
  `fee` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `training`
--

INSERT INTO `training` (`eventid`, `title`, `fee`) VALUES
(3030, 'chill out', 0),
(9090, 'passers', 10.5),
(9010, 'swimming gathering', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` varchar(8) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pass` varchar(8) NOT NULL,
  `position` varchar(10) NOT NULL,
  `status_` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `pass`, `position`, `status_`) VALUES
('A1001', 'Jasper ', 'xyz', 'admin', 'N/A'),
('A1002', 'kerim', 'xyz', 'admin', 'N/A'),
('A3005', 'arda', 'xyz', 'admin', 'N/A'),
('A4000', 'Jejeje', 'xyz', 'admin', 'N/A'),
('M1005', 'kliol', 'xyz', 'member', 'active'),
('M2000', 'Ghani M', 'xyz', 'member', 'active'),
('M3000', 'Derimov', 'xyz', 'member', 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_`
--
ALTER TABLE `group_`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `invitations`
--
ALTER TABLE `invitations`
  ADD KEY `eve_fk` (`eveid`),
  ADD KEY `member_fk` (`memid`);

--
-- Indexes for table `mbr_in_event`
--
ALTER TABLE `mbr_in_event`
  ADD KEY `mbr_event_fk1` (`memid`),
  ADD KEY `mbr_event_fk2` (`eventid`);

--
-- Indexes for table `mbr_in_grp`
--
ALTER TABLE `mbr_in_grp`
  ADD KEY `mbr_grp_fk1` (`memid`),
  ADD KEY `mbr_grp_fk2` (`grpname`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD KEY `meeting_fk` (`eventid`);

--
-- Indexes for table `training`
--
ALTER TABLE `training`
  ADD KEY `train_fk` (`eventid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invitations`
--
ALTER TABLE `invitations`
  ADD CONSTRAINT `eve_fk` FOREIGN KEY (`eveid`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `member_fk` FOREIGN KEY (`memid`) REFERENCES `user` (`id`);

--
-- Constraints for table `mbr_in_event`
--
ALTER TABLE `mbr_in_event`
  ADD CONSTRAINT `mbr_event_fk1` FOREIGN KEY (`memid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `mbr_event_fk2` FOREIGN KEY (`eventid`) REFERENCES `event` (`id`);

--
-- Constraints for table `mbr_in_grp`
--
ALTER TABLE `mbr_in_grp`
  ADD CONSTRAINT `mbr_grp_fk1` FOREIGN KEY (`memid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `mbr_grp_fk2` FOREIGN KEY (`grpname`) REFERENCES `group_` (`name`);

--
-- Constraints for table `meeting`
--
ALTER TABLE `meeting`
  ADD CONSTRAINT `meeting_fk` FOREIGN KEY (`eventid`) REFERENCES `event` (`id`);

--
-- Constraints for table `training`
--
ALTER TABLE `training`
  ADD CONSTRAINT `train_fk` FOREIGN KEY (`eventid`) REFERENCES `event` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
