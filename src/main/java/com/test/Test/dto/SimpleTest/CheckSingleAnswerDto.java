package com.test.Test.dto.SimpleTest;

import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.validation.BasicValidation.Exists;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


@Validated
public class CheckSingleAnswerDto {


    @NotNull(message = "{test_id.notNull}")
    @Exists(entity = SimpleTestEntity.class)
    private Long testId;

    @NotNull()
    private Character userAnswer;

    public @NotNull(message = "{test_id.notNull}") Long getTestId() {
        return testId;
    }

    public CheckSingleAnswerDto setTestId(@NotNull(message = "{test_id.notNull}") Long testId) {
        this.testId = testId;
        return this;
    }

    public @NotNull() Character getUserAnswer() {
        return userAnswer;
    }

    public CheckSingleAnswerDto setUserAnswer(@NotNull() Character userAnswer) {
        this.userAnswer = userAnswer;
        return this;
    }
}
