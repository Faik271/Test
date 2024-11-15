package com.test.Test.model.SimpleTest;

public class SimpleTestVariantsModel {


    private Long id;

    private String content;

    private char variant;

    public SimpleTestVariantsModel() {
    }

    public Long getId() {
        return id;
    }

    public SimpleTestVariantsModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestVariantsModel setContent(String content) {
        this.content = content;
        return this;
    }

    public char getVariant() {
        return variant;
    }

    public SimpleTestVariantsModel setVariant(char variant) {
        this.variant = variant;
        return this;
    }
}
