DELIMITER //

-- Procedure to get all appointments for a specific dentist in a date range
CREATE PROCEDURE GetDentistAppointments(
    IN dentist_id_param INT,
    IN start_date_param DATETIME,
    IN end_date_param DATETIME
)
BEGIN
    SELECT a.id,
           a.time,
           a.status,
           u.username AS patient_name,
           u.phone    AS patient_phone
    FROM appointments a
             JOIN patients p ON a.patient_id = p.id
             JOIN users u ON p.id = u.id
    WHERE a.dentist_id = dentist_id_param
      AND a.time BETWEEN start_date_param AND end_date_param
    ORDER BY a.time;
END //

-- Procedure to get all treatments for a specific patient
CREATE PROCEDURE GetPatientTreatmentHistory(
    IN patient_id_param INT
)
BEGIN
    SELECT t.id,
           t.title,
           t.description,
           t.status,
           t.created_date,
           u.username AS dentist_name,
           d.specialization
    FROM treatments t
             JOIN dentists d ON t.dentist_id = d.id
             JOIN users u ON d.id = u.id
    WHERE t.patient_id = patient_id_param
    ORDER BY t.created_date DESC;
END //

-- Procedure to register a new patient
CREATE PROCEDURE RegisterNewPatient(
    IN username_param VARCHAR(255),
    IN email_param VARCHAR(255),
    IN phone_param VARCHAR(50),
    IN password_param VARCHAR(255),
    IN date_of_birth_param DATE,
    IN insurance_number_param VARCHAR(255),
    IN emergency_contact_param VARCHAR(255),
    OUT new_patient_id INT
)
BEGIN
    DECLARE new_user_id INT;

    -- Insert into users table
    INSERT INTO users (username, email, phone, password, user_type, date_of_birth)
    VALUES (username_param, email_param, phone_param, password_param, 'PATIENT', date_of_birth_param);

    -- Get the new user ID
    SET new_user_id = LAST_INSERT_ID();

    -- Insert into patients table
    INSERT INTO patients (id, insurance_number, emergency_contact)
    VALUES (new_user_id, insurance_number_param, emergency_contact_param);

    -- Return the new patient ID
    SET new_patient_id = new_user_id;
END //

-- Procedure to create a new appointment
CREATE PROCEDURE CreateAppointment(
    IN time_param DATETIME,
    IN dentist_id_param INT,
    IN patient_id_param INT,
    OUT new_appointment_id INT
)
BEGIN
    -- Insert into appointments table
    INSERT INTO appointments (time, status, dentist_id, patient_id)
    VALUES (time_param, 'Scheduled', dentist_id_param, patient_id_param);

    -- Return the new appointment ID
    SET new_appointment_id = LAST_INSERT_ID();
END //

-- Procedure to find available appointment slots for a dentist
CREATE PROCEDURE FindAvailableAppointmentSlots(
    IN dentist_id_param INT,
    IN date_param DATE
)
BEGIN
    -- Define working hours (9 AM to 5 PM with 30-minute intervals)
    WITH RECURSIVE time_slots AS (SELECT TIMESTAMP(date_param, '09:00:00') AS slot_time
                                  UNION ALL
                                  SELECT slot_time + INTERVAL 30 MINUTE
                                  FROM time_slots
                                  WHERE slot_time < TIMESTAMP(date_param, '16:30:00'))
    SELECT ts.slot_time
    FROM time_slots ts
             LEFT JOIN appointments a ON
        a.dentist_id = dentist_id_param AND
        a.time = ts.slot_time AND
        a.status != 'Cancelled'
    WHERE a.id IS NULL
    ORDER BY ts.slot_time;
END //

-- Procedure to post on a board and create association
CREATE PROCEDURE CreatePostOnBoard(
    IN title_param VARCHAR(255),
    IN content_param TEXT,
    IN board_id_param INT,
    IN user_id_param INT,
    OUT new_post_id INT
)
BEGIN
    -- Insert the post
    INSERT INTO posts (title, content, created_date, board_id, user_id)
    VALUES (title_param, content_param, NOW(), board_id_param, user_id_param);

    -- Get the new post ID
    SET new_post_id = LAST_INSERT_ID();

    -- Create the association in boards_posts
    INSERT INTO boards_posts (board_id, post_id)
    VALUES (board_id_param, new_post_id);
END //

-- Procedure to get all posts and comments for a specific board (FIXED)
CREATE PROCEDURE GetBoardContent(
    IN board_id_param INT
)
BEGIN
    -- Get all posts for the board
    SELECT p.id        AS post_id,
           p.title,
           p.content,
           p.created_date,
           u.username  AS author,
           COUNT(c.id) AS comment_count
    FROM posts p
             JOIN users u ON p.user_id = u.id
             LEFT JOIN comments c ON p.id = c.post_id
    WHERE p.board_id = board_id_param
    GROUP BY p.id, p.title, p.content, p.created_date, u.username
    ORDER BY p.created_date DESC;
END //

-- Procedure to get a dentist's schedule for a week
CREATE PROCEDURE GetDentistWeeklySchedule(
    IN dentist_id_param INT,
    IN week_start_date DATE
)
BEGIN
    DECLARE week_end_date DATE;
    SET week_end_date = DATE_ADD(week_start_date, INTERVAL 6 DAY);

    SELECT a.id,
           a.time,
           a.status,
           u.username                   AS patient_name,
           DATE_FORMAT(a.time, '%W')    AS day_of_week,
           DATE_FORMAT(a.time, '%H:%i') AS appointment_time
    FROM appointments a
             JOIN patients p ON a.patient_id = p.id
             JOIN users u ON p.id = u.id
    WHERE a.dentist_id = dentist_id_param
      AND DATE(a.time) BETWEEN week_start_date AND week_end_date
    ORDER BY a.time;
END //

-- Procedure to get statistics on boards activity
CREATE PROCEDURE GetBoardsStatistics()
BEGIN
    SELECT b.id,
           b.name,
           COUNT(DISTINCT bp.post_id) AS post_count,
           COUNT(DISTINCT c.id)       AS comment_count,
           COUNT(DISTINCT p.user_id)  AS active_users_count,
           MAX(p.created_date)        AS last_activity_date
    FROM boards b
             LEFT JOIN boards_posts bp ON b.id = bp.board_id
             LEFT JOIN posts p ON bp.post_id = p.id
             LEFT JOIN comments c ON p.id = c.post_id
    GROUP BY b.id
    ORDER BY post_count DESC;
END //

-- Procedure to transfer a patient between dentists (reassign treatments and appointments)
CREATE PROCEDURE TransferPatient(
    IN patient_id_param INT,
    IN old_dentist_id_param INT,
    IN new_dentist_id_param INT
)
BEGIN
    -- Update all treatments assigned to old dentist
    UPDATE treatments
    SET dentist_id = new_dentist_id_param
    WHERE patient_id = patient_id_param
      AND dentist_id = old_dentist_id_param
      AND status != 'Closed';

    -- Update all appointments assigned to old dentist
    UPDATE appointments
    SET dentist_id = new_dentist_id_param
    WHERE patient_id = patient_id_param
      AND dentist_id = old_dentist_id_param
      AND status = 'Scheduled';

    -- Log the transfer (you might want to create a transfer_logs table)
    SELECT CONCAT('Patient ', patient_id_param, ' transferred from dentist ',
                  old_dentist_id_param, ' to dentist ', new_dentist_id_param) AS transfer_log;
END //

DELIMITER ;