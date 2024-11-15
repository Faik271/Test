package com.test.Test.model.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.entity.SimpleTest.SimpleTestVariantEntity;
import com.test.Test.entity.Topic.TopicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleTestModel implements Serializable{

    private Long id;

    private String content;

    private TopicEntity topic;

    private List<SimpleTestVariantsModel> simpleTestVariant;

    public SimpleTestModel toModel(SimpleTestEntity simpleTestEntity){
        SimpleTestModel simpleTestModel = new SimpleTestModel();
        List<SimpleTestVariantsModel> newSimpleTestVariant =new ArrayList<>();
        for(SimpleTestVariantEntity simpleTestVariantEntity : simpleTestEntity.getSimpleTestVariant()){
            SimpleTestVariantsModel simpleTestVariantsModel = new SimpleTestVariantsModel();
            simpleTestVariantsModel.setId(simpleTestVariantEntity.getId())
                    .setContent(simpleTestVariantEntity.getContent())
                    .setVariant(simpleTestVariantEntity.getVariant());
            newSimpleTestVariant.add(simpleTestVariantsModel);
        }
        simpleTestModel.setId(simpleTestEntity.getId()).setContent(simpleTestEntity.getContent())
                .setTopic(simpleTestEntity.getTopic()).setSimpleTestVariant(newSimpleTestVariant);
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

    public List<SimpleTestVariantsModel> getSimpleTestVariant() {
        return simpleTestVariant;
    }

    public SimpleTestModel setSimpleTestVariant(List<SimpleTestVariantsModel> simpleTestVariant) {
        this.simpleTestVariant = simpleTestVariant;
        return this;
    }
}
