package com.nwb.userexperior.biometric.models.response;

import com.nwb.userexperior.biometric.persistence.ActivityType;
import lombok.Builder;
import lombok.Data;

public interface WeeklyActivityOccurencesReportProjection {
    ActivityType getActivityName();

    long getOccurrences();
}
