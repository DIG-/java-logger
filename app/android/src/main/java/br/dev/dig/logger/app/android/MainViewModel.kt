package br.dev.dig.logger.app.android

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.dev.dig.logger.BaseLogger
import br.dev.dig.logger.Logger
import br.dev.dig.logger.builder.LoggerBuilder
import br.dev.dig.logger.printer.android_log.AndroidLogLogger
import br.dev.dig.logger.printer.timber.TimberLogger
import br.dev.dig.logger.union.UnionLogger

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _isAndroidLog = MutableLiveData(true)
    private val _isTimberLog = MutableLiveData(false)

    val isAndroidLog: LiveData<Boolean>
        get() = _isAndroidLog

    val isTimberLob: LiveData<Boolean>
        get() = _isTimberLog

    fun updateTimberCheck(check: Boolean) {
        _isTimberLog.value = check
    }

    fun updateAndroidLogCheck(check: Boolean) {
        _isAndroidLog.value = check
    }

    fun dispatchEvents() {
        val log = Logger.getInstance(builder)
        // or
        // val log = builder.getLogger();

        val handler = Handler(Looper.getMainLooper())
        log.debug("Test one")
        handler.postDelayed({
            log.w { "With lambda" }
            handler.postDelayed({
                try {
                    (null as String?)!!.isEmpty()
                } catch (e: NullPointerException) {
                    log.e("Catch exception", e)
                }
                log.i("Finish")
            }, 1000)
        }, 100)
    }

    private val builder: LoggerBuilder
        get() = if (_isAndroidLog.value == true && _isTimberLog.value == true) {
            builderBoth
        } else if (_isAndroidLog.value == true && _isTimberLog.value != true) {
            builderAndroid
        } else if (_isAndroidLog.value != true && _isTimberLog.value == true) {
            builderTimber
        } else {
            builderStub
        }


    private val builderStub = object : LoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return StubLogger()
        }
    }
    private val builderTimber = object : LoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return TimberLogger()
        }
    }
    private val builderAndroid = object : LoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return AndroidLogLogger()
        }
    }
    private val builderBoth = object : LoggerBuilder() {
        override fun getBaseLogger(): BaseLogger {
            return UnionLogger.create(TimberLogger(), AndroidLogLogger())
        }
    }

}