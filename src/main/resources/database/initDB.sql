CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    card CHAR(18) NOT NULL ,
    password CHAR(18)  NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id    SERIAL PRIMARY KEY ,
    user_id INTEGER NOT NULL ,
    balance_byn DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    balance_usd DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    balance_eur DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS services (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true
);
