package com.sumit.employedetails.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import java.net.InetAddress

/**
 * https://itnext.io/simplifying-jetpack-navigation-between-top-level-destinations-using-dagger-hilt-3d918721d91e
 * scoped viewmodel with Navigation component and Hilt
 */
inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModels(@IdRes navGraphIdRes: Int) =
    viewModels<T>(
        ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
        factoryProducer = { defaultViewModelProviderFactory }
    )

fun isInternetAvailable(): Boolean {
    return try {
        val ipAddr: InetAddress = InetAddress.getByName("google.com")
        //You can replace it with your name
        !ipAddr.equals("")
    } catch (e: Exception) {
        false
    }
}