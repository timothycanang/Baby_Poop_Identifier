package com.poop.server.engine.service;

import com.poop.server.engine.model.TrgTensorPredict;
import com.poop.server.engine.model.TrgTensorPredictImpl;
import org.springframework.stereotype.Service;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.poop.server.Constant.TENSORFLOW_INCEPTION_FOLDER_PATH;


@Service
@Transactional
public class TensorFlowServiceImpl implements TensorFlowService{
    private byte[] graphDef;
    private List<String> labels;

      TensorFlowServiceImpl() {
        graphDef = readAllBytesOrExit(Paths.get(TENSORFLOW_INCEPTION_FOLDER_PATH, "tensorflow_inception_graph.pb"));
        labels = readAllLinesOrExit(Paths.get(TENSORFLOW_INCEPTION_FOLDER_PATH, "imagenet_comp_graph_label_strings.txt"));
    }

    @Override
    public TrgTensorPredict perdictImage(byte[] imageBytes) {
        try (Tensor image = Tensor.create(imageBytes)) {
            float[] labelProbabilities = executeInceptionGraph(graphDef, image);
            int bestLabelIdx = maxIndex(labelProbabilities);
            System.out.println(String.format("BEST MATCH: %s (%.2f%% likely)", labels.get(bestLabelIdx), labelProbabilities[bestLabelIdx] * 100f));
            return new TrgTensorPredictImpl((labelProbabilities[bestLabelIdx] * 100f) + " %", labels.get(bestLabelIdx));
        }
    }


    private static float[] executeInceptionGraph(byte[] graphDef, Tensor image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session s = new Session(g);
                 Tensor result = s.runner().feed("DecodeJpeg/contents", image).fetch("softmax").run().get(0)) {
                final long[] rshape = result.shape();
                if (result.numDimensions() != 2 || rshape[0] != 1) {
                    throw new RuntimeException(
                            String.format(
                                    "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                                    Arrays.toString(rshape)));
                }
                int nlabels = (int) rshape[1];
                float[][] res = new float[1][nlabels];
                result.copyTo(res);
                return res[0];
            }
        }
    }

        private static int maxIndex ( float[] probabilities){
            int best = 0;
            for (int i = 1; i < probabilities.length; ++i) {
                if (probabilities[i] > probabilities[best]) {
                    best = i;
                }
            }
            return best;
        }

        private static byte[] readAllBytesOrExit (Path path){
            try {
                return Files.readAllBytes(path);
            } catch (IOException e) {
                System.err.println("Failed to read [" + path + "]: " + e.getMessage());
                System.exit(1);
            }
            return null;
        }

        private static List<String> readAllLinesOrExit (Path path){
            try {
                return Files.readAllLines(path, Charset.forName("UTF-8"));
            } catch (IOException e) {
                System.err.println("Failed to read [" + path + "]: " + e.getMessage());
                System.exit(0);
            }
            return null;
        }
    }
