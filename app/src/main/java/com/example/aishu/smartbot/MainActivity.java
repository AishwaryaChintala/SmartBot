package com.example.aishu.smartbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements  AIListener{
    AIService aiService;
    TextView t1;
    TextView t2;



   public void buttonClicked(View view){
       aiService.startListening();


   }

   public void onAudioLevel(float level){}
    public void onListeningStarted(){}
    public void onListeningCanceled(){}

    public void onResult(AIResponse result)
    {
       Log.d("aish",result.toString());
       Result result1=result.getResult();
        t1.setText("Query "+result1.getResolvedQuery());
        t2.setText("speech: "+result1.getFulfillment().getSpeech());


    }

    @Override
    public void onError(AIError error) {

    }

    @Override

    public void onListeningFinished() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1= (TextView) findViewById(R.id.textView2);
        t2=(TextView) findViewById(R.id.textView4);
        final AIConfiguration config = new AIConfiguration("439d4f5c45cd43f5b7e3eded0591a14d",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService= AIService.getService(this, config);
        aiService.setListener(this);
    }
}
