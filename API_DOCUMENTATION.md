# API Documentation

## User Registration REST API

Base URL: `http://localhost:8080/api/users`

---

## Endpoints

### 1. Register New User

**Endpoint:** `POST /api/users/register`

**Description:** Register a new user in the system

**Request Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "password": "password123",
  "confirmPassword": "password123",
  "phoneNumber": "9876543210",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "address": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "postalCode": "110001",
  "country": "USA"
}
```

**Success Response (201 Created):**
```json
{
  "userId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "address": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "postalCode": "110001",
  "country": "USA",
  "isActive": true,
  "emailVerified": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

**Error Response (409 Conflict):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Email already registered: john.doe@example.com",
  "path": "/api/users/register"
}
```

**Error Response (400 Bad Request):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users/register",
  "validationErrors": {
    "email": "Please provide a valid email address",
    "password": "Password must be at least 8 characters"
  }
}
```

---

### 2. Get All Users

**Endpoint:** `GET /api/users`

**Description:** Retrieve all registered users

**Success Response (200 OK):**
```json
[
  {
    "userId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "username": "johndoe",
    "email": "john.doe@example.com",
    "phoneNumber": "9876543210",
    "dateOfBirth": "1990-01-15",
    "gender": "Male",
    "city": "New York",
    "state": "NY",
    "isActive": true,
    "emailVerified": false,
    "createdAt": "2024-01-15T10:30:00"
  }
]
```

---

### 3. Get User by ID

**Endpoint:** `GET /api/users/{id}`

**Description:** Retrieve a specific user by ID

**Path Parameters:**
- `id` (required) - User ID

**Success Response (200 OK):**
```json
{
  "userId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "address": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "postalCode": "110001",
  "country": "USA",
  "isActive": true,
  "emailVerified": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

**Error Response (404 Not Found):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with ID: 999",
  "path": "/api/users/999"
}
```

---

### 4. Get Active Users

**Endpoint:** `GET /api/users/active`

**Description:** Retrieve all active users

**Success Response (200 OK):**
```json
[
  {
    "userId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "username": "johndoe",
    "isActive": true,
    "createdAt": "2024-01-15T10:30:00"
  }
]
```

---

### 5. Search Users

**Endpoint:** `GET /api/users/search?term={searchTerm}`

**Description:** Search users by first name or last name

**Query Parameters:**
- `term` (required) - Search term

**Example:** `GET /api/users/search?term=John`

**Success Response (200 OK):**
```json
[
  {
    "userId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "username": "johndoe",
    "email": "john.doe@example.com"
  }
]
```

---

### 6. Update User

**Endpoint:** `PUT /api/users/{id}`

**Description:** Update user information

**Path Parameters:**
- `id` (required) - User ID

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "password": "newpassword123",
  "confirmPassword": "newpassword123",
  "phoneNumber": "9876543210",
  "dateOfBirth": "1990-01-15",
  "gender": "Male",
  "address": "456 New Street",
  "city": "Los Angeles",
  "state": "CA",
  "postalCode": "110002",
  "country": "USA"
}
```

**Success Response (200 OK):**
```json
{
  "userId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "city": "Los Angeles",
  "updatedAt": "2024-01-15T11:30:00"
}
```

---

### 7. Deactivate User

**Endpoint:** `PATCH /api/users/{id}/deactivate`

**Description:** Deactivate a user account

**Path Parameters:**
- `id` (required) - User ID

**Success Response (204 No Content)**

---

### 8. Delete User

**Endpoint:** `DELETE /api/users/{id}`

**Description:** Permanently delete a user

**Path Parameters:**
- `id` (required) - User ID

**Success Response (204 No Content)**

**Error Response (404 Not Found):**
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with ID: 999",
  "path": "/api/users/999"
}
```

---

### 9. Check Email Exists

**Endpoint:** `GET /api/users/check/email/{email}`

**Description:** Check if an email is already registered

**Path Parameters:**
- `email` (required) - Email address to check

**Success Response (200 OK):**
```json
true
```
or
```json
false
```

---

### 10. Check Username Exists

**Endpoint:** `GET /api/users/check/username/{username}`

**Description:** Check if a username is already taken

**Path Parameters:**
- `username` (required) - Username to check

**Success Response (200 OK):**
```json
true
```
or
```json
false
```

---

## HTTP Status Codes

| Code | Meaning | When Used |
|------|---------|-----------|
| 200 | OK | Successful GET, PUT requests |
| 201 | Created | Successful POST (user created) |
| 204 | No Content | Successful DELETE, PATCH |
| 400 | Bad Request | Validation errors |
| 404 | Not Found | User not found |
| 409 | Conflict | Duplicate email/username |
| 500 | Internal Server Error | Server error |

---

## Validation Rules

### Required Fields
- firstName (2-50 chars)
- lastName (2-50 chars)
- username (3-30 chars, alphanumeric + underscore)
- email (valid email format)
- password (min 8 chars)
- phoneNumber (exactly 10 digits)
- dateOfBirth (must be in past)
- gender (Male/Female/Other)

### Optional Fields
- address (max 200 chars)
- city (max 50 chars)
- state (max 50 chars)
- postalCode (exactly 6 digits)
- country (max 50 chars)

---

## cURL Examples

### Register User
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

### Get All Users
```bash
curl http://localhost:8080/api/users
```

### Get User by ID
```bash
curl http://localhost:8080/api/users/1
```

### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe Updated",
    "username": "johndoe",
    "email": "john.doe@example.com",
    "password": "",
    "confirmPassword": "",
    "phoneNumber": "9876543210",
    "dateOfBirth": "1990-01-15",
    "gender": "Male"
  }'
```

### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

### Search Users
```bash
curl "http://localhost:8080/api/users/search?term=John"
```

### Check Email
```bash
curl http://localhost:8080/api/users/check/email/john.doe@example.com
```

---

## Postman Collection

Import this JSON to test all endpoints in Postman:

```json
{
  "info": {
    "name": "User Registration API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Register User",
      "request": {
        "method": "POST",
        "header": [{"key": "Content-Type", "value": "application/json"}],
        "body": {
          "mode": "raw",
          "raw": "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"username\":\"johndoe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password123\",\"phoneNumber\":\"9876543210\",\"dateOfBirth\":\"1990-01-15\",\"gender\":\"Male\"}"
        },
        "url": {"raw": "http://localhost:8080/api/users/register"}
      }
    }
  ]
}
```

---

## Error Handling

All errors follow a consistent format:

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Error Type",
  "message": "Error description",
  "path": "/api/endpoint",
  "validationErrors": {
    "field": "error message"
  }
}
```

---

**For more information, see the main [README.md](README.md)**
