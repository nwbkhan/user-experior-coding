package com.nwb.userexperior.biometric.mapper;

import com.nwb.userexperior.biometric.models.response.ActivityTodaysDto;
import com.nwb.userexperior.biometric.models.response.StaffTodayActivityDto;
import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.Staff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StaffToTodaysActResponseMapper
        extends AbstractMapper<Staff, StaffTodayActivityDto> {


    @Override
    StaffTodayActivityDto toDestination(Staff sourceObject);

    @Override
    List<StaffTodayActivityDto> toDestination(List<Staff> sourceObject);

    List<ActivityTodaysDto> toDestinationForActivities(List<Activity> sourceObject);

    ActivityTodaysDto toDestinationForActivity(Activity sourceObject);

}
