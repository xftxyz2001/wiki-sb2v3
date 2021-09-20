DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`
(
    `id`       bigint NOT NULL,
    `name`     varchar(50),
    `password` varchar(50),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4;

INSERT INTO `test` (id, name, password)
VALUES (1, '测试', 'password');

SELECT *
From test;

DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`
(
    `id`   bigint NOT NULL,
    `name` varchar(50),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  default
      CHARACTER SET = utf8mb4;

INSERT INTO `demo` (id, name)
VALUES (1, '测试');