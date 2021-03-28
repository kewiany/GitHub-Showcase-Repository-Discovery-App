package xyz.kewiany.showcase

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import xyz.kewiany.showcase.di.mainModule

class ShowcaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShowcaseApp)
            modules(mainModule)
        }
    }
}