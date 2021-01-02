package com.example.sharedpreferences

abstract class BasePresenter<V : BaseView> constructor(private val view: V)