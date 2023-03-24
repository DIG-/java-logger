package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;
import br.dev.dig.logger.intrinsics.Intrinsics;

final class FilterLoggerByLevel implements BaseLogger {

    @NotNull
    final BaseLogger target;
    final int level;

    FilterLoggerByLevel(int minimum, @NotNull final BaseLogger target) {
        if (minimum < Logger.LEVEL_VERBOSE || minimum > Logger.LEVEL_NONE) {
            throw new InvalidParameterException("Minimum must be between `Logger.LEVEL_VERBOSE` and `Logger.LEVEL_NONE`.");
        }
        this.level = minimum;
        this.target = Intrinsics.parameterNotNull(target, "Target BaseLogger must not be null");
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