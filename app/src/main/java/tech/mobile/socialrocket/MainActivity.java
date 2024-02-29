package tech.mobile.socialrocket;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import io.socket.client.IO;
import io.socket.client.Socket;
import tech.mobile.socialrocket.databinding.ActivityMainBinding;
import tech.mobile.socialrocket.di.AppContainer;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Socket socket;

    {
        try {
            socket = IO.socket("http://localhost:3001");
        } catch (URISyntaxException ignored) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppContainer appContainer = ((SocialRocketApplication) getApplication()).appContainer;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_chat)
                .build();

        socket.connect().on(Socket.EVENT_CONNECT, args ->{
            Log.i("WS", "onCreate: connected to server");
            socket.emit("message", "helloworld");
        } );


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


}