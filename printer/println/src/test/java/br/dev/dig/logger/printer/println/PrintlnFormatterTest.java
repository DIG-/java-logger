package br.dev.dig.logger.printer.println;

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
import br.dev.dig.logger.printer.println.styles.PrintlnStyleConstant;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleCurrentDate;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleCurrentTime;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleElapsedTime;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleMessage;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleStackTrace;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleTag;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleTagShort;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleThrowableMessage;

@ExtendWith(MockitoExtension.class)
class PrintlnFormatterTest {

    @Mock
    PrintlnFormatter.Style style;

    @NotNull
    PrintlnFormatter formatter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        formatter = new PrintlnFormatter(Collections.singletonList(style));
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
        verify(style, only()).print(eq(level), eq(tag), eq(start), eq(message), eq(throwable));
        //noinspection ConstantConditions
        verify(style, never()).print(not(eq(level)), not(eq(tag)), not(eq(start)), not(eq(message)), not(eq(throwable)));
    }

    @Test
    void parse() {
        final PrintlnFormatter formatter = PrintlnFormatter.parse("0${tag}${tag-short}${date}${time}${elapsed}${message}${throwable}${stacktrace}1");
        Assertions.assertNotNull(formatter);
        Assertions.assertTrue(formatter.styles instanceof List);
        final List<PrintlnFormatter.Style> styles = (List<PrintlnFormatter.Style>) formatter.styles;
        Assertions.assertEquals(10, styles.size());
        Assertions.assertEquals(PrintlnStyleConstant.class, styles.get(0).getClass());
        Assertions.assertEquals(PrintlnStyleTag.class, styles.get(1).getClass());
        Assertions.assertEquals(PrintlnStyleTagShort.class, styles.get(2).getClass());
        Assertions.assertEquals(PrintlnStyleCurrentDate.class, styles.get(3).getClass());
        Assertions.assertEquals(PrintlnStyleCurrentTime.class, styles.get(4).getClass());
        Assertions.assertEquals(PrintlnStyleElapsedTime.class, styles.get(5).getClass());
        Assertions.assertEquals(PrintlnStyleMessage.class, styles.get(6).getClass());
        Assertions.assertEquals(PrintlnStyleThrowableMessage.class, styles.get(7).getClass());
        Assertions.assertEquals(PrintlnStyleStackTrace.class, styles.get(8).getClass());
        Assertions.assertEquals(PrintlnStyleConstant.class, styles.get(9).getClass());
    }

}