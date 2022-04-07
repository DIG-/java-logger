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
            final ArrayList<Style> styles = new ArrayList<>(4);
            styles.add(new ElapsedTime());
            styles.add(new Constant(" - "));
            styles.add(new Message());
            styles.add(new ThrowableMessage());
            return new Formatter(styles);
        }

        public static Formatter parse(@NotNull final String format) {
            final LinkedList<Style> styles = new LinkedList<>();
            final Pattern pattern = Pattern.compile("(\\$\\{[a-z]+})");
            final Matcher matches = pattern.matcher(format);
            int lastEnd = 0;
            while (matches.find()) {
                if (lastEnd < matches.start()) {
                    styles.add(new Constant(format.substring(lastEnd, matches.start())));
                }
                lastEnd = matches.end();
                switch (matches.group()) {
                    case "${date}" -> styles.add(new CurrentDate());
                    case "${time}" -> styles.add(new CurrentTime());
                    case "${elapsed}" -> styles.add(new ElapsedTime());
                    case "${message}" -> styles.add(new Message());
                    case "${throwable}" -> styles.add(new ThrowableMessage());
                    case "${stacktrace}" -> styles.add(new StackTrace());
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