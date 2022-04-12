package br.dev.dig.logger.printer.timber;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.Logger;
import timber.log.Timber;

public final class TimberLogger extends Logger {

    @Nullable
    final String tag;

    public TimberLogger(@Nullable final String tag) {
        super();
        this.tag = tag;
    }

    @NotNull
    public static TimberLogger create(@Nullable final String tag) {
        return new TimberLogger(tag);
    }

    @Override
    protected @Nullable String getTag() {
        return tag;
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
            case LEVEL_VERBOSE:
                getTimber().v(throwable, message);
                break;
            case LEVEL_DEBUG:
                getTimber().d(throwable, message);
                break;
            case LEVEL_INFO:
                getTimber().i(throwable, message);
                break;
            case LEVEL_WARNING:
                getTimber().w(throwable, message);
                break;
            case LEVEL_ERROR:
                getTimber().e(throwable, message);
                break;
            case LEVEL_ASSERT:
                getTimber().wtf(throwable, message);
                break;
        }
    }

    private Timber.Tree getTimber() {
        if (tag == null) {
            return Timber.asTree();
        }
        return Timber.tag(tag);
    }

}
