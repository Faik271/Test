package com.test.Test.model.SimpleTest;

public class SimpleTestAnswersModel {

    private boolean status;

    private Character userAnswer;

    private Character rightAnswer;

    public boolean getStatus() {
        return status;
    }

    public SimpleTestAnswersModel setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Character getUserAnswer() {
        return userAnswer;
    }

    public SimpleTestAnswersModel setUserAnswer(Character userAnswer) {
        this.userAnswer = userAnswer;
        return this;
    }

    public Character getRightAnswer() {
        return rightAnswer;
    }

    public SimpleTestAnswersModel setRightAnswer(Character rightAnswer) {
        this.rightAnswer = rightAnswer;
        return this;
    }
}
