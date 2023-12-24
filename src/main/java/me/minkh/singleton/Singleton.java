package me.minkh.singleton;

public class Singleton {

    private static final Singleton singleton = new Singleton();

    private Singleton() {
        System.out.println("instance init");
    }

    public static Singleton getInstance() {
        return singleton;
    }

}
