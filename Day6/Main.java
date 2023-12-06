import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    static List<Integer> time = new ArrayList<Integer>();
    static List<Integer> distance = new ArrayList<Integer>();
        
    public static void main(String[] args) throws NumberFormatException, IOException{

        int countHighscore = 0;
        int produkt = 0;
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;

        while((readLine = b.readLine()) != null){
            Matcher m = Pattern.compile("Time\\:\\s*(?:(\\d*)\\s*)*").matcher(readLine);
            Matcher d = Pattern.compile("Distance\\:\\s*(?:(\\d*)\\s*)*").matcher(readLine);
            
            while(m.find()){
                for (int i = 0; (m.group(i) != null); i++){
                    time.add(Integer.parseInt(m.group(i)));
                }
            }
            while(d.find()){
                for (int i = 0; (d.group(i) != null); i++){
                    distance.add(Integer.parseInt(d.group(i)));
                }
            }
            for (int i = 0; i < time.size(); i++){
                produkt = produkt * berechneCount(time.get(i), distance.get(i));
            }
            System.out.println(produkt);
        }
        // distanceReached = timeWait * timeLeft;
    }

    public static int berechneCount(int time, int distance){
        int distanceReached;
        int timeLeft;
        int timeWait;
        int countHighscore = 0;
        for (timeWait = 0; timeWait < time; timeWait++){
            timeLeft = time - timeWait;
            distanceReached = timeWait * timeLeft;
            if (distanceReached >= distance){
                countHighscore++;
            }else if(distanceReached < distance){

            }
        }
        return countHighscore;
    }


}