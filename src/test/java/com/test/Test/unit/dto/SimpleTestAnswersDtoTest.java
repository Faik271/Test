package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.SimpleTestAnswersDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTestAnswersDtoTest {

    @Test
    public void testGetAndSetAnswer() {
        SimpleTestAnswersDto dto = new SimpleTestAnswersDto();
        assertNull(dto.getAnswer());

        Character answer = 'A';
        dto.setAnswer(answer);
        assertEquals(answer, dto.getAnswer());
    }

    @Test
    public void testGetAndSetAnswerExplanation() {
        SimpleTestAnswersDto dto = new SimpleTestAnswersDto();
        assertNull(dto.getAnswerExplanation());

        String explanation = "This is the explanation for the answer.";
        dto.setAnswerExplanation(explanation);
        assertEquals(explanation, dto.getAnswerExplanation());
    }
}
