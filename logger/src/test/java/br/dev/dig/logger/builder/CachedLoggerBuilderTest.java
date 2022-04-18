package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class CachedLoggerBuilderTest extends LoggerBuilderTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        builder = new CachedLoggerBuilder() {
            @Override
            protected @NotNull BaseLogger getBaseLogger() {
                return base;
            }
        };
    }

    @Test
    void checkCachedTag() {
        final String tag = UUID.randomUUID().toString();
        final Logger first = builder.getLogger(tag);
        final Logger last = builder.getLogger(tag);
        Assertions.assertEquals(first, last);
    }

    @Test
    void checkNullCreator() {
        final CachedLoggerBuilder builder = (CachedLoggerBuilder) this.builder;
        Assertions.assertNotNull(builder.createLogger(null));
    }

}