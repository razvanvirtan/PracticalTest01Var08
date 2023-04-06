package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context;
    String answer;

    public ProcessingThread(Context context, String answer) {
        this.answer = answer;
        this.context = context;
    }

    @Override
    public void run() {
        while(true){
            sendMessage(answer);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage(String answer) {
        Intent intent = new Intent();
        Random rand = new Random();

        StringBuilder builder = new StringBuilder(answer.length());
        for (int i = 0; i < answer.length(); i++) {
            builder.append("*");
        }
        int index = rand.nextInt(answer.length() - 0);
        builder.setCharAt(index, answer.charAt(index));
        String myString = builder.toString();

        intent.setAction("ro.pub.cs.systems.eim.practicaltest01var08.message");
        intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var08.data", myString);

        context.sendBroadcast(intent);
    }
}
