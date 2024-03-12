package edu.umb.cs681.hw03;

import java.util.List;
import java.util.stream.IntStream;

public class Cosine implements DistanceMetric {

    public double distance(List<Double> p1, List<Double> p2) {
        double dotProduct = IntStream
                                .range(0, p1.size())
                                .mapToDouble(i -> p1.get(i) * p2.get(i))
                                .sum();
        double normP1 = Math.sqrt(p1.stream().mapToDouble(i -> i * i).sum());
        double normP2 = Math.sqrt(p2.stream().mapToDouble(i -> i * i).sum());
        
        return 1 - (dotProduct / (normP1 * normP2));
    }
}
