package me.minkh.factory.v1;

import org.junit.jupiter.api.Test;

class FactoryTest {

    @Test
    void test() {
        Mechanic goliath = new Goliath();
        Mechanic tank = new Tank();
        Mechanic vulture = new Vulture();
    }

    @Test
    void test2() {
        Mechanic goliath = Factory.createMechanic(Unit.GOLIATH);
        Mechanic tank = Factory.createMechanic(Unit.TANK);
        Mechanic vulture = Factory.createMechanic(Unit.VULTURE);
    }

}