package hshukla067gmail.com.tic;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
private TextView player1TV,player2TV;
private Button reset;
private Button[][] btns = new Button[3][3];

private int Player1Pts=0;
private int Player2Pts=0;
private int roundCount=0;
private Boolean Player1Turn=true;
private String player1Name,player2Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initViews();
        player1Name = getIntent().getStringExtra("Player 1 name");
        player2Name = getIntent().getStringExtra("Player 2 name");
        player1TV.setText(player1Name+"(X): 0");
        player2TV.setText(player2Name+"(O): 0");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restBoard();
            }
        });
    }

    private void restBoard() {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                btns[i][j].setText("");
                btns[i][j].setEnabled(true);
            }
        }
        roundCount=0;
        Player1Turn=true;
    }

    private void initViews() {
        player1TV=findViewById(R.id.p1);
        player2TV=findViewById(R.id.p2);
        reset=findViewById(R.id.resetBtn);
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String id_name="btn_"+i+j;
                int Btnid=this.getResources().getIdentifier(id_name,"id",getPackageName());
                btns[i][j]=findViewById(Btnid);
                btns[i][j].setOnClickListener(this);
            }
        }
    }
    @Override
    public void onClick(View v){
        if(Player1Turn){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(this.getResources().getColor(R.color.colorGreen));
            ((Button)v).setEnabled(false);
            Player1Turn=!Player1Turn;
        }
        else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(this.getResources().getColor(R.color.colorRed));
            ((Button)v).setEnabled(false);
            Player1Turn=!Player1Turn;
        }
        if(checkForWin()){
            if(Player1Turn){
                Player1Wins();
            }
            else if(!Player1Turn){
                Player2Wins();
            }
            else if(roundCount==9){
                draw();
            }
            else{
                roundCount++;
                Player1Turn=!Player1Turn;
            }
        }
    }

    private void draw() {
        Toast.makeText(this,"Match draw",Toast.LENGTH_SHORT).show();
        askForAnotherGame("Match Draw");
    }

    private void Player2Wins() {
        Player2Pts++;
        updatePointText();
        askForAnotherGame(player2Name+"Wins!");
    }

    private void updatePointText() {
     player1TV.setText(player1Name+":"+Player1Pts);
     player2TV.setText(player2Name+":"+Player2Pts);
    }

    private void Player1Wins() {
        Player1Pts++;
        updatePointText();
        askForAnotherGame(player1Name+"Wins!");
    }

    private void askForAnotherGame(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(s);
        builder.setMessage("Play Again??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restBoard();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(GameActivity.this,MainActivity.class));
            }
        });
        AlertDialog AlertDialogue=builder.create();
        AlertDialogue.setCancelable(false);
        AlertDialogue.setCanceledOnTouchOutside(false);
        AlertDialogue.show();
    }

    private boolean checkForWin() {
        String field[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = btns[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !(field[i][0].equals(""))) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !(field[0][i].equals(""))) {
                return true;
            }
        }

            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !(field[0][0].equals(""))) {
                return true;
            }


            if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&!(field[0][2].equals(""))){
                return true;
            }
           return false;
    }
}
