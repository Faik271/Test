package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.CheckSingleAnswerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CheckSingleAnswerDtoTest {

    @Test
    public void testGetAndSetTestId() {
        CheckSingleAnswerDto dto = new CheckSingleAnswerDto();
        assertNull(dto.getTestId());

        Long testId = 1L;
        dto.setTestId(testId);
        assertEquals(testId, dto.getTestId());
    }

    @Test
    public void testGetAndSetUserAnswer() {
        CheckSingleAnswerDto dto = new CheckSingleAnswerDto();
        assertNull(dto.getUserAnswer());

        Character userAnswer = 'A';
        dto.setUserAnswer(userAnswer);
        assertEquals(userAnswer, dto.getUserAnswer());
    }
}
