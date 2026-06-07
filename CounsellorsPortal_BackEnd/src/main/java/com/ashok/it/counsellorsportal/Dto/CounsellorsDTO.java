package com.ashok.it.counsellorsportal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounsellorsDTO {
    private Long counsellorId;
    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

