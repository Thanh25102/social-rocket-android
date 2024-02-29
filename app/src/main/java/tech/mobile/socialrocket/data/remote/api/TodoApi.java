package tech.mobile.socialrocket.data.remote.api;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import tech.mobile.socialrocket.data.remote.model.Todo;

import java.util.List;

public interface TodoApi {
    @GET("/todos")
    Observable<List<Todo>> getTodos();
}

