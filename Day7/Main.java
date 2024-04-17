import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public List<Hand> winningList;

    public static Map<String, Integer> map;

    public static Map<String, Integer> initMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 14);
        map.put("K", 13);
        map.put("Q", 12);
        map.put("J", 11);
        map.put("T", 10);
        map.put("9", 9);
        map.put("8", 8);
        map.put("7", 7);
        map.put("6", 6);
        map.put("5", 5);
        map.put("4", 4);
        map.put("3", 3);
        map.put("2", 2);
        return map;

    }

    public static class Card {
        private int value;

        public Card(String value) {
            this.value = map.get(value);
        }
    }

    public static class Hand {
        public int type;
        public int bid;
        public List<Card> listCards;

        public Hand(List<Card> listCards, String bid) {
            this.type = getType(listCards);
            this.bid = Integer.parseInt(bid);
            this.listCards = listCards;
        }

        @Override
        public String toString() {
            String cards = "";
            for (Card card : listCards) {
                cards = cards + "|" + card.value;
            }
            return "Hand:" + cards + "; " + this.bid + ";" + this.type;
        }

        public int getType(List<Card> listCards) {
            List<MatchGroup> matchGroups = new ArrayList<MatchGroup>();
            for (int i = 0; i < listCards.size(); i++) {

                int valueOfCard = listCards.get(i).value;
                boolean exist = true;
                for (int j = 0; j < matchGroups.size(); j++) {
                    if (valueOfCard == matchGroups.get(j).value) {
                        matchGroups.get(j).oneUp();
                        exist = false;
                    }
                }
                if (!exist) {
                    MatchGroup newMatchGroup = new MatchGroup(valueOfCard);
                    matchGroups.add(newMatchGroup);
                }
            }

            // Welche Karte kommt auf häufigsten vor?
            MatchGroup häufig = null;
            MatchGroup zweithäufig = null;
            for (MatchGroup verteilung : matchGroups) {
                int buffer = 0;
                if (buffer < verteilung.count) {
                    zweithäufig = null;
                    häufig = verteilung;
                }

                if (häufig.count == 5) {
                    return 5;
                } else if (häufig.count == 4) {
                    return 4;
                } else if (häufig.count == 3) {
                    return 3;
                } else if (häufig.count == 2 && zweithäufig.count == 2) {
                    return 2;
                } else if (häufig.count == 2) {
                    return 1;
                } else {
                    int highCard = 0;
                    for (int j = 0; j < matchGroups.size(); j++) {
                        if (highCard < matchGroups.get(j).value) {
                            highCard = matchGroups.get(j).value;
                        }
                    }
                    return -highCard;
                }

            }
            return 0;
        }
    }

    public static class MatchGroup {
        public int value;
        public int count;

        public MatchGroup(int value) {
            this.value = value;
            count = 0;
        }

        public void oneUp() {
            count++;
        }

    }

    public static List<Hand> sortType(List<Hand> listHands) {
        // [1, 5, 3, 5, 6, 7, 4, 3, 2]
        // wie lange gewinnt mit type -> index = coutn;
        // wie lange verliert, bis gewinnt, countVerlieren; index = index -
        // countVerlieren; List<Hand>.add(index, Hand)
        System.out.println(listHands);
        List<Hand> ergebnisListe = new ArrayList<Hand>();

        for (Hand hand : listHands) {
            List<MatchGroup> deck = new ArrayList<MatchGroup>();

            for (Card card : hand.listCards) {
                // ist die Karte im Deck registriert? (Matchgroup?)
                // Matchgroup.get(i).oneUp();
                // else
                MatchGroup cardValue = new MatchGroup(card.value);
                deck.add(cardValue);
            }

            int häufigsten = 0;
            int zweithäufig = 0;
        }

        return ergebnisListe;
    }

    public static void berechneAufgabeA(List<Hand> sortedList) {
        double sum = 0;
        Hand hand;
        for (int rank = 0; rank < sortedList.size(); rank++) {
            hand = sortedList.get(rank);
            int bid = hand.bid;
            sum = sum + bid * rank;
        }
    }

    public static List<Hand> initCards() throws IOException {
        File file = new File("./testinput.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        List<Hand> listHand = new ArrayList<Hand>();
        String bidCaputure = "";
        String capture = "";

        while ((readLine = b.readLine()) != null) { // readLine
            Matcher m = Pattern.compile("([A-Z0-9]*)\\s*(\\d*)\\s*").matcher(readLine);
            List<Card> cardSet = new ArrayList<Card>();
            while (m.find()) {
                capture = m.group(1);
                if (capture == "")
                    break;
                bidCaputure = m.group(2);
                for (int i = 0; i < 5; i++) {
                    String buffer = "";
                    char ch = capture.charAt(i);
                    buffer = "" + ch;
                    Card c = new Card(buffer);
                    cardSet.add(c);
                }
                Hand h = new Hand(cardSet, bidCaputure);
                listHand.add(h);
            }

        }
        return listHand;
    }

    public static void main(String[] args) throws IOException {
        map = initMap();
        berechneAufgabeA(sortType(initCards()));

    }

}
