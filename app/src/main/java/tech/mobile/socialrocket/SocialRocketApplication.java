package tech.mobile.socialrocket;

import android.app.Application;
import tech.mobile.socialrocket.di.AppContainer;

public class SocialRocketApplication extends Application {
    private static SocialRocketApplication instance;
    public AppContainer appContainer = new AppContainer();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static SocialRocketApplication getInstance() {
        return  instance;
    }
}
