package com.nwb.userexperior.biometric.service;

import com.nwb.userexperior.biometric.models.response.WeeklyReportResponse;

import java.util.List;

public interface ActivityTrackingService {
    List<String> processJson();

    WeeklyReportResponse getWeeklyReport();
}
