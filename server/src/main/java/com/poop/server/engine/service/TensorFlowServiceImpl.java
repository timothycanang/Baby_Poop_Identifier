package com.poop.server.engine.service;

import com.poop.server.engine.model.TrgTensorPredict;
import com.poop.server.engine.model.TrgTensorPredictImpl;
import org.springframework.stereotype.Service;
import org.tensorflow.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.poop.server.Constant.TENSORFLOW_GRAPH_NAME;
import static com.poop.server.Constant.TENSORFLOW_INCEPTION_FOLDER_PATH;
import static com.poop.server.Constant.TENSORFLOW_LABEL_NAME;


@Service
@Transactional
public class TensorFlowServiceImpl implements TensorFlowService {

    private byte[] graphDef;
    private List<String> labels;

    TensorFlowServiceImpl() {
        graphDef = readAllBytesOrExit(Paths.get(TENSORFLOW_INCEPTION_FOLDER_PATH, TENSORFLOW_GRAPH_NAME));
        labels = readAllLinesOrExit(Paths.get(TENSORFLOW_INCEPTION_FOLDER_PATH, TENSORFLOW_LABEL_NAME));
    }

    @Override
    public String tensorFlowVersion() throws UnsupportedEncodingException {
            return TensorFlow.version();
    }


    @Override
    public TrgTensorPredict perdictImage(byte[] imageBytes) {
        try (Tensor image = Tensor.create(imageBytes)) {
            float[] labelProbabilities
                    = executeInceptionGraph(graphDef, image);
            int bestLabelIdx = maxIndex(labelProbabilities);
            System.out.println(String.format("BEST MATCH: %s (%.2f%% likely)", labels.get(bestLabelIdx), labelProbabilities[bestLabelIdx] * 100f));
            return new TrgTensorPredictImpl((labelProbabilities[bestLabelIdx] * 100f) + " %", labels.get(bestLabelIdx));
        }
    }


    private static float[] executeInceptionGraph(byte[] graphDef, Tensor image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session s = new Session(g);
                 Tensor<?> result = s.runner().feed("Placeholder", image).fetch("final_result").run().get(0)) {
                final long[] rshape = result.shape();
//                if (result.numDimensions() != 2 || rshape[0] != 1) {
//                    throw new RuntimeException(
//                            String.format(
//                                    "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
//                                    Arrays.toString(rshape)));
//                }
                int nlabels = (int) rshape[1];
                float[][] res = new float[1][nlabels];
                result.copyTo(res);
                return res[0];
            }
        }
    }

//    public static void executeInceptionGraphV2(byte[] graphDef, Tensor image) {
//        try (Graph g = new Graph();
//             Session s = new Session(g)) {
//            // Construct a graph to add two float Tensors, using placeholders.
//            Output x = g.opBuilder("Placeholder", "x").setAttr("dtype", DataType.FLOAT).build().output(0);
//            Output y = g.opBuilder("Placeholder", "y").setAttr("dtype", DataType.FLOAT).build().output(0);
//            Output z = g.opBuilder("Add", "z").addInput(x).addInput(y).build().output(0);
//            // Execute the graph multiple times, each time with a different value of x and y
//            float[] X = new float[]{1,2,3};
//            float[] Y = new float[]{4,5,6};
//            for (int i = 0; i < X.length; i++) {
//                try (Tensor tx = Tensor.create(X[i]);
//                     Tensor ty = Tensor.create(Y[i]);
//                     Tensor tz = s.runner().feed("x", tx).feed("y", ty).fetch("z").run().get(0)) {
//                    System.out.println(X[i] + " + " + Y[i] + " = " + tz.floatValue());
//                }
//            }
//        }
//    }
//}


    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static List<String> readAllLinesOrExit(Path path) {
        try {
            return Files.readAllLines(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
