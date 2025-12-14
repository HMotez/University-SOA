# Specifications Document – SOA University System

## 1. Context

The university aims to modernize its information system by adopting a **Service-Oriented Architecture (SOA)**.  
The objective is to separate functionalities into several independent services (REST and SOAP) in order to facilitate:

- scalability,  
- maintainability,  
- interoperability between different technologies.

This mini-project is carried out as part of the **SOA Architecture and Web Services** module, supervised by **Ms. Ghada Feki**.

---

## 2. Project Objectives

- Implement a **complete SOA architecture** for a university management system.
- Develop multiple **heterogeneous web services** (REST and SOAP).
- Ensure **security** through an authentication service (JWT).
- Enable **interoperability** between different technologies (Spring Boot, Node.js, etc.).
- Prepare the project for **containerized deployment** (Docker / Docker Compose).
- Provide **technical documentation** and a **user manual**.
- Deliver a **project defense** with a functional demonstration.

---

## 3. Functional Scope

The system covers the following areas:

### 3.1 User Management & Authentication

- Account creation (admin, teacher, student).
- Authentication (username/password).
- **JWT** token generation.
- Role and access rights management.

---

### 3.2 Student Management

- Create, update, and delete a student.
- View student information.
- List students by department, level, and group.

---

### 3.3 Course and Timetable Management

- Create a course (code, name, teacher, level).
- Update / delete a course.
- Assign a course to a group and a schedule.
- View timetables.

---

### 3.4 Grade Management

- Enter student grades for a course.
- Calculate the average.
- Determine the status: **PASSED** / **RETAKE**.

---

### 3.5 Billing Management

- Calculation of university fees (registration, tuition, penalties, etc.).
- Payment management.
- Generation of invoices / receipts.

---

### 3.6 API Gateway

- Provide a single entry point for clients (front-end, Postman).
- Route requests to internal services.
- Optional data aggregation  
  (e.g., student profile = information + grades + billing).

---

## 4. Non-Functional Scope

- **Performance**: acceptable response time in a local environment.
- **Security**:
  - JWT-based authentication.
  - Protection of sensitive endpoints.
- **Interoperability**:
  - Services developed using different backend technologies.
- **Scalability**:
  - Decoupled services that can be deployed independently.
- **Deployment**:
  - Use of Docker and Docker Compose.

---

## 5. Actors

- **Administrator**: manages users, courses, and billing.
- **Teacher**: enters grades.
- **Student**: views personal information (grades, courses, invoices).
