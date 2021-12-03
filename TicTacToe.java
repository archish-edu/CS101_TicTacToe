import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1","2","3","4","5","6","7","8","9"};
    static boolean playerX;

    // board is a 1d array initialized with numbers 1-9
    public static void main(String[] args){
        Scanner input  = new Scanner(System.in);
        playerX = true;
        boolean win = false;
        int numTurns = 0;

        printCurrentBoard(board);

        while(win == false) {
            try{
                System.out.println("Enter slot: ");
                int slot = input.nextInt();

                if(slot > 9 || slot < 1) {
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    continue;
                }
                if (numTurns > 7) {
                    printCurrentBoard(board);
                    System.out.println("Draw! No more possible moves.");
                    break;
                }
                // x turn
                if(playerX == true) {

                    if(board[slot-1].equals(String.valueOf(slot))) {
                        board[slot-1] = "X";
                        playerX = false;
                        printCurrentBoard(board);
                        if(potentialDiagonalWin(board) == true || potentialHorizontalWin(board) == true || potentialVerticalWin(board) == true) {
                            System.out.println("X wins!"); 
                            break; 
                        }
                        numTurns += 1;
                        // call win checker here!
                    } else {
                        System.out.println("Try again! Slot is already taken.");
                    }
                // o turn
                } else if (playerX == false) {
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        board[slot-1] = "O";
                        playerX = true;
                        printCurrentBoard(board);
                        if(potentialDiagonalWin(board) == true || potentialHorizontalWin(board) == true || potentialVerticalWin(board) == true) {
                            System.out.println("O wins!"); 
                            break; 
                        }
                        numTurns += 1;
                    } else {
                        System.out.println("Try again! Slot is already taken.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter a valid input!");
            }

            
        }



    
    //public static boolean potentialHorizontalWin()
    //public static boolean potentialDiagonalWin()
        
        

    }
    public static boolean potentialVerticalWin(String[] board) {
        // checks for vertical win
        String[] winArray = new String[3];
        winArray[0] = board[0]+board[3]+board[6];
        winArray[1] = board[1]+board[4]+board[7];
        winArray[2] = board[2]+board[5]+board[8];
        for(String check : winArray)
            if(check.equals("XXX") || check.equals("OOO")){
                return true;
            }
        
        return false;
    }

    public static boolean potentialHorizontalWin(String[] board) {
        // checks for horizontal win
        String[] winArray = new String[3];
        winArray[0] = board[0]+board[1]+board[2];
        winArray[1] = board[3]+board[4]+board[5];
        winArray[2] = board[6]+board[7]+board[8];
        for(String check : winArray)
            if(check.equals("XXX") || check.equals("OOO")){
                return true;
            }
        
        return false;
    }
    public static boolean potentialDiagonalWin(String[] board) {
        // checks for horizontal win
        String[] winArray = new String[2];
        winArray[0] = board[0]+board[4]+board[8];
        winArray[1] = board[2]+board[4]+board[6];
        for(String check : winArray)
            if(check.equals("XXX") || check.equals("OOO")){
                return true;
            }
        
        return false;
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
    /*int predictBestMove(String[] board){
        int place = 0; // will return from 1-9 if valid move available

        if ()//2 in a row already, 
        {
            //place = 3rd spot
        }
        else if () //1 placed already, 
        {
            //place = 2nd spot
        }
        
        // else place in first available random spot 
        else {
            int randPlace = (int) (Math.random() * 9.0);
            return randPlace;
        }
        
        return place;


    } */

}




