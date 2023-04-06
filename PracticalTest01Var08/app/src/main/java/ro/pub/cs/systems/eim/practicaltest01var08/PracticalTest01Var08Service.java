package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO: exercise 5 - implement and start the ProcessingThread
        String answer = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var08.practicaltest01var08service.answer");
        new ProcessingThread(getApplicationContext(), answer).start();

        return START_REDELIVER_INTENT;
    }
}