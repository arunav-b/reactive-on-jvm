package com.arunav.learning.rxjava.examples;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

// Functional Programming(FP) : Functional composition + Lazy evaluation
// Reactive programming (RP) : FP++

public class _01Basics {

    // Flowable running on a different thread
    public static Flowable<Long> create() {
        return Flowable.interval(0, 1, TimeUnit.SECONDS);
    }

    // custom producer
    private static Flowable<Integer> createPublisher() {
        return Flowable.create(emitter -> emit(emitter), BackpressureStrategy.BUFFER);
    }

    // Is invoked lazily
    private static void emit(FlowableEmitter<Integer> emitter) throws InterruptedException {
        System.out.println("Emitting.." + Thread.currentThread()); // lazy evaluation
        int count = 0;
        while (count++ < 100) {
            Thread.sleep(200);
            emitter.onNext(count); // Mouth of data channel
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Stream of data running on a different thread
        // create().subscribe(System.out::println);

        Disposable subscriber1 = createPublisher()
                .subscribeOn(Schedulers.computation()) // Running on a different thread
                .filter(data -> data % 2 == 0) // Processor : Both publisher and subscriber
                .map(data -> data * 99)
                .subscribe(System.out::println, error -> System.out.println("ERROR" + error));

        Disposable subscriber2 = createPublisher()
                .subscribeOn(Schedulers.computation()) // Running on a different thread
                .filter(data -> data % 2 == 0) // Processor : Both publisher and subscriber
                .map(data -> data * 0.004)
                .subscribe(System.out::println);

        Thread.sleep(5000);
        System.out.println("Subscriber1 doesn't want anymore data..");
        // subscriber1 sent a dispose signal, but since subscriber2 is still on, producer will continue sending data
        subscriber1.dispose();
        Thread.sleep(5000);
        System.out.println("Done!");
    }
}
