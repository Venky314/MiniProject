package com.ashok.it.counsellorsportal.Mapper;

import com.ashok.it.counsellorsportal.Dto.CounsellorsDTO;
import com.ashok.it.counsellorsportal.Model.counsellors;
import org.springframework.stereotype.Component;

@Component
public class CounsellorsMapper {

    public CounsellorsDTO toCounsellorsDTO(counsellors counsellor) {
        if (counsellor == null) {
            return null;
        }
        CounsellorsDTO dto = new CounsellorsDTO();
        dto.setCounsellorId(counsellor.getCounsellorId());
        dto.setName(counsellor.getName());
        dto.setEmail(counsellor.getEmail());
        dto.setPassword(counsellor.getPassword());
        dto.setPhoneNumber(counsellor.getPhoneNumber());
        dto.setCreatedAt(counsellor.getCreatedAt());
        dto.setUpdatedAt(counsellor.getUpdatedAt());
        return dto;
    }

    public counsellors toCounsellorsEntity(CounsellorsDTO dto) {
        if (dto == null) {
            return null;
        }
        counsellors counsellor = new counsellors();
        counsellor.setCounsellorId(dto.getCounsellorId());
        counsellor.setName(dto.getName());
        counsellor.setEmail(dto.getEmail());
        counsellor.setPassword(dto.getPassword());
        counsellor.setPhoneNumber(dto.getPhoneNumber());
        counsellor.setCreatedAt(dto.getCreatedAt());
        counsellor.setUpdatedAt(dto.getUpdatedAt());
        return counsellor;
    }
}

