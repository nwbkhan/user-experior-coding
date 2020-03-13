package com.nwb.userexperior.biometric;


import com.nwb.userexperior.biometric.exception.ActTrackException;

public class ThrowableUtil {


    public static Throwable getRootCause(Throwable ex) {
        if (ex == null) return new ActTrackException("System caught error");
        Throwable cause = ex;
        while (cause.getCause() != null && cause != cause.getCause()) {
            cause = cause.getCause();
        }
        return cause;
    }
}
