package com.kotlin.training.myapplication.mvp.base

interface BaseSubscriber<T> {
    /**
     * Handles successful success and accepts an object
     */
    fun onSuccess(t: T)

    /**
     * Handles failed request
     */
    fun onError()
}