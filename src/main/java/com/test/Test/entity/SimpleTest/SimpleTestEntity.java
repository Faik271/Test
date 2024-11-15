package com.test.Test.entity.SimpleTest;


import com.test.Test.entity.Topic.TopicEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "simple_tests")
public class SimpleTestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "simpleTest")
    private List<SimpleTestVariantEntity> simpleTestVariant;

    @OneToOne(mappedBy = "simpleTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private SimpleTestAnswerEntity simpleTestAnswerEntity;

    public SimpleTestEntity() {
    }

    public Long getId() {
        return id;
    }

    public SimpleTestEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public SimpleTestEntity setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }

    public SimpleTestEntity setSimpleTestVariant(List<SimpleTestVariantEntity> simpleTestVariant) {
        this.simpleTestVariant = simpleTestVariant;
        return this;
    }

    public SimpleTestAnswerEntity getSimpleTestAnswerEntity() {
        return simpleTestAnswerEntity;
    }

    public SimpleTestEntity setSimpleTestAnswerEntity(SimpleTestAnswerEntity simpleTestAnswerEntity) {
        this.simpleTestAnswerEntity = simpleTestAnswerEntity;
        return this;
    }
}
