package com.nwb.userexperior.biometric.repo;

import com.nwb.userexperior.biometric.models.response.WeeklyActivityOccurencesReportProjection;
import com.nwb.userexperior.biometric.persistence.Activity;
import com.nwb.userexperior.biometric.persistence.ActivityType;
import com.nwb.userexperior.biometric.persistence.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ActivityRepo extends JpaRepository<Activity, Long> {
    Optional<Activity> findByNameAndStartTimeAndStaffStaffId(ActivityType type,
                                                             Date statTime,
                                                             Long staffId);

    @Query("select a.name as activityName, count(a.name) as occurrences " +
            " from Activity a where a.startTime >= :lastWeekDate " +
            " group by a.name ")
    List<WeeklyActivityOccurencesReportProjection> groupByActOccurrences(@Temporal(value = TemporalType.DATE)
                                                                         @Param(value = "lastWeekDate") Date lastWeekDate);


    List<Activity> findByStaffIdOrderByStartTimeAsc(Long staffId);


}
