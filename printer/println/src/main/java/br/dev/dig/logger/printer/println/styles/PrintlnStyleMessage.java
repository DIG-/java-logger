package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

@Deprecated
// Use LoggerFormatter
public final class PrintlnStyleMessage implements PrintlnFormatter.Style {
    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        if (message == null) {
            return "";
        }
        return message.toString();
    }
}
