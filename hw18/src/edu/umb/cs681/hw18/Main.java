package edu.umb.cs681.hw18;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        StockQuoteObservable observable = new StockQuoteObservable();
        
        observable.addObserver((Observable<StockEvent> o, StockEvent event) -> System.out.println("Table Observer: " + event.quote()));
        observable.addObserver((Observable<StockEvent> o, StockEvent event) -> System.out.println("3D Observer: " + event.quote()));
        observable.addObserver((Observable<StockEvent> o, StockEvent event) -> System.out.println("Line Chart Observer: " + event.quote()));

        List<DataHandler> dataHandlers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataHandler dataHandler = new DataHandler(observable);
            dataHandlers.add(dataHandler);
            threads.add(new Thread(dataHandler));
        }

        threads.forEach((t) -> t.start());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dataHandlers.forEach((dh) -> dh.setDone());
        threads.forEach((t) -> t.interrupt());
        

    }

}
