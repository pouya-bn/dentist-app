-- =================================================================
-- Dental Clinic Management System Database Schema
-- =================================================================

-- Create and select the database
CREATE DATABASE IF NOT EXISTS dentist_db;
USE dentist_db;

-- =================================================================
-- Users Table
-- Core table for all system users (both dentists and patients)
-- =================================================================
CREATE TABLE `users`
(
    id            INT          NOT NULL AUTO_INCREMENT,
    username      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    phone         VARCHAR(50),           -- Optional phone number
    password      VARCHAR(255) NOT NULL,
    user_type     VARCHAR(31)  NOT NULL, -- Either 'DENTIST' or 'PATIENT'
    date_of_birth DATE,                  -- Optional date of birth
    PRIMARY KEY (id),
    UNIQUE (username),                   -- Prevent duplicate usernames
    UNIQUE (email)                       -- Prevent duplicate emails
);

-- =================================================================
-- Boards Table
-- Manages different discussion topics/categories
-- =================================================================
CREATE TABLE boards
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1024),          -- Optional board description
    PRIMARY KEY (id),
    UNIQUE (name)                       -- Each board must have unique name
);

-- =================================================================
-- Posts Table
-- Stores posts within discussion boards
-- =================================================================
CREATE TABLE posts
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL,
    content      TEXT,                  -- Post content with no length limit
    created_date DATETIME     NOT NULL, -- Timestamp of post creation
    board_id     INT          NOT NULL, -- Board this post belongs to
    user_id      INT,                   -- Author of the post (can be NULL if user deleted)
    PRIMARY KEY (id),
    CONSTRAINT fk_posts_boards FOREIGN KEY (board_id) REFERENCES boards (id) ON DELETE CASCADE,
    CONSTRAINT fk_posts_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE SET NULL
);

-- =================================================================
-- Dentists Table
-- Extended information for users who are dentists
-- =================================================================
CREATE TABLE dentists
(
    id             INT          NOT NULL,
    license_number VARCHAR(100) NOT NULL, -- Dentist's license number
    specialization VARCHAR(255),          -- Dentist's area of expertise
    PRIMARY KEY (id),
    CONSTRAINT fk_dentists_users FOREIGN KEY (id) REFERENCES `users` (id) ON DELETE CASCADE,
    UNIQUE (license_number)               -- Each license number must be unique
);

-- =================================================================
-- Patients Table
-- Extended information for users who are patients
-- =================================================================
CREATE TABLE patients
(
    id                INT NOT NULL,
    insurance_number  VARCHAR(255),      -- Optional insurance information
    emergency_contact VARCHAR(255),      -- Emergency contact details
    PRIMARY KEY (id),
    CONSTRAINT fk_patients_users FOREIGN KEY (id) REFERENCES `users` (id) ON DELETE CASCADE,
    UNIQUE (insurance_number)            -- Each insurance number must be unique
);

-- =================================================================
-- Board Memberships Table
-- Many-to-many relationship between users and boards
-- =================================================================
CREATE TABLE boards_users
(
    board_id INT NOT NULL,
    user_id  INT NOT NULL,
    PRIMARY KEY (board_id, user_id),     -- Composite primary key
    CONSTRAINT fk_boards_users_boards FOREIGN KEY (board_id) REFERENCES boards (id) ON DELETE CASCADE,
    CONSTRAINT fk_boards_users_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE CASCADE
);

-- =================================================================
-- Comments Table
-- Stores comments on board posts
-- =================================================================
CREATE TABLE comments
(
    id           INT           NOT NULL AUTO_INCREMENT,
    content      VARCHAR(2000) NOT NULL, -- Limited to 2000 characters
    created_date DATETIME      NOT NULL, -- Timestamp of comment creation
    post_id      INT           NOT NULL, -- Post this comment belongs to
    user_id      INT,                    -- Author of comment (can be NULL if user deleted)
    PRIMARY KEY (id),
    CONSTRAINT fk_comments_posts FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES `users` (id) ON DELETE SET NULL
);

-- =================================================================
-- Appointments Table
-- Manages scheduling between dentists and patients
-- =================================================================
CREATE TABLE appointments
(
    id         INT         NOT NULL AUTO_INCREMENT,
    time       DATETIME    NOT NULL, -- Scheduled appointment time
    status     VARCHAR(50) NOT NULL, -- Status: scheduled/completed/cancelled/rescheduled
    dentist_id INT,                  -- Treating dentist (can be NULL if dentist deleted)
    patient_id INT,                  -- Patient (can be NULL if patient deleted)
    PRIMARY KEY (id),
    CONSTRAINT fk_appointments_dentists FOREIGN KEY (dentist_id) REFERENCES dentists (id) ON DELETE SET NULL,
    CONSTRAINT fk_appointments_patients FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE SET NULL
);

-- =================================================================
-- Treatments Table
-- Records of dental procedures and treatments
-- =================================================================
CREATE TABLE treatments
(
    id           INT          NOT NULL AUTO_INCREMENT,
    title        VARCHAR(255) NOT NULL, -- Name of the treatment
    description  TEXT,                  -- Detailed treatment description
    status       VARCHAR(50)  NOT NULL, -- Status: completed/ongoing
    created_date DATETIME     NOT NULL, -- When treatment was initiated
    patient_id   INT,                   -- Patient receiving treatment
    dentist_id   INT,                   -- Dentist performing treatment
    PRIMARY KEY (id),
    CONSTRAINT fk_treatments_patients FOREIGN KEY (patient_id) REFERENCES patients (id) ON DELETE SET NULL,
    CONSTRAINT fk_treatments_dentists FOREIGN KEY (dentist_id) REFERENCES dentists (id) ON DELETE SET NULL
);