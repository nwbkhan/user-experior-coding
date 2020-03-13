package com.nwb.userexperior.biometric.mapper;


import com.nwb.userexperior.biometric.mapper.factory.StaffObjectFactory;
import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.Staff;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {
        StaffObjectFactory.class
}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class StaffMapper
        implements AbstractMapper<Staff, Staff> {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    @Mappings({
            @Mapping(source = "activities", target = "activities", ignore = true)
    })
    public abstract Staff toDestination(Staff sourceObject);

    @AfterMapping
    void afterMap(Staff staff, @MappingTarget Staff target) {

        // this needs to be done here becoz we staff from parent (activity)
        final List<Activity> activities = activityMapper.toDestination(staff.getActivities(), staff);

        target.setActivities(activities);

        if (target.getActivities() != null) {
            target.getActivities().forEach(activity -> activity.setStaff(target));
        }
    }
}
