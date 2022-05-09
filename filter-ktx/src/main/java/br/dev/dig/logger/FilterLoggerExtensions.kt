package br.dev.dig.logger

import br.dev.dig.logger.builder.LoggerBuilder
import br.dev.dig.logger.filter.FilterLogger

@Suppress("unused", "FunctionName")
inline fun LoggerBuilder.Filter(crossinline config: FilterLogger.Builder.() -> Unit): FilterLogger {
    val builder = FilterLogger.Builder()
    config(builder)
    return builder.build()
}