package com.ratepay.tracker.bug.controllers;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugStatus;
import com.ratepay.tracker.bug.services.BugService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bug")
@AllArgsConstructor
public class BugTrackingController {
    private BugService bugService;

    @GetMapping
    public List<BugDto> getAllBugs() {
        return bugService.getAll();
    }

    @PostMapping
    public void createBug(@RequestBody BugCreationRequest bugCreationRequest) {
        bugService.createBug(bugCreationRequest);
    }

    @GetMapping("/status/{status}")
    public List<BugDto> getByStatus(@PathVariable BugStatus status) {
        return bugService.getByStatus(status);
    }

    @GetMapping("/created/{createdUser}")
    public List<BugDto> getByCreatedUser(@PathVariable String createdUser) {
        return bugService.getByCreatedUser(createdUser);
    }

    @GetMapping("/assigned/{assignedUser}")
    public List<BugDto> getByAssignedUser(@PathVariable String assignedUser) {
        return bugService.getByAssignedUser(assignedUser);
    }
}
