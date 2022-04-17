package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PrintlnStyleStackTraceTest extends PrintlnStyleCommon<PrintlnStyleStackTrace> {

    @Override
    @NotNull
    PrintlnStyleStackTrace create() {
        return new PrintlnStyleStackTrace();
    }

    @Override
    void log_NotNullOrEmpty() {
        // Handled next tests
    }

    @Test
    void log_Null() {
        final String output = style.print(randInt(), randString(), LocalDateTime.now(), randString(), null);
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_StackTrace() {
        final String message = randString();
        final Throwable throwable = new RuntimeException(message);
        final String output = style.print(randInt(), randString(), LocalDateTime.now(), randString(), throwable);
        Assertions.assertNotNull(output);
        Assertions.assertTrue(output.contains("RuntimeException"));
        Assertions.assertTrue(output.contains(message));
    }

}