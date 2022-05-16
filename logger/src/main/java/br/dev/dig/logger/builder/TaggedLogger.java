package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

class TaggedLogger extends Logger {

    @Nullable
    @VisibleForTesting
    final String tag;
    @NotNull
    @VisibleForTesting
    final BaseLogger base;

    TaggedLogger(@Nullable final String tag, @NotNull final BaseLogger base) {
        super();
        this.tag = tag;
        this.base = base;
    }

    @Override
    public final @Nullable String getTag() {
        return tag;
    }

    @Override
    protected final @NotNull BaseLogger getLogger() {
        return base;
    }
}
