package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class LoggerBuilderTest {

    @Mock
    protected BaseLogger base;

    @NotNull
    protected LoggerBuilder builder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        builder = new LoggerBuilder() {
            @Override
            protected @NotNull BaseLogger getBaseLogger() {
                return base;
            }
        };
    }

    @Test
    void checkBase() {
        Assertions.assertEquals(base, builder.getBaseLogger());
    }

    @Test
    void checkBasicInstance() {
        final Logger logger = builder.getLogger();
        Assertions.assertNotNull(logger);
        Assertions.assertEquals(logger, builder.common);
    }

    @Test
    void checkTag() {
        final String tag = UUID.randomUUID().toString();
        final Logger logger = builder.getLogger(tag);
        Assertions.assertNotNull(logger);
        Assertions.assertTrue(logger instanceof TaggedLogger);
        final TaggedLogger tagged = (TaggedLogger) logger;
        Assertions.assertEquals(tag, tagged.getTag());
    }

    @Test
    void checkNullTag() {
        builder.getLogger();
        Assertions.assertEquals(builder.common, builder.getLogger(null));
        Assertions.assertEquals(builder.common, builder.getLogger(""));
    }

    @Test
    void fromLogger_withoutTag() {
        final Logger first = builder.getLogger();
        Assertions.assertNotNull(first);
        final Logger last = Logger.getInstance(builder);
        Assertions.assertNotNull(last);
        Assertions.assertEquals(first, last);
        Assertions.assertEquals(builder.DEFAULT_LOGGER_TAG, ((TaggedLogger) last).getTag());
    }

    @Test
    void fromLogger_withTag() {
        final String tag = UUID.randomUUID().toString();
        final Logger first = builder.getLogger(tag);
        Assertions.assertNotNull(first);
        Assertions.assertEquals(tag, ((TaggedLogger) first).getTag());

        final Logger last = Logger.getInstance(builder, tag);
        Assertions.assertNotNull(last);
        Assertions.assertEquals(tag, ((TaggedLogger) last).getTag());
    }

}