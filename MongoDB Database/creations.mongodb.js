// MongoDB Playground - Creation File
// Creation of comments collection and populating with sample data

// Switch to dentist_db (create if it doesn't exist)
use("dentist_db");

// Drop existing collection for a clean start
db.comments.drop();

// Create new comments collection
db.createCollection("comments");

// Insert 10 sample comments with auto-generated IDs
db.comments.insertMany([
    {
        content: "Great post, very informative!",
        created_date: new Date("2025-01-06T14:00:00Z"),
        post_id: 1,
        user_id: 6,
        username: "alicewilliams",
        likes: 5,
        replies: []
    },
    {
        content: "I found these tips very useful.",
        created_date: new Date("2025-01-07T15:00:00Z"),
        post_id: 2,
        user_id: 7,
        username: "bobjones",
        likes: 3,
        replies: [
            {
                reply_id: 1,
                content: "Glad you found it helpful!",
                created_date: new Date("2025-01-07T16:30:00Z"),
                user_id: 2,
                username: "emilydavis"
            }
        ]
    },
    {
        content: "I love the new dental tools mentioned.",
        created_date: new Date("2025-01-08T16:00:00Z"),
        post_id: 3,
        user_id: 8,
        username: "caroltaylor",
        likes: 7,
        tags: ["innovation", "technology"]
    },
    {
        content: "These strategies have really helped my practice.",
        created_date: new Date("2025-01-09T17:00:00Z"),
        post_id: 4,
        user_id: 9,
        username: "davidmiller",
        likes: 10,
        sentiment: "positive"
    },
    {
        content: "Thank you for sharing your experience!",
        created_date: new Date("2025-01-10T18:00:00Z"),
        post_id: 5,
        user_id: 10,
        username: "emmawilson",
        likes: 4,
        replies: []
    },
    {
        content: "I had a similar dental issue last year.",
        created_date: new Date("2025-01-11T10:15:00Z"),
        post_id: 5,
        user_id: 6,
        username: "alicewilliams",
        likes: 2,
        attachments: [
            {
                type: "image",
                description: "X-ray image"
            }
        ]
    },
    {
        content: "Could you share more details about this procedure?",
        created_date: new Date("2025-01-12T13:45:00Z"),
        post_id: 2,
        user_id: 8,
        username: "caroltaylor",
        likes: 1,
        tags: ["questions", "procedures"]
    },
    {
        content: "I'm scheduling an appointment for this next week.",
        created_date: new Date("2025-01-13T09:30:00Z"),
        post_id: 3,
        user_id: 9,
        username: "davidmiller",
        likes: 0,
        replies: []
    },
    {
        content: "This treatment changed my life! Highly recommend.",
        created_date: new Date("2025-01-14T14:20:00Z"),
        post_id: 4,
        user_id: 10,
        username: "emmawilson",
        likes: 15,
        sentiment: "very positive",
        tags: ["success story", "recommendation"]
    },
    {
        content: "Does insurance typically cover this procedure?",
        created_date: new Date("2025-01-15T11:05:00Z"),
        post_id: 1,
        user_id: 7,
        username: "bobjones",
        likes: 6,
        replies: [
            {
                reply_id: 1,
                content: "Most insurance plans do cover it, but you should check with your provider.",
                created_date: new Date("2025-01-15T14:30:00Z"),
                user_id: 1,
                username: "johnsmith"
            },
            {
                reply_id: 2,
                content: "Mine covered 80% of the cost.",
                created_date: new Date("2025-01-15T15:45:00Z"),
                user_id: 6,
                username: "alicewilliams"
            }
        ]
    }
]);


// Verify that comments were inserted
db.comments.find();

// Insert a new comment
db.comments.insertOne({
    content: "I just had this procedure done and it was painless!",
    created_date: new Date(),
    post_id: 3,
    user_id: 6,
    username: "alicewilliams",
    likes: 0,
    replies: []
});

// Find all comments for post 3
db.comments.find({post_id: 3});

// Add a like to the newest comment (increment likes by 1)
db.comments.updateOne(
    {post_id: 3, user_id: 6},
    {$inc: {likes: 1}}
);

// Remove a specific comment
db.comments.deleteOne({_id: ObjectId("67d596424486f8e7280c6e34")});

// Count total documents
db.comments.countDocuments();