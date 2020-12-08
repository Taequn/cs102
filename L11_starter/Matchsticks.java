import java.util.*;

public class Matchsticks{

    //determines whether debug information is displayed for both players
    public static final boolean DEBUG = true;

    private static final int DEFAULT_STARTING_STICKS = 10;

    //The maximum matchsticks a player can choose to take on their turn
    public static final int MAX_STICKS_CHOICE = 3;

    //for use in any classes that need random values
    public static Random rand = new Random();

    private Player[] players = new Player[2]; //stores the two players
    private int startingSticks;

    private static Scanner scan;

    
    public Matchsticks(Player player1, Player player2){
        this(player1, player2, DEFAULT_STARTING_STICKS);
    }


    public Matchsticks(Player player1, Player player2, int startingSticks){
        if (startingSticks <= 0)
            this.startingSticks = DEFAULT_STARTING_STICKS;
        else
            this.startingSticks = startingSticks;
        this.players[0] = player1;
        this.players[0].initPlayer(startingSticks);
        this.players[1] = player2;
        this.players[1].initPlayer(startingSticks);

        scan = new Scanner(System.in);
    }



    private void finishGame(int loserIdx){
        System.out.println("\nPlayer " + (loserIdx + 1) + " (" + players[loserIdx].getPlayerType() + ") loses!\n");
        for (int i = 0; i < players.length; i++)
            players[i].gameOver(i != loserIdx);
    }

    
    public void playSingleGame(){
        System.out.println("\nStarting game with " + this.startingSticks + " matchsticks!...");
        int currentPlayerIdx = rand.nextInt(players.length);
        int sticksRemaining = this.startingSticks;
        
        while (sticksRemaining > 0){
            System.out.println("\n*** There are " + sticksRemaining + " matchstick(s) remaining! ***\n");
            currentPlayerIdx = (currentPlayerIdx + 1) % players.length;
            String currentPlayer = "Player "+ (currentPlayerIdx+1) +" ("+ players[currentPlayerIdx].getPlayerType() +")";
            System.out.println(currentPlayer + "'s turn!");
            if (DEBUG)
                System.out.println("[DEBUG] " + players[currentPlayerIdx].getDebugInfo());
            int sticksTaken = players[currentPlayerIdx].takeTurn(sticksRemaining);
            System.out.println(currentPlayer + " takes " + sticksTaken + " matchstick(s)!");
            sticksRemaining -= sticksTaken;
        }
        finishGame(currentPlayerIdx);

    }



    public static String getValidInput(String prompt, String[] validInputs){
        while(true){
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            for (String validInput : validInputs){
                if (input.equalsIgnoreCase(validInput))
                    return input.toLowerCase();
            }
            System.out.println("Invalid Input!  Enter one of the following: " + String.join(", ", validInputs));
        }
    }
    
    
    public static int getValidInput(String prompt, int min, int max){
        while(true){
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            try{
                int result = Integer.parseInt(input);
                if (result >= min && result <= max)
                    return result;
                System.out.println("Invalid Input! Number out of range!");
            }
            catch (NumberFormatException nfe){
                System.out.println("Invalid Input!  Enter a number!");
            }
        }
    }    





}
