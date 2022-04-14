package br.dev.dig.logger.async;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;

import br.dev.dig.logger.BaseLogger;

public final class AsyncLogger implements BaseLogger {

    @NotNull
    private final BaseLogger target;
    @NotNull
    private final Executor executor;

    public AsyncLogger(@NotNull final BaseLogger target, @NotNull final Executor executor) {
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