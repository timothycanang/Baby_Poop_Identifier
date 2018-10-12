package com.poop.server.engine.api.transformer;

import com.poop.server.engine.api.vo.PredictionVo;
import com.poop.server.engine.model.TrgTensorPredict;

public class PredictionTransformer {

    public PredictionTransformer() {
    }

    public PredictionVo toVo(TrgTensorPredict prediction) {
        PredictionVo vo = new PredictionVo();
        vo.setMatch(prediction.getMatch());
        vo.setMatchPercentage(prediction.getMatchPercentage());
        return vo;
    }


}
