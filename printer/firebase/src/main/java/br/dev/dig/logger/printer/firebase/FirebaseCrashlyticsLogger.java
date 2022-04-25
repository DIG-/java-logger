package br.dev.dig.logger.printer.firebase;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.security.InvalidParameterException;
import java.util.Arrays;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public class FirebaseCrashlyticsLogger implements BaseLogger {

    @NotNull
    public final FirebaseCrashlytics crashlytics;
    private final boolean propagate;

    @SuppressWarnings("ConstantConditions")
    public FirebaseCrashlyticsLogger(@NotNull final FirebaseCrashlytics crashlytics, final boolean useThrowableAsCause) {
        if (crashlytics == null) {
            throw new InvalidParameterException("FirebaseCrashlytics must not be null");
        }
        this.crashlytics = crashlytics;
        this.propagate = useThrowableAsCause;
    }

    public FirebaseCrashlyticsLogger(@NotNull final FirebaseCrashlytics crashlytics) {
        this(crashlytics, false);
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        crashlytics.recordException(clearStackTrace(getExceptionByLevel(level, formatMessage(tag, message, throwable), propagate ? throwable : null)));
    }

    @NotNull
    String formatMessage(final @Nullable String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        final StringBuilder builder = new StringBuilder();
        if (tag != null && !tag.isEmpty()) {
            builder.append(tag).append(": ");
        }
        if (message != null && message.length() > 0) {
            builder.append(message);
        } else if (builder.length() > 0) {
            if (throwable != null) {
                builder.append(throwable);
            } else {
                builder.append("Empty message");
            }
        }
        if (!propagate && throwable != null) {
            appendThrowableCauses(builder, throwable);
        }
        if (builder.length() <= 0) {
            builder.append("Empty message");
        }
        return builder.toString();
    }

    void appendThrowableCauses(@NotNull final StringBuilder builder, @NotNull final Throwable start) {
        builder.append("\nCaused by:");
        Throwable cause = start;
        while (cause != null) {
            builder.append("\n  ").append(cause);
            // By JVM it is impossible, but I want warranty
            if (cause.getCause() == cause) {
                break;
            }
            cause = cause.getCause();
        }
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
