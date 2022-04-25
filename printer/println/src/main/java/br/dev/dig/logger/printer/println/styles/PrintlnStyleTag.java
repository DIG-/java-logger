package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleTag implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
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
