package me.minkh.adapter.v5;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FileIOTest {

    @Test
    void test() {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("file.txt");
            f.setValue("year", "2023");
            f.setValue("month", "12");
            f.setValue("day", "25");
            f.writeToFile("new_file.txt");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}