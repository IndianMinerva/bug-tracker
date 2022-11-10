package com.ratepay.tracker.bug.services;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugStatus;

import java.util.List;

public interface BugService {
    void createBug(BugCreationRequest bugCreationRequest);

    List<BugDto> getAll();

    BugDto getById(String id);

    List<BugDto> getByStatus(BugStatus status);

    List<BugDto> getByCreatedUser(String createdUser);

    List<BugDto> getByAssignedUser(String createdUser);

    BugDto updateStatus(String id, BugStatus status);

    BugDto assignTo(String id, String assignedUser);  //ruling out the case of assigned with a state of RESOLVED
}
