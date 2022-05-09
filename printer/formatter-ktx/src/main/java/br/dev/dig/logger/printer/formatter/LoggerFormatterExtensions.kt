@file:Suppress("unused")

package br.dev.dig.logger.printer.formatter

inline fun LoggerFormatter(crossinline config: LoggerFormatter.Builder.() -> Unit): LoggerFormatter {
    val builder = LoggerFormatter.Builder()
    config(builder)
    return builder.build()
}

@Suppress("NOTHING_TO_INLINE")
inline fun LoggerFormatter(format: String): LoggerFormatter =
    LoggerFormatter.Builder().add(format).build()

@Suppress("NOTHING_TO_INLINE")
inline fun LoggerFormatter(vararg format: LoggerFormatter.Style): LoggerFormatter =
    LoggerFormatter.Builder().add(*format).build()