package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

public abstract class LoggerBuilder {

    final private static String DEFAULT_LOGGER_TAG = "Log";

    @NotNull
    final private BaseLogger base = getBaseLogger();

    @Nullable
    private Logger common = null;

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

    public static final class StubLogger implements BaseLogger {
        @Override
        public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        }

        @Override
        public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        }
    }

}
