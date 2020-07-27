package com.arunav.learning.rxjava.labs.lab1.stockprice;

import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Producer {

    public Flowable<String> fetchPrices(List<String> tickers) {
        return Flowable.interval(1, 1, TimeUnit.SECONDS)
                .map(index -> tickers.get((int) (index % tickers.size())))
                .map(ticker -> ticker + ":$" + StockFetcher.getPrice(ticker));
    }
}
