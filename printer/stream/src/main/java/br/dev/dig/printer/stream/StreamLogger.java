package br.dev.dig.printer.stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import br.dev.dig.logger.BaseLogger;
import br.dev.dig.logger.intrinsics.Intrinsics;
import br.dev.dig.logger.printer.formatter.LoggerFormatter;

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