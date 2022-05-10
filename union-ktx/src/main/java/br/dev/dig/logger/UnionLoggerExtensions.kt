package br.dev.dig.logger

import br.dev.dig.logger.builder.LoggerBuilder
import br.dev.dig.logger.union.UnionLogger

@Suppress("unused", "FunctionName")
inline fun LoggerBuilder.UnionLogger(crossinline config: UnionLogger.Builder.() -> Unit): UnionLogger {
    val builder = UnionLogger.Builder()
    config(builder)
    return builder.build()
}