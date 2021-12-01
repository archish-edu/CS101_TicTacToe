import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1","2","3","4","5","6","7","9","9"};
    static String player;

    // board is a 1d array initialized with numbers 1-9
    public static void main(String[] args){
        Scanner input  = new Scanner(System.in);
        printCurrentBoard(board);

        

        
        

    }
    public static void printCurrentBoard(String[] board){
        System.out.println();
        System.out.println(" "+board[0]+" "+"|" + " "+board[1]+" "+"|" + " "+board[2]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[3]+" "+"|" + " "+board[4]+" "+"|" + " "+board[5]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[6]+" "+"|" + " "+board[7]+" "+"|" + " "+board[8]+" ");
        System.out.println();

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




