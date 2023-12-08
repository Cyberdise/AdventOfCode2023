import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public List<Hand> winningList; 
    
    public Card {
        private String value;

        public Card(String value) {
            this.value = value;
        }
    }

    public Hand {
        public int type;
        public int bid;

        public Hand(List<Card> listCards, String bid){
            this.type = getType(listCards);
            this.bid = Integer.parseInt(bid);
        }
        public int getType(List<Card> listCards) {
            return 2;
        }
    }

    public static List<Hand> sortType(List<Hand> listHands){
        return null;    
    }

    public static void berechneAufgabeA(List<Hand> sortedList) {
        double sum = 0;
        Hand hand;
        for(int rank = 0; rank < sortedList.size(); rank ++){
            hand = sortedList.get(rank);
            int bid = hand.bid;
            sum = sum + bid*rank;
        }
    }



    public static List<Hand> initCards() throws IOException{
        File file = new File("./input.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        List<Hand> listHand = new ArrayList<Hand>(); 
        String bidCaputure = "";
        String capture = "";
        String buffer = "";
        while((readLine = b.readLine()) != null){ //readLine 
            Matcher m = Pattern.compile("([A-Z0-9]*)\\s*(\\d*)\\s*").matcher(readLine);
            List<Card> cardSet = new ArrayList<Card>();
            while(m.find()){
                capture = m.group(1);
                bidCaputure = m.group(2);
            }
            for(int i = 0; i < 5; i++){
                buffer = "A"; //capture muss noch in 5 geteilt werden ["A", "2", "3", "4", "5"]
                Card c = new Card(buffer);
                cardSet.add(c);
            }
            Hand h = new Hand(cardSet,bidCaputure);
            listHand.add(h);
        }
        return listHand;
    }








    public static void main(String[] args) throws IOException{
        Main.berechneAufgabeA(sortType(initCards()));

    }
}