package com.nwb.userexperior.biometric.models.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WeeklyReportResponse {
    List<WeeklyActivityOccurencesReportProjection> allStaffsLast7DaysActivities;
    List<StaffTodayActivityDto> todayActivities;
}
