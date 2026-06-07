package com.ashok.it.counsellorsportal.Impl;

import com.ashok.it.counsellorsportal.Dto.CounsellorsDTO;
import com.ashok.it.counsellorsportal.Mapper.CounsellorsMapper;
import com.ashok.it.counsellorsportal.Model.counsellors;
import com.ashok.it.counsellorsportal.Repository.counsellorsRepository;
import com.ashok.it.counsellorsportal.Service.CounsellorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounsellorsServiceImpl implements CounsellorsService {

    @Autowired
    private counsellorsRepository counsellorRepository;

    @Autowired
    private CounsellorsMapper counsellorMapper;

    @Override
    public CounsellorsDTO createCounsellor(CounsellorsDTO counsellorDTO) {
        counsellors counsellor = counsellorMapper.toCounsellorsEntity(counsellorDTO);
        counsellor.setCreatedAt(LocalDateTime.now());
        counsellor.setUpdatedAt(LocalDateTime.now());
        counsellor = counsellorRepository.save(counsellor);
        return counsellorMapper.toCounsellorsDTO(counsellor);
    }

    @Override
    public CounsellorsDTO getCounsellorById(Long counsellorId) {
        counsellors counsellor = counsellorRepository.findById(counsellorId)
                .orElseThrow(() -> new RuntimeException("Counsellor not found with id: " + counsellorId));
        return counsellorMapper.toCounsellorsDTO(counsellor);
    }

    @Override
    public List<CounsellorsDTO> getAllCounsellors() {
        return counsellorRepository.findAll()
                .stream()
                .map(counsellorMapper::toCounsellorsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CounsellorsDTO updateCounsellor(Long counsellorId, CounsellorsDTO counsellorDTO) {
        counsellors counsellor = counsellorRepository.findById(counsellorId)
                .orElseThrow(() -> new RuntimeException("Counsellor not found with id: " + counsellorId));
        counsellor.setName(counsellorDTO.getName());
        counsellor.setEmail(counsellorDTO.getEmail());
        counsellor.setPassword(counsellorDTO.getPassword());
        counsellor.setPhoneNumber(counsellorDTO.getPhoneNumber());
        counsellor.setUpdatedAt(LocalDateTime.now());
        counsellor = counsellorRepository.save(counsellor);
        return counsellorMapper.toCounsellorsDTO(counsellor);
    }

    @Override
    public void deleteCounsellor(Long counsellorId) {
        counsellors counsellor = counsellorRepository.findById(counsellorId)
                .orElseThrow(() -> new RuntimeException("Counsellor not found with id: " + counsellorId));
        counsellorRepository.delete(counsellor);
    }
}

