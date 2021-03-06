package br.dev.dig.logger;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class LoggerTest_Message {

    @Mock
    protected BaseLogger base;

    @NotNull
    protected Logger log;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        log = new Logger() {
            @Override
            @Nullable
            public String getTag() {
                return null;
            }

            @Override
            protected @NotNull BaseLogger getLogger() {
                return base;
            }
        };
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void levels() {
        Assertions.assertTrue(Logger.LEVEL_VERBOSE < Logger.LEVEL_DEBUG);
        Assertions.assertTrue(Logger.LEVEL_DEBUG < Logger.LEVEL_INFO);
        Assertions.assertTrue(Logger.LEVEL_INFO < Logger.LEVEL_WARNING);
        Assertions.assertTrue(Logger.LEVEL_WARNING < Logger.LEVEL_ERROR);
        Assertions.assertTrue(Logger.LEVEL_ERROR < Logger.LEVEL_ASSERT);
        Assertions.assertTrue(Logger.LEVEL_ASSERT < Logger.LEVEL_NONE);
    }

    @Test
    void verbose() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.v(message);
        log.v(message, throwable);
        log.verbose(message);
        log.verbose(message, throwable);

        verify(base, times(2)).log(eq(Logger.LEVEL_VERBOSE), isNull(), eq(message), isNull());
        verify(base, times(2)).log(eq(Logger.LEVEL_VERBOSE), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_VERBOSE), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_VERBOSE)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_VERBOSE)), isNull(), anyString(), any());
    }

    @Test
    void debug() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.d(message);
        log.d(message, throwable);
        log.debug(message);
        log.debug(message, throwable);

        verify(base, times(2)).log(eq(Logger.LEVEL_DEBUG), isNull(), eq(message), isNull());
        verify(base, times(2)).log(eq(Logger.LEVEL_DEBUG), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_DEBUG), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_DEBUG)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_DEBUG)), isNull(), anyString(), any());
    }

    @Test
    void info() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.i(message);
        log.i(message, throwable);
        log.info(message);
        log.info(message, throwable);

        verify(base, times(2)).log(eq(Logger.LEVEL_INFO), isNull(), eq(message), isNull());
        verify(base, times(2)).log(eq(Logger.LEVEL_INFO), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_INFO), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_INFO)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_INFO)), isNull(), anyString(), any());
    }

    @Test
    void warning() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.w(message);
        log.w(message, throwable);
        log.warning(message);
        log.warning(message, throwable);

        verify(base, times(2)).log(eq(Logger.LEVEL_WARNING), isNull(), eq(message), isNull());
        verify(base, times(2)).log(eq(Logger.LEVEL_WARNING), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_WARNING), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_WARNING)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_WARNING)), isNull(), anyString(), any());
    }

    @Test
    void error() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.e(message);
        log.e(message, throwable);
        log.error(message);
        log.error(message, throwable);

        verify(base, times(2)).log(eq(Logger.LEVEL_ERROR), isNull(), eq(message), isNull());
        verify(base, times(2)).log(eq(Logger.LEVEL_ERROR), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_ERROR), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_ERROR)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_ERROR)), isNull(), anyString(), any());
    }

    @Test
    void wtf() {
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();
        log.wtf(message);
        log.wtf(message, throwable);

        verify(base, times(1)).log(eq(Logger.LEVEL_ASSERT), isNull(), eq(message), isNull());
        verify(base, times(1)).log(eq(Logger.LEVEL_ASSERT), isNull(), eq(message), eq(throwable));
        // Must not dispatch as String
        verify(base, never()).log(eq(Logger.LEVEL_ASSERT), isNull(), anyString(), any());
        // Others levels
        verify(base, never()).log(not(eq(Logger.LEVEL_ASSERT)), isNull(), any(BaseLogger.Message.class), any());
        verify(base, never()).log(not(eq(Logger.LEVEL_ASSERT)), isNull(), anyString(), any());
    }
}