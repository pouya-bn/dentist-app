-- Delete a specific user (will cascade to delete associated dentist/patient record)
DELETE
FROM users
WHERE id = 1;

-- Delete posts older than a specific date
DELETE
FROM posts
WHERE created_date < '2025-01-02';

-- Delete appointments with a specific status
DELETE
FROM appointments
WHERE status = 'Cancelled';

-- Delete comments from a specific post
DELETE
FROM comments
WHERE post_id = 5;

-- Delete a specific treatment
DELETE
FROM treatments
WHERE id = 3;

-- Delete all treatments for a specific patient
DELETE
FROM treatments
WHERE patient_id = 8;

-- Delete a board and all associated content (leverages cascading)
DELETE
FROM boards
WHERE id = 5;

-- Delete users who haven't made any posts or comments
DELETE
FROM users
WHERE id NOT IN (SELECT DISTINCT user_id FROM posts)
  AND id NOT IN (SELECT DISTINCT user_id FROM comments);

-- Delete all memberships from a specific board
DELETE
FROM boards_users
WHERE board_id = 4;

-- Delete associations between posts and boards for inactive posts
DELETE
FROM boards_posts
WHERE post_id IN (SELECT id FROM posts WHERE created_date < '2025-01-03');