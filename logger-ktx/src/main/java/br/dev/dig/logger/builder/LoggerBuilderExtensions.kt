@file:Suppress("unused")

package br.dev.dig.logger.builder

import br.dev.dig.logger.BaseLogger
import br.dev.dig.logger.Logger
import br.dev.dig.logger.style.LongLogger
import br.dev.dig.logger.style.SmallLogger

inline fun LoggerBuilder(crossinline logger: LoggerBuilder.() -> BaseLogger): LoggerBuilder =
    object : LoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return logger()
        }
    }

inline fun CachedLoggerBuilder(crossinline logger: CachedLoggerBuilder.() -> BaseLogger): CachedLoggerBuilder =
    object : CachedLoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return logger()
        }
    }

@Suppress("NOTHING_TO_INLINE")
inline fun <Builder : LoggerBuilder> Builder.getSmallLogger(tag: String? = null): SmallLogger =
    getLogger(tag)

val <Builder : LoggerBuilder> Builder.smallLogger: SmallLogger
    inline get() = logger

@Suppress("NOTHING_TO_INLINE")
inline fun <Builder : LoggerBuilder> Builder.getLongLogger(tag: String? = null): LongLogger =
    getLogger(tag)

val <Builder : LoggerBuilder> Builder.longLogger: LongLogger
    inline get() = logger

@Suppress("NOTHING_TO_INLINE")
inline fun <Builder : LoggerBuilder> Builder.logger(tag: String? = null): Logger =
    getLogger(tag)

@Suppress("NOTHING_TO_INLINE")
inline fun <Builder : LoggerBuilder> Builder.logger(tag: String, vararg tags: String): Logger =
    getLogger(tag, *tags)

@Suppress("NOTHING_TO_INLINE")
inline fun <Builder : LoggerBuilder> Builder.logger(logger: Logger, vararg tags: String): Logger =
    getLogger(logger, *tags)