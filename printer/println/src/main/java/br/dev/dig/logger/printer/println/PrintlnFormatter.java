package br.dev.dig.logger.printer.println;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.dev.dig.logger.printer.println.styles.PrintlnStyleConstant;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleCurrentDate;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleCurrentTime;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleElapsedTime;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleMessage;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleTag;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleTagShort;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleThrowableMessage;
import br.dev.dig.logger.printer.println.styles.PrintlnStyleStackTrace;

public class PrintlnFormatter {

    public interface Style {
        @NotNull
        String print(int level, @Nullable String tag, @NotNull final LocalDateTime start, @Nullable final CharSequence message, @Nullable final Throwable t);
    }

    @NotNull
    public Iterable<Style> styles;

    public PrintlnFormatter(@NotNull Iterable<Style> styles) {
        this.styles = styles;
    }

    public static PrintlnFormatter simple() {
        final ArrayList<Style> styles = new ArrayList<>(6);
        styles.add(new PrintlnStyleElapsedTime());
        styles.add(new PrintlnStyleConstant(" ["));
        styles.add(new PrintlnStyleTagShort());
        styles.add(new PrintlnStyleConstant("] "));
        styles.add(new PrintlnStyleMessage());
        styles.add(new PrintlnStyleThrowableMessage());
        return new PrintlnFormatter(styles);
    }

    public static PrintlnFormatter parse(@NotNull final String format) {
        final LinkedList<Style> styles = new LinkedList<>();
        final Pattern pattern = Pattern.compile("(\\$\\{[a-z\\-]+})");
        final Matcher matches = pattern.matcher(format);
        int lastEnd = 0;
        while (matches.find()) {
            if (lastEnd < matches.start()) {
                styles.add(new PrintlnStyleConstant(format.substring(lastEnd, matches.start())));
            }
            lastEnd = matches.end();
            switch (matches.group()) {
                case "${tag}":
                    styles.add(new PrintlnStyleTag());
                    break;
                case "${tag-short}":
                    styles.add(new PrintlnStyleTagShort());
                    break;
                case "${date}":
                    styles.add(new PrintlnStyleCurrentDate());
                    break;
                case "${time}":
                    styles.add(new PrintlnStyleCurrentTime());
                    break;
                case "${elapsed}":
                    styles.add(new PrintlnStyleElapsedTime());
                    break;
                case "${message}":
                    styles.add(new PrintlnStyleMessage());
                    break;
                case "${throwable}":
                    styles.add(new PrintlnStyleThrowableMessage());
                    break;
                case "${stacktrace}":
                    styles.add(new PrintlnStyleStackTrace());
                    break;
            }
        }
        if (lastEnd + 1 < format.length()) {
            styles.add(new PrintlnStyleConstant(format.substring(lastEnd)));
        }
        return new PrintlnFormatter(new ArrayList<>(styles));
    }

    @NotNull
    public CharSequence format(@NotNull final LocalDateTime start, int level, @Nullable final String tag, @Nullable final CharSequence message, @Nullable final Throwable throwable) {
        final StringBuilder builder = new StringBuilder();
        for (final PrintlnFormatter.Style style : styles) {
            builder.append(style.print(level, tag, start, message, throwable));
        }
        return builder;
    }

}
