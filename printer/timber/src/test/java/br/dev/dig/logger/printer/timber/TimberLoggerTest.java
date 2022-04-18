package br.dev.dig.logger.printer.timber;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;
import timber.log.Timber;

class TimberLoggerTest {

    private final String tag = UUID.randomUUID().toString();
    private final String content = UUID.randomUUID().toString();
    private final Logger.Message message = () -> content;
    private final Throwable throwable = new RuntimeException();

    @Mock
    private Timber.Tree tree;
    @NotNull
    private BaseLogger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger = new TimberLogger() {
            @Override
            Timber.Tree getTimber(String tag) {
                return tree;
            }
        };
    }

    @Test
    void verbose() {
        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        logger.log(Logger.LEVEL_VERBOSE, tag, content, throwable);
        verify(tree, times(2)).v(eq(throwable), eq(content));
        verify(tree, never()).v(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).d(any(Throwable.class), anyString());
        verify(tree, never()).i(any(Throwable.class), anyString());
        verify(tree, never()).w(any(Throwable.class), anyString());
        verify(tree, never()).e(any(Throwable.class), anyString());
        verify(tree, never()).wtf(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_VERBOSE, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).v(eq(throwable), isNull());
    }

    @Test
    void debug() {
        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        logger.log(Logger.LEVEL_DEBUG, tag, content, throwable);
        verify(tree, times(2)).d(eq(throwable), eq(content));
        verify(tree, never()).d(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).v(any(Throwable.class), anyString());
        verify(tree, never()).i(any(Throwable.class), anyString());
        verify(tree, never()).w(any(Throwable.class), anyString());
        verify(tree, never()).e(any(Throwable.class), anyString());
        verify(tree, never()).wtf(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_DEBUG, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).d(eq(throwable), isNull());
    }

    @Test
    void info() {
        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        logger.log(Logger.LEVEL_INFO, tag, content, throwable);
        verify(tree, times(2)).i(eq(throwable), eq(content));
        verify(tree, never()).i(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).v(any(Throwable.class), anyString());
        verify(tree, never()).d(any(Throwable.class), anyString());
        verify(tree, never()).w(any(Throwable.class), anyString());
        verify(tree, never()).e(any(Throwable.class), anyString());
        verify(tree, never()).wtf(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_INFO, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).i(eq(throwable), isNull());
    }

    @Test
    void warning() {
        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        logger.log(Logger.LEVEL_WARNING, tag, content, throwable);
        verify(tree, times(2)).w(eq(throwable), eq(content));
        verify(tree, never()).w(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).v(any(Throwable.class), anyString());
        verify(tree, never()).d(any(Throwable.class), anyString());
        verify(tree, never()).i(any(Throwable.class), anyString());
        verify(tree, never()).e(any(Throwable.class), anyString());
        verify(tree, never()).wtf(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_WARNING, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).w(eq(throwable), isNull());
    }

    @Test
    void error() {
        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        logger.log(Logger.LEVEL_ERROR, tag, content, throwable);
        verify(tree, times(2)).e(eq(throwable), eq(content));
        verify(tree, never()).e(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).v(any(Throwable.class), anyString());
        verify(tree, never()).d(any(Throwable.class), anyString());
        verify(tree, never()).i(any(Throwable.class), anyString());
        verify(tree, never()).w(any(Throwable.class), anyString());
        verify(tree, never()).wtf(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_ERROR, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).e(eq(throwable), isNull());
    }

    @Test
    void wtf() {
        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        logger.log(Logger.LEVEL_ASSERT, tag, content, throwable);
        verify(tree, times(2)).wtf(eq(throwable), eq(content));
        verify(tree, never()).wtf(not(any(Throwable.class)), not(eq(content)));

        verify(tree, never()).v(any(Throwable.class), anyString());
        verify(tree, never()).d(any(Throwable.class), anyString());
        verify(tree, never()).i(any(Throwable.class), anyString());
        verify(tree, never()).w(any(Throwable.class), anyString());
        verify(tree, never()).e(any(Throwable.class), anyString());

        reset(tree);
        logger.log(Logger.LEVEL_ASSERT, tag, (CharSequence) null, throwable);
        verify(tree, times(1)).wtf(eq(throwable), isNull());
    }

    @Test
    void checkTreeNotNull() {
        final TimberLogger logger = new TimberLogger();
        Assertions.assertNotNull(logger.getTimber(null));
        Assertions.assertNotNull(logger.getTimber(tag));
    }

}