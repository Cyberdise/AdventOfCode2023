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
    static List<Double> Extradistance = new ArrayList<Double>();
    static List<Double> Extratime = new ArrayList<Double>();
        
    public static void main(String[] args) throws NumberFormatException, IOException{
        leseAufgabeB();
        berechneAufgabeB(Extratime, Extradistance);
        // distanceReached = timeWait * timeLeft;
    }

    public static void berechneAufgabeB(List<Double> time, List<Double> distance) {
        double produkt = 1;
        for (int i = 0; i < Extratime.size(); i++){
                produkt = produkt * berechneCount(Extratime.get(i), Extradistance.get(i));
        }
        System.out.println(produkt);
    }

    public static void berechneAufgabeA(List<Integer> time, List<Integer> distance) {
        int produkt = 1;
        for (int i = 0; i < time.size(); i++){
                produkt = produkt * berechneCount(time.get(i), distance.get(i));
        }
        System.out.println(produkt);
    }
    public static void leseAufgabeB() throws NumberFormatException, IOException{
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;

        while((readLine = b.readLine()) != null){
            Matcher m = Pattern.compile("(Time|Distance)\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*").matcher(readLine);
            String buffer = "";
            while(m.find()){
                if (m.group(1).equals("Time")){
                    for (int i = 2; i < 6; i++){
                        buffer = buffer + m.group(i);
                    }
                    Extratime.add(Double.parseDouble(buffer));
                } else if (m.group(1).equals("Distance")){
                    for (int i = 2; i < 6 ; i++){
                        buffer = buffer + m.group(i);
                    }
                    Extradistance.add(Double.parseDouble(buffer));
                }
            } 
            System.out.println(time);
            System.out.println(Extradistance); 
            b.close();
        }
    }



    public static void leseAufgabeA() throws NumberFormatException, IOException{
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;

        while((readLine = b.readLine()) != null){
            Matcher m = Pattern.compile("(Time|Distance)\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*").matcher(readLine);

            while(m.find()){
                if (m.group(1).equals("Time")){
                    for (int i = 2; i < 6; i++){
                        time.add(Integer.parseInt(m.group(i)));
                    }
                } else if (m.group(1).equals("Distance")){
                    for (int i = 2; i < 6 ; i++){
                        distance.add(Integer.parseInt(m.group(i)));
                    }
                }
            }  
        }
        b.close();
    }

    public static int berechneCount(double time, double distance){
        double distanceReached;
        double timeLeft;
        double timeWait;
        int countHighscore = 0;
        
        for (timeWait = 0; timeWait < time; timeWait++){
            timeLeft = time - timeWait;
            distanceReached = timeWait * timeLeft;
            if (distanceReached >= distance){
                countHighscore++;
            }else if(distanceReached < distance){

            }
            System.out.println("Warte: " + timeWait);
        }
        return countHighscore;
    }


}