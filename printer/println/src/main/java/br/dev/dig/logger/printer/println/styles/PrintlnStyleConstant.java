package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

public final class PrintlnStyleConstant implements PrintlnFormatter.Style {
    @NotNull
    private final String value;

    @SuppressWarnings("ConstantConditions")
    public PrintlnStyleConstant(@NotNull final String value) {
        if (value == null) {
            throw new InvalidParameterException("Value must not be null");
        }
        this.value = value;
    }

    @Override
    public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
        return value;
    }
}
