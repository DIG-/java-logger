package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;

class PrintlnStyleTagTest extends PrintlnStyleCommon<PrintlnStyleTag> {

    @Override
    @NotNull
    PrintlnStyleTag create() {
        return new PrintlnStyleTag();
    }

    @Override
    void log_NotNullOrEmpty() {
        // It can be empty since use of randInt()
    }

    @Test
    void log_Invalid() {
        final String output = style.print(Logger.LEVEL_NONE, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_Verbose() {
        final String output = style.print(Logger.LEVEL_VERBOSE, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("VERBOSE", output);
    }

    @Test
    void log_Debug() {
        final String output = style.print(Logger.LEVEL_DEBUG, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("DEBUG", output);
    }

    @Test
    void log_Info() {
        final String output = style.print(Logger.LEVEL_INFO, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("INFO", output);
    }

    @Test
    void log_Warning() {
        final String output = style.print(Logger.LEVEL_WARNING, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("WARNING", output);
    }

    @Test
    void log_Error() {
        final String output = style.print(Logger.LEVEL_ERROR, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("ERROR", output);
    }

    @Test
    void log_Wtf() {
        final String output = style.print(Logger.LEVEL_ASSERT, randString(), LocalDateTime.now(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("ASSERT", output);
    }

}