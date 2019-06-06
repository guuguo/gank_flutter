package com.example.bianla_flutter.host

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.taobao.idlefish.flutterboost.FlutterBoostPlugin
import com.taobao.idlefish.flutterboost.interfaces.IPlatform
import io.flutter.app.FlutterApplication
import java.util.*

class TestApplication : FlutterApplication(), Application.ActivityLifecycleCallbacks {

    val instance by lazy { this }

    private val mActivityStack: Stack<Activity> = Stack()


    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {
        mActivityStack.push(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityStack.remove(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun getCurrentActivity(): Activity? {
        if (mActivityStack.isEmpty()) {
            return null
        }
        return mActivityStack.peek()
    }

    fun <T : Activity> getActivityByClass(clazz: Class<T>): T? {
        val activityListIterator = mActivityStack.listIterator()
        while (activityListIterator.hasNext()) {
            val activity = activityListIterator.next()
            if (activity == null) {
                activityListIterator.remove()
                continue
            }
            if (clazz == activity.javaClass) {
                return activity as T
            }
        }
        return null
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)

        FlutterBoostPlugin.init(object : IPlatform {
            override fun getApplication(): Application {
                return this@TestApplication
            }

            override fun getMainActivity(): Activity? {
                return instance.getActivityByClass(MainActivity::class.java)
            }

            override fun isDebug(): Boolean {
                return true
            }

            override fun startActivity(context: Context?, url: String?, params: MutableMap<Any?, Any?>?, requestCode: Int): Boolean {

                println("$context   $url    $params    $requestCode")

                return true
            }

            override fun getSettings(): MutableMap<Any?, Any?> {
                return mutableMapOf()
            }
        })
    }
}