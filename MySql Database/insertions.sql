-- 1. Populate the `users` table with 10 records (5 dentists and 5 patients)
INSERT INTO users (id, username, email, phone, password, user_type, date_of_birth)
VALUES (1, 'johnsmith', 'john.smith@dentalcare.com', '5551234567', 'password1', 'DENTIST', '1975-05-20'),
       (2, 'emilydavis', 'emily.davis@dentalcare.com', '5552345678', 'password2', 'DENTIST', '1980-03-15'),
       (3, 'robertjohnson', 'robert.johnson@dentalcare.com', '5553456789', 'password3', 'DENTIST', '1978-11-30'),
       (4, 'lisamartinez', 'lisa.martinez@dentalcare.com', '5554567890', 'password4', 'DENTIST', '1982-07-25'),
       (5, 'michaelbrown', 'michael.brown@dentalcare.com', '5555678901', 'password5', 'DENTIST', '1979-02-10'),
       (6, 'alicewilliams', 'alice.williams@example.com', '5556789012', 'password6', 'PATIENT', '1990-01-05'),
       (7, 'bobjones', 'bob.jones@example.com', '5557890123', 'password7', 'PATIENT', '1988-08-12'),
       (8, 'caroltaylor', 'carol.taylor@example.com', '5558901234', 'password8', 'PATIENT', '1992-12-03'),
       (9, 'davidmiller', 'david.miller@example.com', '5559012345', 'password9', 'PATIENT', '1985-06-17'),
       (10, 'emmawilson', 'emma.wilson@example.com', '5550123456', 'password10', 'PATIENT', '1995-09-09');

-- 2. Populate the `dentists` table (for users with ids 1-5) with extra fields
INSERT INTO dentists (id, license_number, specialization)
VALUES (1, 'DENT-1001', 'General Dentistry'),
       (2, 'DENT-1002', 'Orthodontics'),
       (3, 'DENT-1003', 'Endodontics'),
       (4, 'DENT-1004', 'Periodontics'),
       (5, 'DENT-1005', 'Prosthodontics');

-- 3. Populate the `patients` table (for users with ids 6-10) with extra fields
INSERT INTO patients (id, insurance_number, emergency_contact)
VALUES (6, 'INS-2001', 'Laura Williams: 5551112222'),
       (7, 'INS-2002', 'Mark Jones: 5552223333'),
       (8, 'INS-2003', 'Sarah Taylor: 5553334444'),
       (9, 'INS-2004', 'James Miller: 5554445555'),
       (10, 'INS-2005', 'Olivia Wilson: 5555556666');

-- 4. Populate the `boards` table with 5 records
INSERT INTO boards (id, name, description)
VALUES (1, 'General Discussion', 'A board for general dental topics and discussions'),
       (2, 'Treatment Tips', 'Share and discuss dental treatment tips and techniques'),
       (3, 'Dental Technology', 'Latest updates and discussions on dental technology'),
       (4, 'Practice Management', 'Talk about managing dental practices and offices'),
       (5, 'Patient Feedback', 'Patients share their experiences and feedback');

-- 5. Populate the join table `boards_users` with sample associations
INSERT INTO boards_users (board_id, user_id)
VALUES (1, 1), -- Dr. John Smith on General Discussion
       (1, 6), -- Alice Williams on General Discussion
       (2, 2), -- Dr. Emily Davis on Treatment Tips
       (2, 7), -- Bob Jones on Treatment Tips
       (3, 3), -- Dr. Robert Johnson on Dental Technology
       (3, 8), -- Carol Taylor on Dental Technology
       (4, 4), -- Dr. Lisa Martinez on Practice Management
       (4, 9), -- David Miller on Practice Management
       (5, 5), -- Dr. Michael Brown on Patient Feedback
       (5, 10);
-- Emma Wilson on Patient Feedback

INSERT INTO posts (id, title, content, created_date, board_id, user_id)
VALUES (1, 'Welcome to the Forum', 'We welcome everyone to join our dental community forum!', '2025-01-01 09:00:00', 1,
        1),
       (2, 'New Treatment Techniques', 'Let’s discuss some innovative treatment techniques.', '2025-01-02 10:30:00', 2,
        2),
       (3, 'Latest Dental Tools', 'Check out these new dental tools that are changing the industry.',
        '2025-01-03 11:15:00', 3, 3),
       (4, 'Managing Your Practice', 'Effective strategies for managing your dental practice.', '2025-01-04 12:45:00',
        4, 4),
       (5, 'Patient Experiences', 'Share your experiences with dental treatments and procedures.',
        '2025-01-05 13:30:00', 5, 10),
       (6, 'General Discussion', 'A board for general dental topics and discussions', '2025-01-06 14:00:00', 1,
        6),
       (7, 'Treatment Tips', 'Share and discuss dental treatment tips and techniques', '2025-01-07 15:00:00', 2,
        7),
       (8, 'Dental Technology', 'Latest updates and discussions on dental technology', '2025-01-08 16:00:00', 3,
        8),
       (9, 'Practice Management', 'Talk about managing dental practices and offices', '2025-01-09 17:00:00', 4,
        9),
       (10, 'Patient Feedback', 'Patients share their experiences and feedback', '2025-01-10 18:00:00', 5,
        10);

-- 6. Populate the join table `boards_posts` with sample associations
INSERT INTO boards_posts (board_id, post_id)
VALUES (1, 1), -- General Discussion post 1
       (2, 2), -- Treatment Tips post 2
       (3, 3), -- Dental Technology post 3
       (4, 4), -- Practice Management post 4
       (5, 5), -- Patient Feedback post 5
       (1, 6), -- General Discussion post 6
       (2, 7), -- Treatment Tips post 7
       (3, 8), -- Dental Technology post 8
       (4, 9), -- Practice Management post 9
       (5, 10);
-- Patient Feedback post 10

-- 7. Populate the `posts` table with 5 records
INSERT INTO posts (id, title, content, created_date, board_id, user_id)
VALUES (11, 'Welcome to the Forum', 'We welcome everyone to join our dental community forum!', '2025-01-01 09:00:00', 1,
        1),
       (12, 'New Treatment Techniques', 'Let’s discuss some innovative treatment techniques.', '2025-01-02 10:30:00', 2,
        2),
       (13, 'Latest Dental Tools', 'Check out these new dental tools that are changing the industry.',
        '2025-01-03 11:15:00', 3, 3),
       (14, 'Managing Your Practice', 'Effective strategies for managing your dental practice.', '2025-01-04 12:45:00',
        4, 4),
       (15, 'Patient Experiences', 'Share your experiences with dental treatments and procedures.',
        '2025-01-05 13:30:00', 5, 10),
       (16, 'General Discussion', 'A board for general dental topics and discussions', '2025-01-06 14:00:00', 1,
        6),
       (17, 'Treatment Tips', 'Share and discuss dental treatment tips and techniques', '2025-01-07 15:00:00', 2,
        7),
       (18, 'Dental Technology', 'Latest updates and discussions on dental technology', '2025-01-08 16:00:00', 3,
        8),
       (19, 'Practice Management', 'Talk about managing dental practices and offices', '2025-01-09 17:00:00', 4,
        9),
       (20, 'Patient Feedback', 'Patients share their experiences and feedback', '2025-01-10 18:00:00', 5,
        10);

-- 8. Populate the `comments` table with 5 records
INSERT INTO comments (id, content, created_date, post_id, user_id)
VALUES (1, 'Great post, very informative!', '2025-01-06 14:00:00', 1, 6),
       (2, 'I found these tips very useful.', '2025-01-07 15:00:00', 2, 7),
       (3, 'I love the new dental tools mentioned.', '2025-01-08 16:00:00', 3, 8),
       (4, 'These strategies have really helped my practice.', '2025-01-09 17:00:00', 4, 9),
       (5, 'Thank you for sharing your experience!', '2025-01-10 18:00:00', 5, 10);

-- 9. Populate the `appointments` table with 5 records
INSERT INTO appointments (id, time, status, dentist_id, patient_id)
VALUES (1, '2025-01-11 10:00:00', 'Scheduled', 1, 6),
       (2, '2025-01-12 11:00:00', 'Completed', 2, 7),
       (3, '2025-01-13 12:00:00', 'Cancelled', 3, 8),
       (4, '2025-01-14 13:00:00', 'Scheduled', 4, 9),
       (5, '2025-01-15 14:00:00', 'Scheduled', 5, 10);

-- 10. Populate the treatments table with 5 records (for patient treatments)
INSERT INTO treatments (id, title, description, status, created_date, patient_id, dentist_id)
VALUES (1, 'Routine Check-up', 'Annual routine check-up and cleaning.', 'Closed', '2025-02-01 09:00:00', 6, 1),
       (2, 'Cavity Filling', 'Filling a small cavity in a molar.', 'In Progress', '2025-02-02 09:30:00', 7, 2),
       (3, 'Wisdom Tooth Extraction', 'Extraction of an impacted wisdom tooth.', 'Open', '2025-02-03 10:00:00', 8, 3),
       (4, 'Root Canal Treatment', 'Root canal treatment on a lower incisor.', 'Closed', '2025-02-04 10:30:00', 9, 4),
       (5, 'Dental Implant Consultation', 'Consultation regarding a dental implant procedure.', 'In Progress',
        '2025-02-05 11:00:00', 10, 5);