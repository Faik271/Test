package com.test.Test.validation.SimpleTest;

import com.test.Test.repository.SimpleTest.SimpleTestRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueContentValidator implements ConstraintValidator<UniqueContent, String> {

    @Autowired
    private SimpleTestRepo simpleTestRepo;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(UniqueContent constraintAnnotation) {
    }

    @Override
    public boolean isValid(String content, ConstraintValidatorContext context) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            return !simpleTestRepo.existsByContent(content);
        }
        return true;
    }

}
