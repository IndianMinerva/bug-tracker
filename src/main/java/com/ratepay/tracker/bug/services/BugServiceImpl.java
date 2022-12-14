package com.ratepay.tracker.bug.services;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugEntity;
import com.ratepay.tracker.bug.entity.BugStatus;
import com.ratepay.tracker.bug.mapper.BugMapper;
import com.ratepay.tracker.bug.repositories.BugRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final BugMapper bugMapper;

    @Override
    public void createBug(BugCreationRequest bugCreationRequest) {
        BugEntity entity = new BugEntity(
                null, bugCreationRequest.getTitle(),
                bugCreationRequest.getDescription(),
                bugCreationRequest.getCreatedBy(),
                null,
                BugStatus.CREATED);

        bugRepository.save(entity);
    }

    @Override
    public List<BugDto> getAll() {
        return bugRepository.findAll().stream()
                .map(bugMapper::mapBugEntityToBugDto).toList();
    }

    @Override
    public BugDto getById(String id) {
        return bugRepository
                .findById(id)
                .map(bugMapper::mapBugEntityToBugDto)
                .orElseThrow(() -> new RuntimeException("Bug could not be found"));
    }

    @Override
    public List<BugDto> getByCreatedUser(String createdUser) {
        return bugRepository
                .findAllByCreatedBy(createdUser)
                .stream()
                .map(bugMapper::mapBugEntityToBugDto)
                .toList();
    }

    @Override
    public List<BugDto> getByAssignedUser(String assignedTo) {
        return bugRepository
                .findAllByAssignedTo(assignedTo)
                .stream()
                .map(bugMapper::mapBugEntityToBugDto)
                .toList();
    }

    @Override
    public List<BugDto> getByStatus(BugStatus status) {
        return bugRepository
                .findAllByStatus(status)
                .stream()
                .map(bugMapper::mapBugEntityToBugDto)
                .toList();
    }

    @Override
    public BugDto updateStatus(String id, BugStatus status) {
        return bugRepository.findById(id).map(bug -> {
            bug.setStatus(status);
            return bugRepository.save(bug);
        }).map(bugMapper::mapBugEntityToBugDto)
                .orElseThrow(() -> new RuntimeException("Failed to update the status of the bug"));
    }

    @Override
    public BugDto assignTo(String id, String assignedUser) {
        return bugRepository.findById(id)
                .map(bug -> {
                    bug.setStatus(BugStatus.ASSIGNED);
                    bug.setAssignedTo(assignedUser);
                    return bugRepository.save(bug);
                }).map(bugMapper::mapBugEntityToBugDto)
                .orElseThrow(() -> new RuntimeException("Failed to assign the bug"));
    }
}
