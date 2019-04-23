package com.kotlin.training.myapplication.mvp.base

interface MvpView {
    fun showLoader()
    fun hideLoader()
    fun showTechnicalError()
}