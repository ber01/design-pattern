package me.minkh.adapter.v4;

import org.junit.jupiter.api.Test;

class PrintTest {

    @Test
    void test() {
        Print banner = new PrintBanner(new Banner("Hello World!"));
        banner.printWeak();
        banner.printStrong();
    }

}