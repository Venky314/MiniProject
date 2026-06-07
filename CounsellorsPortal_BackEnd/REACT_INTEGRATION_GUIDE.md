# React Frontend Integration Guide

## Getting Started with Counsellors Portal Frontend

This guide will help you connect your React frontend application with the Counsellors Portal backend API.

---

## Backend Setup

### Prerequisites
- Java 11 or higher
- MySQL 5.7 or higher
- Maven 3.6 or higher

### Steps to Run Backend

1. **Create MySQL Database:**
```sql
CREATE DATABASE counsellors_portal;
USE counsellors_portal;
```

2. **Update Database Configuration** (if needed)
   - Edit `src/main/resources/application.properties`
   - Update `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password`

3. **Run the Application:**
```bash
cd CounsellorsPortal
mvn clean install
mvn spring-boot:run
```

4. **Verify Backend is Running:**
   - Open browser and go to `http://localhost:8080/api`
   - You should be able to access the API endpoints

---

## React Frontend Setup

### Installation

```bash
# Create React App
npx create-react-app counsellors-portal-frontend
cd counsellors-portal-frontend

# Install Axios for API calls
npm install axios

# Install React Router
npm install react-router-dom

# Install Material-UI (optional, for better UI)
npm install @mui/material @emotion/react @emotion/styled

# Start the application
npm start
```

---

## API Integration Examples

### 1. Setup Axios Instance

Create `src/services/api.js`:

```javascript
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

export default apiClient;
```

### 2. Counsellors Service

Create `src/services/counsellorsService.js`:

```javascript
import apiClient from './api';

const COUNSELLORS_ENDPOINT = '/counsellors';

export const counsellorsService = {
  createCounsellor: (data) => 
    apiClient.post(COUNSELLORS_ENDPOINT, data),
  
  getAllCounsellors: () => 
    apiClient.get(COUNSELLORS_ENDPOINT),
  
  getCounsellorById: (id) => 
    apiClient.get(`${COUNSELLORS_ENDPOINT}/${id}`),
  
  updateCounsellor: (id, data) => 
    apiClient.put(`${COUNSELLORS_ENDPOINT}/${id}`, data),
  
  deleteCounsellor: (id) => 
    apiClient.delete(`${COUNSELLORS_ENDPOINT}/${id}`)
};
```

### 3. Courses Service

Create `src/services/coursesService.js`:

```javascript
import apiClient from './api';

const COURSES_ENDPOINT = '/courses';

export const coursesService = {
  createCourse: (data) => 
    apiClient.post(COURSES_ENDPOINT, data),
  
  getAllCourses: () => 
    apiClient.get(COURSES_ENDPOINT),
  
  getCourseById: (id) => 
    apiClient.get(`${COURSES_ENDPOINT}/${id}`),
  
  updateCourse: (id, data) => 
    apiClient.put(`${COURSES_ENDPOINT}/${id}`, data),
  
  deleteCourse: (id) => 
    apiClient.delete(`${COURSES_ENDPOINT}/${id}`)
};
```

### 4. Enquiries Service

Create `src/services/enquiriesService.js`:

```javascript
import apiClient from './api';

const ENQUIRIES_ENDPOINT = '/enquiries';

export const enquiriesService = {
  createEnquiry: (data) => 
    apiClient.post(ENQUIRIES_ENDPOINT, data),
  
  getAllEnquiries: () => 
    apiClient.get(ENQUIRIES_ENDPOINT),
  
  getEnquiryById: (id) => 
    apiClient.get(`${ENQUIRIES_ENDPOINT}/${id}`),
  
  updateEnquiry: (id, data) => 
    apiClient.put(`${ENQUIRIES_ENDPOINT}/${id}`, data),
  
  deleteEnquiry: (id) => 
    apiClient.delete(`${ENQUIRIES_ENDPOINT}/${id}`),
  
  getEnquiriesByCounsellor: (counsellorId) => 
    apiClient.get(`${ENQUIRIES_ENDPOINT}/counsellor/${counsellorId}`),
  
  getEnquiriesByCourse: (courseId) => 
    apiClient.get(`${ENQUIRIES_ENDPOINT}/course/${courseId}`)
};
```

---

## React Component Examples

### 5. List All Counsellors Component

Create `src/components/CounsellorsList.js`:

```javascript
import React, { useState, useEffect } from 'react';
import { counsellorsService } from '../services/counsellorsService';

export const CounsellorsList = () => {
  const [counsellors, setCounsellors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchCounsellors();
  }, []);

  const fetchCounsellors = async () => {
    try {
      setLoading(true);
      const response = await counsellorsService.getAllCounsellors();
      setCounsellors(response.data.data || []);
      setError(null);
    } catch (err) {
      setError(err.message || 'Failed to fetch counsellors');
      console.error('Error:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure?')) {
      try {
        await counsellorsService.deleteCounsellor(id);
        fetchCounsellors();
      } catch (err) {
        alert('Error deleting counsellor');
      }
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Counsellors List</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {counsellors.map((counsellor) => (
            <tr key={counsellor.counsellorId}>
              <td>{counsellor.counsellorId}</td>
              <td>{counsellor.name}</td>
              <td>{counsellor.email}</td>
              <td>{counsellor.phoneNumber}</td>
              <td>
                <button onClick={() => handleEdit(counsellor.counsellorId)}>Edit</button>
                <button onClick={() => handleDelete(counsellor.counsellorId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
```

### 6. Create Counsellor Component

Create `src/components/CreateCounsellor.js`:

```javascript
import React, { useState } from 'react';
import { counsellorsService } from '../services/counsellorsService';

export const CreateCounsellor = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    phoneNumber: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'phoneNumber' ? parseInt(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await counsellorsService.createCounsellor(formData);
      setFormData({ name: '', email: '', password: '', phoneNumber: '' });
      setError(null);
      if (onSuccess) onSuccess();
      alert('Counsellor created successfully!');
    } catch (err) {
      setError(err.message || 'Error creating counsellor');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add New Counsellor</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      
      <input
        type="text"
        name="name"
        placeholder="Name"
        value={formData.name}
        onChange={handleChange}
        required
      />
      
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />
      
      <input
        type="password"
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleChange}
        required
      />
      
      <input
        type="number"
        name="phoneNumber"
        placeholder="Phone Number"
        value={formData.phoneNumber}
        onChange={handleChange}
        required
      />
      
      <button type="submit" disabled={loading}>
        {loading ? 'Creating...' : 'Create Counsellor'}
      </button>
    </form>
  );
};
```

### 7. Create Enquiry Component

Create `src/components/CreateEnquiry.js`:

```javascript
import React, { useState, useEffect } from 'react';
import { enquiriesService } from '../services/enquiriesService';
import { counsellorsService } from '../services/counsellorsService';
import { coursesService } from '../services/coursesService';

export const CreateEnquiry = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    stuName: '',
    studentPhoneNumber: '',
    classMode: 'Online',
    enqStatus: 'Pending',
    counsellorId: '',
    courseId: ''
  });
  const [counsellors, setCounsellors] = useState([]);
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [counsellorRes, coursesRes] = await Promise.all([
        counsellorsService.getAllCounsellors(),
        coursesService.getAllCourses()
      ]);
      setCounsellors(counsellorRes.data.data || []);
      setCourses(coursesRes.data.data || []);
    } catch (err) {
      setError('Error loading data');
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'studentPhoneNumber' ? parseInt(value) : 
              name === 'courseId' ? parseInt(value) : 
              name === 'counsellorId' ? parseInt(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await enquiriesService.createEnquiry(formData);
      setFormData({
        stuName: '',
        studentPhoneNumber: '',
        classMode: 'Online',
        enqStatus: 'Pending',
        counsellorId: '',
        courseId: ''
      });
      setError(null);
      if (onSuccess) onSuccess();
      alert('Enquiry created successfully!');
    } catch (err) {
      setError(err.message || 'Error creating enquiry');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create Enquiry</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      
      <input
        type="text"
        name="stuName"
        placeholder="Student Name"
        value={formData.stuName}
        onChange={handleChange}
        required
      />
      
      <input
        type="number"
        name="studentPhoneNumber"
        placeholder="Phone Number"
        value={formData.studentPhoneNumber}
        onChange={handleChange}
        required
      />
      
      <select name="classMode" value={formData.classMode} onChange={handleChange}>
        <option value="Online">Online</option>
        <option value="Offline">Offline</option>
        <option value="Hybrid">Hybrid</option>
      </select>
      
      <select name="enqStatus" value={formData.enqStatus} onChange={handleChange}>
        <option value="Pending">Pending</option>
        <option value="Approved">Approved</option>
        <option value="Rejected">Rejected</option>
      </select>
      
      <select name="counsellorId" value={formData.counsellorId} onChange={handleChange} required>
        <option value="">Select Counsellor</option>
        {counsellors.map(c => (
          <option key={c.counsellorId} value={c.counsellorId}>
            {c.name}
          </option>
        ))}
      </select>
      
      <select name="courseId" value={formData.courseId} onChange={handleChange} required>
        <option value="">Select Course</option>
        {courses.map(c => (
          <option key={c.courseId} value={c.courseId}>
            {c.courseName}
          </option>
        ))}
      </select>
      
      <button type="submit" disabled={loading}>
        {loading ? 'Creating...' : 'Create Enquiry'}
      </button>
    </form>
  );
};
```

---

## CORS Troubleshooting

If you encounter CORS errors, the backend is already configured to allow all origins. Make sure:

1. Backend is running on `http://localhost:8080`
2. Frontend is running on `http://localhost:3000` (default React port)
3. Check browser console for the exact error message

---

## Environment Variables (Optional)

Create `.env` file in React project root:

```
REACT_APP_API_BASE_URL=http://localhost:8080/api
```

Update `src/services/api.js`:

```javascript
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';
```

---

## Testing the Integration

1. Create a few courses through the API
2. Create a few counsellors through the API
3. Create enquiries linking both counsellors and courses
4. Test CRUD operations through your React components
5. Verify relationships by querying `/enquiries/counsellor/{id}` and `/enquiries/course/{id}`

---

## Common Issues and Solutions

### Issue: "Cannot POST /"
**Solution:** Make sure the backend is running on port 8080

### Issue: "CORS error"
**Solution:** This shouldn't happen as CORS is enabled, but check browser console

### Issue: "400 Bad Request when creating enquiry"
**Solution:** Make sure you're sending valid counsellorId and courseId that exist in database

### Issue: "Database connection error"
**Solution:** 
- Start MySQL: `net start MySQL80` (Windows) or `brew services start mysql` (Mac)
- Create database: `CREATE DATABASE counsellors_portal;`

---

## Deployment

### Backend (Spring Boot)
- Package: `mvn clean package`
- Deploy to AWS, Heroku, or any Java hosting platform

### Frontend (React)
- Build: `npm run build`
- Deploy to Vercel, Netlify, or any static hosting platform
- Update API_BASE_URL to point to deployed backend

---

Happy coding! 🚀

