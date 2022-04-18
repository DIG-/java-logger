package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleElapsedTime implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        return format(start, LocalDateTime.now());
    }

    @NotNull
    @VisibleForTesting
    String format(@NotNull final LocalDateTime start, @NotNull final LocalDateTime current) {
        final Duration diff = Duration.between(start, current);
        final LocalDateTime time = LocalDateTime.ofEpochSecond(diff.getSeconds(), diff.getNano(), ZoneOffset.UTC);
        // TODO: Check how 24h+ will be showed
        return PrintlnStyleCurrentTime.ISO_LOCAL_TIME.format(time);
    }
}
