package com.ifarmer.movielist.data.datasource.model

data class BaseDataModel<T>(
    var status: Boolean,
    var message: String?,
    var data: T?
)
