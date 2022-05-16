package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public abstract class LoggerBuilder {

    protected String DEFAULT_LOGGER_TAG = "Log";
    protected String DEFAULT_LOGGER_TAG_SEPARATOR = "/";

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
    public final synchronized Logger getLogger(@NotNull final String tag, @NotNull final String... tags) {
        StringBuilder sb = new StringBuilder(tag);
        for (final String it : tags) {
            sb.append(DEFAULT_LOGGER_TAG_SEPARATOR);
            sb.append(it);
        }
        return getLogger(sb.toString());
    }

    @NotNull
    public final synchronized Logger getLogger(@NotNull final Logger logger, @NotNull final String... tags) {
        final String tag = logger.getTag();
        if (tag == null) {
            return getLogger(DEFAULT_LOGGER_TAG, tags);
        }
        return getLogger(tag, tags);
    }

    @NotNull
    protected abstract BaseLogger getBaseLogger();

    @NotNull
    protected Logger createLogger(@Nullable final String tag) {
        return new TaggedLogger(tag, base);
    }

}
