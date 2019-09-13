USE rubik;
CREATE TABLE `users`
(
    `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `password` NCHAR(32)    NOT NULL,
    `email`    VARCHAR(255) NOT NULL UNIQUE,
    `role`     TINYINT      NOT NULL,
    `phone`    INT UNSIGNED NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (`id`),
    CONSTRAINT type_check CHECK ( `role` IN (0, 1))
);
CREATE TABLE `manufacturer`
(
    `id`                TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name_manufacturer` VARCHAR(255)     NOT NULL UNIQUE,
    CONSTRAINT PK_manufacturer PRIMARY KEY (`id`)
);
CREATE TABLE `plastic_color`
(
    `id`            TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `plastic_color` VARCHAR(20)      NOT NULL UNIQUE,
    CONSTRAINT PK_plastic_color PRIMARY KEY (`id`)
);
CREATE TABLE `type_cube`
(
    `id`   TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(255)     NOT NULL UNIQUE,
    CONSTRAINT PK_type_cube PRIMARY KEY (`id`)
);
CREATE TABLE rubiks_cube
(
    `id`               INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `model`            VARCHAR(255)     NOT NULL,
    `price`            DOUBLE           NOT NULL,
    `weight`           DOUBLE           NOT NULL,
    `info`             VARCHAR(2000)    NOT NULL,
    `primary_plastic`  BOOLEAN          NOT NULL,
    `size`             VARCHAR(8)       NOT NULL,
    `plastic_color_id` TINYINT UNSIGNED NOT NULL,
    `manufacturer_id`  TINYINT UNSIGNED NOT NULL,
    `type_cube_id`     TINYINT UNSIGNED NOT NULL,
    `date`             DATETIME         NOT NULL,
    CONSTRAINT PK_custom_rubiks_cube PRIMARY KEY (`id`),
    CONSTRAINT FK_Rubiks_Plastic FOREIGN KEY (`plastic_color_id`)
        REFERENCES plastic_color (`id`),
    CONSTRAINT FK_Rubiks_Manufacturer FOREIGN KEY (`manufacturer_id`)
        REFERENCES manufacturer (`id`),
    CONSTRAINT FK_Rubiks_Type FOREIGN KEY (`type_cube_id`)
        REFERENCES type_cube (id)
);
CREATE TABLE `basket_rubiks_cube`
(
    `id`      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `cube_id` INT UNSIGNED NOT NULL,
    `user_id` INT UNSIGNED NOT NULL,
    CONSTRAINT PK_basket_cube PRIMARY KEY (`id`),
    CONSTRAINT FK_Basket_Rubiks FOREIGN KEY (`cube_id`)
        REFERENCES rubiks_cube (`id`),
    CONSTRAINT FK_Basket_User FOREIGN KEY (`user_id`)
        REFERENCES users (`id`)
);
CREATE TABLE `store_image`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `cube_id`    INT UNSIGNED NOT NULL,
    `image_path` VARCHAR(255) NOT NULL,
    CONSTRAINT PK_store_image PRIMARY KEY (`id`),
    CONSTRAINT FK_Store_Rubiks FOREIGN KEY (`cube_id`)
        REFERENCES rubiks_cube (`id`)
);