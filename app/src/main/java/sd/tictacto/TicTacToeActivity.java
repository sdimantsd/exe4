package sd.tictacto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity
        implements View.OnClickListener  {
    public static final String O_SIGN = "O";
    public static final String X_SIGN = "X";
    private Button btn[] = new Button[9];
    private TicTacToeGame board = new TicTacToeGame();
    private boolean gameOver = false; //if the game is over, the player can't click on any button

    private boolean CheckIfTheGameOver(){ //if the gamme is over return true
        String winner = board.WhoIsTheWinner();
        gameOver = false;
        switch (winner) {
            case "":
                gameOver = false;
                break;
            case "game over":
                Toast.makeText(this, "the game is over...",
                        Toast.LENGTH_SHORT).show();
                gameOver = true;
                break;
            default:
                Toast.makeText(this, winner + " is the winner!!!",
                        Toast.LENGTH_SHORT).show();
                gameOver = true;
                break;
        }
        return gameOver;

    }
    @Override public void onClick(View v) {
        if (gameOver)//if the game is over, the player can't click on any button
            return;
        Button b = (Button)v;
        if (X_SIGN.contentEquals(b.getText()) || O_SIGN.contentEquals(b.getText())) //check if it's clicked button,
            return;

        b.setText(X_SIGN);
        board.PlayTurn(b.getId(), X_SIGN);
        if (CheckIfTheGameOver()) //if the player win, the computer not need to play
            return;

        int place = board.ComputerTurn(); //computer turn
        btn[place].setText(O_SIGN);
        CheckIfTheGameOver();

    }

    private void StartNewGame(){ //reset all data
        for (int i = 0 ; i < 9; i++)
            btn[i].setText("");
        board.StartNewGame();
        gameOver = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        Button btnStartNewGame = (Button) findViewById(R.id.btnStartNewGame);
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

