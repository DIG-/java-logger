@file:Suppress("unused")

package br.dev.dig.logger.builder

import br.dev.dig.logger.BaseLogger

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