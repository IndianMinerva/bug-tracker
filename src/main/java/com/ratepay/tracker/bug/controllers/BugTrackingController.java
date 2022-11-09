package com.ratepay.tracker.bug.controllers;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
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
}
