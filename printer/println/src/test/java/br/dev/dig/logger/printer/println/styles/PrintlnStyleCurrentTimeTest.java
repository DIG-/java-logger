package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}