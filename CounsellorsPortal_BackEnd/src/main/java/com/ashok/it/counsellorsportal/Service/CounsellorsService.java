package com.ashok.it.counsellorsportal.Service;

import com.ashok.it.counsellorsportal.Dto.CounsellorsDTO;
import java.util.List;

public interface CounsellorsService {
    CounsellorsDTO createCounsellor(CounsellorsDTO counsellorDTO);
    CounsellorsDTO getCounsellorById(Long counsellorId);
    List<CounsellorsDTO> getAllCounsellors();
    CounsellorsDTO updateCounsellor(Long counsellorId, CounsellorsDTO counsellorDTO);
    void deleteCounsellor(Long counsellorId);
}

