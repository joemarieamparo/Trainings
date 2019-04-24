package com.kotlin.training.myapplication.mvp.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

open class BasePresenter<T : BaseMvpView> {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun <V> observe(observable: Observable<V>, subscriber: BaseSubscriber<V>) {
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<V>() {
                override fun onComplete() { }

                override fun onNext(value: V) {
                    subscriber.onSuccess(value)
                }

                override fun onError(e: Throwable) {
                    subscriber.onError()
                }
            })
        compositeDisposable.add(disposable)
    }
}