package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.regex.Pattern;

class PrintlnStyleCurrentTimeTest extends PrintlnStyleCommon<PrintlnStyleCurrentTime> {

    @Override
    @NotNull
    PrintlnStyleCurrentTime create() {
        return new PrintlnStyleCurrentTime();
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
        final String output = style.format(LocalTime.of(21,57,3));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("21:57:03.000", output);
    }

}