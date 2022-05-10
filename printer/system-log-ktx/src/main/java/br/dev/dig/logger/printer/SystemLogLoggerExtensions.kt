package br.dev.dig.logger.printer

import br.dev.dig.logger.BaseLogger
import br.dev.dig.logger.builder.LoggerBuilder
import br.dev.dig.logger.printer.system.SystemLogLogger

@Suppress("unused", "FunctionName")
inline fun LoggerBuilder.SystemLogLogger(config: SystemLogLogger.Builder.() -> Unit): BaseLogger {
    val builder = SystemLogLogger.Builder()
    config(builder)
    return builder.build()
}