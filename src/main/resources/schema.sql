DROP TABLE IF EXISTS COIN;
CREATE TABLE IF NOT EXISTS COIN
(
    coin_id    int            NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name       varchar(200)   NOT NULL,
    price      decimal(65, 8) NOT NULL DEFAULT '0',
    created_at TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP               DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
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


DROP TABLE IF EXISTS ORDERBOOK;
CREATE TABLE IF NOT EXISTS ORDERBOOK
(
    orderbook_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id      int NOT NULL,
    coin_id      int NOT NULL,
    price        decimal(65, 8) COMMENT '가격',
    quantity     decimal(65, 4) COMMENT '수량',
    types        varchar(200) COMMENT '주문 구분(매수/매도)',
    state        varchar(200) COMMENT '주문 상태(대기/체결/취소)',
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at   TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);


DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
    user_id    INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id         VARCHAR(50)                    NOT NULL,
    name       VARCHAR(50)                    NOT NULL,
    password   VARCHAR(20)                    NOT NULL,
    phone      VARCHAR(20),
    created_at TIMESTAMP                      NOT NULL,
    updated_at TIMESTAMP                      NOT NULL
);


DROP TABLE IF EXISTS WALLET;
CREATE TABLE WALLET
(
    wallet_id  INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id    INT                            NOT NULL,
    coin_id    INT                            NOT NULL,
    quantity   decimal(65, 4)                 NOT NULL,
    avg_price  decimal(65, 8)                 NOT NULL,
    created_at TIMESTAMP                      NOT NULL,
    updated_at TIMESTAMP                      NOT NULL
);