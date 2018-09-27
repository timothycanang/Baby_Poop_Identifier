package com.poop.server.engine.api;


import com.poop.server.engine.model.TrgTensorPredict;
import com.poop.server.engine.service.TensorFlowEngine;
import com.poop.server.engine.api.vo.TensorFlowVo;
import com.poop.server.engine.service.TensorFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("api/tensorflow")
public class TensorFlowController {

    @Autowired
    private final TensorFlowEngine engine;
    @Autowired
    private final TensorFlowService tensorFlowService;

    public TensorFlowController(TensorFlowEngine engine, TensorFlowService tensorFlowService) {
        this.engine = engine;
        this.tensorFlowService = tensorFlowService;
    }

    @GetMapping("/version")
    public ResponseEntity<TensorFlowVo> getTensorFlowDetails() throws Exception {
        return new ResponseEntity<TensorFlowVo>(new TensorFlowVo(engine.tensorFlowVersion()), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"}, path = "/predict")
    public ResponseEntity<TrgTensorPredict> predictImage(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<TrgTensorPredict>(tensorFlowService.perdictImage(file.getBytes()),HttpStatus.OK);
    }


}
