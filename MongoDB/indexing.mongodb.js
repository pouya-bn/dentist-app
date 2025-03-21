// =================================================================
// MongoDB - Indexing File
// Creating and testing indexes for performance optimization
// =================================================================

use("dentist_db");

// Drop all existing indexes (except _id)
db.comments.dropIndexes();

// 1. Compound index for post listing and sorting (covers most common query pattern)
db.comments.createIndex({post_id: 1, created_date: -1});

// 2. Compound index for user activity tracking
db.comments.createIndex({user_id: 1, created_date: -1});

// 3. Text index for content search (limit to one text index per collection)
db.comments.createIndex({content: "text"});

// Verify indexes
db.comments.getIndexes();

// Example queries to demonstrate index usage
// Test post listing with date sort (will use compound index)
db.comments.find({post_id: 2}).sort({created_date: -1}).explain("executionStats");

// Test user activity query (will use compound index)
db.comments.find({user_id: 10}).sort({created_date: -1}).explain("executionStats");

// Test full-text search
db.comments.find({$text: {$search: "insurance"}}).explain("executionStats");
