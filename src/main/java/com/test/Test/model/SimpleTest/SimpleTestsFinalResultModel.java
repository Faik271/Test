package com.test.Test.model.SimpleTest;

import java.util.List;

public class SimpleTestsFinalResultModel {

    public SimpleTestsFinalResultModel(List<SimpleTestAnswersModel> answers, int total) {
        this.answers = answers;
        this.total = total;
    }

    private int total;



    private List<SimpleTestAnswersModel> answers;

    public int getTotal() {
        return total;
    }

    public SimpleTestsFinalResultModel setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<SimpleTestAnswersModel> getAnswers() {
        return answers;
    }

    public SimpleTestsFinalResultModel setAnswers(List<SimpleTestAnswersModel> answers) {
        this.answers = answers;
        return this;
    }

}
