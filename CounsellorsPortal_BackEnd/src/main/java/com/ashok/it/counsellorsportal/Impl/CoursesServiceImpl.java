package com.ashok.it.counsellorsportal.Impl;

import com.ashok.it.counsellorsportal.Dto.CoursesDTO;
import com.ashok.it.counsellorsportal.Mapper.CoursesMapper;
import com.ashok.it.counsellorsportal.Model.courses;
import com.ashok.it.counsellorsportal.Repository.coursesRepository;
import com.ashok.it.counsellorsportal.Service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private coursesRepository courseRepository;

    @Autowired
    private CoursesMapper courseMapper;

    @Override
    public CoursesDTO createCourse(CoursesDTO coursesDTO) {
        courses course = courseMapper.toCoursesEntity(coursesDTO);
        course = courseRepository.save(course);
        return courseMapper.toCoursesDTO(course);
    }

    @Override
    public CoursesDTO getCourseById(int courseId) {
        courses course = courseRepository.findById((long) courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return courseMapper.toCoursesDTO(course);
    }

    @Override
    public List<CoursesDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toCoursesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CoursesDTO updateCourse(int courseId, CoursesDTO coursesDTO) {
        courses course = courseRepository.findById((long) courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        course.setCourseName(coursesDTO.getCourseName());
        course = courseRepository.save(course);
        return courseMapper.toCoursesDTO(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courses course = courseRepository.findById((long) courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        courseRepository.delete(course);
    }
}

