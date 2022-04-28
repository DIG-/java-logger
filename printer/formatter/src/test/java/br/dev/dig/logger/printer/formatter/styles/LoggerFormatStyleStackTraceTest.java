package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LoggerFormatStyleStackTraceTest extends LoggerFormatStyleCommon<LoggerFormatStyleStackTrace> {

    @Override
    @NotNull
    LoggerFormatStyleStackTrace create() {
        return new LoggerFormatStyleStackTrace();
    }

    @Override
    void log_NotNullOrEmpty() {
        // Handled next tests
    }

    @Test
    void log_Null() {
        final String output = style.print(LocalDateTime.now(), randInt(), randString(), randString(), null);
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_StackTrace() {
        final String message = randString();
        final Throwable throwable = new RuntimeException(message);
        final String output = style.print(LocalDateTime.now(), randInt(), randString(), randString(), throwable);
        Assertions.assertNotNull(output);
        Assertions.assertTrue(output.contains("RuntimeException"));
        Assertions.assertTrue(output.contains(message));
    }

}