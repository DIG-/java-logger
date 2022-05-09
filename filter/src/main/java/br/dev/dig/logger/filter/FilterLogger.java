package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;
import br.dev.dig.logger.intrinsics.Intrinsics;

public final class FilterLogger implements BaseLogger {
    @NotNull
    final BaseLogger target;
    final int level;

    public FilterLogger(int minimum, @NotNull final BaseLogger target) {
        if (minimum < Logger.LEVEL_VERBOSE || minimum > Logger.LEVEL_NONE) {
            throw new InvalidParameterException("Minimum must be between `Logger.LEVEL_VERBOSE` and `Logger.LEVEL_NONE`.");
        }
        this.level = minimum;
        this.target = Intrinsics.parameterNotNull(target, "Target BaseLogger must not be null");
    }

    @SuppressWarnings("unused")
    public static class Builder {
        @Nullable
        BaseLogger target;
        int level = Logger.LEVEL_NONE;

        public Builder() {
        }

        public Builder(@NotNull final BaseLogger target) {
            this.target = target;
        }

        @Nullable
        public BaseLogger getTarget() {
            return target;
        }

        public Builder setTarget(@NotNull final BaseLogger target) {
            this.target = target;
            return this;
        }

        public int getLevel() {
            return level;
        }

        public Builder setLevel(final int level) {
            this.level = level;
            return this;
        }

        @SuppressWarnings("ConstantConditions")
        public FilterLogger build() {
            return new FilterLogger(level, target);
        }
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
