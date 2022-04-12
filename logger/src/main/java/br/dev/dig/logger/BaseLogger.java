package br.dev.dig.logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BaseLogger {
    @FunctionalInterface
    interface Message {
        @NotNull CharSequence generate();
    }

    void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable);

    void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable);
}