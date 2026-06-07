package com.ashok.it.counsellorsportal.Repository;

import com.ashok.it.counsellorsportal.Model.enquiries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<enquiries, Long> {
    List<enquiries> findByCounsellorCounsellorId(Long counsellorId);
    List<enquiries> findByCourseCourseId(int courseId);
}


