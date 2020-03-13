package com.nwb.userexperior.biometric.models.response;

import lombok.Data;

import java.util.List;

@Data
public class StaffTodayActivityDto {

    Long staffId;
    List<ActivityTodaysDto> activities;
}
