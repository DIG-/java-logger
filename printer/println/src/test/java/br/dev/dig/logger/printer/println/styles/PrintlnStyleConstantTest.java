package br.dev.dig.logger.printer.println.styles;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrintlnStyleConstantTest extends PrintlnStyleCommon<PrintlnStyleConstant> {

    String constant;

    @Override
    @NotNull
    PrintlnStyleConstant create() {
        constant = randString();
        return new PrintlnStyleConstant(constant);
    }

    @Test
    void log_Constant() {
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(constant, randomPrint(style));
        }
    }

}