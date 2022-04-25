package br.dev.dig.logger.printer.system.unix;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Arrays;

import br.dev.dig.logger.Logger;
import br.dev.dig.logger.printer.println.PrintlnFormatter;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleConstant;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleMessage;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleThrowableMessage;
import br.dev.dig.logger.printer.system.SystemLogLogger;

public class SystemUnixLogLogger extends SystemLogLogger implements AutoCloseable {

    @SuppressWarnings("unused")
    public static abstract class Facility {
        private Facility() {
        }

        public static final int KERNEL = (0);    /* kernel messages */
        public static final int USER = (8);    /* random user-level messages */
        public static final int MAIL = (16);    /* mail system */
        public static final int DAEMON = (24);    /* system daemons */
        public static final int AUTH = (32);    /* security/authorization messages */
        public static final int SYSLOG = (40);    /* messages generated internally by syslogd */
        public static final int LPR = (48);    /* line printer subsystem */
        public static final int NEWS = (56);    /* network news subsystem */
        @SuppressWarnings("SpellCheckingInspection")
        public static final int UUCP = (64);    /* UUCP subsystem */
        public static final int CRON = (72);    /* clock daemon */
        public static final int AUTH_PRIVATE = (80);    /* security/authorization messages (private) */
        public static final int FTP = (88);    /* ftp daemon */
    }

    public static abstract class Formatter {
        private Formatter() {
        }

        public static PrintlnFormatter simple() {
            return new PrintlnFormatter(Arrays.asList(
                new PrintlnStyleMessage(),
                new PrintlnStyleConstant(" "),
                new PrintlnStyleThrowableMessage()
            ));
        }
    }

    @SuppressWarnings("unused")
    private static abstract class Level {
        private static final int LOG_EMERGENCY = 0;    /* system is unusable */
        private static final int LOG_ALERT = 1;    /* action must be taken immediately */
        private static final int LOG_CRITICAL = 2;    /* critical conditions */
        private static final int LOG_ERROR = 3;    /* error conditions */
        private static final int LOG_WARNING = 4;    /* warning conditions */
        private static final int LOG_NOTICE = 5;    /* normal but significant condition */
        private static final int LOG_INFO = 6;    /* informational */
        private static final int LOG_DEBUG = 7;    /* debug-level messages */
    }

    @NotNull
    final PrintlnFormatter formatter;
    @NotNull
    final LocalDateTime start = LocalDateTime.now();
    final int facility;

    @SuppressWarnings("ConstantConditions")
    public SystemUnixLogLogger(@NotNull final String appName, @NotNull PrintlnFormatter formatter, final int facility) {
        if (appName == null) {
            throw new InvalidParameterException("ApplicationName must not be null");
        }
        if (formatter == null) {
            throw new InvalidParameterException("Formatter must not be null");
        }
        if (facility < 0 || facility > 184) {
            throw new InvalidParameterException("Invalid facility range");
        } else if ((facility >> 3) << 3 != facility) {
            throw new InvalidParameterException("Invalid facility value");
        }

        this.formatter = formatter;
        this.facility = facility;
        GLibC.INSTANCE.openlog(appName, 0, facility);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        GLibC.INSTANCE.syslog(getSyslogLevel(level), formatter.format(start, level, tag, message, throwable).toString());
    }

    @Override
    public void close() {
        GLibC.INSTANCE.closelog();
    }

    int getSyslogLevel(final int level) {
        switch (level) {
            case Logger.LEVEL_VERBOSE:
            case Logger.LEVEL_DEBUG:
                return Level.LOG_DEBUG;
            case Logger.LEVEL_INFO:
                return Level.LOG_INFO;
            case Logger.LEVEL_WARNING:
                return Level.LOG_WARNING;
            case Logger.LEVEL_ERROR:
                return Level.LOG_ERROR;
            case Logger.LEVEL_ASSERT:
                return Level.LOG_CRITICAL;
        }
        return Level.LOG_DEBUG;
    }

}