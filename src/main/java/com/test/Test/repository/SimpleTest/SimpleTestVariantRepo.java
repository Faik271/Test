package com.test.Test.repository.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleTestVariantRepo extends JpaRepository<SimpleTestVariantEntity, Long> {

}
