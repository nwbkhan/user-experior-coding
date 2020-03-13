package com.nwb.userexperior.biometric.models.response;

import com.nwb.userexperior.biometric.persistence.ActivityType;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityTodaysDto {

    ActivityType name;
    Date startTime;
}
