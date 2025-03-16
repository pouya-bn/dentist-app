// MongoDB Playground - Indexing File
// Creating and testing indexes for performance optimization

use("dentist_db");

// List existing indexes
db.comments.getIndexes();

// Create indexes for common query patterns
// 1. Index for queries by post_id (to get all comments for a post)
db.comments.createIndex({post_id: 1});

// 2. Index for queries by user_id (to get all comments by a user)
db.comments.createIndex({user_id: 1});

// 3. Index for sorting by creation date (newest first)
db.comments.createIndex({created_date: -1});

// 4. Compound index for finding comments by post and sorted by date
db.comments.createIndex({post_id: 1, created_date: -1});

// 5. Index for text search on comment content
db.comments.createIndex({content: "text"});

// 6. Index for finding comments with specific tags
db.comments.createIndex({tags: 1});

// Verify all indexes have been created
db.comments.getIndexes();

// Test query execution with indexes
// Get execution statistics for a query by post_id
db.comments.find({post_id: 2}).explain("executionStats");

// Get execution statistics for a query by user_id
db.comments.find({user_id: 10}).explain("executionStats");

// Get execution statistics for sorting by date
db.comments.find().sort({created_date: -1}).explain("executionStats");

// Get execution statistics for compound query (post_id + date sort)
db.comments.find({post_id: 4}).sort({created_date: -1}).explain("executionStats");

// Test text search capability
db.comments.find({$text: {$search: "insurance"}}).explain("executionStats");

// Test tag search
db.comments.find({tags: "technology"}).explain("executionStats");
