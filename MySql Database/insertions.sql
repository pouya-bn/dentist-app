INSERT INTO users (username, email, phone, password, user_type, date_of_birth)
VALUES ('johndoe', 'johndoe@example.com', '1234567890', 'password123', 'DENTIST', '1970-01-01'),
       ('maryjohnson', 'maryjohnson@example.com', '1234567891', 'password123', 'DENTIST', '1971-02-02'),
       ('robertsmith', 'robertsmith@example.com', '1234567892', 'password123', 'DENTIST', '1972-03-03'),
       ('lisajones', 'lisajones@example.com', '1234567893', 'password123', 'DENTIST', '1973-04-04'),
       ('davidbrown', 'davidbrown@example.com', '1234567894', 'password123', 'DENTIST', '1974-05-05'),
       ('sarahdavis', 'sarahdavis@example.com', '1234567895', 'password123', 'DENTIST', '1975-06-06'),
       ('michaelwilson', 'michaelwilson@example.com', '1234567896', 'password123', 'DENTIST', '1976-07-07'),
       ('emilytaylor', 'emilytaylor@example.com', '1234567897', 'password123', 'DENTIST', '1977-08-08'),
       ('jamesmiller', 'jamesmiller@example.com', '1234567898', 'password123', 'DENTIST', '1978-09-09'),
       ('annethomas', 'annethomas@example.com', '1234567899', 'password123', 'DENTIST', '1979-10-10'),
       ('susanwhite', 'susanwhite@example.com', '1234567800', 'password123', 'PATIENT', '1990-01-01'),
       ('thomaslee', 'thomaslee@example.com', '1234567801', 'password123', 'PATIENT', '1991-02-02'),
       ('patriciaclark', 'patriciaclark@example.com', '1234567802', 'password123', 'PATIENT', '1992-03-03'),
       ('williamhall', 'williamhall@example.com', '1234567803', 'password123', 'PATIENT', '1993-04-04'),
       ('jenniferadams', 'jenniferadams@example.com', '1234567804', 'password123', 'PATIENT', '1994-05-05'),
       ('chrisking', 'chrisking@example.com', '1234567805', 'password123', 'PATIENT', '1995-06-06'),
       ('lindawright', 'lindawright@example.com', '1234567806', 'password123', 'PATIENT', '1996-07-07'),
       ('danielgreen', 'danielgreen@example.com', '1234567807', 'password123', 'PATIENT', '1997-08-08'),
       ('nancyturner', 'nancyturner@example.com', '1234567808', 'password123', 'PATIENT', '1998-09-09'),
       ('kevinharris', 'kevinharris@example.com', '1234567809', 'password123', 'PATIENT', '1999-10-10');

INSERT INTO dentists (id, license_number, specialization)
VALUES (1, 'LIC001', 'General Dentistry'),
       (2, 'LIC002', 'Orthodontics'),
       (3, 'LIC003', 'Pediatric Dentistry'),
       (4, 'LIC004', 'Periodontics'),
       (5, 'LIC005', 'Endodontics'),
       (6, 'LIC006', 'Oral Surgery'),
       (7, 'LIC007', 'Prosthodontics'),
       (8, 'LIC008', 'Cosmetic Dentistry'),
       (9, 'LIC009', 'Implantology'),
       (10, 'LIC010', 'Geriatric Dentistry');

INSERT INTO patients (id, insurance_number, emergency_contact)
VALUES (11, 'INS001', '555-123-4567'),
       (12, 'INS002', '555-234-5678'),
       (13, 'INS003', '555-345-6789'),
       (14, 'INS004', '555-456-7890'),
       (15, 'INS005', '555-567-8901'),
       (16, 'INS006', '555-678-9012'),
       (17, 'INS007', '555-789-0123'),
       (18, 'INS008', '555-890-1234'),
       (19, 'INS009', '555-901-2345'),
       (20, 'INS010', '555-012-3456');

INSERT INTO appointments (time, status, dentist_id, patient_id)
VALUES ('2025-02-01 09:00:00', 'scheduled', 1, 11),
       ('2025-02-01 10:00:00', 'completed', 1, 12),
       ('2025-02-02 09:00:00', 'cancelled', 2, 11),
       ('2025-02-02 10:00:00', 'completed', 2, 13),
       ('2025-02-03 09:00:00', 'rescheduled', 3, 12),
       ('2025-02-03 10:00:00', 'scheduled', 3, 14),
       ('2025-02-04 09:00:00', 'completed', 4, 13),
       ('2025-02-04 10:00:00', 'cancelled', 4, 15),
       ('2025-02-05 09:00:00', 'completed', 5, 14),
       ('2025-02-05 10:00:00', 'rescheduled', 5, 15);

INSERT INTO boards (name, description)
VALUES ('General Discussion', 'A place to discuss general topics related to dentistry.'),
       ('Oral Hygiene Tips', 'Share and learn about maintaining good oral hygiene.'),
       ('Cosmetic Dentistry', 'Discuss cosmetic procedures like teeth whitening, veneers, etc.'),
       ('Pediatric Dentistry', 'For topics related to children''s dental health.'),
       ('Orthodontics', 'Discuss braces, aligners, and other orthodontic treatments.'),
       ('Periodontics', 'For gum health and periodontal treatments.'),
       ('Endodontics', 'Discuss root canal treatments and other endodontic procedures.'),
       ('Patient Feedback', 'Share your experiences and feedback about our services.'),
       ('Appointment Scheduling', 'Discuss appointment-related queries and issues.'),
       ('Dental News', 'Latest news and updates in the field of dentistry.');

INSERT INTO posts (title, content, created_date, board_id, user_id)
VALUES ('Welcome to the forum', 'Hello everyone, welcome to our dental forum.', '2025-01-01 10:00:00', 1, 1),
       ('How to brush properly', 'Here are some tips on proper brushing techniques.', '2025-01-02 11:00:00', 2, 2),
       ('My experience with teeth whitening', 'I recently had my teeth whitened and here''s what I think.',
        '2025-01-03 12:00:00', 3, 11),
       ('Tips for kids'' dental care', 'Here are some ways to make dental care fun for children.',
        '2025-01-04 13:00:00', 4, 3),
       ('Braces vs Aligners', 'Discussing the pros and cons of braces and aligners.', '2025-01-05 14:00:00', 5, 2),
       ('Gum health importance', 'Why maintaining gum health is crucial.', '2025-01-06 15:00:00', 6, 4),
       ('Root canal myths', 'Debunking common myths about root canal treatments.', '2025-01-07 16:00:00', 7, 5),
       ('Great service!', 'I had a wonderful experience at the clinic.', '2025-01-08 17:00:00', 8, 12),
       ('Appointment availability', 'Are there any slots available next week?', '2025-01-09 18:00:00', 9,
        13),
       ('New dental technology', 'Exciting advancements in dental tech.', '2025-01-10 19:00:00', 10, 1);

INSERT INTO boards_users (board_id, user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 11),
       (7, 12),
       (8, 13),
       (9, 14),
       (10, 15);


INSERT INTO comments (content, created_date, post_id, user_id)
VALUES ('Great to be here!', '2025-01-01 11:00:00', 1, 2),
       ('Looking forward to learning more.', '2025-01-01 12:00:00', 1, 3),
       ('Thanks for the tips!', '2025-01-02 12:00:00', 2, 1),
       ('I''m considering teeth whitening too.', '2025-01-03 13:00:00', 3, 4),
       ('These tips are helpful for my kids.', '2025-01-04 14:00:00', 4, 5),
       ('I have aligners and they work great.', '2025-01-05 15:00:00', 5, 11),
       ('Gum health is so important.', '2025-01-06 16:00:00', 6, 12),
       ('I was scared of root canals but now I understand better.', '2025-01-07 17:00:00', 7, 13),
       ('I agree, the service is excellent.', '2025-01-08 18:00:00', 8, 14),
       ('I think there are slots on Tuesday.', '2025-01-09 19:00:00', 9, 15);

INSERT INTO treatments (title, description, status, created_date, patient_id, dentist_id)
VALUES ('Routine Checkup', 'Regular dental checkup', 'completed', '2025-01-15 00:00:00', 11, 1),
       ('Teeth Cleaning', 'Professional cleaning', 'completed', '2025-01-16 00:00:00', 12, 1),
       ('Filling', 'Cavity filling', 'completed', '2025-01-17 00:00:00', 11, 2),
       ('Root Canal', 'Root canal treatment', 'ongoing', '2025-01-18 00:00:00', 13, 2),
       ('Braces Adjustment', 'Adjusting orthodontic braces', 'ongoing', '2025-01-19 00:00:00', 12, 3),
       ('Gum Treatment', 'Treatment for gum disease', 'completed', '2025-01-20 00:00:00', 14, 3),
       ('Tooth Extraction', 'Extracting a decayed tooth', 'completed', '2025-01-21 00:00:00', 13, 4),
       ('Dental Implant', 'Placing a dental implant', 'ongoing', '2025-01-22 00:00:00', 15, 4),
       ('Whitening', 'Teeth whitening procedure', 'completed', '2025-01-23 00:00:00', 14, 5),
       ('Crown Placement', 'Placing a dental crown', 'ongoing', '2025-01-24 00:00:00', 15, 5);