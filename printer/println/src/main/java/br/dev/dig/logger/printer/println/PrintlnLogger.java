package br.dev.dig.logger.printer.println;

import br.dev.dig.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class PrintlnLogger extends Logger {

    @NotNull
    private final Formatter formatter;
    @Nullable
    private final String tag;
    @NotNull
    private final LocalDateTime start = LocalDateTime.now();

    public PrintlnLogger(@Nullable final String tag, @NotNull final Formatter formatter) {
        super();
        this.tag = tag;
        this.formatter = formatter;
    }

    @NotNull
    @SuppressWarnings("unused")
    public static PrintlnLogger create(@Nullable final String tag) {
        return new PrintlnLogger(tag, Formatter.simple());
    }

    @NotNull
    @SuppressWarnings("unused")
    public static PrintlnLogger create(@Nullable final String tag, @NotNull final Formatter formatter) {
        return new PrintlnLogger(tag, formatter);
    }

    @NotNull
    @SuppressWarnings("unused")
    public static PrintlnLogger create(@Nullable final String tag, @NotNull final String format) {
        return new PrintlnLogger(tag, Formatter.parse(format));
    }

    @Override
    public void log(int level, @Nullable final CharSequence message, @Nullable final Throwable t) {
        final StringBuilder builder = new StringBuilder();
        for (final Formatter.Style style : formatter.styles) {
            builder.append(style.print(level, tag, start, message, t));
        }
        System.out.println(builder);
    }

    public static final class Formatter {
        private static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                .appendLiteral(':')
                .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
                .optionalStart()
                .appendFraction(ChronoField.MILLI_OF_SECOND, 3, 3, true)
                .toFormatter();

        public interface Style {
            @NotNull
            String print(int level, @Nullable String tag, @NotNull final LocalDateTime start, @Nullable final CharSequence message, @Nullable final Throwable t);
        }

        @NotNull
        public Iterable<Style> styles;

        public Formatter(@NotNull Iterable<Style> styles) {
            this.styles = styles;
        }

        public static Formatter simple() {
            final ArrayList<Style> styles = new ArrayList<>(6);
            styles.add(new ElapsedTime());
            styles.add(new Constant(" ["));
            styles.add(new TagShort());
            styles.add(new Constant("] "));
            styles.add(new Message());
            styles.add(new ThrowableMessage());
            return new Formatter(styles);
        }

        public static Formatter parse(@NotNull final String format) {
            final LinkedList<Style> styles = new LinkedList<>();
            final Pattern pattern = Pattern.compile("(\\$\\{[a-z\\-]+})");
            final Matcher matches = pattern.matcher(format);
            int lastEnd = 0;
            while (matches.find()) {
                if (lastEnd < matches.start()) {
                    styles.add(new Constant(format.substring(lastEnd, matches.start())));
                }
                lastEnd = matches.end();
                switch (matches.group()) {
                    case "${tag}":
                        styles.add(new Tag());
                        break;
                    case "${tag-short}":
                        styles.add(new TagShort());
                        break;
                    case "${date}":
                        styles.add(new CurrentDate());
                        break;
                    case "${time}":
                        styles.add(new CurrentTime());
                        break;
                    case "${elapsed}":
                        styles.add(new ElapsedTime());
                        break;
                    case "${message}":
                        styles.add(new Message());
                        break;
                    case "${throwable}":
                        styles.add(new ThrowableMessage());
                        break;
                    case "${stacktrace}":
                        styles.add(new StackTrace());
                        break;
                }
            }
            if (lastEnd + 1 < format.length()) {
                styles.add(new Constant(format.substring(lastEnd)));
            }
            return new Formatter(new ArrayList<>(styles));
        }

        public static final class Constant implements Style {
            @NotNull
            private final String value;

            public Constant(@NotNull final String value) {
                this.value = value;
            }

            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                return value;
            }
        }

        public static final class Tag implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                final String result;
                switch (level) {
                    case Logger.LEVEL_VERBOSE:
                        result = "VERBOSE";
                        break;
                    case Logger.LEVEL_DEBUG:
                        result = "DEBUG";
                        break;
                    case Logger.LEVEL_INFO:
                        result = "INFO";
                        break;
                    case Logger.LEVEL_WARNING:
                        result = "WARNING";
                        break;
                    case Logger.LEVEL_ERROR:
                        result = "ERROR";
                        break;
                    case Logger.LEVEL_ASSERT:
                        result = "ASSERT";
                        break;
                    default:
                        result = "";
                        break;
                }
                return result;
            }
        }

        public static final class TagShort implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                final String result;
                switch (level) {
                    case Logger.LEVEL_VERBOSE:
                        result = "V";
                        break;
                    case Logger.LEVEL_DEBUG:
                        result = "D";
                        break;
                    case Logger.LEVEL_INFO:
                        result = "I";
                        break;
                    case Logger.LEVEL_WARNING:
                        result = "W";
                        break;
                    case Logger.LEVEL_ERROR:
                        result = "E";
                        break;
                    case Logger.LEVEL_ASSERT:
                        result = "A";
                        break;
                    default:
                        result = "";
                        break;
                }
                return result;
            }
        }

        public static final class CurrentDate implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                return DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());
            }
        }

        public static final class CurrentTime implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                return ISO_LOCAL_TIME.format(LocalTime.now());
            }
        }

        public static final class ElapsedTime implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                final Duration diff = Duration.between(start, LocalDateTime.now());
                final LocalDateTime local = LocalDateTime.ofEpochSecond(diff.getSeconds(), diff.getNano(), ZoneOffset.UTC);
                // TODO: Check how 24h+ will be showed
                return ISO_LOCAL_TIME.format(local);
            }
        }

        public static final class Message implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                if (message == null) {
                    return "";
                }
                return message.toString();
            }
        }

        public static final class ThrowableMessage implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                if (t == null) {
                    return "";
                }
                final String msg = t.getMessage();
                if (msg == null) {
                    return t.getClass().getCanonicalName();
                }
                return t.getClass().getCanonicalName() + " - " + msg;
            }
        }

        public static final class StackTrace implements Style {
            @Override
            public @NotNull String print(int level, @Nullable String tag, @NotNull LocalDateTime start, @Nullable CharSequence message, @Nullable Throwable t) {
                if (t == null) {
                    return "";
                }
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                return sw.toString();
            }
        }
    }
}