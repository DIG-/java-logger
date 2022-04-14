package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.UUID;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

@ExtendWith(MockitoExtension.class)
class FilterLoggerTest_Message {

    @Mock
    private BaseLogger parent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assertTag() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();

        final FilterLogger logger = new FilterLogger(Logger.LEVEL_VERBOSE, parent);
        logger.log(Logger.LEVEL_VERBOSE, tag, message, null);
        logger.log(Logger.LEVEL_DEBUG, tag, message, null);
        logger.log(Logger.LEVEL_INFO, tag, message, null);
        logger.log(Logger.LEVEL_WARNING, tag, message, null);
        logger.log(Logger.LEVEL_ERROR, tag, message, null);
        logger.log(Logger.LEVEL_ASSERT, tag, message, null);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.eq(tag), Mockito.any(BaseLogger.Message.class), Mockito.any());
    }

    @Test
    void assertMessage() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();

        final FilterLogger logger = new FilterLogger(Logger.LEVEL_VERBOSE, parent);
        logger.log(Logger.LEVEL_VERBOSE, tag, message, null);
        logger.log(Logger.LEVEL_DEBUG, tag, message, null);
        logger.log(Logger.LEVEL_INFO, tag, message, null);
        logger.log(Logger.LEVEL_WARNING, tag, message, null);
        logger.log(Logger.LEVEL_ERROR, tag, message, null);
        logger.log(Logger.LEVEL_ASSERT, tag, message, null);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.eq(message), Mockito.any());
    }

    @Test
    void assertThrowable() {
        Assertions.assertNotNull(parent);
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        final FilterLogger logger = new FilterLogger(Logger.LEVEL_VERBOSE, parent);
        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.eq(throwable));
    }

    @Test
    void FilterVerbose() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_VERBOSE, parent);
        log(logger, true, true, true, true, true, true);
        Mockito.verify(parent, Mockito.times(6)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterDebug() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_DEBUG, parent);
        log(logger, false, true, true, true, true, true);
        Mockito.verify(parent, Mockito.times(5)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterInfo() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_INFO, parent);
        log(logger, false, false, true, true, true, true);
        Mockito.verify(parent, Mockito.times(4)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterWarning() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_WARNING, parent);
        log(logger, false, false, false, true, true, true);
        Mockito.verify(parent, Mockito.times(3)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterError() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_ERROR, parent);
        log(logger, false, false, false, false, true, true);
        Mockito.verify(parent, Mockito.times(2)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterAssert() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_ASSERT, parent);
        log(logger, false, false, false, false, false, true);
        Mockito.verify(parent, Mockito.times(1)).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }

    @Test
    void FilterNone() {
        Assertions.assertNotNull(parent);
        final FilterLogger logger = new FilterLogger(Logger.LEVEL_NONE, parent);
        log(logger, false, false, false, false, false, false);
        Mockito.verify(parent, Mockito.never()).log(Mockito.anyInt(), Mockito.anyString(), Mockito.any(BaseLogger.Message.class), Mockito.any(Throwable.class));
    }
    
    private void log(@NotNull final BaseLogger logger, boolean verbose, boolean debug, boolean info, boolean warning, boolean error, boolean wtf) {
        final String tag = UUID.randomUUID().toString();
        final Logger.Message message = () -> UUID.randomUUID().toString();
        final Throwable throwable = new RuntimeException();

        logger.log(Logger.LEVEL_VERBOSE, tag, message, throwable);
        Mockito.verify(parent, hasOutput(verbose, "Showing verbose message when is not intended to be shown.")).log(Logger.LEVEL_VERBOSE, tag, message, throwable);

        logger.log(Logger.LEVEL_DEBUG, tag, message, throwable);
        Mockito.verify(parent, hasOutput(debug, "Showing debug message when is not intended to be shown.")).log(Logger.LEVEL_DEBUG, tag, message, throwable);

        logger.log(Logger.LEVEL_INFO, tag, message, throwable);
        Mockito.verify(parent, hasOutput(info, "Showing info message when is not intended to be shown.")).log(Logger.LEVEL_INFO, tag, message, throwable);

        logger.log(Logger.LEVEL_WARNING, tag, message, throwable);
        Mockito.verify(parent, hasOutput(warning, "Showing warning message when is not intended to be shown.")).log(Logger.LEVEL_WARNING, tag, message, throwable);

        logger.log(Logger.LEVEL_ERROR, tag, message, throwable);
        Mockito.verify(parent, hasOutput(error, "Showing error message when is not intended to be shown.")).log(Logger.LEVEL_ERROR, tag, message, throwable);

        logger.log(Logger.LEVEL_ASSERT, tag, message, throwable);
        Mockito.verify(parent, hasOutput(wtf, "Showing assert message when is not intended to be shown.")).log(Logger.LEVEL_ASSERT, tag, message, throwable);
    }

    private VerificationMode hasOutput(final boolean hasOutput, @NotNull final String description) {
        return VerificationModeFactory.description(Mockito.times(hasOutput ? 1 : 0), description);
    }
}