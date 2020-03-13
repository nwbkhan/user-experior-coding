package com.nwb.userexperior.biometric.repo;

import com.nwb.userexperior.biometric.persistence.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffRepo extends JpaRepository<Staff, Long> {

    Optional<Staff> findByStaffId(Long staffId);

    @Query("select distinct s " +
            " from Staff s " +
            " left join s.activities acts on acts.startTime >= current_date " +
            " order by s.staffId desc ")
    List<Staff> getTodayActs();
}