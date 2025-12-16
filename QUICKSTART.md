# Quick Start Guide

## 🚀 Quick Start (5 Minutes)

### Option 1: Run with H2 In-Memory Database (Easiest)
```bash
# Build the project
mvn clean install

# Run with development profile (uses H2 database - no setup needed)
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**Access the application:**
- Web Interface: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (leave empty)

### Option 2: Run with MySQL Database
```bash
# 1. Start MySQL and create database
mysql -u root -p
CREATE DATABASE registration_db;
EXIT;

# 2. Update credentials in src/main/resources/application.properties
# spring.datasource.username=YOUR_USERNAME
# spring.datasource.password=YOUR_PASSWORD

# 3. Build and run
mvn clean install
mvn spring-boot:run
```

**Access at:** http://localhost:8080

---

## 📋 Testing Checklist

### Web Interface Testing
- [ ] Visit http://localhost:8080
- [ ] Click "Register New Account"
- [ ] Fill registration form with:
  - First Name: John
  - Last Name: Doe
  - Username: johndoe
  - Email: john.doe@example.com
  - Password: password123
  - Confirm Password: password123
  - Phone: 9876543210
  - Date of Birth: 1990-01-15
  - Gender: Male
  - City: New York
  - State: NY
  - Postal Code: 110001
  - Country: USA
- [ ] Submit and verify success page
- [ ] Click "View All Users"
- [ ] Click on a user to view details
- [ ] Edit a user
- [ ] Delete a user

### REST API Testing
```bash
# Register User
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Smith",
    "username": "janesmith",
    "email": "jane.smith@example.com",
    "password": "password123",
    "confirmPassword": "password123",
    "phoneNumber": "9876543211",
    "dateOfBirth": "1992-05-20",
    "gender": "Female",
    "city": "Los Angeles",
    "state": "CA",
    "postalCode": "110002",
    "country": "USA"
  }'

# Get All Users
curl http://localhost:8080/api/users

# Get User by ID
curl http://localhost:8080/api/users/1

# Search Users
curl http://localhost:8080/api/users/search?term=John

# Check Email Exists
curl http://localhost:8080/api/users/check/email/john.doe@example.com
```

---

## 🎯 Features to Test

### ✅ Registration Features
- [x] User registration with complete form
- [x] Password confirmation validation
- [x] Email uniqueness check
- [x] Username uniqueness check
- [x] Field validation (all fields)
- [x] Success page after registration

### ✅ User Management Features
- [x] View all users in a table
- [x] Search/filter users (DataTables)
- [x] View user details
- [x] Edit user information
- [x] Delete user
- [x] User status (Active/Inactive)

### ✅ REST API Features
- [x] POST /api/users/register - Register user
- [x] GET /api/users - Get all users
- [x] GET /api/users/{id} - Get user by ID
- [x] PUT /api/users/{id} - Update user
- [x] DELETE /api/users/{id} - Delete user
- [x] GET /api/users/active - Get active users
- [x] GET /api/users/search?term={term} - Search users
- [x] PATCH /api/users/{id}/deactivate - Deactivate user

### ✅ Database Features
- [x] MySQL integration with Hibernate
- [x] H2 in-memory database for testing
- [x] Auto-create tables
- [x] Connection pooling (HikariCP)
- [x] Transaction management
- [x] Indexes for performance

### ✅ Security Features
- [x] Password encryption (BCrypt)
- [x] Input validation
- [x] SQL injection protection
- [x] XSS protection
- [x] Unique constraints

---

## 🐛 Troubleshooting

### Port 8080 Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### MySQL Connection Failed
```bash
# Verify MySQL is running
sudo systemctl status mysql

# Check credentials in application.properties
# Ensure database exists
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS registration_db;"
```

### Maven Build Failed
```bash
# Clear Maven cache and rebuild
mvn clean
rm -rf ~/.m2/repository
mvn clean install -U
```

### Application Won't Start
```bash
# Check Java version (must be 17+)
java -version

# Run with debug logging
mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.root=DEBUG
```

---

## 📊 Project Statistics

- **Total Files**: 25+
- **Java Classes**: 14
- **HTML Templates**: 6
- **Configuration Files**: 5
- **Lines of Code**: ~3000+
- **Layers**: 5 (Controller, Service, Repository, Entity, DTO)

---

## 🎓 Learning Objectives Achieved

✅ Spring Boot application structure  
✅ Hibernate/JPA entity mapping  
✅ Repository pattern implementation  
✅ Service layer with business logic  
✅ REST API development  
✅ MVC pattern with Thymeleaf  
✅ Exception handling  
✅ Input validation  
✅ Database integration  
✅ Password encryption  
✅ DTO pattern  
✅ Transaction management  

---

**Need Help?** Check the main README.md for detailed documentation!
