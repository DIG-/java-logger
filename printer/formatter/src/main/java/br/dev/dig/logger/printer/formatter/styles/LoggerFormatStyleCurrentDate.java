package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public final class LoggerFormatStyleCurrentDate implements LoggerFormatter.Style {
    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(LocalDate.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalDate date) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(Intrinsics.parameterNotNull(date, "LocalDate must not be null"));
    }
}
