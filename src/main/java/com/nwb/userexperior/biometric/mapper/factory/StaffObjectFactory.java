package com.nwb.userexperior.biometric.mapper.factory;

import com.nwb.userexperior.biometric.persistence.Staff;
import com.nwb.userexperior.biometric.repo.StaffRepo;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffObjectFactory {

    @Autowired
    StaffRepo staffRepo;

    @ObjectFactory
    public Staff forStaff(Staff staff) {
        return staffRepo
                .findByStaffId(staff.getStaffId())
                .orElseGet(Staff::new);
    }
}
