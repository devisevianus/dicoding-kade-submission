package com.devis.foobatllapp.core.base

/**
 * Created by Devis on 27/09/20
 */

sealed class BaseViewState<out R> {
    object Loading: BaseViewState<Nothing>()
    data class Error(val errorMessage: String?): BaseViewState<Nothing>()
    data class Success<T>(val data: T?): BaseViewState<T>()
}