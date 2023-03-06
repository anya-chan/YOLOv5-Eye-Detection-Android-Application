package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.LongStream;

public class ResultActivity extends AppCompatActivity {
    TextView averageResult;
    TextView minimumResult;
    TextView maximumResult;

    TextView ratioSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        averageResult = (TextView) findViewById(R.id.averageResult);
        minimumResult = (TextView) findViewById(R.id.minimumResult);
        maximumResult = (TextView) findViewById(R.id.maximumResult);
        ratioSize = (TextView) findViewById(R.id.ratioSize);

        Log.e("Original averageResult", (String) averageResult.getText());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Float> object = (ArrayList<Float>) args.getSerializable("ARRAYLIST");
        Log.e("Size",Integer.toString(object.size()));
        ratioSize.append(Integer.toString(object.size()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            object.stream().max(Comparator.comparing(Float::floatValue))
                    .ifPresent(e -> maximumResult.append(Float. toString(e)));
            object.stream().min(Comparator.comparing(Float::floatValue))
                    .ifPresent(e -> minimumResult.append(Float. toString(e)));
            float result = object.stream().reduce(Float.valueOf(0), (a , b) -> a+b);
            result = result/object.size();
            averageResult.append(Float. toString(result));
        }
    }

    public void callStartActivity(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}