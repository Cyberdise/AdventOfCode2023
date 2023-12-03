import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException{
        
        //int gameID = Integer.parseInt(null);
        int gameSet; // für die Iliteration
        int red = 0;
        int blue = 0;
        int green = 0;
        
        int sum = 0;
        
        spliten();

 
    }

    public static void spliten() throws IOException{
        final int possibleRed = 12;
        final int possibleBlue = 14;
        final int possibleGreen = 13;
        File f = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine;
        int highRed = 0;
        int highBlue = 0;
        int highGreen = 0;
        int gameID = 0;
        int sum = 0;
        double SUM = 0;
        while ((readLine = b.readLine()) != null){
            gameID++;
            Matcher redMatcher = Pattern.compile("=?(\\d*)(\\sred)").matcher(readLine);
            Matcher greenMatcher = Pattern.compile("=?(\\d*)(\\sgreen)").matcher(readLine);
            Matcher blueMatcher = Pattern.compile("=?(\\d*)(\\sblue)").matcher(readLine);
            //Matcher gameMatcher = Pattern.compile("=?(Game\\s)(\\d*)").matcher(readLine);
            
            while(redMatcher.find()){
                int buffer = Integer.parseInt(redMatcher.group(1));
                if (highRed < buffer){
                    highRed = Integer.parseInt(redMatcher.group(1));
                }
            }
            while(greenMatcher.find()){
                int buffer = Integer.parseInt(greenMatcher.group(1));
                if (highGreen < buffer){
                    highGreen = buffer;
                }
            }
            while(blueMatcher.find()){
                int buffer = Integer.parseInt(blueMatcher.group(1));
                if (highBlue < buffer){
                    highBlue = Integer.parseInt(blueMatcher.group(1));
                }
            }
            System.out.println(readLine);
            System.out.println("Green: " + highGreen);
            System.out.println("Blue: " + highBlue);
            System.out.println("Red: " + highRed);
            
            System.out.println("GameID: "+ gameID);
            /* 
            if(highBlue <= possibleBlue && highGreen <= possibleGreen && highRed <= possibleRed){
                System.out.println("Vorsumme: "+ sum);
                sum = sum + gameID;
                System.out.println("Summe: "+ sum);
            }
            */
            
            sum = sum + highBlue*highGreen*highRed;
            
            
            
            
            highBlue = 0;
            highGreen = 0;
            highRed = 0;

        }
        System.out.println(sum);
    }

}
