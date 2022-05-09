package br.dev.dig.printer.stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleConstant;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentDate;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleCurrentTime;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleMessage;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleTag;
import br.dev.dig.logger.printer.formatter.styles.LoggerFormatStyleThrowableMessage;

public class StreamLogger implements BaseLogger {

    @FunctionalInterface
    interface ExceptionCatcher {
        void onException(@NotNull final IOException exception, @NotNull final CharSequence message);
    }

    @NotNull
    final OutputStream stream;
    @NotNull
    final LoggerFormatter formatter;
    @Nullable
    final ExceptionCatcher catcher;
    @NotNull
    final LocalDateTime start = LocalDateTime.now();

    StreamLogger(@NotNull final OutputStream stream, @NotNull final LoggerFormatter formatter, @Nullable final ExceptionCatcher catcher) {
        this.stream = Intrinsics.parameterNotNull(stream, "Stream must not be null");
        this.formatter = Intrinsics.parameterNotNull(formatter, "Formatter must not be null");
        this.catcher = catcher;
    }

    @SuppressWarnings("unused")
    public static class Builder {
        @Nullable
        OutputStream stream;
        @Nullable
        LoggerFormatter formatter;
        @Nullable
        ExceptionCatcher catcher;

        public Builder() {
        }

        public Builder(@NotNull final OutputStream stream) {
            this.stream = stream;
        }

        @Nullable
        public OutputStream getStream() {
            return stream;
        }

        public Builder setStream(@NotNull final OutputStream stream) {
            this.stream = stream;
            return this;
        }

        @Nullable
        public LoggerFormatter getFormatter() {
            return formatter;
        }

        public Builder setFormatter(@NotNull final LoggerFormatter formatter) {
            this.formatter = formatter;
            return this;
        }

        @Nullable
        public ExceptionCatcher getCatcher() {
            return catcher;
        }

        public Builder setCatcher(@Nullable final ExceptionCatcher catcher) {
            this.catcher = catcher;
            return this;
        }

        @SuppressWarnings("ConstantConditions")
        public StreamLogger build() {
            return new StreamLogger(stream, Intrinsics.ifIsNull(formatter, Formatter::simple), catcher);
        }
    }

    public static abstract class Formatter {
        private Formatter() {
        }

        public static @NotNull LoggerFormatter simple() {
            return new LoggerFormatter(Arrays.asList(
                new LoggerFormatStyleCurrentDate(),
                new LoggerFormatStyleConstant(" "),
                new LoggerFormatStyleCurrentTime(),
                new LoggerFormatStyleConstant(" ["),
                new LoggerFormatStyleTag(),
                new LoggerFormatStyleConstant("]: "),
                new LoggerFormatStyleMessage(),
                new LoggerFormatStyleConstant(" "),
                new LoggerFormatStyleThrowableMessage()
            ));
        }
    }

    @Override
    public void log(int level, @Nullable String tag, @NotNull Message message, @Nullable Throwable throwable) {
        log(level, tag, message.generate(), throwable);
    }

    @Override
    public void log(int level, @Nullable String tag, @Nullable CharSequence message, @Nullable Throwable throwable) {
        final CharSequence formatted = formatter.format(start, level, tag, message, throwable);
        final ByteBuffer buffer = StandardCharsets.UTF_8.encode(CharBuffer.wrap(formatted));
        try {
            stream.write(buffer.array());
        } catch (IOException exception) {
            if (catcher != null) {
                catcher.onException(exception, formatted);
            }
        }
    }

}