package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleTagShort implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        final String result;
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                result = "V";
                break;
            case Logger.LEVEL_DEBUG:
                result = "D";
                break;
            case Logger.LEVEL_INFO:
                result = "I";
                break;
            case Logger.LEVEL_WARNING:
                result = "W";
                break;
            case Logger.LEVEL_ERROR:
                result = "E";
                break;
            case Logger.LEVEL_ASSERT:
                result = "A";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
