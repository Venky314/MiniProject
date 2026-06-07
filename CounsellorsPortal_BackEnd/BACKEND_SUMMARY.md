# Counsellors Portal - Backend Project Summary

## Project Overview
Counsellors Portal is a complete Spring Boot backend application designed to manage counsellors, courses, and student enquiries. The application is fully RESTful and ready to be integrated with a React frontend.

---

## Project Structure

```
CounsellorsPortal/
├── src/
│   ├── main/
│   │   ├── java/com/ashok/it/counsellorsportal/
│   │   │   ├── Model/
│   │   │   │   ├── counsellors.java        (Entity)
│   │   │   │   ├── courses.java            (Entity)
│   │   │   │   └── enquiries.java          (Entity)
│   │   │   ├── Dto/
│   │   │   │   ├── CounsellorsDTO.java
│   │   │   │   ├── CoursesDTO.java
│   │   │   │   └── EnquiriesDTO.java
│   │   │   ├── Mapper/
│   │   │   │   ├── CounsellorsMapper.java
│   │   │   │   ├── CoursesMapper.java
│   │   │   │   └── EnquiriesMapper.java
│   │   │   ├── Repository/
│   │   │   │   ├── counsellorsRepository.java
│   │   │   │   ├── coursesRepository.java
│   │   │   │   └── EnquiryRepository.java
│   │   │   ├── Service/
│   │   │   │   ├── CounsellorsService.java (Interface)
│   │   │   │   ├── CoursesService.java    (Interface)
│   │   │   │   └── EnquiriesService.java  (Interface)
│   │   │   ├── Impl/
│   │   │   │   ├── CounsellorsServiceImpl.java
│   │   │   │   ├── CoursesServiceImpl.java
│   │   │   │   └── EnquiriesServiceImpl.java
│   │   │   ├── Controller/
│   │   │   │   ├── CounsellorsController.java
│   │   │   │   ├── CoursesController.java
│   │   │   │   └── EnquiriesController.java
│   │   │   ├── Exception/
│   │   │   │   └── ApiResponse.java        (Response Wrapper)
│   │   │   └── CounsellorsPortalApplication.java
│   │   └── resources/
│   │       ├── application.properties      (Configuration)
│   │       └── ...
│   └── test/
│       └── java/...
├── pom.xml                                 (Maven Dependencies)
├── API_DOCUMENTATION.md                    (API Endpoints)
├── REACT_INTEGRATION_GUIDE.md             (Frontend Guide)
└── README.md

```

---

## Technologies Used

- **Java:** 17
- **Spring Boot:** 4.0.6
- **Spring Data JPA:** Database ORM
- **MySQL:** Database
- **Lombok:** Reduced boilerplate code
- **Maven:** Build tool

---

## Database Schema

### Relationships

```
Counsellors (1) -----> (Many) Enquiries
    |
    └---- One-to-Many relationship (via counsellorId)

Courses (1) -----> (Many) Enquiries
    |
    └---- One-to-Many relationship (via courseId)
```

### Table Definitions

#### Counsellors Table
```sql
CREATE TABLE counsellors (
    counsellor_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
```

#### Courses Table
```sql
CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL
);
```

#### Enquiries Table
```sql
CREATE TABLE enquiries (
    enquiry_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    stu_name VARCHAR(255) NOT NULL,
    student_phone_number BIGINT,
    class_mode VARCHAR(50),
    enq_status VARCHAR(50),
    counsellor_id BIGINT NOT NULL,
    course_id INT NOT NULL,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    FOREIGN KEY (counsellor_id) REFERENCES counsellors(counsellor_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
```

---

## Features Implemented

### ✅ Complete CRUD Operations
- Create, Read, Update, Delete for all entities
- Proper error handling with descriptive messages
- Timestamp tracking for created and updated records

### ✅ Relationships
- One-to-Many relationship between Counsellors and Enquiries
- One-to-Many relationship between Courses and Enquiries
- Cascade operations on delete
- Orphan removal for data integrity

### ✅ REST API
- Clean RESTful endpoints
- CORS enabled for all origins
- Consistent API response format with status codes
- Proper HTTP status codes (201 Created, 200 OK, 400 Bad Request, 404 Not Found, 500 Internal Server Error)

### ✅ Data Transfer Objects (DTOs)
- Separation of concerns between internal models and API responses
- Prevents exposure of sensitive data

### ✅ Mappers
- Entity-to-DTO and DTO-to-Entity conversion
- Centralized mapping logic

### ✅ Services
- Business logic layer
- Separation from controller logic
- Reusable across multiple controllers
- Transaction management

### ✅ Repositories
- Spring Data JPA repositories
- Custom query methods for filtering by counsellor and course
- Database access layer abstraction

---

## API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Counsellors
- `POST /counsellors` - Create counsellor
- `GET /counsellors` - Get all counsellors
- `GET /counsellors/{counsellorId}` - Get specific counsellor
- `PUT /counsellors/{counsellorId}` - Update counsellor
- `DELETE /counsellors/{counsellorId}` - Delete counsellor

### Courses
- `POST /courses` - Create course
- `GET /courses` - Get all courses
- `GET /courses/{courseId}` - Get specific course
- `PUT /courses/{courseId}` - Update course
- `DELETE /courses/{courseId}` - Delete course

### Enquiries
- `POST /enquiries` - Create enquiry
- `GET /enquiries` - Get all enquiries
- `GET /enquiries/{enquiryId}` - Get specific enquiry
- `PUT /enquiries/{enquiryId}` - Update enquiry
- `DELETE /enquiries/{enquiryId}` - Delete enquiry
- `GET /enquiries/counsellor/{counsellorId}` - Get enquiries by counsellor
- `GET /enquiries/course/{courseId}` - Get enquiries by course

---

## Configuration

### Database Configuration (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/counsellors_portal
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### Server Configuration
- **Server Port:** 8080
- **Context Path:** /api
- **CORS:** Enabled for all origins

---

## How to Run

### Prerequisites
1. Java 17 or higher installed
2. MySQL 5.7 or higher installed and running
3. Maven 3.6 or higher installed

### Setup Steps

1. **Create Database:**
```bash
mysql -u root
CREATE DATABASE counsellors_portal;
EXIT;
```

2. **Navigate to Project:**
```bash
cd CounsellorsPortal
```

3. **Clean and Build:**
```bash
mvn clean install
```

4. **Run Application:**
```bash
mvn spring-boot:run
```

5. **Verify:**
- Backend should start on `http://localhost:8080`
- Database tables created automatically via Hibernate

---

## API Response Format

### Success Response
```json
{
  "status": 201,
  "message": "Counsellor created successfully",
  "data": {
    "counsellorId": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phoneNumber": 9876543210,
    "createdAt": "2026-06-07T10:30:00",
    "updatedAt": "2026-06-07T10:30:00"
  },
  "timestamp": "2026-06-07T10:30:00"
}
```

### Error Response
```json
{
  "status": 400,
  "message": "Error creating counsellor: Database error",
  "timestamp": "2026-06-07T10:30:00"
}
```

---

## Design Patterns Used

1. **DTO Pattern** - For data transfer between layers
2. **Mapper Pattern** - For entity-DTO conversion
3. **Service Pattern** - Business logic separation
4. **Repository Pattern** - Data access abstraction
5. **Dependency Injection** - Spring IoC container
6. **MVC Architecture** - Model-View-Controller separation

---

## Code Quality

- **Lombok** - Reduces boilerplate getter/setter code
- **Spring Boot Convention** - Follows best practices
- **Error Handling** - Try-catch blocks with meaningful errors
- **Logging** - Configured logging levels
- **Naming Conventions** - Clear, descriptive names for classes and methods

---

## Testing the Backend

### Using cURL

Create a Counsellor:
```bash
curl -X POST http://localhost:8080/api/counsellors \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@test.com","password":"123456","phoneNumber":9876543210}'
```

Get All Counsellors:
```bash
curl http://localhost:8080/api/counsellors
```

### Using Postman

1. Import the endpoints into Postman
2. Set base URL to `http://localhost:8080/api`
3. Test all CRUD operations

---

## Integration with React Frontend

See `REACT_INTEGRATION_GUIDE.md` for complete React setup and integration examples.

### Quick Start for React
1. Enable CORS (✅ Already enabled)
2. Setup Axios service layers
3. Create React components using service methods
4. Handle loading and error states

---

## Future Enhancements

- [ ] Authentication & Authorization (JWT)
- [ ] Pagination and filtering
- [ ] Search functionality
- [ ] File upload capability
- [ ] Advanced reporting features
- [ ] Email notifications
- [ ] SMS notifications
- [ ] Dashboard analytics
- [ ] Unit and Integration tests
- [ ] API versioning

---

## Common Issues & Solutions

### Issue: 404 Not Found
- Check if backend is running on port 8080
- Verify endpoint URL spelling and case sensitivity

### Issue: 500 Internal Server Error
- Check application logs
- Verify MySQL is running and database exists
- Check application.properties database configuration

### Issue: CORS Error
- Already handled, ensure CORS is not blocked by proxy

### Issue: 400 Bad Request
- Verify JSON format in request body
- Check field names and types match DTO

---

## Support & Documentation

- API Documentation: See `API_DOCUMENTATION.md`
- React Integration: See `REACT_INTEGRATION_GUIDE.md`
- Spring Boot Documentation: https://spring.io/projects/spring-boot
- Lombok Documentation: https://projectlombok.org/

---

## Version History

- **v0.0.1** (Current)
  - Initial project setup
  - Complete CRUD operations
  - Table relationships implemented
  - REST API endpoints created
  - DTOs and Mappers added
  - Service layer implemented

---

## Author
Ashok IT Solutions

## License
All rights reserved

---

## Contact
For support and inquiries, please contact the development team.

