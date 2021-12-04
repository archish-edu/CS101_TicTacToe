import java.util.ArrayList;
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

        printCurrentBoard();

        while(win == false) {
            try{
                System.out.println("Enter slot: ");
                int slot = input.nextInt();

                if(slot > 9 || slot < 1) {
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    continue;
                }

                // x turn
                if(playerX == true) {

                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;
                        
                        
                        board[slot-1] = "X";
                        
                        playerX = false;
                        printCurrentBoard();
                        if(winCheck()) {
                            System.out.println("X wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                        // call win checker here!
                    } else {
                        System.out.println("Try again! Slot is already taken.");
                    }

                    System.out.println("Prediction: " + predictBestMove("O"));

                    // o turn
                } else if (playerX == false) {
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;
                        
                        board[slot-1] = "O";
                        System.out.println("Prediction: " + predictBestMove("O"));
                        playerX = true;
                        printCurrentBoard();
                        if(winCheck()) {
                            System.out.println("O wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else {
                        System.out.println("Try again! Slot is already taken.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Error! Please enter a valid input!");
                break;
            }

        }

    }

    public static boolean winCheck(){
        return (diagonalWin()|| horizontalWin()|| verticalWin());
    }

    public static boolean verticalWin() {
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

    public static boolean horizontalWin() {
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

    public static boolean diagonalWin() {
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

    public static boolean draw() {
        int totalPlacesTaken = 0;
        for(String place : board) {
            if(place.equals("X") || place.equals("O")){
                totalPlacesTaken += 1;
            }
        }
        if(totalPlacesTaken == 9)
            return true;

        return false;
    }

    public static void printCurrentBoard(){
        System.out.println();
        System.out.println(" "+board[0]+" "+"|" + " "+board[1]+" "+"|" + " "+board[2]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[3]+" "+"|" + " "+board[4]+" "+"|" + " "+board[5]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[6]+" "+"|" + " "+board[7]+" "+"|" + " "+board[8]+" ");
        System.out.println();

    }

    public static int predictBestMove(String player) {
        
        String temp = "";

        ArrayList<Integer> possibleMoves = new ArrayList();

        
        for(int i = 1; i < board.length+1; i++) {
            if( !board[i-1].equals("X") && !board[i-1].equals("O")) {
                possibleMoves.add(i);
            }
        }
                
        int moveCount = 0;

        while(moveCount < possibleMoves.size()) {
            int place = possibleMoves.get((int) Math.random() * possibleMoves.size());
            temp = board[place];
            board[place] = player;
            if(winCheck() == true) {
                board[place] = temp;
                return place;
            } 
            board[place] = temp;

            moveCount += 1;
        }

        return possibleMoves.get((int) Math.random() * possibleMoves.size());
    } 

}

