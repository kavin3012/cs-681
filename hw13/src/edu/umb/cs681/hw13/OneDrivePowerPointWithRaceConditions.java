package edu.umb.cs681.hw13;

public class OneDrivePowerPointWithRaceConditions {

    private String content;
    private static OneDrivePowerPointWithRaceConditions instance = null;

    public OneDrivePowerPointWithRaceConditions() {
        this.content = "";
    }

    public static OneDrivePowerPointWithRaceConditions getInstance() {
        if (instance == null) {
            instance = new OneDrivePowerPointWithRaceConditions();
        }
        return instance;
    }

    public String readFile() {
        return content;
    }

    public void writeToFile(String s) {
        this.content = this.content + s;
    }

}