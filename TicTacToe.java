import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    // data
    // board is a 1d array initialized with numbers 1-9
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

    public static void main(String[] args){
        System.out.println("Welcome to Tic Tac Toe! Enter 1 for 2 players, 2 for playing against CPU, or 0 to quit: ");
        do{
            try {
                // set mode (catches invalid inputs)
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
                else{ // if non-int input
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) { // clears input
                System.out.println("Enter a valid input!");
                input.nextLine();

            }
        } while(modeSet == false);
    }
 
    // player vs player
    public static void mode1(){
        // reset strike counters for both players
        int strikeCounterP1 = 0;
        int strikeCounterP2 = 0;
        int consecutiveStrikeP1 = 0;
        int consecutiveStrikeP2 = 0;
        int cpuAssistP1 = 0;
        int cpuAssistP2 = 0;

        // print initial board
        printCurrentBoard();
        while(win == false) {

            try{
                // swap players for gui
                if(playerX == true){
                    player = "Player 1";
                } else {
                    player = "Player 2";
                }

                // get slot input
                System.out.println(player + "'s turn \nEnter slot (or -1 for best predicted move): ");
                slot = input.nextInt();

                // catch invalid slot input ints, increment strike counters as needed
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

                    // check if either strike counter limit has been reached and forfeit player as needed
                    if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                        System.out.println("Player 1 forfeits by too many incorrect entries! Player 2 wins!");
                        break;
                    } else if(strikeCounterP2 > 4 || consecutiveStrikeP2 > 2) {
                        System.out.println("Player 2 forfeits by too many incorrect entries! Player 1 wins!");
                        break;
                    }

                    continue;
                }

                // auto forfeit if player enters 0
                if(slot == 0) {
                    if(playerX = false) {
                        System.out.println("Player 2 forfeits! Player 1 wins!");
                        break;
                    } else if(playerX = true) {
                        System.out.println("Player 1 forfeits! Player 2 wins!");
                        break;
                    }
                }

                if(playerX == true) { // player 1's turn

                    // if -1 is pressed, predict best move
                    if(slot == -1) {
                        if(cpuAssistP1 < 3) {
                            slot = predictBestMove("X");
                            cpuAssistP1 += 1;
                            System.out.println("Player 1 Uses CPU Assist! CPU Picks: " + slot);
                            printCurrentBoard();

                        } else {
                            System.out.println("Player 1 has used too many CPU Assists! Try again!");
                            continue;
                        }
                        
                    }

                    // if valid input, place X and update board
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;

                        board[slot-1] = "X";

                        // reset consecutive strike counter
                        consecutiveStrikeP1 = 0;

                        // switch players
                        playerX = false;
                        printCurrentBoard();

                        // check if player 1 has win or if draw is reached
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else { // player enters slot that's already taken
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP1 += 1;
                        strikeCounterP1 += 1;

                        // check strike counters
                        if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                            System.out.println("Player 1 forfeits by too many incorrect entries! Player 2 wins!");
                            break;
                        }
                    }

                    
                } else if (playerX == false) { // player 2's turn

                    // if -1 is pressed, predict best move
                    if(slot == -1) {
                        if(cpuAssistP2 < 3) {
                            slot = predictBestMove("O");
                            cpuAssistP2 += 1;
                            System.out.println("Player 2 Uses CPU Assist! CPU Picks: " + slot);
                            printCurrentBoard();

                        } else {
                            System.out.println("Player 2 has used too many CPU Assists! Try again!");
                            continue;
                        }
                        
                    }

                    // if valid input, place O and update board
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;
                        board[slot-1] = "O";
                        
                        // reset consecutive strike counter
                        consecutiveStrikeP2 = 0;
                        
                        // switch players
                        playerX = true;
                        printCurrentBoard();

                        // check if player 2 has win or if draw is reached
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else { // player enters slot that's already taken
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP2 += 1;
                        strikeCounterP2 += 1;

                        // check strike counters
                        if(strikeCounterP2 > 4 || consecutiveStrikeP2 > 2) {
                            System.out.println("Player 2 forfeits by too many incorrect entries! Player 1 wins!");
                            break;
                        }
                    }

                }

            } catch (InputMismatchException e) { // invalid input strike counters
                if(playerX == true) { // check which player put in the wrong input
                    consecutiveStrikeP1 += 1;
                    strikeCounterP1 += 1;

                    // if P1 exceeds strike limits
                    if(consecutiveStrikeP1 > 2 || strikeCounterP1 > 4) {
                        System.out.println("Player 1 forfeits by too many incorrect entries, Player 2 wins!");
                        break;
                    }

                } else {
                    consecutiveStrikeP2 += 1;
                    strikeCounterP2 += 1;
                    
                    // if P2 exceeds strike limits
                    if(consecutiveStrikeP2 > 2 || strikeCounterP2 > 4) {
                        System.out.println("Player 2 forfeits by too many incorrect entries, Player 1 wins!");
                        break;
                    }
                }

                // clear input for given turn
                System.out.println("Error! Please enter a valid input!");
                input.nextLine();
            }

        }
    }

    // player vs CPU
    public static void mode2() {
        // reset strike counters
        int strikeCounterP1 = 0;
        int consecutiveStrikeP1 = 0;
        int cpuAssistP1 = 0;
    
        printCurrentBoard();

        while(win == false) {
            try{
                // swap players for gui
                if(playerX == true){
                    player = "Player 1";
                } else {
                    player = "CPU";
                }
                if(playerX == true) {
                    System.out.println(player + "'s turn \nEnter slot (or -1 for best predicted slot): ");
                    slot = input.nextInt();
                } else { // if cpu is playing, simulate whether X will win next turn and block that move &/OR find best possible move 
                    if(simulateXWin() == false)
                        slot = predictBestMove("O");
                    else {
                        slot = predictBestMove("X");
                    }
                }
                if(slot > 9 || slot < -1) { // if player 1 enters invalid slot number
                    System.out.println("Try again! Please enter a number between 1 and 9!");
                    if(playerX == true) {
                        strikeCounterP1 += 1;
                        consecutiveStrikeP1 += 1;
                    }
                    continue;
                }

                if(slot == 0) { // auto forfeit if player enters 0
                    System.out.println("Player 1 forfeits! CPU Wins!");
                    break;
                }

                // player turn
                if(playerX == true) {
                    if(slot == -1) { // if -1 is pressed, predict best move
                        System.out.println("Predicted best move: " + predictBestMove("X"));
                        continue;
                    }

                    // if valid input, place X and update board
                    if(board[slot-1].equals(String.valueOf(slot))) {
                        numTurns += 1;

                        board[slot-1] = "X";

                        // reset consecutive strike counter
                        consecutiveStrikeP1 = 0;

                        playerX = false;
                        printCurrentBoard();

                        // check if win or draw
                        if(winCheck()) {
                            System.out.println(player + " wins!"); 
                            break; 
                        } else if(draw() == true) {
                            System.out.println("Draw! No more possible moves");
                            break;
                        }

                    } else { // player enters slot that is already taken
                        System.out.println("Try again! Slot is already taken.");
                    }

                  
                } else if (playerX == false) { // cpu turn

                    // display which slot cpu picks
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

                    } else { // in case player tries to interfere with cpu (unlikely)
                        System.out.println("Try again! Slot is already taken.");
                        consecutiveStrikeP1 += 1;
                        strikeCounterP1 += 1;
                        if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                            System.out.println("Player 1 forfeits by too many incorrect entries! CPU Wins!");
                            break;
                        }
                    }
                }

            } 
            catch (InputMismatchException e) { // if player enters non-int value
                consecutiveStrikeP1 += 1;
                strikeCounterP1 += 1;
                if(strikeCounterP1 > 4 || consecutiveStrikeP1 > 2) {
                    System.out.println("Player 1 forfeits by too many incorrect entries! CPU Wins!");
                    break;
                }
                System.out.println("Error! Please enter a valid input!");
                input.nextLine();
            }

        }
    }

    public static boolean winCheck(){
        // calls all possible win check methods
        return (diagonalWin()|| horizontalWin()|| verticalWin());
    }

    public static boolean verticalWin() {
        // checks for vertical win by concatenating and comparing slot strings
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
        // checks for vertical win by concatenating and comparing slot strings
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
         // checks for vertical win by concatenating and comparing slot strings
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
        // checks for draw, returns true if board is full, false if moves still available (calculated by counting places)
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
        // prints current state of board 
        System.out.println();
        System.out.println(" "+board[0]+" "+"|" + " "+board[1]+" "+"|" + " "+board[2]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[3]+" "+"|" + " "+board[4]+" "+"|" + " "+board[5]+" ");
        System.out.println("---+---+---");
        System.out.println(" "+board[6]+" "+"|" + " "+board[7]+" "+"|" + " "+board[8]+" ");
        System.out.println();

    }

    public static int predictBestMove(String player) {
        // predicts best possible move by testing available slots and calling winCheck() for each possible outcome. if none result in win, return first random available number
        String temp = "";

        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

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
        // test if cpu can attempt to block player 1
        // simulates best possible player move and returns true if player win is possible (cpu blocks) - player MUST FORK CPU TO WIN

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
