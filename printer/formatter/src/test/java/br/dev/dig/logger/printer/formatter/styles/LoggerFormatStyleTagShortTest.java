package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;

class LoggerFormatStyleTagShortTest extends LoggerFormatStyleCommon<LoggerFormatStyleTagShort> {

    @Override
    @NotNull
    LoggerFormatStyleTagShort create() {
        return new LoggerFormatStyleTagShort();
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
        Assertions.assertEquals("V", output);
    }

    @Test
    void log_Debug() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_DEBUG, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("D", output);
    }

    @Test
    void log_Info() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_INFO, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("I", output);
    }

    @Test
    void log_Warning() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_WARNING, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("W", output);
    }

    @Test
    void log_Error() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_ERROR, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("E", output);
    }

    @Test
    void log_Wtf() {
        final String output = style.print(LocalDateTime.now(), Logger.LEVEL_ASSERT, randString(), randString(), randThrowable());
        Assertions.assertNotNull(output);
        Assertions.assertEquals("A", output);
    }

}