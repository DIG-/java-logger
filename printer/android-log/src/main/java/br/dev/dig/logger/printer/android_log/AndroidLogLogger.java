package br.dev.dig.logger.printer.android_log;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public final class AndroidLogLogger implements BaseLogger {

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence _message, @Nullable Throwable throwable) {
        final String message;
        if (_message == null) {
            message = "";
        } else {
            message = _message.toString();
        }
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                Log.v(tag, message, throwable);
                break;
            case Logger.LEVEL_DEBUG:
                Log.d(tag, message, throwable);
                break;
            case Logger.LEVEL_INFO:
                Log.i(tag, message, throwable);
                break;
            case Logger.LEVEL_WARNING:
                Log.w(tag, message, throwable);
                break;
            case Logger.LEVEL_ERROR:
                Log.e(tag, message, throwable);
                break;
            case Logger.LEVEL_ASSERT:
                Log.wtf(tag, message, throwable);
                break;
        }
    }
}