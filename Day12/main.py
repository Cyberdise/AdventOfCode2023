def readFile(filename: str):
    with open(f"Day12/{filename}.txt", "r") as f:
        records = f.readlines()
    input = []
    for line in records:
        split = line.split(" ")
        record: str = split[0]
        recovery: str = split[1].replace("\n", "")
        input.append([record, recovery.split(",")])
    print(input)
    return input


def main():
    input = readFile("test_input")


def possible_Arrangements(input: list[list]):
    amountGroups = len(input[1])


hash = "#"
dot = "."
question_mark = "?"


class Baustein:
    length = 0


class Puzzle:
    reihenfolge = {}
    notes = {}

    def generateAllNotes():
        pass


class Note:
    filled: bool
    potentialis: bool
    name = ""


class Solver:
    def part1():
        pass

    def part2():
        pass


if __name__ == "__main__":
    pass
