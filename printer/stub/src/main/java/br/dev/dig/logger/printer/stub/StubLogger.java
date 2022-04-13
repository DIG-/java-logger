package br.dev.dig.logger.printer.stub;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;

public final class StubLogger implements BaseLogger {

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
    }

}