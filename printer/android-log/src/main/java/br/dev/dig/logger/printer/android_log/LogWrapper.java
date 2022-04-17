package br.dev.dig.logger.printer.android_log;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class LogWrapper {
    void v(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.v(tag, msg.toString(), tr);
    }

    void d(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.d(tag, msg.toString(), tr);
    }

    void i(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.i(tag, msg.toString(), tr);
    }

    void w(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.w(tag, msg.toString(), tr);
    }

    void e(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.e(tag, msg.toString(), tr);
    }

    void wtf(@Nullable final String tag, @NotNull final CharSequence msg, @Nullable final Throwable tr) {
        Log.wtf(tag, msg.toString(), tr);
    }
}
