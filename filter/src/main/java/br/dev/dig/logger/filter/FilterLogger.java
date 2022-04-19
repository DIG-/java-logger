package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;

public final class FilterLogger implements BaseLogger {
    @NotNull
    final BaseLogger parent;
    final int level;

    public FilterLogger(int minimum, @NotNull final BaseLogger parent) {
        super();
        this.level = minimum;
        this.parent = parent;
    }

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
        if (level >= this.level) {
            parent.log(level, tag, message, throwable);
        }
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        if (level >= this.level) {
            parent.log(level, tag, message, throwable);
        }
    }
}
