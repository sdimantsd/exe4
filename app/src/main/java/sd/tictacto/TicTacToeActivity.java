package sd.tictacto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity
        implements View.OnClickListener  {
    private Button btn[] = new Button[9];
    private Button btnStartNewGame;
    private TicTacToeGame board = new TicTacToeGame();
    private boolean GameOver = false; //if the game is over, the player can't click on any button

    private boolean CheckIfTheGameOver(){ //if the gamme is over return true
        String winner = board.WhoIsTheWinner();
        GameOver = false;
        if (winner == "")
            GameOver = false;
        else if (winner == "game over"){
            Toast.makeText(this, "the game is over...",
                    Toast.LENGTH_SHORT).show();
            GameOver = true;
        }
        else {
            Toast.makeText(this, winner + " is the winner!!!",
                    Toast.LENGTH_SHORT).show();
            GameOver = true;
        }
        return GameOver;

    }
    @Override public void onClick(View v) {
        if (GameOver)//if the game is over, the player can't click on any button
            return;
        Button b = (Button)v;
        if (b.getText() == "X" || b.getText() == "O") //check if it's clicked button,
            return;

        b.setText("X");
        board.PlayTurn(b.getId(), "X");
        if (CheckIfTheGameOver()) //if the player win, the computer not need to play
            return;

        int place = board.ComputerTurn(); //computer turn
        btn[place].setText("O");
        CheckIfTheGameOver();

    }

    private void StartNewGame(){ //reset all data
        for (int i = 0 ; i < 9; i++)
            btn[i].setText("");
        board.StartNewGame();
        GameOver = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        btnStartNewGame = (Button)findViewById(R.id.btnStartNewGame);
        btnStartNewGame.setOnClickListener(
            new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener to btnStartNewGame on click
                Button b = (Button)v;
                StartNewGame();
            }
            });
        btn[0] = (Button)findViewById(R.id.btn1);
        btn[1] = (Button)findViewById(R.id.btn2);
        btn[2] = (Button)findViewById(R.id.btn3);
        btn[3] = (Button)findViewById(R.id.btn4);
        btn[4] = (Button)findViewById(R.id.btn5);
        btn[5] = (Button)findViewById(R.id.btn6);
        btn[6] = (Button)findViewById(R.id.btn7);
        btn[7] = (Button)findViewById(R.id.btn8);
        btn[8] = (Button)findViewById(R.id.btn9);
        for (int i = 0 ; i < 9; i++){
            btn[i].setId(i); //to identify the clicked button
            btn[i].setOnClickListener(this);
        }
    };

}

