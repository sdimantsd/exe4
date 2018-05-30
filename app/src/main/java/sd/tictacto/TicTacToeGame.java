package sd.tictacto;
import java.util.Random;

/**
 * Created by Shalom on 29-May-18.
 */

public class TicTacToeGame {
    private String[][] board = new String[3][3];
    private Random rand = new Random();
    private int numberOfFreeSpaces = 9;

    public TicTacToeGame(){
        StartNewGame();
    }

    public void StartNewGame(){ //inisilize the board
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = "";

        numberOfFreeSpaces = 9;
    }
    public void PlayTurn(int place, String player){
        int r = place / 3; //calculate the choosen cell
        int c = place % 3;

        board[r][c] = player;
        numberOfFreeSpaces--;
    }

    public int ComputerTurn(){ //Randomly selects one of the empty buttons, without any strategy
        int r = rand.nextInt(3);
        int c = rand.nextInt(3);

        while (board[r][c] != ""){
            r = rand.nextInt(3);
            c = rand.nextInt(3);
        }
        int squre = (r*3 + c);//  the square number that the computer choose
        PlayTurn(squre, "O");
        return squre; // return the square number that the computer choose
    }

    public String WhoIsTheWinner(){ //if the compuer win the function return -1, if the poerson win the function return +1, otherwith the function return 0


        for (int i = 0; i < 3; i++) { //check if in one of the rows there is a win situasion
            if (board[i][0] != "" && (board[i][0] == board[i][1] && board[i][0] == board[i][2]))
                return board[i][0];
        }

        for (int i = 0; i < 3; i++) { //check if in one of the colums there is a win situasion
            if (board[0][i] != "" && (board[0][i] == board[1][i] && board[0][i] == board[2][i]))
                return board[0][i];
        }

        if (board[0][0] != "" && (board[0][0] == board[1][1] && board[0][0] == board[2][2])) //check if in the slant there is a win situasion
            return board[0][0];

        if (board[0][2] != "" && (board[0][2] == board[1][1] && board[0][2] == board[2][0])) //check if in the slant there is a win situasion
            return board[0][2];

        if (numberOfFreeSpaces == 0)
            return "game over";

        return "";
    }
}
