package com.ashok.it.counsellorsportal.Controller;

import com.ashok.it.counsellorsportal.Dto.EnquiriesDTO;
import com.ashok.it.counsellorsportal.Exception.ApiResponse;
import com.ashok.it.counsellorsportal.Service.EnquiriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/enquiries")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnquiriesController {

    @Autowired
    private EnquiriesService enquiriesService;

    @PostMapping
    public ResponseEntity<?> createEnquiry(@RequestBody EnquiriesDTO enquiriesDTO) {
        try {
            EnquiriesDTO created = enquiriesService.createEnquiry(enquiriesDTO);
            return new ResponseEntity<>(new ApiResponse(201, "Enquiry created successfully", created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error creating enquiry: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{enquiryId}")
    public ResponseEntity<?> getEnquiryById(@PathVariable Long enquiryId) {
        try {
            EnquiriesDTO enquiry = enquiriesService.getEnquiryById(enquiryId);
            return new ResponseEntity<>(new ApiResponse(200, "Enquiry retrieved successfully", enquiry), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(404, "Enquiry not found: " + e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEnquiries() {
        try {
            List<EnquiriesDTO> enquiries = enquiriesService.getAllEnquiries();
            return new ResponseEntity<>(new ApiResponse(200, "All enquiries retrieved successfully", enquiries), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "Error fetching enquiries: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{enquiryId}")
    public ResponseEntity<?> updateEnquiry(@PathVariable Long enquiryId, @RequestBody EnquiriesDTO enquiriesDTO) {
        try {
            EnquiriesDTO updated = enquiriesService.updateEnquiry(enquiryId, enquiriesDTO);
            return new ResponseEntity<>(new ApiResponse(200, "Enquiry updated successfully", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error updating enquiry: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{enquiryId}")
    public ResponseEntity<?> deleteEnquiry(@PathVariable Long enquiryId) {
        try {
            enquiriesService.deleteEnquiry(enquiryId);
            return new ResponseEntity<>(new ApiResponse(200, "Enquiry deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error deleting enquiry: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/counsellor/{counsellorId}")
    public ResponseEntity<?> getEnquiriesByCounsellor(@PathVariable Long counsellorId) {
        try {
            List<EnquiriesDTO> enquiries = enquiriesService.getEnquiriesByCounsellor(counsellorId);
            return new ResponseEntity<>(new ApiResponse(200, "Enquiries retrieved successfully", enquiries), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "Error fetching enquiries: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getEnquiriesByCourse(@PathVariable int courseId) {
        try {
            List<EnquiriesDTO> enquiries = enquiriesService.getEnquiriesByCourse(courseId);
            return new ResponseEntity<>(new ApiResponse(200, "Enquiries retrieved successfully", enquiries), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "Error fetching enquiries: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

