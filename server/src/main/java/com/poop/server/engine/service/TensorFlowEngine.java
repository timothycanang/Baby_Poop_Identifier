package com.poop.server.engine.service;

import java.io.UnsupportedEncodingException;

public interface TensorFlowEngine {
    String tensorFlowVersion() throws UnsupportedEncodingException;
}
