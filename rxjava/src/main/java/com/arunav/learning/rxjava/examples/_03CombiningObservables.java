package com.arunav.learning.rxjava.examples;

import io.reactivex.Observable;

public class _03CombiningObservables {

    public static void main(String[] args) {
        usingMerge();
        usingFlatMap();
    }

    private static void usingFlatMap() {
    }

    private static void usingMerge() {
        Observable<Integer> src1 = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> src2 = Observable.just(15, 14, 13, 12, 11);
        Observable.merge(src1, src2, src1)
                .subscribe(System.out::println);
    }
}


