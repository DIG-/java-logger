package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;

public final class FilterLogger {
    private FilterLogger() {
        throw new RuntimeException("Can not instantiate this class");
    }

    @SuppressWarnings("unused")
    public static class Builder {
        @Nullable
        BaseLogger target;
        @Nullable
        Integer level = null;

        public Builder() {
        }

        public Builder(@NotNull final BaseLogger target) {
            this.target = target;
        }

        @Nullable
        public BaseLogger getTarget() {
            return target;
        }

        @NotNull
        public Builder setTarget(@NotNull final BaseLogger target) {
            this.target = target;
            return this;
        }

        @Nullable
        public Integer getLevel() {
            return level;
        }

        @NotNull
        public Builder setLevel(final int level) {
            this.level = level;
            return this;
        }

        @NotNull
        public BaseLogger build() {
            BaseLogger previous = Intrinsics.parameterNotNull(target, "Can not build without target BaseLogger");
            if (level != null) {
                previous = new FilterLoggerByLevel(level, previous);
            }
            return previous;
        }
    }

}