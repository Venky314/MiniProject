package com.ashok.it.counsellorsportal.Repository;

import com.ashok.it.counsellorsportal.Model.counsellors;
import com.ashok.it.counsellorsportal.Model.courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface counsellorsRepository extends JpaRepository<counsellors, Long> {

}
