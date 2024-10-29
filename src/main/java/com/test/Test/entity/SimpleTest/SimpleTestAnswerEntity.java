package com.test.Test.entity.SimpleTest;

import jakarta.persistence.*;

@Entity
@Table(name = "simple_tests_answers")
public class SimpleTestAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Character variant;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @OneToOne
    @JoinColumn(name = "test_id", nullable = false)
    private SimpleTestEntity simpleTest;

    // Constructors, getters, setters

    public SimpleTestAnswerEntity() {
    }

    public Long getId() {
        return id;
    }

    public SimpleTestAnswerEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Character getVariant() {
        return variant;
    }

    public SimpleTestAnswerEntity setVariant(Character variant) {
        this.variant = variant;
        return this;
    }

    public String getExplanation() {
        return explanation;
    }

    public SimpleTestAnswerEntity setExplanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

//    public SimpleTestEntity getSimpleTest() {
//        return simpleTest;
//    }

    public SimpleTestAnswerEntity setSimpleTest(SimpleTestEntity simpleTest) {
        this.simpleTest = simpleTest;
        return this;
    }

}
