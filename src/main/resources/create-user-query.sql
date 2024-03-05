CREATE TABLE IF NOT EXISTS app_user(
    id VARCHAR(255) not null primary key,
    username VARCHAR(255) not null unique,
    password VARCHAR(10) not null,
    full_name VARCHAR(100) not null,
    user_token VARCHAR(255) not null,
    status VARCHAR(20) not null,
    created_date datetime not null,
    modified_date datetime not null,
    last_login datetime not null
);

CREATE TABLE IF NOT EXISTS phone (
    id VARCHAR(255) not null primary key,
    number VARCHAR(100)  null,
    city_code VARCHAR(100)  null,
    contry_code VARCHAR(100)  null,
    id_user VARCHAR(255) not null,
    FOREIGN KEY (id_user) REFERENCES appUser(id)
);




