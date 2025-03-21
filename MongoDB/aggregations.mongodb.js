// =================================================================
// MongoDB - Aggregation File
// Advanced queries and aggregations for the comments collection
// =================================================================

use("dentist_db");

// Count number of comments per post
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

// Find all comments made in the last 7 days
let lastWeek = new Date();
lastWeek.setDate(lastWeek.getDate() - 7);
db.comments.aggregate([
    {$match: {created_date: {$gte: lastWeek}}},
    {$sort: {created_date: -1}}
]);

// Analyze user engagement by day of week
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

// Find trending topics based on tags and likes
db.comments.aggregate([
    {$match: {tags: {$exists: true}}},
    {$unwind: "$tags"},
    {
        $group: {
            _id: "$tags",
            totalComments: {$sum: 1},
            totalLikes: {$sum: "$likes"},
            avgLikes: {$avg: "$likes"}
        }
    },
    {
        $addFields: {
            engagementScore: {
                $add: [
                    "$totalComments",
                    {$multiply: ["$avgLikes", 2]}
                ]
            }
        }
    },
    {$sort: {engagementScore: -1}}
]);