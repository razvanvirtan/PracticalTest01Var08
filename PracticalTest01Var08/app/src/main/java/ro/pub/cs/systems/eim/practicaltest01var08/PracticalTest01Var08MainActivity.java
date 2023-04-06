package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        Button playButton = (Button) findViewById(R.id.Play);
        playButton.setOnClickListener(new Listener());
    }

    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText riddleText = (EditText) findViewById(R.id.Riddle);
            EditText answerText = (EditText) findViewById(R.id.Answer);

            if (riddleText.getText().toString().length() > 0 && answerText.getText().toString().length() > 0) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01var08.intent.action.PracticalTest01Var02PlayActivity");
                intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var08.RIDDLE_KEY", riddleText.getText().toString());
                intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var08.ANSWER_KEY", answerText.getText().toString());
                startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        EditText riddleText = (EditText) findViewById(R.id.Riddle);
        EditText answerText = (EditText) findViewById(R.id.Answer);

        savedInstanceState.putString("riddle", riddleText.getText().toString());
        savedInstanceState.putString("answer", answerText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText riddleText = (EditText) findViewById(R.id.Riddle);
        EditText answerText = (EditText) findViewById(R.id.Answer);

        if (savedInstanceState.getString("riddle") != null)
            riddleText.setText(savedInstanceState.getString("riddle"));

        if (savedInstanceState.getString("answer") != null)
            answerText.setText(savedInstanceState.getString("answer"));
    }
}