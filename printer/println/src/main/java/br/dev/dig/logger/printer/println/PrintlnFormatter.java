package br.dev.dig.logger.printer.println;

import org.jetbrains.annotations.NotNull;

import br.dev.dig.logger.printer.formatter.LoggerFormatter;

@Deprecated
// Use LoggerFormatter
public class PrintlnFormatter extends LoggerFormatter {
    public PrintlnFormatter(@NotNull Iterable<Style> styles) {
        super(styles);
    }
}
