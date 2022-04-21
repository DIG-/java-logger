package br.dev.dig.logger.printer.firebase;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.Arrays;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public class FirebaseCrashlyticsLogger implements BaseLogger {

    @NotNull
    public final FirebaseCrashlytics crashlytics;

    public FirebaseCrashlyticsLogger(@NotNull final FirebaseCrashlytics crashlytics) {
        this.crashlytics = crashlytics;
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        crashlytics.recordException(
            clearStackTrace(
                getExceptionByLevel(level,
                    formatMessage(tag, message), throwable)));
    }

    @NotNull
    String formatMessage(final @Nullable String tag, @Nullable final CharSequence message) {
        final StringBuilder builder = new StringBuilder();
        if (tag != null && !tag.isEmpty()) {
            builder.append(tag).append(": ");
        }
        if (message != null && message.length() > 0) {
            builder.append(message);
        }
        if (builder.length() <= 0) {
            builder.append("Empty message");
        }
        return builder.toString();
    }

    @VisibleForTesting
    RuntimeException getExceptionByLevel(final int level, @Nullable final String message, @Nullable final Throwable throwable) {
        switch (level) {
            case Logger.LEVEL_VERBOSE:
                return new Verbose(message, throwable);
            case Logger.LEVEL_DEBUG:
                return new Debug(message, throwable);
            case Logger.LEVEL_INFO:
                return new Info(message, throwable);
            case Logger.LEVEL_WARNING:
                return new Warning(message, throwable);
            case Logger.LEVEL_ERROR:
                return new Error(message, throwable);
            case Logger.LEVEL_ASSERT:
                return new Assert(message, throwable);
        }
        return new Assert(message, throwable);
    }

    @VisibleForTesting
    <T extends Throwable> T clearStackTrace(@NotNull T throwable) {
        final StackTraceElement[] elements = throwable.getStackTrace();
        int i;
        for (i = 0; i < elements.length; i++) {
            final StackTraceElement element = elements[i];
            try {
                final Class<?> clazz = Class.forName(element.getClassName());
                if (!BaseLogger.class.isAssignableFrom(clazz) && !Logger.class.isAssignableFrom(clazz)) {
                    break;
                }
            } catch (ClassNotFoundException e) {
                break;
            }
        }
        throwable.setStackTrace(Arrays.copyOfRange(elements, i, elements.length));
        return throwable;
    }

    final static class Verbose extends RuntimeException {
        Verbose(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

    final static class Debug extends RuntimeException {
        Debug(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

    final static class Info extends RuntimeException {
        Info(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

    final static class Warning extends RuntimeException {
        Warning(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

    final static class Error extends RuntimeException {
        Error(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

    final static class Assert extends RuntimeException {
        Assert(@Nullable final String message, @Nullable final Throwable throwable) {
            super(message, throwable);
        }
    }

}
