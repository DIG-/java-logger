package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleElapsedTime implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        final Duration diff = Duration.between(start, LocalDateTime.now());
        final LocalDateTime local = LocalDateTime.ofEpochSecond(diff.getSeconds(), diff.getNano(), ZoneOffset.UTC);
        // TODO: Check how 24h+ will be showed
        return PrintlnStyleCurrentTime.ISO_LOCAL_TIME.format(local);
    }
}
