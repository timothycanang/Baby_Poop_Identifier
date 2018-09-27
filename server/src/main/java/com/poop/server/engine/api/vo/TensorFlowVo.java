package com.poop.server.engine.api.vo;

public class TensorFlowVo {

    private String tensorFlowVersion;

    public TensorFlowVo(String tensorFlowVersion) {
        this.tensorFlowVersion = tensorFlowVersion;
    }

    public String getTensorFlowVersion() {
        return tensorFlowVersion;
    }

    public void setTensorFlowVersion(String tensorFlowVersion) {
        this.tensorFlowVersion = tensorFlowVersion;
    }
}
