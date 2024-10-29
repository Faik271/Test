package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.SimpleTestDto;
import com.test.Test.dto.SimpleTest.SimpleTestAnswersDto;
import com.test.Test.dto.SimpleTest.SimpleTestVariantDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestDtoTest {

    @Test
    public void testGetAndSetTopicId() {
        SimpleTestDto dto = new SimpleTestDto();
        assertNull(dto.getTopicId());

        Long topicId = 1L;
        dto.setTopicId(topicId);
        assertEquals(topicId, dto.getTopicId());
    }

    @Test
    public void testGetAndSetContent() {
        SimpleTestDto dto = new SimpleTestDto();
        assertNull(dto.getContent());

        String content = "Test content";
        dto.setContent(content);
        assertEquals(content, dto.getContent());
    }

    @Test
    public void testGetAndSetVariants() {
        SimpleTestDto dto = new SimpleTestDto();
        assertNull(dto.getVariants());

        List<SimpleTestVariantDto> variants = new ArrayList<>();
        variants.add(new SimpleTestVariantDto().setVariant('A').setContent("Option A"));
        variants.add(new SimpleTestVariantDto().setVariant('B').setContent("Option B"));

        dto.setVariants(variants);
        assertEquals(variants, dto.getVariants());
    }

    @Test
    public void testGetAndSetAnswers() {
        SimpleTestDto dto = new SimpleTestDto();
        assertNull(dto.getAnswers());

        SimpleTestAnswersDto answers = new SimpleTestAnswersDto().setAnswer('A').setAnswerExplanation("Explanation");
        dto.setAnswers(answers);
        assertEquals(answers, dto.getAnswers());
    }
}
