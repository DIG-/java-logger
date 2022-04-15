package br.dev.dig.logger.printer.android_log;

import android.util.Log;

class LogWrapper {
    void v(String tag, CharSequence msg, Throwable tr) {
        Log.v(tag, msg.toString(), tr);
    }

    void d(String tag, CharSequence msg, Throwable tr) {
        Log.d(tag, msg.toString(), tr);
    }

    void i(String tag, CharSequence msg, Throwable tr) {
        Log.i(tag, msg.toString(), tr);
    }

    void w(String tag, CharSequence msg, Throwable tr) {
        Log.w(tag, msg.toString(), tr);
    }

    void e(String tag, CharSequence msg, Throwable tr) {
        Log.e(tag, msg.toString(), tr);
    }

    void wtf(String tag, CharSequence msg, Throwable tr) {
        Log.wtf(tag, msg.toString(), tr);
    }
}
