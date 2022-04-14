package br.dev.dig.logger.union;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class UnionLoggerTest_Propagation {

    @Mock
    private BaseLogger parent_1;
    @Mock
    private BaseLogger parent_2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSingle_Message() {
        Assertions.assertNotNull(parent_1);
        final UnionLogger logger = UnionLogger.create(parent_1);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

    @Test
    void testDuo_Message() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        final UnionLogger logger = UnionLogger.create(parent_1, parent_2);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

    @Test
    void testSingle_CharSequence() {
        Assertions.assertNotNull(parent_1);
        final UnionLogger logger = UnionLogger.create(parent_1);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

    @Test
    void testDuo_CharSequence() {
        Assertions.assertNotNull(parent_1);
        Assertions.assertNotNull(parent_2);
        final UnionLogger logger = UnionLogger.create(parent_1, parent_2);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent_1);
        Mockito.reset(parent_2);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent_1, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.verify(parent_2, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

}