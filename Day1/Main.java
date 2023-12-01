import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException{
        aufgabe2();

    }

    public static void aufgabe1() throws FileNotFoundException, IOException{
        File f = new File("./input.txt");
            try (BufferedReader b = new BufferedReader(new FileReader(f))) {       
                String readLine = "";
                int sum = 0;
                while((readLine = b.readLine()) != null){ // String line != null
                    char first = 0;
                    char following = 0;
                    int foundInt = 0;
                    String number = "";
                    int linecount = 0;
                    for (int charcount = 0; charcount < readLine.length(); charcount++){ // Iteration über alle Chars
                        char c = readLine.charAt(charcount);
                        if (Character.isDigit(c)){
                            if (foundInt == 0){
                                first = c;
                                foundInt++;
                            }else {
                                following = c;  
                            }
                        }
                    }
                    if (following == 0){
                        following = first;
                    }
                    number = number + first + following;
                    linecount = Integer.parseInt(number);
                    sum = linecount + sum;
                    System.out.println("foundInt: " + foundInt);
                    System.out.println("First: " + first);
                    System.out.println("Following: " + following);
                    System.out.println("Linecount: " + linecount);
                    System.out.println("Sum: " + sum); 
                }
            }
    }

    public static void aufgabe2() throws IOException{
        String readLine = "";
        File f = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        int sum = 0;
        while((readLine = b.readLine()) != null){ 
            int countM = 0;
            Matcher m = Pattern.compile("(?=([0-9]|(?:one|two|three|four|five|six|seven|eight|nine)))").matcher(readLine);
            int first = 0;
            int following = 0;
            while (m.find()) {
                if (countM == 0){
                    first = translate(m.group(1));
                    following = first;
                }else{
                    following = translate(m.group(1));
                }
                countM ++;
                System.out.println(first + ", " + following);
            }
            sum = sum + (first*10) + following;    
        }
        System.out.println(sum);
        b.close();    
    }

    public static int translate(String input){
        if (isNumeric(input)){
            int i = Integer.parseInt(input);
            return i;
        }
        if(input.equals("one")){
            return 1;
        }
        if (input.equals("two")){
            return 2;
        }
        if (input.equals("three")){
            return 3;
        }if (input.equals("four")){
            return 4;
        }if (input.equals("five")){
            return 5;
        }if (input.equals("six")){
            return 6;
        }if (input.equals("seven")){
            return 7;
        }if (input.equals("eight")){
            return 8;
        }if (input.equals("nine")){
            return 9;
        }
        return 0;
    }
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
}