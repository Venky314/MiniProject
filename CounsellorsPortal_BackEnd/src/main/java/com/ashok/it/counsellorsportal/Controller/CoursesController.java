package com.ashok.it.counsellorsportal.Controller;

import com.ashok.it.counsellorsportal.Dto.CoursesDTO;
import com.ashok.it.counsellorsportal.Exception.ApiResponse;
import com.ashok.it.counsellorsportal.Service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CoursesDTO coursesDTO) {
        try {
            CoursesDTO created = coursesService.createCourse(coursesDTO);
            return new ResponseEntity<>(new ApiResponse(201, "Course created successfully", created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error creating course: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
        try {
            CoursesDTO course = coursesService.getCourseById(courseId);
            return new ResponseEntity<>(new ApiResponse(200, "Course retrieved successfully", course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(404, "Course not found: " + e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        try {
            List<CoursesDTO> courses = coursesService.getAllCourses();
            return new ResponseEntity<>(new ApiResponse(200, "All courses retrieved successfully", courses), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "Error fetching courses: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId, @RequestBody CoursesDTO coursesDTO) {
        try {
            CoursesDTO updated = coursesService.updateCourse(courseId, coursesDTO);
            return new ResponseEntity<>(new ApiResponse(200, "Course updated successfully", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error updating course: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        try {
            coursesService.deleteCourse(courseId);
            return new ResponseEntity<>(new ApiResponse(200, "Course deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error deleting course: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

