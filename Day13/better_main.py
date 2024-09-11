import numpy as np

# counter: int = 0


def main():
    print(f"Part One: {part_one()}")


# 36102, wrong
def part_one() -> int:
    maps: list[np.array] = generate_maps()

    sum: int = 0
    runde: int = 0
    for map in maps:
        matrix = np.array(map)
        print("------------------------------------")
        print(f"Runde: {runde}")

        vertical_reflection: int = search_for_reflection(matrix.T)
        print(f"Vertikal: {vertical_reflection}")
        horizontal_reflection: int = search_for_reflection(matrix)
        print(f"Horizontal: {horizontal_reflection}")

        if vertical_reflection == horizontal_reflection:
            print(
                "aeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshioaeruilgftqeiugftwzuifgzuadzhfiguzhsdfizhgiouzdrofuzgioerhougkhdohgohseoghshio"
            )
        if vertical_reflection >= horizontal_reflection:
            sum = sum + vertical_reflection
        else:
            sum = sum + horizontal_reflection * 100
        # global counter
        # counter += 1
        print(f"Summe: {sum}")
        runde += 1
    return sum


# Searches for the best reflection vertically and horizontally
def search_for_reflection(map: np.array) -> int:
    longest_reflection: int = 0
    map_length: int = map.shape[0]

    for rowIndex in range(map_length - 1):
        pointer_one = map[rowIndex]
        pointer_two = map[rowIndex + 1]
        # print((pointer_one == pointer_two).all())
        if (pointer_one == pointer_two).all():
            upper_reflection_bound: int = rowIndex
            lower_reflection_bound: int = rowIndex + 1

            # iterate up and down to find length of reflection
            i: int = 0
            current_reflection_length: int = 0
            while (
                upper_reflection_bound - i >= 0
                and lower_reflection_bound + i < map_length
            ):
                upper_reflection_pointer = map[upper_reflection_bound - i]
                lower_reflection_pointer = map[lower_reflection_bound + i]

                if (upper_reflection_pointer == lower_reflection_pointer).all():
                    current_reflection_length = current_reflection_length + 1
                else:
                    break

                i = i + 1
            print(f"Cord: {lower_reflection_bound}")
            # has reached end of map, while reflecting
            if lower_reflection_bound + i >= map_length:
                current_reflection_length = upper_reflection_bound + 1
                # while(True):
                #     global counter
                #     print(counter)

            # changes for longest reflection
            if longest_reflection < current_reflection_length:
                longest_reflection = current_reflection_length
    return longest_reflection


def generate_maps():
    input = []
    # with open("day_13/test_input.txt", "r") as f:
    with open("input.txt", "r") as f:
        input = f.readlines()

    # removes \n
    counter = 0
    for karte in input:
        input[counter] = karte.replace("\n", "")
        counter += 1

    # puts every map into its own list
    maps = [[]]
    counter = 0
    for line in input:
        if line == "":
            maps.append([])
            counter += 1
            continue
        maps[counter].append(list(line))
    return maps


main()
