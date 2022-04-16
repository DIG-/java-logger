package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PrintlnStyleMessageTest extends PrintlnStyleCommon<PrintlnStyleMessage> {

    @Override
    @NotNull PrintlnStyleMessage create() {
        return new PrintlnStyleMessage();
    }

    @Test
    void log_Null() {
        final String output = style.print(randInt(), randString(), LocalDateTime.now(), null, randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_Message() {
        final String message = randString();
        final String output = style.print(randInt(), randString(), LocalDateTime.now(), message, randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals(message, output);
    }
}