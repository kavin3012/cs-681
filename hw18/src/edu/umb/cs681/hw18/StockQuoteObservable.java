package edu.umb.cs681.hw18;

import java.util.concurrent.ConcurrentHashMap;

public class StockQuoteObservable extends Observable<StockEvent> {

    private ConcurrentHashMap<String, Float> tickerQuote = new ConcurrentHashMap<>();
    
    public void changeQuote(String ticker, Float quote) {
        tickerQuote.put(ticker, quote);
        notifyObservers(new StockEvent(ticker, quote));
    }
    
}
