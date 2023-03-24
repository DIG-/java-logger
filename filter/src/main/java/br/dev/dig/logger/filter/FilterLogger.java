package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        @Nullable
        Set<Class<? extends Throwable>> allowed;
        @Nullable
        Set<Class<? extends Throwable>> blocked;

        public Builder() {
        }

        public Builder(@NotNull final BaseLogger target) {
            this.target = target;
        }

        @Nullable
        public final BaseLogger getTarget() {
            return target;
        }

        @NotNull
        public final Builder setTarget(@NotNull final BaseLogger target) {
            this.target = target;
            return this;
        }

        @Nullable
        public final Integer getLevel() {
            return level;
        }

        @NotNull
        public final Builder setLevel(final int level) {
            this.level = level;
            return this;
        }

        @Nullable
        public final Set<Class<? extends Throwable>> getAllowed() {
            return allowed;
        }

        @NotNull
        public final Builder setAllowed(@Nullable final Collection<Class<? extends Throwable>> allowed) {
            if (allowed == null) {
                this.allowed = null;
            } else {
                this.allowed = new HashSet<>(allowed);
            }
            return this;
        }

        @SafeVarargs
        public final Builder setAllowed(@NotNull final Class<? extends Throwable>... allowed) {
            this.allowed = new HashSet<>();
            Collections.addAll(this.allowed, allowed);
            return this;
        }

        @SafeVarargs
        public final Builder addAllowed(@NotNull final Class<? extends Throwable>... allowed) {
            if (this.allowed == null) {
                this.allowed = new HashSet<>();
            }
            Collections.addAll(this.allowed, allowed);
            return this;
        }

        @Nullable
        public final Set<Class<? extends Throwable>> getBlocked() {
            return blocked;
        }


        @NotNull
        public final Builder setBlocked(@Nullable final Collection<Class<? extends Throwable>> blocked) {
            if (blocked == null) {
                this.blocked = null;
            } else {
                this.blocked = new HashSet<>(blocked);
            }
            return this;
        }

        @SafeVarargs
        public final Builder setBlocked(@NotNull final Class<? extends Throwable>... blocked) {
            this.blocked = new HashSet<>();
            Collections.addAll(this.blocked, blocked);
            return this;
        }

        @SafeVarargs
        public final Builder addBlocked(@NotNull final Class<? extends Throwable>... blocked) {
            if (this.blocked == null) {
                this.blocked = new HashSet<>();
            }
            Collections.addAll(this.blocked, blocked);
            return this;
        }

        @NotNull
        public final BaseLogger build() {
            BaseLogger previous = Intrinsics.parameterNotNull(target, "Can not build without target BaseLogger");
            if (allowed != null) {
                previous = new FilterLoggerByAllowedThrowable(previous, allowed);
            }
            if (blocked != null) {
                previous = new FilterLoggerByBlockedThrowable(previous, blocked);
            }
            if (level != null) {
                previous = new FilterLoggerByLevel(level, previous);
            }
            return previous;
        }
    }

}