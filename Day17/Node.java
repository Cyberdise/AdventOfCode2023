public class Node {
    int distanceToNode = Integer.MAX_VALUE;
    int y;
    int x;
    int value;
    Node parent = null;

    public Node(int x, int y, Node parent, int distanceToNode, int value){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.distanceToNode = distanceToNode;
        this.value = value;
    }
}
