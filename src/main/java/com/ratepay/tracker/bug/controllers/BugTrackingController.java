package com.ratepay.tracker.bug.controllers;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugStatus;
import com.ratepay.tracker.bug.services.BugService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bugs")
@AllArgsConstructor
public class BugTrackingController {
    private BugService bugService;

    @GetMapping
    public List<BugDto> getAllBugs() {
        return bugService.getAll();
    }

    @GetMapping("/{id}")
    public BugDto getById(String id) {
        return bugService.getById(id); //TODO handle the error and return a status
    }

    @PostMapping
    public void createBug(@RequestBody BugCreationRequest bugCreationRequest) {
        bugService.createBug(bugCreationRequest); //TODO handle the error and return a status
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

    @PutMapping("status/{id}/{status}")
    public BugDto updateStatus(@PathVariable String id, @PathVariable BugStatus status) {
        return bugService.updateStatus(id, status); //TODO handle the error and return a status
    }

    @PutMapping("assign/{id}/{assignedUser}")
    public BugDto assign(@PathVariable String id, @PathVariable String assignedUser) {
        return bugService.assignTo(id, assignedUser); //TODO handle the error and return a status
    }

}
