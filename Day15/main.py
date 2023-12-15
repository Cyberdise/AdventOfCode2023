import time

def read_input(filename: str) -> list:
    input: list = []
    with open(f"Day15/{filename}.txt", "r") as f:
        input = f.readlines()
    return input[0].split(",")

def calculate_hash(input_string: str) -> int:
    sum: int = 0
    # print(input_string)
    for char in input_string:
        # print(f"{char}: {ord(char)}")
        sum = sum + ord(char)
        sum = sum * 17
        sum = sum % 256
    # print(sum)
    return sum

def task_one(input: list):
    sum: int = 0
    for input_string in input:
        sum += calculate_hash(input_string)
    print(sum)

def calculate_focusing_power(boxes: list[list]) -> int:
    box_counter: int = 0
    dicti_counter: int = 0
    focusing_power_sum: int = 0
    for box in boxes:
        for dicti in box:
            for i in dicti.items():
                dicti_value = i[1]
            focusing_power_sum += ((box_counter+1) * (dicti_counter+1) * dicti_value)
            dicti_counter += 1
        box_counter += 1
        dicti_counter = 0
    return focusing_power_sum


def task_two(input: list):
    boxes: list = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]

    for input_string in input:
        print(input_string)
        if "=" in input_string:
            split_string: list[str] = input_string.split("=")
            label: str = split_string[0]
            operator: str = "="
            lens_value: int = int(split_string[1])
        else:
            split_string: list[str] = input_string.split("-")
            label: str = split_string[0]
            operator: str = "-"
        
        box_value: int = calculate_hash(label)
        
        box = boxes[box_value]

        if operator == "=":
            is_label_in_box = False
            counter = 0
            for b in box:
                if label in b:
                    box[counter][f"{label}"] = lens_value
                    is_label_in_box = True
                counter += 1
            if is_label_in_box == False:
                box.append({f"{label}": lens_value})
        else:
            counter = 0
            for b in box:
                if label in b:
                    box.pop(counter)
                counter += 1

    print(f"Ergebnis von Aufgabenteil 2 ist (trommelwirbel) ... {calculate_focusing_power(boxes)}")

def main():
    input: list = read_input("input")
    # input: list = read_input("test_input")
    # input: list = ["ab", "ot", "pc"]
    # task_one(input=input)

    task_two(input=input)
    

main()