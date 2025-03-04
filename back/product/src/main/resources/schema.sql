CREATE TABLE IF NOT EXISTS compte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    username VARCHAR(255) ,
    firstname VARCHAR(255) ,
    password VARCHAR(1000) ,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Product (
    id BIGINT  AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(1000),
    image VARCHAR(255),
    category VARCHAR(255),
    price DOUBLE,
    quantity INT,
    internalReference VARCHAR(255),
    shellId INT,
    inventoryStatus VARCHAR(255),
    rating INT,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);

