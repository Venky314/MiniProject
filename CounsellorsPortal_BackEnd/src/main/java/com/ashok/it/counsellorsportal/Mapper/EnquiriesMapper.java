package com.ashok.it.counsellorsportal.Mapper;

import com.ashok.it.counsellorsportal.Dto.EnquiriesDTO;
import com.ashok.it.counsellorsportal.Model.enquiries;
import org.springframework.stereotype.Component;

@Component
public class EnquiriesMapper {

    public EnquiriesDTO toEnquiriesDTO(enquiries enquiry) {
        if (enquiry == null) {
            return null;
        }
        EnquiriesDTO dto = new EnquiriesDTO();
        dto.setEnquiryId(enquiry.getEnquiryId());
        dto.setStuName(enquiry.getStuName());
        dto.setStudentPhoneNumber(enquiry.getStudentPhoneNumber());
        dto.setClassMode(enquiry.getClassMode());
        dto.setEnqStatus(enquiry.getEnqStatus());
        dto.setCreatedDate(enquiry.getCreatedDate());
        dto.setUpdatedDate(enquiry.getUpdatedDate());
        if (enquiry.getCounsellor() != null) {
            dto.setCounsellorId(enquiry.getCounsellor().getCounsellorId());
        }
        if (enquiry.getCourse() != null) {
            dto.setCourseId(enquiry.getCourse().getCourseId());
        }
        return dto;
    }

    public enquiries toEnquiriesEntity(EnquiriesDTO dto) {
        if (dto == null) {
            return null;
        }
        enquiries enquiry = new enquiries();
        enquiry.setEnquiryId(dto.getEnquiryId());
        enquiry.setStuName(dto.getStuName());
        enquiry.setStudentPhoneNumber(dto.getStudentPhoneNumber());
        enquiry.setClassMode(dto.getClassMode());
        enquiry.setEnqStatus(dto.getEnqStatus());
        enquiry.setCreatedDate(dto.getCreatedDate());
        enquiry.setUpdatedDate(dto.getUpdatedDate());
        return enquiry;
    }
}

