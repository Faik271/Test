package com.test.Test.entity.SimpleTest;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "simple_tests_variants")
public class SimpleTestVariantEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;


    private char variant;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private SimpleTestEntity simpleTest;

    public SimpleTestVariantEntity() {
    }

    public Long getId() {return id;}

    public SimpleTestVariantEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestVariantEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Character getVariant() {
        return variant;
    }

    public SimpleTestVariantEntity setVariant(Character variant) {
        this.variant = variant;
        return this;
    }

//    public SimpleTestEntity getSimpleTest() {
//        return simpleTest;
//    }

    public SimpleTestVariantEntity setSimpleTest(SimpleTestEntity simpleTest) {
        this.simpleTest = simpleTest;
        return this;
    }
}
