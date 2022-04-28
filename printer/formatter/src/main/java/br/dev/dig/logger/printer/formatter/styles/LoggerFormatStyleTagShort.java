package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

public final class LoggerFormatStyleTagShort implements LoggerFormatter.Style {
    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                return "V";
            case Logger.LEVEL_DEBUG:
                return "D";
            case Logger.LEVEL_INFO:
                return "I";
            case Logger.LEVEL_WARNING:
                return "W";
            case Logger.LEVEL_ERROR:
                return "E";
            case Logger.LEVEL_ASSERT:
                return "A";
        }
        return "";
    }
}
