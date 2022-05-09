package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;

class LoggerFormatStyleTagTest extends LoggerFormatStyleCommon<LoggerFormatStyleTag> {

    @Override
    @NotNull
    LoggerFormatStyleTag create() {
        return new LoggerFormatStyleTag();
    }

    @Override
    void log_NotNullOrEmpty() {
        // It can be empty since use of randInt()
    }

    @Test
    void log_Invalid() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_NONE, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("", output);
    }

    @Test
    void log_Verbose() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_VERBOSE, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("VERBOSE", output);
    }

    @Test
    void log_Debug() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_DEBUG, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("DEBUG", output);
    }

    @Test
    void log_Info() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_INFO, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("INFO", output);
    }

    @Test
    void log_Warning() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_WARNING, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("WARNING", output);
    }

    @Test
    void log_Error() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_ERROR, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("ERROR", output);
    }

    @Test
    void log_Wtf() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_ASSERT, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("ASSERT", output);
    }

}