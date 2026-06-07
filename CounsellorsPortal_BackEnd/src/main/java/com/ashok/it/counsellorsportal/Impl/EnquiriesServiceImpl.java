package com.ashok.it.counsellorsportal.Impl;

import com.ashok.it.counsellorsportal.Dto.EnquiriesDTO;
import com.ashok.it.counsellorsportal.Mapper.EnquiriesMapper;
import com.ashok.it.counsellorsportal.Model.enquiries;
import com.ashok.it.counsellorsportal.Model.counsellors;
import com.ashok.it.counsellorsportal.Model.courses;
import com.ashok.it.counsellorsportal.Repository.EnquiryRepository;
import com.ashok.it.counsellorsportal.Repository.counsellorsRepository;
import com.ashok.it.counsellorsportal.Repository.coursesRepository;
import com.ashok.it.counsellorsportal.Service.EnquiriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiriesServiceImpl implements EnquiriesService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private counsellorsRepository counsellorRepository;

    @Autowired
    private coursesRepository courseRepository;

    @Autowired
    private EnquiriesMapper enquiriesMapper;

    @Override
    public EnquiriesDTO createEnquiry(EnquiriesDTO enquiriesDTO) {
        enquiries enquiry = enquiriesMapper.toEnquiriesEntity(enquiriesDTO);

        counsellors counsellor = counsellorRepository.findById(enquiriesDTO.getCounsellorId())
                .orElseThrow(() -> new RuntimeException("Counsellor not found with id: " + enquiriesDTO.getCounsellorId()));

        courses course = courseRepository.findById((long) enquiriesDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + enquiriesDTO.getCourseId()));

        enquiry.setCounsellor(counsellor);
        enquiry.setCourse(course);
        enquiry.setCreatedDate(LocalDateTime.now());
        enquiry.setUpdatedDate(LocalDateTime.now());

        enquiry = enquiryRepository.save(enquiry);
        return enquiriesMapper.toEnquiriesDTO(enquiry);
    }

    @Override
    public EnquiriesDTO getEnquiryById(Long enquiryId) {
        enquiries enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + enquiryId));
        return enquiriesMapper.toEnquiriesDTO(enquiry);
    }

    @Override
    public List<EnquiriesDTO> getAllEnquiries() {
        return enquiryRepository.findAll()
                .stream()
                .map(enquiriesMapper::toEnquiriesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnquiriesDTO updateEnquiry(Long enquiryId, EnquiriesDTO enquiriesDTO) {
        enquiries enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + enquiryId));

        if (!enquiry.getCounsellor().getCounsellorId().equals(enquiriesDTO.getCounsellorId())) {
            counsellors counsellor = counsellorRepository.findById(enquiriesDTO.getCounsellorId())
                    .orElseThrow(() -> new RuntimeException("Counsellor not found with id: " + enquiriesDTO.getCounsellorId()));
            enquiry.setCounsellor(counsellor);
        }

        if (enquiry.getCourse().getCourseId() != enquiriesDTO.getCourseId()) {
            courses course = courseRepository.findById((long) enquiriesDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + enquiriesDTO.getCourseId()));
            enquiry.setCourse(course);
        }

        enquiry.setStuName(enquiriesDTO.getStuName());
        enquiry.setStudentPhoneNumber(enquiriesDTO.getStudentPhoneNumber());
        enquiry.setClassMode(enquiriesDTO.getClassMode());
        enquiry.setEnqStatus(enquiriesDTO.getEnqStatus());
        enquiry.setUpdatedDate(LocalDateTime.now());

        enquiry = enquiryRepository.save(enquiry);
        return enquiriesMapper.toEnquiriesDTO(enquiry);
    }

    @Override
    public void deleteEnquiry(Long enquiryId) {
        enquiries enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + enquiryId));
        enquiryRepository.delete(enquiry);
    }

    @Override
    public List<EnquiriesDTO> getEnquiriesByCounsellor(Long counsellorId) {
        return enquiryRepository.findByCounsellorCounsellorId(counsellorId)
                .stream()
                .map(enquiriesMapper::toEnquiriesDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnquiriesDTO> getEnquiriesByCourse(int courseId) {
        return enquiryRepository.findByCourseCourseId(courseId)
                .stream()
                .map(enquiriesMapper::toEnquiriesDTO)
                .collect(Collectors.toList());
    }
}

