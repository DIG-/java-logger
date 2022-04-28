package br.dev.dig.logger.printer.println;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDateTime;
import java.util.Arrays;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleConstant;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleElapsedTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleMessage;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTagShort;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleThrowableMessage;


public final class PrintlnLogger implements BaseLogger {

    @NotNull
    @VisibleForTesting
    LoggerFormatter formatter;
    @NotNull
    @VisibleForTesting
    final LocalDateTime start = LocalDateTime.now();

    public static abstract class Formatter {
        public static LoggerFormatter simple() {
            return new LoggerFormatter(Arrays.asList(
                new LoggerFormatStyleElapsedTime(),
                new LoggerFormatStyleConstant(" ["),
                new LoggerFormatStyleTagShort(),
                new LoggerFormatStyleConstant("] "),
                new LoggerFormatStyleMessage(),
                new LoggerFormatStyleThrowableMessage()
            ));
        }
    }

    public PrintlnLogger(@NotNull final LoggerFormatter formatter) {
        this.formatter = Intrinsics.parameterNotNull(formatter, "Formatter must not be null");
    }

    @SuppressWarnings("unused")
    public PrintlnLogger(@NotNull final String format) {
        this(LoggerFormatter.parse(format));
    }

    @SuppressWarnings("unused")
    public PrintlnLogger() {
        this(Formatter.simple());
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