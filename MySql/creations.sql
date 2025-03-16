-- Create the database
CREATE DATABASE IF NOT EXISTS dentist_db;
USE dentist_db;

-- 1. Create base table for Users
CREATE TABLE `users`
(
    id            INT          NOT NULL AUTO_INCREMENT,
    username      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    phone         VARCHAR(50),
    password      VARCHAR(255) NOT NULL,
    user_type     VARCHAR(31)  NOT NULL,
    date_of_birth DATE,
    PRIMARY KEY (id),
    UNIQUE (username),
    UNIQUE (email)
);

-- 2. Create table for Boards
CREATE TABLE boards
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1024),
    PRIMARY KEY (id),
    UNIQUE (name)
);

-- 3. Create table for Posts
CREATE TABLE posts
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    content      TEXT,
    created_date DATETIME     NOT NULL,
    board_id     INT          NOT NULL,
    user_id      INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_posts_boards FOREIGN KEY (board_id) REFERENCES boards (id) ON DELETE CASCADE,
    CONSTRAINT fk_posts_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE SET NULL
);

-- 4. Create table for Dentists (inherits from Users)
CREATE TABLE dentists
(
    id             INT          NOT NULL,
    license_number VARCHAR(100) NOT NULL,
    specialization VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_dentists_users FOREIGN KEY (id) REFERENCES `users` (id) ON DELETE CASCADE,
    UNIQUE (license_number)
);

-- 5. Create table for Patients (inherits from Users; date_of_birth is now in users)
CREATE TABLE patients
(
    id                INT NOT NULL,
    insurance_number  VARCHAR(255),
    emergency_contact VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_patients_users FOREIGN KEY (id) REFERENCES `users` (id) ON DELETE CASCADE,
    UNIQUE (insurance_number)
);

-- 6. Create join table for Boards and Users (board members)
CREATE TABLE boards_users
(
    board_id INT NOT NULL,
    user_id  INT NOT NULL,
    PRIMARY KEY (board_id, user_id),
    CONSTRAINT fk_boards_users_boards FOREIGN KEY (board_id) REFERENCES boards (id) ON DELETE CASCADE,
    CONSTRAINT fk_boards_users_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE CASCADE
);

-- 7. Create table for Comments
CREATE TABLE comments
(
    id           INT           NOT NULL AUTO_INCREMENT,
    content      VARCHAR(2000) NOT NULL,
    created_date DATETIME      NOT NULL,
    post_id      INT           NOT NULL,
    user_id      INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_comments_posts FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE SET NULL
);

-- 8. Create table for Appointments
CREATE TABLE appointments
(
    id         INT         NOT NULL AUTO_INCREMENT,
    time       DATETIME    NOT NULL,
    status     VARCHAR(50) NOT NULL,
    dentist_id INT,
    patient_id INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_appointments_dentists FOREIGN KEY (dentist_id) REFERENCES dentists (id) ON DELETE SET NULL,
    CONSTRAINT fk_appointments_patients FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE SET NULL
);

-- 9. Create table for Treatments
CREATE TABLE treatments
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    status       VARCHAR(50)  NOT NULL,
    created_date DATETIME     NOT NULL,
    patient_id   INT,
    dentist_id   INT,
    PRIMARY KEY (id),
    CONSTRAINT fk_treatments_patients FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE SET NULL,
    CONSTRAINT fk_treatments_dentists FOREIGN KEY (dentist_id) REFERENCES dentists (id) ON DELETE SET NULL
);