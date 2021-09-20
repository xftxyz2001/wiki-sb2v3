DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
                         `id` bigint NOT NULL,
                         `name` varchar(50),
                         `password` varchar(50),
                         PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;