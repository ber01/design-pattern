package me.minkh.adapter.v2;

import org.junit.jupiter.api.Test;

class TurkeyAdapterTest {

    @Test
    void test() {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.fly();

        Turkey wildTurkey = new WildTurkey();
        wildTurkey.gobble();
        wildTurkey.fly();

        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(wildTurkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }

}