package com.test.Test.unit.model;

import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.entity.SimpleTest.SimpleTestVariantEntity;
import com.test.Test.entity.Topic.TopicEntity;
import com.test.Test.model.SimpleTest.SimpleTestModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestModelTest {

    @Test
    public void testGetAndSetId() {
        SimpleTestModel model = new SimpleTestModel();
        assertNull(model.getId());

        Long id = 1L;
        model.setId(id);
        assertEquals(id, model.getId());
    }

    @Test
    public void testGetAndSetContent() {
        SimpleTestModel model = new SimpleTestModel();
        assertNull(model.getContent());

        String content = "Test content";
        model.setContent(content);
        assertEquals(content, model.getContent());
    }

    @Test
    public void testGetAndSetTopic() {
        SimpleTestModel model = new SimpleTestModel();
        assertNull(model.getTopic());

        TopicEntity topic = new TopicEntity();
        model.setTopic(topic);
        assertEquals(topic, model.getTopic());
    }

    @Test
    public void testGetAndSetSimpleTestVariant() {
        SimpleTestModel model = new SimpleTestModel();
        assertNull(model.getSimpleTestVariant());

        List<SimpleTestVariantEntity> variants = new ArrayList<>();
        variants.add(new SimpleTestVariantEntity());
        variants.add(new SimpleTestVariantEntity());

        model.setSimpleTestVariant(variants);
        assertEquals(variants, model.getSimpleTestVariant());
    }

    @Test
    public void testToModel() {
        SimpleTestEntity entity = new SimpleTestEntity();
        entity.setId(1L);
        entity.setContent("Test content");

        TopicEntity topic = new TopicEntity();
        entity.setTopic(topic);

        List<SimpleTestVariantEntity> variants = new ArrayList<>();
        variants.add(new SimpleTestVariantEntity());
        entity.setSimpleTestVariant(variants);

        SimpleTestModel model = new SimpleTestModel().toModel(entity);

        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getContent(), model.getContent());
        assertEquals(entity.getTopic(), model.getTopic());
        assertEquals(entity.getSimpleTestVariant(), model.getSimpleTestVariant());
    }
}
