package com.ashok.it.counsellorsportal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnquiriesDTO {
    private Long enquiryId;
    private String stuName;
    private Long studentPhoneNumber;
    private String classMode;
    private String enqStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long counsellorId;
    private int courseId;
}

