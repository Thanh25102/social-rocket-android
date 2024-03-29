package tech.mobile.socialrocket.data.repo.interfaces;

import io.reactivex.rxjava3.core.Observable;
import tech.mobile.socialrocket.data.remote.model.Todo;

import java.util.List;

public interface TodoRepo {
    Observable<List<Todo>> getTodos();
}
