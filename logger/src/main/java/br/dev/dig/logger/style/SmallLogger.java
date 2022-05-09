package br.dev.dig.logger.style;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import br.dev.dig.logger.Logger;

public interface SmallLogger {
    /* **********************************
                   VERBOSE
    *********************************** */
    void v(@NotNull final CharSequence message);

    void v(@NotNull final Throwable t);

    void v(@NotNull final Logger.Message message);

    void v(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void v(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                    DEBUG
    *********************************** */
    void d(@NotNull final CharSequence message);

    void d(@NotNull final Throwable t);

    void d(@NotNull final Logger.Message message);

    void d(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void d(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   INFO
    *********************************** */
    void i(@NotNull final CharSequence message);

    void i(@NotNull final Throwable t);

    void i(@NotNull final Logger.Message message);

    void i(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void i(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   WARNING
    *********************************** */
    void w(@NotNull final CharSequence message);

    void w(@NotNull final Throwable t);

    void w(@NotNull final Logger.Message message);

    void w(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void w(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   ERROR
    *********************************** */
    void e(@NotNull final CharSequence message);

    void e(@NotNull final Throwable t);

    void e(@NotNull final Logger.Message message);

    void e(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void e(@Nullable final CharSequence message, @Nullable final Throwable t);

    /* **********************************
                   WTF
    *********************************** */
    void wtf(@NotNull final CharSequence message);

    void wtf(@NotNull final Throwable t);

    void wtf(@NotNull final Logger.Message message);

    void wtf(@NotNull final Logger.Message message, @Nullable final Throwable t);

    void wtf(@Nullable final CharSequence message, @Nullable final Throwable t);

}
