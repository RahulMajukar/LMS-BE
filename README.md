# LMS-BE (Learning Management System - Backend)

This repository contains the backend code for the Learning Management System (LMS). The backend is developed using Spring Boot, a powerful and flexible framework for building Java-based applications.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- User authentication and authorization
- Course management (CRUD operations)
- Enrollment management
- Role-based access control (Admin, Instructor, Student)
- Email notifications

## Technologies Used

- **Java**: Programming language used to build the application
- **Spring Boot**: Framework for building the backend application
- **Spring Security**: For authentication and authorization
- **Spring Data JPA**: For database operations
- **MySQL/PostgreSQL**: Database to store data (you can specify the one you're using)
- **Maven**: Dependency management
- **Hibernate**: ORM for database interaction
- **Swagger**: API documentation
- **JUnit**: For testing

## Getting Started

To get a local copy of the project up and running, follow these steps.

### Prerequisites

- **Java JDK 11 or later**: Make sure you have Java installed.
- **Maven**: Ensure Maven is installed to manage the project dependencies.
- **MySQL/PostgreSQL**: Set up your database server.

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/RahulMajukar/LMS-BE.git
    cd LMS-BE
    ```

2. **Configure the database:**

   Update the `application.properties` or `application.yml` file with your database configuration:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/lms
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

## Running the Application

1. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

2. **Access the application:**

   The application will be running at `http://localhost:8080`.

## API Endpoints

Here are some of the key API endpoints:

- **User Registration/Login:**
  - `POST /api/auth/signup` - Register a new user
  - `POST /api/auth/login` - Log in with credentials
- **Courses:**
  - `GET /api/courses` - Get all courses
  - `POST /api/courses` - Create a new course
  - `PUT /api/courses/{id}` - Update a course
  - `DELETE /api/courses/{id}` - Delete a course
- **Enrollments:**
  - `POST /api/enrollments` - Enroll in a course
  - `GET /api/enrollments` - Get all enrollments

For detailed API documentation, you can access Swagger UI at `http://localhost:8080/swagger-ui.html`.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
