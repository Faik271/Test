package com.test.Test.dto.SimpleTest;

import com.test.Test.constant.TestConstant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Validated
public class CheckTestAnswersDto {


    @Valid
    @Size(min = TestConstant.NUMBER_OF_TESTS)
    private List<CheckSingleAnswerDto> answers;

    public @Valid @Size(min = TestConstant.NUMBER_OF_TESTS) List<CheckSingleAnswerDto> getAnswers() {
        return answers;
    }

    public CheckTestAnswersDto setAnswers(@Valid @Size(min = TestConstant.NUMBER_OF_TESTS) List<CheckSingleAnswerDto> answers) {
        this.answers = answers;
        return this;
    }
}
