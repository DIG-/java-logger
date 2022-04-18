package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

class PrintlnStyleElapsedTimeTest extends PrintlnStyleCommon<PrintlnStyleElapsedTime> {

    @Override
    @NotNull
    PrintlnStyleElapsedTime create() {
        return new PrintlnStyleElapsedTime();
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
        final LocalDate date = LocalDate.now();
        final LocalTime start = LocalTime.of(14, 13, 12);
        final LocalTime end = LocalTime.of(21, 57, 3);
        final String output = style.format(LocalDateTime.of(date, start), LocalDateTime.of(date, end));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("07:43:51.000", output);
    }

    @Test
    void log_ExactAcrossDays() {
        final LocalDate dateStart = LocalDate.now();
        final LocalDate dateEnd = dateStart.plusDays(1);
        final LocalTime start = LocalTime.of(21, 57, 3);
        final LocalTime end = LocalTime.of(14, 13, 12);
        final String output = style.format(LocalDateTime.of(dateStart, start), LocalDateTime.of(dateEnd, end));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("16:16:09.000", output);
    }

    @Test
    void log_ExactAbove24Hours() {
        final LocalDate dateStart = LocalDate.now();
        final LocalDate dateEnd = dateStart.plusDays(2);
        final LocalTime start = LocalTime.of(21, 57, 3);
        final LocalTime end = LocalTime.of(14, 13, 12);
        final String output = style.format(LocalDateTime.of(dateStart, start), LocalDateTime.of(dateEnd, end));
        Assertions.assertNotNull(output);
        Assertions.assertEquals("40:16:09.000", output);
    }

}