package edu.umb.cs681.hw02;

import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {

        Map<String, String> transcript = new HashMap<>();

        transcript.put("CS680", "A");
        transcript.put("CS188", "B+");
        transcript.put("CS681", "B"); 
        transcript.put("CHEM470", "A");
        transcript.put("HUM520", "A");
        transcript.put("CS520", "A");
        transcript.put("CS682", "A");
        transcript.put("CS420", "A");
        transcript.put("CS613", "A-");
        transcript.put("CS341", "A-");
        transcript.put("CS345", "A-");
        transcript.put("CS520", "A-");        


        Map<String, Double> groupByUGorG = transcript
                .entrySet()
                .stream()
                .collect(groupingBy(entry -> {
                    if (Integer.parseInt(entry.getKey().replaceAll("[^0-9]", "")) < 600) return "UG";
                    return "G";
                }, averagingDouble(e -> {
                    switch (e.getValue()) {
                        case "A":
                            return 4.0;
                        case "A-":
                            return 3.7;
                        case "B+":
                            return 3.3;
                        case "B":
                            return 3.0;
                        default:
                            return 0.0;
                    }
                })));
        System.out.println("UG GPA: " + groupByUGorG.get("UG"));
        System.out.println("G GPA: " + groupByUGorG.get("G"));


        double majorUGGPA = transcript
                .entrySet()
                .stream()
                .filter(entry -> Integer.parseInt(entry.getKey().replaceAll("[^0-9]", "")) < 600)
                .filter(entry -> entry.getKey().startsWith("CS"))
                .mapToDouble(e -> {
                    switch (e.getValue()) {
                        case "A":
                            return 4.0;
                        case "A-":
                            return 3.7;
                        case "B+":
                            return 3.3;
                        case "B":
                            return 3.0;
                        default:
                            return 0.0;
                    }
                })
                .average()
                .orElse(0.0);
        System.out.println("Major UG GPA: " + majorUGGPA);
        
        double countMajorUG = transcript
                .entrySet()
                .stream()
                .filter(entry -> Integer.parseInt(entry.getKey().replaceAll("[^0-9]", "")) < 600)
                .filter(entry -> entry.getKey().startsWith("CS"))
                .count();
        System.out.println("Major UG courses: " + countMajorUG);
        
        double majorGGPA = transcript
                .entrySet()
                .stream()
                .filter(entry -> Integer.parseInt(entry.getKey().replaceAll("[^0-9]", "")) >= 600)
                .filter(entry -> entry.getKey().startsWith("CS"))
                .mapToDouble(e -> {
                    switch (e.getValue()) {
                        case "A":
                            return 4.0;
                        case "A-":
                            return 3.7;
                        case "B+":
                            return 3.3;
                        case "B":
                            return 3.0;
                        default:
                            return 0.0;
                    }
                })
                .average()
                .orElse(0.0);
        System.out.println("Major G GPA: " + majorGGPA);


        double countMajorG = transcript
                .entrySet()
                .stream()
                .filter(entry -> Integer.parseInt(entry.getKey().replaceAll("[^0-9]", "")) >= 600)
                .filter(entry -> entry.getKey().startsWith("CS"))
                .count();
        System.out.println("Major G courses: " + countMajorG);
        
        double majorGPA = transcript
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("CS"))
                .mapToDouble(e -> {
                    switch (e.getValue()) {
                        case "A":
                            return 4.0;
                        case "A-":
                            return 3.7;
                        case "B+":
                            return 3.3;
                        case "B":
                            return 3.0;
                        default:
                            return 0.0;
                    }
                })
                .average()
                .orElse(0.0);
        System.out.println("Cumm Major GPA: " + majorGPA);
    }
}
