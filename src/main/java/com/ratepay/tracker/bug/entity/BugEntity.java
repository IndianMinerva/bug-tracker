package com.ratepay.tracker.bug.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BugEntity {
    private String id;

    private String title;
    private String description;
    private String createdBy;
    private BugStatus status;

    //Omitting the details like Sprint and the respective dates but that should be VERY easy to extend this with
}
