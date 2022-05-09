package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public final class LoggerFormatStyleElapsedTime implements LoggerFormatter.Style {
    static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
        .appendLiteral(':')
        .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
        .appendLiteral(':')
        .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
        .optionalStart()
        .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, true)
        .toFormatter();


    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(start, LocalDateTime.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalDateTime start, @NotNull final LocalDateTime current) {
        final Duration diff = Duration.between(Intrinsics.parameterNotNull(start, "Start LocalDateTime must not be null"), Intrinsics.parameterNotNull(current, "Current LocalDateTime must not be null"));
        final LocalDateTime time = LocalDateTime.ofEpochSecond(diff.getSeconds(), diff.getNano(), ZoneOffset.UTC);
        return String.format(Locale.US, "%02d%s", diff.getSeconds() / (60 * 60), ISO_LOCAL_TIME.format(time));
    }
}
