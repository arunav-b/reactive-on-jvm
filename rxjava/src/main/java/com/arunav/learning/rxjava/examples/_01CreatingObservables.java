package com.arunav.learning.rxjava.examples;

import io.reactivex.Observable;

public class _01CreatingObservables {

    private static void _create() {
        System.out.println("Observable using create");
        Observable<Integer> intSource = Observable.create(observableEmitter -> {
            for (int i = 0; i < 10; i++)
                observableEmitter.onNext(i);
            observableEmitter.onComplete();
        });
        intSource.subscribe(System.out::println);
    }

    private static int count = 5;

    private static void _defer() {
        System.out.println("Observable using defer");
        Observable<Integer> source = Observable.defer(() -> Observable.range(1, count));
        source.subscribe(System.out::println);
        count = 10;
        source.subscribe(System.out::println);
    }

    private static void _fromCallable() {
        System.out.println("Observable using fromCallable");
        System.out.println("Exception is getting passed to the subscriber, if exception generated in Observable");
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(System.out::println, System.out::println);

        System.out.println("Exception is not getting passed to the subscriber, if exception generated in Observable");
        Observable.just(1 / 0)
                .subscribe(System.out::println, System.out::println);
    }

    public static void main(String[] args) {
        _create();
        _defer();
        _fromCallable();
    }
}
