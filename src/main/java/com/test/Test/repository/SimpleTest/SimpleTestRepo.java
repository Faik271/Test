package com.test.Test.repository.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimpleTestRepo extends JpaRepository<SimpleTestEntity, Long> {
    List<SimpleTestEntity> findByTopicId(Long topicId);
    void deleteAllByIdIn(List<Long> ids);
    boolean existsByContent(String content);
    SimpleTestEntity findTopByOrderByIdDesc();
}
