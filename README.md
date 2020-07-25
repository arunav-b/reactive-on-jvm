# Reactive on JVM

## Reactive Manifesto
- Responsive
- Elastic
- Resilient
- Message driven

[Read more](https://www.reactivemanifesto.org/)

<br/>

## Reactive Stream API in Java 9+ -

Support for Reactive Streams has been added to the JDK, Java 9 onwards. Several interfaces have been added in the `java.util.concurrent.Flow` class. The 4 primary interfaces that define reactive streams are:
- `Publisher<T>`: A producer of items (and related control messages) received by Subscribers.
- `Subscriber<T>`: A receiver of messages.
- `Processor<T,R>`: A component that acts as both a Subscriber and Publisher.
- `Subscription`: Message control linking a Publisher and Subscriber. A Subscriber can have multiple subscriptions to a Publisher.

<br/>

## Streams

The word `Observable` or `Flowable` is used to mean a reactive stream of data. Although `Observable` or `Flowable` is a type in RxJava, the other Reactive Streams libraries have other types, such as `Flux` in Reactor and `Source` in Akka Streams, that represent streams of data. Everything in Reactive Streams starts with a stream.

> Difference between `Flowable` & `Observable` is that the `Flowable` has support for backpressure, `Observable` does not.
 
<br/>

## Some common concepts

### Hot & Cold Observables

- A hot Observable is one that cannot be repeated. It starts creating data immediately regardless of whether it has subscribers. Typically it involves interacting with data from the outside world such as mouse inputs, data readings, or web requests.
- A cold Observable is one that can be repeated and does not start until subscribed to. This could be things like a range, file data, or a cached recording of data from a hot Observable.

> Hot Observables typically are candidates for using backpressure flow control strategies such as throttling, buffers, or windows.

### Backpressure

- Backpressure is what happens when there are too many events/data in a stream than the downstream can handle. When this happens in the downstream application, it can cause big problems like OutOfMemory exceptions or starved threads and timeouts. 
- **Backpressure strategies** help us deal with these problems proactively to avoid these problems. There are multiple backpressure strategies as discussed below -  
    - **Dropping**: We simply drop the items above what can be handled (using some criteria such as oldest or newest).
    - **Buffer**: Buffer keeps data over some time period and sticks it in a list, then observes each list. It caches all elements from a stream if too many elements were produced than the subscriber could handle. In this case the buffer is kept in memory and does not affect the data type of the stream. If buffer is used, we have the option of dropping or ignoring any elements above the buffer’s maximum size. 
    - **Window**: Window is much like buffer but results in Observables instead of lists.
    - **Latest**: Latest takes only the last element from a stream if too many elements were produced than the subscriber could handle.
    - **Throttling**: Throttle first (`throttleFirst` in RxJava) drops any elements from the stream (after the first element emitted) for some given duration. Throttle last is very similar only emitting the last element emitted during the time period instead of the first. Reactor has similar methods, `sample` and `sampleFirst`. Akka Streams has a similar method named `throttle`.
    
<br/>

## RxJava

<br/>

## Reactor

<br/>

## Akka Streams

<br/>

## Smallrye Mutiny

<br/>

    
## References
- Reactive Streams in Java by Adam L. Davis


