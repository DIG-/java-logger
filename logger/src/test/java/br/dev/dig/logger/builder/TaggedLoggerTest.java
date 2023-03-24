package br.dev.dig.logger.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;

@ExtendWith(MockitoExtension.class)
class TaggedLoggerTest {

    @Mock
    private LoggerBuilder builder;

    @Mock
    private BaseLogger base;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creator() {
        final String tag = UUID.randomUUID().toString();
        final TaggedLogger logger = new TaggedLogger(builder, tag, base);
        Assertions.assertEquals(builder, logger.builder);
        Assertions.assertEquals(tag, logger.tag);
        Assertions.assertEquals(base, logger.base);
    }

    @Test
    void getter() {
        final String tag = UUID.randomUUID().toString();
        final TaggedLogger logger = new TaggedLogger(builder, tag, base);
        Assertions.assertEquals(tag, logger.getTag());
        Assertions.assertEquals(base, logger.getLogger());
    }
}