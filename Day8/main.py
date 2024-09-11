import re


operations = ""
curr_pos = ""
dict = {}
# create dict[key] = value


def readInput():
    count = 0
    with open("./Day8/input.txt", "r") as f:
        lines = f.readlines()
    operations = lines[0]

    for line in lines:
        print(f"Line {count}: {line}")
        m = re.match("/\w+/g", line)
        print(m.groups())
    count += 1


def runA():
    for dir in operations:
        if dir == "L":
            pass
        elif dir == "R":
            pass


if __name__ == "__main__":
    readInput()
    pass
