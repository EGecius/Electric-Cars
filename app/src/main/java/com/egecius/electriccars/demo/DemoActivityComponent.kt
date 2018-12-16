package com.egecius.electriccars.demo

import dagger.Component

@Component(modules = arrayOf(DemoActivityModule::class))
interface DemoActivityComponent {

    fun injectInto(demoActivity: DemoActivity)
}
