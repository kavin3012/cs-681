package edu.umb.cs681.hw04;

public class Color {
    private int redScale;
    private int greenScale;
    private int blueScale;

    public Color(int redScale, int greenScale, int blueScale) {
        this.redScale = redScale;
        this.greenScale = greenScale;
        this.blueScale = blueScale;
    }

    public int red() {
        return redScale;
    }

    public int green() {
        return greenScale;
    }

    public int blue() {
        return blueScale;
    }
}
