package tech.mobile.socialrocket.ui.home;

import tech.mobile.socialrocket.SocialRocketApplication;
import tech.mobile.socialrocket.data.repo.interfaces.TodoRepo;
import tech.mobile.socialrocket.di.AppContainer;
import tech.mobile.socialrocket.ui.Factory;

public class HomeViewModelFactory implements Factory<HomeViewModel> {

    private final SocialRocketApplication application;

    public HomeViewModelFactory(SocialRocketApplication application) {
        this.application = application;
    }

    @Override
    public HomeViewModel create() {
        AppContainer appContainer = application.appContainer;
        TodoRepo repo = appContainer.todoRepo;
        return new HomeViewModel(repo);
    }
}
