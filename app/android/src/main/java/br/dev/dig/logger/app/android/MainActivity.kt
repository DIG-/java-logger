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
        model.isTimberLob.observe(this) {
            bind.optTimber.isChecked = it
        }

        bind.optAndroidLog.setOnCheckedChangeListener { _, isChecked ->
            model.updateAndroidLogCheck(isChecked)
        }
        bind.optTimber.setOnCheckedChangeListener { _, isChecked ->
            model.updateTimberCheck(isChecked)
        }
        bind.triggerLog.setOnClickListener {
            model.dispatchEvents()
        }
    }

}