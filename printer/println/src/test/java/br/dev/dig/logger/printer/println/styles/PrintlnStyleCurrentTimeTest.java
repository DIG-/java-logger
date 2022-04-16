package br.dev.dig.logger.printer.println.styles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class PrintlnStyleCurrentTimeTest {

    final PrintlnStyleCurrentTime style = new PrintlnStyleCurrentTime();

    @Test
    void log_NotNullOrEmpty() {
        final String output = PrintlnStyleUtil.randomPrint(style);
        Assertions.assertNotNull(output);
        Assertions.assertFalse(output.isEmpty());
    }

    @Test
    void log_Format() {
        final Pattern pattern = Pattern.compile("^[\\d]{2}:[\\d]{2}:[\\d]{2}(\\.[\\d]{3})$");
        for (int i = 0; i < 10; i++) {
            final String output = PrintlnStyleUtil.randomPrint(style);
            Assertions.assertTrue(pattern.matcher(output).matches());
        }
    }

}