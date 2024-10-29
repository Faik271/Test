CREATE TABLE simple_tests_variants (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      test_id BIGINT,
      content TEXT,
      variant CHAR(1),
      FOREIGN KEY (test_id) REFERENCES simple_tests(id) ON DELETE CASCADE
);
