package com.safi.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDirections;

import com.safi.base.util.SingleEvent;

abstract public class BaseViewModel extends ViewModel {
    MutableLiveData<SingleEvent<NavDirections>> navigationEvent = new MutableLiveData<>();
    MutableLiveData<SingleEvent<Exception>> exceptionEvent = new MutableLiveData<>();

    protected void navigate(NavDirections navDirections) {
        navigationEvent.postValue(new SingleEvent<>(navDirections));
    }

    protected void showException(Exception exception) {
        exceptionEvent.postValue(new SingleEvent<>(exception));
    }
}
