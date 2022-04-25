package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public final class FilterLogger implements BaseLogger {
    @NotNull
    final BaseLogger parent;
    final int level;

    @SuppressWarnings("ConstantConditions")
    public FilterLogger(int minimum, @NotNull final BaseLogger parent) {
        if (minimum < Logger.LEVEL_VERBOSE || minimum > Logger.LEVEL_NONE) {
            throw new InvalidParameterException("Minimum must be between `Logger.LEVEL_VERBOSE` and `Logger.LEVEL_NONE`.");
        }
        if (parent == null) {
            throw new InvalidParameterException("Parent BaseLogger must not be null");
        }
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
