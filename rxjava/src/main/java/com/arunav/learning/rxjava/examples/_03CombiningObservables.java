package com.arunav.learning.rxjava.examples;

import io.reactivex.Observable;

public class _03CombiningObservables {

    public static void main(String[] args) {
        _merge();
        System.out.println("---------------------------------");
        _flatMap();
        System.out.println("---------------------------------");
        _flatMapAddingObservables();
    }

    private static void _flatMapAddingObservables() {

//        Observable.just(2, 5, 7, 15, 13)
//                .flatMap();
    }

    private static void _flatMap() {
        Observable<String> source = Observable.just("521934/2342/FOXTROT", "21962/12112/TANGO/78886");
        source
                .flatMap(src -> Observable.fromArray(src.split("/")))
                .filter(src -> src.matches("[0-9]+"))
                .map(str -> Integer.parseInt(str) % 100)
                .subscribe(System.out::println);
    }

    private static void _merge() {
        Observable<Integer> src1 = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> src2 = Observable.just(15, 14, 13, 12, 11);
        Observable<Integer> observable = Observable.merge(src1, src2);
        observable.subscribe(System.out::println);
    }
}


