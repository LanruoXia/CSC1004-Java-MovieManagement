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
DROP TABLE IF EXISTS `useraccounts`;
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

-- ----------------------------
-- Records of useraccounts
-- ----------------------------
BEGIN;
INSERT INTO `useraccounts` VALUES (1,'hey','123','19','Female','K Avenue'),(2,'hi','456','19','Male','C Avenue'),(3,'Scarlet','333','20','Female','LA'),(4,'Elle','999','21','Female','FL'),(5,'Austin','111','21','Male','Norway'),(6,'Christopher','111','25','Male','Paris'),(7,'Y','1','18','Male','LG'),(8,'X','111','19','Female','11'),(9,'Test','11','60','Nonbinary','11'),(10,'Hello','2','12','Female','2'),(11,'Demo','2','12','Nonbinary','2'),(12,'New','2','23','Nonbinary','1');
COMMIT;

-- ----------------------------
-- Table structure for movie_repo
-- ----------------------------
DROP TABLE IF EXISTS `movie_repo`;
CREATE TABLE `movie_repo` (
                              `idmovies` int NOT NULL AUTO_INCREMENT,
                              `movie` varchar(45) NOT NULL,
                              `genre` varchar(45) NOT NULL,
                              `year` varchar(45) NOT NULL,
                              `country` varchar(45) DEFAULT NULL,
                              `director` varchar(45) NOT NULL,
                              `rating` varchar(45) DEFAULT NULL,
                              `poster_path` varchar(250) DEFAULT NULL,
                              `introduction` varchar(10000) NOT NULL,
                              PRIMARY KEY (`idmovies`),
                              UNIQUE KEY `idmovies_UNIQUE` (`idmovies`),
                              UNIQUE KEY `movie_UNIQUE` (`movie`),
                              UNIQUE KEY `image_path_UNIQUE` (`poster_path`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of movies
-- ----------------------------
BEGIN;
INSERT INTO `movie_repo` VALUES (1,'Interstellar','Sci-Fi','2014','United Kindom, United States','Christopher Nolan','9.6','images/interstellar.jpg','In the near future around the American Midwest, Cooper, an ex-science engineer and pilot, is tied to his farming land with his daughter Murph and son Tom. As devastating sandstorms ravage Earth\'s crops, the people of Earth realize their life here is coming to an end as food begins to run out. Eventually stumbling upon a N.A.S.A. base near Cooper\'s home, he is asked to go on a daring mission with a few other scientists into a wormhole because of Cooper\'s scientific intellect and ability to pilot aircraft unlike the other crew members. In order to find a new home while Earth decays, Cooper must decide to either stay, or risk never seeing his children again in order to save the human race by finding another habitable planet.'),(6,'Mean Girls','Comedy','2004','United States','Mark Waters','8.0','images/MeanGirls.jpg','Cady Heron moves to a new home from the bush country of Africa. She goes to a new school where she meets Janis and Damian. Her new friends warn her to stay away from the Plastics: the A-list, popular, crude, and beautiful clique headed by Regina George with Gretchen and Karen. When Cady sees Aaron Samuels, she falls in love. When Regina discovers this, she seeks revenge by taking and dangling Aaron in front of Cady. Now Cady, Janis, and Damian plot to bring Regina\'s status down. However, as Cady continues to spend more time with the Plastics, she begins to become one of them.'),(7,'The Matrix','Sci-Fi','1999','United States, Australia','The Wachowskis','8.0','images/theMatrix.jpg','Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. As a rebel against the machines, Neo must confront the agents: super-powerful computer programs devoted to stopping Neo and the entire human rebellion.'),(9,'Heidi','Drama','2015','Switzerland, Germany','Alain Gsponer',NULL,'images/Heidi.jpg','Heidi is an eight-year-old Swiss orphan whose aunt hands her off to her mountain-dwelling grandfather, then returns and takes her to live in the wealthy Sesemann household in Frankfurt, Germany as a companion to Klara, a sheltered girl in a wheelchair. Heidi is unhappy but makes the best of the situation, always longing for her grandfather.'),(10,'Before Sunrise','Romance','1995','United States, Austria','Richard Linklater','9.5','images/beforeSunrise.jpg','Two young people, a French graduate student and an American tourist, meet briefly on a train traveling through Europe. Each enjoy talking to each other so much that they decide to get off the train together in Vienna. As they wander the city together and their night progresses, their connection makes the idea of separating the next morning a difficult choice.'),(15,'The Silence of the Lambs','Horror','1991','United States','Jonathan Demme','9.0','images/SilenceLamb.jpg','Young F.B.I. trainee Clarice Starling (Jodie Foster) is assigned to help find a missing woman to save her from a psychopathic serial killer (Ted Levine) who skins his victims. Clarice attempts to gain a better insight into the twisted mind of the killer by talking to another psychopath: Dr. Hannibal Lecter (Sir Anthony Hopkins), who used to be a respected psychiatrist. F.B.I. Special Agent Jack Crawford (Scott Glenn) believes that Lecter, who is also a very powerful and clever mind manipulator, has the answers to their questions and can help locate the killer. However, Clarice must first gain Lecter\'s confidence before the inmate will give away any information.'),(16,'The Martian','Sci-Fi','2015','United Kindom, United States','Ridley Scott','9.0','images/TheMartian.jpg','During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive. Millions of miles away, NASA and a team of international scientists work tirelessly to bring the Martian home, while his crewmates concurrently plot a daring, if not impossible, rescue mission. As these stories of incredible bravery unfold, the world comes together to root for Watney\'s safe return.'),(21,'The Shawshank Redemption','Drama','1994','United States','Frank Darabont',NULL,'images/Shawshank.jpg','Chronicles the experiences of a formerly successful banker as a prisoner in the gloomy jailhouse of Shawshank after being found guilty of a crime he did not commit. The film portrays the man\'s unique way of dealing with his new, torturous life; along the way he befriends a number of fellow prisoners, most notably a wise long-term inmate named Red.');
COMMIT;


-- ----------------------------
-- Table structure for user_comments
-- ----------------------------
DROP TABLE IF EXISTS `user_comments`;
CREATE TABLE `user_comments` (
                                 `iduser_reviews` int NOT NULL AUTO_INCREMENT,
                                 `idmovies` int NOT NULL,
                                 `movie` varchar(45) NOT NULL,
                                 `username` varchar(45) NOT NULL,
                                 `comment` varchar(10000) NOT NULL,
                                 `headline` varchar(50) NOT NULL,
                                 `image_path` varchar(45) DEFAULT NULL,
                                 PRIMARY KEY (`iduser_reviews`),
                                 UNIQUE KEY `iduser_reviews_UNIQUE` (`iduser_reviews`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Records of movies
-- ----------------------------
BEGIN;
INSERT INTO `user_comments` VALUES (1,1,'Interstellar','Y','','Incredible',NULL),(2,6,'Mean Girls','Y','Cool		','Cool',NULL),(3,1,'Interstellar','Y','Cool','Cool',NULL),(4,1,'Interstellar','Y','You should watch this!!!!','Nice Movie',NULL),(5,1,'Interstellar','Y','It\'s nice, isn\'t  it?','A nice movie',NULL),(6,6,'Mean Girls','Y','!!!!','Great',NULL),(7,7,'The Matrix','Y','It\'s a test\n','Nice Movie',NULL),(8,1,'Interstellar','Scarlet','The best movie I have ever watched!','I love this movie!',NULL),(9,6,'Mean Girls','Scarlet','Look at how dramatic it is!','So dramatic!',NULL),(10,6,'Mean Girls','Scarlet','I decided to change my rating to a 8. I don\'t know why','Change my rating',NULL),(11,6,'Mean Girls','Scarlet','!!','Change to 7 now',NULL),(12,1,'Interstellar','Y','It\'s a test!','Hey',NULL),(13,10,'Before Sunrise','Y','Love it!','Amazing',NULL),(16,15,'The Silence of the Lambs','Scarlet','She is an amazing actress!','Jodie Foster!','images/Jodie.jpg'),(21,10,'Before Sunrise','Y','hi','hi',NULL),(22,10,'Before Sunrise','Y','hey','hey',NULL),(23,10,'Before Sunrise','Scarlet','great!','So great',NULL),(24,10,'Before Sunrise','Elle','Celine','Celine','images/Celine.jpg'),(25,16,'The Martian','hey','Hey!','Nice Movie',NULL),(26,10,'Before Sunrise','hey','love it!','Love it!','images/Hey.jpg'),(27,1,'Interstellar','Hello','It\'s amazing.','I love this movie!','images/murph.jpg'),(28,6,'Mean Girls','hey','Hi','Hi',NULL),(29,6,'Mean Girls','hey','random','!','images/random.jpg'),(30,6,'Mean Girls','hey','hey','another random','images/random2.jpg'),(31,6,'Mean Girls','hey','Test','Test','images/111.jpg'),(32,1,'Interstellar','Demo','I love it!','This is a good one','images/Shawshank.jpg'),(33,1,'Interstellar','New','It\'s cool','I love it',NULL);
COMMIT;


-- ----------------------------
-- Table structure for movie_repo
-- ----------------------------
DROP TABLE IF EXISTS `user_ratings`;
CREATE TABLE `user_ratings` (
                                `iduser_ratings` int NOT NULL AUTO_INCREMENT,
                                `username` varchar(45) NOT NULL,
                                `movie` varchar(45) NOT NULL,
                                `idmovies` int NOT NULL,
                                `rating` int NOT NULL,
                                PRIMARY KEY (`iduser_ratings`),
                                UNIQUE KEY `iduser_ratings_UNIQUE` (`iduser_ratings`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of movies
-- ----------------------------
BEGIN;
INSERT INTO `user_ratings` VALUES (1,'Y','Interstellar',1,8),(2,'Y','Mean Girls',6,8),(3,'Y','The Matrix',7,8),(4,'Scarlet','Interstellar',1,10),(5,'Scarlet','Mean Girls',6,7),(6,'Y','Before Sunrise',10,9),(7,'Elle','Before Sunrise',10,9),(8,'Scarlet','The Silence of the Lambs',15,9),(9,'Scarlet','Before Sunrise',10,10),(10,'hey','The Martian',16,9),(11,'hey','Before Sunrise',10,10),(12,'Hello','Interstellar',1,10),(13,'hey','Mean Girls',6,9),(14,'Demo','Interstellar',1,10),(15,'New','Interstellar',1,10);

COMMIT;


SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 1;
