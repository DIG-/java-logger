package br.dev.dig.logger.printer.firebase;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
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
class FirebaseCrashlyticsLoggerTest {

    @Mock
    private FirebaseCrashlytics crashlytics;
    @NotNull
    private FirebaseCrashlyticsLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger = new FirebaseCrashlyticsLogger(crashlytics);
    }

    @Test
    void Message_to_CharSequence() {
        final String tag = UUID.randomUUID().toString();
        final String content = UUID.randomUUID().toString();
        final Logger.Message message = () -> content;
        FirebaseCrashlyticsLogger spy = Mockito.spy(logger);

        spy.log(Logger.LEVEL_VERBOSE, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_VERBOSE), eq(tag), eq(content), isNull());
        reset(spy);
        spy.log(Logger.LEVEL_DEBUG, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_DEBUG), eq(tag), eq(content), isNull());
        reset(spy);
        spy.log(Logger.LEVEL_INFO, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_INFO), eq(tag), eq(content), isNull());
        reset(spy);
        spy.log(Logger.LEVEL_WARNING, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_WARNING), eq(tag), eq(content), isNull());
        reset(spy);
        spy.log(Logger.LEVEL_ERROR, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_ERROR), eq(tag), eq(content), isNull());
        reset(spy);
        spy.log(Logger.LEVEL_ASSERT, tag, message, null);
        verify(spy).log(eq(Logger.LEVEL_ASSERT), eq(tag), eq(content), isNull());
    }

    @Test
    void check_getExceptionByLevel() {
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_VERBOSE, null, null) instanceof FirebaseCrashlyticsLogger.Verbose);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_VERBOSE, message, null) instanceof FirebaseCrashlyticsLogger.Verbose);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_VERBOSE, null, throwable) instanceof FirebaseCrashlyticsLogger.Verbose);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_VERBOSE, message, throwable) instanceof FirebaseCrashlyticsLogger.Verbose);

        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_DEBUG, null, null) instanceof FirebaseCrashlyticsLogger.Debug);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_DEBUG, message, null) instanceof FirebaseCrashlyticsLogger.Debug);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_DEBUG, null, throwable) instanceof FirebaseCrashlyticsLogger.Debug);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_DEBUG, message, throwable) instanceof FirebaseCrashlyticsLogger.Debug);

        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_INFO, null, null) instanceof FirebaseCrashlyticsLogger.Info);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_INFO, message, null) instanceof FirebaseCrashlyticsLogger.Info);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_INFO, null, throwable) instanceof FirebaseCrashlyticsLogger.Info);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_INFO, message, throwable) instanceof FirebaseCrashlyticsLogger.Info);

        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_WARNING, null, null) instanceof FirebaseCrashlyticsLogger.Warning);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_WARNING, message, null) instanceof FirebaseCrashlyticsLogger.Warning);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_WARNING, null, throwable) instanceof FirebaseCrashlyticsLogger.Warning);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_WARNING, message, throwable) instanceof FirebaseCrashlyticsLogger.Warning);

        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ERROR, null, null) instanceof FirebaseCrashlyticsLogger.Error);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ERROR, message, null) instanceof FirebaseCrashlyticsLogger.Error);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ERROR, null, throwable) instanceof FirebaseCrashlyticsLogger.Error);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ERROR, message, throwable) instanceof FirebaseCrashlyticsLogger.Error);

        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ASSERT, null, null) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ASSERT, message, null) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ASSERT, null, throwable) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(Logger.LEVEL_ASSERT, message, throwable) instanceof FirebaseCrashlyticsLogger.Assert);

        Assertions.assertTrue(logger.getExceptionByLevel(99, null, null) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(99, message, null) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(99, null, throwable) instanceof FirebaseCrashlyticsLogger.Assert);
        Assertions.assertTrue(logger.getExceptionByLevel(99, message, throwable) instanceof FirebaseCrashlyticsLogger.Assert);
    }

    @Test
    void format_WithoutPropagation() {
        final String tag = UUID.randomUUID().toString();
        final String message = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException(UUID.randomUUID().toString());
        Assertions.assertEquals("Empty message", logger.formatMessage(null, null, null));
        Assertions.assertEquals(tag + ": Empty message", logger.formatMessage(tag, null, null));
        Assertions.assertEquals(tag + ": " + message, logger.formatMessage(tag, message, null));
        Assertions.assertTrue(logger.formatMessage(tag, null, throwable).startsWith(tag + ": " + throwable));
        Assertions.assertTrue(logger.formatMessage(tag, message, throwable).startsWith(tag + ": " + message));
        Assertions.assertTrue(logger.formatMessage(tag, message, throwable).contains(throwable.toString()));
    }

    @Test
    void check_clearStackTrace() {
        final Throwable throwable = logger.getExceptionByLevel(Logger.LEVEL_VERBOSE, null, null);
        final Throwable clear = logger.clearStackTrace(throwable);
        int count = 0;
        for (final StackTraceElement element : clear.getStackTrace()) {
            try {
                final Class<?> clazz = Class.forName(element.getClassName());
                Assertions.assertFalse(BaseLogger.class.isAssignableFrom(clazz));
                Assertions.assertFalse(Logger.class.isAssignableFrom(clazz));
            } catch (ClassNotFoundException e) {
                break;
            }
            count++;
            if (count >= 5)
                break;
        }
    }

}