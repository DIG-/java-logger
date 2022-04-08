package br.dev.dig.logger.printer.android_log;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.Logger;

public class AndroidLogLogger extends Logger {

    @NotNull
    final String tag;

    public AndroidLogLogger(@Nullable final String tag) {
        super();
        if (tag == null) {
            this.tag = "";
        } else {
            this.tag = tag;
        }
    }

    @Override
    public void log(int level, @Nullable CharSequence _message, @Nullable Throwable t) {
        final String message;
        if (_message == null) {
            message = "";
        } else {
            message = _message.toString();
        }
        switch (level) {
            case LEVEL_VERBOSE:
                Log.v(tag, message, t);
                break;
            case LEVEL_DEBUG:
                Log.d(tag, message, t);
                break;
            case LEVEL_INFO:
                Log.i(tag, message, t);
                break;
            case LEVEL_WARNING:
                Log.w(tag, message, t);
                break;
            case LEVEL_ERROR:
                Log.e(tag, message, t);
                break;
            case LEVEL_ASSERT:
                Log.wtf(tag, message, t);
                break;
        }
    }
}