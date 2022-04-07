package br.dev.dig.logger.union;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UnionLogger extends Logger {

    @NotNull
    final LinkedList<Logger> loggers;

    protected UnionLogger(@NotNull final Collection<Logger> loggers) {
        super();
        if (loggers instanceof LinkedList) {
            this.loggers = (LinkedList<Logger>) loggers;
        } else {
            this.loggers = new LinkedList<>(loggers);
        }
    }

    public static UnionLogger create(@NotNull final Logger... loggers) {
        UnionLogger output = new UnionLogger(new LinkedList<>());
        for (final Logger logger : loggers) {
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

    public static UnionLogger create(@NotNull final Collection<Logger> loggers) {
        UnionLogger output = new UnionLogger(new LinkedList<>());
        for (final Logger logger : loggers) {
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

    public void add(@NotNull final Logger logger) {
        loggers.add(logger);
    }

    public void addAll(@NotNull final Collection<Logger> logger) {
        loggers.addAll(logger);
    }

    public boolean remove(@NotNull final Logger logger) {
        return loggers.remove(logger);
    }

    public List<Logger> getLoggers() {
        return loggers;
    }

    @Override
    public void log(int level, @NotNull Message message, @Nullable Throwable t) {
        for (final Logger logger : loggers) {
            logger.log(level, message, t);
        }
    }

    @Override
    public void log(int level, @Nullable CharSequence message, @Nullable Throwable t) {
        for (final Logger logger : loggers) {
            logger.log(level, message, t);
        }
    }
}
