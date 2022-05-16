package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public abstract class LoggerBuilder {

    @VisibleForTesting
    protected String DEFAULT_LOGGER_TAG = "Log";

    @NotNull
    final private BaseLogger base = getBaseLogger();

    @Nullable
    @VisibleForTesting
    protected Logger common = null;

    @NotNull
    public final synchronized Logger getLogger() {
        if (common == null) {
            common = createLogger(DEFAULT_LOGGER_TAG);
        }
        return common;
    }

    @NotNull
    public final synchronized Logger getLogger(@Nullable final String tag) {
        if (tag == null || tag.isEmpty()) {
            return getLogger();
        }
        return createLogger(tag);
    }

    @NotNull
    protected abstract BaseLogger getBaseLogger();

    @NotNull
    protected Logger createLogger(@Nullable final String tag) {
        return new TaggedLogger(tag, base);
    }

}
