package br.dev.dig.logger.app.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.dev.dig.logger.app.android.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val bind by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model by lazy { defaultViewModelProviderFactory.create(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        Timber.plant(Timber.DebugTree())

        model.isAndroidLog.observe(this) {
            bind.optAndroidLog.isChecked = it
        }
        model.isTimberLog.observe(this) {
            bind.optTimber.isChecked = it
        }
        model.isFirebaseLog.observe(this) {
            bind.optFirebase.isChecked = it
        }

        bind.optAndroidLog.setOnCheckedChangeListener { _, isChecked ->
            model.updateAndroidLogCheck(isChecked)
        }
        bind.optTimber.setOnCheckedChangeListener { _, isChecked ->
            model.updateTimberCheck(isChecked)
        }
        bind.optFirebase.setOnCheckedChangeListener { _, isChecked ->
            model.updateFirebaseCheck(isChecked)
        }
        bind.triggerLog.setOnClickListener {
            model.dispatchEvents()
        }
    }

}