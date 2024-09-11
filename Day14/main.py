import numpy as np


class Obstical:
    def __init__(self, y, x) -> None:
        self.x = x
        self.y = y
        self.dictionary = {"left": 0, "top": 0, "right": 0, "bottom": 0}


inversDict = {"left": "right", "right": "left", "top": "bottom", "bottom": "top"}

# -----------------------------------------------------------------------------------------------


def generate_init_map(filename: str) -> list[list[str]]:
    with open(f"{filename}", "r") as f:
        input = f.readlines()
    # mapArray = np.array()
    map = input.split("\n")

    print(map)
    return map


def generateNewMapInstanz(list_obst: list[Obstical], direction: str) -> list[list[str]]:
    pass


def getAllOs(map: list[list[map]]):
    pass


def getResult(map):
    pass


# -----------------------------------------------------------------------------------------------


def part_one():
    list_obst: list[Obstical] = []
    generate_init_map("input.txt")
    # generate_init_map
    # getAllOs -> list_pos_obst
    # generateNewMapInstanz


def part_two():
    pass


def main():
    print(f"Part One: {part_one()}")
    print(f"Part Two: {part_two()}")


main()
