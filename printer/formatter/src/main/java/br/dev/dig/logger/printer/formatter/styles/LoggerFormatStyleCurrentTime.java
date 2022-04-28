package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public class LoggerFormatStyleCurrentTime implements LoggerFormatter.Style {
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
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(LocalTime.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalTime time) {
        return ISO_LOCAL_TIME.format(Intrinsics.parameterNotNull(time, "LocalTime must not be null"));
    }
}
