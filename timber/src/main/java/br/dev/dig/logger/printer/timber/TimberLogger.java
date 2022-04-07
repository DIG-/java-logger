package br.dev.dig.logger.printer.timber;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    public void log(int level, @Nullable final CharSequence _message, @Nullable final Throwable t) {
        final String message;
        if (_message == null) {
            message = null;
        } else {
            message = _message.toString();
        }
        switch (level) {
            case LEVEL_VERBOSE:
                getTimber().v(t, message);
                break;
            case LEVEL_DEBUG:
                getTimber().d(t, message);
                break;
            case LEVEL_INFO:
                getTimber().i(t, message);
                break;
            case LEVEL_WARNING:
                getTimber().w(t, message);
                break;
            case LEVEL_ERROR:
                getTimber().e(t, message);
                break;
            case LEVEL_ASSERT:
                getTimber().wtf(t, message);
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
