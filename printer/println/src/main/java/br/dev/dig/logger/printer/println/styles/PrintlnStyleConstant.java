package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.println.PrintlnFormatter;

@Deprecated
// Use LoggerFormatter
public final class PrintlnStyleConstant implements PrintlnFormatter.Style {
    @NotNull
    private final String value;

    public PrintlnStyleConstant(@NotNull final String value) {
        this.value = Intrinsics.parameterNotNull(value, "Value must not be null");
    }

    @Override
    public @NotNull String print(@NotNull LocalDateTime start, int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable t) {
        return value;
    }
}
