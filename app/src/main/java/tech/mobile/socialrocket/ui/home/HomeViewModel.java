package tech.mobile.socialrocket.ui.home;

import android.annotation.SuppressLint;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import io.reactivex.rxjava3.disposables.Disposable;
import tech.mobile.socialrocket.SocialRocketApplication;
import tech.mobile.socialrocket.data.remote.model.Todo;
import tech.mobile.socialrocket.data.repo.interfaces.TodoRepo;
import tech.mobile.socialrocket.di.AppContainer;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final TodoRepo todoRepo;
    private final MutableLiveData<String> mText;
    private MutableLiveData<List<Todo>> todos = new MutableLiveData<>();

    private Disposable disposable;
    private ObservableBoolean isSuccessful = new ObservableBoolean();


    public HomeViewModel(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    @SuppressLint("CheckResult")
    public void getRemoteTodos() {
        todoRepo.getTodos().subscribe(
                todosRes -> {
                    todos.setValue(todosRes);
                    isSuccessful.set(true);
                },
                throwable -> isSuccessful.set(false)
        );
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

    public MutableLiveData<List<Todo>> getTodos() {
        return todos;
    }

    public void setTodos(MutableLiveData<List<Todo>> todos) {
        this.todos = todos;
    }

    public ObservableBoolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(ObservableBoolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    static ViewModelInitializer<HomeViewModel> Initializer = new ViewModelInitializer<>(
            HomeViewModel.class,
            creationExtras -> {
                AppContainer appContainer = SocialRocketApplication.getInstance().appContainer;
                return new HomeViewModel(appContainer.todoRepo);
            }
    );
}