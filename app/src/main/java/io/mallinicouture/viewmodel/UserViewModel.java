package io.mallinicouture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.mallinicouture.model.UserModel;
import io.mallinicouture.repository.UserRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<UserModel> modelMutableLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<UserModel> getModelMutableLiveData() {
        loadData();

        return modelMutableLiveData;
    }

    private void loadData() {
        disposable.add(userRepository
                .modelSingle()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    @Override
                    public void onSuccess(UserModel value) {
                        getModelMutableLiveData().setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }
        ));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
