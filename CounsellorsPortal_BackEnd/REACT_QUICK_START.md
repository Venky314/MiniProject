# 🚀 React Integration - Copy & Paste Ready Code

This file contains ready-to-use code snippets for React integration. Copy and paste directly into your React project.

---

## 1️⃣ Install Dependencies

```bash
npm install axios react-router-dom
```

---

## 2️⃣ Create API Service Files

### `src/services/api.js`
```javascript
import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

apiClient.interceptors.response.use(
  response => response,
  error => {
    console.error('API Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export default apiClient;
```

### `src/services/counsellorsService.js`
```javascript
import apiClient from './api';

export const counsellorsService = {
  createCounsellor: (data) => apiClient.post('/counsellors', data),
  getAllCounsellors: () => apiClient.get('/counsellors'),
  getCounsellorById: (id) => apiClient.get(`/counsellors/${id}`),
  updateCounsellor: (id, data) => apiClient.put(`/counsellors/${id}`, data),
  deleteCounsellor: (id) => apiClient.delete(`/counsellors/${id}`)
};
```

### `src/services/coursesService.js`
```javascript
import apiClient from './api';

export const coursesService = {
  createCourse: (data) => apiClient.post('/courses', data),
  getAllCourses: () => apiClient.get('/courses'),
  getCourseById: (id) => apiClient.get(`/courses/${id}`),
  updateCourse: (id, data) => apiClient.put(`/courses/${id}`, data),
  deleteCourse: (id) => apiClient.delete(`/courses/${id}`)
};
```

### `src/services/enquiriesService.js`
```javascript
import apiClient from './api';

export const enquiriesService = {
  createEnquiry: (data) => apiClient.post('/enquiries', data),
  getAllEnquiries: () => apiClient.get('/enquiries'),
  getEnquiryById: (id) => apiClient.get(`/enquiries/${id}`),
  updateEnquiry: (id, data) => apiClient.put(`/enquiries/${id}`, data),
  deleteEnquiry: (id) => apiClient.delete(`/enquiries/${id}`),
  getEnquiriesByCounsellor: (counsellorId) => apiClient.get(`/enquiries/counsellor/${counsellorId}`),
  getEnquiriesByCourse: (courseId) => apiClient.get(`/enquiries/course/${courseId}`)
};
```

---

## 3️⃣ Create React Components

### `src/components/CounsellorsList.js`
```javascript
import React, { useState, useEffect } from 'react';
import { counsellorsService } from '../services/counsellorsService';

export const CounsellorsList = () => {
  const [counsellors, setCounsellors] = useState([]);
  const [loading, setLoading] = useState(false);
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
      setError(err.response?.data?.message || 'Error fetching counsellors');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this counsellor?')) {
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
      <h2>Counsellors</h2>
      <table border="1" style={{ width: '100%' }}>
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
                <button>Edit</button>
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

### `src/components/CreateCounsellor.js`
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
      [name]: name === 'phoneNumber' ? parseInt(value) || '' : value
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
      setError(err.response?.data?.message || 'Error creating counsellor');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ border: '1px solid #ccc', padding: '20px' }}>
      <h3>Add New Counsellor</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <div>
        <label>Name:</label>
        <input
          type="text"
          name="name"
          placeholder="Full Name"
          value={formData.name}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <div>
        <label>Email:</label>
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <div>
        <label>Password:</label>
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <div>
        <label>Phone Number:</label>
        <input
          type="number"
          name="phoneNumber"
          placeholder="Phone Number"
          value={formData.phoneNumber}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <button type="submit" disabled={loading} style={{ padding: '10px 20px' }}>
        {loading ? 'Creating...' : 'Add Counsellor'}
      </button>
    </form>
  );
};
```

### `src/components/CoursesList.js`
```javascript
import React, { useState, useEffect } from 'react';
import { coursesService } from '../services/coursesService';

export const CoursesList = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    try {
      setLoading(true);
      const response = await coursesService.getAllCourses();
      setCourses(response.data.data || []);
      setError(null);
    } catch (err) {
      setError(err.response?.data?.message || 'Error fetching courses');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure?')) {
      try {
        await coursesService.deleteCourse(id);
        fetchCourses();
      } catch (err) {
        alert('Error deleting course');
      }
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Courses</h2>
      <table border="1" style={{ width: '100%' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Course Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {courses.map((course) => (
            <tr key={course.courseId}>
              <td>{course.courseId}</td>
              <td>{course.courseName}</td>
              <td>
                <button>Edit</button>
                <button onClick={() => handleDelete(course.courseId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
```

### `src/components/CreateEnquiry.js`
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
    fetchDropdownData();
  }, []);

  const fetchDropdownData = async () => {
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
      [name]: ['studentPhoneNumber', 'courseId', 'counsellorId'].includes(name) ? 
        (parseInt(value) || '') : value
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
      setError(err.response?.data?.message || 'Error creating enquiry');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ border: '1px solid #ccc', padding: '20px' }}>
      <h3>Create Enquiry</h3>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <div>
        <label>Student Name:</label>
        <input
          type="text"
          name="stuName"
          placeholder="Student Name"
          value={formData.stuName}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <div>
        <label>Phone Number:</label>
        <input
          type="number"
          name="studentPhoneNumber"
          placeholder="Phone"
          value={formData.studentPhoneNumber}
          onChange={handleChange}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
        />
      </div>

      <div>
        <label>Class Mode:</label>
        <select name="classMode" value={formData.classMode} onChange={handleChange}
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}>
          <option value="Online">Online</option>
          <option value="Offline">Offline</option>
          <option value="Hybrid">Hybrid</option>
        </select>
      </div>

      <div>
        <label>Status:</label>
        <select name="enqStatus" value={formData.enqStatus} onChange={handleChange}
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}>
          <option value="Pending">Pending</option>
          <option value="Approved">Approved</option>
          <option value="Rejected">Rejected</option>
        </select>
      </div>

      <div>
        <label>Counsellor:</label>
        <select name="counsellorId" value={formData.counsellorId} onChange={handleChange} required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}>
          <option value="">Select Counsellor</option>
          {counsellors.map(c => (
            <option key={c.counsellorId} value={c.counsellorId}>
              {c.name} ({c.email})
            </option>
          ))}
        </select>
      </div>

      <div>
        <label>Course:</label>
        <select name="courseId" value={formData.courseId} onChange={handleChange} required
          style={{ width: '100%', padding: '8px', marginBottom: '10px' }}>
          <option value="">Select Course</option>
          {courses.map(c => (
            <option key={c.courseId} value={c.courseId}>
              {c.courseName}
            </option>
          ))}
        </select>
      </div>

      <button type="submit" disabled={loading} style={{ padding: '10px 20px' }}>
        {loading ? 'Creating...' : 'Create Enquiry'}
      </button>
    </form>
  );
};
```

### `src/components/EnquiriesList.js`
```javascript
import React, { useState, useEffect } from 'react';
import { enquiriesService } from '../services/enquiriesService';

export const EnquiriesList = () => {
  const [enquiries, setEnquiries] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchEnquiries();
  }, []);

  const fetchEnquiries = async () => {
    try {
      setLoading(true);
      const response = await enquiriesService.getAllEnquiries();
      setEnquiries(response.data.data || []);
      setError(null);
    } catch (err) {
      setError(err.response?.data?.message || 'Error fetching enquiries');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure?')) {
      try {
        await enquiriesService.deleteEnquiry(id);
        fetchEnquiries();
      } catch (err) {
        alert('Error deleting enquiry');
      }
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Enquiries</h2>
      <table border="1" style={{ width: '100%', fontSize: '12px' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Student Name</th>
            <th>Phone</th>
            <th>Mode</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {enquiries.map((enquiry) => (
            <tr key={enquiry.enquiryId}>
              <td>{enquiry.enquiryId}</td>
              <td>{enquiry.stuName}</td>
              <td>{enquiry.studentPhoneNumber}</td>
              <td>{enquiry.classMode}</td>
              <td>{enquiry.enqStatus}</td>
              <td>
                <button>Edit</button>
                <button onClick={() => handleDelete(enquiry.enquiryId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
```

---

## 4️⃣ Main App Component

### `src/App.js`
```javascript
import React, { useState } from 'react';
import './App.css';
import { CounsellorsList } from './components/CounsellorsList';
import { CreateCounsellor } from './components/CreateCounsellor';
import { CoursesList } from './components/CoursesList';
import { EnquiriesList } from './components/EnquiriesList';
import { CreateEnquiry } from './components/CreateEnquiry';

function App() {
  const [activeTab, setActiveTab] = useState('counsellors');

  return (
    <div className="App">
      <header>
        <h1>Counsellors Portal</h1>
      </header>

      <nav style={{ marginBottom: '20px', borderBottom: '2px solid #333' }}>
        <button 
          onClick={() => setActiveTab('counsellors')}
          style={{ marginRight: '10px', fontWeight: activeTab === 'counsellors' ? 'bold' : 'normal' }}
        >
          Counsellors
        </button>
        <button 
          onClick={() => setActiveTab('courses')}
          style={{ marginRight: '10px', fontWeight: activeTab === 'courses' ? 'bold' : 'normal' }}
        >
          Courses
        </button>
        <button 
          onClick={() => setActiveTab('enquiries')}
          style={{ marginRight: '10px', fontWeight: activeTab === 'enquiries' ? 'bold' : 'normal' }}
        >
          Enquiries
        </button>
      </nav>

      <main>
        {activeTab === 'counsellors' && (
          <div>
            <CreateCounsellor />
            <hr />
            <CounsellorsList />
          </div>
        )}

        {activeTab === 'courses' && (
          <div>
            <CoursesList />
          </div>
        )}

        {activeTab === 'enquiries' && (
          <div>
            <CreateEnquiry />
            <hr />
            <EnquiriesList />
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
```

---

## 5️⃣ Environment Configuration

### `.env`
```
REACT_APP_API_BASE_URL=http://localhost:8080/api
```

---

## 6️⃣ CSS Styling (Optional)

### `src/App.css`
```css
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background: #f5f5f5;
}

.App {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
}

header h1 {
  font-size: 28px;
}

nav {
  display: flex;
  gap: 10px;
  padding: 10px 0;
}

nav button {
  padding: 10px 20px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: 0.3s;
}

nav button:hover {
  background: #764ba2;
}

main {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

table th {
  background: #667eea;
  color: white;
  padding: 12px;
  text-align: left;
}

table td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

table tr:hover {
  background: #f5f5f5;
}

button {
  padding: 8px 16px;
  margin: 5px;
  background: #667eea;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: 0.3s;
}

button:hover {
  background: #764ba2;
}

button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

input, select {
  width: 100%;
  padding: 10px;
  margin: 5px 0 15px 0;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

input:focus, select:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
}

h2, h3 {
  color: #333;
  margin-bottom: 15px;
}

hr {
  margin: 20px 0;
  border: none;
  border-top: 1px solid #ddd;
}

.error {
  color: #d32f2f;
  padding: 10px;
  background: #ffebee;
  border-radius: 4px;
  margin-bottom: 10px;
}
```

---

## 🎯 Usage Flow

1. **Install Dependencies**
   ```bash
   npm install
   ```

2. **Create Service Files** in `src/services/`

3. **Create Component Files** in `src/components/`

4. **Update App.js** with the code above

5. **Add Environment Variables** in `.env`

6. **Start Backend** (Maven)
   ```bash
   mvn spring-boot:run
   ```

7. **Start React App**
   ```bash
   npm start
   ```

8. **Test Integration**
   - Open http://localhost:3000
   - Create counsellor, course, and enquiry
   - Verify data in MySQL database

---

## ✅ Checklist

- [ ] Backend running on http://localhost:8080
- [ ] MySQL database created and connected
- [ ] React project created
- [ ] Axios installed
- [ ] API services created
- [ ] Components created
- [ ] App.js updated
- [ ] .env configured
- [ ] Frontend running on http://localhost:3000
- [ ] Tested CRUD operations

---

**Happy Coding! 🚀**

