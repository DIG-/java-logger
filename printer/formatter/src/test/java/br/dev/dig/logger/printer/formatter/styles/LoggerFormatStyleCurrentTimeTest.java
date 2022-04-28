package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.regex.Pattern;

class LoggerFormatStyleCurrentTimeTest extends LoggerFormatStyleCommon<LoggerFormatStyleCurrentTime> {

    @Override
    @NotNull
    LoggerFormatStyleCurrentTime create() {
        return new LoggerFormatStyleCurrentTime();
    }

    @Test
    void log_Format() {
        final Pattern pattern = Pattern.compile("^[\\d]{2}:[\\d]{2}:[\\d]{2}(\\.[\\d]{3})$");
        for (int i = 0; i < 10; i++) {
            final String output = randomPrint(style);
            Assertions.assertTrue(pattern.matcher(output).matches());
        }
    }

    @Test
    void log_Exact() {
        final String output = style.format(LocalTime.of(21, 57, 3));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("21:57:03.000", output);
    }

}