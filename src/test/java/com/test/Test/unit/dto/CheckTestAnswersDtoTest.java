package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.CheckSingleAnswerDto;
import com.test.Test.dto.SimpleTest.CheckTestAnswersDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckTestAnswersDtoTest {

    @Test
    public void testGetAndSetAnswers() {
        CheckTestAnswersDto dto = new CheckTestAnswersDto();
        assertNull(dto.getAnswers());

        List<CheckSingleAnswerDto> answerList = new ArrayList<>();
        answerList.add(new CheckSingleAnswerDto().setTestId(1L).setUserAnswer('A'));

        dto.setAnswers(answerList);
        assertEquals(answerList, dto.getAnswers());
    }

}
