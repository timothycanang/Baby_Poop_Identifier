package com.poop.server.engine.service;

import com.poop.server.engine.model.TrgTensorPredict;

public interface TensorFlowService {
    TrgTensorPredict perdictImage(byte[] image);
}
