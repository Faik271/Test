package com.test.Test.dto.SimpleTest;

import com.test.Test.entity.Topic.TopicEntity;
import com.test.Test.validation.BasicValidation.Exists;
import com.test.Test.validation.SimpleTest.UniqueContent;
import com.test.Test.validation.SimpleTest.UniqueVariants;
import com.test.Test.validation.SimpleTest.VariantsContainsAnswer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@VariantsContainsAnswer
public class SimpleTestDto {

    @NotNull(message = "{topic_id.notNull}")
    @Exists(entity = TopicEntity.class)
    private Long topicId;

    @NotBlank()
    @UniqueContent
    private String content;

    @Valid
    @Size(min = 2, max = 10)
    @UniqueVariants
    private List<SimpleTestVariantDto> variants;

    @Valid
    @NotNull
    private SimpleTestAnswersDto answers;

    public Long getTopicId() {
        return topicId;
    }

    public SimpleTestDto setTopicId(Long topicId) {
        this.topicId = topicId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SimpleTestDto setContent(String content) {
        this.content = content;
        return this;
    }

    public List<SimpleTestVariantDto> getVariants() {
        return variants;
    }

    public SimpleTestDto setVariants(List<SimpleTestVariantDto> variants) {
        this.variants = variants;
        return this;
    }

    public SimpleTestAnswersDto getAnswers() {
        return answers;
    }

    public SimpleTestDto setAnswers(SimpleTestAnswersDto answers) {
        this.answers = answers;
        return this;
    }

}
