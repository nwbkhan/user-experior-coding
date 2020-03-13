package com.nwb.userexperior.biometric.api;


import com.nwb.userexperior.biometric.ApiResponse;
import com.nwb.userexperior.biometric.models.response.WeeklyReportResponse;
import com.nwb.userexperior.biometric.service.ActivityTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.nwb.userexperior.biometric.PathConstants.ACT_TRACK_PATH;
import static com.nwb.userexperior.biometric.PathConstants.GET_WEEKLY_REPORt_PATH;
import static com.nwb.userexperior.biometric.PathConstants.POST_PRCESS_PATH;

@RestController
@RequestMapping(ACT_TRACK_PATH)
public class ActivityTrackingApplicationServiceController {

    private final ActivityTrackingService activityTrackingService;

    public ActivityTrackingApplicationServiceController(ActivityTrackingService activityTrackingService) {
        this.activityTrackingService = activityTrackingService;
    }

    // get the weekly reports
    @GetMapping(value = GET_WEEKLY_REPORt_PATH)
    public ResponseEntity<ApiResponse> getWeeklyReport() {
        final WeeklyReportResponse weeklyReport = activityTrackingService.getWeeklyReport();
        return ResponseEntity.ok(ApiResponse.of(weeklyReport));
    }

    // Processing the json files
    @PostMapping(value = POST_PRCESS_PATH)
    public ResponseEntity<ApiResponse> processReports() {
        final List<String> errors = activityTrackingService.processJson();

        return ResponseEntity.ok(ApiResponse.of(errors));

    }
}
