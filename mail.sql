-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 01, 2016 at 08:44 PM
-- Server version: 5.7.12-0ubuntu1
-- PHP Version: 7.0.4-7ubuntu2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mail`
--

-- --------------------------------------------------------

--
-- Table structure for table `ADMINSTRADOR`
--

CREATE TABLE `ADMINSTRADOR` (
  `id` int(11) UNSIGNED NOT NULL,
  `email` varchar(244) NOT NULL,
  `senh` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ALUNOS`
--

CREATE TABLE `ALUNOS` (
  `ID_ALU` int(11) NOT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FK_TURMA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CONFIGURACAO`
--

CREATE TABLE `CONFIGURACAO` (
  `ID_CONF` int(11) NOT NULL,
  `USUARIO` varchar(255) DEFAULT NULL,
  `EMAILHOST` varchar(255) DEFAULT NULL,
  `EMAILSMTP` varchar(255) DEFAULT NULL,
  `EMAILPROVEDOR` varchar(255) DEFAULT NULL,
  `PORTASMTP` int(255) DEFAULT NULL,
  `SENHA` varchar(255) DEFAULT NULL,
  `ASSINATURA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CURSOS`
--

CREATE TABLE `CURSOS` (
  `ID_CUR` int(11) NOT NULL,
  `NOME` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `EMAILENVIADOS`
--

CREATE TABLE `EMAILENVIADOS` (
  `ID_EM` int(11) NOT NULL,
  `TITULO` varchar(255) NOT NULL,
  `CORPO` varchar(255) NOT NULL,
  `FK_relmail` int(11) DEFAULT NULL,
  `FK_CURSO` int(11) DEFAULT NULL,
  `urlAnexo` varchar(2000) CHARACTER SET utf8 DEFAULT NULL,
  `DateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `relEmail`
--

CREATE TABLE `relEmail` (
  `id` int(11) UNSIGNED NOT NULL,
  `fk_id_em` int(255) NOT NULL,
  `fk_turma` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `TURMAS`
--

CREATE TABLE `TURMAS` (
  `ID_TUR` int(255) NOT NULL,
  `NUMERO` varchar(255) NOT NULL,
  `FK_CUR` int(11) NOT NULL,
  `DATA_INICIO` date NOT NULL,
  `DATA_FIM` date NOT NULL,
  `periodo` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ADMINSTRADOR`
--
ALTER TABLE `ADMINSTRADOR`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ALUNOS`
--
ALTER TABLE `ALUNOS`
  ADD PRIMARY KEY (`ID_ALU`),
  ADD KEY `ALUNOS_fk0` (`FK_TURMA`);

--
-- Indexes for table `CONFIGURACAO`
--
ALTER TABLE `CONFIGURACAO`
  ADD PRIMARY KEY (`ID_CONF`),
  ADD UNIQUE KEY `USUARIO` (`USUARIO`);

--
-- Indexes for table `CURSOS`
--
ALTER TABLE `CURSOS`
  ADD PRIMARY KEY (`ID_CUR`),
  ADD UNIQUE KEY `NOME` (`NOME`);

--
-- Indexes for table `EMAILENVIADOS`
--
ALTER TABLE `EMAILENVIADOS`
  ADD PRIMARY KEY (`ID_EM`),
  ADD KEY `EMAILENVIADOS_fk0` (`FK_relmail`),
  ADD KEY `EMAILENVIADOS_fk1` (`FK_CURSO`);

--
-- Indexes for table `relEmail`
--
ALTER TABLE `relEmail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `TURMAS`
--
ALTER TABLE `TURMAS`
  ADD PRIMARY KEY (`ID_TUR`),
  ADD UNIQUE KEY `NUMERO` (`NUMERO`),
  ADD KEY `TURMAS_fk0` (`FK_CUR`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ADMINSTRADOR`
--
ALTER TABLE `ADMINSTRADOR`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `ALUNOS`
--
ALTER TABLE `ALUNOS`
  MODIFY `ID_ALU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT for table `CONFIGURACAO`
--
ALTER TABLE `CONFIGURACAO`
  MODIFY `ID_CONF` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `CURSOS`
--
ALTER TABLE `CURSOS`
  MODIFY `ID_CUR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `EMAILENVIADOS`
--
ALTER TABLE `EMAILENVIADOS`
  MODIFY `ID_EM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `relEmail`
--
ALTER TABLE `relEmail`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `TURMAS`
--
ALTER TABLE `TURMAS`
  MODIFY `ID_TUR` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `ALUNOS`
--
ALTER TABLE `ALUNOS`
  ADD CONSTRAINT `ALUNOS_fk0` FOREIGN KEY (`FK_TURMA`) REFERENCES `TURMAS` (`ID_TUR`);

--
-- Constraints for table `EMAILENVIADOS`
--
ALTER TABLE `EMAILENVIADOS`
  ADD CONSTRAINT `EMAILENVIADOS_fk0` FOREIGN KEY (`FK_relmail`) REFERENCES `TURMAS` (`ID_TUR`),
  ADD CONSTRAINT `EMAILENVIADOS_fk1` FOREIGN KEY (`FK_CURSO`) REFERENCES `CURSOS` (`ID_CUR`);

--
-- Constraints for table `TURMAS`
--
ALTER TABLE `TURMAS`
  ADD CONSTRAINT `TURMAS_fk0` FOREIGN KEY (`FK_CUR`) REFERENCES `CURSOS` (`ID_CUR`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
