package br.dev.dig.logger.printer.android_log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public final class AndroidLogLogger implements BaseLogger {

    private final LogWrapper wrapper;

    protected AndroidLogLogger(@NotNull final LogWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public AndroidLogLogger() {
        this(new LogWrapper());
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence _message, @Nullable Throwable throwable) {
        final CharSequence message;
        if (_message == null) {
            message = "";
        } else {
            message = _message;
        }
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                wrapper.v(tag, message, throwable);
                break;
            case Logger.LEVEL_DEBUG:
                wrapper.d(tag, message, throwable);
                break;
            case Logger.LEVEL_INFO:
                wrapper.i(tag, message, throwable);
                break;
            case Logger.LEVEL_WARNING:
                wrapper.w(tag, message, throwable);
                break;
            case Logger.LEVEL_ERROR:
                wrapper.e(tag, message, throwable);
                break;
            case Logger.LEVEL_ASSERT:
                wrapper.wtf(tag, message, throwable);
                break;
        }
    }

}