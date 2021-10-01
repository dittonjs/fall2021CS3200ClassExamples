package com.usu.mvvmcounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    // Our model...
    MutableLiveData<Integer> count = new MutableLiveData<>();

    public CountViewModel() {
        count.postValue(0);
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    // Command
    public void incrementCount() {
        count.postValue(count.getValue() + 1);
    }

    // Command
    public void decrementCount() {
        count.postValue(count.getValue() - 1);
    }

    public void clear() {
        count.postValue(0);
    }
}
