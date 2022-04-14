package br.dev.dig.logger.async;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class MockExecutor implements Executor {
    @Override
    public void execute(@NotNull Runnable command) {
        command.run();
    }
}
