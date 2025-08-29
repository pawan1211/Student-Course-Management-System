


# 🎓 Student-Course-Enrollment Management System

A **Spring Boot Application** that manages Students, Courses, and Enrollments with **Relational Database (JPA/Hibernate)** and **REST APIs**.  
This project demonstrates a **medium-level Spring Boot application** with multiple layers:
- Controller
- Service
- Repository
- Exception Handling
- DTOs for clean API responses  

---

## 📦 Project Structure

com.example.Student_Course.Management.System
│── controller # REST Controllers for Student, Course, Enrollment
│── data # DTOs (Data Transfer Objects)
│── exception # Global exception handling & custom exceptions
│── service # Business logic for Student, Course, Enrollment
│── repo # JPA Repositories (DB access)
│── entity # JPA Entities (Student, Course, Enrollment)
│── Application.java # Main Spring Boot entry point



---

## 🛠 Features
- CRUD for **Students**, **Courses**, and **Enrollments**
- Search students by **name**
- Get courses by **category**
- Filter enrollments by **semester**
- **Relational Database** (One-to-Many, Many-to-One with JPA)
- **REST API** based microservice-style architecture
- DTO-based clean API responses
- Global Exception Handling with `@ControllerAdvice`
- Easily extensible with **pagination & sorting**

---

## ⚙️ Tech Stack
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL / MySQL** (can configure in `application.properties`)
- **Lombok**
- **Postman** for testing APIs

---


