/*
 Navicat Premium Data Transfer

 Source Server         : localDataBase
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : newsSystem

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 03/07/2018 23:08:42
*/
CREATE DATABASE IF NOT EXISTS movieadmin;
USE movieadmin;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for useraccounts
-- ----------------------------
CREATE TABLE `useraccounts` (
                                `idUserAccounts` int unsigned NOT NULL AUTO_INCREMENT,
                                `Username` varchar(45) NOT NULL,
                                `Password` varchar(45) NOT NULL,
                                PRIMARY KEY (`idUserAccounts`),
                                UNIQUE KEY `idUserAccounts_UNIQUE` (`idUserAccounts`),
                                UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

BEGIN;
INSERT INTO useraccounts (username, password) VALUES ('hey', 123);
INSERT INTO useraccounts (username, password) VALUES ('hi', 456);
COMMIT;
-- ----------------------------
-- View structure for userreview
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 1;
