import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        int gameID = Integer.parseInt(null);
        int gameSet; // für die Iliteration
        int red = 0;
        int blue = 0;
        int green = 0;
        final int possibleRed = 12;
        final int possibleBlue = 14;
        final int possibleGreen = 13;
        int sum = 0;
        
        spliten();

 
    }

    public static void spliten() throws IOException{
        File f = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine;
        System.out.println("a");
        while ((readLine = b.readLine()) != null){
            String[] firstSplit = readLine.split("[A-Za-z0-9] :");
            System.out.println(readLine);
            System.out.println(firstSplit[0]);
        }
    }


}
