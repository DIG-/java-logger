package br.dev.dig.logger.async;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class AsyncLoggerTest {

    @Spy
    private final MockExecutor executor = new MockExecutor();

    @Mock
    private BaseLogger parent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creator() {
        final AsyncLogger logger = new AsyncLogger(parent, executor);
        Assertions.assertEquals(parent, logger.target);
        Assertions.assertEquals(executor, logger.executor);
    }

    @Test
    void log_Message() {
        final AsyncLogger logger = new AsyncLogger(parent, executor);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

    @Test
    void log_CharSequence() {
        final AsyncLogger logger = new AsyncLogger(parent, executor);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_VERBOSE), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_DEBUG), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_INFO), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_WARNING), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ERROR), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
        Mockito.reset(parent, executor);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(executor, Mockito.only()).execute(Mockito.any());
        Mockito.verify(parent, Mockito.only()).log(Mockito.eq(Logger.LEVEL_ASSERT), Mockito.eq(tag), Mockito.eq(message), Mockito.eq(throwable));
    }

}