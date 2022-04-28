package com.safi.dicegame.ui.setupGame.gameTypeChooser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.safi.base.BaseViewModel;

public class GameTypeChooserViewModel extends BaseViewModel {
    private final MutableLiveData<Integer> _penalty = new MutableLiveData<>();
    public final LiveData<Integer> penalty = _penalty;

    public void onGameType1Clicked() {
        _penalty.postValue(0);
    }

    public void onGameType2Clicked() {
        _penalty.postValue(-1);
    }
}
