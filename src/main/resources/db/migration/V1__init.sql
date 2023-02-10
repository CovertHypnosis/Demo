CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_on` date DEFAULT NULL,
    `update_on` date DEFAULT NULL,
    `password` varchar(255) NOT NULL,
    `role` smallint NOT NULL,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_on` date DEFAULT NULL,
    `update_on` date DEFAULT NULL,
    `name` varchar(255) NOT NULL,
    `price` FLOAT NOT NULL,
    `category` smallint NOT NULL,
    `count` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` date DEFAULT NULL,
  `update_on` date DEFAULT NULL,
  `product_count` bigint DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;