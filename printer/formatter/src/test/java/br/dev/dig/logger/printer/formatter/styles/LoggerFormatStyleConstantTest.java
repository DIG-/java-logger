package br.dev.dig.logger.printer.formatter.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoggerFormatStyleConstantTest extends LoggerFormatStyleCommon<LoggerFormatStyleConstant> {

    String constant;

    @Override
    @NotNull
    LoggerFormatStyleConstant create() {
        constant = randString();
        return new LoggerFormatStyleConstant(constant);
    }

    @Test
    void log_Constant() {
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(constant, randomPrint(style));
        }
    }

}