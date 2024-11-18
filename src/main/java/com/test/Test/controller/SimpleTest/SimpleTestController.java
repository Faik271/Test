package com.test.Test.controller.SimpleTest;

import com.test.Test.constant.MessageConstant;
import com.test.Test.dto.SimpleTest.CheckTestAnswersDto;
import com.test.Test.dto.SimpleTest.SimpleTestDeleteDto;
import com.test.Test.dto.SimpleTest.SimpleTestDto;
import com.test.Test.model.SimpleTest.SimpleTestModel;
import com.test.Test.service.SimpleTest.SimpleTestService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simple/test")
public class SimpleTestController {


    private final Logger logInfo = LoggerFactory.getLogger(SimpleTestController.class);

    @Autowired
    private SimpleTestService simpleTestService;


    @PostMapping
    public ResponseEntity createSimpleTest(@RequestBody @Valid SimpleTestDto simpleTest)
    {
        try{
                simpleTestService.createSimpleTest(simpleTest);
                String successMessage = MessageConstant.Http.Test.created;
                return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);



        }catch (Exception exception){
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);

        }
    }



    @PutMapping("/{id}")
    public ResponseEntity updateSimpleTest(@RequestBody @Valid SimpleTestDto simpleTest, @PathVariable Long id)
    {
        try {
            if(simpleTestService.updateSimpleTest(simpleTest, id)){
                String successMessage = MessageConstant.Http.Test.updated;
                return ResponseEntity.status(HttpStatus.OK).body(successMessage);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception exception){
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTestById(@PathVariable Long id){
        try {
            SimpleTestModel simpleTestEntity = simpleTestService.getTestById(id);
            if(simpleTestEntity != null){
                return ResponseEntity.status(HttpStatus.OK).body(simpleTestEntity);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception exception){
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/generate/exam")
    public ResponseEntity generateExam()
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(simpleTestService.generateExam());
        }catch(Exception exception) {
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @GetMapping("/all")
    public ResponseEntity getAllSimpleTests()
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(simpleTestService.getAllSimpleTests());
        }catch(Exception exception) {
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteSimpleTest(@Valid @RequestBody SimpleTestDeleteDto simpleTestDeleteDto)
    {
        try {
            simpleTestService.deleteSimpleTest(simpleTestDeleteDto.getIds());
            String successMessage = MessageConstant.Http.Test.deleted;
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        }catch(Exception exception) {
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/answers/check")
    public ResponseEntity checkAnswers(@RequestBody @Valid CheckTestAnswersDto simpleTestAnswers)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(simpleTestService.checkAnswers(simpleTestAnswers.getAnswers()));
        }catch(Exception exception) {
            logInfo.error(exception.getMessage());
            String errorMessage = MessageConstant.Http.server_error;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


}
