package br.dev.dig.logger.printer.system.windows;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.WinNT;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.time.LocalDateTime;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;
import br.dev.dig.logger.printer.system.SystemLogLogger;

public class WindowsLogLogger extends SystemLogLogger implements Closeable {

    private final static int STATUS_SEVERITY_WARNING = 0x2;
    private final static int STATUS_SEVERITY_SUCCESS = 0x0;
    private final static int STATUS_SEVERITY_INFORMATIONAL = 0x1;
    private final static int STATUS_SEVERITY_ERROR = 0x3;

    @NotNull
    final WinNT.HANDLE handle;
    @NotNull
    final PrintlnFormatter formatter;
    @NotNull
    final LocalDateTime start = LocalDateTime.now();

    public WindowsLogLogger(@NotNull final String appName, @NotNull PrintlnFormatter formatter) {
        handle = Advapi32.INSTANCE.RegisterEventSource(null, appName);
        if (handle == null) {
            throw new RuntimeException(Kernel32Util.getLastErrorMessage());
        }
        this.formatter = formatter;
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