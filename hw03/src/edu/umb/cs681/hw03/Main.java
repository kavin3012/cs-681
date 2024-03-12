package edu.umb.cs681.hw03;

import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;

public class Main {

    private static Random random = new Random(123); // Fixed seed value

    private static List<List<Double>> createPoints(int numOfPoints, int numOfDimensions) {
        return IntStream.range(0, numOfPoints)
                        .mapToObj(i -> createSinglePoint(numOfDimensions, random)) // Pass the Random instance
                        .collect(toList());
    }

    private static List<Double> createSinglePoint(int numOfDimensions, Random random) {
        return IntStream.range(0, numOfDimensions)
                        .mapToObj(j -> random.nextDouble() * 100) // Use the Random instance for generating random numbers
                        .collect(toList());
    }

    public static void main(String[] args) {
        // Calculate the distance matrix
        long start = System.currentTimeMillis();
        List<List<Double>> randomPoints = createPoints(1300, 100);
        long end = System.currentTimeMillis();
        System.out.println("Time taken to calculate the distance matrix: " + (end - start) + "ms");

        
        // Calculate the distance between two points
        List<List<Double>> euclidean = Distance.matrix(randomPoints, new Euclidean());
        System.out.println("Distance between the first two points: " + euclidean.get(0).get(1));

        // Calculate the distance between two points using Manhattan distance
        List<List<Double>> manhattan = Distance.matrix(randomPoints, new Manhattan());
        System.out.println("Distance between the first two points: " + manhattan.get(0).get(1));
        
        // Calculate the distance between two points using Cosine distance
        List<List<Double>> cosine = Distance.matrix(randomPoints, new Cosine());
        System.out.println("Distance between the first two points: " + cosine.get(0).get(1));
    }
}
