package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;
import java.util.Random;

public class Image {

    private int width;
    private int height;
    private List<List<Color>> pixels;


    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        Random random = new Random();
        this.pixels = IntStream.range(0, height)
                                .mapToObj(y -> IntStream.range(0, width)
                                                        .mapToObj(x -> new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)))
                                                        .collect(toList()))
                                .collect(toList());
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public List<List<Color>> getPixels() {
        return pixels;
    }

    public void setColor(int x, int y, Color color) {
        pixels.get(y).set(x, color);
    }
    
} 