package com.test.Test.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.test.Test.constant.ApiRout;
import com.test.Test.constant.TestConstant;
import com.test.Test.constant.TopicConstant;
import com.test.Test.controller.SimpleTest.SimpleTestController;
import com.test.Test.dto.SimpleTest.*;
import com.test.Test.entity.SimpleTest.SimpleTestAnswerEntity;
import com.test.Test.entity.SimpleTest.SimpleTestEntity;
import com.test.Test.model.SimpleTest.SimpleTestModel;
import com.test.Test.model.SimpleTest.SimpleTestsFinalResultModel;
import com.test.Test.repository.SimpleTest.SimpleTestRepo;
import com.test.Test.repository.Topic.TopicRepo;
import com.test.Test.service.SimpleTest.SimpleTestService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SimpleTestIntegrationTest {

    private final Logger logInfo= LoggerFactory.getLogger(SimpleTestIntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private SimpleTestService simpleTestService;

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private SimpleTestRepo simpleTestRepo;

    private SimpleTestDto simpleTestDto;

    private CheckTestAnswersDto checkTestAnswersDto;

    private int totalMark;

    @BeforeEach
    public void setUp() {

    }

    private SimpleTestDto createValidSimpleTestDto() {
        SimpleTestVariantDto variant1 = new SimpleTestVariantDto()
                .setVariant('A')
                .setContent("Variant 1");

        SimpleTestVariantDto variant2 = new SimpleTestVariantDto()
                .setVariant('B')
                .setContent("Variant 2");

        SimpleTestAnswersDto answers = new SimpleTestAnswersDto()
                .setAnswer('A')
                .setAnswerExplanation("This is why A is the answer");

        return new SimpleTestDto()
                .setTopicId(1L)
                .setContent("Valid test content")
                .setVariants(Arrays.asList(variant1, variant2))
                .setAnswers(answers);
    }

    private List<SimpleTestEntity> createFakeSimpleTests(int size) {
        Random random = new Random();
        List<SimpleTestEntity> fakeTests = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            SimpleTestEntity simpleTestEntity = new SimpleTestEntity();
            String randomContent = RandomStringUtils.randomAlphanumeric(20);
            //different topic on each iteration
            simpleTestEntity.setTopic(topicRepo.findById((long) (i%TopicConstant.ALL_TOPICS.size())+1).get()).setContent(randomContent);
            SimpleTestAnswerEntity simpleTestAnswerEntity = new SimpleTestAnswerEntity();
            simpleTestAnswerEntity.setSimpleTest(simpleTestEntity).setVariant((char) (random.nextInt(10) + 'A'));
            simpleTestEntity.setSimpleTestAnswerEntity(simpleTestAnswerEntity);
            fakeTests.add(simpleTestEntity);
        }
        simpleTestRepo.saveAll(fakeTests);
        return fakeTests;
    }

    private boolean[] generateFakeUserAnswers(List<SimpleTestEntity> fakeTests){
        Random random = new Random();
        boolean[] answersStatuses = new boolean[TestConstant.NUMBER_OF_TESTS];
        List<CheckSingleAnswerDto> answers = new ArrayList<>();
        totalMark = 0;
        for (int i = 0; i < fakeTests.size(); i++) {
            SimpleTestEntity el = fakeTests.get(i);
            boolean isCorrect = random.nextBoolean();
            answersStatuses[i] = isCorrect;
            char userAnswer = el.getSimpleTestAnswerEntity().getVariant();
            userAnswer = isCorrect ? userAnswer : (char) (userAnswer + 1);
            totalMark = isCorrect ? totalMark + TestConstant.SIMPLE_TEST_MARK : totalMark;
            CheckSingleAnswerDto checkSingleAnswerDto = new CheckSingleAnswerDto().setTestId(el.getId())
                    .setUserAnswer(userAnswer);
            answers.add(checkSingleAnswerDto);
        }
        checkTestAnswersDto = new CheckTestAnswersDto();
        checkTestAnswersDto.setAnswers(answers);
        return answersStatuses;
    }


    @Test
    public void testCreateAndUpdateSimpleTest_Success() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.CREATED.value()));
        mockMvc.perform(put(ApiRout.UPDATE_SIMPLE_TEST.getUrl(), simpleTestRepo.findTopByOrderByIdDesc().getId()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.OK.value()));
    }


    @Test
    public void testCreateSimpleTest_MissingTopicId() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.setTopicId(null);
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testCreateSimpleTest_BlankContent() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.setContent("");
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testCreateSimpleTest_InvalidVariantsSize() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.setVariants(Collections.singletonList(new SimpleTestVariantDto()
                .setVariant('A')
                .setContent("Variant 1")));
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testCreateSimpleTest_MissingAnswers() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.setAnswers(null);
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testCreateSimpleTest_BlankAnswerExplanation() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.getAnswers().setAnswerExplanation("");
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testCreateSimpleTest_NonExistent() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        simpleTestDto.setTopicId(TopicConstant.ALL_TOPICS.size()+1L);
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(post(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdateSimpleTest_NotFound() throws Exception {
        simpleTestDto = createValidSimpleTestDto();
        Mockito.when(simpleTestService.updateSimpleTest(any(SimpleTestDto.class), anyLong()))
                .thenReturn(false);
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDto);
        mockMvc.perform(put(ApiRout.UPDATE_SIMPLE_TEST.getUrl(), 1L).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void testGetAllSimpleTests_Success() throws Exception {
        simpleTestRepo.deleteAll();
        Random random = new Random();
        int size = random.nextInt(5)+1;
        List<SimpleTestEntity> fakeTests = createFakeSimpleTests(size);
        MvcResult result = mockMvc.perform(get(ApiRout.GET_ALL_SIMPLE_TESTS.getUrl()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        List<SimpleTestDto> returnedSimpleTestList = objectMapper.readValue(jsonResponse, new TypeReference<List<SimpleTestDto>>() {});

        assertEquals(size, returnedSimpleTestList.size());
        for(int i = 0; i < size; i++) {
            assertEquals(fakeTests.get(i).getContent(), returnedSimpleTestList.get(i).getContent());
        }

    }

    @Test
    public void testGetSimpleTestById() throws Exception {
        SimpleTestEntity fakeTests = createFakeSimpleTests(1).get(0);
        MvcResult result = mockMvc.perform(get(ApiRout.GET_SIMPLE_TEST_BY_ID.getUrl(), fakeTests.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        SimpleTestModel returnedSimpleTest = objectMapper.readValue(jsonResponse, new TypeReference<SimpleTestModel>() {});
        assertEquals(fakeTests.getContent(), returnedSimpleTest.getContent());
    }

    @Test
    public void testGetSimpleTestByIdNotFound() throws Exception {
        mockMvc.perform(get(ApiRout.GET_SIMPLE_TEST_BY_ID.getUrl(), -1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

    }

    @Test
    public void testGenerateExam_Success() throws Exception {
        simpleTestRepo.deleteAll();
        //minimum 2 tests for the same topic.
        int size = TopicConstant.ALL_TOPICS.size()*2;
        List<SimpleTestEntity> fakeTests = createFakeSimpleTests(size);
        MvcResult result = mockMvc.perform(get(ApiRout.GENERATE_EXAM.getUrl()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        List<SimpleTestDto> returnedSimpleTestList = objectMapper.readValue(jsonResponse, new TypeReference<List<SimpleTestDto>>() {});

        assertEquals(TestConstant.NUMBER_OF_TESTS, returnedSimpleTestList.size());
        Set<String> fakeTestsContentSet = new HashSet<>();
        Set<String> returnedSimpleTestContentSet = new HashSet<>();
        for (int i = 0; i < fakeTests.size(); i++) {
            fakeTestsContentSet.add(fakeTests.get(i).getContent());
        }
        for (int i = 0; i < returnedSimpleTestList.size(); i++) {
            returnedSimpleTestContentSet.add(returnedSimpleTestList.get(i).getContent());
        }
        assertTrue(fakeTestsContentSet.containsAll(returnedSimpleTestContentSet));
    }

    @Test
    public void testDeleteSimpleTests_Success() throws Exception {
        Random random = new Random();
        int size = random.nextInt(5)+1;
        List<SimpleTestEntity> fakeTests = createFakeSimpleTests(size);
        SimpleTestDeleteDto simpleTestDeleteDto = new SimpleTestDeleteDto();
        List<Long> ids = new ArrayList<>();

        for(SimpleTestEntity el : fakeTests) {
            ids.add(el.getId());
        }
        simpleTestDeleteDto.setIds(ids);
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDeleteDto);
         mockMvc.perform(delete(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
        List<SimpleTestEntity> entities = simpleTestRepo.findAllById(ids);
        assertTrue(entities.isEmpty());
    }

    @Test
    public void testDeleteSimpleTests_EmptyIds() throws Exception {
        SimpleTestDeleteDto simpleTestDeleteDto = new SimpleTestDeleteDto();
        String jsonRequest = objectMapper.writeValueAsString(simpleTestDeleteDto);
        mockMvc.perform(delete(ApiRout.SIMPLE_TEST.getUrl()).contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }


    @Test
    public void testCheckAnswers_Success() throws Exception {
        simpleTestRepo.deleteAll();
        checkTestAnswersDto = new CheckTestAnswersDto();
        int size = TestConstant.NUMBER_OF_TESTS;
        List<SimpleTestEntity> fakeTests = createFakeSimpleTests(size);
        boolean[] answersStatuses = generateFakeUserAnswers(fakeTests);
        String jsonRequest = objectMapper.writeValueAsString(checkTestAnswersDto);
        MvcResult result = mockMvc.perform(get(ApiRout.CHECK_ANSWERS.getUrl()).contentType(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        SimpleTestsFinalResultModel finalResult = objectMapper.readValue(jsonResponse, SimpleTestsFinalResultModel.class);

        assertEquals(totalMark, finalResult.getTotal());
        for(int i=0; i<answersStatuses.length; i++) {
            assertEquals(answersStatuses[i], finalResult.getAnswers().get(i).getStatus());
            }
        }

}
