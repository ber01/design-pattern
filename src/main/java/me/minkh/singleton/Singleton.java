package me.minkh.singleton;

public class Singleton {

    private static Singleton singleton;

    private Singleton() {
        System.out.println("instance init");
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

}
