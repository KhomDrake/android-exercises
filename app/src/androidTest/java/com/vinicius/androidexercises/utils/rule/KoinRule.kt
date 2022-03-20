package com.vinicius.androidexercises.utils.rule

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.vinicius.androidexercises.di.networkModule
import com.vinicius.androidexercises.di.repositoryModule
import com.vinicius.androidexercises.di.viewModelModule
import com.vinicius.androidexercises.remote.network.GithubApi
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class KoinRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    kotlin.runCatching {
                        startKoin {
                            androidContext(ApplicationProvider.getApplicationContext())
                            modules(listOf(
                                networkModule,
                                repositoryModule,
                                viewModelModule
                            ))
                        }

                        loadKoinModules(listOf(
                            module(override = true) {
                                single { mockk<GithubApi>(relaxed = true) }
                            }
                        ))
                    }.onFailure {
                        Log.i("Vini", it.message.toString())
                        loadKoinModules(listOf(
                            module(override = true) {
                                single { mockk<GithubApi>(relaxed = true) }
                            }
                        ))
                    }

                    base?.evaluate()
                } finally {
                    stopKoin()
                    unmockkAll()
                }
            }
        }
    }
}