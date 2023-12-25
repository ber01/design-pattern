package me.minkh.adapter.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {

    @Test
    void test() {
        Print print = new PrintBanner("Hello");
        print.printWeak();
        print.printStrong();
    }

}