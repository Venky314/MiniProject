package com.ashok.it.counsellorsportal.Mapper;

import com.ashok.it.counsellorsportal.Dto.CoursesDTO;
import com.ashok.it.counsellorsportal.Model.courses;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {

    public CoursesDTO toCoursesDTO(courses course) {
        if (course == null) {
            return null;
        }
        CoursesDTO dto = new CoursesDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        return dto;
    }

    public courses toCoursesEntity(CoursesDTO dto) {
        if (dto == null) {
            return null;
        }
        courses course = new courses();
        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        return course;
    }
}

