package tech.mobile.socialrocket.data.repo.interfaces;

import tech.mobile.socialrocket.data.remote.model.TodoApiRes;

import java.util.List;

public interface TodoRepo {

    List<TodoApiRes> getTodos();
}
