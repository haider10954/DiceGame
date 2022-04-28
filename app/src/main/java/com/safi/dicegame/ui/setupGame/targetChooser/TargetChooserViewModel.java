package com.safi.dicegame.ui.setupGame.targetChooser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.safi.base.BaseViewModel;

public class TargetChooserViewModel extends BaseViewModel {
    public final MutableLiveData<String> uiTarget = new MutableLiveData<>();
    private final MutableLiveData<Integer> _target = new MutableLiveData<>();
    public final LiveData<Integer> target = _target;
    public LiveData<Boolean> isNextButtonEnabled = Transformations.map(uiTarget, input -> {
        try {
            int t = Integer.parseInt(input);
            return t >= 6 && t <= 26;
        } catch (Exception ignored) {
        }
        return false;
    });

    public void onNextClicked() {
        String target = this.uiTarget.getValue();
        if (target == null) {
            return;
        }
        try {
            int t = Integer.parseInt(target);
            _target.postValue(t);
            navigate(TargetChooserFragmentDirections.toGameTypeChooserFragment());
        } catch (Exception ignored) {
        }
    }
}
