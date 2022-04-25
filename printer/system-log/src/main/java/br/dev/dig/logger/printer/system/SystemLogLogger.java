package br.dev.dig.logger.printer.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.printer.stub.StubLogger;

public abstract class SystemLogLogger implements BaseLogger {

    public static BaseLogger create(@NotNull final String applicationName) {
        return new StubLogger();
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

}