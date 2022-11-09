package com.ratepay.tracker.bug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BugCreationRequest {
    private String title;
    private String description;
    private String createdBy;
}
