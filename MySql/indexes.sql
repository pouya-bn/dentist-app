-- Indexes for users table
CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_user_type ON users (user_type);
CREATE INDEX idx_users_date_of_birth ON users (date_of_birth);

-- Indexes for dentists table
CREATE INDEX idx_dentists_license ON dentists (license_number);
CREATE INDEX idx_dentists_specialization ON dentists (specialization);

-- Indexes for patients table
CREATE INDEX idx_patients_insurance ON patients (insurance_number);

-- Indexes for boards table
CREATE INDEX idx_boards_name ON boards (name);

-- Indexes for posts table
CREATE INDEX idx_posts_title ON posts (title);
CREATE INDEX idx_posts_created_date ON posts (created_date);
CREATE INDEX idx_posts_board_id ON posts (board_id);
CREATE INDEX idx_posts_user_id ON posts (user_id);

-- Indexes for comments table
CREATE INDEX idx_comments_created_date ON comments (created_date);
CREATE INDEX idx_comments_post_id ON comments (post_id);
CREATE INDEX idx_comments_user_id ON comments (user_id);

-- Indexes for appointments table
CREATE INDEX idx_appointments_time ON appointments (time);
CREATE INDEX idx_appointments_status ON appointments (status);
CREATE INDEX idx_appointments_dentist_id ON appointments (dentist_id);
CREATE INDEX idx_appointments_patient_id ON appointments (patient_id);

-- Indexes for treatments table
CREATE INDEX idx_treatments_title ON treatments (title);
CREATE INDEX idx_treatments_status ON treatments (status);
CREATE INDEX idx_treatments_created_date ON treatments (created_date);
CREATE INDEX idx_treatments_patient_id ON treatments (patient_id);
CREATE INDEX idx_treatments_dentist_id ON treatments (dentist_id);

-- Composite indexes for frequently joined columns
CREATE INDEX idx_appointments_dentist_patient ON appointments (dentist_id, patient_id);
CREATE INDEX idx_treatments_dentist_patient ON treatments (dentist_id, patient_id);
CREATE INDEX idx_posts_board_date ON posts (board_id, created_date);