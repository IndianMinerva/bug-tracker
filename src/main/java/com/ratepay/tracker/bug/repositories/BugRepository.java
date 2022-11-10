package com.ratepay.tracker.bug.repositories;

import com.ratepay.tracker.bug.entity.BugEntity;
import com.ratepay.tracker.bug.entity.BugStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends MongoRepository<BugEntity, String> {
    List<BugEntity> findAllByStatus(BugStatus status);
    List<BugEntity> findAllByCreatedBy(String createdBy);
    List<BugEntity> findAllByAssignedTo(String assignedTo);
}
