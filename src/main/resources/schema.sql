DROP TABLE IF EXISTS COIN;
CREATE TABLE IF NOT EXISTS COIN
(
    coin_id     int               NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        varchar(200)      NOT NULL,
    price       float             NOT NULL DEFAULT '0',
    created_at  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

INSERT INTO COIN (coin_id, name, price, created_at, updated_at)
VALUES (1, 'BTC', 0, now(), now());

INSERT INTO COIN (coin_id, name, price, created_at, updated_at)
VALUES (2, 'ETH', 0, now(), now());

INSERT INTO COIN (coin_id, name, price, created_at, updated_at)
VALUES (3, 'LTC', 0, now(), now());

INSERT INTO COIN (coin_id, name, price, created_at, updated_at)
VALUES (4, 'ETC', 0, now(), now());

INSERT INTO COIN (coin_id, name, price, created_at, updated_at)
VALUES (5, 'XRP', 0, now(), now());