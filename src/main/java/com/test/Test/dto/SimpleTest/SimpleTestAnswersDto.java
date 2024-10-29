package com.test.Test.dto.SimpleTest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SimpleTestAnswersDto {

    @NotNull()
    private Character answer;

    @NotBlank()
    private String answerExplanation;

    public Character getAnswer() {
        return answer;
    }

    public SimpleTestAnswersDto setAnswer(Character answer) {
        this.answer = answer;
        return this;
    }

    public String getAnswerExplanation() {
        return answerExplanation;
    }

    public SimpleTestAnswersDto setAnswerExplanation(String answerExplanation) {
        this.answerExplanation = answerExplanation;
        return this;
    }
}
