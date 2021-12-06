import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    static String[] board = {"1","2","3","4","5","6","7","8","9"};
    static Scanner input  = new Scanner(System.in);
    static boolean playerX = true;
    static boolean win = false;
    static int numTurns = 0;
    static int slot;
    static String player;
    static String player2;
    static boolean modeSet = false;
    static int mode;
    // board is a 1d array initialized with numbers 1-9

    
    public static void main(String[] args){
        System.out.println("Welcome to Tic Tac Toe! Enter 1 for 2 players, 2 for playing against CPU, or 0 to quit: ");
        do{
            try {
                mode = input.nextInt();
                if(mode == 1) {
                    mode1();
                    modeSet = true;
                }
                else if(mode == 2) {
                    mode2();
                    modeSet = true;
                }
                else if(mode == 0) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                else{
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input!");
                input.nextLine();

            }
        }while(modeSet == false);
    }
 

    public static void mode1(){
        int strikeCounterP1 = 0;
        int strikeCounterP2 = 0;
        int consecutiveStrikeP1 = 0;
        int consecutiveStrikeP2 = 0;

        printCurrentBoard();
        while(win == false) {

            try{
                if(playerX == true){
                    player = "Player 1";
                } else {
                    player = "Player 2";
                }
                
                System.out.println(player + "'s turn \nEnter slot (or -1 for best predicted move): ");
                slot = input.nextInt();

                if(slot > 9 || slot < -1) {
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    
                    if(playerX == true) {
                        strikeCounterP1 += 1;
                        consecutiveStrikeP1 += 1;
                    }
                    
                    else if(playerX == false) {
                        strikeCounterP2 += 1;
                        consecutiveStrikeP2 += 1;
                    }

                    if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                        System.out.println("Player 1 forfeits by too many incorrect entries!");
                        break;
                    } else if(strikeCounterP2 > 4 || consecutiveStrikeP2 > 2) {
                        System.out.println("Player 2 forfeits by too many incorrect entries");
                        break;
                    }

                    continue;
                }

                if(slot == 0) {
                    if(playerX = false) {
                        System.out.println("Player 2 forfeits! Player 1 wins!");
                        break;
                    } else if(playerX = true) {
                        System.out.println("Player 1 forfeits! Player 2 wins!");
                        break;
                    }
                }

                // x turn
                if(playerX == true) {
                    if(slot == -1) {
                        System.out.println("Predicted best move: " + predictBestMove("X"));
                        continue;
                    }

                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;

                        board[slot-1] = "X";

                        consecutiveStrikeP1 = 0;

                        playerX = false;
                        printCurrentBoard();
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                        // call win checker here!
                    } else {
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP1 += 1;
                        strikeCounterP1 += 1;
                        if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                            System.out.println("Player 1 forfeits by too many incorrect entries!");
                            break;
                        }
                    }

                    // o turn
                } else if (playerX == false) {
                    if(slot == -1) {
                        System.out.println("Predicted best move: " + predictBestMove("O"));
                        continue;
                    }
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;
                        board[slot-1] = "O";
                        
                        consecutiveStrikeP2 = 0;
                        
                        playerX = true;
                        printCurrentBoard();

                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else {
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP2 += 1;
                        strikeCounterP2 += 1;
                        if(strikeCounterP2 > 4 || consecutiveStrikeP2 > 2) {
                            System.out.println("Player 2 forfeits by too many incorrect entries");
                            break;
                        }
                    }

                }

            } catch (InputMismatchException e) {
                if(playerX == true) {
                    consecutiveStrikeP1 += 1;
                    strikeCounterP1 += 1;
                    if(consecutiveStrikeP1 > 2 || strikeCounterP1 > 4) {
                        System.out.println("Player 1 forfeits by too many incorrect entries, Player 2 wins!");
                        break;
                    }

                } else {
                    consecutiveStrikeP2 += 1;
                    strikeCounterP2 += 1;
                    if(consecutiveStrikeP2 > 2 || strikeCounterP2 > 4) {
                        System.out.println("Player 2 forfeits by too many incorrect entries, Player 1 wins!");
                        break;
                    }
                }
                System.out.println("Error! Please enter a valid input!");
                input.nextLine();
            }

        }
    }

    public static void mode2(){
        int strikeCounterP1 = 0;
        int consecutiveStrikeP1 = 0;
        printCurrentBoard();
        while(win == false) {
            try{
                if(playerX == true){
                    player = "Player 1";
                } else {
                    player = "CPU";
                }
                if(playerX == true) {
                    System.out.println(player + "'s turn \nEnter slot (or -1 for best predicted slot): ");
                    slot = input.nextInt();
                } else {
                    if(simulateXWin() == false)
                        slot = predictBestMove("O");
                    else {
                        slot = predictBestMove("X");
                    }
                }
                if(slot > 9 || slot < -1) {
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    if(playerX == true) {
                        strikeCounterP1 += 1;
                        consecutiveStrikeP1 += 1;
                    }
                    continue;
                }

                if(slot == 0) {
                    System.out.println("Player 1 forfeits! CPU Wins!");
                    break;
                }

                // x turn
                if(playerX == true) {
                    if(slot == -1) {
                        System.out.println("Predicted best move: " + predictBestMove("X"));
                        continue;
                    }

                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;

                        board[slot-1] = "X";

                        consecutiveStrikeP1 = 0;

                        playerX = false;
                        printCurrentBoard();
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                        // call win checker here!
                    } else {
                        System.out.println("Try again! Slot is already taken.");
                    }

                    // o turn
                } else if (playerX == false) {
                    
                    System.out.println("CPU picks slot: " + slot);
                    
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;

                        board[slot-1] = "O";

                        playerX = true;
                        printCurrentBoard();
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else {
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP1 += 1;
                        strikeCounterP1 += 1;
                        if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                            System.out.println("Player 1 forfeits by too many incorrect entries!");
                            break;
                        }
                    }
                }

            } 
            catch (InputMismatchException e) {
                consecutiveStrikeP1 += 1;
                strikeCounterP1 += 1;
                if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                    System.out.println("Player 1 forfeits by too many incorrect entries!");
                    break;
                }
                System.out.println("Error! Please enter a valid input!");
                input.nextLine();
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
            int place = possibleMoves.get(moveCount);
            temp = board[place-1];
            board[place-1] = player;
            if(winCheck() == true) {
                board[place-1] = temp;
                return place;
            } 
            board[place-1] = temp;

            moveCount += 1;
        }

        return possibleMoves.get((int) (Math.random() * possibleMoves.size()) );
    }
    public static boolean simulateXWin() {
        String[] boardClone = board.clone();
        String[] temp = board;
        board = boardClone;

        int move = predictBestMove("X");

        board[move-1] = "X";

        if(winCheck() == true) {
            board = temp;
            return true;
        } else {
            board = temp;
            return false;
        }
    } 

}
