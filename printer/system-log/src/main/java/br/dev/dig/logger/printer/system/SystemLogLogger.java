package br.dev.dig.logger.printer.system;

import com.sun.jna.Platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;
import br.dev.dig.logger.printer.stub.StubLogger;
import br.dev.dig.logger.printer.system.unix.SystemUnixLogLogger;
import br.dev.dig.logger.printer.system.windows.SystemWindowsLogLogger;

public abstract class SystemLogLogger implements BaseLogger {

    @SuppressWarnings("unused")
    public static class Builder {
        @NotNull
        String appName;
        @Nullable
        LoggerFormatter windowsFormatter;
        @Nullable
        LoggerFormatter unixFormatter;
        int unixFacility = SystemUnixLogLogger.Facility.USER;

        public Builder(@NotNull final String applicationName) {
            this.appName = Intrinsics.parameterNotNull(applicationName, "ApplicationName must not be null");
        }

        public Builder() {
            this("SimpleLogger");
        }

        @NotNull
        public String getAppName() {
            return appName;
        }

        public Builder setAppName(@NotNull final String appName) {
            this.appName = appName;
            return this;
        }

        @Nullable
        public LoggerFormatter getWindowsFormatter() {
            return windowsFormatter;
        }

        public Builder setWindowsFormatter(@NotNull final LoggerFormatter windowsFormatter) {
            this.windowsFormatter = windowsFormatter;
            return this;
        }

        @Nullable
        public LoggerFormatter getUnixFormatter() {
            return unixFormatter;
        }

        public Builder setUnixFormatter(@NotNull final LoggerFormatter unixFormatter) {
            this.unixFormatter = unixFormatter;
            return this;
        }

        public int getUnixFacility() {
            return unixFacility;
        }

        public Builder setUnixFacility(final int unixFacility) {
            this.unixFacility = unixFacility;
            return this;
        }

        public BaseLogger build() {
            if (Platform.isWindows()) {
                return new SystemWindowsLogLogger(appName, Intrinsics.ifIsNull(windowsFormatter, SystemWindowsLogLogger.Formatter::simple));
            } else if (Platform.isLinux() || Platform.isFreeBSD() || Platform.isOpenBSD() || Platform.isGNU()) {
                return new SystemUnixLogLogger(appName, Intrinsics.ifIsNull(unixFormatter, SystemUnixLogLogger.Formatter::simple), unixFacility);
            }
            return new StubLogger();
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

}