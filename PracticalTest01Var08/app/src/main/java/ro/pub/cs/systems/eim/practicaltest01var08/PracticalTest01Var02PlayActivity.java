package ro.pub.cs.systems.eim.practicaltest01var08;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {
    String answer;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        Intent intent = getIntent();
        TextView riddleText = (TextView) findViewById(R.id.riddleText);
        if (intent != null) {
            riddleText.setText(intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var08.RIDDLE_KEY"));
            answer = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var08.ANSWER_KEY");
        }

        Button checkButton = (Button) findViewById(R.id.checkButton);
        Button backButton = (Button) findViewById(R.id.backButton);

        checkButton.setOnClickListener(new ListenerCheck());
        backButton.setOnClickListener(new ListenerBack());

        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var08", "ro.pub.cs.systems.eim.practicaltest01var08.PracticalTest01Var08Service"));
        serviceIntent.putExtra("ro.pub.cs.systems.eim.practicaltest01var08.practicaltest01var08service.answer", answer);
        startService(serviceIntent);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();

        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01var08.message");
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }

    class ListenerCheck implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText answerText = findViewById(R.id.answerText);

            if (answerText.getText().toString().equals(answer)) {
                Toast.makeText(getApplication(), "Correct answer!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), "Wrong answer!", Toast.LENGTH_LONG).show();
            }
        }
    }

    class ListenerBack implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var08", "ro.pub.cs.systems.eim.practicaltest01var08.PracticalTest01Var08Service"));
            stopService(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO: exercise 8d - stop the service
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var08", "ro.pub.cs.systems.eim.practicaltest01var08.PracticalTest01Var08Service"));
        stopService(intent);
        super.onDestroy();
    }

}