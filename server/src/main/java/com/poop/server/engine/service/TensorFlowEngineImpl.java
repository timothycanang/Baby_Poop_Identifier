package com.poop.server.engine.service;


import org.springframework.stereotype.Service;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@Transactional
public class TensorFlowEngineImpl implements TensorFlowEngine {

    @Override
    public String tensorFlowVersion() throws UnsupportedEncodingException {
        try (Graph g = new Graph()) {
            final String value = TensorFlow.version();
            try (Tensor t = Tensor.create(value.getBytes(StandardCharsets.UTF_8))) {
                g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
            }
            try (Session s = new Session(g);
                 Tensor output = s.runner().fetch("MyConst").run().get(0)) {
                System.out.println(new String(output.bytesValue(), StandardCharsets.UTF_8));
            }

            return value;
        }
    }

}
