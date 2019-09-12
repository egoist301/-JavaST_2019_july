USE rubik;
CREATE TABLE `users`
(
    `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username`     VARCHAR(255) NOT NULL UNIQUE,
    `password`     NCHAR(32)    NOT NULL,
    `email`        VARCHAR(255) NOT NULL UNIQUE,
    `account_type` TINYINT      NOT NULL,
    `phone`        INT UNSIGNED NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (`id`),
    CONSTRAINT type_check CHECK ( `account_type` IN (0, 1))
);
CREATE TABLE `manufacturer`
(
    `id`                TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name_manufacturer` VARCHAR(255)     NOT NULL,
    CONSTRAINT PK_manufacturer PRIMARY KEY (`id`)
);
CREATE TABLE `plastic_color`
(
    `id`            TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `plastic_color` VARCHAR(20)      NOT NULL,
    CONSTRAINT PK_plastic_color PRIMARY KEY (`id`)
);
CREATE TABLE rubiks_cube
(
    `id`               INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(255)     NOT NULL,
    `price`            DOUBLE           NOT NULL,
    `weight`           DOUBLE           NOT NULL,
    `info`             VARCHAR(2000)    NOT NULL,
    `primary_plastic`  BOOLEAN          NOT NULL,
    `size`             VARCHAR(16)      NOT NULL,
    `id_plastic_color` TINYINT UNSIGNED NOT NULL,
    `id_manufacturer`  TINYINT UNSIGNED NOT NULL,
    `id_type_cube`     TINYINT UNSIGNED NOT NULL,
    CONSTRAINT PK_custom_rubiks_cube PRIMARY KEY (`id`),
    CONSTRAINT FK_Rubiks_Plastic FOREIGN KEY (`id_plastic_color`)
        REFERENCES plastic_color (`id`),
    CONSTRAINT FK_Rubiks_Manufacturer FOREIGN KEY (`id_manufacturer`)
        REFERENCES manufacturer (`id`),
    CONSTRAINT FK_Rubiks_Type FOREIGN KEY (`id_type_cube`)
        REFERENCES type_cube (id)
);
CREATE TABLE `basket_rubiks_cube`
(
    `id`      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `id_cube` INT UNSIGNED NOT NULL,
    `id_user` INT UNSIGNED NOT NULL,
    CONSTRAINT PK_basket_cube PRIMARY KEY (`id`),
    CONSTRAINT FK_Basket_Rubiks FOREIGN KEY (`id_cube`)
        REFERENCES rubiks_cube (`id`),
    CONSTRAINT FK_Basket_User FOREIGN KEY (`id_user`)
        REFERENCES users (`id`)
);
CREATE TABLE `type_cube`
(
    `id`   TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(255)     NOT NULL,
    CONSTRAINT PK_type_cube PRIMARY KEY (`id`)
);
