package br.dev.dig.logger.printer.android_log;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class AndroidLogLoggerTest {

    @Mock
    private LogWrapper wrapper;
    @NotNull
    private AndroidLogLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger = new AndroidLogLogger(wrapper);
    }

    @Test
    void nullTag() {
        logger.log(Logger.LEVEL_VERBOSE, null, (String) null, null);
        verify(wrapper, only()).v(isNull(), isNotNull(), isNull());
    }

    @Test
    void verbose() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        verify(wrapper, only()).v(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_VERBOSE, tag, content, throwable);
        verify(wrapper, only()).v(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
    }

    @Test
    void debug() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        verify(wrapper, only()).d(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_DEBUG, tag, content, throwable);
        verify(wrapper, only()).d(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
    }

    @Test
    void info() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        verify(wrapper, only()).i(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_INFO, tag, content, throwable);
        verify(wrapper, only()).i(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
    }

    @Test
    void warning() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        verify(wrapper, only()).w(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_WARNING, tag, content, throwable);
        verify(wrapper, only()).w(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
    }

    @Test
    void error() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        verify(wrapper, only()).e(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_ERROR, tag, content, throwable);
        verify(wrapper, only()).e(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).wtf(anyString(), any(), any());
    }

    @Test
    void wtf() {
        final String tag = UUID.randomUUID().toString();
        final String toMessage = UUID.randomUUID().toString();
        final Logger.Message message = () -> toMessage;
        final String content = UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        verify(wrapper, only()).wtf(eq(tag), eq(toMessage), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
        reset(wrapper);

        logger.log(Logger.LEVEL_ASSERT, tag, content, throwable);
        verify(wrapper, only()).wtf(eq(tag), eq(content), eq(throwable));
        verify(wrapper, never()).v(anyString(), any(), any());
        verify(wrapper, never()).d(anyString(), any(), any());
        verify(wrapper, never()).i(anyString(), any(), any());
        verify(wrapper, never()).w(anyString(), any(), any());
        verify(wrapper, never()).e(anyString(), any(), any());
    }

    @Test
    void dummy_Constructor() {
        new AndroidLogLogger();
    }
}