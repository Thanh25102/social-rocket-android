package tech.mobile.socialrocket.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private final MutableLiveData<Boolean> socketStatus;

    public ChatViewModel() {
        socketStatus = new MutableLiveData<>(false);
        mText = new MutableLiveData<>();
        mText.setValue("This is chat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}
