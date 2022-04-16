package br.dev.dig.logger.printer.println.styles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PrintlnStyleConstantTest {

    private final String constant = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
    }

    @Test
    void log() {
        final PrintlnStyleConstant style = new PrintlnStyleConstant(constant);
        final Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(constant, style.print(
                    rand.nextInt(),
                    String.valueOf(rand.nextLong()),
                    LocalDateTime.now(),
                    String.valueOf(rand.nextLong()),
                    rand.nextFloat() > 0.5 ? null : new RuntimeException()
            ));
        }
    }

}