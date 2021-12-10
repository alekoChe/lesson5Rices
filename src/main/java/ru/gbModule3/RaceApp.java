package ru.gbModule3;

import java.util.concurrent.CyclicBarrier;

/**
 * Hello world!
 *
 */
public class RaceApp {
    public static final int CARS_COUNT = 4;

    public static void main( String[] args )
    {
        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT); // 1
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier);
        }
        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            barrier.await();
            //barrier.wait();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            //barrier.notifyAll();
            barrier.await();
            //barrier.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        //barrier.notifyAll();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
