# User Registration System

A comprehensive, enterprise-level user registration system built with **Spring Boot**, **Hibernate (JPA)**, and **MySQL**. This project demonstrates best practices in software development following professional SDLC standards.

## 🚀 Features

### Core Functionality
- ✅ **User Registration** - Complete registration form with validation
- ✅ **User Management** - View, Edit, Delete users
- ✅ **RESTful API** - Full REST API for user operations
- ✅ **Database Persistence** - Hibernate/JPA with MySQL
- ✅ **Data Validation** - Server-side and client-side validation
- ✅ **Password Encryption** - BCrypt password hashing
- ✅ **Exception Handling** - Global exception handler
- ✅ **Responsive UI** - Modern, mobile-friendly interface

### Technical Features
- 🏗️ **Layered Architecture** - Controller → Service → Repository
- 📦 **DTO Pattern** - Separation of concerns with DTOs
- 🔒 **Security** - Password encryption and validation
- 📊 **Database** - MySQL with Hibernate ORM
- 🎨 **UI** - Thymeleaf templates with Bootstrap 5
- 📝 **Logging** - SLF4J with Logback
- ⚡ **Connection Pooling** - HikariCP for performance
- 🧪 **Multiple Profiles** - Dev, Prod configurations

## 📋 Prerequisites

Before running this project, ensure you have:

- **Java 17** or higher
- **Maven 3.6+** 
- **MySQL 8.0+** (or H2 for development)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd Create-Registration-Form
```

### 2. Configure Database

#### Option A: MySQL (Production)
1. Install MySQL and start the service
2. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/registration_db?createDatabaseIfNotExist=true
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

3. Run the schema file (optional):
```bash
mysql -u root -p < src/main/resources/schema.sql
```

#### Option B: H2 Database (Development)
Use the development profile which includes H2 in-memory database:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

Or with a specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 5. Access the Application

- **Web Interface**: http://localhost:8080
- **Registration Form**: http://localhost:8080/register
- **User List**: http://localhost:8080/users
- **H2 Console** (dev profile): http://localhost:8080/h2-console

## 📁 Project Structure

```
Create-Registration-Form/
├── src/
│   ├── main/
│   │   ├── java/com/registration/
│   │   │   ├── config/           # Configuration classes
│   │   │   │   └── WebConfig.java
│   │   │   ├── controller/       # MVC and REST controllers
│   │   │   │   ├── RegistrationController.java
│   │   │   │   └── UserRestController.java
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   ├── UserRegistrationDTO.java
│   │   │   │   └── UserResponseDTO.java
│   │   │   ├── entity/           # JPA Entities
│   │   │   │   └── User.java
│   │   │   ├── exception/        # Custom exceptions
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── repository/       # JPA Repositories
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/          # Business logic
│   │   │   │   ├── UserService.java
│   │   │   │   └── impl/
│   │   │   │       └── UserServiceImpl.java
│   │   │   └── RegistrationApplication.java  # Main class
│   │   └── resources/
│   │       ├── templates/        # Thymeleaf templates
│   │       │   ├── index.html
│   │       │   ├── registration-form.html
│   │       │   ├── registration-success.html
│   │       │   ├── user-list.html
│   │       │   ├── user-details.html
│   │       │   └── edit-user.html
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       └── schema.sql
└── pom.xml
```

## 🔌 API Endpoints

### REST API (JSON)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register a new user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/active` | Get all active users |
| GET | `/api/users/search?term={term}` | Search users by name |
| PUT | `/api/users/{id}` | Update user |
| PATCH | `/api/users/{id}/deactivate` | Deactivate user |
| DELETE | `/api/users/{id}` | Delete user |
| GET | `/api/users/check/email/{email}` | Check if email exists |
| GET | `/api/users/check/username/{username}` | Check if username exists |

### Web Pages (HTML)

| Route | Description |
|-------|-------------|
| `/` | Home page |
| `/register` | Registration form |
| `/registration-success` | Success page after registration |
| `/users` | List all users |
| `/users/{id}` | View user details |
| `/users/{id}/edit` | Edit user form |

## 📊 Database Schema

### Users Table
```sql
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(50),
    postal_code VARCHAR(6),
    country VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    email_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## 🧪 Testing the Application

### Using Web Interface
1. Open http://localhost:8080
2. Click "Register New Account"
3. Fill in the registration form
4. Submit and view success page
5. Navigate to "View All Users" to see registered users

### Using REST API (cURL)

#### Register a new user:
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "username": "johndoe",
    "email": "john.doe@example.com",
    "password": "password123",
    "confirmPassword": "password123",
    "phoneNumber": "9876543210",
    "dateOfBirth": "1990-01-15",
    "gender": "Male",
    "city": "New York",
    "state": "NY",
    "postalCode": "110001",
    "country": "USA"
  }'
```

#### Get all users:
```bash
curl http://localhost:8080/api/users
```

#### Get user by ID:
```bash
curl http://localhost:8080/api/users/1
```

## 🎯 Validation Rules

- **First/Last Name**: 2-50 characters
- **Username**: 3-30 characters (alphanumeric and underscore only)
- **Email**: Valid email format
- **Password**: Minimum 8 characters
- **Phone**: Exactly 10 digits
- **Date of Birth**: Must be in the past
- **Gender**: Male, Female, or Other
- **Postal Code**: 6 digits (if provided)

## 🔐 Security Features

- **Password Encryption**: BCrypt hashing algorithm
- **Unique Constraints**: Email and username uniqueness
- **Input Validation**: Server-side validation
- **SQL Injection Protection**: Hibernate parameterized queries
- **XSS Protection**: Thymeleaf auto-escaping

## 🌐 Environment Profiles

### Development Profile (`dev`)
- H2 in-memory database
- SQL logging enabled
- Auto-create schema
- H2 console enabled

### Production Profile (`prod`)
- MySQL database
- Minimal logging
- Schema validation only
- Error details hidden

## 📝 Configuration

### Key Application Properties

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/registration_db
spring.jpa.hibernate.ddl-auto=update

# Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.com.registration=DEBUG
```

## 🚀 Deployment

### Package the Application
```bash
mvn clean package
```

### Run the JAR
```bash
java -jar target/registration-form-1.0.0.jar
```

### Run with Production Profile
```bash
java -jar target/registration-form-1.0.0.jar --spring.profiles.active=prod
```

## 🛠️ Technologies Used

- **Backend Framework**: Spring Boot 3.2.0
- **ORM**: Hibernate (JPA)
- **Database**: MySQL 8.0 / H2
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5, Font Awesome
- **Build Tool**: Maven
- **Java Version**: 17
- **Password Encryption**: BCrypt
- **Connection Pool**: HikariCP
- **Logging**: SLF4J + Logback
- **DTO Mapping**: ModelMapper

## 📚 Design Patterns Used

1. **MVC Pattern** - Model-View-Controller architecture
2. **DTO Pattern** - Data Transfer Objects
3. **Repository Pattern** - Data access abstraction
4. **Service Layer Pattern** - Business logic separation
5. **Singleton Pattern** - Spring beans
6. **Factory Pattern** - Bean creation
7. **Builder Pattern** - Lombok @Builder

## 🏆 Best Practices Implemented

✅ Layered architecture (Controller → Service → Repository)  
✅ Separation of concerns with DTOs  
✅ Global exception handling  
✅ Input validation at multiple levels  
✅ Password encryption  
✅ Transaction management  
✅ Connection pooling  
✅ Logging and monitoring  
✅ Code documentation  
✅ RESTful API design  
✅ Responsive web design  
✅ Environment-specific configurations  

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Registration System Team**  
Version: 1.0.0

## 📞 Support

For issues or questions, please create an issue in the repository.

---

**Happy Coding! 🎉**