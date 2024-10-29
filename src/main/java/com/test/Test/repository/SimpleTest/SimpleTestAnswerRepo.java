package com.test.Test.repository.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimpleTestAnswerRepo extends JpaRepository<SimpleTestAnswerEntity, Long> {

    SimpleTestAnswerEntity findBySimpleTestId(Long testId);


    @Query("SELECT s FROM SimpleTestAnswerEntity s WHERE s.simpleTest.id IN :testIds")
    List<SimpleTestAnswerEntity> findAllBySimpleTestIdIn(@Param("testIds") List<Long> testIds);
}
