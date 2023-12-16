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
    public boolean dead = false;
    
    public Beam(int yCord, int xCord, String direction){
        this.yCord = yCord;
        this.xCord = xCord;
        this.direction = direction;
    }

    public Beam interact(Tile tile, Beam beam) {
        Object object = tile.object;
        if (object.type.equals("/")){
            if (beam.direction.equals("rechts")) {
                beam.direction = "oben";
                tile.energize();
                return null;
            }else if (beam.direction.equals("links")) {
                beam.direction = "unten";
                tile.energize();
                return null;
            }else if(beam.direction.equals("oben")){
                beam.direction = "rechts";
                tile.energize();
                return null;
            }else if(beam.direction.equals("unten")){
                beam.direction = "links";
                tile.energize();
                return null;
            }else{
                tile.energize();
                return null; 
            }
        }
        else if(object.type.equals("|")){
            if (beam.direction.equals("rechts")) {
                beam.direction = "oben";
                Beam newBeam = new Beam(beam.yCord, beam.xCord, "unten");
                tile.energize();
                return newBeam;
            }else if (beam.direction.equals("links")) {
                beam.direction = "oben";
                Beam newBeam = new Beam(beam.yCord, beam.xCord, "unten");
                tile.energize();
                return newBeam;
            }else if(beam.direction.equals("oben")){
                tile.energize();
                return null;
            }else if(beam.direction.equals("unten")){
                tile.energize();
                return null;
            }else{
                tile.energize();
                return null; 
            }
        }
        else if(object.type.equals("\\")){
            if (beam.direction.equals("rechts")) {
                beam.direction = "unten";
                tile.energize();
                return null;
            }else if (beam.direction.equals("links")) {
                beam.direction = "oben";
                tile.energize();
                return null;
            }else if(beam.direction.equals("oben")){
                beam.direction = "rechts";
                tile.energize();
                return null;
            }else if(beam.direction.equals("unten")){
                beam.direction = "links";
                tile.energize();
                return null;
            }else{
                tile.energize();
                return null; 
            }
        }
        else if(object.type.equals("-")){
            if (beam.direction.equals("rechts")) {
                tile.energize();
                return null;
            }else if (beam.direction.equals("links")) {
                tile.energize();
                return null;
            }else if(beam.direction.equals("oben")){
                beam.direction = "rechts";
                Beam newBeam = new Beam(beam.yCord, beam.xCord, "links");
                tile.energize();
                return newBeam;
            }else if(beam.direction.equals("unten")){
                beam.direction = "rechts";
                Beam newBeam = new Beam(beam.yCord, beam.xCord, "links");
                tile.energize();
                return newBeam;
            }else{
                tile.energize();
                return null; 
            }
        }
        else if(object.type.equals(".")){
            tile.energize();
                return null;
        }else{
            tile.energize();
                return null;
        }
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

class Tile {
    public Object object;
    public boolean energized = false;
    
    public Tile(Object object) {
        this.object = object;
    }
    
    @Override
    public String toString(){
        return object.type;
    }

    public void energize(){
        if (energized ==  false){
            energized = true;
        }
    }
}

class Map {
    List<Tile> zeile = new ArrayList<Tile>();
    List<List<Tile>> map = new ArrayList<List<Tile>>();
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
                Tile tile = new Tile(object);
                zeile.add(tile);
            }
            map.add(zeile);
        }    
    }

    public int countEnergizedTiles(){
        int count = 0;
        for(int zeile = 0; zeile < map.size(); zeile++) {
            for(int tile = 0; tile < map.get(zeile).size(); tile++){
                if(map.get(zeile).get(tile).energized){
                    count++;
                }
            }
        }
        return count;
    }

    public void addNewBeam(int yCord, int xCord, String direction){
        Beam beam = new Beam(yCord, xCord, direction);
        beams.add(beam);
    }



    public void moveBeam(Beam beam) {
        if(beam.direction.equals("rechts")){
            try {
                beam.xCord++;
                Tile tile = map.get(beam.yCord).get(beam.xCord);
                Beam newBeam = beam.interact(tile, beam);
                if (newBeam != null) {
                    beams.add(newBeam);
                }
            } catch (Exception e) {
                beam.dead = true;
            }
            
        }else if(beam.direction.equals("links")){
            try {
                beam.xCord--;
                Tile tile = map.get(beam.yCord).get(beam.xCord);
                Beam newBeam = beam.interact(tile, beam);
                if (newBeam != null) {
                    beams.add(newBeam);
                }
            } catch (Exception e) {
                beam.dead = true;
            }
            
        }else if(beam.direction.equals("oben")){
            try {
                beam.yCord++;
                Tile tile = map.get(beam.yCord).get(beam.xCord);
                Beam newBeam = beam.interact(tile, beam);
                if (newBeam != null) {
                    beams.add(newBeam);
                }
            } catch (Exception e) {
                beam.dead = true;
            }
            
        }else if(beam.direction.equals("unten")){
            try {
               beam.yCord--;
                Tile tile = map.get(beam.yCord).get(beam.xCord);
                Beam newBeam = beam.interact(tile, beam);
                if (newBeam != null) {
                    beams.add(newBeam);
                } 
            } catch (Exception e) {
                beam.dead = true;
            } 
        }
    }

    public void moveBeams(){
        for(int i = 0; i < beams.size(); i++){
            Beam beam = beams.get(i);
            while(beam.dead != true){
                moveBeam(beam);
            }
        }
    }
}

class Main { 
    public static void main(String[] args) throws IOException{
        Map map = new Map("test_input");
        System.out.println("a");
        System.out.println(map.map);
    }
}