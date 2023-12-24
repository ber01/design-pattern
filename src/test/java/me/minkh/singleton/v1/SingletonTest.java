package me.minkh.singleton.v1;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {

    @Test
    void test() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        Set<Singleton> set = ConcurrentHashMap.newKeySet();
        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                set.add(Singleton.getInstance());
            });
        }

        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) break;
        }

        assertThat(set.size()).isEqualTo(1);
    }

}