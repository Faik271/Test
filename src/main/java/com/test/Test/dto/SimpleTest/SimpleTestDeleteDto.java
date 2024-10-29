package com.test.Test.dto.SimpleTest;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SimpleTestDeleteDto {

    @NotEmpty(message = "{simple_test.ids}")
    private List<Long> ids;

    public @NotEmpty(message = "{simple_test.ids}") List<Long> getIds() {
        return ids;
    }

    public SimpleTestDeleteDto setIds(@NotEmpty(message = "{simple_test.ids}") List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
