package com.poop.server.engine.api;


import com.poop.server.engine.api.transformer.PredictionTransformer;
import com.poop.server.engine.api.vo.PredictionVo;
import com.poop.server.engine.model.TrgTensorPredict;
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

    private final TensorFlowService tensorFlowService;
    private PredictionTransformer transformer = new PredictionTransformer();

    @Autowired
    public TensorFlowController(TensorFlowService tensorFlowService) {
        this.tensorFlowService = tensorFlowService;
    }

    /**
     * @return ResponseEntity<TensorFlowVo>
     * @throws Exception
     */
    @GetMapping("/version")
    public ResponseEntity<TensorFlowVo> getTensorFlowDetails() throws Exception {
        return new ResponseEntity<TensorFlowVo>(new TensorFlowVo(tensorFlowService.tensorFlowVersion()), HttpStatus.OK);
    }

    /**
     * @param file
     * @return ResponseEntity<TrgTensorPredict>
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"}, path = "/predict")
    public ResponseEntity<PredictionVo> predictImage(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<PredictionVo>(transformer.toVo(tensorFlowService.perdictImage(file.getBytes())), HttpStatus.OK);
    }


}
