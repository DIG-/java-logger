package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleTag implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        final String result;
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                result = "VERBOSE";
                break;
            case Logger.LEVEL_DEBUG:
                result = "DEBUG";
                break;
            case Logger.LEVEL_INFO:
                result = "INFO";
                break;
            case Logger.LEVEL_WARNING:
                result = "WARNING";
                break;
            case Logger.LEVEL_ERROR:
                result = "ERROR";
                break;
            case Logger.LEVEL_ASSERT:
                result = "ASSERT";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
