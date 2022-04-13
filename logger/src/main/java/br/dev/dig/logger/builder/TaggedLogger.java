package br.dev.dig.logger.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.Logger;

class TaggedLogger extends Logger {

    @Nullable
    private final String tag;
    @NotNull
    private final BaseLogger base;

    TaggedLogger(@Nullable final String tag, @NotNull final BaseLogger base) {
        super();
        this.tag = tag;
        this.base = base;
    }

    @Override
    protected final @Nullable String getTag() {
        return tag;
    }

    @Override
    protected final @NotNull BaseLogger getLogger() {
        return base;
    }
}