package io.mallinicouture.ui.bottom_navigation.fragment.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.mallinicouture.data.model.Advertisement;
import io.mallinicouture.data.model.Category;
import io.mallinicouture.data.repository.AdvertisementRepository;
import io.mallinicouture.data.repository.CategoryRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    private final CategoryRepository categoryRepository;
    private final AdvertisementRepository advertisementRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Category>> mCategories;
    private final MutableLiveData<List<Advertisement>> mAdvertisements;
    private final MutableLiveData<Boolean> loading;
    private final MutableLiveData<Boolean> loadingError;

    @Inject
    public HomeViewModel(CategoryRepository categoryRepository,
                         AdvertisementRepository advertisementRepository) {
        this.categoryRepository = categoryRepository;
        this.advertisementRepository = advertisementRepository;

        this.disposable = new CompositeDisposable();

        this.mCategories = new MutableLiveData<>();
        this.mAdvertisements = new MutableLiveData<>();
        this.loading = new MutableLiveData<>();
        this.loadingError = new MutableLiveData<>();

        fetchLatestData();
    }

    public LiveData<List<Category>> getCategories() {
        return mCategories;
    }

    public LiveData<List<Advertisement>> getAdvertisements() {
        return mAdvertisements;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Boolean> getLoadingError() {
        return loadingError;
    }

    public void fetchLatestData() {
        fetchCategories();
        fetchAdvertisements();
    }

    private void fetchCategories() {
        loading.setValue(true);
        disposable.add(categoryRepository
                .getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Category>>() {
                    @Override
                    public void onSuccess(@NonNull List<Category> categories) {
                        Log.i(TAG, "onSuccess: categories loaded :" + categories.toString());
                        loadingError.setValue(false);
                        mCategories.setValue(categories);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: can not load categories", e);
                        loadingError.setValue(true);
                        loading.setValue(false);
                    }
                })
        );
    }

    private void fetchAdvertisements() {
        loading.setValue(true);
        disposable.add(advertisementRepository
                .getAllAdvertisements().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Advertisement>>() {
                    @Override
                    public void onSuccess(@NonNull List<Advertisement> advertisements) {
                        Log.i(TAG, "onSuccess: advertisements loaded :" + advertisements.toString());
                        loadingError.setValue(false);
                        mAdvertisements.setValue(advertisements);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: can not load categories", e);
                        loadingError.setValue(true);
                        loading.setValue(false);
                    }
                })
        );
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
