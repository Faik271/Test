package com.test.Test.service.SimpleTest;

import com.test.Test.constant.TestConstant;
import com.test.Test.constant.TopicConstant;
import com.test.Test.controller.SimpleTest.SimpleTestController;
import com.test.Test.dto.SimpleTest.CheckSingleAnswerDto;
import com.test.Test.dto.SimpleTest.SimpleTestAnswersDto;
import com.test.Test.dto.SimpleTest.SimpleTestDto;
import com.test.Test.dto.SimpleTest.SimpleTestVariantDto;
import com.test.Test.entity.SimpleTest.SimpleTestAnswerEntity;
import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.entity.SimpleTest.SimpleTestVariantEntity;
import com.test.Test.entity.Topic.TopicEntity;
import com.test.Test.model.SimpleTest.SimpleTestAnswersModel;
import com.test.Test.model.SimpleTest.SimpleTestModel;
import com.test.Test.model.SimpleTest.SimpleTestsFinalResultModel;
import com.test.Test.repository.SimpleTest.SimpleTestAnswerRepo;
import com.test.Test.repository.SimpleTest.SimpleTestRepo;
import com.test.Test.repository.SimpleTest.SimpleTestVariantRepo;
import com.test.Test.repository.Topic.TopicRepo;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimpleTestService {

    private final Logger logInfo= LoggerFactory.getLogger(SimpleTestController.class);


    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private SimpleTestRepo simpleTestRepo;

    @Autowired
    private SimpleTestVariantRepo simpleTestVariantRepo;

    @Autowired
    private SimpleTestAnswerRepo simpleTestAnswerRepo;

    @Autowired private EntityManager entityManager;

    @Transactional
    public void createAllTopics()
    {

        if(topicRepo.count() < TopicConstant.ALL_TOPICS.size()) {
            topicRepo.deleteAll();
            entityManager.createNativeQuery("ALTER TABLE topics AUTO_INCREMENT = 1").executeUpdate();

            List<TopicEntity> topics = TopicConstant.ALL_TOPICS.stream()
                    .map(topicName -> new TopicEntity(null, topicName))
                    .collect(Collectors.toList());

            topicRepo.saveAll(topics);
        }

    }

    public void createSimpleTest(SimpleTestDto simpleTest)
    {
            //create test itself
            SimpleTestEntity simpleTestEntity = new SimpleTestEntity();
            simpleTestEntity.setContent(simpleTest.getContent());
            TopicEntity topicEntity = topicRepo.findById(simpleTest.getTopicId()).get();
            simpleTestEntity.setTopic(topicEntity);

            //create variants of test
            List<SimpleTestVariantEntity> variants = createSimpleTestVariants(simpleTest.getVariants(), simpleTestEntity);
            simpleTestEntity.setSimpleTestVariant(variants);

            //create answer
            SimpleTestAnswerEntity simpleTestAnswerEntity = createSimpleTestAnswer(simpleTest.getAnswers(), simpleTestEntity);
            simpleTestEntity.setSimpleTestAnswerEntity(simpleTestAnswerEntity);
            simpleTestRepo.save(simpleTestEntity);
    }

    private List<SimpleTestVariantEntity> createSimpleTestVariants(List<SimpleTestVariantDto> variants, SimpleTestEntity simpleTestEntity)
    {
        return variants.stream()
                .map(variant -> {
                    SimpleTestVariantEntity variantEntity = new SimpleTestVariantEntity();
                    variantEntity.setContent(variant.getContent());
                    variantEntity.setVariant(variant.getVariant());
                    variantEntity.setSimpleTest(simpleTestEntity);
                    return variantEntity;
                })
                .collect(Collectors.toList());
    }

    private SimpleTestAnswerEntity createSimpleTestAnswer(SimpleTestAnswersDto simpleTestAnswer, SimpleTestEntity simpleTestEntity)
    {
        SimpleTestAnswerEntity simpleTestAnswerEntity = new SimpleTestAnswerEntity();
        simpleTestAnswerEntity.setVariant(simpleTestAnswer.getAnswer());
        simpleTestAnswerEntity.setExplanation(simpleTestAnswer.getAnswerExplanation());
        simpleTestAnswerEntity.setSimpleTest(simpleTestEntity);

        return simpleTestAnswerEntity;
    }


    @Transactional
    public boolean updateSimpleTest(SimpleTestDto simpleTest, Long id)
    {
        //check if test exists (if it does not exist then response will be 404)
            Optional<SimpleTestEntity> optionalSimpleTestEntity = simpleTestRepo.findById(id);
            if(optionalSimpleTestEntity.isEmpty()) {
                return false;
            }
            //change test itself
            SimpleTestEntity simpleTestEntity = optionalSimpleTestEntity.get();
            simpleTestEntity.setContent(simpleTest.getContent());
            TopicEntity topicEntity = topicRepo.findById(simpleTest.getTopicId()).get();
            simpleTestEntity.setTopic(topicEntity);
            //change variants of test
            List<SimpleTestVariantEntity> variants = updateSimpleTestVariants(simpleTest.getVariants(), simpleTestEntity);
            simpleTestEntity.setSimpleTestVariant(variants);
            //change answers
            SimpleTestAnswerEntity simpleTestAnswerEntity = updateSimpleTestAnswers(simpleTest.getAnswers(), simpleTestEntity);
            simpleTestEntity.setSimpleTestAnswerEntity(simpleTestAnswerEntity);
            simpleTestRepo.save(simpleTestEntity);

            return true;
    }

    private List<SimpleTestVariantEntity> updateSimpleTestVariants(List<SimpleTestVariantDto> variants, SimpleTestEntity simpleTestEntity)
    {
        simpleTestVariantRepo.deleteAll(simpleTestEntity.getSimpleTestVariant());
        return variants.stream()
                .map(variant -> {
                    SimpleTestVariantEntity variantEntity = new SimpleTestVariantEntity();
                    variantEntity.setContent(variant.getContent());
                    variantEntity.setVariant(variant.getVariant());
                    variantEntity.setSimpleTest(simpleTestEntity);
                    return variantEntity;
                })
                .collect(Collectors.toList());
    }

    private SimpleTestAnswerEntity updateSimpleTestAnswers(SimpleTestAnswersDto simpleTestAnswer, SimpleTestEntity simpleTestEntity)
    {
        SimpleTestAnswerEntity simpleTestAnswerEntity = simpleTestEntity.getSimpleTestAnswerEntity();
        simpleTestAnswerEntity.setVariant(simpleTestAnswer.getAnswer());
        simpleTestAnswerEntity.setExplanation(simpleTestAnswer.getAnswerExplanation());
        simpleTestAnswerEntity.setSimpleTest(simpleTestEntity);

        return simpleTestAnswerEntity;
    }

    public Set<SimpleTestEntity> generateExam()
    {
        //get all topics
        List<Long> topicIds = topicRepo.findAll().stream().map(TopicEntity::getId).toList();
        Set<SimpleTestEntity> randomTests = new HashSet<>();
        int topicIndex;
        int iteration = 1;
        while(iteration != TestConstant.NUMBER_OF_TESTS+1){
            //range of topicIndex: [1,19]
            topicIndex = iteration % topicIds.size()+1;
            //find all tests of topic
            List<SimpleTestEntity> simpleTests = simpleTestRepo.findByTopicId((long) topicIndex);
            Random random = new Random();
            //random test of topic
            int randomIndex = random.nextInt(simpleTests.size());
            if(randomTests.add(simpleTests.get(randomIndex))){
                ++iteration;
            }
        }
        // Shuffle the randomTests set by converting it to a List first
        List<SimpleTestEntity> shuffledList = new ArrayList<>(randomTests);
        Collections.shuffle(shuffledList);

        logInfo.info(String.valueOf(shuffledList.size()));
        return new LinkedHashSet<>(shuffledList);
    }

    public List<SimpleTestModel> getAllSimpleTests()
    {
        return simpleTestRepo.findAll().stream().map(entity -> {
            SimpleTestModel simpleTestModel = new SimpleTestModel();
            return simpleTestModel.toModel(entity);

        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteSimpleTest(List<Long> ids)
    {
        simpleTestRepo.deleteAllByIdIn(ids);
    }

    public SimpleTestsFinalResultModel checkAnswers(List<CheckSingleAnswerDto> answers)
    {
        int total = 0;
        List<SimpleTestAnswersModel> rightAnswers = new ArrayList<>();
        List<Long> testIds = answers.stream().map(CheckSingleAnswerDto::getTestId).collect(Collectors.toList());
        List<SimpleTestAnswerEntity> allRightAnswers = simpleTestAnswerRepo.findAllBySimpleTestIdIn(testIds);
        int index = 0;
        for(CheckSingleAnswerDto answer : answers){
            Character rightAnswer = allRightAnswers.get(index).getVariant();
            index++;
            boolean status = rightAnswer.equals(answer.getUserAnswer());
            SimpleTestAnswersModel rightAnswerModel = new SimpleTestAnswersModel();
            rightAnswerModel.setUserAnswer(answer.getUserAnswer());
            rightAnswerModel.setRightAnswer(rightAnswer);
            rightAnswerModel.setStatus(status);
            rightAnswers.add(rightAnswerModel);
            if(status) {
                total += TestConstant.SIMPLE_TEST_MARK;
            }
        }
        return new SimpleTestsFinalResultModel(rightAnswers, total);
    }




}
