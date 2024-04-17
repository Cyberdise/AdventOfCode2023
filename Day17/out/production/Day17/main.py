import itertools
import sys


class Node:
    def __init__(
        self, pxCoord: int, pyCoord: int, pLoss: int, pNeighbors: list, pExplored: bool
    ) -> None:
        self.xCoord: int = pxCoord
        self.yCoord: int = pyCoord
        self.loss: int = pLoss
        self.neighbors: list = pNeighbors

        self.explored: bool = pExplored
        self.previousNode: Node = None
        self.distance: int = sys.maxsize

    def __repr__(self) -> str:
        return f"({self.xCoord}|{self.yCoord}): {self.loss}"


def read_file(input: str):
    data: list[str] = []
    with open(f"{input}.txt", "r") as f:
        data = f.readlines()
    counter = 0
    for i in data:
        data[counter] = list(i)
        counter += 1
    i, j = 0, 0
    for line in data:
        for col in line:
            if data[i][j] == "\n":
                data[i].pop(j)
            j += 1
        i += 1
        j = 0
    return data


all_nodes: list[list[Node]] = []


def initialize_nodes(input: list[str]):
    x, y = 0, 0
    for line in input:
        all_nodes.append([])
        for col in line:
            # print(f"({x}|{y}): {col}")
            newNode: Node = Node(x, y, int(col), [], False)
            all_nodes[y].append(newNode)
            x += 1
        y += 1
        x = 0


def add_neighbors():
    x, y = 0, 0
    for line in all_nodes:
        for col in line:
            if x < len(line) - 1:
                all_nodes[x][y].neighbors.append(all_nodes[x + 1][y])
                all_nodes[x + 1][y].neighbors.append(all_nodes[x][y])
            if y < len(all_nodes) - 1:
                all_nodes[x][y].neighbors.append(all_nodes[x][y + 1])
                all_nodes[x][y + 1].neighbors.append(all_nodes[x][y])
            x += 1
        y += 1
        x = 0


def print_map():
    x, y = 0, 0
    for line in all_nodes:
        for col in line:
            print(col)
            x += 1
        y += 1
        x = 0


def distance_update(oldNode: Node, newNode: Node):
    # Alternative Route (GesLoss bis zur bisherigen Node PLUS den zusätzlichen Loss der aktuellen Node)
    alternative = oldNode.distance + newNode.loss

    # Wenn der die Alternative Route kürzer als die aktuelle Route ist
    if alternative < newNode.distance:
        # ACHTUNG: NUR DIE DISTANZ DER NODE ÄNDERN, DIE IN DER LISTE ALL_NODES IST
        # Die Alternative Route wird aktuelle Distanz gesetzt
        x: int = newNode.xCoord
        y: int = newNode.yCoord
        newNode.distance = alternative

        # Die Alternative Route hängt mit den der vorherigen Node zusammen (Pointer auf den Vorgänger)
        newNode.previousNode = oldNode


def dijkstra():
    prevNode: Node = None

    # StartNode definieren
    startNode: Node = all_nodes[0][0]

    goalNode: Node = all_nodes[len(all_nodes) - 1][len(all_nodes[0]) - 1]

    startNode.distance = 0

    # Alle Nodes in die Unexplored-Liste, dafür wird die all_nodes Liste geflattet
    unexploredNodes: list[Node] = list(itertools.chain(*all_nodes))

    # Solange die kürzeste Distanz ermitteln und die nächste Node nicht der ZielNode ist
    while len(unexploredNodes) > 0:
        # Sortieren aller Nodes nach ihrer Distanz
        unexploredNodes.sort(key=lambda x: x.distance)
        # print(unexploredNodes)
        # Die Node u mit der kürzerster Distanz nehmen
        u: Node = unexploredNodes[0]

        # Wenn die neue Node mit der kürzesten Strecke die GoalNode ist, dann wird der Vorgänger der GoalNode herausgeben
        if u == goalNode:
            print("Goal")
            return u

        # Node u aus der unexploredNodes entfernen
        unexploredNodes.remove(u)

        # Ab hier weiter arbeiten(distance_update, prevNode)
        # Alle Nachbarn exploren, die noch nicht explort sind
        deltaY: int = 0
        deltaX: int = 0
        prevNode: Node = u.previousNode
        # print(u)
        # print(u.previousNode)
        # print(prevNode)
        # print("----------------")
        # print(u)
        previousNodeCounter: int = 1
        for i in range(2):
            if prevNode != None:
                prevNode = prevNode.previousNode
                previousNodeCounter += 1
            else:
                break
        if previousNodeCounter == 3 and prevNode != None:
            deltaY = prevNode.yCoord - u.yCoord
            deltaX = prevNode.xCoord - u.xCoord
        excludedNode: Node = None
        for n in u.neighbors:
            """
            for-Loop

            ausschluss: Node =
            """
            # Wenn der nächste Schritt in x- oder y-Richtung verboten wäre und der Nachbar von der jeweiligen Richtung nicht abweicht, wir dieser Nachbar nicht beachtet
            if ((deltaX == 2 or deltaX == -2) and (n.xCoord != u.xCoord)) or (
                (deltaY == 2 or deltaY == -2) and (n.yCoord != u.yCoord)
            ):
                excludedNode = n
            if n in unexploredNodes and n != excludedNode:
                # Aktualisieren der kürzesten Distanz der !!!!Nodes in der Liste all_nodes!!!!
                distance_update(u, n)

    print("Kein Weg zum Goal möglich")


def backtracking(target: Node):
    path: list[Node] = [target]
    u: Node = target
    while u.previousNode != None:
        # print(u)
        u = u.previousNode
        path.insert(0, u)
    return path


def main():
    test_input: list[str] = read_file("testinput")
    initialize_nodes(test_input)
    # print_map()
    add_neighbors()
    # print(dijkstra())

    # dijkstra()
    # backtracking(all_nodes[12][12])
    ges: int = 0
    for n in backtracking(dijkstra()):
        ges += n.loss
    print(ges)
    print(backtracking(dijkstra()))


main()
