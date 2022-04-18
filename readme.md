Simple Logger for Java and Android
==================================
[![AppVeyor](https://img.shields.io/appveyor/build/DIG-/java-logger/main?logo=appveyor&logoColor=dddddd)](https://ci.appveyor.com/project/DIG-/java-logger/branch/main)
[![AppVeyor tests](https://img.shields.io/appveyor/tests/DIG-/java-logger/main?logo=appveyor&logoColor=dddddd)](https://ci.appveyor.com/project/DIG-/java-logger/branch/main)
[![Maven Central](https://img.shields.io/maven-central/v/br.dev.dig.logger/logger?label=maven)](https://search.maven.org/search?q=g:br.dev.dig.logger*)
[![License](https://img.shields.io/static/v1?label=license&message=CC%20BY-ND%204.0&color=blue)](https://creativecommons.org/licenses/by-nd/4.0/)

![Windows - Supported](https://img.shields.io/badge/windows-supported-success?logo=windows&logoColor=dddddd)
![Linux - Supported](https://img.shields.io/badge/linux-supported-success?logo=linux&logoColor=dddddd)
![MacOS - Partial](https://img.shields.io/badge/macos-partial-orange?logo=apple&logoColor=dddddd)

- Make log process easier
- Logs with scope (and tag)
- Reduced boilerplate
- Purely written in Java
- Integrate with others Log system

Reason
------
As an Android developer, **Timber** does not have restricted scope.

How to use
==========
1. Include maven central as repository
2. Choose, at least, one logger printer (`br.dev.dig.logger.printer`) and import into your project.

Simple log for Android:
```groovy
dependencies {
    ⋮
    implementation "br.dev.dig.logger.printer:android-log:${lastest_version}"
    ⋮
}

```
3. Create your own `LoggerBuilder` (can be a singleton)
```kotlin
object MainLoggerBuilder : LoggerBuilder() {
    override fun getBaseLogger(): BaseLogger {
        return AndroidLogLogger()
    }
}
```
4. Get `Logger` instance:
```kotlin
val log = MainLoggerBuilder.logger
// OR
val log = Logger.getInstance(MainLoggerBuilder)

// With tag:
val log = MainLogger.getLogger("tag")
// OR
val log = Logger.getInstance(MainLoggerBuilder, "tag")
```

Logger Printers
===============
Can be found in folder `printer` or doing some [maven search](https://search.maven.org/search?q=g:br.dev.dig.logger.printer) for group `br.dev.dig.logger.printer`.

| Package       | Class                 | Description |
|---------------|-----------------------|-------------|
| `stub`        | `StubLogger`          | Do nothing. |
| `println`     | `PrintLnLogger`       | Use `println` as output. Format can be customized. |
| `android-log` | `AndroidLogLogger`    | Use Android native Log as output. |
| `timber`      | `TimberLogger`        | Use Timber as output. |

Utilities
=========
Some utilities that can help or improve Log operations. All of them are under group `br.dev.dig.logger` ([search]((https://search.maven.org/search?q=g:br.dev.dig.logger))).

Utilities are chainable. Comparing to tree structure, Printers are leafs and Utilities are branch.

| Package       | Class                 | Description |
|---------------|-----------------------|-------------|
| `async`       | `AsyncLogger`         | Propagate log in other `Executor`|
| `filter`      | `FilterLogger`        | Propagate log with level above desired level |
| `union`       | `UnionLogger`         | Propagate log to more than one `BaseLogger` |

License
=======
[CC BY-ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/)
- You can use and re-dist freely.
- You can also modify, but only for yourself.
- You can use it as a part of your project, but without modifications in this project.