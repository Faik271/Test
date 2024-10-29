CREATE TABLE simple_tests_answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    test_id BIGINT,
    variant CHAR(1),
    explanation TEXT,
    FOREIGN KEY (test_id) REFERENCES simple_tests(id) ON DELETE CASCADE
);