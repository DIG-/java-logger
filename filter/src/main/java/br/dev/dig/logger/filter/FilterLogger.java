package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;

public class FilterLogger implements BaseLogger {
    @NotNull
    final BaseLogger parent;
    final int level;

    public FilterLogger(int minimum, @NotNull final BaseLogger parent) {
        super();
        this.level = minimum;
        this.parent = parent;
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        if (level >= this.level) {
            parent.log(level, tag, message, throwable);
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        if (level >= this.level) {
            parent.log(level, tag, message, throwable);
        }
    }
}
