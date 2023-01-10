-- Adminer 4.8.1 MySQL 8.0.31 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` int NOT NULL,
                            `name` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `category_event`;
CREATE TABLE `category_event` (
                                  `event_id` int NOT NULL,
                                  `category_id` int NOT NULL,
                                  PRIMARY KEY (`event_id`,`category_id`),
                                  KEY `FKdcjyvv7u0eyqyuvcmb60j8yxc` (`category_id`),
                                  CONSTRAINT `FKdcjyvv7u0eyqyuvcmb60j8yxc` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
                                  CONSTRAINT `FKjmgtunie7m7jhfre5h2uqn6fw` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `category_seq`;
CREATE TABLE `category_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `category_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
                         `id` int NOT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `end_date` date DEFAULT NULL,
                         `max_participants` int DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `privacy` smallint DEFAULT NULL,
                         `start_date` date DEFAULT NULL,
                         `user_fk` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKtd9qh212371c6v669ji1ef2k0` (`user_fk`),
                         CONSTRAINT `FKtd9qh212371c6v669ji1ef2k0` FOREIGN KEY (`user_fk`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `event_geolocation`;
CREATE TABLE `event_geolocation` (
                                     `event_id` int NOT NULL,
                                     `geolocation` double DEFAULT NULL,
                                     `geolocation_order` int NOT NULL,
                                     PRIMARY KEY (`event_id`,`geolocation_order`),
                                     CONSTRAINT `FKnj0g9i0gyxi80i95octqg8hdl` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `event_photos`;
CREATE TABLE `event_photos` (
                                `event_id` int NOT NULL,
                                `photos` varchar(255) DEFAULT NULL,
                                KEY `FKim0syq91dn8w2mbdxp1qoo5x4` (`event_id`),
                                CONSTRAINT `FKim0syq91dn8w2mbdxp1qoo5x4` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `event_seq`;
CREATE TABLE `event_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `event_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
                        `id` int NOT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `is_general` bit(1) NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `event_fk` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK99m53vu1l7ovoxcis0n1qe36o` (`event_fk`),
                        CONSTRAINT `FK99m53vu1l7ovoxcis0n1qe36o` FOREIGN KEY (`event_fk`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `feed_seq`;
CREATE TABLE `feed_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `feed_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest` (
                            `id` int NOT NULL,
                            `hashtag` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `interest_event`;
CREATE TABLE `interest_event` (
                                  `event_id` int NOT NULL,
                                  `interest_id` int NOT NULL,
                                  PRIMARY KEY (`event_id`,`interest_id`),
                                  KEY `FKn28kq09trg88vxgvn9st99wnu` (`interest_id`),
                                  CONSTRAINT `FKn28kq09trg88vxgvn9st99wnu` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`id`),
                                  CONSTRAINT `FKsqjwwu3wa5sjcf8aa7c9pdb9e` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `interest_seq`;
CREATE TABLE `interest_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `interest_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
                            `id` int NOT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `location_event`;
CREATE TABLE `location_event` (
                                  `event_id` int NOT NULL,
                                  `location_id` int NOT NULL,
                                  PRIMARY KEY (`event_id`,`location_id`),
                                  KEY `FKlw3grdcil5qqb3ou3sa2yya7e` (`location_id`),
                                  CONSTRAINT `FK8n48720wwpxnxpqft6sxdd5yo` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
                                  CONSTRAINT `FKlw3grdcil5qqb3ou3sa2yya7e` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `location_seq`;
CREATE TABLE `location_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `location_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `id` int NOT NULL,
                           `content` varchar(255) DEFAULT NULL,
                           `creation_date` date DEFAULT NULL,
                           `feed_fk` int DEFAULT NULL,
                           `user_fk` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKm91vt2uddopc5ybqhs1d3gfkh` (`feed_fk`),
                           KEY `FKnlj1iqap3n0sf8sd7ocgognur` (`user_fk`),
                           CONSTRAINT `FKm91vt2uddopc5ybqhs1d3gfkh` FOREIGN KEY (`feed_fk`) REFERENCES `feed` (`id`),
                           CONSTRAINT `FKnlj1iqap3n0sf8sd7ocgognur` FOREIGN KEY (`user_fk`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `message_seq`;
CREATE TABLE `message_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `message_seq` (`next_val`) VALUES
    (1);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `first_name` varchar(255) DEFAULT NULL,
                        `last_name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `photo` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `user_feed_bridge`;
CREATE TABLE `user_feed_bridge` (
                                    `role` smallint DEFAULT NULL,
                                    `user_id` int NOT NULL,
                                    `feed_id` int NOT NULL,
                                    PRIMARY KEY (`feed_id`,`user_id`),
                                    KEY `FKei84sxxprbw3tgb3c56chqqww` (`user_id`),
                                    CONSTRAINT `FKei84sxxprbw3tgb3c56chqqww` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                                    CONSTRAINT `FKtiwp4nnj60xj1a7dgk5ou2fgv` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `user_seq`;
CREATE TABLE `user_seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_seq` (`next_val`) VALUES
    (1);

-- 2023-01-09 18:49:14
