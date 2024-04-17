import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static String[][] zeichen = new String[145][145];

    public static void main(String[] args) throws IOException {
        File f = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine = "";
        String buffer = "";

        for (int j = 0; (readLine = b.readLine()) != null; j++) {
            for (int i = 0; i < readLine.length(); i++) {
                buffer = "" + readLine.charAt(i);
                zeichen[i][j] = buffer;
                System.out.println(buffer);
            }
        }

        /*
         * String lastString = null;
         * String prevprevString = null;
         * String prevString = null;
         * String currString = null;
         * File f = new File("./input.txt");
         * BufferedReader b = new BufferedReader(new FileReader(f));
         * boolean init = true;
         * String readLine = "";
         * while ((readLine = b.readLine()) != null){
         * if (init){
         * readLine = currString;
         * init = false;
         * }else{
         * prevprevString = prevString;
         * prevString = currString;
         * currString = readLine;
         * }
         * String buffer = null;
         * int startIndex = 0;
         * int endIndex = 0;
         * 
         * for(char ch: prevString.toCharArray()){
         * int countIndex = 0;
         * String courser = "" + ch;
         * 
         * if (isNumeric(courser)){
         * startIndex = countIndex;
         * }else{
         * countIndex++;
         * }
         * }
         * 
         * }
         */

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
