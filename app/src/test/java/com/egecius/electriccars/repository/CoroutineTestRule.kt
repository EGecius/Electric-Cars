package com.egecius.electriccars.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return CoroutineStatement(base)
    }
}

class CoroutineStatement(private val base: Statement?) : Statement() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    override fun evaluate() {
        print("setting up")
        // setup
        Dispatchers.setMain(mainThreadSurrogate)
        // run the test
        base?.evaluate()
        // cleanup
        print("cleaning up")
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}