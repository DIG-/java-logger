package br.dev.dig.logger.printer.println;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

@ExtendWith(MockitoExtension.class)
class PrintlnLoggerTest {

    @NotNull
    private LocalDateTime start;
    private final String tag = UUID.randomUUID().toString();
    private final String content = UUID.randomUUID().toString();
    private final Logger.Message message = () -> content;
    private final Throwable throwable = new RuntimeException();

    @Mock
    private PrintlnFormatter formatter;
    @NotNull
    private PrintlnLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lenient().doReturn("").when(formatter).format(any(), anyInt(), anyString(), any(), any());
        logger = new PrintlnLogger(formatter);
        start = logger.start;
    }

    @Test
    void creators() {
        final PrintlnLogger first = new PrintlnLogger();
        Assertions.assertNotNull(first.start);
        Assertions.assertNotNull(first.formatter);

        final PrintlnLogger second = new PrintlnLogger("1");
        Assertions.assertNotNull(second.start);
        Assertions.assertNotNull(second.formatter);

        final LoggerFormatter formatter = LoggerFormatter.parse("2");
        final PrintlnLogger last = new PrintlnLogger(formatter);
        Assertions.assertNotNull(last.start);
        Assertions.assertNotNull(last.formatter);
        Assertions.assertEquals(formatter, last.formatter);
    }

    @Test
    void verbose() {
        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        logger.log(Logger.LEVEL_VERBOSE, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_VERBOSE), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_VERBOSE)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_VERBOSE)), anyString(), anyString(), any());
    }

    @Test
    void debug() {
        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        logger.log(Logger.LEVEL_DEBUG, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_DEBUG), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_DEBUG)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_DEBUG)), anyString(), anyString(), any());
    }

    @Test
    void info() {
        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        logger.log(Logger.LEVEL_INFO, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_INFO), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_INFO)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_INFO)), anyString(), anyString(), any());
    }

    @Test
    void warning() {
        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        logger.log(Logger.LEVEL_WARNING, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_WARNING), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_WARNING)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_WARNING)), anyString(), anyString(), any());
    }

    @Test
    void error() {
        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        logger.log(Logger.LEVEL_ERROR, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_ERROR), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_ERROR)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_ERROR)), anyString(), anyString(), any());
    }

    @Test
    void wtf() {
        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        logger.log(Logger.LEVEL_ASSERT, tag, content, throwable);
        verify(formatter, times(2)).format(eq(start), eq(Logger.LEVEL_ASSERT), eq(tag), eq(content), eq(throwable));
        //noinspection ConstantConditions
        verify(formatter, never()).format(not(eq(start)), not(eq(Logger.LEVEL_ASSERT)), not(eq(tag)), not(eq(content)), not(eq(throwable)));
        verify(formatter, never()).format(any(), not(eq(Logger.LEVEL_ASSERT)), anyString(), anyString(), any());
    }

}