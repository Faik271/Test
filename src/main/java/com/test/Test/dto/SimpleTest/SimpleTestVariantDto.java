package com.test.Test.dto.SimpleTest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class SimpleTestVariantDto {
    @NotNull()
    private Character variant;

    @NotBlank(message = "{variant.blank}")
    private String content;

    public Character getVariant() {
        return variant;
    }

    public SimpleTestVariantDto setVariant(Character variant) {
        this.variant = variant;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestVariantDto setContent(String content) {
        this.content = content;
        return this;
    }
}
