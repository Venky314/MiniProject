package com.ashok.it.counsellorsportal.Controller;

import com.ashok.it.counsellorsportal.Dto.CounsellorsDTO;
import com.ashok.it.counsellorsportal.Exception.ApiResponse;
import com.ashok.it.counsellorsportal.Service.CounsellorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/counsellors")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CounsellorsController {

    @Autowired
    private CounsellorsService counsellorsService;

    @PostMapping
    public ResponseEntity<?> createCounsellor(@RequestBody CounsellorsDTO counsellorDTO) {
        try {
            CounsellorsDTO created = counsellorsService.createCounsellor(counsellorDTO);
            return new ResponseEntity<>(new ApiResponse(201, "Counsellor created successfully", created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error creating counsellor: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{counsellorId}")
    public ResponseEntity<?> getCounsellorById(@PathVariable Long counsellorId) {
        try {
            CounsellorsDTO counsellor = counsellorsService.getCounsellorById(counsellorId);
            return new ResponseEntity<>(new ApiResponse(200, "Counsellor retrieved successfully", counsellor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(404, "Counsellor not found: " + e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCounsellors() {
        try {
            List<CounsellorsDTO> counsellors = counsellorsService.getAllCounsellors();
            return new ResponseEntity<>(new ApiResponse(200, "All counsellors retrieved successfully", counsellors), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(500, "Error fetching counsellors: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{counsellorId}")
    public ResponseEntity<?> updateCounsellor(@PathVariable Long counsellorId, @RequestBody CounsellorsDTO counsellorDTO) {
        try {
            CounsellorsDTO updated = counsellorsService.updateCounsellor(counsellorId, counsellorDTO);
            return new ResponseEntity<>(new ApiResponse(200, "Counsellor updated successfully", updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error updating counsellor: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{counsellorId}")
    public ResponseEntity<?> deleteCounsellor(@PathVariable Long counsellorId) {
        try {
            counsellorsService.deleteCounsellor(counsellorId);
            return new ResponseEntity<>(new ApiResponse(200, "Counsellor deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(400, "Error deleting counsellor: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

