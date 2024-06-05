import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static class Map {
        List<Feld> zeile = new ArrayList<Feld>();
        List<List<Feld>> spalte = new ArrayList<List<Feld>>();

        public Map(String input_path) throws FileNotFoundException, IOException {
            File file = new File(input_path);
            BufferedReader b = new BufferedReader(new FileReader(file));
            String readLine;
            String buffer;
            for (int j = 0; (readLine = b.readLine()) != null; j++) {
                for (int i = 0; i < readLine.length(); i++) {
                    buffer = "" + readLine.charAt(i);

                    System.out.println(buffer);
                }
            }
        }
    }

    public static class Player {
        int x;
        int y;

        public Player() {

        }
    }

    public static class Feld {
        public char feld;
        public boolean player_stands_on_field;
        public boolean accessible_field;

        public Feld() {

        }
    }

    public static void main(String[] args) {

    }
}