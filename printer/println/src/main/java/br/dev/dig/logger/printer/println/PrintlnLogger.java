package br.dev.dig.logger.printer.println;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDateTime;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;


public final class PrintlnLogger implements BaseLogger {

    @NotNull
    @VisibleForTesting
    final PrintlnFormatter formatter;
    @NotNull
    @VisibleForTesting
    final LocalDateTime start = LocalDateTime.now();

    public PrintlnLogger(@NotNull final PrintlnFormatter formatter) {
        this.formatter = Intrinsics.parameterNotNull(formatter, "Formatter must not be null");
    }

    @SuppressWarnings("unused")
    public PrintlnLogger(@NotNull final String format) {
        this(PrintlnFormatter.parse(format));
    }

    @SuppressWarnings("unused")
    public PrintlnLogger() {
        this(PrintlnFormatter.simple());
    }

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        print(formatter.format(start, level, tag, message, throwable));
    }

    private void print(@NotNull CharSequence message) {
        System.out.println(Intrinsics.parameterNotNull(message, "Message must not be null"));
    }

}