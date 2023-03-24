package br.dev.dig.logger.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import br.dev.dig.logger.BaseLogger;

class FilterLoggerByBlockedThrowable implements BaseLogger {

    @NotNull
    final BaseLogger target;
    @NotNull
    final Set<Class<? extends Throwable>> blocked;

    FilterLoggerByBlockedThrowable(@NotNull final BaseLogger target, @NotNull final Set<Class<? extends Throwable>> blocked) {
        this.target = target;
        this.blocked = blocked;
    }

    @Override
    public void log(int level, @Nullable final String tag, @NotNull final Message message, @Nullable final Throwable throwable) {
        if (throwable != null) {
            for (Class<? extends Throwable> blocked : this.blocked) {
                if (blocked.isInstance(throwable)) {
                    return;
                }
            }
        }
        target.log(level, tag, message, throwable);
    }

    @Override
    public void log(int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        if (throwable != null) {
            for (Class<? extends Throwable> blocked : this.blocked) {
                if (blocked.isInstance(throwable)) {
                    return;
                }
            }
        }
        target.log(level, tag, message, throwable);
    }

}
