package edu.umb.cs681.hw03;

import java.util.List;
import java.util.stream.IntStream;

public class Euclidean implements DistanceMetric {

	public double distance(List<Double> p1, List<Double> p2) {
		double sumOfSquares = IntStream
								.range(0, p1.size())
								.mapToDouble(i -> p1.get(i) - p2.get(i))
								.map(n -> n*n)
								.sum();
		
		return Math.sqrt(sumOfSquares);
				
	}
}
