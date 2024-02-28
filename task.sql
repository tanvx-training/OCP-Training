CREATE TABLE users
(
    user_id   SERIAL PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL
);

CREATE TABLE tasks
(
    task_id     SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    deadline    DATE,
    status      VARCHAR(50),
    assigned_to INT,
    FOREIGN KEY (assigned_to) REFERENCES users (user_id)
);
