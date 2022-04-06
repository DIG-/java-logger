package br.dev.dig.logger.builder;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.Nullable;

class StubLogger extends Logger {
    @Override
    public void log(int level, @Nullable CharSequence message, @Nullable Throwable t) {
    }
}
