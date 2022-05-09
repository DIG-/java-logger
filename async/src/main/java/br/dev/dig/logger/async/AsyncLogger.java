package br.dev.dig.logger.async;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.concurrent.Executor;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;

public final class AsyncLogger implements BaseLogger {

    @NotNull
    @VisibleForTesting
    final BaseLogger target;
    @NotNull
    @VisibleForTesting
    final Executor executor;

    public AsyncLogger(@NotNull final BaseLogger target, @NotNull final Executor executor) {
        this.target = Intrinsics.parameterNotNull(target, "Target BaseLogger must not be null");
        this.executor = Intrinsics.parameterNotNull(executor, "Executor must not be null");
    }

    @SuppressWarnings("unused")
    public static class Builder {
        @Nullable
        BaseLogger target;
        @Nullable
        Executor executor;

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

        @Nullable
        public Executor getExecutor() {
            return executor;
        }

        public Builder setExecutor(@NotNull final Executor executor) {
            this.executor = executor;
            return this;
        }

        @SuppressWarnings("ConstantConditions")
        public AsyncLogger build() {
            return new AsyncLogger(target, executor);
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        executor.execute(() -> target.log(level, tag, message, throwable));
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        executor.execute(() -> target.log(level, tag, message, throwable));
    }
}