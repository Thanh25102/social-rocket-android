package tech.mobile.socialrocket.di;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.mobile.socialrocket.data.remote.api.TodoApi;
import tech.mobile.socialrocket.data.repo.interfaces.TodoRepo;
import tech.mobile.socialrocket.data.repo.todo.TodoRepoImpl;

import java.util.concurrent.TimeUnit;

public class AppContainer {

    public final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
    );

    public final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor);

    public final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build();

    public final TodoApi todoApi = retrofit.create(TodoApi.class);
    public final TodoRepo todoRepo = new TodoRepoImpl(todoApi);
}
