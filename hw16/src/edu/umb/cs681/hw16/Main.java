package edu.umb.cs681.hw16;

public class Main {

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        Point taxiLocation = new Point(0, 0);
        Point destLocation = new Point(100, 100);
        Taxi taxi = new Taxi(dispatcher, taxiLocation, destLocation);
        Thread taxiThread = new Thread(taxi);
        taxiThread.start();

        Point taxiLocation2 = new Point(0, 0);
        Point destLocation2 = new Point(100, 100);
        Taxi taxi2 = new Taxi(dispatcher, taxiLocation2, destLocation2);
        Thread taxiThread2 = new Thread(taxi2);
        taxiThread2.start();

        Point taxiLocation3 = new Point(0, 0);
        Point destLocation3 = new Point(100, 100);
        Taxi taxi3 = new Taxi(dispatcher, taxiLocation3, destLocation3);
        Thread taxiThread3 = new Thread(taxi2);
        taxiThread3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        taxi.setDone();
        taxi2.setDone();
        taxi3.setDone();

        taxiThread.interrupt();
        taxiThread2.interrupt();
        taxiThread3.interrupt();
    }
    
}
