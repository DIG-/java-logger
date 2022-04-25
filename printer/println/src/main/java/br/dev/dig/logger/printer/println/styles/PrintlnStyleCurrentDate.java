package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleCurrentDate implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(LocalDate.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalDate date) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(Intrinsics.parameterNotNull(date, "LocalDate must not be null"));
    }
}
