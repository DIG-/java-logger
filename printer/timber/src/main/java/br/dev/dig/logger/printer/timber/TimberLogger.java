package br.dev.dig.logger.printer.timber;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;
import timber.log.Timber;

public class TimberLogger implements BaseLogger {

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    @SuppressLint("TimberExceptionLogging")
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence _message, @Nullable final Throwable throwable) {
        final String message;
        if (_message == null) {
            message = null;
        } else {
            message = _message.toString();
        }
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                getTimber(tag).v(throwable, message);
                break;
            case Logger.LEVEL_DEBUG:
                getTimber(tag).d(throwable, message);
                break;
            case Logger.LEVEL_INFO:
                getTimber(tag).i(throwable, message);
                break;
            case Logger.LEVEL_WARNING:
                getTimber(tag).w(throwable, message);
                break;
            case Logger.LEVEL_ERROR:
                getTimber(tag).e(throwable, message);
                break;
            case Logger.LEVEL_ASSERT:
                getTimber(tag).wtf(throwable, message);
                break;
        }
    }

    Timber.Tree getTimber(String tag) {
        if (tag == null) {
            return Timber.asTree();
        }
        return Timber.tag(tag);
    }

}
