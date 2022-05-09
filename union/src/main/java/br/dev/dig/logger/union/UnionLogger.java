package br.dev.dig.logger.union;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;

public final class UnionLogger implements BaseLogger {

    @NotNull
    private final ArrayList<BaseLogger> loggers;

    protected UnionLogger(@NotNull final List<BaseLogger> loggers) {
        this.loggers = new ArrayList<>(Intrinsics.parameterNotNull(loggers, "List of BaseLogger must not be null"));
    }

    @SuppressWarnings("unused")
    public static class Builder {
        final LinkedList<BaseLogger> loggers = new LinkedList<>();
        int hasUnion = 0;

        public Builder add(@NotNull final BaseLogger... loggers) {
            if (loggers.length == 0) {
                throw new InvalidParameterException("Requires at least one BaseLogger");
            }
            for (BaseLogger logger : loggers) {
                if (logger instanceof UnionLogger) hasUnion++;
                this.loggers.add(Intrinsics.parameterNotNull(logger, "BaseLogger must not be null"));
            }
            return this;
        }

        public Builder add(@NotNull Iterable<BaseLogger> loggers) {
            for (final BaseLogger logger : Intrinsics.parameterNotNull(loggers, "Iterable of BaseLogger must not be null")) {
                if (logger instanceof UnionLogger) hasUnion++;
                this.loggers.add(logger);
            }
            return this;
        }

        public Builder remove(@NotNull final BaseLogger... loggers) {
            if (loggers.length == 0) {
                throw new InvalidParameterException("Requires at least one BaseLogger");
            }
            for (BaseLogger logger : loggers) {
                if (this.loggers.contains(Intrinsics.parameterNotNull(logger, "BaseLogger must not be null"))) {
                    if (logger instanceof UnionLogger) hasUnion--;
                    this.loggers.remove(logger);
                }
            }
            return this;
        }

        public Builder remove(@NotNull final Iterable<BaseLogger> loggers) {
            for (BaseLogger logger : loggers) {
                if (this.loggers.contains(Intrinsics.parameterNotNull(logger, "BaseLogger must not be null"))) {
                    if (logger instanceof UnionLogger) hasUnion--;
                    this.loggers.remove(logger);
                }
            }
            return this;
        }

        // TODO: Remove recursive
        private void merge(@NotNull final LinkedList<BaseLogger> storage, @NotNull final Iterable<BaseLogger> loggers) {
            for (final BaseLogger logger : loggers) {
                if (logger instanceof UnionLogger) {
                    merge(storage, ((UnionLogger) logger).loggers);
                } else {
                    storage.add(logger);
                }
            }
        }

        public UnionLogger build() {
            if (hasUnion <= 0) {
                return new UnionLogger(loggers);
            }
            final LinkedList<BaseLogger> output = new LinkedList<>();
            merge(output, loggers);
            return new UnionLogger(output);
        }
    }

    @Deprecated
    // Will be removed in next major update, use builder
    public static UnionLogger create(@NotNull final BaseLogger... loggers) {
        return new Builder().add(loggers).build();
    }

    @Deprecated
    // Will be removed in next major update, use builder
    public static UnionLogger create(@NotNull final Collection<BaseLogger> loggers) {
        return new Builder().add(loggers).build();
    }

    @Deprecated
    // Will be removed in next major update, use builder
    public void add(@NotNull final BaseLogger logger) {
        loggers.add(logger);
    }

    @Deprecated
    // Will be removed in next major update, use builder
    public void addAll(@NotNull final Collection<BaseLogger> logger) {
        loggers.addAll(logger);
    }

    @Deprecated
    // Will be removed in next major update, use builder
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
