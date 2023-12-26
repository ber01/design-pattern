package me.minkh.proxy.v1;

import me.minkh.proxy.v1.Printable;
import me.minkh.proxy.v1.PrinterProxy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrinterProxyTest {

    @Test
    void test() {
        Printable p = new PrinterProxy("Alice");
        assertThat(p.getPrinterName()).isEqualTo("Alice");

        p.setPrinterName("Bob");
        assertThat(p.getPrinterName()).isEqualTo("Bob");

        p.print("Hello World!");
    }

}