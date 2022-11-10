package com.ratepay.tracker.bug.services.impl;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.dto.BugDto;
import com.ratepay.tracker.bug.entity.BugStatus;
import com.ratepay.tracker.bug.repositories.BugRepository;
import com.ratepay.tracker.bug.services.BugService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.ratepay.tracker.bug.entity.BugStatus.ASSIGNED;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class BugServiceSpec {

    @Autowired
    BugRepository bugRepository;

    @Autowired
    BugService bugService;

    @Autowired
    MongoTemplate mongoTemplate;

    @AfterEach
    public void tearDown() {
        bugRepository.deleteAll();
    }

    @Test
    @DisplayName("Bug - Verify the creation")
    public void givenBugEntity_shouldSave() {
        //Given
        BugCreationRequest bugCreationRequest = new BugCreationRequest( "Title-1", "Desc-1", "creator@creator.com");
        bugService.createBug(bugCreationRequest);

        //When
        var maybeBug = bugRepository.findAll().stream().findFirst();

        //Then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(maybeBug.isPresent())
                    .as("Check if a bug has been created")
                    .isEqualTo(true);

            maybeBug.map(bug -> {
                softly.assertThat(bug.getStatus())
                        .as("Check if the status is created")
                        .isEqualTo(BugStatus.CREATED);

                softly.assertThat(bug.getCreatedBy())
                        .as("Verify the creator")
                        .isEqualTo(bugCreationRequest.getCreatedBy());

                softly.assertThat(bug.getTitle())
                        .as("Verify the title")
                        .isEqualTo(bugCreationRequest.getTitle());

                softly.assertThat(bug.getDescription())
                        .as("Verify the description")
                        .isEqualTo(bugCreationRequest.getDescription());

                softly.assertThat(bug.getAssignedTo())
                        .as("Verify that it is not yet assigned")
                        .isNull();
                return true;
            });
        });
    }

    @Test
    @DisplayName("Bug - Verify assignment")
    public void givenBugEntity_assign_shouldChangeTheState() {
        String assignee = "user@test.com";
        //Given
        BugCreationRequest bugCreationRequest = new BugCreationRequest( "Title-1", "Desc-1", "creator@creator.com");
        bugService.createBug(bugCreationRequest);

        //When
        List<BugDto> bugs = bugService.getAll();
        BugDto assignedBug = bugService.assignTo(bugs.get(0).getId(), assignee);

        //Then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(assignedBug.getStatus())
                    .as("Verify status is ASSIGNED")
                    .isEqualTo(ASSIGNED);

            softly.assertThat(assignedBug.getAssignedTo())
                    .as("Verify the assignee")
                    .isEqualTo(assignee);
        });
    }
    
}
