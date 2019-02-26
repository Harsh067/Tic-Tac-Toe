package hshukla067gmail.com.tic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
private Button start,exit;
private EditText player_1,player_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intViews();
      start.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,GameActivity.class);
              intent.putExtra("Player 1 name",player_1.getText().toString());
              intent.putExtra("Player 2 name",player_2.getText().toString());
              startActivity(intent);
          }
      });
    }

    private void intViews() {
        start = (Button) findViewById(R.id.startGamebtn);
        exit = (Button) findViewById(R.id.exitBtn);
        player_1 = (EditText) findViewById(R.id.e1);
        player_2 = (EditText) findViewById(R.id.e2);
    }
}
