package br.dev.dig.logger.style;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.Logger;

public interface LongLogger {
    /* **********************************
                   VERBOSE
    *********************************** */
    void verbose(@NotNull final CharSequence message);

    void verbose(@NotNull final Throwable t);

    void verbose(@NotNull final Logger.Message message);

    void verbose(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void verbose(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                    DEBUG
    *********************************** */
    void debug(@NotNull final CharSequence message);

    void debug(@NotNull final Throwable t);

    void debug(@NotNull final Logger.Message message);

    void debug(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void debug(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   INFO
    *********************************** */
    void info(@NotNull final CharSequence message);

    void info(@NotNull final Throwable t);

    void info(@NotNull final Logger.Message message);

    void info(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void info(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   WARNING
    *********************************** */
    void warning(@NotNull final CharSequence message);

    void warning(@NotNull final Throwable t);

    void warning(@NotNull final Logger.Message message);

    void warning(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void warning(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   ERROR
    *********************************** */
    void error(@NotNull final CharSequence message);

    void error(@NotNull final Throwable t);

    void error(@NotNull final Logger.Message message);

    void error(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void error(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   WTF
    *********************************** */
    void wtf(@NotNull final CharSequence message);

    void wtf(@NotNull final Throwable t);

    void wtf(@NotNull final Logger.Message message);

    void wtf(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void wtf(@Nullable final CharSequence message, @Nullable final Throwable t);

}
