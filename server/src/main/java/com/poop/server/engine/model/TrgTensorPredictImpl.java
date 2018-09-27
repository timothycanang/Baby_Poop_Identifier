package com.poop.server.engine.model;



public class TrgTensorPredictImpl implements TrgTensorPredict {

    private String matchPercentage;
    private String match;

    public TrgTensorPredictImpl() {
    }

    public TrgTensorPredictImpl(String matchPercentage, String match) {
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
