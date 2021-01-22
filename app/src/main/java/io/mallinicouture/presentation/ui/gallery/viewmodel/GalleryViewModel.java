package io.mallinicouture.presentation.ui.gallery.viewmodel;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Dress;
import io.mallinicouture.data.repository.DressRepository;
import io.mallinicouture.presentation.ui.gallery.model.GalleryCardItem;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class GalleryViewModel extends ViewModel {

    private final DressRepository dressRepository;

    private CompositeDisposable disposable;

    private final MutableLiveData<List<Dress>> mDresses;
    private final MutableLiveData<List<GalleryCardItem>> mGalleryDresses;
    private final MutableLiveData<Boolean> loading;
    private final MutableLiveData<Boolean> loadingError;

    private final MutableLiveData<Long> categoryId;
    private final String BUNDLE_CATEGORY_ID_KEY = "category_id";

    @Inject
    public GalleryViewModel(DressRepository dressRepository) {
        this.dressRepository = dressRepository;

        this.disposable = new CompositeDisposable();

        this.mDresses = new MutableLiveData<>();
        this.mGalleryDresses = new MutableLiveData<>();
        this.loading = new MutableLiveData<>();
        this.loadingError = new MutableLiveData<>();

        this.categoryId = new MutableLiveData<>();
    }

    public LiveData<List<Dress>> getDresses() {
        return mDresses;
    }

    public LiveData<List<GalleryCardItem>> getGalleryDresses() {
        return mGalleryDresses;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Boolean> getLoadingError() {
        return loadingError;
    }

    public void setCategoryId(Long categoryId) {

    }

    public void saveToBundle(Bundle outState) {
        if (categoryId.getValue() != null) {
            outState.putLong(BUNDLE_CATEGORY_ID_KEY, categoryId.getValue());
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if (categoryId.getValue() == null &&
            savedInstanceState != null &&
            savedInstanceState.containsKey(BUNDLE_CATEGORY_ID_KEY)) {
            categoryId.setValue(savedInstanceState.getLong(BUNDLE_CATEGORY_ID_KEY));
        }
    }

    public void onCategoryIdChange() {
    }

    private void fetchDresses() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
