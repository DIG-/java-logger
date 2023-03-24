package br.dev.dig.logger.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class FilterLoggerByAllowedThrowableTest {

    @Mock
    private BaseLogger parent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assertParent() {
        Assertions.assertNotNull(parent);
        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, new HashSet<>());
        Assertions.assertEquals(parent, logger.target);
    }

    @Test
    void assertAllowed() {
        Assertions.assertNotNull(parent);
        final Set<Class<? extends Throwable>> allowed = new HashSet<>();
        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, allowed);
        Assertions.assertEquals(allowed, logger.allowed);
    }


    @Test
    void assertTag() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();

        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, new HashSet<>());
        logger.log(Logger.LEVEL_VERBOSE, tag, message, null);
        logger.log(Logger.LEVEL_DEBUG, tag, message, null);
        logger.log(Logger.LEVEL_INFO, tag, message, null);
        logger.log(Logger.LEVEL_WARNING, tag, message, null);
        logger.log(Logger.LEVEL_ERROR, tag, message, null);
        logger.log(Logger.LEVEL_ASSERT, tag, message, null);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.eq(tag), Mockito.anyString(), Mockito.any());
    }

    @Test
    void assertMessage() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();

        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, new HashSet<>());
        logger.log(Logger.LEVEL_VERBOSE, tag, message, null);
        logger.log(Logger.LEVEL_DEBUG, tag, message, null);
        logger.log(Logger.LEVEL_INFO, tag, message, null);
        logger.log(Logger.LEVEL_WARNING, tag, message, null);
        logger.log(Logger.LEVEL_ERROR, tag, message, null);
        logger.log(Logger.LEVEL_ASSERT, tag, message, null);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.eq(message), Mockito.any());
    }

    @Test
    void assertThrowableAllowed() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new ArithmeticException();

        final HashSet<Class<? extends Throwable>> allowed = new HashSet<>();
        allowed.add(RuntimeException.class);
        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, allowed);
        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.eq(throwable));
    }

    @Test
    void assertThrowableNotAllowed() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        final HashSet<Class<? extends Throwable>> allowed = new HashSet<>();
        allowed.add(ArithmeticException.class);
        final FilterLoggerByAllowedThrowable logger = new FilterLoggerByAllowedThrowable(parent, allowed);
        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent, Mockito.times(0)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.eq(throwable));
    }
}