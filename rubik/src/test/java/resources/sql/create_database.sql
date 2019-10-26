CREATE DATABASE IF NOT EXISTS `rubik_test` DEFAULT CHARACTER SET utf8;

CREATE USER IF NOT EXISTS `rubik_user`@`localhost` IDENTIFIED BY 'rubik_password';

GRANT SELECT, UPDATE, INSERT, DELETE
    ON `rubik_test`.*
    TO rubik_user@localhost;