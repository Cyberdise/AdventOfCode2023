import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        int[] gewinnZahlen = new int[10];
        int[] zahlen = new int[25];
        int potenz = -1;
        int sum = 0;
        List<Integer> ergebnisse = new ArrayList<Integer>();
        while((readLine = b.readLine()) != null){
            Matcher m = Pattern.compile("Card\\s*\\d*\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*\\|\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*").matcher(readLine);
            
            while(m.find()){
                for (int i = 1; i < 36; i++){
                    if (i < 11){
                        gewinnZahlen[i-1] = Integer.parseInt(m.group(i));
                    }else{
                        zahlen[i-11] = Integer.parseInt(m.group(i));
                    }
                }
            }
           
           
            for (int i = 0; i < 10; i++){
                if(contains(zahlen, gewinnZahlen[i])){
                    potenz++;
                }
            }
            System.err.println("Es gibt " + (potenz+1) + " Ergebnisse.");
            
            ergebnisse.add(potenz+1);

            int buffer;
            if (potenz >= 0){
                buffer = (int) Math.pow(2,potenz);
            }else{
                buffer = 0;
            }
            System.out.println("Buffer: " + buffer);
            sum = sum + buffer;
            potenz = -1;
            
        }
        System.out.println("Summe aller Karten: " + sum);
        aufgabe2(ergebnisse);
    }
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    public static void aufgabe2(List<Integer> list){
        List<Integer> cardSet = new ArrayList<Integer>();
        int sum =  0;
        
        for (int index = 0; index < list.size(); index++){
            cardSet.add(1);
            
        }

        for (int index = 0; index < list.size(); index++){
            System.out.println("Aktueller Karte: "+ (index+1));
            for(int ergebnisse = 1; ergebnisse <= list.get(index); ergebnisse++){
                cardSet.set((index+ergebnisse), (cardSet.get(index+ergebnisse)+cardSet.get(index)));
                //System.out.println("Value von Karte " + (index+ergebnisse) + " auf " + cardSet.get(index+ergebnisse) + " verändert.");
            }
            
            System.out.println("---------------------------------------------");
        
        }

        for (int index = 0; index < cardSet.size(); index++){
            sum = sum + cardSet.get(index);
        }

        System.out.println("Es sind insgesamt " + sum + " Karten." );

    }
}