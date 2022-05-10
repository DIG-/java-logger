package br.dev.dig.logger

import br.dev.dig.logger.async.AsyncLogger
import br.dev.dig.logger.builder.LoggerBuilder

@Suppress("unused", "FunctionName")
inline fun LoggerBuilder.AsyncLogger(crossinline config: AsyncLogger.Builder.() -> Unit): AsyncLogger {
    val builder = AsyncLogger.Builder()
    config(builder)
    return builder.build()
}