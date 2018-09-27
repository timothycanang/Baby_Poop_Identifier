package com.poop.server.integration.google.cloud.api;


import com.google.cloud.automl.v1beta1.PredictRequest;
import com.google.cloud.automl.v1beta1.PredictResponse;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.stub.StreamObserver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/automl")
public class PredictionController {

    private ArrayList<GeneratedMessageV3> requests;
    private Queue<Object> responses;

    public PredictionController() {
        requests = new ArrayList<>();
        responses = new LinkedList<>();
    }


    @PostMapping(path = "/predict")
    public void predict(@RequestBody PredictRequest request,@RequestBody StreamObserver<PredictResponse> responseObserver) {
        Object response = responses.remove();
        if (response instanceof PredictResponse) {
            requests.add(request);
            responseObserver.onNext((PredictResponse) response);
            responseObserver.onCompleted();
        } else if (response instanceof Exception) {
            responseObserver.onError((Exception) response);
        } else {
            responseObserver.onError(new IllegalArgumentException("Unrecognized response type"));
        }
    }

}
