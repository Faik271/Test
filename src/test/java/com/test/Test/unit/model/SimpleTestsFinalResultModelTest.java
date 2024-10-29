package com.test.Test.unit.model;

import com.test.Test.model.SimpleTest.SimpleTestAnswersModel;
import com.test.Test.model.SimpleTest.SimpleTestsFinalResultModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestsFinalResultModelTest {

    @Test
    public void testConstructor() {
        List<SimpleTestAnswersModel> answers = new ArrayList<>();
        answers.add(new SimpleTestAnswersModel().setStatus(true).setUserAnswer('A').setRightAnswer('B'));

        int total = 3;

        SimpleTestsFinalResultModel model = new SimpleTestsFinalResultModel(answers, total);

        assertEquals(total, model.getTotal());
        assertEquals(answers, model.getAnswers());
    }

    @Test
    public void testGetAndSetTotal() {
        SimpleTestsFinalResultModel model = new SimpleTestsFinalResultModel(null, 0);
        assertEquals(0, model.getTotal());

        int total = 5;
        model.setTotal(total);
        assertEquals(total, model.getTotal());
    }

    @Test
    public void testGetAndSetAnswers() {
        SimpleTestsFinalResultModel model = new SimpleTestsFinalResultModel(null, 0);
        assertNull(model.getAnswers());

        List<SimpleTestAnswersModel> answers = new ArrayList<>();
        answers.add(new SimpleTestAnswersModel().setStatus(true).setUserAnswer('A').setRightAnswer('B'));

        model.setAnswers(answers);
        assertEquals(answers, model.getAnswers());
    }
}
