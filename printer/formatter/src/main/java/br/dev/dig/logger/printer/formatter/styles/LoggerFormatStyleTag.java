package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public final class LoggerFormatStyleTag implements LoggerFormatter.Style {
    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                return "VERBOSE";
            case Logger.LEVEL_DEBUG:
                return "DEBUG";
            case Logger.LEVEL_INFO:
                return "INFO";
            case Logger.LEVEL_WARNING:
                return "WARNING";
            case Logger.LEVEL_ERROR:
                return "ERROR";
            case Logger.LEVEL_ASSERT:
                return "ASSERT";
        }
        return "";
    }
}
