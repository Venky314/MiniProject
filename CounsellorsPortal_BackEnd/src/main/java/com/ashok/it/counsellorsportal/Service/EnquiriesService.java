package com.ashok.it.counsellorsportal.Service;

import com.ashok.it.counsellorsportal.Dto.EnquiriesDTO;
import java.util.List;

public interface EnquiriesService {
    EnquiriesDTO createEnquiry(EnquiriesDTO enquiriesDTO);
    EnquiriesDTO getEnquiryById(Long enquiryId);
    List<EnquiriesDTO> getAllEnquiries();
    EnquiriesDTO updateEnquiry(Long enquiryId, EnquiriesDTO enquiriesDTO);
    void deleteEnquiry(Long enquiryId);
    List<EnquiriesDTO> getEnquiriesByCounsellor(Long counsellorId);
    List<EnquiriesDTO> getEnquiriesByCourse(int courseId);
}

