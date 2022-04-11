CREATE DATABASE  IF NOT EXISTS `vtv_prueba_tecnica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vtv_prueba_tecnica`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: vtv_prueba_tecnica
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `automovil`
--

DROP TABLE IF EXISTS `automovil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `automovil` (
  `id_automovil` int NOT NULL AUTO_INCREMENT,
  `dominio` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `marca` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modelo` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_propietario` int DEFAULT NULL,
  PRIMARY KEY (`id_automovil`),
  KEY `fk_automovil_propietario1_idx` (`id_propietario`),
  CONSTRAINT `fk_automovil_propietario1` FOREIGN KEY (`id_propietario`) REFERENCES `propietario` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automovil`
--

LOCK TABLES `automovil` WRITE;
/*!40000 ALTER TABLE `automovil` DISABLE KEYS */;
INSERT INTO `automovil` VALUES (1,'ABC123','Toyota','Etios',1),(2,'AB123DE','Renault','Megane',2),(3,'BCD234','Chevrolet','Corsa',3),(4,'BC234DE','Volkswagen','Gol',1),(5,'CDE345','Peugeot','207',15),(6,'CD345EF','Ford','Ka',12),(7,'DEF456','Fiat','Palio',13),(8,'DE456FG','Volkswagen','Suran',12),(9,'EFG567','Toyota','Etios',4),(10,'EF567GH','Ford','Focus',1),(11,'ZXY987','Ford','Fiesta',4),(12,'ZX987YW','Volkswagen','Gol',2),(13,'VMM181','Renault','Clio',5),(15,'DAS459','Ford','Focus',24),(16,'RDM108','Volkswagen','Gol',25),(17,'AP901XW','Peugeot','208',25);
/*!40000 ALTER TABLE `automovil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspeccion`
--

DROP TABLE IF EXISTS `inspeccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspeccion` (
  `id_inspeccion` int NOT NULL AUTO_INCREMENT,
  `nro_inspeccion` int DEFAULT NULL,
  `fecha_inspeccion` date DEFAULT NULL,
  `estado_inspeccion` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `esta_exento` tinyint DEFAULT NULL,
  `id_automovil` int NOT NULL,
  `id_inspector` int NOT NULL,
  PRIMARY KEY (`id_inspeccion`),
  KEY `fk_inspeccion_inspector1_idx` (`id_inspector`),
  KEY `fk_inspeccion_automovil1_idx` (`id_automovil`),
  CONSTRAINT `fk_inspeccion_automovil1` FOREIGN KEY (`id_automovil`) REFERENCES `automovil` (`id_automovil`),
  CONSTRAINT `fk_inspeccion_inspector1` FOREIGN KEY (`id_inspector`) REFERENCES `inspector` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspeccion`
--

LOCK TABLES `inspeccion` WRITE;
/*!40000 ALTER TABLE `inspeccion` DISABLE KEYS */;
INSERT INTO `inspeccion` VALUES (4,998,'2022-03-17','Apto',0,1,8),(5,1001,'2022-03-18','Condicional',1,2,10),(6,890,'2022-03-02','Rechazado',1,3,17),(7,911,'2022-03-04','Apto',0,4,17),(8,921,'2022-03-10','Condicional',0,5,20),(9,990,'2022-03-16','Condicional',1,6,17),(10,1002,'2022-03-18','Apto',0,7,9),(11,977,'2022-03-14','Condicional',1,8,16),(12,1003,'2022-03-18','Apto',0,3,6),(13,392,'2021-02-04','Apto',0,1,6),(14,950,'2022-03-12','Condicional',0,11,18),(15,945,'2022-03-11','Rechazado',0,12,20),(16,985,'2022-03-15','Apto',0,13,6),(17,1004,'2022-03-18','Apto',0,11,20),(27,1005,'2022-03-20','Rechazado',0,15,17),(28,1006,'2022-03-20','Condicional',1,16,7),(29,1007,'2022-03-20','Apto',1,17,10);
/*!40000 ALTER TABLE `inspeccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspector`
--

DROP TABLE IF EXISTS `inspector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspector` (
  `id_persona` int NOT NULL,
  `nro_legajo` char(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `fk_inspector_persona1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspector`
--

LOCK TABLES `inspector` WRITE;
/*!40000 ALTER TABLE `inspector` DISABLE KEYS */;
INSERT INTO `inspector` VALUES (6,'A000'),(7,'A001'),(8,'A002'),(9,'A003'),(10,'B123'),(16,'B898'),(17,'C004'),(18,'C500'),(19,'C997'),(20,'C998');
/*!40000 ALTER TABLE `inspector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'Ford'),(2,'Fiat'),(3,'Volkswagen'),(4,'Chevrolet');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo`
--

DROP TABLE IF EXISTS `modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelo` (
  `id_modelo` int NOT NULL AUTO_INCREMENT,
  `id_marca` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_modelo`),
  KEY `fk_modelo_marca1_idx` (`id_marca`),
  CONSTRAINT `fk_modelo_marca1` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo`
--

LOCK TABLES `modelo` WRITE;
/*!40000 ALTER TABLE `modelo` DISABLE KEYS */;
INSERT INTO `modelo` VALUES (1,2,'Cronos'),(2,3,'Gol'),(3,4,'Onix'),(4,1,'Ka'),(5,2,'Palio');
/*!40000 ALTER TABLE `modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nombres` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `apellido` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'12345678','Carlos','Ramos'),(2,'23456789','Juana','Herrera'),(3,'34567890','Juan José','García'),(4,'45678901','Ana María','Flores'),(5,'56789012','Ramón','Valdez'),(6,'3180648','Paula','Ruiz'),(7,'35908901','Omar','Fernández'),(8,'3164001','Marcela','González'),(9,'31501650','Alan','Pinto'),(10,'5501611','Ana','Varela'),(11,'67890123','Pedro','González'),(12,'78901234','Emilia','Gómez'),(13,'89012345','Omar','Morales'),(14,'90123456','Inés','Pérez'),(15,'18163900','Pablo','Arias'),(16,'32470110','Fernando','Herrera'),(17,'40165101','Hernán','Franco'),(18,'9181652','Ramiro','Zelaya'),(19,'48256181','Florencia','Arias'),(20,'20771203','María','Estévez'),(24,'9532001','Elisa','Pérez'),(25,'7203101','Jorge','Díaz');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propietario`
--

DROP TABLE IF EXISTS `propietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propietario` (
  `id_persona` int NOT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `fk_propietario_persona1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propietario`
--

LOCK TABLES `propietario` WRITE;
/*!40000 ALTER TABLE `propietario` DISABLE KEYS */;
INSERT INTO `propietario` VALUES (1),(2),(3),(4),(5),(11),(12),(13),(14),(15),(24),(25);
/*!40000 ALTER TABLE `propietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `version`
--

DROP TABLE IF EXISTS `version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `version` (
  `id_version` int NOT NULL,
  `id_modelo` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_version`),
  KEY `fk_version_modelo1_idx` (`id_modelo`),
  CONSTRAINT `fk_version_modelo1` FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id_modelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version`
--

LOCK TABLES `version` WRITE;
/*!40000 ALTER TABLE `version` DISABLE KEYS */;
INSERT INTO `version` VALUES (1,1,'1.3L'),(2,1,'1.8L'),(3,2,'1.4L'),(4,2,'1.6L'),(5,3,'1.0L'),(6,3,'1.2L'),(7,4,'1.0L'),(8,4,'1.5L'),(9,4,'1.6L'),(10,5,'1.3L'),(11,5,'1.4L'),(12,5,'1.6L'),(13,5,'1.7L'),(14,5,'1.8L');
/*!40000 ALTER TABLE `version` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-11 10:11:28
