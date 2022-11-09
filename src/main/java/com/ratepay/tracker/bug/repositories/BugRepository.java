package com.ratepay.tracker.bug.repositories;

import com.ratepay.tracker.bug.entity.BugEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends MongoRepository<BugEntity, Long> {
}
