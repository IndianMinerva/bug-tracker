package com.ratepay.tracker.bug.services;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugStatus;

import java.util.List;

public interface BugService {
    void createBug(BugCreationRequest bugCreationRequest);
    List<BugDto> getAll();
    BugDto getById(Long id);
    List<BugDto> getByStatus(BugStatus status);
    List<BugDto> updateStatus(Long id, BugStatus status);
}