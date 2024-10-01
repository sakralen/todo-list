--liquibase formatted sql

--changeset sakralen:1
CREATE TABLE subscriber (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL DEFAULT '{bcrypt}$2a$12$PJeCB2tVVS.0/gyeHwS3geHdXUXU3qkijW7KjZ3Rlh09xeP1saRHC',
    email VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(64) NOT NULL,
    description TEXT,
    due_date TIMESTAMP,
    status VARCHAR(16) NOT NULL,
    subscriber_id BIGINT NOT NULL,
    project_id BIGINT,

    FOREIGN KEY (subscriber_id) REFERENCES subscriber(id)
);

CREATE TABLE assigned_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subscriber_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,

    CONSTRAINT unique_subscriber_task UNIQUE (subscriber_id, task_id),

    FOREIGN KEY (subscriber_id) REFERENCES subscriber(id),
    FOREIGN KEY (task_id) REFERENCES task(id)
);
