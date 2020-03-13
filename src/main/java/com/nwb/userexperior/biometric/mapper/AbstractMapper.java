package com.nwb.userexperior.biometric.mapper;

import java.util.List;

public interface AbstractMapper<S, D> {

    D toDestination(S sourceObject);

    List<D> toDestination(List<S> sourceObject);

}