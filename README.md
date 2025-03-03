# Intern Intelligence Movie API

This project is a **Spring Boot REST API** that provides CRUD operations for movies. Users can **register, authenticate**, and perform different actions on movies based on their roles.

## Features
- **User Authentication** (Register, Login, JWT Authentication, Forgot Password)
- **Movie Management** (Create, Read, Update, Delete - CRUD)
- **Role-Based Authorization** (Users & Admins have different permissions)
- **Swagger API Documentation**

## Technologies Used
- **Spring Boot** (Spring Web, Spring Security, Spring Data JPA)
- **Lombok** (to reduce boilerplate code)
- **MapStruct** (for DTO mapping)
- **PostgreSQL** (as the database)
- **Swagger UI** (for API documentation)

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Agabehlul/InternIntelligence_Movie_api.git
   cd InternIntelligence_Movie_api
   ```
2. Configure **PostgreSQL** database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Open Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## API Endpoints & Authorization

| Method | Endpoint | Role Required | Description |
|--------|---------|--------------|-------------|
| `POST` | `/user/register` | Public | Register a new user |
| `POST` | `/api/authenticate` | Public | User login |
| `GET` | `/api/movie/**` | `ROLE_USER`, `ROLE_ADMIN` | Get movie details |
| `POST` | `/api/movie/**` | `ROLE_ADMIN` | Add a new movie |
| `PUT` | `/api/movie/**` | `ROLE_ADMIN` | Update an existing movie |
| `DELETE` | `/api/movie/**` | `ROLE_ADMIN` | Delete a movie |
| `PATCH` | `/api/movie/**` | `ROLE_ADMIN` | Partially update a movie |

## Security & Authentication
- Uses **Spring Security** with JWT authentication.
- User roles: `ROLE_USER` (read access), `ROLE_ADMIN` (full CRUD access).
- Login endpoint: `/api/authenticate` (returns JWT token on success).
- Add JWT token in **Authorization Header** when making API requests.

## Author
👤 **Agabehlul**  

