package br.dev.dig.logger;

import br.dev.dig.logger.builder.LoggerBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public abstract class Logger {

    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_WARNING = 4;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_ASSERT = 6;
    public static final int LEVEL_NONE = 7;

    @FunctionalInterface
    public interface Message {
        @NotNull CharSequence generate();
    }

    @NotNull
    public static Logger getInstance() {
        return LoggerBuilder.getLogger();
    }

    @NotNull
    public static Logger getInstance(@NotNull final String tag) {
        return LoggerBuilder.getLogger(tag);
    }

    public static void setCreator(@NotNull final LoggerBuilder.LoggerCreator creator) {
        LoggerBuilder.setCreator(creator);
    }

    /* **********************************
                   VERBOSE
    *********************************** */
    public void verbose(@NotNull final CharSequence message) {
        verbose(message, null);
    }

    public void verbose(@NotNull final Throwable t) {
        verbose((CharSequence) null, t);
    }

    public void verbose(@NotNull final Message message) {
        verbose(message, null);
    }

    public void verbose(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_VERBOSE, message, t);
    }

    public void verbose(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_VERBOSE, message, t);
    }

    //  Reduced
    public final void v(@NotNull final CharSequence message) {
        verbose(message);
    }

    public final void v(@NotNull final Throwable t) {
        verbose(t);
    }

    public final void v(@NotNull final Message message) {
        verbose(message);
    }

    public final void v(@NotNull final Message message, @Nullable final Throwable t) {
        verbose(message, t);
    }

    public final void v(@Nullable final CharSequence message, @Nullable final Throwable t) {
        verbose(message, t);
    }

    /* **********************************
                    DEBUG
    *********************************** */
    public void debug(@NotNull final CharSequence message) {
        debug(message, null);
    }

    public void debug(@NotNull final Throwable t) {
        debug((CharSequence) null, t);
    }

    public void debug(@NotNull final Message message) {
        debug(message, null);
    }

    public void debug(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_DEBUG, message, t);
    }

    public void debug(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_DEBUG, message, t);
    }

    //  Reduced
    public final void d(@NotNull final CharSequence message) {
        debug(message);
    }

    public final void d(@NotNull final Throwable t) {
        debug(t);
    }

    public final void d(@NotNull final Message message) {
        debug(message);
    }

    public final void d(@NotNull final Message message, @Nullable final Throwable t) {
        debug(message, t);
    }

    public final void d(@Nullable final CharSequence message, @Nullable final Throwable t) {
        debug(message, t);
    }

    /* **********************************
                   INFO
    *********************************** */
    public void info(@NotNull final CharSequence message) {
        info(message, null);
    }

    public void info(@NotNull final Throwable t) {
        info((CharSequence) null, t);
    }

    public void info(@NotNull final Message message) {
        info(message, null);
    }

    public void info(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_INFO, message, t);
    }

    public void info(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_INFO, message, t);
    }

    //  Reduced
    public final void i(@NotNull final CharSequence message) {
        info(message);
    }

    public final void i(@NotNull final Throwable t) {
        info(t);
    }

    public final void i(@NotNull final Message message) {
        info(message);
    }

    public final void i(@NotNull final Message message, @Nullable final Throwable t) {
        info(message, t);
    }

    public final void i(@Nullable final CharSequence message, @Nullable final Throwable t) {
        info(message, t);
    }

    /* **********************************
                   WARNING
    *********************************** */
    public void warning(@NotNull final CharSequence message) {
        warning(message, null);
    }

    public void warning(@NotNull final Throwable t) {
        warning((CharSequence) null, t);
    }

    public void warning(@NotNull final Message message) {
        warning(message, null);
    }

    public void warning(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_WARNING, message, t);
    }

    public void warning(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_WARNING, message, t);
    }

    //  Reduced
    public final void w(@NotNull final CharSequence message) {
        warning(message);
    }

    public final void w(@NotNull final Throwable t) {
        warning(t);
    }

    public final void w(@NotNull final Message message) {
        warning(message);
    }

    public final void w(@NotNull final Message message, @Nullable final Throwable t) {
        warning(message, t);
    }

    public final void w(@Nullable final CharSequence message, @Nullable final Throwable t) {
        warning(message, t);
    }

    /* **********************************
                   ERROR
    *********************************** */
    public void error(@NotNull final CharSequence message) {
        error(message, null);
    }

    public void error(@NotNull final Throwable t) {
        error((CharSequence) null, t);
    }

    public void error(@NotNull final Message message) {
        error(message, null);
    }

    public void error(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_ERROR, message, t);
    }

    public void error(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_ERROR, message, t);
    }

    //  Reduced
    public final void e(@NotNull final CharSequence message) {
        error(message);
    }

    public final void e(@NotNull final Throwable t) {
        error(t);
    }

    public final void e(@NotNull final Message message) {
        error(message);
    }

    public final void e(@NotNull final Message message, @Nullable final Throwable t) {
        error(message, t);
    }

    public final void e(@Nullable final CharSequence message, @Nullable final Throwable t) {
        error(message, t);
    }

    /* **********************************
                   WTF
    *********************************** */
    public void wtf(@NotNull final CharSequence message) {
        wtf(message, null);
    }

    public void wtf(@NotNull final Throwable t) {
        wtf((CharSequence) null, t);
    }

    public void wtf(@NotNull final Message message) {
        wtf(message, null);
    }

    public void wtf(@NotNull final Message message, @Nullable final Throwable t) {
        log(LEVEL_ASSERT, message, t);
    }

    public void wtf(@Nullable final CharSequence message, @Nullable final Throwable t) {
        log(LEVEL_ASSERT, message, t);
    }

    protected void log(int level, @NotNull final Message message, @Nullable final Throwable t) {
        log(level, message.generate(), t);
    }

    protected abstract void log(int level, @Nullable final CharSequence message, @Nullable final Throwable t);

}
