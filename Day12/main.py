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
    

main()


    