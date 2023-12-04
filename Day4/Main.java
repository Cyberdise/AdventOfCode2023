import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        //Auslesen des Files
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        
        //Arrays, um "winning numbers" und "numbers" einer Karte zwischenzuspeichern.  
        int[] gewinnZahlen = new int[10];
        int[] zahlen = new int[25];
        
        /*
         * Potenz muss bei -1 starten, 2^0 immernoch 1 ergibt. 
         * potenz = -1, ist dafür da, um später im If-Else von [Matches 1=< und Matches = 0]
         */
        int potenz = -1;
        int sum = 0;

        //Für Aufgabe 2
        List<Integer> ergebnisse = new ArrayList<Integer>();
        
        /*
         * REGEX: (vereinfacht) (\d*)\s* --> Eine Group sind die Zahlen, die gecaptured "(Alles in der Klammer ist eine Gruppe)" werden sollen. 
         * Alles in der while-Schleife verarbeitet eine Zeile der "input.txt"
         */
        while((readLine = b.readLine()) != null){
            Matcher m = Pattern.compile("Card\\s*\\d*\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*\\|\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*").matcher(readLine);
            
            //Insert die ersten 10 Groups in "winning numbers" und die darauffolgenden 25 in "numbers"
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
            
            //Für Aufgabe 2: Potenz + 1 = Anzahl der Matches
            ergebnisse.add(potenz+1);
            
            //Berechnung der Punkte einer Karte mit Math.pow(2,potenz)
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
        
        //Methode für Aufgabe 2: aufgabe2(List<Integer> anzahlDerMatches)
        aufgabe2(ergebnisse);
    }
    
    //Hilfsmethode für Prüfung, ob in einem int[] ein bestimmtes int ist. 
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    
    public static void aufgabe2(List<Integer> list){
        List<Integer> cardSet = new ArrayList<Integer>();
        int sum =  0;
        
        //Instanzieren einer List<Integer> der Länge des Kartensets, mit jeweils einer Originalkarte
        for (int index = 0; index < list.size(); index++){
            cardSet.add(1);
            
        }

        //Iteration über alle anzahlMatches
        for (int index = 0; index < list.size(); index++){
            System.out.println("Aktueller Karte: "+ (index+1));
            
            //Addieren der Kopien ins finale Kartensets
            for(int ergebnisse = 1; ergebnisse <= list.get(index); ergebnisse++){
                cardSet.set((index+ergebnisse), (cardSet.get(index+ergebnisse)+cardSet.get(index)));
                //System.out.println("Value von Karte " + (index+ergebnisse) + " auf " + cardSet.get(index+ergebnisse) + " verändert.");
            }
            
            System.out.println("---------------------------------------------");
        
        }
        
        //Summation des finalen Kartensets
        for (int index = 0; index < cardSet.size(); index++){
            sum = sum + cardSet.get(index);
        }

        System.out.println("Es sind insgesamt " + sum + " Karten." );

    }
}