package com.poop.server.integration.google.cloud.service;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.automl.v1beta1.AnnotationPayload;
import com.google.cloud.automl.v1beta1.PredictRequest;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public interface GoogleCloudService {

    void authenticate();

    List<AnnotationPayload> predict(
            ByteString content,
            String scoreThreshold)
            throws IOException;
}
