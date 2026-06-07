# 🎓 Counsellors Portal - Complete Backend Project

A comprehensive Spring Boot REST API for managing counsellors, courses, and student enquiries. Ready to integrate with React frontend.

---

## 📋 Table of Contents

1. [Quick Start](#-quick-start)
2. [Project Structure](#-project-structure)
3. [Features](#-features)
4. [API Endpoints](#-api-endpoints-summary)
5. [Database Setup](#-database-setup)
6. [Running the Application](#-running-the-application)
7. [React Integration](#-react-integration)
8. [Documentation](#-documentation)
9. [Troubleshooting](#-troubleshooting)
10. [Support](#-support)

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- MySQL 5.7+
- Maven 3.6+
- (Optional) Postman or Insomnia for API testing

### Steps

1. **Start MySQL Server**
   ```bash
   # Windows
   net start MySQL80
   
   # Mac
   brew services start mysql
   
   # Linux
   sudo systemctl start mysql
   ```

2. **Create Database**
   ```bash
   mysql -u root -p
   CREATE DATABASE counsellors_portal;
   EXIT;
   ```

3. **Navigate to Project**
   ```bash
   cd CounsellorsPortal
   ```

4. **Build Project**
   ```bash
   mvn clean install
   ```

5. **Run Application**
   ```bash
   mvn spring-boot:run
   ```

6. **Verify It's Running**
   ```
   Open browser: http://localhost:8080/api
   ```

---

## 📁 Project Structure

```
CounsellorsPortal/
│
├── src/main/java/com/ashok/it/counsellorsportal/
│   ├── Model/                  # JPA Entities
│   │   ├── counsellors.java       (Counsellor entity with One-to-Many)
│   │   ├── courses.java           (Course entity with One-to-Many)
│   │   └── enquiries.java         (Enquiry entity with foreign keys)
│   │
│   ├── Dto/                    # Data Transfer Objects
│   │   ├── CounsellorsDTO.java
│   │   ├── CoursesDTO.java
│   │   └── EnquiriesDTO.java
│   │
│   ├── Mapper/                 # Entity ↔ DTO Conversion
│   │   ├── CounsellorsMapper.java
│   │   ├── CoursesMapper.java
│   │   └── EnquiriesMapper.java
│   │
│   ├── Repository/             # Database Access Layer
│   │   ├── counsellorsRepository.java
│   │   ├── coursesRepository.java
│   │   └── EnquiryRepository.java
│   │
│   ├── Service/                # Business Logic (Interfaces)
│   │   ├── CounsellorsService.java
│   │   ├── CoursesService.java
│   │   └── EnquiriesService.java
│   │
│   ├── Impl/                   # Service Implementations
│   │   ├── CounsellorsServiceImpl.java
│   │   ├── CoursesServiceImpl.java
│   │   └── EnquiriesServiceImpl.java
│   │
│   ├── Controller/             # REST Endpoints
│   │   ├── CounsellorsController.java
│   │   ├── CoursesController.java
│   │   └── EnquiriesController.java
│   │
│   ├── Exception/              # Exception Handling
│   │   └── ApiResponse.java    (Consistent API responses)
│   │
│   └── CounsellorsPortalApplication.java  # Main Spring Boot App
│
├── src/main/resources/
│   └── application.properties  # Configuration
│
├── pom.xml                      # Maven Dependencies
├── API_DOCUMENTATION.md         # Detailed API docs
├── REACT_INTEGRATION_GUIDE.md  # React setup guide
├── BACKEND_SUMMARY.md          # Complete backend overview
└── README.md                   # This file
```

---

## ✨ Features

### ✅ Implemented Features
- **Complete CRUD Operations** for all three entities
- **Database Relationships**
  - One-to-Many: Counsellors → Enquiries
  - One-to-Many: Courses → Enquiries
- **RESTful API** with proper HTTP methods and status codes
- **DTO Pattern** for clean data transfer
- **Mapper Layer** for entity conversion
- **Service Layer** with business logic
- **Repository Pattern** for data access
- **Error Handling** with meaningful error messages
- **CORS Enabled** for frontend integration
- **Timestamp Tracking** for created/updated records
- **Cascade Operations** for data integrity
- **Consistent API Response Format** with metadata

### 🔄 Data Models
```
┌─────────────────────────────────────┐
│          Counsellors                │
├─────────────────────────────────────┤
│ • counsellorId (PK)                 │
│ • name                              │
│ • email (UNIQUE)                    │
│ • password                          │
│ • phoneNumber                       │
│ • createdAt                         │
│ • updatedAt                         │
│ • enquiriesList (One-to-Many) ──┐   │
└─────────────────────────────────────┤
                                      │
┌─────────────────────────────────────┤
│          Enquiries                  │
├─────────────────────────────────────┤
│ • enquiryId (PK)                    │
│ • stuName                           │
│ • studentPhoneNumber                │
│ • classMode                         │
│ • enqStatus                         │
│ • createdDate                       │
│ • updatedDate                       │
│ • counsellor (FK) ◄────────────────┘
│ • course (FK) ◄─────────────────┐   │
└─────────────────────────────────────┤
                                      │
┌─────────────────────────────────────┤
│           Courses                   │
├─────────────────────────────────────┤
│ • courseId (PK)                     │
│ • courseName                        │
│ • enquiriesList (One-to-Many) ◄────┘
└─────────────────────────────────────┘
```

---

## 📡 API Endpoints Summary

### Base URL
```
http://localhost:8080/api
```

### Endpoints Table

| Method | Endpoint | Description |
|--------|----------|-------------|
| **COUNSELLORS** |
| POST | `/counsellors` | Create new counsellor |
| GET | `/counsellors` | Get all counsellors |
| GET | `/counsellors/{id}` | Get specific counsellor |
| PUT | `/counsellors/{id}` | Update counsellor |
| DELETE | `/counsellors/{id}` | Delete counsellor |
| **COURSES** |
| POST | `/courses` | Create new course |
| GET | `/courses` | Get all courses |
| GET | `/courses/{id}` | Get specific course |
| PUT | `/courses/{id}` | Update course |
| DELETE | `/courses/{id}` | Delete course |
| **ENQUIRIES** |
| POST | `/enquiries` | Create new enquiry |
| GET | `/enquiries` | Get all enquiries |
| GET | `/enquiries/{id}` | Get specific enquiry |
| PUT | `/enquiries/{id}` | Update enquiry |
| DELETE | `/enquiries/{id}` | Delete enquiry |
| GET | `/enquiries/counsellor/{counsellorId}` | Get enquiries by counsellor |
| GET | `/enquiries/course/{courseId}` | Get enquiries by course |

---

## 🗄️ Database Setup

### Create Database Manually
```sql
CREATE DATABASE counsellors_portal;
USE counsellors_portal;
```

### Automatic Table Creation
Tables are created automatically when the application starts (via Hibernate).

### Manual Table Creation (if needed)
```sql
-- Counsellors Table
CREATE TABLE counsellors (
    counsellor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Courses Table
CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL
);

-- Enquiries Table
CREATE TABLE enquiries (
    enquiry_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    stu_name VARCHAR(255) NOT NULL,
    student_phone_number BIGINT,
    class_mode VARCHAR(50),
    enq_status VARCHAR(50),
    counsellor_id BIGINT NOT NULL,
    course_id INT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (counsellor_id) REFERENCES counsellors(counsellor_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);
```

---

## ▶️ Running the Application

### Method 1: Using Maven
```bash
cd CounsellorsPortal
mvn clean install
mvn spring-boot:run
```

### Method 2: Running JAR directly
```bash
mvn clean package
java -jar target/CounsellorsPortal-0.0.1-SNAPSHOT.jar
```

### Method 3: Using IDE
- Open in IntelliJ/Eclipse
- Right-click `CounsellorsPortalApplication.java`
- Click "Run"

---

## 🔌 React Integration

### For React Developers

**See `REACT_INTEGRATION_GUIDE.md` for complete setup**

#### Quick Integration Steps:

1. **Install Axios**
   ```bash
   npm install axios
   ```

2. **Create API Service**
   ```javascript
   // src/services/api.js
   import axios from 'axios';
   
   const apiClient = axios.create({
     baseURL: 'http://localhost:8080/api'
   });
   
   export default apiClient;
   ```

3. **Create Service Layer**
   ```javascript
   // src/services/counsellorsService.js
   import apiClient from './api';
   
   export const counsellorsService = {
     getAllCounsellors: () => apiClient.get('/counsellors'),
     createCounsellor: (data) => apiClient.post('/counsellors', data),
     getCounsellorById: (id) => apiClient.get(`/counsellors/${id}`),
     updateCounsellor: (id, data) => apiClient.put(`/counsellors/${id}`, data),
     deleteCounsellor: (id) => apiClient.delete(`/counsellors/${id}`)
   };
   ```

4. **Use in React Component**
   ```javascript
   import { useEffect, useState } from 'react';
   import { counsellorsService } from './services/counsellorsService';
   
   function CounsellorsList() {
     const [counsellors, setCounsellors] = useState([]);
     const [loading, setLoading] = useState(false);
     
     useEffect(() => {
       setLoading(true);
       counsellorsService.getAllCounsellors()
         .then(res => setCounsellors(res.data.data))
         .catch(err => console.error(err))
         .finally(() => setLoading(false));
     }, []);
     
     if(loading) return <div>Loading...</div>;
     
     return (
       <div>
         {counsellors.map(c => (
           <div key={c.counsellorId}>{c.name}</div>
         ))}
       </div>
     );
   }
   ```

---

## 📚 Documentation

### Available Documentation Files

1. **API_DOCUMENTATION.md** - Detailed API endpoints with examples
   - Request/response formats
   - Error handling
   - Status codes

2. **REACT_INTEGRATION_GUIDE.md** - Complete React setup guide
   - Service layer examples
   - Component examples
   - Environment setup

3. **BACKEND_SUMMARY.md** - Backend architecture overview
   - Design patterns used
   - Database schema
   - Project structure

---

## 🐛 Troubleshooting

### Common Issues

#### 1. Port 8080 Already in Use
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill the process (Windows)
taskkill /PID <PID> /F

# On Mac/Linux
sudo lsof -ti:8080 | xargs kill -9
```

#### 2. MySQL Connection Failed
```
Make sure MySQL is running:
- Check Services (Windows)
- Use: mysql -u root -p
```

#### 3. Database Not Found
```sql
CREATE DATABASE counsellors_portal;
```

#### 4. CORS Error in React
- Backend already has CORS enabled
- Check that API URL is correct
- Ensure backend is running

#### 5. 404 Not Found Errors
- Verify endpoint URL and spelling
- Check if backend is running on port 8080
- Verify request method (POST, GET, etc.)

#### 6. 400 Bad Request
- Check JSON format in request body
- Verify field names match DTO
- Check data types

---

## 📊 Example API Requests

### Create Counsellor
```bash
curl -X POST http://localhost:8080/api/counsellors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "phoneNumber": 9876543210
  }'
```

### Get All Counsellors
```bash
curl http://localhost:8080/api/counsellors
```

### Create Course
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "courseName": "Java Development"
  }'
```

### Create Enquiry
```bash
curl -X POST http://localhost:8080/api/enquiries \
  -H "Content-Type: application/json" \
  -d '{
    "stuName": "Student Name",
    "studentPhoneNumber": 9876543210,
    "classMode": "Online",
    "enqStatus": "Pending",
    "counsellorId": 1,
    "courseId": 1
  }'
```

---

## 🔒 Configuration

### Database Configuration
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/counsellors_portal
spring.datasource.username=root
spring.datasource.password=
```

### Server Port
```properties
server.port=8080
server.servlet.context-path=/api
```

### Hibernate Configuration
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 📦 Dependencies Used

- **Spring Boot 4.0.6** - Web framework
- **Spring Data JPA** - ORM for database
- **MySQL Connector/J** - MySQL driver
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool

---

## 🎯 Next Steps

1. ✅ Backend setup complete
2. 📝 Read `API_DOCUMENTATION.md`
3. 🔌 Follow `REACT_INTEGRATION_GUIDE.md`
4. 🧪 Test endpoints with Postman
5. 🎨 Build React frontend
6. 🚀 Deploy to production

---

## 📞 Support

For issues or questions:
1. Check `TROUBLE_SHOOTING` section above
2. Review relevant documentation files
3. Check Spring Boot documentation: https://spring.io/projects/spring-boot
4. Contact development team

---

## 📄 License

All rights reserved © 2026 Ashok IT Solutions

---

## ✍️ Version

- **Version:** 0.0.1
- **Release Date:** June 7, 2026
- **Status:** Production Ready ✅

---

**🎉 Congratulations! Your backend is ready to integrate with React frontend.**

For React integration, see **REACT_INTEGRATION_GUIDE.md**

