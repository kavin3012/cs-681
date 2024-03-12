package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.*;
import java.util.stream.IntStream;

public class Distance {
	public static double get(List<Double> p1, List<Double> p2) {
		return Distance.get(p1, p2, new Euclidean());
	}

	public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
		return metric.distance(p1, p2);
	}

	public static List<List<Double>> matrix(List<List<Double>> points) {
		return Distance.matrix(points, new Euclidean());
	}

	public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
		return IntStream.range(0, points.size())
				.mapToObj(i -> singleRowCalculation(i, points, metric))
				.collect(toList());
	}

	private static List<Double> singleRowCalculation(int rowNumber, List<List<Double>> points, DistanceMetric metric) {
		return IntStream.range(0, points.size())
				.mapToDouble(j -> Distance.get(points.get(rowNumber), points.get(j), metric))
				.collect(ArrayList::new, List::add, List::addAll);
	}

}
