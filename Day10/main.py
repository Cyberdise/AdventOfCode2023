'''
| is a vertical pipe connecting north and south.
- is a horizontal pipe connecting east and west.
L is a 90-degree bend connecting north and east.
J is a 90-degree bend connecting north and west.
7 is a 90-degree bend connecting south and west.
F is a 90-degree bend connecting south and east.
. is ground; there is no pipe in this tile.
S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
'''

import time


class Node:
    def __init__(self, pxCoord: int, pyCoord: int, pchar: str, pdistance: int, pNeighbors: list, pdirections: list) -> None:
        self.xCoord: int = pxCoord
        self.yCoord: int = pyCoord
        self.char: str = pchar
        self.distance: str = pdistance
        self.neighbors = pNeighbors
        self.directions: list = pdirections
    
    def __repr__(self) -> str:
        return f"(Char: {self.char}, (x, y): ({self.xCoord}, {self.yCoord}), Directions: {self.directions})"

karte: list[list[Node]] = []

def read_input(filename: str) -> list:
    input: list = []
    with open(f"Day10/{filename}.txt", "r") as f:
        input = f.readlines()
    counter: int = 0
    for i in input:
        input[counter] = i.replace("\n", "")
        counter += 1
    return input

def addNeighbors(node: Node):
    x = node.xCoord
    y = node.yCoord 
    
    for direction in node.directions:
        try:
            neigh = karte[y-1][x]
            if direction == "north" and "south" in neigh.directions:
                node.neighbors.append(neigh) 
        except:
            pass
        try:
            neigh = karte[y+1][x]
            if direction == "south" and "north" in neigh.directions:
                node.neighbors.append(neigh)
        except:
            pass
        try:
            neigh = karte[y][x+1]
            if direction == "east" and "west" in neigh.directions:
                node.neighbors.append(neigh)
        except:
            pass
        try:
            neigh = karte[y][x-1]
            if direction == "west" and "east" in neigh.directions:
                node.neighbors.append(neigh)
        except:
            pass   

def task_one(input: list):
    xCoord: int = 0
    yCoord: int = 0

    # Erstellt aus allen Zeichen Node, packt sie mit ihren entsprechenden Koordinaten in eine "Karte"
    for line in input:
        karte.append([])
        for char in line:
            directions = []
            if char == "|" or char == "J" or char == "L" or char == "S":
                directions.append("north")
            if char == "|" or char == "7" or char == "F" or char == "S":
                directions.append("south")
            if char == "7" or char == "J" or char == "-" or char == "S":
                directions.append("west")
            if char == "F" or char == "L" or char == "-" or char == "S":
                directions.append("east")
            newNode: Node = Node(pxCoord=xCoord, pyCoord=yCoord, pchar=char, pdistance=0, pNeighbors=[], pdirections=directions)
            karte[yCoord].append(newNode)
            xCoord += 1
        yCoord += 1
        xCoord = 0

    # Geht durch die Karte durch und gibt jeder Node ihre passenden (also, verbindenden) Nachbarn
    for line in karte:
        for node in line:
            addNeighbors(node=node)

    # doUKnowDaWae
    find_way(karte[2][0], distance=0, isFirstNode=True)
            
def find_way(node: Node, distance: int, priorNode: Node = None, isFirstNode: bool = False):
    print(node)
    if isFirstNode == False and node.char == "S":
        print(distance)
        return distance
    if len(node.neighbors) == 1:
        return 0
    for n in node.neighbors:
        # time.sleep(4)
        if n != priorNode:
            find_way(node=n, distance=distance+1, priorNode=node)

def main():
    # input = read_input("input")
    input = read_input("input")
    task_one(input)

    # for y in karte:
    #     for x in y:
    #         print(x)

    # print("----------------------------------------------")
    # print(karte[0][2].neighbors[0])
    # print(karte[0][2].neighbors[1])

main()