package br.dev.dig.logger.printer.firebase;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

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

}