package com.arunav.learning.rxjava.labs.lab2.dronelocator;

import drone.DroneLocation;
import drone.DroneLocator;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.Scanner;

public class ObservingDroneLocations {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Type \"end\" and hit the return key to stop..");
        Observable<DroneLocation> droneLoc = DroneLocator.fetch("DR01");

        droneLoc.take(25)
                .subscribe(System.out::println, System.out::println, ObservingDroneLocations::droneLanded);

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("end")) {
        }
    }

    private static void droneLanded() {
        System.out.println("The drone has landed");
        System.exit(0);
    }
}
