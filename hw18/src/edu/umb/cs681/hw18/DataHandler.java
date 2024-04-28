package edu.umb.cs681.hw18;

import java.util.concurrent.atomic.AtomicBoolean;

public class DataHandler implements Runnable {

    private AtomicBoolean done = new AtomicBoolean(false);
    private StockQuoteObservable observable;

    public DataHandler(StockQuoteObservable observable) {
        this.observable = observable;
    }

    public void setDone() {
        done.set(true);
    }

    public void run() {
        while (true) {
            if (done.get()) {
                break;
            }
            observable.changeQuote("ticker", 12233F);
        }
    }

}
