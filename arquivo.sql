-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: db_ecommerce
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `adress`
--

DROP TABLE IF EXISTS `adress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adress` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `city_id` int DEFAULT NULL,
  `costumer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1wxre6ktmrkjaa9qqy3y9m18o` (`city_id`),
  KEY `FKdfjkqhd99qogdouym6sjgnnec` (`costumer_id`),
  CONSTRAINT `FK1wxre6ktmrkjaa9qqy3y9m18o` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKdfjkqhd99qogdouym6sjgnnec` FOREIGN KEY (`costumer_id`) REFERENCES `costumer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adress`
--

LOCK TABLES `adress` WRITE;
/*!40000 ALTER TABLE `adress` DISABLE KEYS */;
INSERT INTO `adress` VALUES (1,'Rua Josefina Bakhita','Casa 1','Vila Sonia','527','11722330',1,1),(2,'Avenida Paulista','AP 22','Jardim Paulista','134','13574391',2,1);
/*!40000 ALTER TABLE `adress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billet_payment`
--

DROP TABLE IF EXISTS `billet_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billet_payment` (
  `date_expirate` datetime(6) DEFAULT NULL,
  `date_payment` datetime(6) DEFAULT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `FKb1v5sbpd1u4g20cpserlfgk12` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billet_payment`
--

LOCK TABLES `billet_payment` WRITE;
/*!40000 ALTER TABLE `billet_payment` DISABLE KEYS */;
INSERT INTO `billet_payment` VALUES ('2022-03-20 00:00:00.000000',NULL,2);
/*!40000 ALTER TABLE `billet_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_payment`
--

DROP TABLE IF EXISTS `card_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_payment` (
  `number_installments` int DEFAULT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `FKq0ke1a0u90mpcdle77qe5cojt` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_payment`
--

LOCK TABLES `card_payment` WRITE;
/*!40000 ALTER TABLE `card_payment` DISABLE KEYS */;
INSERT INTO `card_payment` VALUES (6,1);
/*!40000 ALTER TABLE `card_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'Computers'),(2,'Books'),(3,'Consoles'),(4,'Office'),(5,'Home & Health'),(6,'Garden'),(7,'Games');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `estate_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4e0x4fd0rcs3j9tf0yqjeh6ka` (`estate_id`),
  CONSTRAINT `FK4e0x4fd0rcs3j9tf0yqjeh6ka` FOREIGN KEY (`estate_id`) REFERENCES `estate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Praia Grande',1),(2,'São Paulo',1),(3,'Rio de Janeiro',2),(4,'Porto Alegre',3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costumer`
--

DROP TABLE IF EXISTS `costumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `costumer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf_or_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_21twn7bxgsmisulawd9jntr7c` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costumer`
--

LOCK TABLES `costumer` WRITE;
/*!40000 ALTER TABLE `costumer` DISABLE KEYS */;
INSERT INTO `costumer` VALUES (1,'451.253.220-08','luanreis2202@gmail.com','Luan',1);
/*!40000 ALTER TABLE `costumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estate`
--

DROP TABLE IF EXISTS `estate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estate`
--

LOCK TABLES `estate` WRITE;
/*!40000 ALTER TABLE `estate` DISABLE KEYS */;
INSERT INTO `estate` VALUES (1,'São Paulo'),(2,'Rio De Janeiro'),(3,'Rio Grande do Sul');
/*!40000 ALTER TABLE `estate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_ordered`
--

DROP TABLE IF EXISTS `item_ordered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_ordered` (
  `amount` int DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FKjeccc4yadljqa9tnbmkqu4om7` (`product_id`),
  CONSTRAINT `FK6dp0taimp4t3gji604swxrb7r` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKjeccc4yadljqa9tnbmkqu4om7` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_ordered`
--

LOCK TABLES `item_ordered` WRITE;
/*!40000 ALTER TABLE `item_ordered` DISABLE KEYS */;
INSERT INTO `item_ordered` VALUES (1,0,300,1,1),(2,0,80,1,3),(1,0,139.99,2,2);
/*!40000 ALTER TABLE `item_ordered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `costumer_id` int DEFAULT NULL,
  `adress_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcg199q9nfg3ukprbc1rt75t7` (`costumer_id`),
  KEY `FKl6bd6xw7clps8juq55pjqcci4` (`adress_id`),
  CONSTRAINT `FKgcg199q9nfg3ukprbc1rt75t7` FOREIGN KEY (`costumer_id`) REFERENCES `costumer` (`id`),
  CONSTRAINT `FKl6bd6xw7clps8juq55pjqcci4` FOREIGN KEY (`adress_id`) REFERENCES `adress` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2022-03-30 12:30:00.000000',1,1),(2,'2022-04-20 11:15:00.000000',1,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `order_id` int NOT NULL,
  `state_payment` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `FKlouu98csyullos9k25tbpk4va` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2),(2,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_billet`
--

DROP TABLE IF EXISTS `payment_billet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_billet` (
  `date_expirate` datetime DEFAULT NULL,
  `date_payment` datetime DEFAULT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_billet`
--

LOCK TABLES `payment_billet` WRITE;
/*!40000 ALTER TABLE `payment_billet` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_billet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_card`
--

DROP TABLE IF EXISTS `payment_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_card` (
  `number_installments` int DEFAULT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_card`
--

LOCK TABLES `payment_card` WRITE;
/*!40000 ALTER TABLE `payment_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Monitor 24p',300),(2,'Mouse Gamer',139.99),(3,'The Agile Manifesto',80),(4,'Red Dead Redemption 2  ',109.99),(5,'Sekiro: Shadows Die Twice',115),(6,'Office Desk Chair Ergonomic',130),(7,'Dining Table Mats',15),(8,'Mousepad 120cmX60cm',60),(9,'Green Hammock',85),(10,'Rainbow Hammock',104.99),(11,'EA33 Printer/Scanner',224.99);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categorie`
--

DROP TABLE IF EXISTS `product_categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categorie` (
  `product_id` int NOT NULL,
  `categorie_id` int NOT NULL,
  KEY `FKt8fkspem09bha3xy1s5fq4o2a` (`categorie_id`),
  KEY `FKrtvofiupi74wkk5epvtb6j446` (`product_id`),
  CONSTRAINT `FKrtvofiupi74wkk5epvtb6j446` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt8fkspem09bha3xy1s5fq4o2a` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categorie`
--

LOCK TABLES `product_categorie` WRITE;
/*!40000 ALTER TABLE `product_categorie` DISABLE KEYS */;
INSERT INTO `product_categorie` VALUES (1,1),(1,3),(2,1),(2,3),(3,2),(4,7),(5,7),(6,4),(7,5),(8,1),(8,4),(9,5),(9,6),(10,5),(10,6),(11,4),(11,1);
/*!40000 ALTER TABLE `product_categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telephone` (
  `costumer_id` int NOT NULL,
  `telephones` varchar(255) DEFAULT NULL,
  KEY `FKn9uai746jv5gy59xdfhj6ta7l` (`costumer_id`),
  CONSTRAINT `FKn9uai746jv5gy59xdfhj6ta7l` FOREIGN KEY (`costumer_id`) REFERENCES `costumer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telephone`
--

LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
INSERT INTO `telephone` VALUES (1,'13 996735588'),(1,'13 997564216');
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-17 12:15:10
