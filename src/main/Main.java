package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import src.main.models.Game;
import src.main.models.Team;

public class Main {

    static Game game;
    static final String TEAMS_FILE = "src/main/teams.txt";
    static final String PLAYS_FILE = "src/main/plays.txt";

    public static void main(String[] args) {

       
        try {
                String[][] data = getData();
                 game =  new Game(
                        new Team(data[0][0], data[0][1], data[0][2], new String[] {data[0][3], data[0][4], data[0][5]}),
                        new Team(data[1][0], data[1][1], data[1][2], new String[] {data[1][3], data[1][4], data[1][5]})
                        );
                startGame();
                printResult();
                        
        } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }

       
        
                
    }

    public static String[][] getData() throws FileNotFoundException{
        
        FileInputStream fis = new FileInputStream(TEAMS_FILE);
        Scanner scan = new Scanner(fis);

       String[] lines = new String[] {scan.nextLine(), scan.nextLine()};
       scan.close();

        return new String[][]{
                lines[0].split(","), lines[1].split(",")
        };

    }

    public static void startGame() throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(PLAYS_FILE);
        Scanner scan = new Scanner(fis);

        while (scan.hasNextLine()){
                wait(3);
               System.out.println("\n" + game.simulate(scan.nextLine()) + "\n");
        }
        scan.close();
    }
  
    public static void printResult(){
        int scoreGryf = game.getScore(game.getTeam("GRYFFINDOR"));
        int scoreSly = game.getScore(game.getTeam("SLYTHERIN"));
        System.out.println("\nGRYFFINDOR: " + scoreGryf + 
                                " SLYTHERIN: " + scoreSly);
        String winner = scoreGryf > scoreSly ? "GRYFFINDOR" :"SLYTHERIN";
        System.out.println("\n" + winner + " WINS!");
    }

   
    public static void wait(int sec){
        try{
                TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e ){
                System.out.println(e.getMessage());
        }
        
    }

}
