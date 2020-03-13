package com.nwb.userexperior.biometric.mapper;


import com.nwb.userexperior.biometric.mapper.factory.ActivityObjectFactory;
import com.nwb.userexperior.biometric.mapper.factory.StaffObjectFactory;
import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.Staff;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = ActivityObjectFactory.class
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ActivityMapper
        extends AbstractMapper<Activity, Activity> {

    @Override
    Activity toDestination(Activity sourceObject);

    @Named("bcd")
    Activity toDestination(Activity sourceObject, @Context Staff staff);

    @IterableMapping(qualifiedByName = "bcd")
    List<Activity> toDestination(List<Activity> sourceObject, @Context Staff staff);
}
