package com.nwb.userexperior.biometric.service;

import com.nwb.userexperior.biometric.mapper.StaffMapper;
import com.nwb.userexperior.biometric.mapper.StaffToTodaysActResponseMapper;
import com.nwb.userexperior.biometric.models.response.ActTrackResponse;
import com.nwb.userexperior.biometric.models.response.StaffTodayActivityDto;
import com.nwb.userexperior.biometric.models.response.WeeklyActivityOccurencesReportProjection;
import com.nwb.userexperior.biometric.models.response.WeeklyReportResponse;
import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.Staff;
import com.nwb.userexperior.biometric.repo.ActivityRepo;
import com.nwb.userexperior.biometric.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ActivityTrackingServiceImpl implements ActivityTrackingService {

    @Value("${act-process-dir}")
    String actRecordsDir;

    private final RecordProcessService recordProcessService;
    private final StaffMapper staffMapper;
    private final ActivityRepo activityRepo;
    private final StaffRepo staffRepo;
    private final StaffToTodaysActResponseMapper staffToTodaysActResponseMapper;

    public ActivityTrackingServiceImpl(RecordProcessService recordProcessService,
                                       StaffMapper staffMapper,
                                       ActivityRepo activityRepo,
                                       StaffRepo staffRepo,
                                       StaffToTodaysActResponseMapper staffToTodaysActResponseMapper) {
        this.recordProcessService = recordProcessService;
        this.staffMapper = staffMapper;
        this.activityRepo = activityRepo;
        this.staffRepo = staffRepo;
        this.staffToTodaysActResponseMapper = staffToTodaysActResponseMapper;
    }

    @Override
    // this can be also done with the help of CORN
    // but for demo purpose i have created post Request Api
    public List<String> processJson() {
        final ActTrackResponse response =
                recordProcessService.process(actRecordsDir);

        //process staffs and save it to the database

        final List<Staff> staffsTobeSaved = response.getStaffs();

        final List<Staff> staffs = staffMapper.toDestination(staffsTobeSaved);

        staffRepo.saveAll(staffs);
        staffRepo.flush();
        return response.getErrors();
    }

    @Override
    public WeeklyReportResponse getWeeklyReport() {

        Calendar calendar = getPreviousWeek();

        // get activities for occurences count
        final List<WeeklyActivityOccurencesReportProjection> weeklyReport =
                activityRepo.groupByActOccurrences(calendar.getTime());

        final List<Staff> todaysActs =
                staffRepo.getTodayActs();

        // get all of the activities for ascending order becoz we can get them in one query
        todaysActs.forEach(staff -> {
            final List<Activity> activities = activityRepo.findByStaffIdOrderByStartTimeAsc(staff.getId());
            staff.setActivities(activities);
        });

        // map for projection
        final List<StaffTodayActivityDto> staffTodayActivityDtos =
                staffToTodaysActResponseMapper.toDestination(todaysActs);

        return WeeklyReportResponse
                .builder()
                .allStaffsLast7DaysActivities(weeklyReport)
                .todayActivities(staffTodayActivityDtos)
                .build();
    }

    private Calendar getPreviousWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        return calendar;
    }
}
