package com.kotlin.training.myapplication.mvp.base

interface BaseMvpView {
    fun showLoader()
    fun hideLoader()
    fun showTechnicalError()
}