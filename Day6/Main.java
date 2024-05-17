
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
    static long Extradistance;
    static long Extratime;

    public static void main(String[] args) throws NumberFormatException, IOException {
        leseAufgabeB();

        berechneAufgabeB(Extratime, Extradistance);
        // distanceReached = timeWait * timeLeft;
    }

    public static void berechneAufgabeB(long time, long distance) {

        long ergebnis = berechneCount(time, distance);

        System.out.println(ergebnis);
    }

    public static void berechneAufgabeA(List<Integer> time, List<Integer> distance) {
        long produkt = 1;
        for (int i = 0; i < time.size(); i++) {
            produkt = produkt * berechneCount(time.get(i), distance.get(i));
        }
        System.out.println(produkt);
    }

    public static void leseAufgabeB() throws NumberFormatException, IOException {
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;

        while ((readLine = b.readLine()) != null) {
            Matcher m = Pattern.compile("(Time|Distance)\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*")
                    .matcher(readLine);
            String buffer = "";
            while (m.find()) {
                if (m.group(1).equals("Time")) {
                    for (int i = 2; i < 6; i++) {
                        buffer = buffer + m.group(i);
                    }
                    Extratime = Long.parseLong(buffer);
                } else if (m.group(1).equals("Distance")) {
                    for (int i = 2; i < 6; i++) {
                        buffer = buffer + m.group(i);
                    }
                    Extradistance = Long.parseLong(buffer);
                }
            }
            System.out.println("Time:");
            System.out.println(Extratime);
            System.out.println("Extradistance: ");
            System.out.println(Extradistance);

        }
    }

    public static void leseAufgabeA() throws NumberFormatException, IOException {
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;

        while ((readLine = b.readLine()) != null) {
            Matcher m = Pattern.compile("(Time|Distance)\\:\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)\\s*")
                    .matcher(readLine);

            while (m.find()) {
                if (m.group(1).equals("Time")) {
                    for (int i = 2; i < 6; i++) {
                        time.add(Integer.parseInt(m.group(i)));
                    }
                } else if (m.group(1).equals("Distance")) {
                    for (int i = 2; i < 6; i++) {
                        distance.add(Integer.parseInt(m.group(i)));
                    }
                }
            }
        }

    }

    public static long berechneCount(long time, long distance) {
        long distanceReached;
        long timeLeft;
        long timeWait;
        long countHighscore = 0;

        for (timeWait = 0; timeWait <= time; timeWait++) {
            timeLeft = (long) time - timeWait;
            distanceReached = (long) timeWait * timeLeft;
            if (distanceReached >= distance) {
                countHighscore++;
            } else if (distanceReached < distance) {

            }
            // System.out.println("Warte: " + timeWait);
        }
        System.out.println(countHighscore);
        return countHighscore;
    }

}