package tech.mobile.socialrocket.data.repo.todo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import tech.mobile.socialrocket.data.remote.api.TodoApi;
import tech.mobile.socialrocket.data.remote.model.Todo;
import tech.mobile.socialrocket.data.repo.interfaces.TodoRepo;

import java.util.List;

public class TodoRepoImpl implements TodoRepo {
    private final TodoApi todoApi;

    public TodoRepoImpl(TodoApi todoApi) {
        this.todoApi = todoApi;
    }

    @Override
    public Observable<List<Todo>> getTodos() {
        return todoApi
                .getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
