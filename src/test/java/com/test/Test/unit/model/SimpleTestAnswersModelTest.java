package com.test.Test.unit.model;

import com.test.Test.model.SimpleTest.SimpleTestAnswersModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTestAnswersModelTest {

    @Test
    public void testGetAndSetStatus() {
        SimpleTestAnswersModel model = new SimpleTestAnswersModel();
        assertFalse(model.getStatus());

        model.setStatus(true);
        assertTrue(model.getStatus());
    }

    @Test
    public void testGetAndSetUserAnswer() {
        SimpleTestAnswersModel model = new SimpleTestAnswersModel();
        assertNull(model.getUserAnswer());

        Character userAnswer = 'A';
        model.setUserAnswer(userAnswer);
        assertEquals(userAnswer, model.getUserAnswer());
    }

    @Test
    public void testGetAndSetRightAnswer() {
        SimpleTestAnswersModel model = new SimpleTestAnswersModel();
        assertNull(model.getRightAnswer());

        Character rightAnswer = 'B';
        model.setRightAnswer(rightAnswer);
        assertEquals(rightAnswer, model.getRightAnswer());
    }
}
