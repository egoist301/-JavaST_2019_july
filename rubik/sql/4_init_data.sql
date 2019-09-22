USE `rubik`;

INSERT INTO users(`username`, `password`, `email`, `role`, `phone`, `blocked`)
VALUES ('admin', 'admin', 'admin@mail.ru', 0, 1111111, false);

INSERT INTO plastic_color(`plastic_color`)
VALUES ('color'),
       ('black'),
       ('white');

INSERT INTO manufacturer(`name_manufacturer`)
VALUES ('Dayan'),
       ('Moyu'),
       ('Gan'),
       ('Rubiks'),
       ('ShengShou'),
       ('XIAOMI'),
       ('V-cube'),
       ('Yuxin'),
       ('Moffange'),
       ('YJ');

INSERT INTO form(`name`)
VALUES ('Cuboid'),
       ('Pyramid'),
       ('Megaminx'),
       ('Skewb'),
       ('Square'),
       ('Cube');