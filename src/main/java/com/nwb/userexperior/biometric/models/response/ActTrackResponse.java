package com.nwb.userexperior.biometric.models.response;

import com.nwb.userexperior.biometric.persistence.Staff;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ActTrackResponse {
    List<Staff> staffs = new ArrayList<>();
    List<String> errors = new ArrayList<>();
}
