package br.dev.dig.logger.printer.formatter;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleConstant;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentDate;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleElapsedTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleMessage;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleStackTrace;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTag;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTagShort;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleThrowableMessage;

@ExtendWith(MockitoExtension.class)
class LoggerFormatterTest {

    @Mock
    LoggerFormatter.Style style;

    @NotNull
    LoggerFormatter formatter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        formatter = new LoggerFormatter(Collections.singletonList(style));
    }

    @Test
    void format() {
        final LocalDateTime start = LocalDateTime.now();
        final int level = Logger.LEVEL_VERBOSE;
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        final CharSequence formatted = formatter.format(start, level, tag, message, throwable);
        Assertions.assertNotNull(formatted);
        verify(style, only()).print(eq(start), eq(level), eq(tag), eq(message), eq(throwable));
        //noinspection ConstantConditions
        verify(style, never()).print(not(eq(start)), not(eq(level)), not(eq(tag)), not(eq(message)), not(eq(throwable)));
    }

    @Test
    void parse() {
        final LoggerFormatter formatter = LoggerFormatter.parse("0${tag}${tag-short}${date}${time}${elapsed}${message}${throwable}${stacktrace}1");
        Assertions.assertNotNull(formatter);
        Assertions.assertTrue(formatter.styles instanceof List);
        final List<LoggerFormatter.Style> styles = (List<LoggerFormatter.Style>) formatter.styles;
        Assertions.assertEquals(10, styles.size());
        Assertions.assertEquals(LoggerFormatStyleConstant.class, styles.get(0).getClass());
        Assertions.assertEquals(LoggerFormatStyleTag.class, styles.get(1).getClass());
        Assertions.assertEquals(LoggerFormatStyleTagShort.class, styles.get(2).getClass());
        Assertions.assertEquals(LoggerFormatStyleCurrentDate.class, styles.get(3).getClass());
        Assertions.assertEquals(LoggerFormatStyleCurrentTime.class, styles.get(4).getClass());
        Assertions.assertEquals(LoggerFormatStyleElapsedTime.class, styles.get(5).getClass());
        Assertions.assertEquals(LoggerFormatStyleMessage.class, styles.get(6).getClass());
        Assertions.assertEquals(LoggerFormatStyleThrowableMessage.class, styles.get(7).getClass());
        Assertions.assertEquals(LoggerFormatStyleStackTrace.class, styles.get(8).getClass());
        Assertions.assertEquals(LoggerFormatStyleConstant.class, styles.get(9).getClass());
    }

}