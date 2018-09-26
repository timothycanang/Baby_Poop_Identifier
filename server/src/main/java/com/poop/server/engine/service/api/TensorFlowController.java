package com.poop.server.engine.service.api;


import com.poop.server.engine.service.TensorFlowEngine;
import com.poop.server.engine.service.api.vo.TensorFlowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/engine")
public class TensorFlowController {

    @Autowired
    private final TensorFlowEngine engine;

    public TensorFlowController(TensorFlowEngine engine) {
        this.engine = engine;
    }

    @GetMapping("/version")
    public ResponseEntity<TensorFlowVo> getTensorFlowDetails() throws Exception {
        return new ResponseEntity<TensorFlowVo>(new TensorFlowVo(engine.tensorFlowVersion()), HttpStatus.OK);
    }

}
