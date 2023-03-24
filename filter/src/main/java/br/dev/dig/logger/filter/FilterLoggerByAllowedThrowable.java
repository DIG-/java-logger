package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import br.dev.dig.logger.BaseLogger;

class FilterLoggerByAllowedThrowable implements BaseLogger {

    @NotNull
    final BaseLogger target;
    @NotNull
    final Set<Class<? extends Throwable>> allowed;

    FilterLoggerByAllowedThrowable(@NotNull final BaseLogger target, @NotNull final Set<Class<? extends Throwable>> allowed) {
        this.target = target;
        this.allowed = allowed;
    }

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
        if (throwable != null) {
            for (Class<? extends Throwable> blocked : this.allowed) {
                if (blocked.isInstance(throwable)) {
                    target.log(level, tag, message, throwable);
                    return;
                }
            }
        } else {
            target.log(level, tag, message, null);
        }
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        if (throwable != null) {
            for (Class<? extends Throwable> blocked : this.allowed) {
                if (blocked.isInstance(throwable)) {
                    target.log(level, tag, message, throwable);
                    return;
                }
            }
        } else {
            target.log(level, tag, message, null);
        }
    }

}
