package com.ratepay.tracker.bug.mapper;

import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugEntity;
import org.springframework.stereotype.Component;

@Component
public class BugMapper {
    public BugDto mapBugEntityToBugDto(BugEntity bugEntity) {
        return new BugDto(
                bugEntity.getId(),
                bugEntity.getTitle(),
                bugEntity.getDescription(),
                bugEntity.getCreatedBy(),
                bugEntity.getAssignedTo(),
                bugEntity.getStatus()
        );
    }
}
