package io.mallinicouture.ui.shoppingbasket;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShoppingBasketViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShoppingBasketViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}