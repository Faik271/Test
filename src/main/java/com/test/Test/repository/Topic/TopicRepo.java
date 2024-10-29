package com.test.Test.repository.Topic;

import com.test.Test.entity.Topic.TopicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TopicRepo extends JpaRepository<TopicEntity, Long> {
    boolean existsByTopicName(String topicName);

    @Modifying  // This tells Spring Data JPA that the query will modify data
    @Transactional
    @Query(value = "TRUNCATE TABLE topics", nativeQuery = true)
    void truncateTable();
}
