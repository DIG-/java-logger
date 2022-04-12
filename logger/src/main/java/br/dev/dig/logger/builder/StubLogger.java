package br.dev.dig.logger.builder;

import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.Logger;

class StubLogger extends Logger {
    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
    }

    @Override
    protected @Nullable String getTag() {
        return null;
    }
}
