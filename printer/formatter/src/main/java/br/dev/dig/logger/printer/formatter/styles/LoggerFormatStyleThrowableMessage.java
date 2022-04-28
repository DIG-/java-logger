package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public final class LoggerFormatStyleThrowableMessage implements LoggerFormatter.Style {
    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        if (t == null) {
            return "";
        }
        final String msg = t.getMessage();
        if (msg == null) {
            return t.getClass().getCanonicalName();
        }
        return t.getClass().getCanonicalName() + " - " + msg;
    }
}
