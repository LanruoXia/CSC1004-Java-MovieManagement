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
                                `iduseraccounts` int NOT NULL AUTO_INCREMENT,
                                `username` varchar(45) NOT NULL,
                                `password` varchar(45) NOT NULL,
                                `age` varchar(45) NOT NULL,
                                `gender` varchar(45) NOT NULL,
                                `address` varchar(45) NOT NULL,
                                PRIMARY KEY (`iduseraccounts`),
                                UNIQUE KEY `iduseraccounts_UNIQUE` (`iduseraccounts`),
                                UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

BEGIN;
INSERT INTO useraccounts (username, password, age, gender, address) VALUES ('hey', 123, 19, 'Female', 'K Avenue' );
INSERT INTO useraccounts (username, password, age, gender, address) VALUES ('hi', 456, 19, 'Male', 'C Avenue' );

COMMIT;
-- ----------------------------
-- View structure for userreview
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 1;
