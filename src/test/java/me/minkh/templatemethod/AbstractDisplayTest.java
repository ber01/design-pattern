package me.minkh.templatemethod;

import org.junit.jupiter.api.Test;

class AbstractDisplayTest {

    @Test
    void test() {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("Hello, World!");
        AbstractDisplay d3 = new StringDisplay("MinKH");

        d1.display();
        d2.display();
        d3.display();
    }

}