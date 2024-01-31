import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    static List<List<Node>> map = new ArrayList<List<Node>>();

    public static void initTestMap() throws IOException{
        File file = new File("./testinput.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        while((readLine = b.readLine()) != null){
            int y = 0;
            List<Node> line = new ArrayList<Node>();

            for(int x = 0; x < readLine.length(); x++) {
                String buffer = "";
                int integer = Integer.parseInt("" + readLine.charAt(x));
                Node node = new Node(x,y,null,Integer.MAX_VALUE, integer);
                line.add(node);
            }
            map.add(line);
        }
    }
    public static void main(String[] args) throws IOException {
        Map.initTestMap();
        for (List<Node> line : Map.map) {
            for (Node node : line) {
                System.out.print(node.value);
            }
            System.out.println();
        }
    }

}
