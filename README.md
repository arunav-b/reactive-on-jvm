# 1. Reactive on JVM

## 1.1. Reactive Manifesto

- For an application to be reactive it has to the meet the requirements as specified in the  reactive manifesto, which are -
    - Responsiveness
    - Elasticity
    - Resiliency
    - Message driven communication

[Read more](https://www.reactivemanifesto.org/)

<br/>

## 1.2. Reactive Stream API in Java 9+ -

Support for Reactive Streams has been added to the JDK, Java 9 onwards. Several interfaces have been added in the `java.util.concurrent.Flow` class. The 4 primary interfaces that define reactive streams are:
- `Publisher<T>`: A producer of items (and related control messages) received by Subscribers.
- `Subscriber<T>`: A receiver of messages.
- `Processor<T,R>`: A component that acts as both a Subscriber and Publisher.
- `Subscription`: Message control linking a Publisher and Subscriber. A Subscriber can have multiple subscriptions to a Publisher.

<br/>

## 1.3. Some common concepts

### 1.3.a. Streams

- The word `Observable` or `Flowable` is used to mean a reactive stream of data. Although `Observable` or `Flowable` is a type in RxJava, the other Reactive Streams libraries have other types, such as `Flux` in Reactor and `Source` in Akka Streams, that represent streams of data. Everything in Reactive Streams starts with a stream.

> Difference between `Flowable` & `Observable` is that the `Flowable` has support for backpressure, `Observable` does not.

### 1.3.b. Hot & Cold Observables

- A **hot Observable** is one that cannot be repeated. It starts creating data immediately regardless of whether it has subscribers. Typically it involves interacting with data from the outside world such as mouse inputs, data readings, or web requests.
- A **cold Observable** is one that can be repeated and does not start until subscribed to. This could be things like a range, file data, or a cached recording of data from a hot Observable.

> Hot Observables typically are candidates for using backpressure flow control strategies such as throttling, buffers, or windows.

### 1.3.c. Backpressure

- **Backpressure** is what happens when there are too many events/data in a stream than the downstream can handle. When this happens in the downstream application, it can cause big problems like OutOfMemory exceptions or starved threads and timeouts.
 
- **Backpressure strategies** help us deal with these problems proactively to avoid these problems. There are multiple backpressure strategies as discussed below -  
    - **Dropping**: We simply drop the items above what can be handled (using some criteria such as oldest or newest).
    - **Buffer**: Buffer keeps data over some time period and sticks it in a list, then observes each list. It caches all elements from a stream if too many elements were produced than the subscriber could handle. In this case the buffer is kept in memory and does not affect the data type of the stream. If buffer is used, we have the option of dropping or ignoring any elements above the buffer’s maximum size. 
    - **Window**: Window is much like buffer but results in Observables instead of lists.
    - **Latest**: Latest takes only the last element from a stream if too many elements were produced than the subscriber could handle.
    - **Throttling**: Throttle first (`throttleFirst` in RxJava) drops any elements from the stream (after the first element emitted) for some given duration. Throttle last is very similar only emitting the last element emitted during the time period instead of the first. Reactor has similar methods, `sample` and `sampleFirst`. Akka Streams has a similar method named `throttle`.
    
    
### 1.3.d. Debounce 

    
<br/>

# 2. RxJava

## 2.1. Basics - Working with Publishers & Subscribers:

### 2.1.a. The Observable 

- `Observable.create()`
- `Observable.just()`

### 2.1.b. Observable Sources

- `Observable.range()` 
- `Observable.interval()`  
- `Observable.future()` 
- `Observable.empty()` 
- `Observable.never()` 
- `Observable.error()` 
- `Observable.defer()` 
- `Observable.fromCallable()`

### 2.1.c. Reactive Stream channels 

- Reactive Streams have 3 channels -
    - data channel: Channel through which data is sent
    - error channel: Channel through which an error maybe sent
    - complete channel: Channel through which a completed signal is sent 

- Once a completed signal is sent, no data will pass through the data channel.
- Once an error is generated, it will never pass a data through the data channel.

### 2.1.d. `Flowable` methods:

- `onNext()`: emits the next data in the stream
- `onComplete()`: signals that we are done, no more data will be sent
- `onError()`: signals that an error or exception occurred and no more data will be sent

### 2.1.e. Unsubscribe:

- `dispose()` when called on a `Disposable` sends a signal from the subscriber to the producer to stop emitting data.
- When multiple subscribers are around and one of them sends a `dispose()` signal, then the producer doesn't stop sending the data.   

### 2.1.f. Error Handling

- Handle errors using `onError()` and pass it on to the subscriber
- Support Resilience using `onErrorResumeNext()`

<br/> 

## 2.2. Basic Operators: 

### 2.2.a. Conditional

- `takeWhile`: Takes records until the condition is true inside the `takeWhile()` method.
- `skipWhile`: Skips records based on the condition passed inside `skipWhile()` method. 
- `defaultIfEmpty`
- `switchIfEmpty`

### 2.2.b. Suppressing

- `filter`
- `take`: Takes the number of records from the stream specified inside the `take()` method. 
- `skip`: Skips the number of records from the stream specified inside the `skip()` method.
- `distinct` 
- `elementAt`

> Actions Taken by `take`
> 1. It will stop data flow once that number of data is reached.
> 2. It sends a complete signal down stream
> 3. It sends a unsubscribe signal up stream

### 2.2.c. Transforming

- `map`
- `cast`
- `startWithItem`
- `sorted`
- `scan`

### 2.2.d. Reducing

- `count`
- `reduce`

### 2.2.e. Collection

- `toList`
- `toMap`
- `collect`

### 2.2.f. Error Recovery

- `onErrorReturnItem`
- `onErrorReturn`
- `onErrorResumeWith`
- `retry`

### 2.2.g. Action

- `doOnNext` & `doAfterNext`
- `doOnComplete` & `doOnError`
- `doOnEach`
- `doOnSubscribe`
- `doOnDispose`
- `doOnSuccess`
- `doFinally`

### 2.2.h. Utility

- `delay`
- `repeat`
- `single`
- `timestamp`
- `timeInterval`

<br/> 

## 2.3. Combining Observables: 

### 2.3.a. Merge

### 2.3.b. Concat 

### 2.3.c. Zip

### 2.3.d. Group

<br/>

## 2.4. Concurrency & Parallelism

### 2.4.a. Hot vs Cold observables

### 2.4.b. Threads

- Computation
- IO
- new Thread
- Single
- Trampoline
- ExecutorService

### 2.4.c. subscribeOn() vs observeOn()

## 2.5. Backpressure

### 2.5.a. Backpressure Strategies

- Buffering
- Windowing
- Throttling
- Switching 

<br/>

# 3. Reactor

<br/>

# 4. Akka Streams

<br/>

# 5. Smallrye Mutiny

<br/>

    
# References
- Reactive Streams in Java by Adam L. Davis
- Learning RxJava, Second Edition by Thomas Nield

