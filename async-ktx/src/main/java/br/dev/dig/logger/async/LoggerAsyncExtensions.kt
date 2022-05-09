package br.dev.dig.logger.async

import br.dev.dig.logger.builder.LoggerBuilder

@Suppress("unused", "FunctionName")
inline fun LoggerBuilder.Async(crossinline config: AsyncLogger.Builder.() -> Unit): AsyncLogger {
    val builder = AsyncLogger.Builder()
    config(builder)
    return builder.build()
}