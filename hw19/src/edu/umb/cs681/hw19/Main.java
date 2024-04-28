package edu.umb.cs681.hw19;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static List<List<String>> getData() {
        Path path = Paths.get("./POWER_Regional_Daily_20240101_20240301.csv");
        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(line -> Stream.of(line.split(","))
                    .collect(Collectors.toList())).collect(Collectors.toList());
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<List<String>> data = getData();
        data = data.subList(11, data.size());

        // get average PS for each lat, lon
        Map<String, Double> avgPS = data.stream()
                .parallel()
                .collect(Collectors.groupingBy(
                        row -> row.get(0) + "," + row.get(1),
                        Collectors.averagingDouble(row -> Double.parseDouble(row.get(4)))
                ));
        
        // get average PRECTOTCORR for each lat, lon
        Map<String, Double> avgPRECTOTCORR = data.stream()
                .parallel()
                .collect(Collectors.groupingBy(
                        row -> row.get(0) + "," + row.get(1),
                        Collectors.averagingDouble(row -> Double.parseDouble(row.get(5)))
                ));

        // get standard deviation PS for each lat, lon
        Map<String, Double> stdPS = data.stream()
                .parallel()
                .collect(Collectors.groupingBy(
                        row -> row.get(0) + "," + row.get(1),
                        Collectors.collectingAndThen(
                                Collectors.mapping(row -> Double.parseDouble(row.get(4)), Collectors.toList()),
                                list -> Math.sqrt(list.stream().mapToDouble(d -> d).map(d -> Math.pow(d - list.stream().mapToDouble(t -> t).average().getAsDouble(), 2)).sum() / list.size())
                        )
                ));
        
        // get standard deviation PRECTOTCORR for each lat, lon
        Map<String, Double> stdPRECTOTCORR = data.stream()
                .parallel()
                .collect(Collectors.groupingBy(
                        row -> row.get(0) + "," + row.get(1),
                        Collectors.collectingAndThen(
                                Collectors.mapping(row -> Double.parseDouble(row.get(5)), Collectors.toList()),
                                list -> Math.sqrt(list.stream().mapToDouble(d -> d).map(d -> Math.pow(d - list.stream().mapToDouble(t -> t).average().getAsDouble(), 2)).sum() / list.size())
                        )
                ));
        
        // get median PS of all
        double medianPS = data.stream()
                .parallel()
                .mapToDouble(row -> Double.parseDouble(row.get(4)))
                .sorted()
                .skip(data.size() / 2)
                .limit(1)
                .sum();
        
        // get median PRECTOTCORR of all
        double medianPRECTOTCORR = data.stream()
                .parallel()
                .mapToDouble(row -> Double.parseDouble(row.get(5)))
                .sorted()
                .skip(data.size() / 2)
                .limit(1)
                .sum();

        System.out.println("Average PS for each lat, lon: " + avgPS);
        System.out.println("Average PRECTOTCORR for each lat, lon: " + avgPRECTOTCORR);
        System.out.println("Standard deviation PS for each lat, lon: " + stdPS);
        System.out.println("Standard deviation PRECTOTCORR for each lat, lon: " + stdPRECTOTCORR);
        System.out.println("Median PS of all: " + medianPS);
        System.out.println("Median PRECTOTCORR of all: " + medianPRECTOTCORR);
        
    }
}
