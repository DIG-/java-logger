package br.dev.dig.logger.printer.println.styles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.regex.Pattern;

class PrintlnStyleCurrentDateTest {

    final PrintlnStyleCurrentDate style = new PrintlnStyleCurrentDate();
    final Random rand = new Random();

    @Test
    void log_NotNullOrEmpty() {
        final String output = PrintlnStyleUtil.randomPrint(style);
        Assertions.assertNotNull(output);
        Assertions.assertFalse(output.isEmpty());
    }

    @Test
    void log_Format() {
        final String output = PrintlnStyleUtil.randomPrint(style);
        final Pattern pattern = Pattern.compile("^[\\d]{4}-[\\d]{2}-[\\d]{2}$");
        Assertions.assertTrue(pattern.matcher(output).matches());
    }

}