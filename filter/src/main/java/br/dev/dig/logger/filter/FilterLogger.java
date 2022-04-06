package br.dev.dig.logger.filter;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FilterLogger extends Logger {
    @NotNull
    final Logger parent;
    final int level;

    public FilterLogger(int minimum, @NotNull final Logger parent) {
        super();
        this.level = minimum;
        this.parent = parent;
    }

    @Override
    public void log(int level, @NotNull Message message, @Nullable Throwable t) {
        if (level >= this.level) {
            parent.log(level, message, t);
        }
    }

    @Override
    public void log(int level, @Nullable CharSequence message, @Nullable Throwable t) {
        if (level >= this.level) {
            parent.log(level, message, t);
        }
    }
}
