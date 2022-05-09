package br.dev.dig.logger.app.android

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.dev.dig.logger.Logger
import br.dev.dig.logger.Union
import br.dev.dig.logger.builder.LoggerBuilder
import br.dev.dig.logger.printer.android_log.AndroidLogLogger
import br.dev.dig.logger.printer.firebase.FirebaseCrashlyticsLogger
import br.dev.dig.logger.printer.stub.StubLogger
import br.dev.dig.logger.printer.timber.TimberLogger
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _isAndroidLog = MutableLiveData(true)
    private val _isTimberLog = MutableLiveData(false)
    private val _isFirebaseLog = MutableLiveData(false)

    val isAndroidLog: LiveData<Boolean>
        get() = _isAndroidLog

    val isTimberLog: LiveData<Boolean>
        get() = _isTimberLog

    val isFirebaseLog: LiveData<Boolean>
        get() = _isFirebaseLog

    init {
        FirebaseApp.initializeApp(application)
    }

    fun updateTimberCheck(check: Boolean) {
        _isTimberLog.value = check
    }

    fun updateAndroidLogCheck(check: Boolean) {
        _isAndroidLog.value = check
    }

    fun updateFirebaseCheck(check: Boolean) {
        _isFirebaseLog.value = check
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
        get() = if (_isAndroidLog.value == true && _isTimberLog.value == true && _isFirebaseLog.value == true) {
            LoggerBuilder {
                Union {
                    add(
                        AndroidLogLogger(),
                        TimberLogger(),
                        FirebaseCrashlyticsLogger(FirebaseCrashlytics.getInstance())
                    )
                }
            }
        } else if (_isAndroidLog.value == true && _isTimberLog.value != true && _isFirebaseLog.value == true) {
            LoggerBuilder {
                Union {
                    add(
                        AndroidLogLogger(),
                        FirebaseCrashlyticsLogger(FirebaseCrashlytics.getInstance())
                    )
                }
            }
        } else if (_isAndroidLog.value != true && _isTimberLog.value == true && _isFirebaseLog.value == true) {
            LoggerBuilder {
                Union {
                    add(
                        TimberLogger(),
                        FirebaseCrashlyticsLogger(FirebaseCrashlytics.getInstance())
                    )
                }
            }
        } else if (_isAndroidLog.value != true && _isTimberLog.value != true && _isFirebaseLog.value == true) {
            LoggerBuilder {
                FirebaseCrashlyticsLogger(FirebaseCrashlytics.getInstance())
            }
        } else if (_isAndroidLog.value == true && _isTimberLog.value == true && _isFirebaseLog.value != true) {
            LoggerBuilder {
                Union {
                    add(
                        AndroidLogLogger(),
                        TimberLogger()
                    )
                }
            }
        } else if (_isAndroidLog.value == true && _isTimberLog.value != true && _isFirebaseLog.value != true) {
            LoggerBuilder {
                AndroidLogLogger()
            }
        } else if (_isAndroidLog.value != true && _isTimberLog.value == true && _isFirebaseLog.value != true) {
            LoggerBuilder {
                TimberLogger()
            }
        } else {
            LoggerBuilder {
                StubLogger()
            }
        }

}