package br.dev.dig.logger.union;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import br.dev.dig.logger.BaseLogger;

public final class UnionLogger implements BaseLogger {

    @NotNull
    private final LinkedList<BaseLogger> loggers;

    @SuppressWarnings("ConstantConditions")
    protected UnionLogger(@NotNull final LinkedList<BaseLogger> loggers) {
        if (loggers == null) {
            throw new InvalidParameterException("List of BaseLogger must not be null");
        }
        this.loggers = loggers;
    }

    public static UnionLogger create(@NotNull final BaseLogger... loggers) {
        UnionLogger output = new UnionLogger(new LinkedList<>());
        for (final BaseLogger logger : loggers) {
            if (logger instanceof UnionLogger) {
                final UnionLogger union = (UnionLogger) logger;
                union.addAll(output.loggers);
                output = union;
            } else {
                output.add(logger);
            }
        }
        return output;
    }

    public static UnionLogger create(@NotNull final Collection<BaseLogger> loggers) {
        UnionLogger output = new UnionLogger(new LinkedList<>());
        for (final BaseLogger logger : loggers) {
            if (logger instanceof UnionLogger) {
                final UnionLogger union = (UnionLogger) logger;
                union.addAll(output.loggers);
                output = union;
            } else {
                output.add(logger);
            }
        }
        return output;
    }

    public void add(@NotNull final BaseLogger logger) {
        loggers.add(logger);
    }

    public void addAll(@NotNull final Collection<BaseLogger> logger) {
        loggers.addAll(logger);
    }

    public boolean remove(@NotNull final BaseLogger logger) {
        return loggers.remove(logger);
    }

    public List<BaseLogger> getLoggers() {
        return loggers;
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        for (final BaseLogger logger : loggers) {
            logger.log(level, tag, message, throwable);
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        for (final BaseLogger logger : loggers) {
            logger.log(level, tag, message, throwable);
        }
    }
}
