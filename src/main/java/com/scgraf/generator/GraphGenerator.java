package com.scgraf.generator;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.data_structures.graph.Node;
import com.scgraf.data_structures.graph.Path;
import com.scgraf.data_structures.tuples.Size;

import java.util.Random;

public class GraphGenerator extends Thread implements IGenerator<Graph> {
    private static final Random randomGenerator = new Random();

    public enum GeneratingType {
        RANDOM("Random"),
        LINEAR_Y("Linear Y"),
        LINEAR_X("Linear X"),
        SKEWED_LINEAR("Skewed linear"),
        XX_YY("x^2 - y^2"),
        SINX_COSY("sin(x) - cos(y)"),
        SQRTXY("sqrt(x*y)");

        public final String str;

        GeneratingType(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public static Graph Generate(Size size, double maxPathWeight) {
        return Generate(size, maxPathWeight, GeneratingType.RANDOM);
    }

    public static Graph Generate(Size size, double maxPathWeight, GeneratingType type) {
        if (type == GeneratingType.RANDOM)
            return Generate(size, maxPathWeight, (x, y) -> maxPathWeight * randomGenerator.nextDouble());
        else if (type == GeneratingType.LINEAR_Y)
            return Generate(size, maxPathWeight, (x, y) -> maxPathWeight / size.height() * y);
        else if (type == GeneratingType.LINEAR_X)
            return Generate(size, maxPathWeight, (x, y) -> maxPathWeight / size.width() * x);
        else if (type == GeneratingType.SKEWED_LINEAR)
            return Generate(size, maxPathWeight, (x, y) -> maxPathWeight / (size.width() * 2) * x + maxPathWeight / (size.height() * 2) * y);
        else if (type == GeneratingType.XX_YY)
            return Generate(size, maxPathWeight, (x, y) -> (Math.abs(maxPathWeight / (size.width() * 2) * x * x - (maxPathWeight / (size.height() * 2)) * y * y)));
        else if (type == GeneratingType.SINX_COSY)
            return Generate(size, maxPathWeight, (x, y) -> Math.abs(maxPathWeight * Math.sin(x) / 2 - maxPathWeight * Math.cos(y) / 2));
        else if (type == GeneratingType.SQRTXY) return Generate(size, maxPathWeight, (x, y) -> Math.sqrt(x * y));
        else return null;
    }

    public static Graph Generate(Size size, double maxWeight, GenerateWeightCallable<Double> weightGeneratingFunction) {
        Graph graph = new Graph();
        graph.setSize(size).setWeight(maxWeight).build();
        for (int y = 0; y < size.height(); y++) {
            for (int x = 0; x < size.width(); x++) {
                Node generatedNode = graph.getNode(y, x);
                if (x > 0)
                    graph.setupPath(generatedNode, Path.Side.LEFT, weightGeneratingFunction.getWeight(x, y));
                if (x < size.width() - 1)
                    graph.setupPath(generatedNode, Path.Side.RIGHT, weightGeneratingFunction.getWeight(x, y));
                if (y > 0)
                    graph.setupPath(generatedNode, Path.Side.TOP, weightGeneratingFunction.getWeight(x, y));
                if (y < size.height() - 1)
                    graph.setupPath(generatedNode, Path.Side.BOTTOM, weightGeneratingFunction.getWeight(x, y));
            }
        }
        return graph;
    }

    public static Graph GenerateExample() {
        Graph g = new Graph();
        g.setWidth(3).setHeight(3).setWeight(3).build();

        g.setupPath(g.getNode(0, 0), Path.Side.RIGHT, 1.4);

        g.setupPath(g.getNode(0, 1), Path.Side.RIGHT, 0.1);
        g.setupPath(g.getNode(0, 1), Path.Side.BOTTOM, 2.3);

        g.setupPath(g.getNode(0, 2), Path.Side.BOTTOM, 0.1);

        g.setupPath(g.getNode(1, 0), Path.Side.RIGHT, 1.3);
        g.setupPath(g.getNode(1, 0), Path.Side.BOTTOM, 0.1);

        g.setupPath(g.getNode(1, 1), Path.Side.RIGHT, 2);
        g.setupPath(g.getNode(1, 1), Path.Side.BOTTOM, 3);

        g.setupPath(g.getNode(2, 0), Path.Side.RIGHT, 0.2);

        g.setupPath(g.getNode(2, 1), Path.Side.RIGHT, 1.0);

        return g;
    }

    protected interface GenerateWeightCallable<T> {
        T getWeight(int x, int y);
    }
}
