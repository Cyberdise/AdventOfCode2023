import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
    char hash = '#';
    char dot = '.';
    char unknown = '?';

    String line;
    int size; // line.length()
    int arrangements;
    int result;

    private List<String> generatePossiblePuzzlePieces(int interations) {
        List<String> pieces = new ArrayList<String>();
        String buffer = "";
        for (int i = 0; i < interations; i++) {
            buffer = buffer + hash;
        }
        pieces.add(buffer + ".");
        pieces.add("." + buffer + ".");
        pieces.add("." + buffer);
        return pieces;
    }

    public List<List<String>> generateAllPieces(List<Integer> puzzleInformation) {
        List<List<String>> allPieces = new ArrayList<List<String>>();
        for (int iteration : puzzleInformation) {
            List<String> buffer = generatePossiblePuzzlePieces(iteration);
            allPieces.add(buffer);
        }
        return allPieces;
    }

    public static void main(String[] args) throws IOException {
        File f = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine = "";
        String buffer = "";

        for (int j = 0; (readLine = b.readLine()) != null; j++) {
            for (int i = 0; i < readLine.length(); i++) {
                buffer = "" + readLine.charAt(i);

                System.out.println(buffer);
            }
        }
    }
}