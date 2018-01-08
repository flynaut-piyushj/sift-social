-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sift_db
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION_ID` varchar(256) DEFAULT NULL,
  `USER_NAME` varchar(256) DEFAULT NULL,
  `CLIENT_ID` varchar(256) DEFAULT NULL,
  `AUTHENTICATION` blob,
  `REFRESH_TOKEN` varchar(256) DEFAULT NULL,
  `CREATED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` (`TOKEN_ID`, `TOKEN`, `AUTHENTICATION_ID`, `USER_NAME`, `CLIENT_ID`, `AUTHENTICATION`, `REFRESH_TOKEN`, `CREATED_TS`, `MODIFIED_TS`) VALUES ('eace0217a7d8d4959338f00016df9364','¨\Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙\Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6ÖZ\‹\Á\–\0\0xpsr\0java.util.DatehjÅKYt\0\0xpw\0\0^Ö\—5Nxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valueq\0~\0xpt\0$d39fe407-f921-4535-aca4-1c2b62df650asq\0~\0	w\0\0_<,xsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearert\0$0db68ba7-d0b0-41e2-9b72-8fd388ebda55','0a843775195bd29c2c337ea9e29d787c','rahul@gmail.com','sift','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0siftsr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0rahul@gmail.comxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0 w\0\0\0?@\0\0\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0ö\0L\0roleq\0~\0xpt\0	ROLE_USERsq\0~\0%t\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0 w\0\0\0?@\0\0\0\0\0\0xsq\0~\0 w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0ö\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0\0w\0\0\0\0xq\0~\00sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0\Zx\0psr\0\"com.sift.apis.beans.UserLoginDtlTo˜\Õ{\⁄˙ùYå\0B\0isActiveB\0isEmailVerifiedB\0isPasswordChangedB\0\nisTncAccptL\0\naccessCodeq\0~\0L\0	createdByq\0~\0L\0	createdTsq\0~\0L\0emailIdq\0~\0L\0forgotPassCodeq\0~\0L\0grantedAuthoritiesq\0~\0L\0\nmodifiedByq\0~\0L\0\nmodifiedTsq\0~\0L\0passwordq\0~\0L\0\ntncAccptTsq\0~\0L\0userLoginDtlsIdq\0~\0L\0userMasterDtlsIdq\0~\0xpt\0\0pt\02017-07-20 17:11:57.0t\0rahul@gmail.comt\0$6fc0b6de-6e14-11e7-a20e-f0761c6b2f6dsq\0~\0\0\0\0\0w\0\0\0\0xpt\02017-09-15 14:16:11.0t\0P929cd9ee9d8d509501b918950d34f46365c4b01edbb7784f7ba96f32e707fa4f9e75f7c1f25375abt\02017-09-15 14:16:11.0t\0rahult\0rahul','298bec0975583ac9abc2c1660944ffd2','2017-09-15 09:41:14','2017-09-15 09:41:14');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION` blob,
  `CREATED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` (`TOKEN_ID`, `TOKEN`, `AUTHENTICATION`, `CREATED_TS`, `MODIFIED_TS`) VALUES ('298bec0975583ac9abc2c1660944ffd2','¨\Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valuet\0Ljava/lang/String;xpt\0$d39fe407-f921-4535-aca4-1c2b62df650asr\0java.util.DatehjÅKYt\0\0xpw\0\0_<,x','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0siftsr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0rahul@gmail.comxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0 w\0\0\0?@\0\0\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0ö\0L\0roleq\0~\0xpt\0	ROLE_USERsq\0~\0%t\0\nROLE_ADMINxsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0 w\0\0\0?@\0\0\0\0\0\0xsq\0~\0 w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0ö\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0\0w\0\0\0\0xq\0~\00sr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxq\0~\0?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0\Zx\0psr\0\"com.sift.apis.beans.UserLoginDtlTo˜\Õ{\⁄˙ùYå\0B\0isActiveB\0isEmailVerifiedB\0isPasswordChangedB\0\nisTncAccptL\0\naccessCodeq\0~\0L\0	createdByq\0~\0L\0	createdTsq\0~\0L\0emailIdq\0~\0L\0forgotPassCodeq\0~\0L\0grantedAuthoritiesq\0~\0L\0\nmodifiedByq\0~\0L\0\nmodifiedTsq\0~\0L\0passwordq\0~\0L\0\ntncAccptTsq\0~\0L\0userLoginDtlsIdq\0~\0L\0userMasterDtlsIdq\0~\0xpt\0\0pt\02017-07-20 17:11:57.0t\0rahul@gmail.comt\0$6fc0b6de-6e14-11e7-a20e-f0761c6b2f6dsq\0~\0\0\0\0\0w\0\0\0\0xpt\02017-09-15 14:16:11.0t\0P929cd9ee9d8d509501b918950d34f46365c4b01edbb7784f7ba96f32e707fa4f9e75f7c1f25375abt\02017-09-15 14:16:11.0t\0rahult\0rahul','2017-09-15 08:52:28','2017-09-15 08:52:28');
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp_dtls`
--

DROP TABLE IF EXISTS `otp_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otp_dtls` (
  `OTP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CELLNUMBER` varchar(15) DEFAULT NULL,
  `DEVICE_INFO` varchar(30) DEFAULT NULL,
  `NUM_OF_ATTEMPTS` int(11) DEFAULT NULL,
  `OTP` int(11) DEFAULT NULL,
  `CREATED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`OTP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp_dtls`
--

LOCK TABLES `otp_dtls` WRITE;
/*!40000 ALTER TABLE `otp_dtls` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_dtls`
--

DROP TABLE IF EXISTS `payment_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_dtls` (
  `PAYMENT_DTLS_ID` char(36) NOT NULL,
  `TXN_REF_NO` varchar(100) NOT NULL,
  `PAYMENT_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PAYMENT_MODE` varchar(80) NOT NULL,
  `PAYMENT_STATUS` varchar(45) NOT NULL,
  `PAYMENT_GATEWAY` varchar(45) DEFAULT NULL,
  `BANK_REF_NUM` varchar(45) DEFAULT NULL,
  `TXN_DATA` varchar(1000) DEFAULT NULL,
  `PAYMENT_AMOUNT` float NOT NULL,
  `CREATED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PAYMENT_DTLS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_dtls`
--

LOCK TABLES `payment_dtls` WRITE;
/*!40000 ALTER TABLE `payment_dtls` DISABLE KEYS */;
INSERT INTO `payment_dtls` (`PAYMENT_DTLS_ID`, `TXN_REF_NO`, `PAYMENT_TS`, `PAYMENT_MODE`, `PAYMENT_STATUS`, `PAYMENT_GATEWAY`, `BANK_REF_NUM`, `TXN_DATA`, `PAYMENT_AMOUNT`, `CREATED_TS`, `MODIFIED_TS`) VALUES ('1455','fdff','2017-07-18 10:55:01','dffsf','1','dxfdsf','dfs','dfsfd',14564,'2017-07-18 11:23:52','2017-07-18 11:23:52');
/*!40000 ALTER TABLE `payment_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_master_dtls`
--

DROP TABLE IF EXISTS `role_master_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_master_dtls` (
  `ROLES_MASTER_DTLS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(45) DEFAULT NULL,
  `ROLE_DESCRIPTION` varchar(100) DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) DEFAULT NULL,
  `CREATED_TS` timestamp NULL DEFAULT NULL,
  `MODIFIED_TS` timestamp NULL DEFAULT NULL,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`ROLES_MASTER_DTLS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_master_dtls`
--

LOCK TABLES `role_master_dtls` WRITE;
/*!40000 ALTER TABLE `role_master_dtls` DISABLE KEYS */;
INSERT INTO `role_master_dtls` (`ROLES_MASTER_DTLS_ID`, `ROLE_NAME`, `ROLE_DESCRIPTION`, `IS_ACTIVE`, `CREATED_TS`, `MODIFIED_TS`, `CREATED_BY`, `MODIFIED_BY`) VALUES (1,'ROLE_ADMIN','admin',1,NULL,NULL,NULL,NULL),(2,'ROLE_USER','user',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role_master_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_dtls`
--

DROP TABLE IF EXISTS `state_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state_dtls` (
  `STATE_DTLS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY_DTLS_ID` int(11) DEFAULT NULL,
  `STATE_NM` varchar(50) DEFAULT NULL,
  `STATE_DESC` varchar(300) DEFAULT NULL,
  `STATE_CODE` varchar(5) DEFAULT NULL,
  `LONGITUDE` decimal(10,7) DEFAULT NULL,
  `LATITUDE` decimal(10,7) DEFAULT NULL,
  `IS_ACTIVE` enum('Y','N') DEFAULT NULL,
  `CREATED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`STATE_DTLS_ID`),
  KEY `COUNTRY_DTLS_ID_idx` (`COUNTRY_DTLS_ID`),
  CONSTRAINT `COUNTRY_DTLS_ID` FOREIGN KEY (`COUNTRY_DTLS_ID`) REFERENCES `country_dtls` (`COUNTRY_DTLS_ID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `state_dtls_ibfk_1` FOREIGN KEY (`COUNTRY_DTLS_ID`) REFERENCES `country_dtls` (`COUNTRY_DTLS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_dtls`
--

LOCK TABLES `state_dtls` WRITE;
/*!40000 ALTER TABLE `state_dtls` DISABLE KEYS */;
INSERT INTO `state_dtls` (`STATE_DTLS_ID`, `COUNTRY_DTLS_ID`, `STATE_NM`, `STATE_DESC`, `STATE_CODE`, `LONGITUDE`, `LATITUDE`, `IS_ACTIVE`, `CREATED_TS`, `MODIFIED_TS`, `CREATED_BY`, `MODIFIED_BY`) VALUES (1,1,'Maharastra',NULL,NULL,NULL,NULL,NULL,'2017-01-05 13:49:20','2017-07-24 06:57:47',NULL,NULL),(2,1,'Karnataka',NULL,NULL,NULL,NULL,NULL,'2017-01-05 13:49:20','2017-07-24 06:57:47',NULL,NULL),(3,1,'Kerala',NULL,NULL,NULL,NULL,NULL,'2017-01-05 13:49:21','2017-01-05 13:49:21',NULL,NULL),(4,1,'Punjab',NULL,NULL,NULL,NULL,NULL,'2017-01-05 13:49:21','2017-01-05 13:49:21',NULL,NULL);
/*!40000 ALTER TABLE `state_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_dtls`
--

DROP TABLE IF EXISTS `user_login_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login_dtls` (
  `USER_LOGIN_DTLS_ID` char(36) NOT NULL,
  `USER_MASTER_DTLS_ID` char(36) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `EMAIL_ID` varchar(100) NOT NULL,
  `IS_PASSWORD_CHANGED` tinyint(1) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `IS_TNC_ACCPT` tinyint(1) NOT NULL,
  `TNC_ACCPT_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `IS_EMAIL_VERIFIED` tinyint(1) NOT NULL,
  `ACCESS_CODE` varchar(50) DEFAULT NULL,
  `FORGOT_PASS_CODE` varchar(50) DEFAULT NULL,
  `CREATED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`USER_LOGIN_DTLS_ID`),
  KEY `FK_UMDI_ULD_UMD_idx` (`USER_MASTER_DTLS_ID`),
  CONSTRAINT `FK_UMDI_ULD_UMD` FOREIGN KEY (`USER_MASTER_DTLS_ID`) REFERENCES `user_master_dtls` (`USER_MASTER_DTLS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_dtls`
--

LOCK TABLES `user_login_dtls` WRITE;
/*!40000 ALTER TABLE `user_login_dtls` DISABLE KEYS */;
INSERT INTO `user_login_dtls` (`USER_LOGIN_DTLS_ID`, `USER_MASTER_DTLS_ID`, `PASSWORD`, `EMAIL_ID`, `IS_PASSWORD_CHANGED`, `IS_ACTIVE`, `IS_TNC_ACCPT`, `TNC_ACCPT_TS`, `IS_EMAIL_VERIFIED`, `ACCESS_CODE`, `FORGOT_PASS_CODE`, `CREATED_TS`, `MODIFIED_TS`, `CREATED_BY`, `MODIFIED_BY`) VALUES ('rahul','rahul','929cd9ee9d8d509501b918950d34f46365c4b01edbb7784f7ba96f32e707fa4f9e75f7c1f25375ab','rahul@gmail.com',1,1,1,'2017-09-15 08:46:11',1,'','6fc0b6de-6e14-11e7-a20e-f0761c6b2f6d','2017-07-20 11:41:57','2017-09-15 08:46:11',NULL,NULL);
/*!40000 ALTER TABLE `user_login_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master_dtls`
--

DROP TABLE IF EXISTS `user_master_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_master_dtls` (
  `USER_MASTER_DTLS_ID` char(36) NOT NULL,
  `FIRST_NAME` varchar(15) DEFAULT NULL,
  `LAST_NAME` varchar(15) DEFAULT NULL,
  `CONTACT_NO` varchar(12) DEFAULT NULL,
  `EMAIL_ID` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `CONFIRM_PASSWORD` varchar(45) DEFAULT NULL,
  `GENDER` varchar(45) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `USER_PROFILE_PHOTO` varchar(150) DEFAULT NULL,
  `ANDROID_REG_ID` varchar(150) DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `CREATED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`USER_MASTER_DTLS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master_dtls`
--

LOCK TABLES `user_master_dtls` WRITE;
/*!40000 ALTER TABLE `user_master_dtls` DISABLE KEYS */;
INSERT INTO `user_master_dtls` (`USER_MASTER_DTLS_ID`, `FIRST_NAME`, `LAST_NAME`, `CONTACT_NO`, `EMAIL_ID`, `PASSWORD`, `CONFIRM_PASSWORD`, `GENDER`, `DATE_OF_BIRTH`, `USER_PROFILE_PHOTO`, `ANDROID_REG_ID`, `IS_ACTIVE`, `CREATED_TS`, `MODIFIED_TS`, `CREATED_BY`, `MODIFIED_BY`) VALUES ('209d4024-99fb-11e7-ade7-f0def1a3fdae','Asmita','Nikam','7412589630','asmitanikam30@gmail.com','asmita',NULL,'female','1993-03-16','sadsdasdsa',NULL,1,'2017-09-15 09:49:12','2017-09-15 10:35:04',NULL,NULL);
/*!40000 ALTER TABLE `user_master_dtls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_dtls`
--

DROP TABLE IF EXISTS `user_role_dtls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_dtls` (
  `USER_ROLE_DTLS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_LOGIN_DTLS_ID` char(36) NOT NULL,
  `ROLES_MASTER_DTLS_ID` int(11) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `CREATED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_TS` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` char(36) DEFAULT NULL,
  `MODIFIED_BY` char(36) DEFAULT NULL,
  PRIMARY KEY (`USER_ROLE_DTLS_ID`),
  KEY `FK_ULDI_URD_ULD_idx` (`USER_LOGIN_DTLS_ID`),
  KEY `FK_RMDI_URD_RMD_idx` (`ROLES_MASTER_DTLS_ID`),
  CONSTRAINT `FK_RMDI_URD_RMD` FOREIGN KEY (`ROLES_MASTER_DTLS_ID`) REFERENCES `role_master_dtls` (`ROLES_MASTER_DTLS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ULDI_URD_ULD` FOREIGN KEY (`USER_LOGIN_DTLS_ID`) REFERENCES `user_login_dtls` (`USER_LOGIN_DTLS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_dtls`
--

LOCK TABLES `user_role_dtls` WRITE;
/*!40000 ALTER TABLE `user_role_dtls` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role_dtls` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-20 17:27:27
