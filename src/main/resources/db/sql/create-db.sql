CREATE TABLE if not exists payment (
  id    BIGINT PRIMARY KEY,
  price DECIMAL(30, 8) NOT NULL,
  bank_response VARCHAR(256)
);

CREATE TABLE if not exists flight (
 id    BIGINT PRIMARY KEY,
 name VARCHAR(256) NOT NULL,
 description VARCHAR(256) NOT NULL,
 --seats BIGINT NOT NULL
 price DECIMAL(30, 8) NOT NULL
);

CREATE TABLE if not exists seat (
 id    BIGINT PRIMARY KEY,
 seat_number DECIMAL(30, 8),
 available TINYINT
 --flight BIGINT
 --lock_until DATE
);

