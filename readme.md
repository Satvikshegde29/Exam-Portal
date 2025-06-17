# Online Exam Portal
 
## Introduction
This document provides the Low-Level Design (LLD) for an Online Exam Portal aimed at streamlining the creation, management, and evaluation of online assessments. This design supports both Java (Spring Boot) for backend development.
 
## Module Overview
### 2.1 Admin Module
- Create and manage exams, questions, and user roles.
 
### 2.2 User Module
- User registration, login, and profile management.
 
### 2.3 Exam Management Module
- Attempt exams, view results, and provide feedback.
 
### 2.4 Question Bank Module
- Manage a repository of categorized questions.
 
### 2.5 Analytics and Reporting Module
- Generate performance reports and insights.
 
## Architecture Overview
### 3.1 Architectural Style
- Frontend: Angular or React
- Backend: REST API-based architecture
- Database: Relational Database (MySQL/PostgreSQL/SQL Server)
 
### 3.2 Component Interaction
- The frontend communicates with the backend through REST APIs for managing exams, users, and reports.
- The backend handles database operations and processes results and analytics.
 
## Module-Wise Design
### 4.1 Admin Module
**Features:**
- Create, update, delete exams and questions.
- Assign roles.

**Endpoints:**
- POST /api/admin/exams – Create exam
- PUT /api/admin/exams/{id} – Update exam
- DELETE /api/admin/exams/{id} – Delete exam
- POST /api/admin/questions – Add question
- PUT /api/admin/questions/{id} – Update question
- DELETE /api/admin/questions/{id} – Delete question
- POST /api/admin/users/assign-role – Assign role to user
 
### 4.2 User Module
**Features:**
- Register, login, and manage profile.

**Endpoints:**
- POST /api/auth/register – Register user
- POST /api/auth/login – Login user
- GET /api/users/profile – Get user profile
- PUT /api/users/profile – Update user profile
 
### 4.3 Exam Management Module
**Features:**
- Attempt exams, submit answers, view results.

**Endpoints:**
- GET /api/exams – List available exams
- GET /api/exams/{id}/questions – Get questions for an exam
- POST /api/exams/{id}/submit – Submit exam responses
- GET /api/results/{userId} – View results

 
### 4.4 Question Bank Module
**Features:**
- Manage categorized questions, import/export.

**Endpoints:**
- GET /api/questions – List all questions
- POST /api/questions/import – Import questions in bulk
- GET /api/questions/export – Export questions
 
### 4.5 Analytics and Reporting Module
**Features:**
- Generate and export performance reports.

**Endpoints:**
- GET /api/reports/user/{userId} – Get user performance report
- GET /api/reports/exam/{examId} – Get exam performance report
- GET /api/reports/export – Export reports

 
## Database Design
### 6.1 Tables and Relationships
1. Exam
  - Primary Key: ExamID
2. User
  - Primary Key: UserID
3. Question
  - Primary Key: QuestionID
4. Response
  - Primary Key: ResponseID
  - Foreign Keys: ExamID, UserID, QuestionID
5. Report
  - Primary Key: ReportID
  - Foreign Keys: ExamID, UserID
 
## User Interface Design
### 7.1 Wireframes:
- Admin Dashboard
- User Registration/Login Page
- Exam Attempt Page
- Results and Analytics Dashboard
 
 
## Project Structure
```plaintext
online-exam-portal-backend/
  └── src/
      └── main/
          ├── java/
          │   └── com/
          │       └── examportal/
          │           ├── controller/
          │           │   ├── AdminController.java
          │           │   ├── UserController.java
          │           │   ├── ExamController.java
          │           │   └── ReportController.java
          │           ├── service/
          │           │   ├── AdminService.java
          │           │   ├── UserService.java
          │           │   ├── ExamService.java
          │           │   └── ReportService.java
          │           ├── repository/
          │           │   ├── UserRepository.java
          │           │   ├── ExamRepository.java
          │           │   ├── QuestionRepository.java
          │           │   └── ReportRepository.java
          │           ├── model/
          │           │   ├── User.java
          │           │   ├── Exam.java
          │           │   ├── Question.java
          │           │   └── Report.java
          │           ├── config/
          │           │   ├── SecurityConfig.java
          │           │   └── WebConfig.java
          │           ├── exception/
          │           │   ├── GlobalExceptionHandler.java
          │           │   └── ResourceNotFoundException.java
          │           └── ExamPortalApplication.java
          └── resources/
              ├── application.properties
              └── data.sql
pom.xml
````
## 4.1 Admin Module (Low-Level Design)

### Overview
The Admin Module enables administrators to manage exams, questions, and user roles. It exposes REST APIs for CRUD operations on exams and questions, as well as for assigning roles to users.

### Main Components

- **Controller:** [`AdminController`](backend/src/main/java/com/examportal/controller/AdminController.java)
- **Service:** [`AdminService`](backend/src/main/java/com/examportal/service/AdminService.java), [`AdminServiceImpl`](backend/src/main/java/com/examportal/service/Impl/AdminServiceImpl.java)
- **Repositories:** [`ExamRepository`](backend/src/main/java/com/examportal/repository/ExamRepository.java), [`QuestionRepository`](backend/src/main/java/com/examportal/repository/QuestionRepository.java), [`UserRepository`](backend/src/main/java/com/examportal/repository/UserRepository.java)
- **Models:** [`Exam`](backend/src/main/java/com/examportal/model/Exam.java), [`Question`](backend/src/main/java/com/examportal/model/Question.java), [`User`](backend/src/main/java/com/examportal/model/User.java)

### Endpoints

| Method | Endpoint                              | Description                        | Access      |
|--------|---------------------------------------|------------------------------------|-------------|
| POST   | `/api/admin/exams`                    | Create a new exam                  | ADMIN only  |
| PUT    | `/api/admin/exams/{id}`               | Update an existing exam            | ADMIN only  |
| DELETE | `/api/admin/exams/{id}`               | Delete an exam                     | ADMIN only  |
| POST   | `/api/admin/questions`                | Add a new question                 | ADMIN only  |
| PUT    | `/api/admin/questions/{id}`           | Update a question                  | ADMIN only  |
| DELETE | `/api/admin/questions/{id}`           | Delete a question                  | ADMIN only  |
| PUT    | `/api/admin/users/{id}/role`          | Assign a role to a user            | ADMIN only  |
| PUT    | `/api/admin/exams/{examId}/questions` | Add questions to an existing exam  | ADMIN only  |

### Controller Logic

- **Exam Management:**  
  - Create, update, and delete exams using `ExamRepository`.
  - Assign an examiner and a set of questions to an exam.
- **Question Management:**  
  - CRUD operations on questions using `QuestionRepository`.
- **Role Assignment:**  
  - Assigns roles to users by updating the `role` field in the `User` entity.
- **Add Questions to Exam:**  
  - Allows batch addition of questions to an existing exam.

### Service Layer

- **AdminService** defines the contract for question management.
- **AdminServiceImpl** implements business logic for adding, updating, deleting, and retrieving questions.

### Security

- All endpoints are protected and require the user to have the `ROLE_ADMIN` authority.
- Security is enforced via Spring Security configuration in [`SecurityConfig`](backend/src/main/java/com/examportal/security/SecurityConfig.java).

### Data Model

- **Exam:**  
  - Fields: `examId`, `title`, `description`, `duration`, `totalMarks`, `examiner` (User), `questions` (List of Question)
- **Question:**  
  - Fields: `questionId`, `text`, `category`, `difficulty`, `correctAnswer`, etc.
- **User:**  
  - Fields: `userId`, `name`, `email`, `password`, `role`

### Example: Create Exam API

**Request:**  
`POST /api/admin/exams?examinerId=1&questionIds=2,3,4`  
**Body:**  
```json
{
  "title": "Java Basics",
  "description": "Test on Java fundamentals",
  "duration": 60,
  "totalMarks": 100
}
```
**Response:**  
Returns the created `Exam` object with assigned examiner and questions.

### Error Handling

- Uses [`GlobalExceptionHandler`](backend/src/main/java/com/examportal/exception/GlobalExceptionHandler.java) for consistent error responses.
- Throws `ResourceNotFoundException` if entities are not found.

---

**Note:**  
All business logic for the Admin Module is implemented in the backend under the `controller`, `service`, and `repository` packages as described above.


