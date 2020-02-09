CREATE DATABASE  IF NOT EXISTS `clinicalsys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinicalsys`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: remotemysql.com    Database: 10xTaRoFxv
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_state` int(11) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoeb98n82eph1dx43v3y2bcmsl` (`doctor_id`),
  KEY `FK4apif2ewfyf14077ichee8g06` (`patient_id`),
  KEY `FK8yxiq8d6ubccrih94xicd2l5b` (`room_id`),
  KEY `FK8v7dike6yqe1ii20gt2ovmk4o` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--


/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,'2020-03-14 08:30:00','2020-03-14 08:00:00',10,5,6,10),(2,0,'2020-02-10 12:00:00','2020-02-10 11:30:00',10,3,NULL,1),(3,0,'2020-02-13 11:00:00','2020-02-13 10:30:00',10,3,NULL,3),(4,1,'2020-02-24 13:30:00','2020-02-24 13:00:00',10,4,7,10),(5,1,'2020-03-07 08:00:00','2020-03-07 07:30:00',10,4,7,1),(6,1,'2020-02-09 09:30:00','2020-02-09 09:00:00',10,3,7,10),(7,1,'2020-03-14 14:00:00','2020-03-14 13:30:00',10,3,6,3),(8,1,'2020-02-05 11:00:00','2020-02-05 10:30:00',10,3,4,3),(9,0,'2020-03-09 12:30:00','2020-03-09 12:00:00',10,2,NULL,3),(10,1,'2020-02-13 07:30:00','2020-02-13 07:00:00',10,5,6,10),(11,0,'2020-02-20 12:30:00','2020-02-20 12:00:00',10,5,NULL,10),(12,1,'2020-02-07 14:00:00','2020-02-07 13:30:00',10,4,5,1),(13,1,'2020-02-23 14:00:00','2020-02-23 13:30:00',10,2,4,1),(14,0,'2020-03-20 09:00:00','2020-03-20 08:30:00',10,5,NULL,1),(15,1,'2020-03-04 11:30:00','2020-03-04 11:00:00',10,3,5,3),(16,0,'2020-02-13 08:00:00','2020-02-13 07:30:00',10,5,NULL,1),(17,1,'2020-02-26 14:00:00','2020-02-26 13:30:00',10,5,4,1),(18,0,'2020-02-12 08:00:00','2020-02-12 07:30:00',10,4,NULL,1),(19,1,'2020-03-15 11:00:00','2020-03-15 10:30:00',10,5,4,3),(20,2,'2020-02-06 14:00:00','2020-02-06 13:30:00',10,2,NULL,10),(21,1,'2020-02-20 08:30:00','2020-02-20 08:00:00',10,2,7,1),(22,1,'2020-02-24 12:00:00','2020-02-24 11:30:00',10,4,6,1),(23,1,'2020-03-11 11:30:00','2020-03-11 11:00:00',10,4,7,10),(24,1,'2020-02-28 14:00:00','2020-02-28 13:30:00',10,5,5,1),(25,0,'2020-03-09 11:00:00','2020-03-09 10:30:00',10,3,NULL,10),(26,0,'2020-02-17 11:00:00','2020-02-17 10:30:00',10,2,NULL,3),(27,1,'2020-03-19 07:30:00','2020-03-19 07:00:00',10,2,7,10),(28,1,'2020-03-24 13:30:00','2020-03-24 13:00:00',10,2,6,1),(29,0,'2020-03-09 10:00:00','2020-03-09 09:30:00',10,2,NULL,3),(30,1,'2020-02-06 09:30:00','2020-02-06 09:00:00',11,2,4,2),(31,1,'2020-02-09 09:30:00','2020-02-09 09:00:00',11,2,5,2),(32,2,'2020-02-27 11:30:00','2020-02-27 11:00:00',11,2,NULL,7),(33,1,'2020-03-19 14:00:00','2020-03-19 13:30:00',11,5,4,2),(34,4,'2020-02-01 08:00:00','2020-02-01 07:30:00',11,3,NULL,7),(35,1,'2020-03-06 12:30:00','2020-03-06 12:00:00',11,4,4,6),(36,4,'2020-02-03 12:30:00','2020-02-03 12:00:00',11,2,NULL,6),(37,1,'2020-02-19 09:30:00','2020-02-19 09:00:00',11,2,4,8),(38,1,'2020-02-25 09:00:00','2020-02-25 08:30:00',11,2,7,6),(39,4,'2020-02-01 11:00:00','2020-02-01 10:30:00',11,4,NULL,8),(40,1,'2020-02-20 13:00:00','2020-02-20 12:30:00',11,3,7,6),(41,1,'2020-03-23 13:30:00','2020-03-23 13:00:00',11,4,5,7),(42,1,'2020-03-06 09:30:00','2020-03-06 09:00:00',11,5,6,8),(43,1,'2020-02-21 08:00:00','2020-02-21 07:30:00',11,2,5,2),(44,1,'2020-03-18 14:00:00','2020-03-18 13:30:00',11,4,5,2),(45,0,'2020-03-04 10:30:00','2020-03-04 10:00:00',11,5,NULL,7),(46,0,'2020-02-23 12:00:00','2020-02-23 11:30:00',11,3,NULL,8),(47,1,'2020-02-26 08:00:00','2020-02-26 07:30:00',11,2,4,2),(48,0,'2020-02-07 12:30:00','2020-02-07 12:00:00',11,5,NULL,6),(49,1,'2020-02-23 13:30:00','2020-02-23 13:00:00',11,2,5,8),(50,0,'2020-02-20 14:00:00','2020-02-20 13:30:00',11,5,NULL,2),(51,0,'2020-02-18 08:30:00','2020-02-18 08:00:00',11,4,NULL,2),(52,1,'2020-03-09 11:30:00','2020-03-09 11:00:00',11,2,5,6),(53,1,'2020-03-17 13:00:00','2020-03-17 12:30:00',11,4,6,2),(54,1,'2020-03-23 11:30:00','2020-03-23 11:00:00',12,4,3,8),(55,0,'2020-02-25 11:00:00','2020-02-25 10:30:00',12,5,NULL,1),(56,1,'2020-03-11 07:30:00','2020-03-11 07:00:00',12,2,3,4),(57,1,'2020-03-28 09:00:00','2020-03-28 08:30:00',12,2,1,1),(58,0,'2020-03-01 09:30:00','2020-03-01 09:00:00',12,4,NULL,1),(59,1,'2020-02-26 08:00:00','2020-02-26 07:30:00',12,2,2,6),(60,1,'2020-03-14 09:00:00','2020-03-14 08:30:00',12,3,2,6),(61,0,'2020-03-02 13:00:00','2020-03-02 12:30:00',12,4,NULL,4),(62,0,'2020-02-19 10:30:00','2020-02-19 10:00:00',12,2,NULL,8),(63,1,'2020-03-15 09:00:00','2020-03-15 08:30:00',12,2,1,6),(64,0,'2020-03-11 13:00:00','2020-03-11 12:30:00',12,5,NULL,6),(65,1,'2020-03-22 08:30:00','2020-03-22 08:00:00',12,3,2,1),(66,0,'2020-03-16 09:30:00','2020-03-16 09:00:00',12,2,NULL,8),(67,0,'2020-03-08 11:30:00','2020-03-08 11:00:00',12,3,NULL,4),(68,1,'2020-02-24 08:30:00','2020-02-24 08:00:00',12,3,2,8),(69,0,'2020-03-16 08:30:00','2020-03-16 08:00:00',12,2,NULL,8),(70,1,'2020-03-03 11:30:00','2020-03-03 11:00:00',12,5,3,4),(71,1,'2020-02-19 12:00:00','2020-02-19 11:30:00',12,2,2,8),(72,0,'2020-02-17 08:30:00','2020-02-17 08:00:00',12,4,NULL,8),(73,0,'2020-03-01 11:30:00','2020-03-01 11:00:00',12,5,NULL,6),(74,0,'2020-02-07 09:30:00','2020-02-07 09:00:00',12,4,NULL,1),(75,0,'2020-02-05 11:30:00','2020-02-05 11:00:00',12,4,NULL,8),(76,0,'2020-03-03 12:30:00','2020-03-03 12:00:00',12,5,NULL,1),(77,1,'2020-03-15 09:30:00','2020-03-15 09:00:00',12,4,3,8),(78,1,'2020-02-16 12:00:00','2020-02-16 11:30:00',12,2,1,4),(79,1,'2020-02-05 08:30:00','2020-02-05 08:00:00',13,2,1,5),(80,0,'2020-02-18 11:30:00','2020-02-18 11:00:00',13,2,NULL,10),(81,0,'2020-03-02 14:00:00','2020-03-02 13:30:00',13,2,NULL,10),(82,0,'2020-03-11 12:00:00','2020-03-11 11:30:00',13,4,NULL,5),(83,0,'2020-02-25 07:30:00','2020-02-25 07:00:00',13,2,NULL,5),(84,4,'2020-02-01 11:30:00','2020-02-01 11:00:00',13,2,NULL,5),(85,1,'2020-02-14 08:30:00','2020-02-14 08:00:00',13,5,3,10),(86,0,'2020-02-09 11:30:00','2020-02-09 11:00:00',13,5,NULL,10),(87,1,'2020-03-02 07:30:00','2020-03-02 07:00:00',13,3,1,10),(88,0,'2020-02-20 08:00:00','2020-02-20 07:30:00',13,5,NULL,5),(89,1,'2020-03-12 13:30:00','2020-03-12 13:00:00',13,2,3,5),(90,1,'2020-03-21 08:30:00','2020-03-21 08:00:00',13,5,3,5),(91,0,'2020-02-21 08:00:00','2020-02-21 07:30:00',13,4,NULL,5),(92,0,'2020-03-18 13:00:00','2020-03-18 12:30:00',13,4,NULL,5),(93,0,'2020-03-04 08:30:00','2020-03-04 08:00:00',13,5,NULL,5),(94,1,'2020-03-20 12:00:00','2020-03-20 11:30:00',13,4,1,5),(95,0,'2020-03-15 10:30:00','2020-03-15 10:00:00',13,3,NULL,10),(96,1,'2020-03-08 10:30:00','2020-03-08 10:00:00',13,3,3,5),(97,1,'2020-03-10 12:00:00','2020-03-10 11:30:00',13,4,2,5),(98,0,'2020-02-08 07:30:00','2020-02-08 07:00:00',13,3,NULL,10),(99,1,'2020-03-01 08:30:00','2020-03-01 08:00:00',13,4,1,5),(100,4,'2020-02-04 10:30:00','2020-02-04 10:00:00',13,3,NULL,5),(101,0,'2020-02-18 08:30:00','2020-02-18 08:00:00',13,2,NULL,5),(102,1,'2020-03-22 13:00:00','2020-03-22 12:30:00',14,2,10,4),(103,1,'2020-03-27 09:00:00','2020-03-27 08:30:00',14,2,9,4),(104,0,'2020-03-06 13:00:00','2020-03-06 12:30:00',14,5,NULL,1),(105,1,'2020-03-06 12:30:00','2020-03-06 12:00:00',14,4,10,1),(106,0,'2020-02-15 10:30:00','2020-02-15 10:00:00',14,2,NULL,8),(107,0,'2020-03-12 08:30:00','2020-03-12 08:00:00',14,2,NULL,4),(108,0,'2020-03-18 08:30:00','2020-03-18 08:00:00',14,4,NULL,4),(109,4,'2020-02-03 09:30:00','2020-02-03 09:00:00',14,5,NULL,3),(110,4,'2020-02-02 07:30:00','2020-02-02 07:00:00',14,2,NULL,4),(111,1,'2020-03-04 08:00:00','2020-03-04 07:30:00',14,2,8,8),(112,0,'2020-02-28 12:30:00','2020-02-28 12:00:00',14,2,NULL,8),(113,1,'2020-03-27 12:30:00','2020-03-27 12:00:00',14,4,8,4),(114,4,'2020-02-02 09:00:00','2020-02-02 08:30:00',14,2,NULL,1),(115,0,'2020-02-19 10:30:00','2020-02-19 10:00:00',14,3,NULL,1),(116,0,'2020-03-16 08:30:00','2020-03-16 08:00:00',14,5,NULL,4),(117,0,'2020-02-15 12:30:00','2020-02-15 12:00:00',14,3,NULL,8),(118,1,'2020-02-13 11:00:00','2020-02-13 10:30:00',14,4,11,8),(119,1,'2020-03-03 13:30:00','2020-03-03 13:00:00',15,5,12,6),(120,0,'2020-03-16 10:30:00','2020-03-16 10:00:00',15,2,NULL,6),(121,0,'2020-03-02 14:00:00','2020-03-02 13:30:00',15,4,NULL,6),(122,0,'2020-02-16 14:00:00','2020-02-16 13:30:00',15,5,NULL,6),(123,1,'2020-03-02 12:30:00','2020-03-02 12:00:00',15,5,12,6),(124,1,'2020-03-05 12:30:00','2020-03-05 12:00:00',15,3,12,6),(125,1,'2020-03-09 10:30:00','2020-03-09 10:00:00',15,3,12,6),(126,1,'2020-03-28 09:30:00','2020-03-28 09:00:00',15,3,12,6),(127,0,'2020-03-23 08:00:00','2020-03-23 07:30:00',15,5,NULL,6),(128,1,'2020-03-23 11:00:00','2020-03-23 10:30:00',15,4,12,6),(129,1,'2020-03-09 08:00:00','2020-03-09 07:30:00',15,2,12,6),(130,1,'2020-03-11 12:30:00','2020-03-11 12:00:00',15,2,12,6),(131,0,'2020-03-21 13:00:00','2020-03-21 12:30:00',15,2,NULL,6),(132,4,'2020-02-03 11:30:00','2020-02-03 11:00:00',15,4,NULL,6),(133,0,'2020-03-22 09:00:00','2020-03-22 08:30:00',15,3,NULL,6),(134,0,'2020-03-02 07:30:00','2020-03-02 07:00:00',15,4,NULL,6),(135,1,'2020-03-15 13:00:00','2020-03-15 12:30:00',15,2,12,6),(136,0,'2020-03-01 13:00:00','2020-03-01 12:30:00',15,5,NULL,6),(137,1,'2020-03-24 14:00:00','2020-03-24 13:30:00',15,3,12,6),(138,0,'2020-02-19 10:30:00','2020-02-19 10:00:00',15,3,NULL,6),(139,0,'2020-02-12 11:00:00','2020-02-12 10:30:00',15,3,NULL,6),(140,0,'2020-02-08 13:30:00','2020-02-08 13:00:00',15,4,NULL,6),(141,1,'2020-03-07 12:00:00','2020-03-07 11:30:00',15,2,12,6),(142,0,'2020-02-16 12:30:00','2020-02-16 12:00:00',15,4,NULL,6),(143,1,'2020-02-10 11:30:00','2020-02-10 11:00:00',15,4,12,6),(144,1,'2020-03-17 09:30:00','2020-03-17 09:00:00',15,2,12,6),(145,0,'2020-02-11 13:00:00','2020-02-11 12:30:00',15,3,NULL,6),(146,1,'2020-02-13 06:37:00','2020-02-13 06:07:00',10,NULL,7,10);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;


--
-- Table structure for table `appointment_type`
--

DROP TABLE IF EXISTS `appointment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_type`
--


/*!40000 ALTER TABLE `appointment_type` DISABLE KEYS */;
INSERT INTO `appointment_type` VALUES (1,'Ocni pregled'),(2,'Pregled grla'),(3,'Pregled sluha'),(4,'Pregled vida'),(5,'Pregled pluca'),(6,'Alergo test'),(7,'Merenje holesterola'),(8,'Sistematski pregled'),(9,'Stomatoloski pregled'),(10,'Rutinska kontrola');
/*!40000 ALTER TABLE `appointment_type` ENABLE KEYS */;


--
-- Table structure for table `appointment_type_price_discount`
--

DROP TABLE IF EXISTS `appointment_type_price_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_type_price_discount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discound` double NOT NULL,
  `price` double NOT NULL,
  `appointment_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmm1bwxay0ff1hbqc88ecdba` (`appointment_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_type_price_discount`
--

/*!40000 ALTER TABLE `appointment_type_price_discount` DISABLE KEYS */;
INSERT INTO `appointment_type_price_discount` VALUES (1,0.95,590,3),(2,0.8,1200,10),(3,1,456,1),(4,0.1,380,8),(5,0.5,1200,7),(6,0.75,4580,2),(7,0.1,856,6),(8,1,850,1),(9,1,180,4),(10,0.98,1120,6),(11,0.85,950,8),(12,0.7,2200,10),(13,1,920,5),(14,1,720,3),(15,1,150,1),(16,0.7,240,4),(17,0.5,1500,8),(18,0.9,860,6);
/*!40000 ALTER TABLE `appointment_type_price_discount` ENABLE KEYS */;



--
-- Table structure for table `business_report`
--

DROP TABLE IF EXISTS `business_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clinic_rating` float NOT NULL,
  `doctor_rating` float NOT NULL,
  `graphic` float NOT NULL,
  `income` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_report`
--


/*!40000 ALTER TABLE `business_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `business_report` ENABLE KEYS */;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `clinic_name` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--


/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (1,'Bulevar Cara Lazara 18','Novi Sad','Klinicki Centar Novi Sad','Srbija',NULL,8.3),(2,'Nemanjina 163a','Beograd','Centralna Klinika Beograd','Srbija',NULL,9.2),(3,'Kerska 34','Subotica','Okruzna Bolnica','Srbija',NULL,9.99),(4,'Brdskih Heroja 34','Vlasenica','Mesna Ambulanta Vlasenica','Bosna i Hercegovina',NULL,5.2);
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;


--
-- Table structure for table `clinic_admin`
--

DROP TABLE IF EXISTS `clinic_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_admin` (
  `id` bigint(20) NOT NULL,
  `clinic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4nr7d8t3dqv8law3iqcw84t7b` (`clinic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_admin`
--

/*!40000 ALTER TABLE `clinic_admin` DISABLE KEYS */;
INSERT INTO `clinic_admin` VALUES (6,2),(7,1),(8,3),(9,4);
/*!40000 ALTER TABLE `clinic_admin` ENABLE KEYS */;

--
-- Table structure for table `clinic_admin_appointments_to_process`
--

DROP TABLE IF EXISTS `clinic_admin_appointments_to_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_admin_appointments_to_process` (
  `clinic_admin_id` bigint(20) NOT NULL,
  `appointments_to_process_id` bigint(20) NOT NULL,
  PRIMARY KEY (`clinic_admin_id`,`appointments_to_process_id`),
  KEY `FK7tlq982m3jf2dot5aee7dcji2` (`appointments_to_process_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_admin_appointments_to_process`
--

/*!40000 ALTER TABLE `clinic_admin_appointments_to_process` DISABLE KEYS */;
INSERT INTO `clinic_admin_appointments_to_process` VALUES (6,2),(6,3),(6,9),(6,11),(6,14),(6,16),(6,18),(6,25),(6,26),(6,29),(6,31),(6,45),(6,46),(6,48),(6,50),(6,51),(6,52),(7,55),(7,58),(7,61),(7,62),(7,64),(7,66),(7,67),(7,69),(7,72),(7,73),(7,74),(7,75),(7,76),(7,80),(7,81),(7,82),(7,83),(7,86),(7,88),(7,91),(7,92),(7,93),(7,95),(7,98),(7,101),(8,104),(8,106),(8,107),(8,108),(8,112),(8,115),(8,116),(8,117),(9,120),(9,121),(9,122),(9,127),(9,131),(9,133),(9,134),(9,136),(9,138),(9,139),(9,140),(9,142),(9,145);
/*!40000 ALTER TABLE `clinic_admin_appointments_to_process` ENABLE KEYS */;

--
-- Table structure for table `clinic_admin_vacations_to_process`
--

DROP TABLE IF EXISTS `clinic_admin_vacations_to_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_admin_vacations_to_process` (
  `clinic_admin_id` bigint(20) NOT NULL,
  `vacations_to_process_id` bigint(20) NOT NULL,
  PRIMARY KEY (`clinic_admin_id`,`vacations_to_process_id`),
  KEY `FKey6twpwur7i3g71lk7uufdubi` (`vacations_to_process_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_admin_vacations_to_process`
--

/*!40000 ALTER TABLE `clinic_admin_vacations_to_process` DISABLE KEYS */;
INSERT INTO `clinic_admin_vacations_to_process` VALUES (7,6),(9,11);
/*!40000 ALTER TABLE `clinic_admin_vacations_to_process` ENABLE KEYS */;

--
-- Table structure for table `clinic_appointment_type_price_discounts`
--

DROP TABLE IF EXISTS `clinic_appointment_type_price_discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_appointment_type_price_discounts` (
  `clinic_id` bigint(20) NOT NULL,
  `appointment_type_price_discounts_id` bigint(20) NOT NULL,
  PRIMARY KEY (`clinic_id`,`appointment_type_price_discounts_id`),
  UNIQUE KEY `UK_4lfc3snmp2t1x305vo1qutpx` (`appointment_type_price_discounts_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_appointment_type_price_discounts`
--

-- -- LOCK TABLES `clinic_appointment_type_price_discounts` WRITE;
/*!40000 ALTER TABLE `clinic_appointment_type_price_discounts` DISABLE KEYS */;
INSERT INTO `clinic_appointment_type_price_discounts` VALUES (1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(3,14),(3,15),(3,16),(3,17),(4,18);
/*!40000 ALTER TABLE `clinic_appointment_type_price_discounts` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `clinic_center_admin`
--

DROP TABLE IF EXISTS `clinic_center_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_center_admin` (
  `predefined` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_center_admin`
--

-- LOCK TABLES `clinic_center_admin` WRITE;
/*!40000 ALTER TABLE `clinic_center_admin` DISABLE KEYS */;
INSERT INTO `clinic_center_admin` VALUES (_binary '',1);
/*!40000 ALTER TABLE `clinic_center_admin` ENABLE KEYS */;
-- LOCK TABLES;

--
-- Table structure for table `clinic_predefined_appointments`
--

DROP TABLE IF EXISTS `clinic_predefined_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_predefined_appointments` (
  `clinic_id` bigint(20) NOT NULL,
  `predefined_appointments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`clinic_id`,`predefined_appointments_id`),
  UNIQUE KEY `UK_5pu7wehiwrjs7gce7puih5ia` (`predefined_appointments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_predefined_appointments`
--

-- LOCK TABLES `clinic_predefined_appointments` WRITE;
/*!40000 ALTER TABLE `clinic_predefined_appointments` DISABLE KEYS */;
INSERT INTO `clinic_predefined_appointments` VALUES (2,146),(2,147),(2,148),(2,149),(2,150),(2,151),(2,152),(2,153),(2,154),(2,155),(2,156),(2,157),(2,158);
/*!40000 ALTER TABLE `clinic_predefined_appointments` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `clinic_rooms`
--

DROP TABLE IF EXISTS `clinic_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_rooms` (
  `clinic_id` bigint(20) NOT NULL,
  `rooms_id` bigint(20) NOT NULL,
  PRIMARY KEY (`clinic_id`,`rooms_id`),
  UNIQUE KEY `UK_gono3b34u1uffifjunwux87fn` (`rooms_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_rooms`
--

-- LOCK TABLES `clinic_rooms` WRITE;
/*!40000 ALTER TABLE `clinic_rooms` DISABLE KEYS */;
INSERT INTO `clinic_rooms` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6),(2,7),(3,8),(3,9),(3,10),(3,11),(4,12);
/*!40000 ALTER TABLE `clinic_rooms` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `diagnose`
--

DROP TABLE IF EXISTS `diagnose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnose`
--

-- LOCK TABLES `diagnose` WRITE;
/*!40000 ALTER TABLE `diagnose` DISABLE KEYS */;
INSERT INTO `diagnose` VALUES (1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Upala pluca'),(2,'Duis pretium sed sapien at pellentesque. ','Rak slepog creva'),(3,'Fusce pharetra sem nisl, non gravida neque vulputate in. Praesent nec augue odio amet.','Temperatura'),(4,'Vivamus mattis pellentesque augue nec nullam. ','Upala pluca'),(5,'Pellentesque faucibus sapien velit, facilisis consectetur enim egestas et. ','Visok pritisak'),(6,'Morbi venenatis sapien et dolor pellentesque condimentum. Mauris quis eros sem.','Nizak secer'),(7,' Aliquam aliquet tellus nec sapien efficitur commodo. Aenean nibh ante, ornare eu mi eu, tristique commodo lorem.','Glavobolja ');
/*!40000 ALTER TABLE `diagnose` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `rating` double DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

-- LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (NULL,10),(NULL,11),(NULL,12),(NULL,13),(NULL,14),(NULL,15);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `doctor_requested`
--

DROP TABLE IF EXISTS `doctor_requested`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_requested` (
  `doctor_id` bigint(20) NOT NULL,
  `requested_id` bigint(20) NOT NULL,
  PRIMARY KEY (`doctor_id`,`requested_id`),
  UNIQUE KEY `UK_4gqamgg1xvlx6grljpful70nh` (`requested_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_requested`
--

-- LOCK TABLES `doctor_requested` WRITE;
/*!40000 ALTER TABLE `doctor_requested` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_requested` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `doctor_specializations`
--

DROP TABLE IF EXISTS `doctor_specializations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_specializations` (
  `doctor_id` bigint(20) NOT NULL,
  `specializations_id` bigint(20) NOT NULL,
  PRIMARY KEY (`doctor_id`,`specializations_id`),
  KEY `FKq4ejua3c5m75qod9k4q9f1ogx` (`specializations_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_specializations`
--

-- LOCK TABLES `doctor_specializations` WRITE;
/*!40000 ALTER TABLE `doctor_specializations` DISABLE KEYS */;
INSERT INTO `doctor_specializations` VALUES (10,1),(10,3),(10,10),(11,2),(11,6),(11,7),(11,8),(12,1),(12,4),(12,6),(12,8),(13,5),(13,10),(14,1),(14,3),(14,4),(14,8),(15,6);
/*!40000 ALTER TABLE `doctor_specializations` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `drug`
--

DROP TABLE IF EXISTS `drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drug` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `drug_name` varchar(30) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_thxypx4aw71vt5xs81umg51vc` (`drug_name`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug`
--

-- LOCK TABLES `drug` WRITE;
/*!40000 ALTER TABLE `drug` DISABLE KEYS */;
INSERT INTO `drug` VALUES (1,'Lek protiv upalnih tipova bolova','Cefaleksin',240),(2,'Protiv napada panike + dobro ide uz alkohol','Xalol',440),(3,'Protiv jakih bolova','Morfijum',940),(4,'Resava glavobolje i bol grla','Vervex',62);
/*!40000 ALTER TABLE `drug` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

-- LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES (1),(2),(3),(4);
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `medical_record_diagnoses`
--

DROP TABLE IF EXISTS `medical_record_diagnoses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record_diagnoses` (
  `medical_record_id` bigint(20) NOT NULL,
  `diagnoses_id` bigint(20) NOT NULL,
  PRIMARY KEY (`medical_record_id`,`diagnoses_id`),
  UNIQUE KEY `UK_s295xwmgnhjrvolh2cf9buged` (`diagnoses_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_diagnoses`
--

-- LOCK TABLES `medical_record_diagnoses` WRITE;
/*!40000 ALTER TABLE `medical_record_diagnoses` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_record_diagnoses` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `medical_record_drugs_in_user`
--

DROP TABLE IF EXISTS `medical_record_drugs_in_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record_drugs_in_user` (
  `medical_record_id` bigint(20) NOT NULL,
  `drugs_in_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`medical_record_id`,`drugs_in_user_id`),
  UNIQUE KEY `UK_6subqhduelc7qrdnnbs48yig4` (`drugs_in_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_drugs_in_user`
--

-- LOCK TABLES `medical_record_drugs_in_user` WRITE;
/*!40000 ALTER TABLE `medical_record_drugs_in_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_record_drugs_in_user` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `medical_record_recipes`
--

DROP TABLE IF EXISTS `medical_record_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record_recipes` (
  `medical_record_id` bigint(20) NOT NULL,
  `recipes_id` bigint(20) NOT NULL,
  PRIMARY KEY (`medical_record_id`,`recipes_id`),
  UNIQUE KEY `UK_o7viwh1fw7u2qabsfjrvvm8b7` (`recipes_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record_recipes`
--

-- LOCK TABLES `medical_record_recipes` WRITE;
/*!40000 ALTER TABLE `medical_record_recipes` DISABLE KEYS */;
INSERT INTO `medical_record_recipes` VALUES (1,2),(1,3),(1,6),(1,7),(1,14),(1,16),(1,17),(1,22),(1,24),(1,36),(1,41),(2,1),(2,4),(2,5),(2,12),(2,13),(2,19),(2,25),(2,27),(2,31),(2,34),(2,38),(3,8),(3,9),(3,20),(3,21),(3,30),(3,37),(4,10),(4,11),(4,15),(4,18),(4,23),(4,26),(4,28),(4,29),(4,32),(4,33),(4,35),(4,39),(4,40);
/*!40000 ALTER TABLE `medical_record_recipes` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

-- LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
INSERT INTO `nurse` VALUES (16),(17),(18),(19),(20),(21);
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `medical_record_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1w6vp11iqf5ifhw3g5106wjcl` (`medical_record_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

-- LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (2,1),(3,2),(4,3),(5,4);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `patient_future_appointments`
--

DROP TABLE IF EXISTS `patient_future_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_future_appointments` (
  `patient_id` bigint(20) NOT NULL,
  `future_appointments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`patient_id`,`future_appointments_id`),
  UNIQUE KEY `UK_o520gscvglaydr1c0wiilj9lx` (`future_appointments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_future_appointments`
--

-- LOCK TABLES `patient_future_appointments` WRITE;
/*!40000 ALTER TABLE `patient_future_appointments` DISABLE KEYS */;
INSERT INTO `patient_future_appointments` VALUES (2,13),(2,21),(2,27),(2,28),(2,30),(2,37),(2,47),(2,49),(2,56),(2,57),(2,59),(2,63),(2,71),(2,78),(2,79),(2,89),(2,102),(2,103),(2,111),(2,129),(2,130),(2,135),(2,141),(2,144),(3,6),(3,7),(3,8),(3,15),(3,40),(3,60),(3,65),(3,68),(3,87),(3,96),(3,124),(3,125),(3,126),(3,137),(4,4),(4,5),(4,12),(4,22),(4,23),(4,35),(4,53),(4,54),(4,77),(4,94),(4,97),(4,99),(4,105),(4,113),(4,118),(4,128),(4,143),(5,1),(5,10),(5,17),(5,19),(5,24),(5,33),(5,42),(5,70),(5,85),(5,90),(5,119),(5,123);
/*!40000 ALTER TABLE `patient_future_appointments` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `patient_surgeries`
--

DROP TABLE IF EXISTS `patient_surgeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_surgeries` (
  `patient_id` bigint(20) NOT NULL,
  `surgeries_id` bigint(20) NOT NULL,
  PRIMARY KEY (`patient_id`,`surgeries_id`),
  UNIQUE KEY `UK_klm94sj2re1g6ci53oimsgkns` (`surgeries_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_surgeries`
--

-- LOCK TABLES `patient_surgeries` WRITE;
/*!40000 ALTER TABLE `patient_surgeries` DISABLE KEYS */;
INSERT INTO `patient_surgeries` VALUES (2,1),(2,10),(2,16),(2,23),(2,28),(2,30),(2,32),(3,3),(3,14),(3,15),(3,31),(4,19),(4,24),(4,25),(4,26),(4,34),(4,35),(5,2),(5,8),(5,20);
/*!40000 ALTER TABLE `patient_surgeries` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_validate` bit(1) DEFAULT NULL,
  `drug_id` bigint(20) DEFAULT NULL,
  `nurse_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqasmqax1ju2kij4310ig5pjmh` (`drug_id`),
  KEY `FKnc6m2tgrgnn12m512rbcfhbuw` (`nurse_id`),
  KEY `FKiv69vil5pa9qu1f44b3iu2isw` (`patient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

-- LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,_binary '',4,16,3),(2,_binary '',2,16,2),(3,_binary '\0',2,18,2),(4,_binary '',4,21,3),(5,_binary '\0',4,20,3),(6,_binary '',3,17,2),(7,_binary '',2,18,2),(8,_binary '',2,21,4),(9,_binary '',4,17,4),(10,_binary '',2,21,5),(11,_binary '',4,20,5),(12,_binary '\0',2,21,3),(13,_binary '\0',3,21,3),(14,_binary '\0',1,21,2),(15,_binary '',4,20,5),(16,_binary '\0',1,21,2),(17,_binary '\0',3,21,2),(18,_binary '',1,21,5),(19,_binary '\0',4,20,3),(20,_binary '',1,18,4),(21,_binary '',4,20,4),(22,_binary '\0',3,21,2),(23,_binary '\0',4,18,5),(24,_binary '',3,18,2),(25,_binary '\0',4,21,3),(26,_binary '',1,17,5),(27,_binary '',3,19,3),(28,_binary '\0',1,19,5),(29,_binary '\0',4,21,5),(30,_binary '\0',1,21,4),(31,_binary '',2,20,3),(32,_binary '\0',2,16,5),(33,_binary '',1,20,5),(34,_binary '\0',4,19,3),(35,_binary '',4,18,5),(36,_binary '',1,21,2),(37,_binary '',3,20,4),(38,_binary '\0',3,21,3),(39,_binary '',3,20,5),(40,_binary '',2,17,5),(41,_binary '\0',2,NULL,NULL);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

-- LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Soba za preglede 2',1),(2,'Soba za preglede 1',1),(3,'Operaciona sala',0),(4,'Glavna operaciona sala',0),(5,'Soba za preglede 1',1),(6,'Pomocna operaciona sala',0),(7,'Soba za preglede 2',1),(8,'Soba za preglede 2',1),(9,'Sala za operacije',0),(10,'Soba za preglede 3',1),(11,'Soba za preglede 1',1),(12,'Jedina soba...',1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `room_future_appointments`
--

DROP TABLE IF EXISTS `room_future_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_future_appointments` (
  `room_id` bigint(20) NOT NULL,
  `future_appointments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`,`future_appointments_id`),
  UNIQUE KEY `UK_h26yrm12gp435cr738oiyrnag` (`future_appointments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_future_appointments`
--

-- LOCK TABLES `room_future_appointments` WRITE;
/*!40000 ALTER TABLE `room_future_appointments` DISABLE KEYS */;
INSERT INTO `room_future_appointments` VALUES (1,57),(1,63),(1,78),(1,79),(1,87),(1,94),(1,99),(2,59),(2,60),(2,65),(2,68),(2,71),(2,97),(3,54),(3,56),(3,70),(3,77),(3,85),(3,89),(3,90),(3,96),(4,8),(4,13),(4,17),(4,19),(4,30),(4,33),(4,35),(4,37),(4,47),(5,12),(5,15),(5,24),(5,31),(5,41),(5,43),(5,49),(5,52),(6,1),(6,7),(6,10),(6,22),(6,28),(6,42),(6,53),(7,4),(7,5),(7,6),(7,21),(7,23),(7,27),(7,38),(7,40),(7,44),(8,111),(8,113),(9,103),(10,102),(10,105),(11,118),(12,119),(12,123),(12,124),(12,125),(12,126),(12,128),(12,129),(12,130),(12,135),(12,137),(12,141),(12,143),(12,144);
/*!40000 ALTER TABLE `room_future_appointments` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `room_future_surgeries`
--

DROP TABLE IF EXISTS `room_future_surgeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_future_surgeries` (
  `room_id` bigint(20) NOT NULL,
  `future_surgeries_id` bigint(20) NOT NULL,
  PRIMARY KEY (`room_id`,`future_surgeries_id`),
  UNIQUE KEY `UK_s5v4smifn6va4lpvr3xar8mwa` (`future_surgeries_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_future_surgeries`
--

-- LOCK TABLES `room_future_surgeries` WRITE;
/*!40000 ALTER TABLE `room_future_surgeries` DISABLE KEYS */;
INSERT INTO `room_future_surgeries` VALUES (3,19),(3,20),(3,23),(3,24),(3,25),(3,26),(3,28),(4,1),(4,2),(4,8),(4,15),(4,16),(6,3),(6,10),(6,14),(9,30),(9,31),(9,32),(9,34),(9,35);
/*!40000 ALTER TABLE `room_future_surgeries` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `clinic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjm78dricymr3dbmrnbc4utr4q` (`clinic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

-- LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (10,2),(11,2),(12,1),(13,1),(14,3),(15,4),(16,1),(17,1),(18,2),(19,2),(20,3),(21,4);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `staff_appointments`
--

DROP TABLE IF EXISTS `staff_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_appointments` (
  `staff_id` bigint(20) NOT NULL,
  `appointments_id` bigint(20) NOT NULL,
  PRIMARY KEY (`staff_id`,`appointments_id`),
  KEY `FKcu2s1mh4x7hjrg5d4a84qbyii` (`appointments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_appointments`
--

-- LOCK TABLES `staff_appointments` WRITE;
/*!40000 ALTER TABLE `staff_appointments` DISABLE KEYS */;
INSERT INTO `staff_appointments` VALUES (10,1),(10,4),(10,5),(10,6),(10,7),(10,8),(10,10),(10,12),(10,13),(10,15),(10,17),(10,19),(10,21),(10,22),(10,23),(10,24),(10,27),(10,28),(11,30),(11,31),(11,33),(11,34),(11,35),(11,36),(11,37),(11,38),(11,39),(11,40),(11,41),(11,42),(11,43),(11,44),(11,47),(11,49),(11,52),(11,53),(12,54),(12,56),(12,57),(12,59),(12,60),(12,63),(12,65),(12,68),(12,70),(12,71),(12,77),(12,78),(13,79),(13,84),(13,85),(13,87),(13,89),(13,90),(13,94),(13,96),(13,97),(13,99),(13,100),(14,102),(14,103),(14,105),(14,109),(14,110),(14,111),(14,113),(14,114),(14,118),(15,119),(15,123),(15,124),(15,125),(15,126),(15,128),(15,129),(15,130),(15,132),(15,135),(15,137),(15,141),(15,143),(15,144),(16,54),(16,56),(16,57),(16,59),(16,60),(16,65),(16,70),(16,77),(16,78),(16,85),(16,87),(16,90),(16,94),(16,96),(16,99),(17,63),(17,68),(17,71),(17,79),(17,89),(17,97),(18,1),(18,6),(18,8),(18,10),(18,12),(18,17),(18,19),(18,22),(18,23),(18,28),(18,30),(18,33),(18,40),(18,42),(18,53),(19,4),(19,5),(19,7),(19,13),(19,15),(19,21),(19,24),(19,27),(19,35),(19,37),(19,47),(19,49),(20,102),(20,103),(20,105),(20,111),(20,113),(20,118),(21,119),(21,123),(21,124),(21,125),(21,126),(21,128),(21,129),(21,130),(21,135),(21,137),(21,141),(21,143),(21,144);
/*!40000 ALTER TABLE `staff_appointments` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `staff_surgeries`
--

DROP TABLE IF EXISTS `staff_surgeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_surgeries` (
  `staff_id` bigint(20) NOT NULL,
  `surgeries_id` bigint(20) NOT NULL,
  PRIMARY KEY (`staff_id`,`surgeries_id`),
  KEY `FKcmivph4i01i9jaruuf9ejupma` (`surgeries_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_surgeries`
--

-- LOCK TABLES `staff_surgeries` WRITE;
/*!40000 ALTER TABLE `staff_surgeries` DISABLE KEYS */;
INSERT INTO `staff_surgeries` VALUES (10,1),(10,2),(10,3),(11,8),(11,10),(11,14),(11,15),(11,16),(12,19),(12,20),(12,23),(12,24),(13,25),(13,26),(13,28),(14,30),(14,31),(14,32),(14,34),(14,35),(16,19),(16,20),(16,23),(16,25),(16,28),(17,24),(17,26),(18,1),(18,3),(18,8),(19,2),(19,10),(19,14),(19,15),(19,16),(20,30),(20,31),(20,32),(20,34),(20,35);
/*!40000 ALTER TABLE `staff_surgeries` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `staff_vac_req`
--

DROP TABLE IF EXISTS `staff_vac_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_vac_req` (
  `staff_id` bigint(20) NOT NULL,
  `vac_req_id` bigint(20) NOT NULL,
  PRIMARY KEY (`staff_id`,`vac_req_id`),
  UNIQUE KEY `UK_goy085t10ltsbsyn49pp639v7` (`vac_req_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_vac_req`
--

-- LOCK TABLES `staff_vac_req` WRITE;
/*!40000 ALTER TABLE `staff_vac_req` DISABLE KEYS */;
INSERT INTO `staff_vac_req` VALUES (10,1),(10,2),(11,3),(11,4),(12,5),(13,7),(13,8),(14,9);
/*!40000 ALTER TABLE `staff_vac_req` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `surgery`
--

DROP TABLE IF EXISTS `surgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surgery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved` bit(1) DEFAULT NULL,
  `end_time` datetime NOT NULL,
  `start_time` datetime NOT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpy9c65dspgsl4te2roe55t1ox` (`room_id`),
  KEY `FKiku3j2kyon5dd4fuxbvh86y44` (`patient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surgery`
--

-- LOCK TABLES `surgery` WRITE;
/*!40000 ALTER TABLE `surgery` DISABLE KEYS */;
INSERT INTO `surgery` VALUES (1,_binary '','2020-02-02 13:00:00','2020-02-02 12:30:00',4,2),(2,_binary '','2020-02-11 14:00:00','2020-02-11 13:30:00',4,5),(3,_binary '','2020-03-12 10:00:00','2020-03-12 09:30:00',6,3),(4,_binary '\0','2020-03-05 11:30:00','2020-03-05 11:00:00',NULL,4),(5,_binary '\0','2020-03-20 09:30:00','2020-03-20 09:00:00',NULL,2),(6,_binary '\0','2020-03-08 08:30:00','2020-03-08 08:00:00',NULL,2),(7,_binary '\0','2020-02-12 14:00:00','2020-02-12 13:30:00',NULL,2),(8,_binary '','2020-03-06 07:30:00','2020-03-06 07:00:00',4,5),(9,_binary '\0','2020-02-16 08:30:00','2020-02-16 08:00:00',NULL,2),(10,_binary '','2020-03-07 12:30:00','2020-03-07 12:00:00',6,2),(11,_binary '\0','2020-02-08 08:00:00','2020-02-08 07:30:00',NULL,5),(12,_binary '\0','2020-02-22 10:30:00','2020-02-22 10:00:00',NULL,2),(13,_binary '\0','2020-02-25 12:30:00','2020-02-25 12:00:00',NULL,5),(14,_binary '','2020-03-22 10:00:00','2020-03-22 09:30:00',6,3),(15,_binary '','2020-02-04 12:00:00','2020-02-04 11:30:00',4,3),(16,_binary '','2020-02-15 12:00:00','2020-02-15 11:30:00',4,2),(17,_binary '\0','2020-03-28 09:00:00','2020-03-28 08:30:00',NULL,3),(18,_binary '\0','2020-02-04 13:30:00','2020-02-04 13:00:00',NULL,2),(19,_binary '','2020-03-04 10:30:00','2020-03-04 10:00:00',3,4),(20,_binary '','2020-02-23 11:00:00','2020-02-23 10:30:00',3,5),(21,_binary '\0','2020-03-19 08:30:00','2020-03-19 08:00:00',NULL,3),(22,_binary '\0','2020-02-13 11:00:00','2020-02-13 10:30:00',NULL,5),(23,_binary '','2020-03-17 12:00:00','2020-03-17 11:30:00',3,2),(24,_binary '','2020-03-07 11:30:00','2020-03-07 11:00:00',3,4),(25,_binary '','2020-03-20 10:00:00','2020-03-20 09:30:00',3,4),(26,_binary '','2020-02-12 08:30:00','2020-02-12 08:00:00',3,4),(27,_binary '\0','2020-02-22 10:00:00','2020-02-22 09:30:00',NULL,5),(28,_binary '','2020-03-22 10:30:00','2020-03-22 10:00:00',3,2),(29,_binary '\0','2020-02-28 07:30:00','2020-02-28 07:00:00',NULL,2),(30,_binary '','2020-03-27 12:00:00','2020-03-27 11:30:00',9,2),(31,_binary '','2020-03-06 08:30:00','2020-03-06 08:00:00',9,3),(32,_binary '','2020-03-27 11:00:00','2020-03-27 10:30:00',9,2),(33,_binary '\0','2020-03-14 10:30:00','2020-03-14 10:00:00',NULL,5),(34,_binary '','2020-03-02 09:30:00','2020-03-02 09:00:00',9,4),(35,_binary '','2020-02-17 10:30:00','2020-02-17 10:00:00',9,4);
/*!40000 ALTER TABLE `surgery` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `surgery_doctors`
--

DROP TABLE IF EXISTS `surgery_doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surgery_doctors` (
  `surgery_id` bigint(20) NOT NULL,
  `doctors_id` bigint(20) NOT NULL,
  PRIMARY KEY (`surgery_id`,`doctors_id`),
  KEY `FK7bv80yww5kxbnetxfwl447tam` (`doctors_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surgery_doctors`
--

-- LOCK TABLES `surgery_doctors` WRITE;
/*!40000 ALTER TABLE `surgery_doctors` DISABLE KEYS */;
INSERT INTO `surgery_doctors` VALUES (1,10),(2,10),(3,10),(4,10),(5,10),(6,11),(7,11),(8,11),(9,11),(10,11),(11,11),(12,11),(13,11),(14,11),(15,11),(16,11),(17,11),(18,11),(19,12),(20,12),(21,12),(22,12),(23,12),(24,12),(25,13),(26,13),(27,13),(28,13),(29,13),(30,14),(31,14),(32,14),(33,14),(34,14),(35,14);
/*!40000 ALTER TABLE `surgery_doctors` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `admin_confirmed` bit(1) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_login` bit(1) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `ssn` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

-- LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','1 Holy Cross Center',_binary '','Barajevo','Serbia','kmalia8@phoca.cz',_binary '','Kial','Malia','663354','(407) 1040666',0,'6199927252131'),(2,_binary '','88529 Fairfield Parkway',_binary '','Budapest','Hungary','grubinow8@php.net',_binary '','Gretna','Rubinow','117565','(682) 9443368',2,'9598205971511'),(3,_binary '\0','88529 Fairfield Parkway',_binary '\0','Subotica','Serbia','drugipacijent@php.net',_binary '','Boris','Brejcha','78596789','(682) 9425368',2,'9598795971511'),(4,_binary '\0','88529 Fairfield Parkway',_binary '\0','Szeged','Hungary','instapatient@java.net',_binary '','Senidah','The Third','120565','(682) 9449668',2,'9598205101511'),(5,_binary '\0','88529 Fairfield Parkway',_binary '\0','Pecs','Hungary','finalpatient@test.co',_binary '','Yo','Yo','007565','(682) 9489668',2,'9598205970311'),(6,_binary '','6 Brentwood Way',_binary '','Savski Venac','Serbia','wdeekes5@tmall.com',_binary '','Whittaker','Deekes','318914','(141) 9739447',1,'67607378738'),(7,_binary '','277 Quincy Lane',_binary '','Radojevo','Serbia','maxi.maksimovic7@gmail.com',_binary '','Gustavus','Prophet','519930','(691) 5982347',1,'4498498325468'),(8,_binary '','29 Melvin Alley',_binary '','Radenka','Serbia','aashton7@acquirethisname.com',_binary '','Adrianne','Ashton','893866','(263) 5567560',1,'5057737453126'),(9,_binary '','610 Forster Point',_binary '','Kovilj','Serbia','avanrembrandt8@ezinearticles.com',_binary '','Ashley','Van Rembrandt','651346','(929) 7283860',1,'9967156809229'),(10,_binary '','810 Cherokee Lane',_binary '','Kuštilj','Srbija','jpavlovic0@google.com.au',_binary '','Justus','Pavlovic','557369','(682) 9409237',3,'8657870655830'),(11,_binary '','33 7th Crossing',_binary '','Gyor','Madjarska','jmingardo1@howstuffworks.com',_binary '','Judith','Mingardo','638473','(666) 6994851',3,'1715377197364'),(12,_binary '','5 International Place',_binary '','Kamenica','Srbija','nattle4@hhs.gov',_binary '','Nigel','Attle','320532','(466) 6189663',3,'9028453261450'),(13,_binary '','67 Little Fleur Plaza',_binary '','Bogojevo','Srbija','gstuchbery6@cargocollective.com',_binary '','Gabriell','Stuchbery','688918','(376) 9635234',3,'2697619396679'),(14,_binary '','8 Ilene Junction',_binary '','Budapest','Hungary','kbalbeck9@java.com',_binary '','Karyn','Balbeck','695696','(587) 8660314',3,'8518068206648'),(15,_binary '','910 Westend Terrace',_binary '','Potoci','Bosna i Hercegovina','aarne1@fastcompany.com',_binary '','Ariela','Arne','658385','(307) 8351741',3,'6437437353973'),(16,_binary '','1715 Boyd Place',_binary '','Nova Pazova','Serbia','rkitchinghamd@salon.com',_binary '','Robinette','Kitchingham','272851','(687) 2189973',4,'8211024836776'),(17,_binary '','1715 Boyd Place',_binary '','Nova Pazova','Serbia','nije_bitno@mejl.com',_binary '','Matt','Parker','514265','(687) 2189973',4,'8211024836789'),(18,_binary '','1810 Michigan Way',_binary '','Balatun','Bosnia and Herzegovina','dbishopp4@wikispaces.com',_binary '','Delcine','Bishopp','785391','(192) 8186705',4,'6771172485802'),(19,_binary '','1810 Michigan Way',_binary '','Balatun','Bosnia and Herzegovina','testtest@wikispaces.com',_binary '','Brow','Rice','782891','(192) 8186705',4,'6771178985802'),(20,_binary '','48 Hoard Hill',_binary '','Lipci','Montenegro','sscholte5@examiner.com',_binary '','Sydney','Scholte','401257','(286) 9748369',4,'3636790947925'),(21,_binary '','1715 Boyd Place',_binary '','Nova Pazova','Serbia','nurse@nrs.com',_binary '','Robinette','Kitchingham','123123','(687) 2189973',4,'8211024836776');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;

--
-- Table structure for table `vacation_req`
--

DROP TABLE IF EXISTS `vacation_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacation_req` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accepted` bit(1) DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `type` varchar(255) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhud24s6ptev53gpb3flmbueqf` (`staff_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacation_req`
--

-- LOCK TABLES `vacation_req` WRITE;
/*!40000 ALTER TABLE `vacation_req` DISABLE KEYS */;
INSERT INTO `vacation_req` VALUES (1,_binary '','2020-01-25 23:00:00','2020-01-19 23:00:00','ABSENCE',10),(2,_binary '','2020-02-28 23:00:00','2020-02-21 23:00:00','HOLIDAY',10),(3,_binary '','2020-03-27 23:00:00','2020-03-22 23:00:00','HOLIDAY',11),(4,_binary '','2020-01-21 23:00:00','2020-01-17 23:00:00','ABSENCE',11),(5,_binary '','2020-02-15 23:00:00','2020-02-11 23:00:00','ABSENCE',12),(6,NULL,'2020-03-28 23:00:00','2020-03-22 23:00:00','HOLIDAY',12),(7,_binary '','2020-02-12 23:00:00','2020-02-10 23:00:00','HOLIDAY',13),(8,_binary '','2020-01-12 23:00:00','2020-01-05 23:00:00','ABSENCE',13),(9,_binary '','2020-01-24 23:00:00','2020-01-23 23:00:00','ABSENCE',14),(10,_binary '\0','2020-02-26 23:00:00','2020-02-25 23:00:00','HOLIDAY',14),(11,NULL,'2020-03-04 23:00:00','2020-03-02 23:00:00','HOLIDAY',15),(12,_binary '\0','2020-03-13 23:00:00','2020-03-10 23:00:00','ABSENCE',15);
/*!40000 ALTER TABLE `vacation_req` ENABLE KEYS */;
-- UNLOCK LOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-07 14:57:08
