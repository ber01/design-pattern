package me.minkh.adapter.v1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AnimalTest {

    @Test
    void test() {
        List<Animal> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new Cat());
        list.add(new FishAdapter(new Fish()));
        for (Animal animal : list) {
            makeWalk(animal);
        }
    }

    private void makeWalk(Animal animal) {
        animal.walk();
    }

}