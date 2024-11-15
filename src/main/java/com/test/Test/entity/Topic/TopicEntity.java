package com.test.Test.entity.Topic;


import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String topicName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<SimpleTestEntity> simpleTestEntity;

    public TopicEntity(Long id, String name) {
        this.id = id;
        this.topicName = name;
    }

    public TopicEntity() {
    }

    public Long getId() {
        return id;
    }

    public TopicEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicEntity setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

//    public List<SimpleTestEntity> getSimpleTestEntity() {
//        return simpleTestEntity;
//    }

    public TopicEntity setSimpleTestEntity(List<SimpleTestEntity> simpleTestEntity) {
        this.simpleTestEntity = simpleTestEntity;
        return this;
    }
}
