-- =================================================================
-- Procedures for Dental Social Network Database
-- =================================================================

DELIMITER //

-- =================================================================
-- Procedure 1: Schedule New Appointment with Validation
-- Ensures data integrity and business rules when creating appointments
-- =================================================================
CREATE PROCEDURE ScheduleAppointment(
    IN dentist_id_param INT,
    IN patient_id_param INT,
    IN appointment_time DATETIME,
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE existing_appointment INT;
    DECLARE is_valid_dentist BOOLEAN;
    DECLARE is_valid_patient BOOLEAN;
    
    -- Start transaction for data consistency
    START TRANSACTION;
    
    -- Check if dentist exists and is actually a dentist
    SELECT COUNT(*) INTO is_valid_dentist 
    FROM dentists d
    JOIN users u ON d.id = u.id
    WHERE d.id = dentist_id_param AND u.user_type = 'DENTIST';
    
    -- Check if patient exists and is actually a patient
    SELECT COUNT(*) INTO is_valid_patient
    FROM patients p
    JOIN users u ON p.id = u.id
    WHERE p.id = patient_id_param AND u.user_type = 'PATIENT';
    
    -- Check for existing appointments at the same time
    SELECT COUNT(*) INTO existing_appointment
    FROM appointments 
    WHERE dentist_id = dentist_id_param 
    AND time = appointment_time
    AND status != 'cancelled';
    
    -- Validate appointment time (must be during business hours and not on weekends)
    IF NOT (HOUR(appointment_time) BETWEEN 9 AND 17) 
    OR WEEKDAY(appointment_time) > 4 THEN
        SET success = FALSE;
        SET message = 'Invalid appointment time. Must be between 9 AM and 5 PM on weekdays.';
        ROLLBACK;
    -- Validate dentist
    ELSEIF NOT is_valid_dentist THEN
        SET success = FALSE;
        SET message = 'Invalid dentist ID or user is not a dentist.';
        ROLLBACK;
    -- Validate patient
    ELSEIF NOT is_valid_patient THEN
        SET success = FALSE;
        SET message = 'Invalid patient ID or user is not a patient.';
        ROLLBACK;
    -- Check for conflicts
    ELSEIF existing_appointment > 0 THEN
        SET success = FALSE;
        SET message = 'Time slot is already booked.';
        ROLLBACK;
    ELSE
        -- All validations passed, create the appointment
        INSERT INTO appointments (time, status, dentist_id, patient_id)
        VALUES (appointment_time, 'scheduled', dentist_id_param, patient_id_param);
        
        SET success = TRUE;
        SET message = 'Appointment scheduled successfully.';
        COMMIT;
    END IF;
END //

-- =================================================================
-- Procedure 2: Complete Treatment with History Update
-- Manages treatment completion and maintains treatment history
-- =================================================================
CREATE PROCEDURE CompleteTreatment(
    IN treatment_id_param INT,
    IN completion_notes TEXT,
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE current_status VARCHAR(50);
    
    START TRANSACTION;
    
    -- Get current treatment status
    SELECT status INTO current_status
    FROM treatments
    WHERE id = treatment_id_param;
    
    -- Validate treatment exists and is ongoing
    IF current_status IS NULL THEN
        SET success = FALSE;
        SET message = 'Treatment not found.';
        ROLLBACK;
    ELSEIF current_status = 'completed' THEN
        SET success = FALSE;
        SET message = 'Treatment is already completed.';
        ROLLBACK;
    ELSE
        -- Update treatment status and add completion notes
        UPDATE treatments 
        SET status = 'completed',
            description = CONCAT(description, '\n\nCompletion Notes: ', completion_notes)
        WHERE id = treatment_id_param;
        
        -- Create follow-up appointment if needed (example: check-up after 6 months)
        INSERT INTO appointments (time, status, dentist_id, patient_id)
        SELECT 
            DATE_ADD(NOW(), INTERVAL 6 MONTH), -- Follow-up after 6 months
            'scheduled',
            dentist_id,
            patient_id
        FROM treatments
        WHERE id = treatment_id_param;
        
        SET success = TRUE;
        SET message = 'Treatment completed and follow-up appointment scheduled.';
        COMMIT;
    END IF;
END //

DELIMITER ;