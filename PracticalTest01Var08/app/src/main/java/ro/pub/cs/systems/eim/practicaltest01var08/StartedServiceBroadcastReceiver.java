package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class StartedServiceBroadcastReceiver extends BroadcastReceiver {


    // TODO: exercise 9 - default constructor
    public StartedServiceBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        String action = intent.getAction();
        String data = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var08.data");
        Toast.makeText(context, data, Toast.LENGTH_LONG).show();
    }
}
