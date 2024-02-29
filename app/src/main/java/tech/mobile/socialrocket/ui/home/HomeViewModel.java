package tech.mobile.socialrocket.ui.home;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;
import tech.mobile.socialrocket.data.remote.api.ApiService;
import tech.mobile.socialrocket.data.remote.model.TodoApiRes;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<List<TodoApiRes>> todos = new MutableLiveData<>();

    private Disposable disposable;
    private ObservableBoolean isSuccessful = new ObservableBoolean();


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public void getRemoteTodos() {
        Observer<List<TodoApiRes>> observer = new Observer<List<TodoApiRes>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                setDisposable(d);
            }

            @Override
            public void onNext(@NotNull List<TodoApiRes> todoApiRes) {
                handleResult(todoApiRes);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                handleError(e);
            }

            @Override
            public void onComplete() {
            }
        };
        ApiService.apiService.getTodos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void handleResult(List<TodoApiRes> todoApiRes) {
        todos.setValue(todoApiRes);
        isSuccessful.set(true);
    }

    public void handleError(Throwable throwable) {
        isSuccessful.set(false);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public Disposable getDisposable() {
        return disposable;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public MutableLiveData<List<TodoApiRes>> getTodos() {
        return todos;
    }

    public void setTodos(MutableLiveData<List<TodoApiRes>> todos) {
        this.todos = todos;
    }

    public ObservableBoolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(ObservableBoolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}