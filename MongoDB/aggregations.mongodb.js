// MongoDB Playground - Aggregation File
// Advanced queries and aggregations for the comments collection

use("dentist_db");

// Basic aggregation: Count number of comments per post
db.comments.aggregate([
    {$group: {_id: "$post_id", commentCount: {$sum: 1}}},
    {$sort: {commentCount: -1}}
]);

// Find most active users (most comments)
db.comments.aggregate([
    {
        $group: {
            _id: "$user_id",
            username: {$first: "$username"},
            commentCount: {$sum: 1}
        }
    },
    {$sort: {commentCount: -1}},
    {
        $project: {
            _id: 0,
            user_id: "$_id",
            username: 1,
            commentCount: 1
        }
    }
]);

// Calculate average likes per comment for each post
db.comments.aggregate([
    {
        $group: {
            _id: "$post_id",
            avgLikes: {$avg: "$likes"},
            totalComments: {$sum: 1}
        }
    },
    {$sort: {avgLikes: -1}}
]);

// Find posts with the most replies to comments
db.comments.aggregate([
    {$match: {"replies.0": {$exists: true}}},
    {
        $project: {
            post_id: 1,
            replyCount: {$size: "$replies"}
        }
    },
    {
        $group: {
            _id: "$post_id",
            totalReplies: {$sum: "$replyCount"}
        }
    },
    {$sort: {totalReplies: -1}}
]);

// Find comments with specific tags
db.comments.aggregate([
    {$match: {tags: {$exists: true}}},
    {$unwind: "$tags"},
    {
        $group: {
            _id: "$tags",
            count: {$sum: 1}
        }
    },
    {$sort: {count: -1}}
]);

// Analyze comment sentiment distribution
db.comments.aggregate([
    {$match: {sentiment: {$exists: true}}},
    {
        $group: {
            _id: "$sentiment",
            count: {$sum: 1}
        }
    },
    {$sort: {count: -1}}
]);

// Find all comments made in the last 7 days (assuming current date is Jan 16, 2025)
let lastWeek = new Date("2025-01-09T00:00:00Z");
db.comments.aggregate([
    {$match: {created_date: {$gte: lastWeek}}},
    {$sort: {created_date: -1}}
]);

// Complex aggregation: Analyze user engagement by day of week
db.comments.aggregate([
    {
        $project: {
            dayOfWeek: {$dayOfWeek: "$created_date"},
            user_id: 1,
            likes: 1
        }
    },
    {
        $group: {
            _id: "$dayOfWeek",
            commentCount: {$sum: 1},
            avgLikes: {$avg: "$likes"},
            uniqueUsers: {$addToSet: "$user_id"}
        }
    },
    {
        $project: {
            _id: 0,
            dayOfWeek: "$_id",
            commentCount: 1,
            avgLikes: 1,
            uniqueUserCount: {$size: "$uniqueUsers"}
        }
    },
    {$sort: {dayOfWeek: 1}}
]);
