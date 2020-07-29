package com.arunav.learning.rxjava.examples;

import io.reactivex.Flowable;

public class _02BasicOperators {

    private static Flowable<Integer> publisher() {
        return Flowable.range(1, 100);
    }

    public static void main(String[] args) throws InterruptedException {
        publisher().skip(10)
                .takeWhile(i -> i < 50)
                .subscribe(System.out::println);

    }
}
