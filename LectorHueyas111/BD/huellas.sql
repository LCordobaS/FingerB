# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.1.41
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-12-12 19:27:35
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for huellas
DROP DATABASE IF EXISTS `huellas`;
CREATE DATABASE IF NOT EXISTS `huellas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `huellas`;


# Dumping structure for table huellas.comida
DROP TABLE IF EXISTS `comida`;
CREATE TABLE IF NOT EXISTS `comida` (
  `clave` int(12) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `salida` varchar(30) DEFAULT '0',
  `claveEmpleado` int(12) DEFAULT '0',
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.comida: 1 rows
/*!40000 ALTER TABLE `comida` DISABLE KEYS */;
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;


# Dumping structure for table huellas.destinos
DROP TABLE IF EXISTS `destinos`;
CREATE TABLE IF NOT EXISTS `destinos` (
  `clave` int(12) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `claveEmpleado` int(11) DEFAULT '0',
  `descripcion` varchar(100) DEFAULT '0',
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.destinos: 2 rows
/*!40000 ALTER TABLE `destinos` DISABLE KEYS */;
/*!40000 ALTER TABLE `destinos` ENABLE KEYS */;


# Dumping structure for table huellas.empleado
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `clave` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apPaterno` varchar(60) DEFAULT NULL,
  `apMaterno` varchar(60) DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `calle` varchar(60) DEFAULT NULL,
  `noExterior` varchar(20) DEFAULT NULL,
  `colonia` varchar(60) DEFAULT NULL,
  `municipio` varchar(60) DEFAULT NULL,
  `estado` varchar(60) DEFAULT NULL,
  `cp` varchar(20) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `cel` varchar(20) DEFAULT NULL,
  `eMail` varchar(25) DEFAULT NULL,
  `hueya` blob,
  `hueya1` blob,
  `foto` blob,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.empleado: 1 rows
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;


# Dumping structure for table huellas.entradas
DROP TABLE IF EXISTS `entradas`;
CREATE TABLE IF NOT EXISTS `entradas` (
  `clave` int(12) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `claveEmpleado` int(11) DEFAULT '0',
  `horaEntrada` time DEFAULT NULL,
  `entrada` varchar(20) DEFAULT '0',
  `destino` varchar(100) DEFAULT NULL,
  `horaSComida` time DEFAULT NULL,
  `horaEComida` time DEFAULT NULL,
  `horaSalida` time DEFAULT NULL,
  `bandera` smallint(6) DEFAULT NULL,
  `totalHoras` time DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.entradas: 18 rows
/*!40000 ALTER TABLE `entradas` DISABLE KEYS */;
/*!40000 ALTER TABLE `entradas` ENABLE KEYS */;


# Dumping structure for table huellas.huella
DROP TABLE IF EXISTS `huella`;
CREATE TABLE IF NOT EXISTS `huella` (
  `identificacion_usuario` bigint(20) unsigned NOT NULL,
  `nombres_usuario` varchar(250) NOT NULL,
  `huella1_usuario` blob NOT NULL,
  `huella2_usuario` blob NOT NULL,
  PRIMARY KEY (`identificacion_usuario`),
  KEY `cedula` (`identificacion_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table huellas.huella: ~2 rows (approximately)
/*!40000 ALTER TABLE `huella` DISABLE KEYS */;
/*!40000 ALTER TABLE `huella` ENABLE KEYS */;


# Dumping structure for table huellas.salidas
DROP TABLE IF EXISTS `salidas`;
CREATE TABLE IF NOT EXISTS `salidas` (
  `clave` int(12) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `salida` varchar(30) DEFAULT NULL,
  `claveEmpleado` int(12) DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

# Dumping data for table huellas.salidas: 0 rows
/*!40000 ALTER TABLE `salidas` DISABLE KEYS */;
/*!40000 ALTER TABLE `salidas` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
