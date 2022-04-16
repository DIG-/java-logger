package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import br.dev.dig.logger.printer.println.PrintlnFormatter;

abstract class PrintlnStyleUtil {
    private PrintlnStyleUtil() {
    }

    static final Random rand = new Random();

    @NotNull
    static String randomPrint(PrintlnFormatter.Style style) {
        return style.print(randInt(), randString(), LocalDateTime.now(), randString(), randThrowable());
    }

    static int randInt() {
        return rand.nextInt();
    }

    @NotNull
    static String randString() {
        return UUID.randomUUID().toString();
    }

    @Nullable
    static Throwable randThrowable() {
        return rand.nextFloat() > 0.5 ? null : new RuntimeException();
    }
}
