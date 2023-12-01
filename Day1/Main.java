class Main {
    public static void main(String[] args){
            File f = new File("./Day1/input.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            for (int charcount = 0; charcount < readLine.lenght(); charcount++){
                int foundInt = 0;
                char c = readLine.charAt(charcount);
                int first;
                int following;
                if (c.isDigit()){
                    if (foundInt = 0){
                        first = (int) c;
                        foundInt++;
                    }else {
                        following = (int) c;
                    }
                }
            }

    }
}