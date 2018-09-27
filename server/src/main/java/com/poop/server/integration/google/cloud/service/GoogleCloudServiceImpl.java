package com.poop.server.integration.google.cloud.service;


import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.automl.v1beta1.*;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.poop.server.Constant.AUTOML_MODEL_ID;
import static com.poop.server.Constant.AUTOML_PROJECT_ID;
import static com.poop.server.Constant.COMPUTE_REGION;

@Service
public class GoogleCloudServiceImpl implements GoogleCloudService {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleCloudServiceImpl.class);
    private Storage storage;


    @Override
    public void authenticate() {
        try {
            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(this.getClass().getResourceAsStream("/automl_service_key.json")))
                    .build()
                    .getService();
        } catch (IOException e) {
            LOG.error("Error authenticating google : " + e);
        }
    }


    /**
     * @param byteImage
     * @param scoreThreshold optional
     * @return List<AnnotationPayload>
     * @throws IOException
     */

    @Override
    public List<AnnotationPayload> predict(ByteString byteImage, String scoreThreshold)
            throws IOException {
        PredictionServiceClient predictionClient = PredictionServiceClient.create();
        ModelName name = ModelName.of(AUTOML_PROJECT_ID, COMPUTE_REGION, AUTOML_MODEL_ID);
        Image image = Image.newBuilder().setImageBytes(byteImage).build();
        ExamplePayload examplePayload = ExamplePayload.newBuilder().setImage(image).build();
        Map<String, String> params = new HashMap<>();
        if (scoreThreshold != null) {
            params.put("score_threshold", scoreThreshold);
        }
        return predictionClient.predict(name, examplePayload, params).getPayloadList();
    }
}
