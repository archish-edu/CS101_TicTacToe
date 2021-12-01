import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1","2","3","4","5","6","7","9","9"};
    static String player;

    // board is a 1d array initialized with numbers 1-9
    public static void main(String[] args){
        Scanner input  = new Scanner(System.in);

        

        
        

    }
    public static void printBoard(String[] board){
        System.out.println(" "+board[0]+" "+"|");
    }
    
    


}

class cpu {
    int predictBestMove(String[][] board){
        int place = 0; // will return from 1-9 if valid move available

        // if 2 in a row already, place in 3rd spot
        // else if 1 placed already, place in 2nd spot
        // else place in first available random spot 

        return place;


    }

}




