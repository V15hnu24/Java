import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        reader.init(System.in);
        System.out.println("Enter the player name and hit enter");
        String PlayerName = reader.nextLine();
        System.out.println("The game setup is ready");
        game gme = new game();
        gme.setPlayerName(PlayerName);
        gme.setFloor(-1);
        Dice dice = new Dice();

        System.out.print("Hit enter to roll the dice");
        String s = reader.nextLine();
        dice.roll();

        // This loop executes untill the facevalue of dice is 1.
        while(dice.getFaceValue()!=1){
            System.out.println("Dice gave 2\n" +
                    "Game cannot start until you get 1");
            System.out.print("Hit enter to roll the dice");
            s = reader.nextLine();
            dice.roll();
        }

        System.out.println("Dice gave 1\n" +
                "Player position Floor-0");
        gme.setFloor(0);
        gme.setPoints(1);
        System.out.println("Total points: " + gme.getPoints());

        int diceFaceValue = 0;
        while(gme.getFloor() != 13){
            System.out.print("Hit enter to roll the dice");
            s = reader.nextLine();

            // Bonus feature.
            // Added to avoid confusion in the command line while testing the code.
            System.out.println();
            System.out.println("-----------*******-----------");

            dice.roll();
            diceFaceValue = dice.getFaceValue();
            int floor = gme.getFloor()+ diceFaceValue;

            if (floor == 2){
                System.out.println("Dice gave "+diceFaceValue);
                gme.elevator();


            }
            else if(floor == 5){
                System.out.println("Dice gave "+diceFaceValue);
                gme.Snake();
            }
            else if(floor == 8){
                System.out.println("Dice gave "+diceFaceValue);
                gme.ladder();

            }
            else if (floor == 11){
                System.out.println("Dice gave "+diceFaceValue);

                gme.kingCobra();
            }
            else if(floor>13){
                floor = floor - diceFaceValue;
                System.out.println("Dice gave "+diceFaceValue);
                System.out.println("Player Cannot move");
            }
            else{
                gme.setFloor(floor);
                System.out.println("Dice gave "+diceFaceValue);
                gme.Empty();
            }
        }
        System.out.println("Dice gave " + diceFaceValue);
        System.out.println("Player position Floor-13");
        System.out.println(gme.getPlayerName()+" has reached an" + " Empty "+"Floor");
        System.out.println("Game Over");
        System.out.println(gme.getPlayerName()+" accumulated " + gme.getPoints() + " points");

    }
}

class Dice {

    private final int numFaces=2; //maximum face value
    private int faceValue; //current value showing on the dice
    // Rolls the dice

    Random rand = new Random();
    public void roll() {
        int curr_faceValue = 1 + rand.nextInt(numFaces);
        setFaceValue(curr_faceValue);
    }
    // Face value setter/mutator.
    private void setFaceValue (int value) {
        if (value <= numFaces)
            faceValue = value;
    }
    // Face value getter/setter.
    public int getFaceValue() {
        return faceValue;
    }

    // Face value getter/setter.
    public int getNumFaces() {
        return numFaces;
    }

    // Returns a string representation of this dice
    public String toString() {
        return "number of Faces"  + numFaces + "current face value"  + faceValue;
    }

}

class Player{

    private String PlayerName;
    private int points;
    private int floor;

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getPoints() {
        return points;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

class game extends Player {

    public void elevator() {
        setPoints(getPoints() + 4);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached an" + " Elevator "+"Floor");
        System.out.println("Total points: " + getPoints());
        setFloor(10);
        empty();
    }

    public void Snake() {
        setPoints(getPoints()-2);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached" + " Snake "+"Floor");
        System.out.println("Total points: " + getPoints());
        setFloor(1);
        empty();

    }

    public void ladder(){
        setPoints(getPoints()+2);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached" + " Ladder "+"Floor");
        System.out.println("Total points: " + getPoints());
        setFloor(12);

        empty();
    }

    public void kingCobra(){
        setPoints(getPoints()-4);
        setFloor(3);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached " + " King Cobra "+"Floor");
        System.out.println("Total points: " + getPoints());
        empty();
    }

    // Used when the player reached empty from any ladders or snakes
    public void empty(){
        setPoints(getPoints()+1);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached an" + " Empty "+"Floor");
        System.out.println("Total points "+getPoints());
    }

    // Used when player directly reaches empty from dice not from any speacial places like snakes or ladders
    public void Empty() {
        setPoints(getPoints()+1);
        System.out.println("Player position "+getFloor());
        System.out.println(getPlayerName()+" has reached an" + " Empty "+"Floor");
        System.out.println("Total points "+getPoints());
    }

}


//general reader class.
class reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    // call this method to initialize reader for InputStream
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
    // get next word
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException{
        return Long.parseLong(next());
    }
}
