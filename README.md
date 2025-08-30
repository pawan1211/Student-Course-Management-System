

🎓 Student-Course-Enrollment Management System

A Spring Boot 3+ Application that manages Students, Courses, and Enrollments with Relational Database (JPA/Hibernate), JWT Authentication, and Role-Based Authorization.
This project demonstrates a real-world medium-level Spring Boot application with multiple layers and security features.

📂 Project Structure
com.example.Student_Course.Management.System
│── controller       # REST Controllers (Student, Course, Enrollment, Auth)
│── data             # DTOs (Data Transfer Objects)
│── exception        # Global exception handling & custom exceptions
│── service          # Business logic for Student, Course, Enrollment
│── repo             # JPA Repositories (DB access)
│── entity           # JPA Entities (Student, Course, Enrollment, User, Role)
│── security         # JWT Security, Filters, Config
│── Application.java # Main Spring Boot entry point

🚀 Features

✅ CRUD for Students, Courses, and Enrollments
✅ Search students by name
✅ Get courses by category
✅ Filter enrollments by semester
✅ Relational Database (One-to-Many, Many-to-One with JPA)
✅ DTO-based clean API responses
✅ Global Exception Handling with @ControllerAdvice
✅ JWT Authentication & Authorization
✅ Role-Based Access Control (RBAC)

ROLE_ADMIN → Full access to /students, /courses, /enrollments

ROLE_USER → Limited access (e.g., view courses, enrollments)
✅ Postman Collection for API testing

⚙️ Tech Stack

Java 17+

Spring Boot 3+

Spring Security + JWT

Spring Data JPA / Hibernate

PostgreSQL / MySQL (configurable)

Lombok

Postman (for testing APIs)

🔑 Authentication & Security

The system uses JWT Tokens for authentication.

🔹 Generate Token (Login)
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}


Response:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}

🔹 Use Token in API Requests

Add token in Postman Headers:

Authorization: Bearer <your_token>

🔹 Role-Based Authorization

ROLE_ADMIN → Can manage everything (CRUD on students, courses, enrollments)

ROLE_USER → Can view courses & enrollments, but cannot create students

📌 API Endpoints
🔐 Auth

POST /auth/register → Register a new user (Admin/User role)

POST /auth/login → Authenticate & get JWT token

👨‍🎓 Student APIs

GET /students → Get all students (ADMIN only)

GET /students/{id} → Get student by ID

POST /students → Add student (ADMIN only)

PUT /students/{id} → Update student (ADMIN only)

DELETE /students/{id} → Delete student (ADMIN only)

📚 Course APIs

GET /courses → Get all courses (ADMIN/USER)

POST /courses → Add new course (ADMIN only)

GET /courses/category/{category} → Get courses by category

📝 Enrollment APIs

GET /enrollments → Get all enrollments (ADMIN only)

POST /enrollments → Enroll a student in a course (ADMIN/USER)

GET /enrollments/semester/{semester} → Filter enrollments by semester

🗄 Database Configuration

src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

▶️ Running the Application
# Clone repo
git clone https://github.com/your-username/student-course-enrollment.git
cd student-course-enrollment

# Run application
mvn spring-boot:run


Application runs at:
👉 http://localhost:8080

📬 Postman Usage

Login → POST /auth/login → Get token

Set Header → Authorization: Bearer <token>

Access APIs (students, courses, enrollments)

👉 Import provided Postman Collection (student-course.postman_collection.json)

🛡️ Example Role-Based Access
Endpoint	ROLE_ADMIN	ROLE_USER
GET /students	✅	❌
POST /students	✅	❌
GET /courses	✅	✅
POST /courses	✅	❌
GET /enrollments	✅	✅ (limited)
📖 Future Enhancements

🔹 Add Pagination & Sorting for Students & Courses
🔹 Add Email Notification on Enrollment
🔹 Add Swagger/OpenAPI Documentation
🔹 Add Docker Support
