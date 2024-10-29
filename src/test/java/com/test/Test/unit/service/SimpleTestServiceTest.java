package com.test.Test.unit.service;


import com.test.Test.constant.TopicConstant;
import com.test.Test.entity.Topic.TopicEntity;
import com.test.Test.repository.Topic.TopicRepo;
import com.test.Test.service.SimpleTest.SimpleTestService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SimpleTestServiceTest {

    @Autowired
    private SimpleTestService simpleTestService;

    @Autowired
    private TopicRepo topicRepo;

    @Test
    @Transactional
    @Rollback
    void testCreateAllTopics() {
        simpleTestService.createAllTopics();
        List<TopicEntity> topics = topicRepo.findAll();
        assertEquals(TopicConstant.ALL_TOPICS.size(), topics.size());
        int i = 0;
        for (String expectedTopic : TopicConstant.ALL_TOPICS) {
            assertEquals(expectedTopic, topics.get(i).getTopicName());
            i++;
        }
        assertEquals(1L, topics.get(0).getId());
    }
}
