package edu.umb.cs681.hw13;


public class RunnableClass implements Runnable {
    public void run() {
        OneDrivePPTWithoutRaceConditions ppt = OneDrivePPTWithoutRaceConditions.getInstance();
        System.out.println(ppt.readFile());
        ppt.writeToFile("random ");
        System.out.println(ppt.readFile());
    }
}