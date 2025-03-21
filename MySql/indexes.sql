-- =================================================================
-- Essential Indexes for Performance Critical Operations
-- =================================================================

-- Users Table - Index for filtering
CREATE INDEX idx_users_user_type ON users (user_type);  -- Used for filtering dentists/patients

-- Appointments Table - Indexes for scheduling operations
CREATE INDEX idx_appointments_time ON appointments (time);  -- For finding available slots
CREATE INDEX idx_appointments_dentist_patient ON appointments (dentist_id, patient_id);  -- For looking up specific appointments

-- Dentists Table - Index for filtering
CREATE INDEX idx_dentists_specialization ON dentists (specialization);  -- For filtering dentists by specialization

-- Posts and Comments - Indexes for forum functionality
CREATE INDEX idx_posts_board_date ON posts (board_id, created_date);  -- For displaying recent posts in boards
CREATE INDEX idx_comments_post_id ON comments (post_id);  -- For fetching comments for posts

-- Treatments Table - Index for filtering
CREATE INDEX idx_treatments_status ON treatments (status);  -- For filtering active/completed treatments
