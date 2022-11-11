package com.ratepay.tracker.bug.controllers;

import com.ratepay.tracker.bug.dto.BugCreationRequest;
import com.ratepay.tracker.bug.services.BugService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class BugControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BugService bugService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test() {
        bugService.createBug(new BugCreationRequest("Title-1", "Desc-1", "penguin@creator.com"));
        bugService.createBug(new BugCreationRequest("Title-2", "Desc-2", "penguin@creator.com"));
        bugService.createBug(new BugCreationRequest("Title-3", "Desc-3", "penguin@creator.com"));
        String url = "http://localhost:" + port + "/bugs";

        System.out.println("**********************************************");
        System.out.println(bugService.getAll());
        System.out.println(url);
        System.out.println("**********************************************");

        restTemplate.getForObject(url, String.class);
    }
}
