import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    > If the beam encounters empty space (.), it continues in the same direction.
    
    > If the beam encounters a mirror (/ or \), the beam is reflected 90 degrees depending on the
     angle of the mirror. For instance, a rightward-moving beam that encounters a / mirror would
      continue upward in the mirror's column, while a rightward-moving beam that encounters a \ mirror
       would continue downward from the mirror's column.
    
    > If the beam encounters the pointy end of a splitter (| or -), the beam passes through the 
    splitter as if the splitter were empty space. For instance, a rightward-moving beam that encounters a
     - splitter would continue in the same direction.
    
    > If the beam encounters the flat side of a splitter (| or -), the beam is split into
     two beams going in each of the two directions the splitter's pointy ends are pointing.
    
     > For instance, a rightward-moving beam that encounters a | 
    splitter would split into two beams: one that continues upward 
    from the splitter's column and one that continues downward from the splitter's column.
 */
class Beam {
    public int xCord = 0;
    public int yCord = 0;
    public String direction = "rechts";
    
    public Beam(int yCord, int xCord, String direction){
        this.yCord = yCord;
        this.xCord = xCord;
        this.direction = direction;
    }
}

class Object {
    public String type; 

    public Object(String type){
        this.type = type;
    } 

    @Override
    public String toString() {
        return type;
    }
}

class Map {
    List<Object> zeile = new ArrayList<Object>();
    List<List<Object>> map = new ArrayList<List<Object>>();
    List<Beam> beams = new ArrayList<Beam>();
    
    public Map(String filename) throws IOException { 
        File file = new File("./%s.txt", filename);
        BufferedReader b = new BufferedReader(new FileReader(file));
        String readLine;
        String buffer = "";
        while((readLine = b.readLine()) != null) {
            for(int indexChar = 0; indexChar < readLine.length(); indexChar++) {
                buffer = "" + readLine.charAt(indexChar);
                Object object = new Object(buffer);
                zeile.add(object);
            }
            map.add(zeile);
        }    
    }

    public void addNewBeam(int yCord, int xCord, String direction){
        Beam beam = new Beam(yCord, xCord, direction);
        beams.add(beam);
    }
}


class Main { 
    public static void main(String[] args) throws IOException{
        Map map = new Map("input");
        System.out.println(map.map);
    }
}