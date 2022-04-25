package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleTagShort implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
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
