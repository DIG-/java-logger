package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

class TaggedLogger extends Logger {

    @NotNull
    @VisibleForTesting
    final LoggerBuilder builder;
    @Nullable
    @VisibleForTesting
    final String tag;
    @NotNull
    @VisibleForTesting
    final BaseLogger base;

    TaggedLogger(@NotNull LoggerBuilder builder, @Nullable final String tag, @NotNull final BaseLogger base) {
        super();
        this.builder = builder;
        this.tag = tag;
        this.base = base;
    }

    @Override
    public final @Nullable String getTag() {
        return tag;
    }

    @Override
    public @NotNull Logger tag(@NotNull final String... tags) {
        return builder.getLogger(this, tags);
    }

    @Override
    protected final @NotNull BaseLogger getLogger() {
        return base;
    }
}
