package br.dev.dig.logger.printer.formatter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleConstant;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentDate;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleElapsedTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleMessage;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleStackTrace;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTag;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTagShort;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleThrowableMessage;

public class LoggerFormatter {
    public interface Style {
        @NotNull
        String print(@NotNull final LocalDateTime start, int level, @Nullable String tag, @Nullable final CharSequence message, @Nullable final Throwable t);
    }

    @NotNull
    public Iterable<Style> styles;

    public LoggerFormatter(@NotNull Iterable<Style> styles) {
        this.styles = Intrinsics.parameterNotNull(styles, "Styles list must not be null");
    }

    public static LoggerFormatter parse(@NotNull final String format) {
        Intrinsics.parameterNotNull(format, "Format must not be null");
        final LinkedList<Style> styles = new LinkedList<>();
        final Pattern pattern = Pattern.compile("(\\$\\{[a-z\\-]+})");
        final Matcher matches = pattern.matcher(format);
        int lastEnd = 0;
        while (matches.find()) {
            if (lastEnd < matches.start()) {
                styles.add(new LoggerFormatStyleConstant(format.substring(lastEnd, matches.start())));
            }
            lastEnd = matches.end();
            switch (matches.group()) {
                case "${tag}":
                    styles.add(new LoggerFormatStyleTag());
                    break;
                case "${tag-short}":
                    styles.add(new LoggerFormatStyleTagShort());
                    break;
                case "${date}":
                    styles.add(new LoggerFormatStyleCurrentDate());
                    break;
                case "${time}":
                    styles.add(new LoggerFormatStyleCurrentTime());
                    break;
                case "${elapsed}":
                    styles.add(new LoggerFormatStyleElapsedTime());
                    break;
                case "${message}":
                    styles.add(new LoggerFormatStyleMessage());
                    break;
                case "${throwable}":
                    styles.add(new LoggerFormatStyleThrowableMessage());
                    break;
                case "${stacktrace}":
                    styles.add(new LoggerFormatStyleStackTrace());
                    break;
            }
        }
        if (lastEnd < format.length()) {
            styles.add(new LoggerFormatStyleConstant(format.substring(lastEnd)));
        }
        return new LoggerFormatter(new ArrayList<>(styles));
    }

    @NotNull
    public CharSequence format(@NotNull final LocalDateTime start, int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        Intrinsics.parameterNotNull(start, "Start LocalDateTime must not be null");
        final StringBuilder builder = new StringBuilder();
        for (final Style style : styles) {
            builder.append(style.print(start, level, tag, message, throwable));
        }
        return builder;
    }
}