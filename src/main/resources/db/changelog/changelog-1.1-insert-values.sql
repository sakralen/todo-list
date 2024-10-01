--liquibase formatted sql

--changeset sakralen:1
INSERT INTO subscriber (first_name, last_name, email)
VALUES
('John', 'Doe', 'john.doe@example.com'),
('Jane', 'Smith', 'jane.smith@example.com'),
('Robert', 'Johnson', 'robert.johnson@example.com');

INSERT INTO task (title, description, due_date, status, subscriber_id, project_id)
VALUES
('Task 1', 'Description for Task 1', '2024-10-05 10:00:00', 'PENDING', 1, NULL),
('Task 2', 'Description for Task 2', '2024-10-07 12:00:00', 'IN_PROGRESS', 2, NULL),
('Task 3', 'Description for Task 3', '2024-10-09 14:00:00', 'DONE', 3, NULL);

INSERT INTO assigned_task (subscriber_id, task_id)
VALUES
(1, 1),
(2, 2),
(3, 3);
