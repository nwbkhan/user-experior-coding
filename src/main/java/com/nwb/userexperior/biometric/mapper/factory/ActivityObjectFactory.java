package com.nwb.userexperior.biometric.mapper.factory;

import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.Staff;
import com.nwb.userexperior.biometric.repo.ActivityRepo;
import com.nwb.userexperior.biometric.repo.StaffRepo;
import org.mapstruct.Context;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityObjectFactory {

    @Autowired
    ActivityRepo activityRepo;

    @ObjectFactory
    public Activity forActivity(Activity activity, @Context Staff staff) {
        return activityRepo
                .findByNameAndStartTimeAndStaffStaffId(activity.getName(),
                        activity.getStartTime(),
                        staff.getStaffId())
                .orElseGet(Activity::new);
    }
}
