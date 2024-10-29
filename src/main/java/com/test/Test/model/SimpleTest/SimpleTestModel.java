package com.test.Test.model.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.entity.SimpleTest.SimpleTestVariantEntity;
import com.test.Test.entity.Topic.TopicEntity;

import java.util.List;

public class SimpleTestModel {

    private Long id;

    private String content;

    private TopicEntity topic;

    private List<SimpleTestVariantEntity> simpleTestVariant;

    public SimpleTestModel toModel(SimpleTestEntity simpleTestEntity){
        SimpleTestModel simpleTestModel = new SimpleTestModel();
        simpleTestModel.setId(simpleTestEntity.getId()).setContent(simpleTestEntity.getContent())
                .setTopic(simpleTestEntity.getTopic()).setSimpleTestVariant(simpleTestEntity.getSimpleTestVariant());
        return simpleTestModel;

    }

    public Long getId() {
        return id;
    }

    public SimpleTestModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestModel setContent(String content) {
        this.content = content;
        return this;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public SimpleTestModel setTopic(TopicEntity topic) {
        this.topic = topic;
        return this;
    }

    public List<SimpleTestVariantEntity> getSimpleTestVariant() {
        return simpleTestVariant;
    }

    public SimpleTestModel setSimpleTestVariant(List<SimpleTestVariantEntity> simpleTestVariant) {
        this.simpleTestVariant = simpleTestVariant;
        return this;
    }
}
