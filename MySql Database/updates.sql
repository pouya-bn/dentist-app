-- Update user information
UPDATE users
SET email = 'john.smith.dds@dentalcare.com',
    phone = '5551238888'
WHERE id = 1;

-- Update dentist specialization
UPDATE dentists
SET specialization = 'Cosmetic Dentistry'
WHERE id = 1;

-- Update patient emergency contact information
UPDATE patients
SET emergency_contact = 'Laura Williams: 5551113333'
WHERE id = 6;

-- Update appointment status
UPDATE appointments
SET status = 'Rescheduled',
    time   = '2025-01-14 15:30:00'
WHERE id = 1;

-- Update treatment status
UPDATE treatments
SET status      = 'Completed',
    description = CONCAT(description, ' Follow-up scheduled.')
WHERE id = 2;

-- Update board description
UPDATE boards
SET description = 'A forum for discussing general dental topics and patient education'
WHERE id = 1;

-- Update post content
UPDATE posts
SET content = 'Updated content with latest dental research and findings.',
    title   = 'Latest Research in Dental Care'
WHERE id = 1;

-- Update multiple users' passwords (for example, after a security policy change)
UPDATE users
SET password = CONCAT(password, '_updated')
WHERE user_type = 'DENTIST';

-- Move posts to a different board
UPDATE posts
SET board_id = 3
WHERE board_id = 2
  AND created_date < '2025-01-05';

-- Update comment content
UPDATE comments
SET content = 'This information has been very helpful for my dental care routine!'
WHERE id = 1;