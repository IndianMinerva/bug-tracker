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
import java.util.stream.StreamSupport;

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
                BugStatus.CREATED);

        bugRepository.save(entity);
    }

    @Override
    public List<BugDto> getAll() {
        return StreamSupport
                .stream(bugRepository.findAll().spliterator(), false)
                .map(bugMapper::mapBugEntityToBugDto).toList();
    }

    @Override
    public BugDto getById(Long id) {
        return null;
    }

    @Override
    public List<BugDto> getByStatus(BugStatus status) {
        return null;
    }

    @Override
    public List<BugDto> updateStatus(Long id, BugStatus status) {
        return null;
    }
}
