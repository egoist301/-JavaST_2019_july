CREATE DATABASE `rubik` DEFAULT CHARACTER SET utf8;

CREATE USER `rubik_user`@`localhost`;

GRANT SELECT, UPDATE, INSERT, DELETE
ON `rubik`.*
TO rubik_user@localhost;

GRANT SELECT, UPDATE, INSERT, DELETE
    ON `rubik`.*
    TO rubik_user@'%';