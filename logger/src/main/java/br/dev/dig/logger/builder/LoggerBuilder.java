package br.dev.dig.logger.builder;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class LoggerBuilder {

    private LoggerBuilder() {
        throw new RuntimeException("Can not instantiate " + getClass().getCanonicalName());
    }

    @FunctionalInterface
    public interface LoggerCreator {
        @NotNull Logger create(@Nullable final String tag);
    }

    @Nullable
    private static HashMap<String, Logger> loggers;
    @Nullable
    private static Logger defaultLogger;
    @NotNull
    private static LoggerCreator creator = tag -> new StubLogger();

    public static synchronized Logger getLogger() {
        if (defaultLogger == null) {
            defaultLogger = creator.create(null);
        }
        return defaultLogger;
    }

    public static synchronized Logger getLogger(@Nullable final String tag) {
        if (tag == null || tag.isEmpty()) {
            return getLogger();
        }
        if (loggers == null) {
            loggers = new HashMap<>();
        }
        if (loggers.containsKey(tag)) {
            return loggers.get(tag);
        }

        final Logger logger = creator.create(tag);
        loggers.put(tag, logger);
        return logger;
    }

    public static synchronized void setCreator(@NotNull final LoggerCreator creator) {
        LoggerBuilder.creator = creator;
        defaultLogger = null;
    }

    @SuppressWarnings("unused")
    public static synchronized void setLogger(@NotNull final Logger logger) {
        setLogger(logger, null);
    }

    public static synchronized void setLogger(@NotNull final Logger logger, @Nullable String tag) {
        if (tag == null || tag.isEmpty()) {
            defaultLogger = logger;
            return;
        }
        if (loggers == null) {
            loggers = new HashMap<>();
        }
        loggers.put(tag, logger);
    }

}
