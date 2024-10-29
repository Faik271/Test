package com.test.Test.util;

import com.test.Test.service.SimpleTest.SimpleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private SimpleTestService simpleTestService;

    @Override
    public void run(String... args) throws Exception {
        simpleTestService.createAllTopics();
    }
}
