-- =================================================================
-- Triggers for Dental Social Network Database
-- =================================================================

DELIMITER //

-- =================================================================
-- Update Treatment Status on Appointment Completion
-- When an appointment status is changed to 'completed',
-- this trigger automatically updates any associated treatments
-- =================================================================
CREATE TRIGGER after_appointment_update
    AFTER UPDATE
    ON appointments
    FOR EACH ROW
BEGIN
    -- If appointment status changed to 'completed'
    IF NEW.status = 'completed' AND OLD.status != 'completed' THEN
        -- Update any ongoing treatments for this dentist-patient pair to 'completed'
        UPDATE treatments
        SET status = 'completed'
        WHERE dentist_id = NEW.dentist_id
          AND patient_id = NEW.patient_id
          AND status = 'ongoing';
    END IF;
END//

DELIMITER ;