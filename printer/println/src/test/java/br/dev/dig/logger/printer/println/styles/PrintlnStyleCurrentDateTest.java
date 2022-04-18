package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Pattern;

class PrintlnStyleCurrentDateTest extends PrintlnStyleCommon<PrintlnStyleCurrentDate> {

    @Override
    @NotNull
    PrintlnStyleCurrentDate create() {
        return new PrintlnStyleCurrentDate();
    }

    @Test
    void log_Format() {
        final String output = randomPrint(style);
        final Pattern pattern = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
        Assertions.assertTrue(pattern.matcher(output).matches());
    }

    @Test
    void log_Exact() {
        final String output = style.format(LocalDate.of(2022, Month.APRIL, 17));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("2022-04-17", output);
    }

}