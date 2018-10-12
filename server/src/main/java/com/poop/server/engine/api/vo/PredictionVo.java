package com.poop.server.engine.api.vo;


import com.poop.server.engine.model.TrgTensorPredict;

public class PredictionVo implements TrgTensorPredict {

    private String matchPercentage;
    private String match;

    public PredictionVo() {
    }

    public PredictionVo(String matchPercentage, String match) {
        this.matchPercentage = matchPercentage;
        this.match = match;
    }

    @Override
    public String getMatchPercentage() {
        return matchPercentage;
    }

    @Override
    public void setMatchPercentage(String matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    @Override
    public String getMatch() {
        return match;
    }

    @Override
    public void setMatch(String match) {
        this.match = match;
    }
}
