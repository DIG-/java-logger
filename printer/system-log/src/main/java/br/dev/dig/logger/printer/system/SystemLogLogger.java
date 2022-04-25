package br.dev.dig.logger.printer.system;

import com.sun.jna.Platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;
import br.dev.dig.logger.printer.stub.StubLogger;
import br.dev.dig.logger.printer.system.windows.WindowsLogLogger;

public abstract class SystemLogLogger implements BaseLogger {

    @SuppressWarnings("unused")
    public static class Builder {
        @Nullable
        String appName;
        @NotNull
        PrintlnFormatter windowsFormatter = WindowsLogLogger.Formatter.simple();

        @Nullable
        public String getAppName() {
            return appName;
        }

        public Builder setAppName(@NotNull final String appName) {
            this.appName = appName;
            return this;
        }

        @NotNull
        public PrintlnFormatter getWindowsFormatter() {
            return windowsFormatter;
        }

        public Builder setWindowsFormatter(@NotNull final PrintlnFormatter windowsFormatter) {
            this.windowsFormatter = windowsFormatter;
            return this;
        }

        @SuppressWarnings("ConstantConditions")
        public BaseLogger build() {
            if (Platform.isWindows()) {
                return new WindowsLogLogger(appName, windowsFormatter);
            }
            return new StubLogger();
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

}