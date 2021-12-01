import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1","2","3","4","5","6","7","8","9"};
    static String currentPlayer;

    // board is a 1d array initialized with numbers 1-9
    public static void main(String[] args){
        Scanner input  = new Scanner(System.in);
        currentPlayer = "X";
        boolean win = false;

        printCurrentBoard(board);

        while(win == false) {
            try{
                System.out.println("Enter slot: ");
                int slot = input.nextInt();

                if(slot > 9 || slot < 1) {
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter a valid input!");
                win = true;
            }

            
        }




        

        
        

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




