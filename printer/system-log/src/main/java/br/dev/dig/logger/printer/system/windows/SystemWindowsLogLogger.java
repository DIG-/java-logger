package br.dev.dig.logger.printer.system.windows;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.WinNT;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.util.Arrays;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleConstant;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleMessage;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTag;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleThrowableMessage;
import br.dev.dig.logger.printer.system.SystemLogLogger;

public class SystemWindowsLogLogger extends SystemLogLogger implements Closeable {

    private final static int STATUS_SEVERITY_WARNING = 0x02;
    private final static int STATUS_SEVERITY_SUCCESS = 0x00;
    private final static int STATUS_SEVERITY_INFORMATIONAL = 0x04;
    private final static int STATUS_SEVERITY_ERROR = 0x01;

    public static abstract class Formatter {
        private Formatter() {
        }

        public static @NotNull LoggerFormatter simple() {
            return new LoggerFormatter(Arrays.asList(
                new LoggerFormatStyleTag(),
                new LoggerFormatStyleConstant(": "),
                new LoggerFormatStyleMessage(),
                new LoggerFormatStyleConstant(" "),
                new LoggerFormatStyleThrowableMessage()
            ));
        }
    }

    @NotNull
    final WinNT.HANDLE handle;
    @NotNull
    final LoggerFormatter formatter;
    @NotNull
    final LocalDateTime start = LocalDateTime.now();

    public SystemWindowsLogLogger(@NotNull final String appName, @NotNull LoggerFormatter formatter) {
        Intrinsics.parameterNotNull(appName, "ApplicationName must not be null");
        this.formatter = Intrinsics.parameterNotNull(formatter, "Formatter must not be null");
        handle = Advapi32.INSTANCE.RegisterEventSource(null, appName);
        if (handle == null) {
            throw new RuntimeException(Kernel32Util.getLastErrorMessage());
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        Advapi32.INSTANCE.ReportEvent(
            handle,
            getWindowsErrorLevel(level),
            0,
            0,
            null,
            1,
            0,
            new String[]{formatter.format(start, level, tag, message, throwable).toString()},
            null
        );
    }

    @Override
    public void close() {
        Advapi32.INSTANCE.DeregisterEventSource(handle);
    }

    int getWindowsErrorLevel(final int level) {
        switch (level) {
            case Logger.LEVEL_VERBOSE:
            case Logger.LEVEL_DEBUG:
                return STATUS_SEVERITY_SUCCESS;
            case Logger.LEVEL_INFO:
                return STATUS_SEVERITY_INFORMATIONAL;
            case Logger.LEVEL_WARNING:
                return STATUS_SEVERITY_WARNING;
            case Logger.LEVEL_ERROR:
            case Logger.LEVEL_ASSERT:
                return STATUS_SEVERITY_ERROR;
        }
        return STATUS_SEVERITY_SUCCESS;
    }

}