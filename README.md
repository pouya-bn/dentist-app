# Dental Social Network Application

This application is a backend solution for the dentist community. Built with Spring Boot, RESTful APIs, MySQL, and MongoDB, it allows dentists to manage patient data, schedule appointments, and maintain treatment cases. The platform also features a community board for dentists and patients to engage in discussions through posts and comments.

## Project Structure

- **SpringBoot**: Contains the Java source code for the Spring Boot application, including controllers, services, repositories, and models, as well as configuration files needed to run the application.
- **MySql**: Includes SQL scripts for creating the MySQL database schema, populating it with sample data, updating and deleting the data, creating indexes, stored procedures and triggers, as well as excecuting queries.
- **MongoDB**: Contains scripts and configurations for setting up the MongoDB database, including the creation of collections, insertion of sample data, aggregations and creating indexes to improve performance.

```
├── SpringBoot/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── pouya/
│   │   │   │           └── dentist/
│   │   │   │               ├── configs/
│   │   │   │               ├── controllers/
│   │   │   │               ├── exceptions/
│   │   │   │               ├── models/
│   │   │   │               ├── repositories/
│   │   │   │               ├── services/
│   │   │   │               └── DentistApplication.java
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   └── pom.xml
│
├── MySQL/
│   ├── creations.sql
│   ├── deletions.sql
│   ├── indexes.sql
│   ├── insertions.sql
│   ├── procedures.sql
│   ├── triggers.sql
│   └── updates.sql
│
├── MongoDB/
│   ├── aggregations.mongodb.js
│   ├── creations.mongodb.js
│   └── indexing.mongodb.js
│
└── README.md
```

## Technologies Used

- **Backend**: Spring Boot
- **Build Tool**: Maven
- **Databases**:
  - MySQL - For structured data like appointments, patients, and dentists
  - MongoDB - For semi-structured data like comments

## Prerequisites

- JDK
- MySQL
- MongoDB
- Git

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/pouya-bn/dentist-app.git
cd dentist-app
```

### 2. Configure MySQL

1. Connect to MySQL: Use the MySQL command-line tool and run this command: `mysql -u root -p` and enter the root password when prompted.
2. Navigate to MySQL directory and run the files in the following order:
   - `creations.sql` (Tables)
   - `indexes.sql` (Indexes)
   - `procedures.sql` (Stored Procedures)
   - `triggers.sql` (Triggers)
   - `insertions.sql` (Initial Data)


### 3. Configure MongoDB

1. Connect to MongoDB: Use the MongoDB Shell and run the `mongosh` command in your terminal.
2. Navigate to MongoDB directory and run the files in the following order:
   - `creations.mongodb.js` (Collections and Schema)
   - `indexing.mongodb.js` (Indexes)
   - `aggregations.mongodb.js` (Aggregations)


### 4. Configure Spring Boot Application

Navigate to Spring Boot directory located at `src/main/resources/application.yml` and edit `application.yml` to add your information.


### 5. Build the Application

```bash
# From the SpringBoot directory
mvn clean install
```

### 6. Run the Application

```bash
mvn spring-boot:run
```

The application should now be running at `http://localhost:8080`

## Project URL

- Project URL: [https://github.com/pouya-bn/dentist-app](https://github.com/pouya-bn/dentist-app)
