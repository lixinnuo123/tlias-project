package org.example;

public class ThrreadLocalTest {

    public static ThreadLocal<Integer> local = new ThreadLocal<>();
    public static void main(String[] args) {

        local.set(1);

        new Thread(()-> {
            local.set(2);
                System.out.println(Thread.currentThread().getName() + ":" +local.get());
            }).start();

        System.out.println(Thread.currentThread().getName() + ":" +local.get());

        local.remove();

        System.out.println(Thread.currentThread().getName() + ":" +local.get());
    }
}
