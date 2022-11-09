package com.ratepay.tracker.bug.dto;

import com.ratepay.tracker.bug.entity.BugStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BugDto {
    private String id;

    private String title;
    private String description;
    private String createdBy;
    private BugStatus status;
}
