package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public final class FilterLogger implements BaseLogger {
    @NotNull
    final BaseLogger target;
    final int level;

    @SuppressWarnings("ConstantConditions")
    public FilterLogger(int minimum, @NotNull final BaseLogger target) {
        if (minimum < Logger.LEVEL_VERBOSE || minimum > Logger.LEVEL_NONE) {
            throw new InvalidParameterException("Minimum must be between `Logger.LEVEL_VERBOSE` and `Logger.LEVEL_NONE`.");
        }
        if (target == null) {
            throw new InvalidParameterException("Target BaseLogger must not be null");
        }
        this.level = minimum;
        this.target = target;
    }

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
        if (level >= this.level) {
            target.log(level, tag, message, throwable);
        }
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        if (level >= this.level) {
            target.log(level, tag, message, throwable);
        }
    }
}
