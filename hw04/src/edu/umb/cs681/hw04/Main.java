package edu.umb.cs681.hw04;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    
    public static void main(String[] args) {
        Image origImg = new Image(2, 2);
        int width = origImg.width();
        int height = origImg.height();
        int[] counter = new int[1];
        counter[0] = 0;

        Image newImg = origImg
                            .getPixels()
                            .stream()
                            .flatMap(row -> row.stream())
                            .map(pixColor -> {
                                    int r = pixColor.red();
                                    int g = pixColor.green();
                                    int b = pixColor.blue();
                                    int avg = (r + g + b) / 3;
                                    return avg;
                                    })
                            .collect(() -> new Image(width, height),
                                    (img, avg) -> {
                                        int x = counter[0] % width;
                                        int y = counter[0] / width; 
                                        img.setColor(x , y, new Color(avg, avg, avg));
                                        counter[0]++;
                                    },
                                    (img1, img2) -> {
                                        // assuming there are no parallel streams
                                        // hence the logic is nothing here
                                    });
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color orgColor = origImg.getPixels().get(j).get(i);
                Color newColor = newImg.getPixels().get(j).get(i);
                System.out.println("Original Color: " + orgColor.red() + "," + orgColor.green() + "," + orgColor.blue() + " New Color: " + newColor.red() + "," + newColor.green() + "," + newColor.blue());
            }
        }
    }
}
