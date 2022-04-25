package br.dev.dig.logger.async;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.security.InvalidParameterException;
import java.util.concurrent.Executor;

import br.dev.dig.logger.BaseLogger;

public final class AsyncLogger implements BaseLogger {

    @NotNull
    @VisibleForTesting
    final BaseLogger target;
    @NotNull
    @VisibleForTesting
    final Executor executor;

    @SuppressWarnings("ConstantConditions")
    public AsyncLogger(@NotNull final BaseLogger target, @NotNull final Executor executor) {
        if (target == null) {
            throw new InvalidParameterException("Target BaseLogger must not be null");
        }
        if (executor == null) {
            throw new InvalidParameterException("Executor must not be null");
        }
        this.target = target;
        this.executor = executor;
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