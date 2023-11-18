package com.lojaJogos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Rotinas uteis para a realizacao dos testes
 */
public class TestsUtil {
    public static InputStream provideInput(String data) {
        InputStream in = new ByteArrayInputStream(data.getBytes());
        return in;
    }

    public static ByteArrayOutputStream listenToOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
        return outContent;
    }

    public static void suppressOutput() {
        PrintStream dummyStream = new PrintStream(new OutputStream(){
            public void write(int b) {
                // NO-OP
            }
        });
        System.setOut(dummyStream);
    }
}