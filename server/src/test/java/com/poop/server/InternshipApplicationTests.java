package com.poop.server;


import com.google.protobuf.ByteString;
import com.poop.server.engine.model.TrgTensorPredict;
import com.poop.server.engine.service.TensorFlowService;
import com.poop.server.integration.google.cloud.service.GoogleCloudService;
import com.poop.server.integration.google.cloud.service.GoogleCloudServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InternshipApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(InternshipApplicationTests.class);

    @Autowired
    TensorFlowService tensorFlowService;

    @Test
    public void tensorFlowTest(){

    }

}
