package br.dev.dig.logger.printer.android_log;

import org.junit.jupiter.api.Test;

class LogWrapperTest_Dummy {

    @Test
    void v() {
        (new LogWrapper()).v("", "", null);
    }

    @Test
    void d() {
        (new LogWrapper()).d("", "", null);
    }

    @Test
    void i() {
        (new LogWrapper()).i("", "", null);
    }

    @Test
    void w() {
        (new LogWrapper()).w("", "", null);
    }

    @Test
    void e() {
        (new LogWrapper()).e("", "", null);
    }

    @Test
    void wtf() {
        (new LogWrapper()).wtf("", "", null);
    }
}