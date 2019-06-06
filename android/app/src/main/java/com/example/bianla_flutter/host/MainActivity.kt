package com.example.bianla_flutter.host

import com.taobao.idlefish.flutterboost.containers.BoostFlutterActivity
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : BoostFlutterActivity() {

    override fun getContainerParams(): MutableMap<Any?, Any?> {
        return mutableMapOf()
    }

    override fun onRegisterPlugins(registry: PluginRegistry?) {
        GeneratedPluginRegistrant.registerWith(registry);
    }

    override fun getContainerName(): String {
        return "test"
    }
}
