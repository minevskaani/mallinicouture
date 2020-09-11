package io.mallinicouture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.mallinicouture.data.model.User;
import io.mallinicouture.data.repository.UserRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<User> modelMutableLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<User> getModelMutableLiveData() {
        loadData();

        return modelMutableLiveData;
    }

    private void loadData() {
        /*
        disposable.add(userRepository
                .modelSingle()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User value) {
                        getModelMutableLiveData().setValue(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }
        ));
         */
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
