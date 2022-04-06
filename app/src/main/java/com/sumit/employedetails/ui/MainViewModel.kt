package com.sumit.employedetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.employedetails.models.ResponseData
import com.sumit.employedetails.models.Sales
import com.sumit.employedetails.models.SalesRegion
import com.sumit.employedetails.other.Resource
import com.sumit.employedetails.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _res = MutableLiveData<Resource<ResponseData>>()

    val res: LiveData<Resource<ResponseData>>
        get() = _res

    init {
        getSales()
    }

    private fun getSales() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getSales().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()?.responseData))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}