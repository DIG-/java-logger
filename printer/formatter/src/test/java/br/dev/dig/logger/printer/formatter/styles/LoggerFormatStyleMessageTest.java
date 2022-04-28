package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LoggerFormatStyleMessageTest extends LoggerFormatStyleCommon<LoggerFormatStyleMessage> {

    @Override
    @NotNull
    LoggerFormatStyleMessage create() {
        return new LoggerFormatStyleMessage();
    }

    @Test
    void log_Null() {
        final String output = style.print(LocalDateTime.now(), randInt(), randString(), null, randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_Message() {
        final String message = randString();
        final String output = style.print(LocalDateTime.now(), randInt(), randString(), message, randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals(message, output);
    }
}