package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

abstract class PrintlnStyleCommon<S extends PrintlnFormatter.Style> {

    static final Random rand = new Random();

    @NotNull
    final S style = create();

    @Test
    void log_NotNullOrEmpty() {
        final String output = randomPrint(style);
        Assertions.assertNotNull(output);
        Assertions.assertFalse(output.isEmpty());
    }

    @NotNull
    String randomPrint(PrintlnFormatter.Style style) {
        return style.print(randInt(), randString(), LocalDateTime.now(), randString(), randThrowable());
    }

    int randInt() {
        return rand.nextInt();
    }

    @NotNull
    String randString() {
        return UUID.randomUUID().toString();
    }

    @Nullable
    Throwable randThrowable() {
        return rand.nextFloat() > 0.5 ? null : new RuntimeException();
    }

    @NotNull
    abstract S create();

}
