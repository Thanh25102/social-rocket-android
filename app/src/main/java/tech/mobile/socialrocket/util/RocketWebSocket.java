package tech.mobile.socialrocket.util;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class RocketWebSocket {
    private final WebSocket ws;
    private final RocketWebSocketListener listener;

    public RocketWebSocket(String host, String endpoint) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://" + host + "/" + endpoint).build();
        listener = new RocketWebSocketListener();
        this.ws = client.newWebSocket(request, listener);
    }

    public void send(String message) {
        if (listener.isConnected())
            ws.send(message);
        else {
            Log.e("WS", "Connection was closed !");
        }
    }

    public void close() {
        ws.close(1000, "Goodbye, World!");
    }
}
