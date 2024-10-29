CREATE TABLE simple_tests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,
    topic_id BIGINT,
    FOREIGN KEY (topic_id) REFERENCES topics(id)
);