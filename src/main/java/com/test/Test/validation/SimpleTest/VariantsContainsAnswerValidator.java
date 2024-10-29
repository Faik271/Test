package com.test.Test.validation.SimpleTest;

import com.test.Test.dto.SimpleTest.SimpleTestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;

public class VariantsContainsAnswerValidator implements ConstraintValidator<VariantsContainsAnswer, SimpleTestDto> {

    @Override
    public void initialize(VariantsContainsAnswer constraintAnnotation) {
    }

    @Override
    public boolean isValid(SimpleTestDto dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getVariants() == null || dto.getAnswers() == null || dto.getAnswers().getAnswer() == null) {
            return true; // Skip validation if dto, variants, or answer is null
        }


        Set<Character> variantSet = dto.getVariants().stream()
                .map(variant -> {return variant.getVariant();})
                .collect(Collectors.toSet());

        return variantSet.contains(dto.getAnswers().getAnswer());
    }
}
