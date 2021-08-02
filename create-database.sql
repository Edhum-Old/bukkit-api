CREATE TABLE IF NOT EXISTS players
(
    uuid        VARCHAR(255),
    name        VARCHAR(255),
    group_id    TINYINT,
    language_id TINYINT,
    money       BIGINT,
    PRIMARY KEY (uuid)
);