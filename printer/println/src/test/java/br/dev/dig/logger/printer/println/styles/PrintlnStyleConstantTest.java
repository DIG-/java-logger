package br.dev.dig.logger.printer.println.styles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PrintlnStyleConstantTest {

    private final String constant = UUID.randomUUID().toString();

    @Test
    void log() {
        final PrintlnStyleConstant style = new PrintlnStyleConstant(constant);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(constant, PrintlnStyleUtil.randomPrint(style));
        }
    }

}