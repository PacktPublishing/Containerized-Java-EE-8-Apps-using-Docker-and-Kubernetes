--
-- create tables

CREATE TABLE orders (
  id          VARCHAR(255) NOT NULL,
  status      VARCHAR(255) NOT NULL,
  type        VARCHAR(255) NOT NULL,
  origin_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE origin_coffee_types (
  origin_name VARCHAR(255) NOT NULL,
  type        VARCHAR(255) NOT NULL,
  PRIMARY KEY (origin_name, type)
);

CREATE TABLE origins (
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
);

ALTER TABLE orders
  ADD CONSTRAINT FKt474abpx5pxojjm61tb1d64vp FOREIGN KEY (origin_name) REFERENCES origins;

ALTER TABLE origin_coffee_types
  ADD CONSTRAINT FKik7slqd5hcdp9kwh6vukqtfli FOREIGN KEY (origin_name) REFERENCES origins;

--
-- insert data

INSERT INTO origins VALUES ('Ethiopia');
INSERT INTO origin_coffee_types VALUES ('Ethiopia', 'ESPRESSO');
INSERT INTO origin_coffee_types VALUES ('Ethiopia', 'LATTE');
INSERT INTO origin_coffee_types VALUES ('Ethiopia', 'POUR_OVER');

INSERT INTO origins VALUES ('Colombia');
INSERT INTO origin_coffee_types VALUES ('Colombia', 'ESPRESSO');
INSERT INTO origin_coffee_types VALUES ('Colombia', 'POUR_OVER');
