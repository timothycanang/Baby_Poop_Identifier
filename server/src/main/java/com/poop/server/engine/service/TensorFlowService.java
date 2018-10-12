package com.poop.server.engine.service;

import com.poop.server.engine.model.TrgTensorPredict;

import java.io.UnsupportedEncodingException;

public interface TensorFlowService {
    String tensorFlowVersion() throws UnsupportedEncodingException;
    TrgTensorPredict perdictImage(byte[] image);
}
