package io.mallinicouture.ui.bottom_navigation.fragment.favourite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class FavouriteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    @Inject
    public FavouriteViewModel() {
        // TODO: add repo
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}