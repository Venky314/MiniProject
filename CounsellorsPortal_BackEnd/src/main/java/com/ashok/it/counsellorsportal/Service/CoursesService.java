package com.ashok.it.counsellorsportal.Service;

import com.ashok.it.counsellorsportal.Dto.CoursesDTO;
import java.util.List;

public interface CoursesService {
    CoursesDTO createCourse(CoursesDTO coursesDTO);
    CoursesDTO getCourseById(int courseId);
    List<CoursesDTO> getAllCourses();
    CoursesDTO updateCourse(int courseId, CoursesDTO coursesDTO);
    void deleteCourse(int courseId);
}

