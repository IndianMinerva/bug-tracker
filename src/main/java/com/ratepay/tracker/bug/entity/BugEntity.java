package com.ratepay.tracker.bug.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BugEntity {
    private String id;

    private String title;
    private String description;
    private String createdBy;

    @Setter
    private String assignedTo;

    @Setter
    private BugStatus status;

    //Omitting the details like Sprint and the respective dates but it should be VERY easy to extend this with
}
