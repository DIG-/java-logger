package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

public class PrintlnStyleCurrentTime implements PrintlnFormatter.Style {
    static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, true)
            .toFormatter();

    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(LocalTime.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalTime time) {
        return ISO_LOCAL_TIME.format(time);
    }
}
