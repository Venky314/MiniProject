# Counsellors Portal API Documentation

## Base URL
```
http://localhost:8080/api
```

## API Endpoints

### Counsellors Endpoints

#### 1. Create Counsellor
- **URL:** `POST /counsellors`
- **Description:** Create a new counsellor
- **Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "phoneNumber": 9876543210
}
```
- **Response:** (201 Created)
```json
{
  "status": 201,
  "message": "Counsellor created successfully",
  "data": {
    "counsellorId": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "phoneNumber": 9876543210,
    "createdAt": "2026-06-07T10:30:00",
    "updatedAt": "2026-06-07T10:30:00"
  },
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 2. Get All Counsellors
- **URL:** `GET /counsellors`
- **Description:** Get all counsellors
- **Response:** (200 OK)
```json
{
  "status": 200,
  "message": "All counsellors retrieved successfully",
  "data": [
    {
      "counsellorId": 1,
      "name": "John Doe",
      "email": "john@example.com",
      "password": "password123",
      "phoneNumber": 9876543210,
      "createdAt": "2026-06-07T10:30:00",
      "updatedAt": "2026-06-07T10:30:00"
    }
  ],
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 3. Get Counsellor by ID
- **URL:** `GET /counsellors/{counsellorId}`
- **Description:** Get a specific counsellor
- **Path Parameters:** `counsellorId` (Long)
- **Response:** (200 OK)
```json
{
  "status": 200,
  "message": "Counsellor retrieved successfully",
  "data": {
    "counsellorId": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "phoneNumber": 9876543210,
    "createdAt": "2026-06-07T10:30:00",
    "updatedAt": "2026-06-07T10:30:00"
  },
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 4. Update Counsellor
- **URL:** `PUT /counsellors/{counsellorId}`
- **Description:** Update a counsellor
- **Path Parameters:** `counsellorId` (Long)
- **Request Body:**
```json
{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "password": "newpassword123",
  "phoneNumber": 9876543211
}
```
- **Response:** (200 OK)

#### 5. Delete Counsellor
- **URL:** `DELETE /counsellors/{counsellorId}`
- **Description:** Delete a counsellor
- **Path Parameters:** `counsellorId` (Long)
- **Response:** (200 OK)
```json
{
  "status": 200,
  "message": "Counsellor deleted successfully",
  "timestamp": "2026-06-07T10:30:00"
}
```

---

### Courses Endpoints

#### 1. Create Course
- **URL:** `POST /courses`
- **Description:** Create a new course
- **Request Body:**
```json
{
  "courseName": "Java Development"
}
```
- **Response:** (201 Created)
```json
{
  "status": 201,
  "message": "Course created successfully",
  "data": {
    "courseId": 1,
    "courseName": "Java Development"
  },
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 2. Get All Courses
- **URL:** `GET /courses`
- **Description:** Get all courses
- **Response:** (200 OK)

#### 3. Get Course by ID
- **URL:** `GET /courses/{courseId}`
- **Description:** Get a specific course
- **Path Parameters:** `courseId` (int)
- **Response:** (200 OK)

#### 4. Update Course
- **URL:** `PUT /courses/{courseId}`
- **Description:** Update a course
- **Path Parameters:** `courseId` (int)
- **Request Body:**
```json
{
  "courseName": "Advanced Java Development"
}
```
- **Response:** (200 OK)

#### 5. Delete Course
- **URL:** `DELETE /courses/{courseId}`
- **Description:** Delete a course
- **Path Parameters:** `courseId` (int)
- **Response:** (200 OK)

---

### Enquiries Endpoints

#### 1. Create Enquiry
- **URL:** `POST /enquiries`
- **Description:** Create a new enquiry
- **Request Body:**
```json
{
  "stuName": "Student Name",
  "studentPhoneNumber": 9876543210,
  "classMode": "Online",
  "enqStatus": "Pending",
  "counsellorId": 1,
  "courseId": 1
}
```
- **Response:** (201 Created)
```json
{
  "status": 201,
  "message": "Enquiry created successfully",
  "data": {
    "enquiryId": 1,
    "stuName": "Student Name",
    "studentPhoneNumber": 9876543210,
    "classMode": "Online",
    "enqStatus": "Pending",
    "createdDate": "2026-06-07T10:30:00",
    "updatedDate": "2026-06-07T10:30:00",
    "counsellorId": 1,
    "courseId": 1
  },
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 2. Get All Enquiries
- **URL:** `GET /enquiries`
- **Description:** Get all enquiries
- **Response:** (200 OK)

#### 3. Get Enquiry by ID
- **URL:** `GET /enquiries/{enquiryId}`
- **Description:** Get a specific enquiry
- **Path Parameters:** `enquiryId` (Long)
- **Response:** (200 OK)

#### 4. Update Enquiry
- **URL:** `PUT /enquiries/{enquiryId}`
- **Description:** Update an enquiry
- **Path Parameters:** `enquiryId` (Long)
- **Request Body:**
```json
{
  "stuName": "Updated Student Name",
  "studentPhoneNumber": 9876543211,
  "classMode": "Offline",
  "enqStatus": "Approved",
  "counsellorId": 1,
  "courseId": 1
}
```
- **Response:** (200 OK)

#### 5. Delete Enquiry
- **URL:** `DELETE /enquiries/{enquiryId}`
- **Description:** Delete an enquiry
- **Path Parameters:** `enquiryId` (Long)
- **Response:** (200 OK)

#### 6. Get Enquiries by Counsellor
- **URL:** `GET /enquiries/counsellor/{counsellorId}`
- **Description:** Get all enquiries assigned to a counsellor
- **Path Parameters:** `counsellorId` (Long)
- **Response:** (200 OK)
```json
{
  "status": 200,
  "message": "Enquiries retrieved successfully",
  "data": [
    {
      "enquiryId": 1,
      "stuName": "Student Name",
      "studentPhoneNumber": 9876543210,
      "classMode": "Online",
      "enqStatus": "Pending",
      "createdDate": "2026-06-07T10:30:00",
      "updatedDate": "2026-06-07T10:30:00",
      "counsellorId": 1,
      "courseId": 1
    }
  ],
  "timestamp": "2026-06-07T10:30:00"
}
```

#### 7. Get Enquiries by Course
- **URL:** `GET /enquiries/course/{courseId}`
- **Description:** Get all enquiries for a specific course
- **Path Parameters:** `courseId` (int)
- **Response:** (200 OK)

---

## Error Responses

### 400 Bad Request
```json
{
  "status": 400,
  "message": "Error creating counsellor: Invalid input",
  "timestamp": "2026-06-07T10:30:00"
}
```

### 404 Not Found
```json
{
  "status": 404,
  "message": "Counsellor not found with id: 999",
  "timestamp": "2026-06-07T10:30:00"
}
```

### 500 Internal Server Error
```json
{
  "status": 500,
  "message": "Error fetching counsellors: Database connection failed",
  "timestamp": "2026-06-07T10:30:00"
}
```

---

## CORS Configuration
All endpoints have CORS enabled for all origins with max age 3600 seconds.

## Running the Backend
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Database Configuration
- **Database:** MySQL
- **Host:** localhost
- **Port:** 3306
- **Database Name:** counsellors_portal
- **Username:** root
- **Password:** (empty by default)

Make sure MySQL is running and create the database before starting the application.

