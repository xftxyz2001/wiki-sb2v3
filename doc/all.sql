#电子书表
DROP table if exists `ebook`;
CREATE TABLE `ebook`
(
    `id`           bigint       NOT NULL COMMENT 'id',
    `name`         varchar(50)  NULL COMMENT '名称',
    `category1_id` bigint       NULL COMMENT '分类1',
    `category2_id` bigint       NULL COMMENT '分类2',
    `description`  varchar(200) NULL COMMENT '描述',
    `cover`        varchar(200) NULL COMMENT '封面',
    `doc_count`    int          NULL COMMENT '文档数',
    `view_count`   int          NULL COMMENT '阅读数',
    `vote_count`   int          NULL COMMENT '点赞数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 comment ='电子书';

INSERT INTO `ebook`(id, name, description)
values (1, 'Spring Boot 入门教程', '零基础入门 Java开发，企业级应用开发');
INSERT INTO `ebook`(id, name, description)
values (2, 'Vue 入门教程', '零基础入门 Vue 开发，企业级应用开发');
INSERT INTO `ebook`(id, name, description)
values (3, 'Python 入门教程', '零基础入门 Python 开发，企业级应用开发');
INSERT INTO `ebook`(id, name, description)
values (4, 'Mysql 入门教程', '零基础入门 Mysql 开发，企业级应用开发');
INSERT INTO `ebook`(id, name, description)
values (5, 'Oracle 入门教程', '零基础入门 Oracle 开发，企业级应用开发');



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

