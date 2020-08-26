package com.binah

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.binah.api.ApiClient
import com.binah.data.Params
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ApiTest {

    private val TAG = "DTAG"

    @Test
    fun apiConnection() {
        val compositeDisposable = CompositeDisposable()
        val subscription = ApiClient.getClient.getQuestions(Params.getParamsSearch())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { objectsQueryResult ->

                Log.d(TAG, objectsQueryResult.items.size.toString())
                assertTrue(objectsQueryResult.items.isNotEmpty())
            }

        compositeDisposable.add(subscription)
        Thread.sleep(3000) //Wait 3 for testing puppose
    }
}