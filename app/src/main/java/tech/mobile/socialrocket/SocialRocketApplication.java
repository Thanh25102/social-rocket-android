package tech.mobile.socialrocket;

import android.app.Application;
import tech.mobile.socialrocket.di.AppContainer;

public class SocialRocketApplication extends Application {
    public AppContainer appContainer = new AppContainer();
}
