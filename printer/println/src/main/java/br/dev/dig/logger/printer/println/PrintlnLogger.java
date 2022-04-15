package br.dev.dig.logger.printer.println;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDateTime;

import br.dev.dig.logger.BaseLogger;


public final class PrintlnLogger implements BaseLogger {

    @NotNull
    @VisibleForTesting
    final PrintlnFormatter formatter;
    @NotNull
    @VisibleForTesting
    final LocalDateTime start = LocalDateTime.now();

    @SuppressWarnings("unused")
    public PrintlnLogger(@NotNull final PrintlnFormatter formatter) {
        super();
        this.formatter = formatter;
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
        System.out.println(message);
    }

}